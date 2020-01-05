package com.simonalong.neo.tx;

//import com.mysql.jdbc.Connection;
//import com.mysql.jdbc.jdbc2.optional.MysqlXAConnection;
//import com.mysql.jdbc.jdbc2.optional.MysqlXid;
import com.mysql.cj.jdbc.JdbcConnection;
import com.mysql.cj.jdbc.MysqlXAConnection;
import com.mysql.cj.jdbc.MysqlXid;
import com.simonalong.neo.Neo;
import com.simonalong.neo.NeoBaseTest;
import com.simonalong.neo.NeoMap;
import com.simonalong.neo.db.xa.NeoXa;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;
import javax.transaction.xa.Xid;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * 分布式XA事务
 * @author zhouzhenyong
 * @since 2019/11/20 下午11:17
 */
public class NeoXATest extends NeoBaseTest {

    public NeoXATest() throws SQLException {
    }

    @Test
    public void testXa() {
        Neo db1 = Neo.connect("jdbc:mysql://127.0.0.1:3306/neo", "neo_test", "neo@Test123");
        Neo db2 = Neo.connect("jdbc:mysql://127.0.0.1:3306/neo2", "neo_test", "neo@Test123");

        NeoXa xa = NeoXa.of("d1", db1, "d2", db2);

        xa.run(() -> {
            Neo d1 = xa.get("d1").initDb(TABLE_NAME);
            Neo d2 = xa.get("d2").initDb(TABLE_NAME);
            d1.insert(TABLE_NAME, NeoMap.of("id", 1, "group", "group111"));
            d2.insert(TABLE_NAME, NeoMap.of("id", 1, "group", "group111"));
        });
    }

    @Test
    @SneakyThrows
    public void testXa0() {
        Neo db1 = Neo.connect("jdbc:mysql://127.0.0.1:3306/neo", "neo_test", "neo@Test123");
        Neo db2 = Neo.connect("jdbc:mysql://127.0.0.1:3306/neo2", "neo_test", "neo@Test123");

        Connection c1 = db1.getConnection();
        Connection c2 = db2.getConnection();

        XAConnection xaConn1 = new MysqlXAConnection(c1.unwrap(JdbcConnection.class), true);
        XAResource rm1 = xaConn1.getXAResource();

        XAConnection xaConn2 = new MysqlXAConnection(c2.unwrap(JdbcConnection.class), true);
        XAResource rm2 = xaConn2.getXAResource();

        Xid xid1 = new MysqlXid("g12345".getBytes(), "b1".getBytes(), 1);
        Xid xid2 = new MysqlXid("g12345".getBytes(), "b2".getBytes(), 1);

        rm1.start(xid1, XAResource.TMNOFLAGS);
        db1.insert(TABLE_NAME, NeoMap.of("id", 8));
        PreparedStatement ps1 = c1.prepareStatement("INSERT into neo_table1(id) VALUES (10)"); ps1.execute();
        rm1.end(xid1, XAResource.TMSUCCESS);

        rm2.start(xid2, XAResource.TMNOFLAGS);
//        db2.insert(TABLE_NAME, NeoMap.of("id", 5));
        PreparedStatement ps2 = c2.prepareStatement("INSERT into neo_table1(id) VALUES (5)"); ps2.execute();
        rm2.end(xid2, XAResource.TMFAIL);

        // ===================两阶段提交================================
        // phase1：询问所有的RM 准备提交事务分支
        int rm1_prepare = rm1.prepare(xid1);
        int rm2_prepare = rm2.prepare(xid2);
        // phase2：提交所有事务分支
        boolean onePhase = false; //TM判断有2个事务分支，所以不能优化为一阶段提交
        if (rm1_prepare == XAResource.XA_OK && rm2_prepare == XAResource.XA_OK) {//所有事务分支都prepare成功，提交所有事务分支
            rm1.commit(xid1, onePhase);
            rm2.commit(xid2, onePhase);
        } else {//如果有事务分支没有成功，则回滚
            rm1.rollback(xid1);
            rm1.rollback(xid2);
        }
    }

    @Test
    @SneakyThrows
    public void cankao(){
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn1 = Neo.connect("jdbc:mysql://127.0.0.1:3306/neo", "neo_test", "neo@Test123").getConnection();
        Connection conn2 = Neo.connect("jdbc:mysql://127.0.0.1:3306/neo2", "neo_test", "neo@Test123").getConnection();

        XAConnection xaConn1 = new MysqlXAConnection(conn1.unwrap(JdbcConnection.class), true);
        XAResource rm1 = xaConn1.getXAResource();

        XAConnection xaConn2 = new MysqlXAConnection(conn2.unwrap(JdbcConnection.class), true);
        XAResource rm2 = xaConn2.getXAResource();

        Xid xid1 = new MysqlXid("g12345".getBytes(), "b1".getBytes(), 1);
        Xid xid2 = new MysqlXid("g12345".getBytes(), "b2".getBytes(), 1);


        // 执行rm1上的事务分支
        rm1.start(xid1, XAResource.TMNOFLAGS);
        PreparedStatement ps1 = conn1.prepareStatement("INSERT into neo_table1(id) VALUES (9)"); ps1.execute();
        rm1.end(xid1, XAResource.TMSUCCESS);

        rm2.start(xid2, XAResource.TMNOFLAGS);
        PreparedStatement ps2 = conn2.prepareStatement("INSERT into neo_table1(id) VALUES (5)"); ps2.execute();
        rm2.end(xid2, XAResource.TMFAIL);

        // ===================两阶段提交================================

        // phase1：询问所有的RM 准备提交事务分支
        int rm1_prepare = rm1.prepare(xid1);
        int rm2_prepare = rm2.prepare(xid2);
        // phase2：提交所有事务分支
        boolean onePhase = false; //TM判断有2个事务分支，所以不能优化为一阶段提交
        if (rm1_prepare == XAResource.XA_OK && rm2_prepare == XAResource.XA_OK) {//所有事务分支都prepare成功，提交所有事务分支
            rm1.commit(xid1, onePhase);
            rm2.commit(xid2, onePhase);
        } else {//如果有事务分支没有成功，则回滚
            rm1.rollback(xid1);
            rm1.rollback(xid2);
        }
    }
}

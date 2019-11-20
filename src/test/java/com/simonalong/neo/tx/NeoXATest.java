package com.simonalong.neo.tx;

import com.simonalong.neo.Neo;
import com.simonalong.neo.NeoBaseTest;
import com.simonalong.neo.NeoMap;
import com.simonalong.neo.db.xa.NeoXa;
import java.sql.SQLException;
import org.junit.Test;

/**
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
            Neo d1 = xa.get("d1");
            d1.insert("t1", NeoMap.of("code", 11));
            Neo d2 = xa.get("d2");
            d2.insert("t1", NeoMap.of("code", 22));
        });
    }
}

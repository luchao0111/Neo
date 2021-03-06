package com.simonalong.neo.db;

import com.simonalong.neo.Columns;
import com.simonalong.neo.NeoMap;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author zhouzhenyong
 * @since 2019/3/17 下午9:11
 */
public class NeoTableTest extends BaseNeoTableTest {

    static NeoTable tinaTest;
    public NeoTableTest() throws SQLException {}

    @Before
    public void before(){
        tinaTest = neo.asTable("neo_table2");
    }

    @Test
    public void testInsert(){
        show(tinaTest.insert(NeoMap.of("group", "table1")));
    }

    @Test
    public void testDelete(){
        show(tinaTest.delete(NeoMap.of("group", "table1")));
    }

    @Test
    public void testUpdate(){
        show(tinaTest.update(NeoMap.of("group", "tableChg"), NeoMap.of("group", "table1")));
    }

    @Test
    public void testOne(){
        show(tinaTest.delete(NeoMap.of("group", "table1")));
    }

    @Test
    public void one(){
        show(tinaTest.one(NeoMap.of("group", "tableChg")));
    }

    @Test
    public void list1(){
        show(tinaTest.list(NeoMap.of("group", "con1")));
    }

    @Test
    public void list2(){
        show(tinaTest.list(Columns.of("group"), NeoMap.of("group", "con1")));
    }

    @Test
    public void list3(){
        show(tinaTest.list(Columns.of("group"), NeoMap.of("group", "con1", "order by", "name desc")));
    }

    @Test
    public void value(){
        show(tinaTest.value("group", NeoMap.of("group", "con1")));
    }

    @Test
    public void value1(){
        show(tinaTest.value(Integer.class, "age", NeoMap.of("group", "ok2")));
    }

    @Test
    public void values(){
        show(tinaTest.values(Integer.class, "age", NeoMap.of("group", "ok2")));
    }

    @Test
    public void page(){
        show(tinaTest.page(Columns.of("group", "name"), NeoMap.of("group", "ok2"), NeoPage.of(1, 10)));
    }

    @Test
    public void page2(){
        show(tinaTest.page(Columns.of("group"), NeoMap.of("group", "ok2"), NeoPage.of(1, 10)));
    }

    @Test
    public void count(){
        show(tinaTest.count());
    }

    @Test
    public void count1(){
        show(tinaTest.count(NeoMap.of("group", "ok2")));
    }

    @Test
    public void getCreateTable(){
        show(tinaTest.getTableCreate());
    }
}

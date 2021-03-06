package com.simonalong.neo.neo;

import com.alibaba.fastjson.JSON;
import com.simonalong.neo.Columns;
import com.simonalong.neo.NeoBaseTest;
import com.simonalong.neo.NeoMap;
import com.simonalong.neo.TableMap;
import com.simonalong.neo.entity.DemoEntity;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import lombok.SneakyThrows;
import org.junit.Test;

/**
 * 测试，其中待测试的表结构请见文件 /db/test.sql
 * @author zhouzhenyong
 * @since 2019/3/12 下午12:47
 */
public class NeoTest extends NeoBaseTest {

    private ExecutorService pool = Executors.newCachedThreadPool();

    public NeoTest() throws SQLException {}

    /**
     * 链接测试
     */
    @Test
    public void connectTest() {
        neo.test();
    }

    /******************************插入******************************/
    /**
     * insert neoMap
     * 单个列数据
     */
    @Test
    @SneakyThrows
    public void testInsert1(){
        NeoMap result = neo.insert(TABLE_NAME, NeoMap.of("group", "ok"));
        // {"name":"","id":22,"group":"ok"}
        show(result);
    }

    /**
     * insert neoMap
     * 多个列数据
     */
    @Test
    @SneakyThrows
    public void testInsert2() {
        NeoMap result = neo.insert(TABLE_NAME, NeoMap.of("user_name", "zhou", "group", "ok"));
        // {"user_name":"zhou","name":"","id":23,"group":"ok"}
        show(result);
    }

    /**
     * neoMap和实体转换
     */
    @Test
    @SneakyThrows
    public void testInsert3(){
        DemoEntity result = neo.insert(TABLE_NAME, NeoMap.of("group", "ok", "name", "haode")).as(DemoEntity.class);
        // DemoEntity(group=ok, name=haode, userName=null, id=26, dataBaseName=null, a=null, sl=0, utilDate=null, sqlDate=null, time=null, timestamp=null)
        show(result);
    }

    /**
     * insert entity
     */
    @Test
    @SneakyThrows
    public void testInsert4(){
        DemoEntity input = new DemoEntity();
        input.setGroup("group1");
        input.setName("name1");
        input.setUserName("user_name1");
        DemoEntity result = neo.insert(TABLE_NAME, input);
        // DemoEntity(group=group1, name=name1, userName=user_name1, id=27, dataBaseName=null, a=null, sl=0, utilDate=null, sqlDate=null, time=null, timestamp=null)
        show(result);
    }

    /**
     * 插入前后的实体变化
     */
    @Test
    @SneakyThrows
    public void testInsert5(){
        NeoMap data = NeoMap.of("group", "ok");
        NeoMap result = neo.insert(TABLE_NAME, data);
        // {"name":"","id":28,"group":"ok"}
        show(result);
        // {"group":"ok"}
        show(data);
    }

    /**
     * 测试插入时间类型，时间类型自动转换，多次插入有主键冲突，忽略就好
     */
    @Test
    @SneakyThrows
    public void testInsert6() {
        String tableName = "neo_table4";
        Long time = new Date().getTime();
        Integer id = neo.value(tableName, Integer.class, "id", NeoMap.of("id", 111));
        if (null != id) {
            NeoMap data = NeoMap.of("id", 111, "time", time, "year", time, "date", time, "datetime", time);
            NeoMap result = neo.insert(tableName, data);
            show(result);
            show(data);
        } else {
            show(neo.one(tableName, NeoMap.of("id", 111)));
        }
    }

    /**
     * 测试异步的数据插入
     */
    @Test
    @SneakyThrows
    public void testInsertAsync1(){
        CompletableFuture<NeoMap> future = neo.insertAsync(TABLE_NAME, NeoMap.of("group", "ok"));
        CountDownLatch latch = new CountDownLatch(1);
        future.thenAccept(r->{
            show(r);
            latch.countDown();
        });
        latch.await();
    }

    /******************************删除******************************/
    @Test
    @SneakyThrows
    public void testDelete1(){
        neo.insert(TABLE_NAME, NeoMap.of("group", "ok"));
        show(neo.delete(TABLE_NAME, NeoMap.of("group", "ok")));
    }

    @Test
    @SneakyThrows
    public void testDelete2(){
        DemoEntity input = new DemoEntity();
        input.setGroup("group1");
        input.setName("name1");
        input.setUserName("user_name1");
        neo.insert(TABLE_NAME, input);
        show(neo.delete(TABLE_NAME, input));
    }

    @Test
    @SneakyThrows
    public void testDelete3(){
        DemoEntity input = new DemoEntity();
        DemoEntity result = neo.insert(TABLE_NAME, input);
        show(neo.delete(TABLE_NAME, result));
    }

    /******************************修改******************************/
    @Test
    @SneakyThrows
    public void testUpdate0(){
        NeoMap dataMap = NeoMap.of("group", "ok2");
        NeoMap dataMap2 = neo.insert(TABLE_NAME, dataMap);
        show("insert 返回值：" + dataMap2.toString());
        dataMap2.put("group", "ok3");
        show("update 入参：" + dataMap2);
        NeoMap dataMap3 = neo.update(TABLE_NAME, dataMap2);
        show("update 返回值：" + dataMap3);
        show("update 执行后入参：" + dataMap2);
    }

    /**
     * 待设置的和搜索的都为neomap
     */
    @Test
    @SneakyThrows
    public void testUpdate1(){
        NeoMap dataMap = NeoMap.of("group", "ok2");
        NeoMap searchMap = NeoMap.of("group", "group2", "name", "name");
        // update neo_table1 set `group`=? where `group` =  ? and `name` =  ?
        show(neo.update(TABLE_NAME, dataMap, searchMap));
    }

    /**
     * 待设置的类型为neoMap，搜索条件为columns，其中的变量名为dataMap中的key
     */
    @Test
    @SneakyThrows
    public void testUpdate2(){
        NeoMap dataMap = NeoMap.of("group", "ok3", "name", "name");
        // update neo_table1 set `group`=?, `name`=? where `name` =  ?
        show(neo.update(TABLE_NAME, dataMap, Columns.of("name")));
    }

    @Test
    @SneakyThrows
    public void testUpdate3(){
        DemoEntity input = new DemoEntity();
        input.setGroup("group2");
        // update neo_table1 set `group`=? where `group` =  ? and `name` =  ?
        show(neo.update(TABLE_NAME, input, NeoMap.of("group", "group1", "name", "name")));
    }

    @Test
    @SneakyThrows
    public void testUpdate4(){
        DemoEntity search = new DemoEntity();
        search.setGroup("group1");

        DemoEntity data = new DemoEntity();
        data.setGroup("group2");
        // update neo_table1 set `group`=? where `group` =  ?
        show(neo.update(TABLE_NAME, data, search));
    }

    /**
     * 指定某个列作为查询条件
     */
    @Test
    @SneakyThrows
    public void testUpdate5(){
        // update neo_table1 set `group`=?, `name`=? where `group` =  ?
        show(neo.update(TABLE_NAME, NeoMap.of("group", "group1", "name", "name2"), Columns.of("group")));
    }

    /**
     * 指定某个列作为查询条件
     * 如果没有搜索条件，则默认按照主键作为搜索条件
     */
    @Test
    @SneakyThrows
    public void testUpdate6(){
        // update neo_table1 set `group`=?, `id`=?, `name`=? where `id` =  ?
        show(neo.update(TABLE_NAME, NeoMap.of("id", 2, "group", "group222", "name", "name2")));
    }

    /**
     * 指定某个列作为查询条件
     * 如果没有搜索条件，则默认按照主键作为搜索条件
     */
    @Test
    @SneakyThrows
    public void testUpdate7(){
        DemoEntity search = new DemoEntity();
        search.setId(281L);
        search.setGroup("group555");
        // update neo_table1 set `group`=?, `id`=? where `id` =  ?
        show(neo.update(TABLE_NAME, search));
    }

    /**
     * 指定某个列作为查询条件
     */
    @Test
    @SneakyThrows
    public void testUpdate61(){
        // update neo_table1 set `group`=?, `id`=?, `name`=? where `id` =  ?
        show(neo.update("neo_table4", NeoMap.of("id", 2, "group", "group222", "name", "name2")));
    }

    /**
     * 指定某个列作为查询条件
     */
    @Test
    @SneakyThrows
    public void testUpdate8(){
        DemoEntity search = new DemoEntity();
        search.setGroup("group555");
        search.setName("name333");
        // update neo_table1 set `group`=?, `name`=? where `name` =  ?
        show(neo.update(TABLE_NAME, search, Columns.of("name")));
    }

    /**
     * 指定某个列作为查询条件
     * 如果采用Columns作为搜索条件，则其中的入参需要根据前面的类型，如果是neoMap则key的值，如果为实体，则为实体名字
     */
    @Test
    @SneakyThrows
    public void testUpdate9(){
        DemoEntity search = new DemoEntity();
        search.setGroup("group555");
        search.setName("name333");
        search.setUserName("userName2222");
        show(neo.update(TABLE_NAME, search, Columns.of("userName")));
    }

    @Test
    @SneakyThrows
    public void testUpdate10(){
        // update neo_table1 set `group`=?, `id`=?, `name`=? where `id` =  ?
        NeoMap dataMap = NeoMap.of("id", 11, "group", "group222", "name", "name2");
        show(neo.update("neo_table1", dataMap));
        show(dataMap);
    }

    /******************************直接执行******************************/
    @Test
    public void testExecute1(){
        show(neo.execute("explain select * from neo_table1 where name ='name'"));
    }

    /**
     * 注意，转换符是直接将对应的输入转换到对应的位置
     */
    @Test
    public void testExecute2(){
//        show(neo.execute("update %s set `group`=?, `name`=%s where id = ?", TABLE_NAME, "group121", "'name123'", 121));
        show(neo.execute("select neo_table1.`group`, neo_table1.`user_name`, neo_table1.`age`, neo_table1.`id`, neo_table1.`name`  from neo_table1 inner join neo_table2 on neo_table1.`id`=neo_table2.`n_id`   where neo_table1.`group` =  ? and neo_table1.`id` =  ? order by sort desc", "group121", 11));
    }

    @Test
    public void testExecute3(){
        show(neo.execute("update neo_table1 set `group`='group1', `name`='name1' where id = 122"));
    }

    @Test
    public void testExecute4(){
        show(neo.execute("select * from neo_table1"));
    }

    @Test
    public void testExecute4_2(){
        show(neo.execute("update neo_table1 set `group` = 'group1'"));
    }

    /**
     * 测试时间
     */
    @Test
    public void testExecute4_3(){
        show(neo.execute("select DATE_FORMAT(NOW(),'%b %d %Y %h:%i %p') as time"));
    }

    /**
     * 测试多结果集
     * CREATE PROCEDURE `pro`()
     * BEGIN
     *   explain select * from neo_table1;
     *   select * from neo_table1;
     * END
     */
    @Test
    public void testExecute5(){
        show(neo.execute("call pro()"));
    }

    @Test
    public void testExecute6(){
        TableMap sql = neo.execute("show create table `xx_test5`").get(0).get(0);
        show("****");
        show(sql.get("Create Table"));
    }

    @Test
    public void testExecute7(){
        show(neo.tx(()->{
            neo.update("neo_table1", NeoMap.of("group", "12"), NeoMap.of("id", 11));
            return neo.value("neo_table1", "group", NeoMap.of("id", 11));
        }));
    }

    /****************************** 查询 ******************************/
    @Test
    public void getColumnNameListTest(){
        show(neo.getColumnNameList(TABLE_NAME));
    }

    @Test
    public void getColumnsTest(){
        show(JSON.toJSONString(neo.getColumnList(TABLE_NAME)));
    }

    @Test
    public void getIndexNameListTest(){
        show(neo.getIndexNameList(TABLE_NAME));
    }

    @Test
    public void getIndexListTest(){
        show(JSON.toJSONString(neo.getIndexList(TABLE_NAME)));
    }

    /****************************** 表的创建语句 ******************************/
    @Test
    public void getTableCreateTest(){
//        mysql version: 5.6
//        CREATE TABLE `xx_test5` (
//          `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
//          `group` char(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '数据来源组，外键关联lk_config_group',
//          `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '' COMMENT '任务name',
//          `user_name` varchar(24) COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '修改人名字',
//          `gander` enum('Y','N') COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别：Y=男；N=女',
//          `biginta` bigint(20) NOT NULL,
//          `binarya` binary(1) NOT NULL,
//          `bit2` bit(1) NOT NULL,
//          `blob2` blob NOT NULL,
//          `boolean2` tinyint(1) NOT NULL,
//          `char1` char(1) COLLATE utf8_unicode_ci NOT NULL,
//          `datetime1` datetime NOT NULL,
//          `date2` date NOT NULL,
//          `decimal1` decimal(10,0) NOT NULL,
//          `double1` double NOT NULL,
//          `enum1` enum('a','b') COLLATE utf8_unicode_ci NOT NULL,
//          `float1` float NOT NULL,
//          `geometry` geometry NOT NULL,
//          `int2` int(11) NOT NULL,
//          `linestring` linestring NOT NULL,
//          `longblob` longblob NOT NULL,
//          `longtext` longtext COLLATE utf8_unicode_ci NOT NULL,
//          `medinumblob` mediumblob NOT NULL,
//          `medinumint` mediumint(9) NOT NULL,
//          `mediumtext` mediumtext COLLATE utf8_unicode_ci NOT NULL,
//          `multilinestring` multilinestring NOT NULL,
//          `multipoint` multipoint NOT NULL,
//          `mutipolygon` multipolygon NOT NULL,
//          `point` point NOT NULL,
//          `polygon` polygon NOT NULL,
//          `smallint` smallint(6) NOT NULL,
//          `text` text COLLATE utf8_unicode_ci NOT NULL,
//          `time` time NOT NULL,
//          `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
//          `tinyblob` tinyblob NOT NULL,
//          `tinyint` tinyint(4) NOT NULL,
//          `tinytext` tinytext COLLATE utf8_unicode_ci NOT NULL,
//          `text1` text COLLATE utf8_unicode_ci NOT NULL,
//          `text1123` text COLLATE utf8_unicode_ci NOT NULL,
//          `time1` time NOT NULL,
//          `timestamp1` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
//          `tinyblob1` tinyblob NOT NULL,
//          `tinyint1` tinyint(4) NOT NULL,
//          `tinytext1` tinytext COLLATE utf8_unicode_ci NOT NULL,
//          `year2` year(4) NOT NULL,
//                PRIMARY KEY (`id`)
//        ) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='配置项';
        show(neo.getTableCreate("xx_test5"));
    }

    /**
     * 测试获取枚举的类型
     */
    @Test
    public void test23(){
        String sql = "`gander` enum('Y','N') COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '性别：Y=男；N=女'";

        String regex = "(?<= enum)\\((.*)\\)";
        Matcher matcher = Pattern.compile(regex).matcher(sql);
        if (matcher.find()) {
            // 'Y','N'
            String enums = matcher.group(1);
            show(enums);
            List<String> dataList = Arrays.stream(enums.split(",")).map(c -> c.substring(1, c.length() - 1))
                .collect(Collectors.toList());
            // [Y, N]
            show(dataList);
        }
    }

//    /**
//     * 全局id生成器
//     */
//    @Test
//    public void testUid(){
//        neo.openUidGenerator();
//        show(neo.getUuid());
//        show(neo.getUuid());
//        show(neo.getUuid());
//        show(neo.getUuid());
//        show(neo.getUuid());
//        show(neo.getUuid());
//        show(neo.getUuid());
//        show(neo.getUuid());
//        show(neo.getUuid());
//        show(neo.getUuid());
//
//        show(neo.getUuid());
//        show(neo.getUuid());
//        show(neo.getUuid());
//    }
}

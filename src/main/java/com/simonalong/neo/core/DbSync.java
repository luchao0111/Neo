package com.simonalong.neo.core;

import com.simonalong.neo.Columns;
import com.simonalong.neo.NeoMap;
import com.simonalong.neo.TableMap;
import com.simonalong.neo.db.NeoPage;
import java.util.List;

/**
 * @author zhouzhenyong
 * @since 2019-08-17 17:16
 */
public interface DbSync extends SyncNeo{

    NeoMap insert(String tableName, NeoMap dataMap);

    <T> T insert(String tableName, T object);


    Integer delete(String tableName, NeoMap searchMap);

    <T> Integer delete(String tableName, T object);

    Integer delete(String tableName, Number id);


    NeoMap update(String tableName, NeoMap dataMap, NeoMap searchMap);

    <T> T update(String tableName, T setEntity, NeoMap searchMap);

    <T> T update(String tableName, T setEntity, T searchEntity);

    <T> NeoMap update(String tableName, NeoMap setMap, T searchEntity);

    NeoMap update(String tableName, NeoMap dataMap, Columns columns);

    <T> T update(String tableName, T entity, Columns columns);

    NeoMap update(String tableName, NeoMap dataMap);

    <T> T update(String tableName, T entity);


    NeoMap one(String tableName, Columns columns, NeoMap searchMap);

    <T> T one(String tableName, Columns columns, T entity);

    NeoMap one(String tableName, Columns columns, Number key);

    NeoMap one(String tableName, NeoMap searchMap);

    <T> T one(String tableName, T entity);

    NeoMap one(String tableName, Number id);

    <T> T one(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap);

    <T> T  one(Class<T> tClass, String tableName, Columns columns, Number key);

    <T> T  one(Class<T> tClass, String tableName, NeoMap searchMap);

    <T> T  one(Class<T> tClass, String tableName, Number id);


    List<NeoMap> list(String tableName, Columns columns, NeoMap searchMap);

    <T> List<T> list(String tableName, Columns columns, T entity);

    List<NeoMap> list(String tableName, NeoMap searchMap);

    <T> List<T> list(String tableName, T entity);

    List<NeoMap> list(String tableName, Columns columns);

    <T> List<T> list(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap);

    <T> List<T> list(Class<T> tClass, String tableName, NeoMap searchMap);

    <T> List<T> list(Class<T> tClass, String tableName, Columns columns);


    <T> T value(String tableName, Class<T> tClass, String field, NeoMap searchMap);

    <T> T value(String tableName, Class<T> tClass, String field, Object entity);

    String value(String tableName, String field, NeoMap searchMap);

    String value(String tableName, String field, Object entity);

    String value(String tableName, String field, Number id);


    <T> List<T> values(String tableName, Class<T> tClass, String field, NeoMap searchMap);

    <T> List<T> values(String tableName, Class<T> tClass, String field, Object entity);

    List<String> values(String tableName, String field, NeoMap searchMap);

    List<String> values(String tableName, String field, Object entity);

    List<String> values(String tableName, String field);


    List<NeoMap> page(String tableName, Columns columns, NeoMap searchMap, NeoPage page);

    <T> List<T> page(String tableName, Columns columns, T entity, NeoPage page);

    List<NeoMap> page(String tableName, NeoMap searchMap, NeoPage page);

    <T> List<T> page(String tableName, T entity, NeoPage page);

    List<NeoMap> page(String tableName, Columns columns, NeoPage page);

    List<NeoMap> page(String tableName, NeoPage page);

    <T> List<T> page(Class<T> tClass, String tableName, Columns columns, NeoMap searchMap, NeoPage page);

    <T> List<T> page(Class<T> tClass, String tableName, NeoMap searchMap, NeoPage page);

    <T> List<T> page(Class<T> tClass, String tableName, Columns columns, NeoPage page);

    <T> List<T> page(Class<T> tClass, String tableName, NeoPage page);


    Integer count(String tableName, NeoMap searchMap);

    Integer count(String tableName, Object entity);

    Integer count(String tableName);


    Boolean exist(String tableName, NeoMap searchMap);

    Boolean exist(String tableName, Object entity);


    Integer batchInsert(String tableName, List<NeoMap> dataMapList);

    <T> Integer batchInsertEntity(String tableName, List<T> dataList);


    Integer batchUpdate(String tableName, List<NeoMap> dataList);

    Integer batchUpdate(String tableName, List<NeoMap> dataList, Columns columns);

    <T> Integer batchUpdateEntity(String tableName, List<T> dataList);

    <T> Integer batchUpdateEntity(String tableName, List<T> dataList, Columns columns);
}

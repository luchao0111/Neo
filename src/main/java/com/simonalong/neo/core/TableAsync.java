package com.simonalong.neo.core;

import com.simonalong.neo.Columns;
import com.simonalong.neo.NeoMap;
import com.simonalong.neo.db.NeoPage;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author zhouzhenyong
 * @since 2019-08-17 18:18
 */
public interface TableAsync extends AsyncNeo{

    CompletableFuture<NeoMap> insertAsync(NeoMap dataMap, Executor executor);

    CompletableFuture<NeoMap> insertAsync(NeoMap dataMap);

    <T> CompletableFuture<T> insertAsync(T object, Executor executor);

    <T> CompletableFuture<T> insertAsync(T object);


    CompletableFuture<Integer> deleteAsync(NeoMap dataMap, Executor executor);

    CompletableFuture<Integer> deleteAsync(NeoMap dataMap);

    <T> CompletableFuture<Integer> deleteAsync(T object, Executor executor);

    <T> CompletableFuture<Integer> deleteAsync(T object);

    CompletableFuture<Integer> deleteAsync(Number id, Executor executor);

    CompletableFuture<Integer> deleteAsync(Number id);


    CompletableFuture<NeoMap> updateAsync(NeoMap dataMap, NeoMap searchMap, Executor executor);

    CompletableFuture<NeoMap> updateAsync(NeoMap dataMap, NeoMap searchMap);

    <T> CompletableFuture<T> updateAsync(T setEntity, NeoMap searchMap, Executor executor);

    <T> CompletableFuture<T> updateAsync(T setEntity, NeoMap searchMap);

    <T> CompletableFuture<T> updateAsync(T setEntity, T searchEntity, Executor executor);

    <T> CompletableFuture<T> updateAsync(T setEntity, T searchEntity);

    CompletableFuture<NeoMap> updateAsync(NeoMap dataMap, Columns columns, Executor executor);

    CompletableFuture<NeoMap> updateAsync(NeoMap dataMap, Columns columns);

    <T> CompletableFuture<T> updateAsync(T entity, Columns columns, Executor executor);

    <T> CompletableFuture<T> updateAsync(T entity, Columns columns);

    CompletableFuture<NeoMap> updateAsync(NeoMap dataMap, Executor executor);

    CompletableFuture<NeoMap> updateAsync(NeoMap dataMap);

    <T> CompletableFuture<T> updateAsync(T entity, Executor executor);

    <T> CompletableFuture<T> updateAsync(T entity);


    CompletableFuture<NeoMap> oneAsync(Columns columns, NeoMap searchMap, Executor executor);

    CompletableFuture<NeoMap> oneAsync(Columns columns, NeoMap searchMap);

    <T> CompletableFuture<T> oneAsync(Columns columns, T entity, Executor executor);

    <T> CompletableFuture<T> oneAsync(Columns columns, T entity);

    CompletableFuture<NeoMap> oneAsync(NeoMap searchMap, Executor executor);

    CompletableFuture<NeoMap> oneAsync(NeoMap searchMap);

    <T> CompletableFuture<T> oneAsync(T entity, Executor executor);

    <T> CompletableFuture<T> oneAsync(T entity);

    CompletableFuture<NeoMap> oneAsync(Number id, Executor executor);

    CompletableFuture<NeoMap> oneAsync(Number id);

    <T> CompletableFuture<T> oneAsync(Class<T> tClass, Columns columns, NeoMap searchMap, Executor executor);

    <T> CompletableFuture<T> oneAsync(Class<T> tClass, Columns columns, NeoMap searchMap);

    <T> CompletableFuture<T> oneAsync(Class<T> tClass, NeoMap searchMap, Executor executor);

    <T> CompletableFuture<T> oneAsync(Class<T> tClass, NeoMap searchMap);

    <T> CompletableFuture<T> oneAsync(Class<T> tClass, Number id, Executor executor);

    <T> CompletableFuture<T> oneAsync(Class<T> tClass, Number id);


    CompletableFuture<List<NeoMap>> listAsync(Columns columns, NeoMap searchMap, Executor executor);

    CompletableFuture<List<NeoMap>> listAsync(Columns columns, NeoMap searchMap);

    <T> CompletableFuture<List<T>> listAsync(Columns columns, T entity, Executor executor);

    <T> CompletableFuture<List<T>> listAsync(Columns columns, T entity);

    CompletableFuture<List<NeoMap>> listAsync(NeoMap searchMap, Executor executor);

    CompletableFuture<List<NeoMap>> listAsync(NeoMap searchMap);

    <T> CompletableFuture<List<T>> listAsync(T entity, Executor executor);

    <T> CompletableFuture<List<T>> listAsync(T entity);

    <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, Columns columns, NeoMap searchMap, Executor executor);

    <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, Columns columns, NeoMap searchMap);

    <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, NeoMap searchMap, Executor executor);

    <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, NeoMap searchMap);


    <T> CompletableFuture<List<T>> valuesAsync(Class<T> tClass, String field, NeoMap searchMap, Executor executor);

    <T> CompletableFuture<List<T>> valuesAsync(Class<T> tClass, String field, NeoMap searchMap);

    <T> CompletableFuture<List<T>> valuesAsync(Class<T> tClass, String field, Object entity, Executor executor);

    <T> CompletableFuture<List<T>> valuesAsync(Class<T> tClass, String field, Object entity);

    CompletableFuture<List<String>> valuesAsync(String field, NeoMap searchMap, Executor executor);

    CompletableFuture<List<String>> valuesAsync(String field, NeoMap searchMap);

    CompletableFuture<List<String>> valuesAsync(String field, Object entity, Executor executor);

    CompletableFuture<List<String>> valuesAsync(String field, Object entity);

    CompletableFuture<List<String>> valuesAsync(String field, Executor executor);

    CompletableFuture<List<String>> valuesAsync(String field);


    <T> CompletableFuture<T> valueAsync(Class<T> tClass, String field, NeoMap searchMap, Executor executor);

    <T> CompletableFuture<T> valueAsync(Class<T> tClass, String field, NeoMap searchMap);

    <T> CompletableFuture<T> valueAsync(Class<T> tClass, String field, Object entity, Executor executor);

    <T> CompletableFuture<T> valueAsync(Class<T> tClass, String field, Object entity);

    CompletableFuture<String> valueAsync(String field, NeoMap searchMap, Executor executor);

    CompletableFuture<String> valueAsync(String field, NeoMap searchMap);

    CompletableFuture<String> valueAsync(String field, Object entity, Executor executor);

    CompletableFuture<String> valueAsync(String field, Object entity);


    CompletableFuture<List<NeoMap>> pageAsync(Columns columns, NeoMap searchMap, NeoPage page, Executor executor);

    CompletableFuture<List<NeoMap>> pageAsync(Columns columns, NeoMap searchMap, NeoPage page);

    <T> CompletableFuture<List<T>> pageAsync(Columns columns, T entity, NeoPage page, Executor executor);

    <T> CompletableFuture<List<T>> pageAsync(Columns columns, T entity, NeoPage page);

    CompletableFuture<List<NeoMap>> pageAsync(NeoMap searchMap, NeoPage page, Executor executor);

    CompletableFuture<List<NeoMap>> pageAsync(NeoMap searchMap, NeoPage page);

    <T> CompletableFuture<List<T>> pageAsync(T entity, NeoPage page, Executor executor);

    <T> CompletableFuture<List<T>> pageAsync(T entity, NeoPage page);

    CompletableFuture<List<NeoMap>> pageAsync(Columns columns, NeoPage page, Executor executor);

    CompletableFuture<List<NeoMap>> pageAsync(Columns columns, NeoPage page);

    CompletableFuture<List<NeoMap>> pageAsync(NeoPage page, Executor executor);

    CompletableFuture<List<NeoMap>> pageAsync(NeoPage page);

    <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, Columns columns, NeoMap searchMap, NeoPage page, Executor executor);

    <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, Columns columns, NeoMap searchMap, NeoPage page);

    <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, NeoMap searchMap, NeoPage page, Executor executor);

    <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, NeoMap searchMap, NeoPage page);

    <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, Columns columns, NeoPage page, Executor executor);

    <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, Columns columns, NeoPage page);

    <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, NeoPage page, Executor executor);

    <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, NeoPage page);


    CompletableFuture<Integer> countAsync(NeoMap searchMap, Executor executor);

    CompletableFuture<Integer> countAsync(NeoMap searchMap);

    CompletableFuture<Integer> countAsync(Object entity, Executor executor);

    CompletableFuture<Integer> countAsync(Object entity);

    CompletableFuture<Integer> countAsync(Executor executor);

    CompletableFuture<Integer> countAsync();


    CompletableFuture<Boolean> existAsync(NeoMap searchMap, Executor executor);

    CompletableFuture<Boolean> existAsync(NeoMap searchMap);

    CompletableFuture<Boolean> existAsync(Object entity, Executor executor);

    CompletableFuture<Boolean> existAsync(Object entity);


    CompletableFuture<Integer> batchInsertAsync(List<NeoMap> dataMapList);

    CompletableFuture<Integer> batchInsertAsync(List<NeoMap> dataMapList, Executor executor);

    <T> CompletableFuture<Integer> batchInsertEntityAsync(List<T> dataList);

    <T> CompletableFuture<Integer> batchInsertEntityAsync(List<T> dataList, Executor executor);


    CompletableFuture<Integer> batchUpdateAsync(List<NeoMap> dataList);

    CompletableFuture<Integer> batchUpdateAsync(List<NeoMap> dataList, Executor executor);

    CompletableFuture<Integer> batchUpdateAsync(List<NeoMap> dataList, Columns columns);

    CompletableFuture<Integer> batchUpdateAsync(List<NeoMap> dataList, Columns columns, Executor executor);

    <T> CompletableFuture<Integer> batchUpdateEntityAsync(List<T> dataList);

    <T> CompletableFuture<Integer> batchUpdateEntityAsync(List<T> dataList, Executor executor);

    <T> CompletableFuture<Integer> batchUpdateEntityAsync(List<T> dataList, Columns columns);

    <T> CompletableFuture<Integer> batchUpdateEntityAsync(List<T> dataList, Columns columns, Executor executor);
}

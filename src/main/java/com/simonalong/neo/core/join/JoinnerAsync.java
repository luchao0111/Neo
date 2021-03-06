package com.simonalong.neo.core.join;

import com.simonalong.neo.Columns;
import com.simonalong.neo.TableMap;
import com.simonalong.neo.core.AsyncNeo;
import com.simonalong.neo.db.TableJoinOn;
import com.simonalong.neo.db.NeoPage;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

/**
 * @author shizi
 * @since 2020/5/23 6:32 PM
 */
public interface JoinnerAsync extends AsyncNeo {

    CompletableFuture<TableMap> oneAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, Executor executor);

    CompletableFuture<TableMap> oneAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap);

    <T> CompletableFuture<T> oneAsync(Class<T> tClass, Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, Executor executor);

    <T> CompletableFuture<T> oneAsync(Class<T> tClass, Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap);


    CompletableFuture<List<TableMap>> listAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, Executor executor);

    CompletableFuture<List<TableMap>> listAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap);

    <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, Executor executor);

    <T> CompletableFuture<List<T>> listAsync(Class<T> tClass, Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap);


    CompletableFuture<String> valueAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, Executor executor);

    CompletableFuture<String> valueAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap);

    <T> CompletableFuture<T> valueAsync(Class<T> tClass, Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, Executor executor);

    <T> CompletableFuture<T> valueAsync(Class<T> tClass, Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap);


    CompletableFuture<List<String>> valuesAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, Executor executor);

    CompletableFuture<List<String>> valuesAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap);

    <T> CompletableFuture<List<T>> valuesAsync(Class<T> tClass, Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, Executor executor);

    <T> CompletableFuture<List<T>> valuesAsync(Class<T> tClass, Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap);


    CompletableFuture<List<TableMap>> pageAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, NeoPage neoPage, Executor executor);

    CompletableFuture<List<TableMap>> pageAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, NeoPage neoPage);

    <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, NeoPage neoPage, Executor executor);

    <T> CompletableFuture<List<T>> pageAsync(Class<T> tClass, Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, NeoPage neoPage);


    CompletableFuture<Integer> countAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap, Executor executor);

    CompletableFuture<Integer> countAsync(Columns joinColumns, TableJoinOn tableJoinOn, TableMap tableMap);
}

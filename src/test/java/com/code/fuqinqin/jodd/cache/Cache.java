package com.code.fuqinqin.jodd.cache;

import java.util.Map;

public interface Cache<K, V> {
    int limit();                                // 缓存容量
    long timeout();                             // 过期时间
    void put(K key, V object);                  // 存值（不带过期时间）
    void put(K key, V object, long timeout);    // 存值（带过期时间）
    V get(K key);                               // 取值
    int prune();                                // 淘汰策略方法
    boolean isFull();                           // 当前缓存是否已满载
    V remove(K key);                            // 删除元素
    void clear();                               // 清空缓存
    int size();                                 // 缓存当前已占用容量
    boolean isEmpty();                          // 当前缓存是否为空
    Map<K, V> snapshot();                       // 获取当前缓存的快照信息
}

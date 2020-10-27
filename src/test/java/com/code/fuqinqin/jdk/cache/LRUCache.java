package com.code.fuqinqin.jdk.cache;

import org.apache.ibatis.cache.decorators.LruCache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author fuqinqin3
 * @date 2020-10-27
 * */
public class LRUCache<K, V> {
    private Node head;
    private Node tail;
    private ConcurrentHashMap<K, Node<K, V>> hashMap;
    private final int MAX_CACHE_SIZE;

    public LRUCache(int cacheSize){
        MAX_CACHE_SIZE = cacheSize;
        hashMap = new ConcurrentHashMap<>();
    }

    public synchronized void put(K key, V value){
        Node node = this.getNode(key);
        if(node == null){
            if(hashMap.size() >= MAX_CACHE_SIZE){
                hashMap.remove(tail.key);
                removeTail();
            }
            node = new Node();
            node.key = key;
        }
        node.value = value;
        move2Head(node);
        hashMap.put(key, node);
    }

    public V get(K key){
        Node node = getNode(key);
        if(node == null){
            return null;
        }
        move2Head(node);
        return (V) node.value;
    }

    /**
     * 将节点移动至head
     *
     * @param node
     * */
    private void move2Head(Node node){
        if(head==null || tail==null){
            head = tail = node;
        }
        if(node == head){
            return;
        }
        if(node.pre != null){
            node.pre.next = node.next;
        }
        if(node.next != null){
            node.next.pre = node.pre;
        }
        node.pre = null;
        node.next = head;
        head.pre = node;
        head = node;
    }

    /**
     * 删除尾部节点
     * */
    private void removeTail(){
        if(tail != null){
            tail = tail.pre;
            if(tail == null){
                head = null;
            }else {
                tail.next = null;
            }
        }
    }

    private Node getNode(K key){
        return hashMap.get(key);
    }

    /**
     * 节点信息
     *
     * @author fuqinqin3
     * @date 2020-10-27
     * */
    class Node<K, V> {
        Node pre;
        Node next;
        K key;
        V value;

        @Override
        public String toString() {
            return "{"+key+" - "+value+"}";
        }
    }

    public static void main(String[] args) {
        LRUCache<String, String> cache = new LRUCache<>(5);
        cache.showCache();
        cache.put("2", "two");
        cache.put("5", "five");
        cache.put("3", "three");
        cache.put("4", "four");
        cache.put("1", "one");
        cache.put("6", "six");
        cache.showCache();
        showMap(cache.hashMap);
        cache.get("3");
        cache.showCache();
        showMap(cache.hashMap);
    }

    private void showCache(){
        if(head == null){
            System.out.println("[]");
            return;
        }
        Node node = head;
        System.out.print("[");
        do {
            System.out.print(node+", ");
            node = node.next;
        }while (node != null);
        System.out.println("]");
    }

    public static <K, V> void showMap(Map<K, V> map){
        System.out.print("HashMap：{");
        for (K k : map.keySet()) {
            System.out.print(map.get(k)+", ");
        }
        System.out.println("}");
    }

}

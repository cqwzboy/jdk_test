package com.code.fuqinqin.shardingJdbc.dataSharding.springAndMybatis;

import com.code.fuqinqin.shardingJdbc.dataSharding.springAndMybatis.entity.Order;
import com.code.fuqinqin.shardingJdbc.dataSharding.springAndMybatis.entity.OrderExample;
import com.code.fuqinqin.shardingJdbc.dataSharding.springAndMybatis.mappers.OrderMapper;
import org.apache.shardingsphere.api.hint.HintManager;
import org.apache.shardingsphere.shardingjdbc.jdbc.core.datasource.ShardingDataSource;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class OrderTest {
    private ApplicationContext ctx;
    private OrderMapper orderMapper;

    @Before
    public void init(){
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderMapper = ctx.getBean(OrderMapper.class);
    }

    @Test
    public void test() throws InterruptedException {
        insert();
//        get();
    }

    private void get() {
        String data = "1|1";
        HintManager hintManager = HintManager.getInstance();
        hintManager.addDatabaseShardingValue("test_order", data);
        hintManager.addTableShardingValue("test_order", data);
        try{
            Long orderId = 1352353635L;
            OrderExample example = new OrderExample();
            example.createCriteria().andOrderIdEqualTo(orderId);
            List<Order> orders = orderMapper.selectByExample(example);
            for (Order order : orders) {
                System.out.println("\t"+order.getId()+" - "+order.getOrderId()+" - "+order.getOrderNo());
            }
        }finally {
            hintManager.close();
        }
    }

    private void insert() {
        String data = "0|0";
        HintManager hintManager = HintManager.getInstance();
        hintManager.addDatabaseShardingValue("test_order", data);
        hintManager.addTableShardingValue("test_order", data);
        try{
            Order order = new Order();
            order.setOrderId(135235363900017L);
            order.setOrderNo(UUID.randomUUID().toString().replaceAll("-", ""));
            order.setGoodsId(72352525L);
            order.setGoodsName("商品名称-1");
            order.setUserId(662525L);
            int rownum = orderMapper.insertSelective(order);
            System.out.println("插入行数 = "+rownum);
        }finally {
            hintManager.close();
        }
    }

    @Test
    public void test2() throws SQLException, ClassNotFoundException {
        ShardingDataSource dataSource = ctx.getBean(ShardingDataSource.class);
        Connection connection = dataSource.getConnection();
//        Connection connection = getConn();
        PreparedStatement pstat = connection.prepareStatement("insert into test_order (order_id, order_no, goods_id, goods_name, user_id ) values (?, ?, ?, ?, ? )");
        pstat.setLong(1, 13523536347L);
        pstat.setString(2, UUID.randomUUID().toString().replaceAll("-", ""));
        pstat.setLong(3, 12515151L);
        pstat.setString(4, "商品名称-2");
        pstat.setLong(5, 325522L);
        int execute = pstat.executeUpdate();
        System.out.println("JDBC插入数据："+execute);
    }

    private Connection getConn() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/sharding_rep", "root", "root");
        return connection;
    }

}

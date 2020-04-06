package com.code.fuqinqin.shardingJdbc.readAndWriteSeperation.springAndMybatis.entity;

import java.io.Serializable;

public class Worker implements Serializable {
    private Long id;

    private String name;

    private Long money;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Long getMoney() {
        return money;
    }

    public void setMoney(Long money) {
        this.money = money;
    }
}
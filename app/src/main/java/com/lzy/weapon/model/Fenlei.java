package com.lzy.weapon.model;

/**
 * Created by Adminstrator on 2018/3/9.
 */

public class Fenlei {
    private int id;
    private String name;
    private String order;

    public Fenlei(int id, String name, String order) {
        this.id = id;
        this.name = name;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }
}

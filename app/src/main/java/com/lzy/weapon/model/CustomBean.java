package com.lzy.weapon.model;

import org.litepal.crud.DataSupport;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class CustomBean extends DataSupport{
    public CustomBean(String country, String country_pic, String desc, int id, String img, String pingfen, String title, String type, String update_time) {
        this.country = country;
        this.country_pic = country_pic;
        this.desc = desc;
        this.id = id;
        this.img = img;
        this.pingfen = pingfen;
        this.title = title;
        this.type = type;
        this.update_time = update_time;
    }
    public CustomBean(String desc, int id, String img,String title) {

        this.desc = desc;
        this.id = id;
        this.img = img;
        this.title = title;

    }
    /**
     * country : 美国
     * country_pic : http://baike.images.huanqiu.com/country/america_s.png
     * desc : 世界上最著名的大口径机枪之一。
     * id : 1
     * img : http://images.huanqiu.com/sarons/2013/08/9b90a2e43263987cad7d1efd474a873e.jpg
     * pingfen : (5.0分)
     * title : 勃朗宁M2HB 0.50英寸机枪
     * type : 机枪 | 二战前
     * update_time : 1周前更新
     */

    private String country;
    private String country_pic;
    private String desc;
    private int id;
    private String img;
    private String pingfen;
    private String title;
    private String type;
    private String update_time;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_pic() {
        return country_pic;
    }

    public void setCountry_pic(String country_pic) {
        this.country_pic = country_pic;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getPingfen() {
        return pingfen;
    }

    public void setPingfen(String pingfen) {
        this.pingfen = pingfen;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(String update_time) {
        this.update_time = update_time;
    }
}

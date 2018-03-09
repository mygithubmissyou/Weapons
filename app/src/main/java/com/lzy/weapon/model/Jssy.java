package com.lzy.weapon.model;

import org.litepal.crud.DataSupport;

/**
 * Created by Adminstrator on 2018/3/8.
 */

public class Jssy extends DataSupport{
    public Jssy(){}

    public Jssy(String descs, int id, String img, String titlesy) {
        this.descs = descs;
        this.id = id;
        this.img = img;
        this.titlesy = titlesy;
    }

    /**
     * desc : 亦称“轻兵器”、“小武器”。单人或战斗小组可携行使用的武器。主要装备对象是步兵、空降兵和海军陆战队，也广泛装备于其他军种和兵种。是军队中装备数量最多的武器。主要用于杀伤生动力量，毁伤轻型装甲车辆及低空目标等。单人武器的质量10千克以内，小组武器的质量几十千克以内。轻武器最初仅指手枪、步枪、冲锋枪、手榴弹等单人使用的武器，后经发展又包括可由战斗小组携行使用的武器，包括机枪、榴弹发射器、火箭发射器及其他便携式武器等。也有人把单兵使用的便携式导弹列入轻武器。轻武器主体是枪械，其发展水平往往代表一个国家轻武器的发展水平。轻武器的主要特点是：质量轻、体积小、操作简便、开火迅速、火力密度大、环境适应性强、能适应多种作战任务；结构简单、便于维修、配套设备少、成本较低；使用范围广，除军队外警察、海关、税务等单位也使用。
     * id : 1
     * img : http://images.huanqiu.com/sarons/2014/04/c42d93f71f2f3980fb1711a4660312a6.jpg
     * title : 轻武器
     */

    private String descs;
    private int id;
    private String img;
    private String titlesy;

    public String getDescs() {
        return descs;
    }

    public void setDescs(String descs) {
        this.descs = descs;
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

    public String getTitlesy() {
        return titlesy;
    }

    public void setTitlesy(String titlesy) {
        this.titlesy = titlesy;
    }
}

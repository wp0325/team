package com.team.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by wang0 on 2016/9/9.
 */
public class Info implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String profession;
    private String phone;
    private String introduction;
    private Timestamp registeTime;

    public Info() {
    }

    public Info(String name, String profession, String phone, String introduction) {
        this.name = name;
        this.profession = profession;
        this.phone = phone;
        this.introduction = introduction;
        this.registeTime = new Timestamp(new Date().getTime());
    }

    public Timestamp getRegisteTime() {
        return registeTime;
    }

    public void setRegisteTime(Timestamp registeTime) {
        this.registeTime = registeTime;
    }

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
        this.name = name;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Info{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", profession='" + profession + '\'' +
                ", phone='" + phone + '\'' +
                ", introduction='" + introduction + '\'' +
                ", registeTime=" + registeTime +
                '}';
    }
}

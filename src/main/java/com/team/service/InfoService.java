package com.team.service;

import com.team.model.Info;

import java.util.List;

/**
 * Created by wang0 on 2016/9/9.
 */
public interface InfoService {

    void create(Info info);

    List<Info> queryall();

    List<String> queryallname();

    List<String> queryallphone();

    int count(String name,String phone);
}

package com.team.service.impl;

import com.team.model.Info;
import com.team.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wang0 on 2016/9/9.
 */

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void create(Info info) {
        String sql = "insert into info(name,profession,phone,introduction,registe_time) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, info.getName(), info.getProfession(), info.getPhone(), info.getIntroduction(), info.getRegisteTime());
    }

    @Override
    public List<Info> queryall() {

        String sql = "select * from info";
//        List<Info> infos =  jdbcTemplate.query(sql,new BeanPropertyRowMapper<Info>(Info.class));
        List<Info> infos = new ArrayList<Info>();
        List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
        if (list!=null){
            for (int i =0;i<list.size();i++){

                Info info = new Info();
                Long id = (Long) list.get(i).get("id");
                String name = (String) list.get(i).get("name");
                String profession = (String) list.get(i).get("profession");
                String phone = (String) list.get(i).get("phone");
                String introduction = (String) list.get(i).get("introduction");
                Timestamp registeTime = (Timestamp) list.get(i).get("registe_time");
                info.setId(id);
                info.setName(name);
                info.setProfession(profession);
                info.setPhone(phone);
                info.setIntroduction(introduction);
                info.setRegisteTime(registeTime);

                infos.add(info);
            }
        }
        return infos;
    }

    @Override
    public List<String> queryallname() {
        String sql = "select name from info";
        List<String> names = new ArrayList<String>();
        List<Map<String,Object>> mapList = jdbcTemplate.queryForList(sql);
        if (mapList!=null){
            for (int i = 0;i<mapList.size();i++){
                names.add((String) mapList.get(i).get("name"));
            }
        }
        return names;
    }

    @Override
    public List<String> queryallphone() {
        String sql = "select phone from info";
        List<String> phones = new ArrayList<String>();
        List<Map<String,Object>> mapList = jdbcTemplate.queryForList(sql);
        if (mapList!=null){
            for (int i = 0;i<mapList.size();i++){
                phones.add((String) mapList.get(i).get("phone"));
            }
        }
        return phones;
    }

    public int count(String name,String phone){
        String sql = "select count(*) from info where name=? and phone=?";
        Integer count = jdbcTemplate.queryForObject(sql,Integer.class,name,phone);
        System.out.println(count);
        return count;
    }

}

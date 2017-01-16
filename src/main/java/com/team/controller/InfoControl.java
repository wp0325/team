package com.team.controller;

import com.team.model.Info;
import com.team.service.InfoService;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.write.*;
import jxl.write.Number;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wang0 on 2016/9/9.
 */

@Controller
@RequestMapping("/info")
public class InfoControl {

    @Autowired
    private InfoService infoService;

    @RequestMapping(value = "/download")
    @ResponseBody
    public void outToExcel(HttpServletResponse response) throws Exception {
        List<Info> infos = new ArrayList<Info>();
        infos = infoService.queryall();

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH时mm分");
        String tixian = "新生报名表.xls";

        String title = sdf.format(date.getTime()) + tixian;
        WritableWorkbook wk = Workbook.createWorkbook(response.getOutputStream());
        WritableSheet sheet = wk.createSheet("新生报名表", 0);
        sheet.mergeCells(0, 0, 5, 0);
        WritableFont titleFont = new WritableFont(WritableFont.createFont("黑体"), 12, WritableFont.BOLD,
                false, UnderlineStyle.NO_UNDERLINE, Colour.LIGHT_BLUE);
        WritableCellFormat titleFormat = new WritableCellFormat();
        titleFormat.setFont(titleFont);
        titleFormat.setAlignment(Alignment.CENTRE);
        titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE);
        titleFormat.setBackground(Colour.GRAY_25);
        titleFormat.setWrap(true);
        Label lab_00 = new Label(0, 0, "新生报名表", titleFormat);
        sheet.addCell(lab_00);
        WritableCellFormat cloumnTitleFormat = new WritableCellFormat();
        cloumnTitleFormat.setFont(new WritableFont(WritableFont.createFont("宋体"), 10, WritableFont.BOLD, false));
        cloumnTitleFormat.setAlignment(Alignment.CENTRE);

        Label lab_01 = new Label(0, 1, "序号", cloumnTitleFormat);
        Label lab_11 = new Label(1, 1, "姓名", cloumnTitleFormat);
        Label lab_21 = new Label(2, 1, "专业", cloumnTitleFormat);
        Label lab_31 = new Label(3, 1, "电话号码", cloumnTitleFormat);
        Label lab_41 = new Label(4, 1, "自我介绍", cloumnTitleFormat);
        Label lab_51 = new Label(5, 1, "报名时间", cloumnTitleFormat);

        sheet.addCell(lab_01);
        sheet.addCell(lab_11);
        sheet.addCell(lab_21);
        sheet.addCell(lab_31);
        sheet.addCell(lab_41);
        sheet.addCell(lab_51);

        DateFormat df = new DateFormat("yyyy-MM-dd hh:mm:ss");
        WritableCellFormat datewcf = new WritableCellFormat(df);

        int j = 0;
        for (int index = 2; index < infos.size() + 2; index++) {
            sheet.addCell(new Number(0, index, infos.get(j).getId()));
            sheet.addCell(new Label(1, index, infos.get(j).getName()));
            sheet.addCell(new Label(2, index, infos.get(j).getProfession()));
            sheet.addCell(new Label(3, index, infos.get(j).getPhone()));
            sheet.addCell(new Label(4, index, infos.get(j).getIntroduction()));
            sheet.addCell(new DateTime(5, index, infos.get(j).getRegisteTime(), datewcf));
            j++;
        }
        wk.write();
        wk.close();
        System.out.println("成功导出一次");
    }

    @RequestMapping("/excelout")
    public String excelout() {
        return "excelOut";
    }

    @RequestMapping("/create")
    @ResponseBody
    public String create(@RequestParam("name") String name,
                         @RequestParam("phone") String phone,
                         @RequestParam("professionId") int professionid,
                         @RequestParam("introduction") String introduction) {

        name = name.trim();
        phone=phone.trim();
        introduction = introduction.trim();
        if (infoService.queryallname().contains(name)) {
            if (infoService.queryallphone().contains(phone)&&infoService.count(name,phone)!=0) {
                return "0";
            } else {
                String profession;
                if (professionid == 1) {
                    profession = "物联网工程";
                } else if (professionid == 2) {
                    profession = "计算机科学与技术";
                } else if (professionid == 3) {
                    profession = "软件工程";
                } else if (professionid == 4) {
                    profession = "网络工程";
                } else profession = "信管";
                Info info = new Info(name, profession, phone, introduction);
                infoService.create(info);
                return "2";
            }
        } else {
            String profession;
            if (professionid == 1) {
                profession = "物联网工程";
            } else if (professionid == 2) {
                profession = "计算机科学与技术";
            } else if (professionid == 3) {
                profession = "软件工程";
            } else if (professionid == 4) {
                profession = "网络工程";
            } else profession = "信管";
            Info info = new Info(name, profession, phone, introduction);
            infoService.create(info);

            return "1";
        }

    }
}

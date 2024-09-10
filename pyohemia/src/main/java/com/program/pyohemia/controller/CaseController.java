package com.program.pyohemia.controller;
import com.github.pagehelper.PageInfo;
import com.program.pyohemia.entity.Case;
import com.program.pyohemia.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

@Controller
public class CaseController {

    @Autowired
    CaseService casesService;

    @RequestMapping(value = "/cases/list")
    public String list(Model model, @RequestParam(name="page",required = true,defaultValue = "1")int page,
                       @RequestParam(name="size",required=true,defaultValue = "25") int size,
                       @RequestParam(name="caseNumber",defaultValue = "")String caseNumber){
        Case cases = new Case().setCaseNumber(caseNumber);
        List<Case> duties = casesService.findAll(page, size,cases);
        PageInfo<Case> pageInfo=new PageInfo(duties);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("cases",cases);
        return "caseslist";
    }

    //跳转添加页面
    @RequestMapping(value = "/cases/toAdd")
    public String toAdd(){
        return "casesAdd";
    }

    //添加功能
    @RequestMapping(value = "/cases/add",method = RequestMethod.POST)
    public String add(@RequestParam("caseNumber")String caseNumber, @RequestParam("patientId")String patientId,
                      @RequestParam("admissionTime")Date admissionTime, @RequestParam("admissionSymptom")String admissionSymptom,
                      @RequestParam("pasthistory")String pasthistory, @RequestParam("allergy")String allergy
                      ){

        Case cases = new Case().setCaseNumber(caseNumber).setPatientId(patientId).setAdmissionTime(admissionTime)
                .setAdmissionSymptom(admissionSymptom).setPasthistory(pasthistory).setAllergy(allergy);
        //添加
        casesService.add(cases);
        return "redirect:/cases/list";
    }

    //删除
    @RequestMapping("/cases/delete/{id}")
    public String delete(@PathVariable("id")int id){
        casesService.delete(id);
        return "redirect:/cases/list";
    }


    //跳转添加页面
    @RequestMapping(value = "/cases/toEdit/{id}")
    public String toEdit(Model model,@PathVariable("id")int id){

        Case cases =casesService.findById(id);
        model.addAttribute("cases",cases);
        return "casesEdit";
    }

    //修改功能
    @RequestMapping(value = "/cases/edit",method = RequestMethod.POST)
    public String edit(@RequestParam("id")int id,@RequestParam("caseNumber")String caseNumber, @RequestParam("patientId")String patientId,
                       @RequestParam("admissionTime")Date admissionTime, @RequestParam("admissionSymptom")String admissionSymptom,
                       @RequestParam("pasthistory")String pasthistory, @RequestParam("allergy")String allergy){
        Case cases=new Case().setId(id).setCaseNumber(caseNumber).setPatientId(patientId).setAdmissionTime(admissionTime)
                .setAdmissionSymptom(admissionSymptom).setPasthistory(pasthistory).setAllergy(allergy);
        //添加
        casesService.edit(cases);
        return "redirect:/cases/list";
    }


}
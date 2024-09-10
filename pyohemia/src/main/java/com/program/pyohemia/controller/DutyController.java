package com.program.pyohemia.controller;
import com.github.pagehelper.PageInfo;
import com.program.pyohemia.entity.Duty;
import com.program.pyohemia.entity.User;
import com.program.pyohemia.service.DutyService;
import com.program.pyohemia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.util.List;

@Controller
public class DutyController {

    @Autowired
    DutyService dutyService;

    @RequestMapping(value = "/duty/list")
    public String list(Model model, @RequestParam(name="page",required = true,defaultValue = "1")int page, @RequestParam(name="size",required=true,defaultValue = "25") int size,@RequestParam(name="dutyTime",defaultValue = "")String dutyTime, @RequestParam(name="dutyDoctor",defaultValue = "")String dutyDoctor){
        Duty duty =new Duty().setDutyDoctor(dutyDoctor).setDutyTime(dutyTime);
        List<Duty> duties = dutyService.findAll(page, size,duty);
        PageInfo<Duty> pageInfo=new PageInfo(duties);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("duty",duty);
        return "dutylist";
    }

    //跳转添加页面
    @RequestMapping(value = "/duty/toAdd")
    public String toAdd(){
        return "dutyAdd";
    }

    //添加功能
    @RequestMapping(value = "/duty/add",method = RequestMethod.POST)
    public String add(@RequestParam("dutyTime")String dutyTime, @RequestParam("dutyDoctor")String dutyDoctor,
                      @RequestParam("sort")String sort, @RequestParam("remarks")String remarks){
        Duty duty=new Duty().setDutyTime(dutyTime).setDutyDoctor(dutyDoctor).setSort(sort).setRemarks(remarks);
        //添加
        dutyService.add(duty);
        return "redirect:/duty/list";
    }

    //删除
    @RequestMapping("/duty/delete/{id}")
    public String delete(@PathVariable("id")int id){
        dutyService.delete(id);
        return "redirect:/duty/list";
    }


    //跳转添加页面
    @RequestMapping(value = "/duty/toEdit/{id}")
    public String toEdit(Model model,@PathVariable("id")int id){

        Duty duty =dutyService.findById(id);
        model.addAttribute("duty",duty);
        return "dutyEdit";
    }

    //修改功能
    @RequestMapping(value = "/duty/edit",method = RequestMethod.POST)
    public String edit(@RequestParam("id")int id,@RequestParam("dutyTime")String dutyTime, @RequestParam("dutyDoctor")String dutyDoctor,
                      @RequestParam("sort")String sort, @RequestParam("remarks")String remarks){
        Duty duty=new Duty().setId(id).setDutyTime(dutyTime).setDutyDoctor(dutyDoctor).setSort(sort).setRemarks(remarks);
        //添加
        dutyService.edit(duty);
        return "redirect:/duty/list";
    }
    //跳转值班表页面
    @RequestMapping(value = "/duty/dutyinfo")
    public String dutylist(Model model){
        Duty duty =new Duty().setDutyDoctor("").setDutyTime("");
        List<Duty> duties = dutyService.findAll(1, 100,duty);
        PageInfo<Duty> pageInfo=new PageInfo(duties);
        model.addAttribute("pageInfo",pageInfo);
        return "dutyinfo";
    }


}
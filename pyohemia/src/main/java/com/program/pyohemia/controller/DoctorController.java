package com.program.pyohemia.controller;
import com.github.pagehelper.PageInfo;
import com.program.pyohemia.entity.Inspect;
import com.program.pyohemia.entity.User;
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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class DoctorController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/manage/list")
    public String list(Model model, @RequestParam(name="page",required = true,defaultValue = "1")int page, @RequestParam(name="size",required=true,defaultValue = "25") int size,@RequestParam(name="name",defaultValue = "")String name){
        User user = new User().setName(name);
        List<User> users = userService.findAll(page, size,name);
        PageInfo<User> pageInfo=new PageInfo(users);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("user",user);

        return "managerlist";
    }

    //添加医生
    @RequestMapping(value = "/reg.do",method = RequestMethod.POST)
    public String add(@RequestParam("username")String username, @RequestParam("name")String name,
                      @RequestParam("password")String password, @RequestParam("gender")String gender,
                      @RequestParam("age")String age, @RequestParam("department")String department,
                      @RequestParam("phone") BigInteger phone, @RequestParam("title")String title){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //加密密码
        String pwd=bCryptPasswordEncoder.encode(password);
        User user = new User().setName(name).setPassword(pwd).setUsername(username).setPhone(phone)
                .setGender(gender).setAge(age).setDepartment(department).setTitle(title);
        //添加
        userService.addUser(user);
        //找到id
        int id = userService.findByUsername(user.getUsername()).getId();
        //添加医生权限
        userService.addUserRole(id);
        return "redirect:/login";
    }


    //跳转编辑页面
    @RequestMapping(value="/manager/toEdit/{id}",method=RequestMethod.GET)
    public String toadd(Model model,@PathVariable("id")int id)
    {
        User user= userService.findById(id);
        model.addAttribute("user", user);
        return "doctorEdit";
    }

    //编辑
    @RequestMapping(value="/manage/edit",method=RequestMethod.POST)
    public String editPatient(@RequestParam("id")int id, @RequestParam("username")String username,
                              @RequestParam("name")String name,
                              @RequestParam("gender")String gender,
                              @RequestParam("age")String age,
                              @RequestParam("title")String title,
                              @RequestParam("phone")BigInteger phone,
                              @RequestParam("department")String department
    ){
        User user = new User().setId(id).setUsername(username).setName(name).setAge(age).setTitle(title)
                .setGender(gender).setDepartment(department).setPhone(phone);
        userService.edit(user);
        return "redirect:/manage/list";
    }

    //删除
    @RequestMapping("/manager/delete/{id}")
    public String delete(@PathVariable("id")int id){
        userService.deleteUser(id);
        return "redirect:/manage/list";
    }
}
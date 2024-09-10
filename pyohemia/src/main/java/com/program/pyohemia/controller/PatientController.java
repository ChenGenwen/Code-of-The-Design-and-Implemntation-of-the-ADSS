package com.program.pyohemia.controller;
import com.github.pagehelper.PageInfo;
import com.program.pyohemia.entity.Inspect;
import com.program.pyohemia.entity.Patient;
import com.program.pyohemia.entity.User;
import com.program.pyohemia.service.InspectService;
import com.program.pyohemia.service.PatientService;
import com.program.pyohemia.service.UserService;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {

    @Autowired
    PatientService patientService;

    @Autowired
    InspectService inspectService;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/patient/list")
    public String list(Model model, @RequestParam(name="page",required = true,defaultValue = "1")int page,
                       @RequestParam(name="size",required=true,defaultValue = "25") int size,
                       @RequestParam(name="name",defaultValue = "")String name,
                       @RequestParam(name="starttime",defaultValue = "")String starttime,
                       @RequestParam(name="endtime",defaultValue = "")String endtime,
                       @RequestParam(name="gender",defaultValue = "")String gender){

        String username = "";
        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String currentUserName = authentication.getName();
                if(!"admin".equals(currentUserName)){
                    username=userService.findByUsername(currentUserName).getName();
                }
            }else{
                System.out.println("没有用户哎！");
            }
        } catch (Exception ex) {
        }

        Patient patient =new Patient().setName(name).setGender(gender).setCreateBy(username);
        List<Patient> patients = patientService.findAll2(page, size,patient,starttime,endtime);
        PageInfo<Patient> pageInfo=new PageInfo(patients);
        model.addAttribute("pageInfo",pageInfo);
        model.addAttribute("patient",patient);
        model.addAttribute("starttime",starttime);
        model.addAttribute("endtime",endtime);
        return "patientlist";
    }

    //跳转添加页面
    @RequestMapping(value = "/patient/toAdd")
    public String toAdd(){
        return "patientAdd";
    }

    //添加功能
    @RequestMapping(value = "/patient/add",method = RequestMethod.POST)
    public String add(@RequestParam("name")String name, @RequestParam("age")String age, @RequestParam("admissionTime")String admissionTime,
                      @RequestParam("gender")String gender, @RequestParam("occupation")String occupation,
                      @RequestParam("phone")BigInteger phone, @RequestParam("address")String adderss,
                      @RequestParam("createBy")String createBy, @RequestParam("nativeplace")String nativeplace,
                      @RequestParam("complaint")String complaint,@RequestParam("admissionSymptom")String admissionSymptom,
                      @RequestParam("pasthistory")String pasthistory, @RequestParam("allergy")String allergy
                      ){

        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date admissionTime2 = null;
        Timestamp sqlDate = null;
        try {
            admissionTime2 = dateFormat.parse(admissionTime.replace("T"," "));
            sqlDate = new Timestamp(admissionTime2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }


        Date createDate = new Date(System.currentTimeMillis());

        Patient patient=new Patient()
                .setName(name)
                .setGender(gender)
                .setAge(age)
                .setAddress(adderss)
                .setCreateBy(createBy)
                .setCreateDate(createDate)
                .setOccupation(occupation)
                .setPhone(phone)
                .setNativeplace(nativeplace)
                .setAdmissionTime(sqlDate)
                .setComplaint(complaint)
                .setAdmissionSymptom(admissionSymptom)
                .setPasthistory(pasthistory)
                .setAllergy(allergy);
        //添加
        patientService.add(patient);
        return "redirect:/patient/list";
    }

    //删除
    @RequestMapping("/patient/delete/{id}")
    public String delete(@PathVariable("id")int id){
        patientService.delete(id);
        return "redirect:/patient/list";
    }


    //删除检测数据
    @RequestMapping("/patient/deleteTest/{id}")
    public String deleteTest(@PathVariable("id")int id){
        Inspect inspect = new Inspect();
        inspect = inspectService.findById(id);

        inspectService.delete(id);
        return "redirect:/patient/info/"+inspect.getPatientId();

    }


    //跳转添加页面
    @RequestMapping(value = "/patient/toEdit/{id}")
    public String toEdit(Model model,@PathVariable("id")int id){
        SimpleDateFormat sdf2= new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Patient patient =patientService.findById(id);
        String patienttime= patient.getAdmissionTime().toString().replace(" ", "T");
        model.addAttribute("patient",patient);
        model.addAttribute("patienttime",patienttime);
        return "patientEdit";
    }

    //修改功能
    @RequestMapping(value = "/patient/edit",method = RequestMethod.POST)
    public String edit(@RequestParam("id")int id,@RequestParam("name")String name, @RequestParam("age")String age,
                       @RequestParam("gender")String gender, @RequestParam("occupation")String occupation,
                       @RequestParam("phone")BigInteger phone, @RequestParam("address")String adderss,
                       @RequestParam("createBy")String createBy, @RequestParam("address")String nativeplace,
                       @RequestParam("complaint")String complaint,
                       @RequestParam("admissionTime")String admissionTime, @RequestParam("admissionSymptom")String admissionSymptom,
                       @RequestParam("pasthistory")String pasthistory, @RequestParam("allergy")String allergy){


        //TODO
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date admissionTime2 = null;
        Timestamp sqlDate = null;
        try {
            admissionTime2 = dateFormat.parse(admissionTime.replace("T"," "));
            sqlDate = new Timestamp(admissionTime2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        //TODO END

        Patient patient=new Patient().setId(id)
                .setName(name)
                .setGender(gender).setAge(age).setAddress(adderss).setCreateBy(createBy)
                .setOccupation(occupation).setPhone(phone).setNativeplace(nativeplace)
                .setAdmissionTime(sqlDate).setComplaint(complaint)
                .setAdmissionSymptom(admissionSymptom).setPasthistory(pasthistory).setAllergy(allergy);
        //添加
        patientService.edit(patient);
        return "redirect:/patient/list";
    }

    //跳转患者详情页面
    @RequestMapping(value="/patient/info/{id}",method=RequestMethod.GET)
    public String info(Model model,@PathVariable("id")int id)
    {
        Patient patient = patientService.findById(id);
        model.addAttribute("patient",patient);
        return "patientInfo";
    }

    //跳转检测添加页面
    @RequestMapping(value="/patient/toAddTest/{id}",method=RequestMethod.GET)
    public String toTest(Model model,@PathVariable("id")int id)
    {
        model.addAttribute("patientId",id);
        return "patientAddTest";
    }
    //跳转检测编辑页面
    @RequestMapping(value="/patient/toEditTest/{id}",method=RequestMethod.GET)
    public String toEditTest(Model model,@PathVariable("id")int id)
    {
        Inspect inspect= inspectService.findById(id);
        String inspecttime= inspect.getCreateDate().toString().replace(" ", "T");
        model.addAttribute("inspect",inspect);
        model.addAttribute("inspecttime",inspecttime);
        return "patientEditTest";
    }
    //跳转查看页面
    @RequestMapping(value="/patient/toViewTest/{id}",method=RequestMethod.GET)
    public String toViewTest(Model model,@PathVariable("id")int id)
    {
        Inspect inspect= inspectService.findById(id);
        String inspecttime= inspect.getCreateDate().toString().replace(" ", "T");
        model.addAttribute("inspect",inspect);
        model.addAttribute("inspecttime",inspecttime);
        return "patientViewTest";
    }



    //添加检测
    @RequestMapping(value="/patient/addTest",method=RequestMethod.POST)
    public String addPatient(@RequestParam("patientId")int patientId, @RequestParam("createDate")String createDate,
                             @RequestParam("meanArterialPressure")String meanArterialPressure,@RequestParam("dopamineDose")String dopamineDose,
                             @RequestParam("adrenalineDose")String adrenalineDose,@RequestParam("norepinephrineDose")String norepinephrineDose,
                             @RequestParam("breathing")String breathing,@RequestParam("creatinine")String creatinine,
                             @RequestParam("urinevolume")String urinevolume,@RequestParam("bilirubin")String bilirubin,
                             @RequestParam("dobutamine")String dobutamine,@RequestParam("paoz")String paoz,
                             @RequestParam("bloodPlatelet")String bloodPlatelet,@RequestParam("gcs")String gcs,
                             @RequestParam("totalBilirubin")String totalBilirubin,@RequestParam("directBilirubin")String directBilirubin,
                             @RequestParam("urinaryCreatinine")String urinaryCreatinine,@RequestParam("ph")String ph,
                             @RequestParam("paco2")String paco2,@RequestParam("hco3")String hco3,
                             @RequestParam("ast")String ast,@RequestParam("bun")String bun,
                             @RequestParam("calcium")String calcium,@RequestParam("glucose")String glucose,
                             @RequestParam("lactate")String lactate,@RequestParam("magnesium")String magnesium,
                             @RequestParam("potassium")String potassium,@RequestParam("phosphate")String phosphate,
                             @RequestParam("hct")String hct,@RequestParam("hgb")String hgb,
                             @RequestParam("wbc")String wbc,@RequestParam("fibrinogen")String fibrinogen,
                             @RequestParam("heartrate")String heartrate,@RequestParam("troponin")String troponin
                             ){
        //TODO
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date admissionTime2 = null;
        Timestamp sqlDate = null;
        try {
            admissionTime2 = dateFormat.parse(createDate.replace("T"," "));
            sqlDate = new Timestamp(admissionTime2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //TODO END
        Inspect inspect = new Inspect().setPatientId(patientId).setCreateDate(sqlDate).setMeanArterialPressure(meanArterialPressure)
                .setDopamineDose(dopamineDose).setAdrenalineDose(adrenalineDose).setNorepinephrineDose(norepinephrineDose)
                .setBreathing(breathing).setCreatinine(creatinine).setUrinevolume(urinevolume).setBilirubin(bilirubin)
                .setBloodPlatelet(bloodPlatelet).setDobutamine(dobutamine).setPaoz(paoz).setGcs(gcs)
                .setTotalBilirubin(totalBilirubin).setDirectBilirubin(directBilirubin).setUrinaryCreatinine(urinaryCreatinine).setPh(ph)
                .setPaco2(paco2).setHco3(hco3).setAst(ast).setBun(bun).setCalcium(calcium).setGlucose(glucose)
                .setLactate(lactate).setMagnesium(magnesium).setPhosphate(phosphate).setPotassium(potassium)
                .setHct(hct).setHgb(hgb).setWbc(wbc).setFibrinogen(fibrinogen).setTroponin(troponin).setHeartrate(heartrate);


        int s = sum(inspect);
        System.out.println(s);
        inspect.setOverallScore(String.valueOf(s));


        inspectService.add(inspect);
        return "redirect:/patient/info/"+inspect.getPatientId();
    }


    //编辑检测
    @RequestMapping(value="/patient/editTest",method=RequestMethod.POST)
    public String editPatient(
            @RequestParam("patientId")int patientId,
            @RequestParam("createDate")String createDate,
                             @RequestParam("meanArterialPressure")String meanArterialPressure,@RequestParam("dopamineDose")String dopamineDose,
                             @RequestParam("adrenalineDose")String adrenalineDose,@RequestParam("norepinephrineDose")String norepinephrineDose,
                             @RequestParam("breathing")String breathing,@RequestParam("creatinine")String creatinine,
                             @RequestParam("urinevolume")String urinevolume,@RequestParam("bilirubin")String bilirubin,
                             @RequestParam("dobutamine")String dobutamine,@RequestParam("paoz")String paoz,
                             @RequestParam("bloodPlatelet")String bloodPlatelet,@RequestParam("gcs")String gcs,
                              @RequestParam("totalBilirubin")String totalBilirubin,@RequestParam("directBilirubin")String directBilirubin,
                              @RequestParam("urinaryCreatinine")String urinaryCreatinine,@RequestParam("ph")String ph,
                              @RequestParam("paco2")String paco2,@RequestParam("hco3")String hco3,
                              @RequestParam("ast")String ast,@RequestParam("bun")String bun,
                              @RequestParam("calcium")String calcium,@RequestParam("glucose")String glucose,
                              @RequestParam("lactate")String lactate,@RequestParam("magnesium")String magnesium,
                              @RequestParam("potassium")String potassium,@RequestParam("phosphate")String phosphate,
                              @RequestParam("hct")String hct,@RequestParam("hgb")String hgb,
                              @RequestParam("wbc")String wbc,@RequestParam("fibrinogen")String fibrinogen,
                              @RequestParam("heartrate")String heartrate,@RequestParam("troponin")String troponin,
                              @RequestParam("id")int id
                             ){
        //TODO
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date admissionTime2 = null;
        Timestamp sqlDate = null;
        try {
            admissionTime2 = dateFormat.parse(createDate.replace("T"," "));
            sqlDate = new Timestamp(admissionTime2.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //TODO END
        Inspect inspect= new Inspect().setPatientId(patientId).setCreateDate(sqlDate).setMeanArterialPressure(meanArterialPressure)
                .setDopamineDose(dopamineDose).setAdrenalineDose(adrenalineDose).setNorepinephrineDose(norepinephrineDose)
                .setBreathing(breathing).setCreatinine(creatinine).setUrinevolume(urinevolume).setBilirubin(bilirubin)
                .setBloodPlatelet(bloodPlatelet).setDobutamine(dobutamine).setPaoz(paoz).setGcs(gcs).setId(id)
                .setTotalBilirubin(totalBilirubin).setDirectBilirubin(directBilirubin).setUrinaryCreatinine(urinaryCreatinine).setPh(ph)
                .setPaco2(paco2).setHco3(hco3).setAst(ast).setBun(bun).setCalcium(calcium).setGlucose(glucose)
                .setLactate(lactate).setMagnesium(magnesium).setPhosphate(phosphate).setPotassium(potassium)
                .setHct(hct).setHgb(hgb).setWbc(wbc).setFibrinogen(fibrinogen).setTroponin(troponin).setHeartrate(heartrate);


        int s = sum(inspect);
        inspect.setOverallScore(String.valueOf(s));
        inspectService.edit(inspect);
        return "redirect:/patient/info/"+inspect.getPatientId();
    }


    public int sum(Inspect inspect) {
        int socre1 = 0;
        int socre2 = 0;
        int socre3 = 0;
        int socre4 = 0;
        int socre5 = 0;
        int socre6 = 0;


        //循环系统
        int a = 0;
        if (Double.parseDouble(inspect.getMeanArterialPressure()) < 70) {
            a = 1;
        } else {
            a = 0;
        }
        int b = 0;
        if (Double.parseDouble(inspect.getDopamineDose()) <= 5) {
            b = 2;
        } else if (Double.parseDouble(inspect.getDopamineDose()) > 15) {
            b = 4;
        } else {
            b = 3;
        }
        int c = 0;
        if (Double.parseDouble(inspect.getAdrenalineDose()) <= 0.1) {
            c = 3;
        } else {
            c = 4;
        }
        int d = 0;
        if (Double.parseDouble(inspect.getNorepinephrineDose()) <= 0.1) {
            d = 3;
        } else {
            d = 4;
        }
        int e = 0;
        if ("是".equals(inspect.getDobutamine())) {
            e = 2;
        }
        List<Integer> integerList = new ArrayList<>();
        integerList.add(a);
        integerList.add(b);
        integerList.add(c);
        integerList.add(d);
        integerList.add(e);
        for(int i=0;i<integerList.size();i++){
            if(socre1<integerList.get(i)){
                socre1 = integerList.get(i);
            }
        }

        //呼吸系统
        int a2 = 0;
        if (Double.parseDouble(inspect.getPaoz()) >= 300&&Double.parseDouble(inspect.getPaoz()) < 400) {
            a2 = 1;
        } else if (Double.parseDouble(inspect.getPaoz()) >= 200&&Double.parseDouble(inspect.getPaoz()) < 300) {
            a2 = 2;
        } else if (Double.parseDouble(inspect.getPaoz()) >= 100&&Double.parseDouble(inspect.getPaoz()) < 200) {
            a2 = 3;
        }else if (Double.parseDouble(inspect.getPaoz()) < 100) {
            a2 = 4;
        }
        int b2 = 0;
        if ("是".equals(inspect.getBreathing())) {
            b2 = 3;
        }else{
            b2 = 4;
        }
        socre2 = a2 > b2 ? a2 : b2;


        //肾脏
        int a3 = 0;
        if (Double.parseDouble(inspect.getCreatinine()) >= 110&&Double.parseDouble(inspect.getCreatinine()) < 170) {
            a3 = 1;
        } else if (Double.parseDouble(inspect.getCreatinine()) >= 171&&Double.parseDouble(inspect.getCreatinine()) < 299) {
            a3 = 2;
        } else if (Double.parseDouble(inspect.getCreatinine()) >= 300&&Double.parseDouble(inspect.getCreatinine()) < 440) {
            a3 = 3;
        }else if (Double.parseDouble(inspect.getCreatinine()) > 440) {
            a3 = 4;
        }
        int b3 = 0;
        if (Double.parseDouble(inspect.getUrinevolume()) >= 200&&Double.parseDouble(inspect.getUrinevolume()) < 500) {
            b3 = 3;
        } else if (Double.parseDouble(inspect.getCreatinine()) < 200) {
            b3 = 4;
        }
        socre3 = a3 > b3 ? a3 : b3;


        //肝脏
        if (Double.parseDouble(inspect.getBilirubin()) >= 20&&Double.parseDouble(inspect.getBilirubin()) <= 32) {
            socre4 = 1;
        } else if (Double.parseDouble(inspect.getBilirubin()) >= 33&&Double.parseDouble(inspect.getBilirubin()) <= 101) {
            socre4 = 2;
        }else if (Double.parseDouble(inspect.getBilirubin()) >= 102&&Double.parseDouble(inspect.getBilirubin()) <= 204) {
            socre4 = 3;
        }else if (Double.parseDouble(inspect.getBilirubin()) >204) {
            socre4 = 4;
        }

        //凝血
        if (Double.parseDouble(inspect.getBloodPlatelet()) >= 100&&Double.parseDouble(inspect.getBloodPlatelet()) <= 149) {
            socre5 = 1;
        } else if (Double.parseDouble(inspect.getBloodPlatelet()) >= 50&&Double.parseDouble(inspect.getBloodPlatelet()) <= 99) {
            socre5 = 2;
        }else if (Double.parseDouble(inspect.getBloodPlatelet()) >= 20&&Double.parseDouble(inspect.getBloodPlatelet()) <= 49) {
            socre5 = 3;
        }else if (Double.parseDouble(inspect.getBloodPlatelet()) <20) {
            socre5 = 4;
        }

        //神经
        if (Double.parseDouble(inspect.getGcs()) >= 13&&Double.parseDouble(inspect.getGcs()) <= 14) {
            socre6 = 1;
        } else if (Double.parseDouble(inspect.getGcs()) >= 10&&Double.parseDouble(inspect.getGcs()) <= 12) {
            socre6 = 2;
        }else if (Double.parseDouble(inspect.getGcs()) >= 6&&Double.parseDouble(inspect.getGcs()) <= 9) {
            socre6 = 3;
        }else if (Double.parseDouble(inspect.getGcs()) <=5) {
            socre6 = 4;
        }

        int score = socre1 + socre2 + socre3 + socre4 + socre5 + socre6;


        return score;
    }



    }

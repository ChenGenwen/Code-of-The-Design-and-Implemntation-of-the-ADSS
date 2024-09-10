package com.program.pyohemia.controller;
import com.program.pyohemia.entity.IndexInfo;
import com.program.pyohemia.entity.Inspect;
import com.program.pyohemia.entity.Patient;
import com.program.pyohemia.service.InspectService;
import com.program.pyohemia.service.PatientService;
import com.program.pyohemia.service.UserService;
import com.program.pyohemia.service.iml.InspectServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Controller
public class IndexController {
    @Autowired
    PatientService patientService;

    @Autowired
    InspectService inspectService;

    @Autowired
    UserService userService;

    /**
     * 接纳患者分析
     * @param model
     * @param starttime
     * @param endtime
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/indexpage",produces="application/json;charset=UTF-8")
    public String index(Model model, @RequestParam(name="starttime",defaultValue = "")String starttime,
                        @RequestParam(name="endtime",defaultValue = "")String endtime) throws ParseException {
        if("".equals(starttime)){
            Date date= new Date(System.currentTimeMillis()-(6*86400000));
            starttime = date.toString();
        }
        if("".equals(endtime)){
            Date date= new Date(System.currentTimeMillis());
            endtime =  date.toString();
        }
        IndexInfo indexInfo=new IndexInfo();
        patientDatas(indexInfo,starttime,endtime);//折线统计图
        model.addAttribute("indexInfo",indexInfo);
        model.addAttribute("starttime",starttime);
        model.addAttribute("endtime",endtime);
        return "index";
    }

    /**
     * 检测数据分析
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/indexpage2",produces="application/json;charset=UTF-8")
    public String index2(Model model, @RequestParam(name="id",defaultValue = "")String id, @RequestParam("dataitem")String dataitem) throws ParseException {
        List<Patient> patients = new ArrayList<>();
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

        Patient patient =new Patient().setCreateBy(username);

        patients= patientService.findAll(1,100,patient);
        //默认显示第一位病人
        if("".equals(id)&&patients!=null&&patients.size()!=0){
            id = Integer.toString(patients.get(0).getId());
        }

        //默认显示第一项检测数据

        IndexInfo indexInfo=new IndexInfo();
        caseDatas(indexInfo,id,dataitem);//折线统计图
        model.addAttribute("indexInfo",indexInfo);
        if(!"".equals(id)){

            model.addAttribute("id",Integer.parseInt(id));
        }
        model.addAttribute("patients",patients);
        model.addAttribute("dataitem",dataitem);
        return "index2";
    }

    /**
     * 检测数据分析
     * @param model
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "/indexpage3",produces="application/json;charset=UTF-8")
    public String index3(Model model,  @RequestParam(name="name",defaultValue = "")String name,@RequestParam(name="starttime",defaultValue = "")String starttime) throws ParseException {
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        java.util.Date dBegin = null;
        if(!"".equals(starttime)){
            dBegin = dateFormat.parse(starttime.replace("T"," "));

        }
        System.out.println(dBegin);

        Patient patient =new Patient().setName(name);
        Patient patient2 =new Patient();
        List<Patient> patients = patientService.findAll(0, 11,patient);
        if(patients.size()>0&&!"".equals(name)){
            patient = patients.get(0);
            patient2 = patientService.findByIdDate(patient.getId(),starttime.replace("T"," "));

            model.addAttribute("overallScore",Integer.parseInt(patient2.getInspects().get(0).getOverallScore()));
        }
        model.addAttribute("patient",patient2);
        model.addAttribute("name",name);
        model.addAttribute("starttime",starttime);

        return "index3";
    }

    //数据
    public void patientDatas(IndexInfo indexInfo,String starttime,String endtime) throws ParseException {
        List<String> dateList = findDaysStr(starttime, endtime);
        List patientNums=new ArrayList();
        List<Long> dates=new ArrayList();


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



        for(int i=0;i<dateList.size();i++){
            java.util.Date parse = new SimpleDateFormat("yyyy-MM-dd").parse(dateList.get(i));
            Long time1 = parse.getTime();
            dates.add(time1);
            String num=patientService.getPatientGroupByDate(dateList.get(i),username);
            patientNums.add(num);
        }
        indexInfo.setDates(dates).setPatientNums(patientNums);
    }

    public  List findDaysStr(String startTime, String endTime) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date dBegin = null;
        java.util.Date dEnd = null;
        try {
            dBegin = sdf.parse(startTime);
            dEnd = sdf.parse(endTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        List daysStrList = new ArrayList();
        daysStrList.add(sdf.format(dBegin));
        Calendar calBegin = Calendar.getInstance();
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        calEnd.setTime(dEnd);

        while (dEnd.after(calBegin.getTime())) {
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            String dayStr = sdf.format(calBegin.getTime());
            daysStrList.add(dayStr);
        }
        return daysStrList;
    }

    public void caseDatas(IndexInfo indexInfo,String id,String dataitem) throws ParseException {
        List<String> list=new ArrayList();
        List<Long> dates=new ArrayList();
        //查询出该name患者所有的检测日期，按时间倒序排序
        Patient patient = new Patient();
        if(!"".equals(id)){
            patient = patientService.findById(Integer.parseInt(id));
        }
        if(patient!=null&&!"".equals(id)){
            List<Inspect> inspectList = patient.getInspects();
            List<String> list1=new ArrayList();
            List<String> list2=new ArrayList();
            List<String> list3=new ArrayList();
            List<String> list4=new ArrayList();
            List<String> list5=new ArrayList();
            List<String> list6=new ArrayList();
            List<String> list7=new ArrayList();
            List<String> list8=new ArrayList();
            List<String> list9=new ArrayList();
            for(Inspect i:inspectList){
                dates.add(i.getCreateDate().getTime());
                list1.add(i.getMeanArterialPressure());
                list2.add(i.getDopamineDose());
                list3.add(i.getAdrenalineDose());
                list4.add(i.getNorepinephrineDose());
                list5.add(i.getPaoz());
                list6.add(i.getCreatinine());
                list7.add(i.getUrinevolume());
                list8.add(i.getBilirubin());
                list9.add(i.getBloodPlatelet());
            }
            if("1".equals(dataitem)){
                list = list1;
            }else if("2".equals(dataitem)){
                list = list2;
            }else if("3".equals(dataitem)){
                list = list3;
            }else if("4".equals(dataitem)){
                list = list4;
            }else if("5".equals(dataitem)){
                list = list5;
            }else if("6".equals(dataitem)){
                list = list6;
            }else if("7".equals(dataitem)){
                list = list7;
            }else if("8".equals(dataitem)){
                list = list8;
            }else if("9".equals(dataitem)){
                list = list9;
            }
        }

        indexInfo.setDates(dates).setList(list);

    }

}

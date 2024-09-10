package com.program.pyohemia.service.iml;

import com.github.pagehelper.PageHelper;
import com.program.pyohemia.dao.InspectDao;
import com.program.pyohemia.dao.PatientDao;
import com.program.pyohemia.entity.Inspect;
import com.program.pyohemia.entity.Patient;
import com.program.pyohemia.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PatientServiceImp implements PatientService {
    @Autowired
    PatientDao dao;

    @Autowired
    InspectDao inspectDao;


    @Override
    public void add(Patient patient) {
        dao.add(patient);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public List<Patient> findAll(int page, int size,Patient patient) {
        PageHelper.startPage(page,size);
        return dao.findAll(patient);
    }

    @Override
    public List<Patient> findAll2(int page, int size, Patient patient,String starttime,String endtime) {
        PageHelper.startPage(page,size);

        return dao.findAll2(patient.getName(),patient.getGender(),starttime,endtime,patient.getCreateBy());
    }

    @Override
    public Patient findById(int id) {
        Patient p=dao.findById(id);
        if(p!=null){
            List<Inspect> i = inspectDao.findById(id);
            List<Inspect> i2 = new ArrayList<>();
            for(Inspect inspect:i){
                if("0".equals(inspect.getFlag())){
                    i2.add(inspect);
                }
            }
            p.setInspects(i2);
        }

        return p;
    }
    @Override
    public Patient findByIdDate(int id, String starttime) {
        Patient p=dao.findById(id);
        if(p!=null){
            List<Inspect> i = inspectDao.findById(id);
            List<Inspect> i2 = new ArrayList<>();
            if(!"".equals(starttime)){
                for(Inspect inspect:i){
                    if(inspect.getCreateDate().toString().substring(0,16).equals(starttime.substring(0,16))){
                        i2.add(inspect);
                    }
                }
                p.setInspects(i2);
            }else{
                p.setInspects(i);
            }
        }

        return p;
    }

    @Override
    public void edit(Patient patient) {
        dao.edit(patient);
    }

    @Override
    public String getPatientGroupByDate(String theDay,String username) {
        return dao.getPatientGroupByDate(theDay,username);
    }
}

package com.program.pyohemia.service;
import com.program.pyohemia.entity.Patient;

import java.util.Date;
import java.util.List;

public interface PatientService {

    //添加
    public void add(Patient patient);
    //删除
    public void delete(int id);
    //查找所有
    public List<Patient> findAll(int page, int size,Patient patient);
    public List<Patient> findAll2(int page, int size,Patient patient,String starttime,String endtime);

    public Patient findById(int id);
    public Patient findByIdDate(int id, String starttime);

    public void edit(Patient patient);

    public String getPatientGroupByDate(String theDay,String username);
}

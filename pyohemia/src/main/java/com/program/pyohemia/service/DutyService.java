package com.program.pyohemia.service;
import com.program.pyohemia.entity.Duty;
import com.program.pyohemia.entity.User;

import java.util.List;

public interface DutyService {


    //添加
    public void add(Duty duty);
    //删除
    public void delete(int id);
    //查找所有
    public List<Duty> findAll(int page, int size,Duty duty);

    public Duty findById(int id);

    public void edit(Duty duty);

}

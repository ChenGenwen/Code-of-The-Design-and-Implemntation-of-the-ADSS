package com.program.pyohemia.service.iml;

import com.github.pagehelper.PageHelper;
import com.program.pyohemia.dao.DutyDao;
import com.program.pyohemia.entity.Duty;
import com.program.pyohemia.service.DutyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DutyServiceImp implements DutyService {
    @Autowired
    DutyDao dao;


    @Override
    public void add(Duty duty) {
        dao.add(duty);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public List<Duty> findAll(int page, int size,Duty duty) {
        PageHelper.startPage(page,size);
        return dao.findAll(duty);
    }

    @Override
    public Duty findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void edit(Duty duty) {
        dao.edit(duty);
    }
}

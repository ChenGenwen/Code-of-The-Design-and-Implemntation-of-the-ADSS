package com.program.pyohemia.service.iml;

import com.github.pagehelper.PageHelper;
import com.program.pyohemia.dao.CaseDao;
import com.program.pyohemia.entity.Case;
import com.program.pyohemia.service.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseServiceImp implements CaseService {
    @Autowired
    CaseDao dao;


    @Override
    public void add(Case cases) {
        dao.add(cases);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }

    @Override
    public List<Case> findAll(int page, int size,Case cases) {
        PageHelper.startPage(page,size);
        return dao.findAll(cases);
    }

    @Override
    public Case findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void edit(Case cases) {
        dao.edit(cases);
    }
}

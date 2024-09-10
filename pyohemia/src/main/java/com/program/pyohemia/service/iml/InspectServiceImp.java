package com.program.pyohemia.service.iml;

import com.program.pyohemia.dao.InspectDao;
import com.program.pyohemia.entity.Inspect;
import com.program.pyohemia.service.InspectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class InspectServiceImp implements InspectService {
    @Autowired
    InspectDao dao;

    @Override
    public void add(Inspect inspect) {

        //计算总评分



        dao.add(inspect);
    }

    @Override
    public void edit(Inspect inspect) {
        dao.edit(inspect);
    }

    @Override
    public Inspect findById(int id) {
        return dao.getById(id);
    }

    @Override
    public void delete(int id) {
        dao.delete(id);
    }
}

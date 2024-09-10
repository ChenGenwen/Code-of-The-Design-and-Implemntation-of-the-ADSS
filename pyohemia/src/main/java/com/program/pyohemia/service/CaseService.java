package com.program.pyohemia.service;
import com.program.pyohemia.entity.Case;

import java.util.List;

public interface CaseService {


    //添加
    public void add(Case cases);
    //删除
    public void delete(int id);
    //查找所有
    public List<Case> findAll(int page, int size, Case cases);

    public Case findById(int id);

    public void edit(Case cases);

}

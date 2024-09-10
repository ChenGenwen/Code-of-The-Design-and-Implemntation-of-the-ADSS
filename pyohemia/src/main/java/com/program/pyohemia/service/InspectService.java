package com.program.pyohemia.service;


import com.program.pyohemia.entity.Inspect;
import com.program.pyohemia.entity.Patient;


public interface InspectService {
    public void add(Inspect inspect);
    public void edit(Inspect inspect);
    public Inspect findById(int id);
    public void delete(int id);

}

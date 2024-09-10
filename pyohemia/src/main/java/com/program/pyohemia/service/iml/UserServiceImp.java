package com.program.pyohemia.service.iml;

import com.github.pagehelper.PageHelper;
import com.program.pyohemia.dao.UserDao;
import com.program.pyohemia.entity.User;
import com.program.pyohemia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    UserDao dao;

    @Override
    public User findByUsername(String username) {
        return dao.findByUsername(username);
    }

    @Override
    public User findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void addUser(User user) {
        dao.addUser(user);
    }

    @Override
    public void edit(User user) {
        dao.edit(user);
    }

    @Override
    public void deleteUser(int id) {
        dao.deleteUser(id);
    }

    @Override
    public List<User> findAll(int page, int size,String username) {
        PageHelper.startPage(page,size);
        return dao.findAll(username);
    }

    @Override
    public void addUserRole(int id) {
        dao.addUserRole(id);
    }

}

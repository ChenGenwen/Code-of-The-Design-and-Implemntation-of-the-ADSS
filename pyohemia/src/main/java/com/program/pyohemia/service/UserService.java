package com.program.pyohemia.service;
import com.program.pyohemia.entity.User;

import java.util.List;

public interface UserService {

    //根据账号查找用户
    public User findByUsername(String username);
    public User findById(int id);
    //添加用户
    public void addUser(User user);
    public void edit(User user);
    //删除用户
    public void deleteUser(int id);

    //查找所有用户
    public List<User> findAll(int page, int size, String username);

    public void addUserRole(int id);


}

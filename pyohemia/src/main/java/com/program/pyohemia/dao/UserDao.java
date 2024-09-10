package com.program.pyohemia.dao;
import com.program.pyohemia.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    //通过username查找User
    @Select("select * from user where username=#{username} and flag=0")
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "department", column = "department"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "address", column = "address"),
            @Result(property = "title", column = "title"),
            @Result(property = "username", column = "username"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "authenticationList",column = "id",javaType = java.util.List.class,many = @Many(select = "com.program.pyohemia.dao.AuthenticationDao.findByUserId"))})
    public User findByUsername(String username);


    //通过id查找User
    @Select("select * from user where id=#{id} and flag=0")
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "department", column = "department"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "address", column = "address"),
            @Result(property = "title", column = "title"),
            @Result(property = "username", column = "username"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "authenticationList",column = "id",javaType = java.util.List.class,many = @Many(select = "com.program.pyohemia.dao.AuthenticationDao.findByUserId"))})
    public User findById(int id);

    //添加用户
    @Insert("insert into user(username,password,phone,department,name,age,gender,address,title,flag,create_date)" +
            "values(#{username},#{password},#{phone},#{department},#{name},#{age},#{gender},#{address},#{title},0,now())")
    public void addUser(User user);
    //编辑
    @Insert("update user set username=#{username},phone=#{phone},department=#{department},name=#{name}" +
            ",age=#{age},gender=#{gender},address=#{address},title=#{title} where id=#{id}")
    public void edit(User user);

    //查找所有用户
    @Select({"<script>",
            "select * from user where 1=1 and flag=0 " +
                    "<if test=\"name != null and name != '' \" >and name = #{name}  </if>" +
                    "</script> "}
    )
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "department", column = "department"),
            @Result(property = "password", column = "password"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "address", column = "address"),
            @Result(property = "title", column = "title"),
            @Result(property = "username", column = "username"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "authenticationList",column = "id",javaType = List.class,many = @Many(select = "com.program.pyohemia.dao.AuthenticationDao.findByUserId"))})
    public List<User> findAll(String name);

    //删除用户
    @Delete("update user set flag=1 where id=#{id}")
    public void deleteUser(int id);

    //为账号添加医生权限
    @Insert("insert into user_authorities(authentication_id,user_id)values(2,#{user_id})")
    public void addUserRole(int id);




}

package com.program.pyohemia.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

//医生信息表
@NoArgsConstructor
@Data
@Accessors(chain=true)
public class User {
    private int id;
    private String name;
    private String department;
    private String age;
    private String gender;
    private String address;
    private String title;
    private String username;
    private String password;
    private List<Authentication> authenticationList;
    private BigInteger phone;
    private String flag;
    @DateTimeFormat(pattern="YYYY-MM-dd HH-mm-ss")
    private Date createDate;

    public boolean isAdmin(){
        boolean flag=false;
        for (Authentication authentication:authenticationList){
            if ("Admin".equals(authentication.getName())){
                flag=true;
            }

        }
        return flag;
    }
}

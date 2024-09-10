package com.program.pyohemia.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Patient {
    private int id;
    private String name;
    private String age;
    private String gender;
    private String occupation;//职业
    private String address;
    private String nativeplace;//籍贯
    private String createBy;
    private BigInteger phone;
    private String flag;
    @DateTimeFormat(pattern="YYYY-MM-dd HH-mm-ss")
    private Date createDate;
    private String complaint;
    @DateTimeFormat(pattern="YYYY-MM-dd HH-mm-ss")
    private Timestamp admissionTime;//入院时间
    private String admissionSymptom;//入院症状
    private String pasthistory;//既往史
    private String allergy;//过敏史



    private List<Inspect> inspects;

}

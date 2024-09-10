package com.program.pyohemia.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.sql.Date;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Case {
    private int id;
    private String caseNumber;
    private String patientId;
    @DateTimeFormat(pattern="YYYY-MM-dd HH-mm-ss")
    private Date admissionTime;//入院时间
    private String admissionSymptom;//入院症状
    private String pasthistory;//既往史
    private String allergy;//过敏史
    private String flag;

}

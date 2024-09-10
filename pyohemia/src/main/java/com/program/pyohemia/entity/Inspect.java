package com.program.pyohemia.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Timestamp;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Inspect implements Serializable {
    private int id;
    private int patientId;//患者id
    private int monitorId;

    private String meanArterialPressure;
    private String dopamineDose;
    private String adrenalineDose;
    private String norepinephrineDose;
    private String dobutamine;
    private String paoz;
    private String breathing;
    private String creatinine;
    private String urinevolume;
    private String bilirubin;
    private String bloodPlatelet;
    private String gcs;
    private String overallScore;
    private String flag;


    private String totalBilirubin;
    private String directBilirubin;
    private String urinaryCreatinine;
    private String ph;
    private String paco2;
    private String hco3;
    private String ast;
    private String bun;
    private String calcium;
    private String lactate;
    private String magnesium;
    private String phosphate;
    private String potassium;
    private String hct;
    private String hgb;
    private String wbc;
    private String fibrinogen;
    private String troponin;
    private String heartrate;
    private String glucose;

    @DateTimeFormat(pattern="YYYY-MM-dd HH-mm-ss")
    private Timestamp createDate;
}

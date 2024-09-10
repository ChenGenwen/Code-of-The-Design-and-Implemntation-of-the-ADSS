package com.program.pyohemia.dao;

import com.program.pyohemia.entity.Inspect;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface InspectDao {
    //查看检测记录
    @Select("select id, patient_id,mean_arterial_pressure, dopamine_dose," +
            " adrenaline_dose, norepinephrine_dose, dobutamine," +
            " paoz, breathing, creatinine, urinevolume, bilirubin," +
            " blood_platelet, gcs, overall_score, case_id,  create_date," +
            "total_bilirubin, direct_bilirubin, urinary_creatinine, ph, paco2, hco3, ast, bun, calcium, glucose, lactate, magnesium, phosphate, potassium, hct, hgb, wbc, fibrinogen, troponin, heartrate,"+

            " create_by, flag from inspect where patient_id=#{patientId} order by create_date ")
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "patientId", column = "patient_id"),
            @Result(property = "meanArterialPressure", column = "mean_arterial_pressure"),
            @Result(property = "dopamineDose", column = "dopamine_dose"),
            @Result(property = "adrenalineDose", column = "adrenaline_dose"),
            @Result(property = "norepinephrineDose", column = "norepinephrine_dose"),
            @Result(property = "bloodPlatelet", column = "blood_platelet"),
            @Result(property = "overallScore", column = "overall_score"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "totalBilirubin", column = "total_bilirubin"),
            @Result(property = "directBilirubin", column = "direct_bilirubin"),
            @Result(property = "urinaryCreatinine", column = "urinary_creatinine"),
            @Result(property = "ph", column = "ph"),
            @Result(property = "paco2", column = "paco2"),
            @Result(property = "hco3", column = "hco3"),
            @Result(property = "ast", column = "ast"),
            @Result(property = "bun", column = "bun"),
            @Result(property = "calcium", column = "calcium"),
            @Result(property = "magnesium", column = "magnesium"),
            @Result(property = "glucose", column = "glucose"),
            @Result(property = "lactate", column = "lactate"),
            @Result(property = "phosphate", column = "phosphate"),
            @Result(property = "potassium", column = "potassium"),
            @Result(property = "hct", column = "hct"),
            @Result(property = "hgb", column = "hgb"),
            @Result(property = "wbc", column = "wbc"),
            @Result(property = "fibrinogen", column = "fibrinogen"),
            @Result(property = "troponin", column = "troponin"),
            @Result(property = "heartrate", column = "heartrate"),
            @Result(property = "flag", column = "flag")})
    public List<Inspect> findById(int patientId);


    //查看检测记录
    @Select("select patient_id,mean_arterial_pressure, dopamine_dose," +
            " adrenaline_dose, norepinephrine_dose, dobutamine," +
            " paoz, breathing, creatinine, urinevolume, bilirubin," +
            " blood_platelet, gcs, overall_score, case_id,  create_date," +
            "total_bilirubin, direct_bilirubin, urinary_creatinine, ph, paco2, hco3, ast, bun, calcium, glucose, lactate, magnesium, phosphate, potassium, hct, hgb, wbc, fibrinogen, troponin, heartrate,"+
            " create_by, flag from inspect where id=#{id} order by create_date ")
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "patientId", column = "patient_id"),
            @Result(property = "meanArterialPressure", column = "mean_arterial_pressure"),
            @Result(property = "dopamineDose", column = "dopamine_dose"),
            @Result(property = "adrenalineDose", column = "adrenaline_dose"),
            @Result(property = "norepinephrineDose", column = "norepinephrine_dose"),
            @Result(property = "bloodPlatelet", column = "blood_platelet"),
            @Result(property = "overallScore", column = "overall_score"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "totalBilirubin", column = "total_bilirubin"),
            @Result(property = "directBilirubin", column = "direct_bilirubin"),
            @Result(property = "urinaryCreatinine", column = "urinary_creatinine"),
            @Result(property = "ph", column = "ph"),
            @Result(property = "paco2", column = "paco2"),
            @Result(property = "hco3", column = "hco3"),
            @Result(property = "ast", column = "ast"),
            @Result(property = "bun", column = "bun"),
            @Result(property = "calcium", column = "calcium"),
            @Result(property = "magnesium", column = "magnesium"),
            @Result(property = "glucose", column = "glucose"),
            @Result(property = "lactate", column = "lactate"),
            @Result(property = "phosphate", column = "phosphate"),
            @Result(property = "potassium", column = "potassium"),
            @Result(property = "hct", column = "hct"),
            @Result(property = "hgb", column = "hgb"),
            @Result(property = "wbc", column = "wbc"),
            @Result(property = "fibrinogen", column = "fibrinogen"),
            @Result(property = "troponin", column = "troponin"),
            @Result(property = "heartrate", column = "heartrate"),
            @Result(property = "flag", column = "flag")})
    public Inspect getById(int id);


    //添加检测记录
    @Insert("insert into inspect(patient_id,mean_arterial_pressure, dopamine_dose, adrenaline_dose, " +
            "norepinephrine_dose, dobutamine, paoz, breathing, creatinine, urinevolume, " +
            "bilirubin, blood_platelet, gcs, overall_score,  create_date, " +
            " total_bilirubin, direct_bilirubin, urinary_creatinine, ph, paco2, hco3, ast, bun, calcium, glucose,lactate, magnesium, phosphate, potassium, hct, hgb, wbc, fibrinogen, troponin, heartrate,"+
            "flag)values(#{patientId},#{meanArterialPressure},#{dopamineDose},#{adrenalineDose}," +
            "#{norepinephrineDose},#{dobutamine},#{paoz},#{breathing}," +
            "#{creatinine},#{urinevolume},#{bilirubin},#{bloodPlatelet}," +
            "#{gcs},#{overallScore},#{createDate}," +
            ""+
            "#{totalBilirubin},"+
            "#{directBilirubin},"+
            "#{urinaryCreatinine},"+
            "#{ph},"+
            "#{paco2},"+
            "#{hco3},"+
            "#{ast},"+
            "#{bun},"+
            "#{calcium},"+
            "#{glucose},"+
            "#{lactate},"+
            "#{magnesium},"+
            "#{phosphate},"+
            "#{potassium},"+
            "#{hct},"+
            "#{hgb},"+
            "#{wbc},"+
            "#{fibrinogen},"+
            "#{troponin},"+
            "#{heartrate},"+
            "0" +
            ")")
    public void add(Inspect inspect);
    //编辑检测记录
    @Insert("update inspect set patient_id=#{patientId},mean_arterial_pressure=#{meanArterialPressure}, dopamine_dose=#{dopamineDose}, adrenaline_dose=#{adrenalineDose}, " +
            "norepinephrine_dose=#{norepinephrineDose}, dobutamine=#{dobutamine}, paoz=#{paoz}, breathing=#{breathing}, creatinine=#{creatinine}"+
            "urinevolume=#{urinevolume},"+
            "bilirubin=#{bilirubin}, blood_platelet=#{bloodPlatelet}, gcs={gcs}, overall_score=#{overallScore},  create_date=#{createDate}, " +
            "total_bilirubin=#{totalBilirubin},"+
            "direct_bilirubin=#{directBilirubin},"+
            "urinary_creatinine=#{urinaryCreatinine},"+
            "ph=#{ph},"+
            "paco2=#{paco2},"+
            "hco3=#{hco3},"+
            "ast=#{ast},"+
            "bun=#{bun},"+
            "calcium=#{calcium},"+
            "glucose=#{glucose},"+
            "lactate=#{lactate},"+
            "magnesium=#{magnesium},"+
            "phosphate=#{phosphate},"+
            "potassium=#{potassium},"+
            "hct=#{hct},"+
            "hgb=#{hgb},"+
            "wbc=#{wbc},"+
            "fibrinogen=#{fibrinogen},"+
            "troponin=#{troponin},"+
            "heartrate=#{heartrate},"+
            "flag={flag} where id=#{id}"+
            "")
    public void edit(Inspect inspect);

    //删除
    @Update("update inspect set flag=1 where id=#{id}")
    public void delete(int id);

}

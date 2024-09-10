package com.program.pyohemia.dao;
import com.program.pyohemia.entity.Case;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CaseDao {

    //添加
    @Insert("insert into case(case_number,patient_id,admission_time,admission_symptom,pasthistory,allergy,flag)" +
            "values(#{caseNumber},#{patientId},#{admissionTime},#{admissionSymptom},#{pasthistory},#{allergy},0)")
    public void add(Case cases);

    //查找所有
    @Select({"<script>",
            "select * from cases where 1=1 and flag=0 " +
                    "<if test=\"caseNumber != null and caseNumber != '' \" >and case_number = #{caseNumber}  </if>" +
            "</script> "}
    )
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "caseNumber", column = "case_number"),
            @Result(property = "patientId", column = "patient_id"),
            @Result(property = "admissionTime", column = "admission_time"),
            @Result(property = "admissionSymptom", column = "admission_symptom"),
            @Result(property = "pasthistory", column = "pasthistory"),
            @Result(property = "allergy", column = "allergy"),
            @Result(property = "flag", column = "flag")})
    public List<Case> findAll(Case cases);

    //删除
    @Update("update case set flag=1 where id=#{id}")
    public void delete(int id);

    //根据id查找
    @Select("select * from cases where id=#{id}")
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "caseNumber", column = "case_number"),
            @Result(property = "patientId", column = "patient_id"),
            @Result(property = "admissionTime", column = "admission_time"),
            @Result(property = "admissionSymptom", column = "admission_symptom"),
            @Result(property = "pasthistory", column = "pasthistory"),
            @Result(property = "allergy", column = "allergy"),
            @Result(property = "flag", column = "flag")})
    public Case findById(int id);

    @Update("update case set case_number=#{caseumber}," +
            "admission_symptom=#{admissionSymptom}," +
            "pasthistory=#{pasthistory}," +
            "allergy=#{allergy}," +
            "patient_id=#{patientId},admission_time=#{admissionTime} where id=#{id}")
    public void edit(Case cases);

}

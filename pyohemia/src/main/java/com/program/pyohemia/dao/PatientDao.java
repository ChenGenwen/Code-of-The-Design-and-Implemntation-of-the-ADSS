package com.program.pyohemia.dao;
import com.program.pyohemia.entity.Patient;
import org.apache.ibatis.annotations.*;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Mapper
public interface PatientDao {

    //添加
    @Insert("insert into patient(name,age,gender,occupation,phone,address,nativeplace,create_date,create_by," +
            "complaint,admission_time,admission_symptom,pasthistory,allergy,flag)" +
            "values(#{name},#{age},#{gender},#{occupation},#{phone},#{address},#{nativeplace},#{createDate},#{createBy}," +
            "#{complaint},#{admissionTime},#{admissionSymptom},#{pasthistory},#{allergy},0)")
    public void add(Patient patient);

    //查找所有
    @Select({"<script>",
            "select * from patient where 1=1 and flag=0 " +
                    "<if test=\"name != null and name != '' \" >and name = #{name}  </if>" +
                    "<if test=\"gender != null and gender != '' \" >and gender = #{gender}  </if>" +
                    "<if test=\"createBy != null and createBy != '' \" >and create_by = #{createBy}  </if>" +
                    " order by id" +
            "</script> "}
    )
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "occupation", column = "occupation"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "address", column = "address"),
            @Result(property = "nativeplace", column = "nativeplace"),
            @Result(property = "title", column = "title"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "complaint", column = "complaint"),
            @Result(property = "admissionTime", column = "admission_time"),
            @Result(property = "admissionSymptom", column = "admission_symptom"),
            @Result(property = "pasthistory", column = "pasthistory"),
            @Result(property = "allergy", column = "allergy"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "inspects",column = "patientId",javaType = java.util.List.class,many = @Many(select = "com.program.pyohemia.dao.InspectDao.findById"))

    })
    public List<Patient> findAll(Patient patient);


    //查找所有
    @Select({"<script>",
            "select * from patient where 1=1 and flag=0 " +
                    "<if test=\"name != null and name != '' \" >and name = #{name}  </if>" +
                    "<if test=\"gender != null and gender != '' \" >and gender = #{gender}  </if>" +
                    "<if test=\"starttime != null and starttime != '' \" >and create_date > #{starttime}  </if>" +
                    "<if test=\"endtime != null and endtime != '' \" >and create_date = #{endtime}  </if>" +
                    "<if test=\"createBy != null and createBy != '' \" >and create_by = #{createBy}  </if>" +
                    "</script> "}
    )
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "occupation", column = "occupation"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "address", column = "address"),
            @Result(property = "nativeplace", column = "nativeplace"),
            @Result(property = "title", column = "title"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "complaint", column = "complaint"),
            @Result(property = "admissionTime", column = "admission_time"),
            @Result(property = "admissionSymptom", column = "admission_symptom"),
            @Result(property = "pasthistory", column = "pasthistory"),
            @Result(property = "allergy", column = "allergy"),
            @Result(property = "flag", column = "flag"),
            @Result(property = "inspects",column = "patientId",javaType = java.util.List.class,many = @Many(select = "com.program.pyohemia.dao.InspectDao.findById"))
    })
    public List<Patient> findAll2(@Param("name") String name,@Param("gender") String gender,@Param("starttime") String starttime,@Param("endtime") String endtime,@Param("createBy") String createBy);

    //删除
    @Update("update patient set flag=1 where id=#{id}")
    public void delete(int id);

    //根据id查找
    @Select("select * from patient where id=#{patientId}")
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "age", column = "age"),
            @Result(property = "gender", column = "gender"),
            @Result(property = "occupation", column = "occupation"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "address", column = "address"),
            @Result(property = "nativeplace", column = "nativeplace"),
            @Result(property = "title", column = "title"),
            @Result(property = "createDate", column = "create_date"),
            @Result(property = "createBy", column = "create_by"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "complaint", column = "complaint"),
            @Result(property = "admissionTime", column = "admission_time"),
            @Result(property = "admissionSymptom", column = "admission_symptom"),
            @Result(property = "pasthistory", column = "pasthistory"),
            @Result(property = "allergy", column = "allergy"),
            @Result(property = "inspects",column = "patientId",javaType = java.util.List.class,many = @Many(select = "com.program.pyohemia.dao.InspectDao.findById")),
            @Result(property = "flag", column = "flag")})
    public Patient findById(int id);

    @Update("update patient set name=#{name},age=#{age},gender=#{gender}" +
            ",occupation=#{occupation}" +
            ",phone=#{phone}" +
            ",address=#{address}" +
            ",nativeplace=#{nativeplace}" +
            ",create_by=#{createBy}" +
            ",admission_symptom=#{admissionSymptom}," +
            "pasthistory=#{pasthistory}," +
            "complaint=#{complaint}," +
            "allergy=#{allergy}," +"admission_time=#{admissionTime}"+
            " where id=#{id}")
    public void edit(Patient patient);

    @Select({"<script>",
            "select count(0) from patient where to_days(create_date)=to_days(#{theDay}) and flag=0"+
            "<if test=\"username != null and username != '' \" >and create_by = #{username}  </if>"
            +"</script> "})
            public String getPatientGroupByDate(@Param("theDay")String theDay, @Param("username")String username);
}

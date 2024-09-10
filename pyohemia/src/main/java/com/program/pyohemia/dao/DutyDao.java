package com.program.pyohemia.dao;
import com.program.pyohemia.entity.Duty;
import com.program.pyohemia.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DutyDao {



    //添加
    @Insert("insert into duty(duty_time,duty_doctor,sort,remarks,flag)" +
            "values(#{dutyTime},#{dutyDoctor},#{sort},#{remarks},0)")
    public void add(Duty duty);

    //查找所有
    @Select({"<script>",
            "select * from duty where 1=1 and flag=0 " +
                    "<if test=\"dutyTime != null and dutyTime != '' \" >and duty_time = #{dutyTime}  </if>" +
                    "<if test=\"dutyDoctor != null and dutyDoctor != '' \" >and duty_doctor = #{dutyDoctor}  </if>" +
            "</script> "}
    )
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "dutyTime", column = "duty_time"),
            @Result(property = "dutyDoctor", column = "duty_doctor"),
            @Result(property = "sort", column = "sort"),
            @Result(property = "remarks", column = "remarks"),
            @Result(property = "flag", column = "flag")})
    public List<Duty> findAll(Duty duty);

    //删除
    @Update("update duty set flag=1 where id=#{id}")
    public void delete(int id);

    //根据id查找
    @Select("select * from duty where id=#{id}")
    @Results({ @Result(id = true, property = "id", column = "id"),
            @Result(property = "dutyTime", column = "duty_time"),
            @Result(property = "dutyDoctor", column = "duty_doctor"),
            @Result(property = "sort", column = "sort"),
            @Result(property = "remarks", column = "remarks"),
            @Result(property = "flag", column = "flag")})
    public Duty findById(int id);

    @Update("update duty set duty_time=#{dutyTime},duty_doctor=#{dutyDoctor},sort=#{sort},remarks=#{remarks} where id=#{id}")
    public void edit(Duty duty);

}

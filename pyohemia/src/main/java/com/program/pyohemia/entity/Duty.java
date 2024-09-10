package com.program.pyohemia.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigInteger;
import java.sql.Date;
import java.util.List;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class Duty {
    private int id;
    private String dutyTime;
    private String dutyDoctor;
    private String sort;
    private String remarks;
    private String flag;

}

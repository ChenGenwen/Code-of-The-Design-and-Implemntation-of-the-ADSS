package com.program.pyohemia.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@NoArgsConstructor
@Data
@Accessors(chain=true)
public class IndexInfo {
    private List dates;
    private List patientNums;


    private List list;
    private List list1;//平均动脉压
    private List list2;//多巴胺剂量
    private List list3;
    private List list4;
    private List list5;
    private List list6;
    private List list7;
    private List list8;
    private List list9;

}

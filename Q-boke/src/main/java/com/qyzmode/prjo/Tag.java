package com.qyzmode.prjo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tag {

    //主键
    private Long id;
    //标签名字
    private String name;

}

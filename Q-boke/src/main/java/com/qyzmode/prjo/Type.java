package com.qyzmode.prjo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;



@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Type {

    //主键
    private Long id;
    //类型
    private String name;

}

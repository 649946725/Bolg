package com.qyzmode.prjo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BlogTypeOrTagCount {

    private Long id;
    private String name;
    private Integer size;
}

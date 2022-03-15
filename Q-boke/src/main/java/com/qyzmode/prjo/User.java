package com.qyzmode.prjo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class User {


    //主键
    private  Long id;
    //用户名名字
    private String nick_name;
    //用户名
    private String username;
    //密码
    private String password;
    //邮箱
    private String email;
    //头像
    private String avatar;
    //权限
    private  Integer type;
    //创建时间
    private Date create_time;
    //更新时间
    private Date update_time;
}

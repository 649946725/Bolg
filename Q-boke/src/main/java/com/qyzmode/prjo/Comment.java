package com.qyzmode.prjo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Comment {

    //主键
    private Long id;
    //评论的用户名名字
    private String nickname;
    //评论的用户的邮箱
    private String email;
    //评论的内容
    private String content;
    //评论的用户的头像
    private String avatar;
    //用户评论的时间
    private Timestamp create_time;
    //评论的博客的id
    private  Long blog_id;
    //该评论的父评论id
    private Long parent_comment_id;
    //子评论列表
    private List<Comment> child_comment;

    private String parent_name;

    private boolean admin;

}

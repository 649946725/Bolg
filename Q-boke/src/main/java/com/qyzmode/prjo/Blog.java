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
public class Blog {
    //博客主键的id
    private Long id;
    //博客的标题
    private String title;
    //博客的内容
    private String content;
    //首图
    private String first_picture;
    //博客的标记
    private String flag;
    //博客的浏览次数
    private Integer views;
    //赞赏功能是否开启
    private  boolean appreciation;
    //转载声明是否开启
    private  boolean share_statement;
    //评论功能是否开启
    private boolean commentabled;
    //博客的发布状态
    private boolean published;
    //是否推荐
    private boolean recommend;
    //创建时间
    private Timestamp create_time;
    //更新时间
    private Timestamp update_time;
    //博客对应类型的id
    private Long type_id;
    //博客对应用户的id
    private Long user_id;
    //标签列表id
    private String tag_id;
    private String type;
    private String description;
    private User user;
    private List<Tag> tags;
}

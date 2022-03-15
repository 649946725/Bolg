package com.qyzmode.service.Imp;

import com.qyzmode.dao.CommentDao;
import com.qyzmode.prjo.Comment;
import com.qyzmode.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImp implements CommentService {

    @Autowired
    private CommentDao commentDao;

    @Override
    public List<Comment> findByBlogId(Long blog_id) {
        //查到所有顶级节点
        List<Comment> comments = commentDao.findByBlogId(blog_id);
        System.out.println(combineChildren(comments));
        return combineChildren(comments);
    }

    //递归迭代子节点
    private List<Comment> temp = new ArrayList<>();

    public List<Comment> combineChildren(List<Comment> comments) {
        //找所有顶级节点的子节点
        for (Comment comment : comments
        ) {
            //得到每个顶级节点的子节点
            List<Comment> replys1 = commentDao.findChildComment(comment.getId());
            //遍历顶级节点的子节点的子节点
            for (Comment reply:replys1
                 ) {
               reply.setParent_name(comment.getNickname());
                recursively(reply);
            }

            comment.setChild_comment(temp);
            temp = new ArrayList<>();
        }
        return comments;
    }
//递归迭代
    public void recursively(Comment comment) {
        //先把顶级节点的子节点存入  然后在遍历顶级节点的子节点的子节点
        temp.add(comment);
        //判断顶级节点的子节点的子节点是否有子节点
        if (commentDao.findChildComment(comment.getId()).size() > 0) {
            //得到顶级节点的子节点的子节点的子节点
            List<Comment> replys2 = commentDao.findChildComment(comment.getId());
            //遍历顶级节点的子节点的子节点的子节点
            for (Comment reply2 : replys2
            ) {
                reply2.setParent_name(comment.getNickname());
                if (commentDao.findChildComment(reply2.getId()).size() > 0)
                    recursively(reply2);
                else
                    temp.add(reply2);
            }
        }
    }

    @Override
    @Transactional
    public int insertComment(Comment comment) {
        String parent_comment_id = String.valueOf(comment.getParent_comment_id());
        System.out.println(parent_comment_id);
        if (parent_comment_id.equals("-1")) {
            comment.setParent_comment_id(null);
        }
        comment.setCreate_time(new Timestamp(new Date().getTime()));
        comment.setAvatar("https://unsplash.it/100/100?image=1005");
        int i = commentDao.insertComment(comment);
        return i;
    }

}

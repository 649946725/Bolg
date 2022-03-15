package com.qyzmode.service.Imp;

import com.qyzmode.dao.TagDao;
import com.qyzmode.exception.NotFoundException;
import com.qyzmode.prjo.Tag;
import com.qyzmode.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TagServiceImp implements TagService {

    @Autowired
    private TagDao tagDao;

    @Override
    @Transactional
    public int insertTag(Tag tag) {
        return tagDao.insertTag(tag);
    }

    @Override
    public Tag findTagById(long id) {
        return tagDao.findTagById(id);
    }

    @Override
    public Tag findTagByName(String name) {
        return tagDao.findTagByName(name);
    }

    @Override
    public List<Tag> selectTag() {
        return tagDao.selectTag();
    }

    @Override
    @Transactional
    public int updateTag(Tag tag) {
        int i=tagDao.updateTag(tag);
        if(i==0)
            throw new NotFoundException("不存在改标签");
        else return i;
    }

    @Override
    @Transactional
    public int deleteTag(long id) {
        return tagDao.deleteTag(id);
    }
}

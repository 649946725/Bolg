package com.qyzmode.service.Imp;

import com.qyzmode.dao.TypeDao;
import com.qyzmode.exception.NotFoundException;
import com.qyzmode.prjo.Type;
import com.qyzmode.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TypeServiceImp implements TypeService {

    @Autowired
    private TypeDao typeDao;

    @Override
    @Transactional
    public int insertType(Type type) {

        return typeDao.insertType(type);
    }

    @Override
    public Type findTypeById(long id) {
        return typeDao.findTypeById(id);
    }

    @Override
    public Type findTypeByName(String name) {
        return typeDao.findTypeByName(name);
    }

    @Override
    public List<Type> selectType() {
        return typeDao.selectType();
    }

    @Override
    @Transactional
    public int updateType(Type type) {
        int i=typeDao.updateType(type);
        if(i==0)
            throw new NotFoundException("不存在该类型");
        else return i;
    }

    @Override
    @Transactional
    public int deleteType(long id) {
        return typeDao.deleteType(id);
    }
}

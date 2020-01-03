package com.classic.service.impl;

import com.classic.dao.BaseDao;
import com.classic.entity.base.ClassicUser;
import com.classic.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BaseServiceImpl implements BaseService {

    @Autowired
    private BaseDao baseDao;

    @Override
    public String begin() {
        return "Hello World !!!";
    }

    @Override
    public List<ClassicUser> getList() {
        return baseDao.getList();
    }

}


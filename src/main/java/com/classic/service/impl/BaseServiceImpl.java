package com.classic.service.impl;

import com.classic.service.BaseService;
import org.springframework.stereotype.Service;

@Service
public class BaseServiceImpl implements BaseService {

    @Override
    public String begin() {
        return "Hello World !!!";
    }

}


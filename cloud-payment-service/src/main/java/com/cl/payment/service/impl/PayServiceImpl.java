package com.cl.payment.service.impl;

import com.cl.payment.entity.Pay;
import com.cl.payment.repository.PayDAO;
import com.cl.payment.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    @Resource
    private PayDAO payDAO;

    @Override
    public Pay getPay(Long id) {
        return payDAO.selectById(id);
    }
}

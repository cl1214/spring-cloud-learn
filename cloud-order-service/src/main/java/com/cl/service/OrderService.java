package com.cl.service;

import com.cl.common.dto.PayDto;

public interface OrderService {

    PayDto getPay(Long id);

    String loadBanlance();
}

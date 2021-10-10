package com.velog.server.service;

import com.velog.server.model.response.CommonResult;
import com.velog.server.model.response.ListResult;
import com.velog.server.model.response.SingleResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ResponseService {
    public <T> SingleResult<T> getSingleResult(T data);
    public <T> ListResult<T> getListResult(List<T> data);
    public CommonResult getSuccessResult();
    public CommonResult getFailResult();
}

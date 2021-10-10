package com.velog.server.service;

import com.velog.server.model.response.CommonResult;
import com.velog.server.model.response.ListResult;
import com.velog.server.model.response.SingleResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponseServiceImpl implements ResponseService{

    @Getter
    @AllArgsConstructor
    public enum CommonResponse {
        SUCCESS(0, "성공했습니다."),
        FAIL(-1, "실패했습니다.");

        int code;
        String msg;
    }

    public <T>SingleResult<T> getSingleResult(T data) {
        SingleResult<T> result = new SingleResult<>();
        result.setData(data);
        setSuccessResult(result);
        return result;
    }

    public <T>ListResult<T> getListResult(List<T> data) {
        ListResult<T> listResult = new ListResult<>();
        listResult.setList(data);
        setSuccessResult(listResult);
        return listResult;
    }

    public CommonResult getSuccessResult() {
        CommonResult successResult = new CommonResult();
        setSuccessResult(successResult);
        return successResult;
    }

    public CommonResult getFailResult() {
        CommonResult failResult = new CommonResult();
        failResult.setSuccess(false);
        failResult.setCode(CommonResponse.FAIL.getCode());
        failResult.setMsg(CommonResponse.FAIL.getMsg());
        return failResult;
    }

    private void setSuccessResult(CommonResult commonResult) {
        commonResult.setSuccess(true);
        commonResult.setCode(CommonResponse.SUCCESS.getCode());
        commonResult.setMsg(CommonResponse.SUCCESS.getMsg());
    }
}

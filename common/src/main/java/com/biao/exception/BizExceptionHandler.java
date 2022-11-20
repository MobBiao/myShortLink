package com.biao.exception;


import com.biao.util.JsonData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@Slf4j
public class BizExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public JsonData handler(Exception e) {
        if (e instanceof BizException) {
            BizException bizException = (BizException) e;
            log.error("link error: {}", bizException.getMsg(), bizException);
            return JsonData.buildCodeAndMsg(bizException.getCode(), bizException.getMsg());
        } else {
            log.error("system error: {}", e.getMessage(), e);
            return JsonData.buildError(e.getMessage());
        }
    }
}

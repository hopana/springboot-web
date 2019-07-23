package com.hp.springboot.exception;

import com.hp.springboot.Application;
import com.hp.springboot.result.CodeMsg;
import com.hp.springboot.result.Result;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ResponseBody
@ControllerAdvice(basePackageClasses = Application.class)
public class BizExceptionHandler {

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result handleControllerException(HttpServletRequest request, Throwable e) {
        if (e instanceof GlobalException) {
            GlobalException ex = (GlobalException) e;
            return Result.error(ex.getCodeMsg());
        } else if (e instanceof BindException) {
            BindException ex = (BindException) e;
            List<ObjectError> errors = ex.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(msg));
        } else {
            return Result.error(CodeMsg.SERVER_ERROR);
        }

        //HttpStatus status = getStatus(request);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return HttpStatus.valueOf(statusCode);
    }
}

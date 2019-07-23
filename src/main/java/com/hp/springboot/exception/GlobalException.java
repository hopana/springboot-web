package com.hp.springboot.exception;

import com.hp.springboot.result.CodeMsg;

public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private CodeMsg codeMsg;

    public GlobalException(CodeMsg cm) {
        super(cm.toString());
        this.codeMsg = cm;
    }

    public CodeMsg getCodeMsg() {
        return codeMsg;
    }

}

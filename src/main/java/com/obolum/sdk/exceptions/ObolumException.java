package com.obolum.sdk.exceptions;

import com.obolum.sdk.enums.ErrorCodeEnum;

/**
 * @author zhu.q
 */
public class ObolumException extends RuntimeException {
    private ErrorCodeEnum errorCode;

    public ObolumException() {
        super();
    }

    public ObolumException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ObolumException(Throwable cause) {
        super(cause);
    }

    public ObolumException(ErrorCodeEnum code, String msg) {
        super(code + ":" + msg);
        this.errorCode = code;
    }

    public ObolumException(ErrorCodeEnum codeEnum) {
        super(codeEnum.getCode() + ":" + codeEnum.getMsg());
        this.errorCode = codeEnum;
    }

    public ErrorCodeEnum getErrorCode() {
        return errorCode;
    }
}

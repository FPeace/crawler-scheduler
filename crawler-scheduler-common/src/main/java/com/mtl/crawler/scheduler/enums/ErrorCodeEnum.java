package com.mtl.crawler.scheduler.enums;

import com.juqitech.common.error.error.FullReError;
import com.juqitech.common.error.handler.util.ReErrorUtils;

/**
 * TODO: 请按照规范定义正确的错误码
 * 规范请参考：
 * http://devops.juqitech.com/confluence/pages/viewpage.action?pageId=3739466

 * @author ZhangFeng
 * @since 2020/01/06
 */
public enum ErrorCodeEnum implements FullReError {

    ENTITY_NOT_FOUND("RE030600022001", "演示错误描述");

    private String errorCode;
    private String errorMsg;

    ErrorCodeEnum(String errorCode, String errorMsg) {
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    @Override
    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMsg() {
        return errorMsg;
    }

    public int getStatusCode() {
        return ReErrorUtils.getStatusCode(this);
    }

}

package com.boss.train.bes.common.log.exception;

import com.boss.train.bes.common.log.exception.error.BusinessError;
import com.boss.train.bes.common.log.exception.error.SystemError;

import java.io.Serializable;

/**
 * @author 郭心蕊
 * @date 2020/07/02
 * @description Dao异常
 */
public class DaoException extends AppException implements Serializable {

    /**
     * 服务异常 业务错误
     *
     * @param businessError
     */
    public DaoException(BusinessError businessError){
        super(businessError);
    }

    /**
     * 服务异常 业务错误 异常堆栈信息
     *
     * @param businessError
     * @param cause
     */
    public DaoException(BusinessError businessError, Throwable cause){
        super(businessError,cause);
    }

    /**
     * 服务异常 系统错误
     *
     * @param systemError
     */
    public DaoException(SystemError systemError){
        super(systemError);
    }

    /**
     * 服务异常 系统错误 异常堆栈信息
     *
     * @param systemError
     * @param cause
     */
    public DaoException(SystemError systemError, Throwable cause){
        super(systemError,cause);
    }
}

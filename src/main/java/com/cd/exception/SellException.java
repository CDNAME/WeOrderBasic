package com.cd.exception;

import com.cd.enums.ResultEnum;

/**
 * Created by ChenDeng
 * 2018-08-23 21:23
 */
public class SellException extends RuntimeException {
    private Integer code;
    
    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public SellException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}

package com.cd.VO;

import lombok.Data;

/**
 * HTTP请求返回的最外层对象
 * Created by chendeng
 * 2018/8/20 0020 下午 10:26
 */
@Data
public class ResultVO<T> {
    private Integer code;   //错误码
    private String msg;     //提示信息
    private T data;     //具体内容
}

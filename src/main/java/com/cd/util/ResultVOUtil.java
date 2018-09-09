package com.cd.util;

import com.cd.VO.ResultVO;

/**
 * Created by chendeng
 * 2018/8/22 0022 下午 4:09
 */
public class ResultVOUtil {
    public static ResultVO success(Object o) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(o);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
    
    public static ResultVO success() {
        return success(null);
    }
    
    public static ResultVO error(Integer code, String msg) {
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMsg(msg);
        return resultVO;
    }
    
}

/**  
 * Project Name:ThornflowerOCR  
 * File Name:GlobleExceptionHandler.java  
 * Package Name:cn.thornflower.exception  
 * Date:2019年10月21日下午1:51:05  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.exception;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.thornflower.pojo.Result;
import lombok.extern.slf4j.Slf4j;

/**  
 * ClassName:GlobleExceptionHandler <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月21日 下午1:51:05 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Slf4j
@ControllerAdvice
public class GlobleExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result handlerException(HttpServletResponse response, Exception e) throws Exception {
        log.info("异常信息", e);
        Result result = new Result(Result.RESULT_FAIL, "error :" + e.getMessage());
        return result;
    }
}
  

/**  
 * Project Name:ThornflowerOCR  
 * File Name:Result.java  
 * Package Name:cn.thornflower.pojo  
 * Date:2019年10月21日下午1:52:38  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.pojo;

import java.io.Serializable;

import lombok.Data;

/**  
 * ClassName:Result <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月21日 下午1:52:38 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Data
public class Result implements Serializable{/**
     * @Field @serialVersionUID : TODO(这里用一句话描述这个类的作用)
     */
    private static final long serialVersionUID = 1L;

    public static final int RESULT_FAIL = 0;
    public static final int RESULT_SUCCESS = 1;
    
    //返回代码
    private Integer  code;

    //返回消息
    private String message;

    //返回对象
    private  Object data;
    
    public Result(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Result(Integer code, String message, Object object) {
        this.code = code;
        this.message = message;
        this.data = object;
    }
}
  

/**  
 * Project Name:ThornflowerOCR  
 * File Name:UserInfo.java  
 * Package Name:cn.thornflower.pojo  
 * Date:2019年10月23日下午4:26:56  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.pojo;

import lombok.Data;

/**  
 * ClassName:UserInfo <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月23日 下午4:26:56 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Data
public class UserInfo {

    private Integer id;
    private String openId;
    private String sessionKey;
    private String sessionId;
}
  

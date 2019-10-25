/**  
 * Project Name:ThornflowerOCR  
 * File Name:UserService.java  
 * Package Name:cn.thornflower.service  
 * Date:2019年10月23日下午2:37:21  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.service;  
/**  
 * ClassName:UserService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月23日 下午2:37:21 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */

import cn.thornflower.pojo.Result;

public interface UserService {

    Result thirdLogin(String code,String userKey);
}
  

/**  
 * Project Name:ThornflowerOCR  
 * File Name:UserController.java  
 * Package Name:cn.thornflower.controller  
 * Date:2019年10月23日下午2:16:46  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.thornflower.pojo.Result;
import cn.thornflower.service.UserService;

/**  
 * ClassName:UserController <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月23日 下午2:16:46 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @GetMapping(value = "thirdLogin")
    public Result login(String code,String userKey) {
        return userService.thirdLogin(code,userKey);
    }
    
    @PostMapping(value = "checkUserStatus")
    public Result checkUserStatus(String userKey) {
        return userService.checkUserStatus(userKey);
    }
}
  

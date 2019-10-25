/**  
 * Project Name:ThornflowerOCR  
 * File Name:UserDaoW.java  
 * Package Name:cn.thornflower.dao  
 * Date:2019年10月23日下午4:18:59  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.mapper;

import org.apache.ibatis.annotations.Mapper;

import cn.thornflower.pojo.UserInfo;

/**  
 * ClassName:UserDaoW <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月23日 下午4:18:59 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Mapper
public interface UserMapperW {

    void addUser(UserInfo userInfo);
}
  

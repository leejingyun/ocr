/**  
 * Project Name:ThornflowerOCR  
 * File Name:OCRService.java  
 * Package Name:cn.thornflower.service  
 * Date:2019年10月21日下午5:09:27  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.service;

import java.io.File;

import cn.thornflower.pojo.Result;

/**  
 * ClassName:OCRService <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月21日 下午5:09:27 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
public interface OCRService {
    
    public Result OCR_byImageBase64(String image);
    
    public Result OCR_byWeiXin(String image,String userKey);
    
    public Result OCR_byWeiXin_upload(File file);
    
}
  

/**  
 * Project Name:ThornflowerOCR  
 * File Name:OCRController.java  
 * Package Name:cn.thornflower.controller  
 * Date:2019年10月21日下午5:51:04  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.thornflower.pojo.Result;
import cn.thornflower.service.OCRService;

/**  
 * ClassName:OCRController <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月21日 下午5:51:04 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@RestController
public class OCRController {

    @Autowired
    private OCRService ocrService;
    
    @RequestMapping(value = "ocr",method = RequestMethod.POST)
    public Result ocrTest(String image){
        return ocrService.OCR_byImageBase64(image);
    }
    
    @RequestMapping(value = "ocr_weixin",method = RequestMethod.POST)
    public Result ocr_weixin(String image,String userKey){
        return ocrService.OCR_byWeiXin(image, userKey);
    }
}
  

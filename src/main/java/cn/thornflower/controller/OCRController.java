/**  
 * Project Name:ThornflowerOCR  
 * File Name:OCRController.java  
 * Package Name:cn.thornflower.controller  
 * Date:2019年10月21日下午5:51:04  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


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
    
    @RequestMapping(value = "ocr_weixin_upload",method = RequestMethod.POST)
    public Result ocr_weixin(@RequestParam("file") MultipartFile  file) throws IOException{
        // 获取文件名
        String fileName = file.getOriginalFilename();
        // 获取文件后缀
        String prefix=fileName.substring(fileName.lastIndexOf("."));
        // 用uuid作为文件名，防止生成的临时文件重复
        final File excelFile = File.createTempFile(UUID.randomUUID().toString(), prefix);
        // MultipartFile to File
        file.transferTo(excelFile);
        Result ocr_byWeiXin_upload = ocrService.OCR_byWeiXin_upload(excelFile);
        //删除临时文件
        deleteFile(excelFile);
        return ocr_byWeiXin_upload;
    }
    
    /**  
     * 删除  
     *   
     * @param files  
     */  
    private void deleteFile(File... files) {  
        for (File file : files) {  
            if (file.exists()) {  
                file.delete();  
            }  
        }  
    }
 
}
  

/**  
 * Project Name:ThornflowerOCR  
 * File Name:OCRServiceImpl.java  
 * Package Name:cn.thornflower.service  
 * Date:2019年10月21日下午5:11:49  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.service;


import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.thornflower.config.WeixinConfig;
import cn.thornflower.pojo.Result;
import cn.thornflower.utils.HttpUtils;
import cn.thornflower.utils.OkHttpUtil;
import cn.thornflower.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;

/**  
 * ClassName:OCRServiceImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月21日 下午5:11:49 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Service
public class OCRServiceImpl implements OCRService{
    
    private static final String OCR_SERVER_URL = "https://ocrapi-advanced.taobao.com";
    private static final String OCR_SERVER_PATH = "/ocrservice/advanced";
    private static final String APP_CODE = "c92bf314fd8e48a2b0d35360d5885462";
    
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private WeixinConfig weixinConfig;
    
    @Override
    public Result OCR_byImageBase64(String image) {
        String http_ocr = http_ocr(image);
        if (http_ocr != null) {
            JSONObject jsonObject = JSON.parseObject(http_ocr);
            String action = jsonObject.getString("content");
            return new Result(200,"解析成功",action);
        }else {
            return new Result(500,"解析失败");
        }
    }
    
    //发送ocr请求，参数：图片base64后的字符串
    private static String http_ocr(String image) {
        String resultJsonString = "";
        //定义请求参数map
        Map<String, String> headers = new HashMap<String, String>();
        //设置阿里云验证应用
        headers.put("Authorization", "APPCODE " + APP_CODE);
        //设置Content-Type
        headers.put("Content-Type", "application/json; charset=UTF-8");
        //query参数   可以为空
        Map<String, String> querys = new HashMap<String, String>();
        //查询参数
        JSONObject body = new JSONObject();
        body.put("img", image);
        body.put("charInfo", true);
        body.put("rotate", true);
        body.put("table", true);
        body.put("sortPage", true);
        String bodyString = body.toJSONString();
        
        try {
            //通过http工具类发送请求
            HttpResponse doPost = HttpUtils.doPost(OCR_SERVER_URL, OCR_SERVER_PATH, "POST", headers, querys, bodyString);
            //http code 等于200  返回，否则返回null
            if (doPost.getStatusLine().getStatusCode() == 200) {
                //获取response的body（返回的JSON）
                resultJsonString = EntityUtils.toString(doPost.getEntity());
            }else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();  
        }
        return resultJsonString;
    }

    /**
     * 使用微信小程序的ocr
     */
    @Override
    public Result OCR_byWeiXin(String image,String userKey) {
        String weixin_get_accessToken = weixinConfig.weixin_get_accessToken();
        String url = "https://api.weixin.qq.com/cv/ocr/comm?img_url" + image +"&access_token=" + weixin_get_accessToken;
        return null;
    }

    /**
     * 使用微信小程序的ocr
     * 
     */
    @Override
    public Result OCR_byWeiXin_upload(File file) {
        //获取access_token
        String weixin_get_accessToken = weixinConfig.weixin_get_accessToken();
        
        if(!"".equals(weixin_get_accessToken)){
            //把 access_token 封装到请求参数中
            Map<String, Object> querys = new HashMap<String, Object>();
            querys.put("access_token", weixin_get_accessToken);
            //发送ocr请求
            String postFile = OkHttpUtil.postFile("https://api.weixin.qq.com/cv/ocr/comm", querys, file);
            return new Result(200, "解析成功", postFile);
        }else{
            return new Result(200, "服务异常");
        }
    }
}
  

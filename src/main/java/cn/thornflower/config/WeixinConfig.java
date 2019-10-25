/**  
 * Project Name:ThornflowerOCR  
 * File Name:WeixinConfig.java  
 * Package Name:cn.thornflower.config  
 * Date:2019年10月25日下午2:43:59  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.alibaba.fastjson.JSONObject;

import cn.thornflower.service.HttpAPIService;
import cn.thornflower.utils.RedisUtils;


/**  
 * ClassName:WeixinConfig <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月25日 下午2:43:59 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        微信工具类
 */
@Configuration
@PropertySource("classpath:weixin_server_url.properties")
public class WeixinConfig {
    
    @Value("${weixin.appId}")
    private String weixin_appId;
 
    @Value("${weixin.appsecret}")
    private String weixin_appsecret;
    
    @Value("${weixin.login.url}")
    private String weixin_login_url;
 
    @Value("${weixin.accessToken.url}")
    private String weixin_accessToken_url;
    
    @Value("${weixin.ocr.printedText.url}")
    private String weixin_ocr_printedText_url;
    
    @Autowired
    private RedisUtils redisUtils;
    
    @Autowired
    private HttpAPIService httpAPIService;
    
    //登陆完整地址
    public String getWeixinLoginUrl(String code){
        String url = weixin_login_url +"&appid=" + weixin_appId +"&secret=" + weixin_appsecret +"&js_code=" + code + "&grant_type=authorization_code";
        return url;
    }
    
    //请求token完整地址
    public String weixin_accessToken_url(){
        String url = weixin_accessToken_url +"&appid=" + weixin_appId +"&secret=" + weixin_appsecret;
        return url;
    }
    
    //ocr完整地址
    public String weixin_ocr_printedText_url(){
        String url = weixin_ocr_printedText_url +"&appid=" + weixin_appId +"&secret=" + weixin_appsecret;
        return url;
    }
    
    /**
     * 
     * @Description 获取token,需要用户的openId
     * @param openId
     * @return
     */
    public String weixin_get_accessToken(String openId){
        String access_token = "";
        try {
            //首先根据 用户的openid和AccessToken 组合的Key 去redis中查找 Token
            Object object = redisUtils.get("AccessToken"+openId);
            //如果没找到 则 请求一条新的token，并存到redis中
            if (object == null) {
                //get请求，请求token
                String doGet = httpAPIService.doGet(weixin_accessToken_url());
                //将返回字符串解析成JSON字符串，然后获取token
                JSONObject parseObject = JSONObject.parseObject(doGet);
                access_token = parseObject.getString("access_token");
                //根据系统当前时间获取时间，加7000秒得到过期时间
                long time = System.currentTimeMillis() + 7000;//获得系统当前时间  加7000秒。得到保存时间
                //token存入redis,并设置过期时间
                redisUtils.set("AccessToken"+openId, access_token, time);
            }else {
                //如果找到则返回token
                JSONObject parseObject = JSONObject.parseObject((String) object);
                access_token = parseObject.getString("AccessToken"+openId);
            }
        } catch (Exception e) {
            e.printStackTrace();  
        }
        return access_token;
    }
}
  

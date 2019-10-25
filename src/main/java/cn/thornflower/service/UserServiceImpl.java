/**  
 * Project Name:ThornflowerOCR  
 * File Name:UserServiceImpl.java  
 * Package Name:cn.thornflower.service  
 * Date:2019年10月23日下午2:37:48  
 * Copyright (c) 2019
 *  
*/  
  
package cn.thornflower.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.thornflower.config.WeixinConfig;
import cn.thornflower.mapper.UserMapperR;
import cn.thornflower.mapper.UserMapperW;
import cn.thornflower.pojo.Result;
import cn.thornflower.pojo.UserInfo;
import cn.thornflower.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;

/**  
 * ClassName:UserServiceImpl <br/>  
 * Function: TODO ADD FUNCTION. <br/>  
 * Reason:   TODO ADD REASON. <br/>  
 * Date:     2019年10月23日 下午2:37:48 <br/>  
 * @author   李景云  
 * @version    
 * @since    JDK 1.8  
 * @see        
 */
@Service
@Slf4j
public class UserServiceImpl implements UserService{
    
    @Autowired
    private HttpAPIService httpAPIService;
    @Autowired
    private UserMapperW userMapperW;
    @Autowired
    private UserMapperR userMapperR;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private WeixinConfig weixinConfig;
    
    @Override
    public Result thirdLogin(String code,String userKey) {
        
        if (!"".equals(userKey) || userKey != null) {
            Object userForRedis = redisUtils.get(userKey);
            if (!(userForRedis == null)) {
                return new Result(200, "已登录成功",userKey);
            }
        }
        
        //微信小程序登陆链接
        String weixin_login_url = weixinConfig.getWeixinLoginUrl(code);
        
        try {
            String doGet = httpAPIService.doGet(weixin_login_url);
            log.info("用户openid:"+doGet);
            JSONObject parseObject = JSONObject.parseObject(doGet);
            String openId = parseObject.getString("openid");
            UserInfo userIdBySessionId = userMapperR.getUserIdBySessionId(openId);
            String generate_UUID = generate_UUID();
            //log.info("用户详情：" + userIdBySessionId.toString());
            //如果数据库中没有这个用户把这个新用户保存到数据库
            if (userIdBySessionId == null) {
                UserInfo userInfo = new UserInfo();
                userInfo.setOpenId(openId);
                userInfo.setSessionKey(parseObject.getString("session_key"));
                userMapperW.addUser(userInfo);
            }else {
                log.info("用户openid:"+openId);
            }
            
            //根据openid从redis中获取skey,如果存在删除重新生成skey返回
            Object redis_openId = redisUtils.get(openId);
            if (redis_openId == null) {
                redisUtils.del(openId);
            }

            //  缓存一份新的
            JSONObject sessionObj = new JSONObject();
            sessionObj.put( "openId",openId );
            sessionObj.put( "sessionKey",parseObject.getString("session_key") );
            redisUtils.set(generate_UUID, sessionObj.toJSONString());
            redisUtils.set(openId, generate_UUID);
 
            return new Result(200, "登陆成功",generate_UUID);
        } catch (Exception e) {
            e.printStackTrace();  
            log.error(e.getMessage());
        }
        return new Result(500, "登陆失败");
    }

    
    private static String generate_UUID() {
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        return str.replace("-", "");
    }
    
}
  

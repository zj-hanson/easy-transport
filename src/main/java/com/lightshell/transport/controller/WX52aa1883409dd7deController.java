package com.lightshell.transport.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.SendMessageV3Request;
import com.baidubce.services.sms.model.SendMessageV3Response;
import com.lightshell.transport.common.ResponseObject;
import com.lightshell.transport.entity.WechatSession;
import com.lightshell.transport.entity.WechatUser;
import com.lightshell.transport.service.WX52aa1883409dd7deServiceImpl;
import com.lightshell.transport.service.WechatSessionServiceImpl;
import com.lightshell.transport.service.WechatUserServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping({"/wx52aa1883409dd7de"})
public class WX52aa1883409dd7deController {

    @Autowired
    private WX52aa1883409dd7deServiceImpl service;

    @Autowired
    private WechatSessionServiceImpl wechatSessionService;

    @Autowired
    private WechatUserServiceImpl wechatUserService;

    @Value("${baidu.sms.access_key_id}")
    private String accessKeyId;

    @Value("${baidu.sms.access_key_secret}")
    private String accessKeySecret;

    @Value("${baidu.sms.signature_id}")
    private String signatureId;

    @Value("${baidu.sms.template_id}")
    private String templateCode;

    @Value("${baidu.sms.endpoint}")
    private String endpoint;

    @Value("${tencent.mp.wx52aa1883409dd7de.appID}")
    private String mpAppID;

    private com.baidubce.services.sms.SmsClient smsClient;

    @GetMapping("/session")
    @ResponseBody
    public ResponseObject<Map<String, Object>> getSession(@RequestParam("code") String code) {
        JSONObject sessionInfo = service.getSessionInfo(service.getAppID(), service.getAppSecret(), code);
        if (sessionInfo != null) {
            log.info("微信小程序code2session:" + sessionInfo.toString());
            Map<String, Object> map = new HashMap<>();
            String openId = sessionInfo.getString("openid");
            String sessionKey = sessionInfo.getString("session_key");
            String sessionId = UUID.randomUUID().toString();
            String unionId = sessionInfo.has("unionid") ? sessionInfo.getString("unionid") : null;

            map.put("openId", openId);
            map.put("unionId", unionId);
            map.put("sessionId", sessionId);
            wechatSessionService.persistIfNotExist(openId, sessionKey, unionId, sessionId);
            WechatUser wechatUser = wechatUserService.findByOpenId(openId);
            if (wechatUser != null) {
                map.put("authorized", wechatUser.isAuthorized());
                map.put("name", wechatUser.getName());
                map.put("phone", wechatUser.getPhone());
            } else {
                map.put("authorized", false);
            }
            return new ResponseObject<>("200", "success", map);
        }
        return new ResponseObject<>("500", "Get Session Failure");
    }

    @PostMapping("/check-code")
    @ResponseBody
    public ResponseObject<Map<String, Object>> getCheckCode(@RequestBody Map<String, String> data) {
        if (data.containsKey("openId") && data.containsKey("sessionId") && data.containsKey("phone")) {
            String openId = data.get("openId");
            String sessionId = data.get("sessionId");
            String phoneNumber = data.get("phone");
            WechatSession wechatSession = wechatSessionService.findByOpenIdAndSessionId(openId, sessionId);
            if (wechatSession == null) {
                return new ResponseObject<>("404", "Get SessionInfo Failure");
            }
            String code = wechatSessionService.getCheckCode();
            SendMessageV3Request request = new SendMessageV3Request();
            request.setMobile(phoneNumber);
            request.setSignatureId(signatureId);
            request.setTemplate(templateCode);
            Map<String, String> contentVar = new HashMap<>();
            contentVar.put("code", code);
            request.setContentVar(contentVar);
            try {
                if (smsClient == null) {
                    initSmsClient();
                }
                SendMessageV3Response response = smsClient.sendMessage(request);
                if (response != null && response.isSuccess()) {
                    // 记录发送状态
                    wechatSession.setCheckCode(code);
                    wechatSession.setExpiresIn(600);
                    wechatSessionService.save(wechatSession);
                    Map<String, Object> map = new HashMap<>();
                    map.put("checkCode", code);
                    ResponseObject<Map<String, Object>> result = new ResponseObject<>("200", "success");
                    result.setObject(map);
                    return result;
                }
                return new ResponseObject<>("500", "系统异常");
            } catch (Exception ex) {
                log.error(ex.toString());
            }
        }
        return new ResponseObject<>("500", "Get RequestBody Failure");
    }

    @PostMapping("/phone-number")
    @ResponseBody
    public ResponseObject<Map<String, Object>> getPhoneNumber(@RequestBody Map<String, String> data) {
        if (data.containsKey("code") && data.containsKey("sessionId")) {
            String code = data.get("code");
            String sessionId = data.get("sessionId");
            if (!wechatSessionService.hasSession(sessionId)) {
                return new ResponseObject<>("404", "Get SessionInfo Failure");
            }
            JSONObject jsonObject = service.getPhoneNumber(service.getAppID(), service.getAppSecret(), code);
            if (jsonObject != null && jsonObject.getInt("errcode") == 0) {
                Map<String, Object> map = new HashMap<>();
                JSONObject phoneInfo = jsonObject.getJSONObject("phone_info");
                map.put("phoneNumber", phoneInfo.getString("phoneNumber"));
                map.put("purePhoneNumber", phoneInfo.getString("purePhoneNumber"));
                map.put("countryCode", phoneInfo.getString("countryCode"));
                ResponseObject<Map<String, Object>> result = new ResponseObject<>("200", "success");
                result.setObject(map);
                return result;
            }
        }
        return new ResponseObject<>("500", "Get RequestBody Failure");
    }

    @PostMapping("/wechat-user")
    @ResponseBody
    ResponseObject<WechatUser> createWechatUser(@RequestBody Map<String, Object> data) {
        if (data.containsKey("openId") && data.containsKey("sessionId") && data.containsKey("phone")
            && data.containsKey("checkCode")) {
            String openId = data.get("openId").toString();
            String sessionId = data.get("sessionId").toString();
            String phoneNumber = data.get("phone").toString();
            String checkCode = data.get("checkCode").toString();
            WechatSession wechatSession = wechatSessionService.findBySessionIdAndCheckCode(sessionId, checkCode);
            if (wechatSession == null) {
                return new ResponseObject<>("404", "Get SessionInfo Failure");
            }
            wechatSession.setStatus("V");
            wechatSessionService.save(wechatSession);
            String name = data.get("name").toString();
            String role = data.get("role").toString();
            WechatUser wechatUser;
            wechatUser = wechatUserService.findByOpenId(openId);
            if (wechatUser == null) {
                wechatUser = new WechatUser(name, role, phoneNumber, openId, null, true);
                wechatUser.setAppId(mpAppID);
            } else {
                wechatUser.setPhone(phoneNumber);
                wechatUser.setRole(role);
                wechatUser.setAuthorized(true);
            }
            wechatUserService.save(wechatUser);

            return new ResponseObject<>("200", "授权成功", wechatUser);
        }
        return new ResponseObject<>("500", "Get RequestBody Failure");
    }

    protected void initSmsClient() {
        if (smsClient == null) {
            SmsClientConfiguration config = new SmsClientConfiguration();
            config.setCredentials(new DefaultBceCredentials(accessKeyId, accessKeySecret));
            config.setEndpoint(endpoint);
            smsClient = new SmsClient(config);
        }
    }

}

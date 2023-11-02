package com.lightshell.transport.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.lightshell.wx.WeChatUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WX67f3d33b78b2ee91ServiceImpl extends WeChatUtil {

    @Value("${tencent.mp.wx67f3d33b78b2ee91.appID}")
    private String appID;

    @Value("${tencent.mp.wx67f3d33b78b2ee91.appSecret}")
    private String appSecret;

    public WX67f3d33b78b2ee91ServiceImpl() {

    }

    @Override
    public String getAppID() {
        return this.appID;
    }

    @Override
    public String getAppSecret() {
        return this.appSecret;
    }

    @Override
    protected String getAppToken() {
        return null;
    }

    @Override
    public void initConfiguration() {

    }

    public JSONObject getSessionInfo(String appId, String appSecret, String code) {
        StringBuilder sb = new StringBuilder();
        sb.append("https://api.weixin.qq.com/sns/jscode2session");
        sb.append("?appid=").append(appId);
        sb.append("&secret=").append(appSecret);
        sb.append("&js_code=").append(code);
        sb.append("&grant_type=authorization_code");

        CloseableHttpResponse response = get(sb.toString(), null, null);
        if (response != null) {
            try {
                HttpEntity entity = response.getEntity();
                JSONObject jsonObject = new JSONObject(EntityUtils.toString(entity, WeChatUtil.CHARSET));
                if (!jsonObject.has("errcode")) {
                    return jsonObject;
                } else {
                    log.error(jsonObject.get("errmsg").toString());
                }
            } catch (IOException | ParseException | JSONException ex) {
                log.error(ex.toString());
            } finally {
                try {
                    response.close();
                } catch (IOException ex) {
                    log.error(ex.toString());
                }
            }
        }
        return null;
    }

    public JSONObject getPhoneNumber(String appId, String appSecret, String code) {
        this.setAccessToken(appId, appSecret);
        String accessToken = this.getAccessToken(appId, appSecret);
        if (accessToken == null || "".equals(accessToken)) {
            return null;
        }
        String url = "https://api.weixin.qq.com/wxa/business/getuserphonenumber" + "?access_token=" + accessToken;
        JSONObject postObject = new JSONObject();
        postObject.put("code", code);
        CloseableHttpResponse response = post(url, initStringEntity(postObject.toString()));
        if (response != null) {
            try {
                HttpEntity entity = response.getEntity();
                return new JSONObject(EntityUtils.toString(entity, WeChatUtil.CHARSET));
            } catch (IOException | ParseException | JSONException ex) {
                log.error(ex.toString());
            } finally {
                try {
                    response.close();
                } catch (IOException ex) {
                    log.error(ex.toString());
                }
            }
        }
        return null;
    }

}

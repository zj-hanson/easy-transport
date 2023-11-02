package com.lightshell.transport.common;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.ParseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OAuth2Util {

    static final String CHARSET = "UTF-8";
    static final String TOKEN_URI = "/oauth2/token?grant_type=client_credentials";

    @Value("${spring.security.oauth2.resourceserver.jwt.issuer-uri}")
    private String ISSUER_URI;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-id}")
    private String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaquetoken.client-secret}")
    private String clientSecret;

    public JSONObject getToken() {
        String url = ISSUER_URI + TOKEN_URI;
        return getToken(url, clientId, clientSecret);
    }

    public JSONObject getToken(String url, String clientId, String secret) {
        String auth = clientId + ":" + secret;
        String authBase64 = Base64.encodeBase64String(auth.getBytes(StandardCharsets.UTF_8));
        HttpPost httpPost = new HttpPost(url);
        httpPost.setHeader(HttpHeaders.AUTHORIZATION, "Basic " + authBase64);
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity httpEntity = response.getEntity();
                try {
                    return new JSONObject(EntityUtils.toString(httpEntity, CHARSET));

                } catch (IOException | ParseException ex) {
                    log.error(ex.getMessage());
                } finally {
                    try {
                        response.close();
                    } catch (IOException ex) {
                        log.error(ex.getMessage());
                    }
                }
            }
        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        return null;
    }

}

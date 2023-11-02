package com.lightshell.transport.common;

public class Token {

    private String accessToken;
    private String scope;
    private String tokenType;
    private int expires;

    public Token() {}

    public Token(String accessToken, int expires) {
        this.accessToken = accessToken;
        this.expires = expires;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public int getExpires() {
        return expires;
    }

    public void setExpires(int expires) {
        this.expires = expires;
    }
}

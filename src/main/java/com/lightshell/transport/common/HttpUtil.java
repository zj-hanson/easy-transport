package com.lightshell.transport.common;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HttpUtil {

    private HttpUtil() {}

    public static final String CHARSET = "UTF-8";

    protected static CloseableHttpClient httpClient;

    protected static CloseableHttpClient createHttpClient()
        throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {

        SSLContext sslContext = SSLContextBuilder.create().loadTrustMaterial(new TrustSelfSignedStrategy()).build();
        X509TrustManager trustManager = new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                String paramString) throws CertificateException {}

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] paramArrayOfX509Certificate,
                String paramString) throws CertificateException {}

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sslContext.init(null, new TrustManager[] {trustManager}, null);

        HostnameVerifier allowAllHosts = new NoopHostnameVerifier();

        SSLConnectionSocketFactory connectionFactory = new SSLConnectionSocketFactory(sslContext, allowAllHosts);

        return HttpClients.custom().setSSLSocketFactory(connectionFactory).build();

    }

    protected static void initHttpClient() {
        if (httpClient == null) {
            try {
                httpClient = createHttpClient();
            } catch (Exception ex) {
                httpClient = HttpClients.createDefault();
            }
        }
    }

    public static CloseableHttpResponse get(String url, Header header) {
        return get(url, null, null, header);
    }

    public static CloseableHttpResponse get(String url, Map<String, String> params, RequestConfig config,
        Header header) {
        initHttpClient();
        try {
            URIBuilder builder = new URIBuilder(url);
            if (params != null) {
                for (String key : params.keySet()) {
                    builder.addParameter(key, params.get(key));
                }
            }
            URI uri = builder.build();
            HttpGet httpGet = new HttpGet(uri);
            if (config != null) {
                httpGet.setConfig(config);
            }
            if (header != null) {
                httpGet.setHeader(header);
            }
            CloseableHttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return response;
            }
        } catch (IOException | URISyntaxException ex) {
            log.error(ex.toString());
            return null;
        }
        return null;
    }

    public static CloseableHttpResponse post(String url, HttpEntity entity, Header header) {
        initHttpClient();
        HttpPost httpPost = new HttpPost(url);
        if (entity != null) {
            httpPost.setEntity(entity);
        }
        if (header != null) {
            httpPost.setHeader(header);
        }
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return response;
            }
        } catch (IOException ex) {
            log.error(ex.toString());
            return null;
        }
        return null;
    }

    public static CloseableHttpResponse post(String url, HttpEntity entity, Header[] headers) {
        initHttpClient();
        HttpPost httpPost = new HttpPost(url);
        if (entity != null) {
            httpPost.setEntity(entity);
        }
        if (headers != null) {
            httpPost.setHeaders(headers);
        }
        try {
            CloseableHttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                return response;
            }
        } catch (IOException ex) {
            log.error(ex.toString());
            return null;
        }
        return null;
    }

}

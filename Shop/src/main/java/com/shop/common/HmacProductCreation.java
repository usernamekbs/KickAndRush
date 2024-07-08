package com.shop.common;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Component;

import com.coupang.openapi.sdk.Hmac;

import lombok.RequiredArgsConstructor;

import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.utils.URIBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class HmacProductCreation {
    private static final String HOST = "api-gateway.coupang.com";
    private static final int PORT = 443;
    private static final String SCHEMA = "https";
    private static final String ACCESS_KEY = "f20949fb-c3f6-417a-a357-94d4e6f405d6";
    private static final String SECRET_KEY = "9a89b7072ce75492b37d9224e7094dece996c0d7";
    
    public void sendDataToCoupang(String jsonPayload) {
        String method = "POST";
        String path = "/v2/providers/seller_api/apis/api/v1/marketplace/seller-products";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            URI uri = new URIBuilder()
                    .setScheme(SCHEMA)
                    .setHost(HOST)
                    .setPort(PORT)
                    .setPath(path)
                    .build();
            URIBuilder uriBuilder = new URIBuilder()
                    .setPath(path);
            String authorization = Hmac.generate(method, uriBuilder.build().toString(), SECRET_KEY, ACCESS_KEY);
            System.out.println(authorization);

            HttpPost requestPost = new HttpPost(uri);
            requestPost.addHeader("Authorization", authorization);
            requestPost.addHeader("Content-Type", "application/json");
            requestPost.setEntity(EntityBuilder.create().setText(jsonPayload).setContentType(org.apache.http.entity.ContentType.APPLICATION_JSON).build());

            try (CloseableHttpResponse response = client.execute(requestPost)) {
                System.out.println("status code: " + response.getStatusLine().getStatusCode());
                System.out.println("status message: " + response.getStatusLine().getReasonPhrase());
                HttpEntity entity = response.getEntity();
                System.out.println("result: " + EntityUtils.toString(entity));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
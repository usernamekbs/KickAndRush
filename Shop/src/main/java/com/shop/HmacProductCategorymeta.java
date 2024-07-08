//package com.shop;
//
//import com.coupang.openapi.sdk.Hmac;
//import org.apache.http.HttpEntity;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.utils.URIBuilder;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;
//import org.apache.http.util.EntityUtils;
//
//import java.io.IOException;
//
//public class HmacProductCategorymeta {
//	
//    private static final String HOST = "api-gateway.coupang.com";
//    private static final int PORT = 443;
//    private static final String SCHEMA = "https";
//    //replace with your own accessKey
//    private static final String ACCESS_KEY = "f20949fb-c3f6-417a-a357-94d4e6f405d6";
//    //replace with your own secretKey
//    private static final String SECRET_KEY = "145f1d5852adfbdacaded7936b8117bdc7969545";
//
//    public void test() {
//        //params
//        String method = "GET";
//        String path = "https://api-gateway.coupang.com/v2/providers/marketplace_openapi/apis/api/v1/vendor/shipping-place/outbound?placeCodes=경기도 안산시 상록구 충장로 565 111동1203호";
//
//        CloseableHttpClient client = null;
//        try {
//            //create client
//            client = HttpClients.createDefault();
//            //build uri 
//            URIBuilder uriBuilder = new URIBuilder()
//                    .setPath(path);
//
//            /********************************************************/
//            //authorize, demonstrate how to generate hmac signature here
//            String authorization = Hmac.generate(method, uriBuilder.build().toString(), SECRET_KEY, ACCESS_KEY);
//            //print out the hmac key
//            System.out.println(authorization);
//            /********************************************************/
//
//            uriBuilder.setScheme(SCHEMA).setHost(HOST).setPort(PORT);
//            HttpGet get = new HttpGet(uriBuilder.build().toString());
//            /********************************************************/
//            // set header, demonstarte how to use hmac signature here
//            get.addHeader("Authorization", authorization);
//            /********************************************************/
//            get.addHeader("content-type", "application/json");
//            CloseableHttpResponse response = null;
//            try {
//                //execute get request
//                response = client.execute(get);
//                //print result
//                System.out.println("status code:" + response.getStatusLine().getStatusCode());
//                System.out.println("status message:" + response.getStatusLine().getReasonPhrase());
//                HttpEntity entity = response.getEntity();
//                System.out.println("result:" + EntityUtils.toString(entity));
//            } catch (Exception e) {
//                e.printStackTrace();
//            } finally {
//                if (response != null) {
//                    try {
//                        response.close();
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            if (client != null) {
//                try {
//                    client.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        HmacProductCategorymeta hmacTest = new HmacProductCategorymeta();
//        hmacTest.test();
//    }
//}
package com.shop;

import com.coupang.openapi.sdk.Hmac;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HmacProductCreation {
	
    private static final String HOST = "api-gateway.coupang.com";
    private static final int PORT = 443;
    private static final String SCHEMA = "https";
    //replace with your own accessKey
    private static final String ACCESS_KEY = "f20949fb-c3f6-417a-a357-94d4e6f405d6";
    //replace with your own secretKey
    private static final String SECRET_KEY = "9a89b7072ce75492b37d9224e7094dece996c0d7";
    
    //권한 요청 진행중
    public void test() {
        //params
        String method = "POST";
        String path = "/v2/providers/seller_api/apis/api/v1/marketplace/seller-products";
        String strjson="{\"sellerProductName\":\"[커버핏]플라워드로잉 에어팟3세대 아머케이스\",\"vendorId\":\"A00962060\",\"saleStartedAt\":\"2024-01-19T00:00:00\",\"saleEndedAt\":\"2099-01-19T00:00:00\",\"displayProductName\":\"[커버핏]플라워드로잉 에어팟3세대 아머케이스\",\"deliveryMethod\":\"SEQUENCIAL\",\"deliveryCompanyCode\":\"EPOST\",\"deliveryChargeType\":\"NOT_FREE\",\"deliveryCharge\":3000,\"freeShipOverAmount\":30000,\"deliveryChargeOnReturn\":3000,\"remoteAreaDeliverable\":\"N\",\"unionDeliveryType\":\"NOT_UNION_DELIVERY\",\"returnCenterCode\":\"1001694769\",\"returnChargeName\":\"경기도 안산시 상록구 충장로 565 111동1203호\",\"companyContactNumber\":\"010-8900-6085\",\"returnZipCode\":\"15287\",\"returnAddress\":\"경기도 안산시 상록구 충장로 565\",\"returnAddressDetail\":\"111동1203호\",\"returnCharge\":3000,\"outboundShippingPlaceCode\":19389011,\"vendorUserId\":\"qudtn0295\",\"requested\":true,\"items\":[{\"itemName\":\"에어팟3세대 블루\",\"originalPrice\":20000,\"salePrice\":20000,\"maximumBuyCount\":10,\"maximumBuyForPerson\":0,\"maximumBuyForPersonPeriod\":1,\"outboundShippingTimeDay\":2,\"unitCount\":0,\"adultOnly\":\"EVERYONE\",\"taxType\":\"TAX\",\"parallelImported\":\"NOT_PARALLEL_IMPORTED\",\"overseasPurchased\":\"NOT_OVERSEAS_PURCHASED\",\"pccNeeded\":false,\"images\":[{\"imageOrder\":0,\"imageType\":\"REPRESENTATION\",\"vendorPath\":\"https://qudtn0295.cafe24.com/web/product/big/thmb70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.png\"}],\"attributes\":[{\"attributeTypeName\":\"옵션2\",\"attributeValueName\":\"에어팟3세대\"},{\"attributeTypeName\":\"옵션\",\"attributeValueName\":\"블루\"}],\"contents\":[{\"contentsType\":\"TEXT\",\"contentDetails\":[{\"content\":\"<p style='color:red; font-size: medium;' align=\\\"center\\\">주문 제작 상품입니다. 출고 소요일 까지 약 2일 정도 소요됩니다 주문 제작 상품이라 반품이 어렵습니다! 제품에 하자가 있을 시에만 반품 가능합니다</p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.jpeg'/></p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/download.jpeg'/></p>\",\"detailType\":\"TEXT\"}]}],\"searchTags\":[\"커버핏\",\"트라이코지\",\"갤럭시\",\"아이폰\",\"케이스\"]},{\"itemName\":\"에어팟3세대 핑크\",\"originalPrice\":20000,\"salePrice\":20000,\"maximumBuyCount\":10,\"maximumBuyForPerson\":0,\"maximumBuyForPersonPeriod\":1,\"outboundShippingTimeDay\":2,\"unitCount\":0,\"adultOnly\":\"EVERYONE\",\"taxType\":\"TAX\",\"parallelImported\":\"NOT_PARALLEL_IMPORTED\",\"overseasPurchased\":\"NOT_OVERSEAS_PURCHASED\",\"pccNeeded\":false,\"images\":[{\"imageOrder\":0,\"imageType\":\"REPRESENTATION\",\"vendorPath\":\"https://qudtn0295.cafe24.com/web/product/big/thmb70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.png\"}],\"attributes\":[{\"attributeTypeName\":\"옵션2\",\"attributeValueName\":\"에어팟3세대\"},{\"attributeTypeName\":\"옵션\",\"attributeValueName\":\"핑크\"}],\"contents\":[{\"contentsType\":\"TEXT\",\"contentDetails\":[{\"content\":\"<p style='color:red; font-size: medium;' align=\\\"center\\\">주문 제작 상품입니다. 출고 소요일 까지 약 2일 정도 소요됩니다 주문 제작 상품이라 반품이 어렵습니다! 제품에 하자가 있을 시에만 반품 가능합니다</p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.jpeg'/></p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/download.jpeg'/></p>\",\"detailType\":\"TEXT\"}]}],\"searchTags\":[\"커버핏\",\"트라이코지\",\"갤럭시\",\"아이폰\",\"케이스\"]},{\"itemName\":\"에어팟3세대 코랄\",\"originalPrice\":20000,\"salePrice\":20000,\"maximumBuyCount\":10,\"maximumBuyForPerson\":0,\"maximumBuyForPersonPeriod\":1,\"outboundShippingTimeDay\":2,\"unitCount\":0,\"adultOnly\":\"EVERYONE\",\"taxType\":\"TAX\",\"parallelImported\":\"NOT_PARALLEL_IMPORTED\",\"overseasPurchased\":\"NOT_OVERSEAS_PURCHASED\",\"pccNeeded\":false,\"images\":[{\"imageOrder\":0,\"imageType\":\"REPRESENTATION\",\"vendorPath\":\"https://qudtn0295.cafe24.com/web/product/big/thmb70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.png\"}],\"attributes\":[{\"attributeTypeName\":\"옵션\",\"attributeValueName\":\"코랄\"},{\"attributeTypeName\":\"옵션2\",\"attributeValueName\":\"에어팟3세대\"}],\"contents\":[{\"contentsType\":\"TEXT\",\"contentDetails\":[{\"content\":\"<p style='color:red; font-size: medium;' align=\\\"center\\\">주문 제작 상품입니다. 출고 소요일 까지 약 2일 정도 소요됩니다 주문 제작 상품이라 반품이 어렵습니다! 제품에 하자가 있을 시에만 반품 가능합니다</p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.jpeg'/></p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/download.jpeg'/></p>\",\"detailType\":\"TEXT\"}]}],\"searchTags\":[\"커버핏\",\"트라이코지\",\"갤럭시\",\"아이폰\",\"케이스\"]},{\"itemName\":\"에어팟3세대 퍼플\",\"originalPrice\":20000,\"salePrice\":20000,\"maximumBuyCount\":10,\"maximumBuyForPerson\":0,\"maximumBuyForPersonPeriod\":1,\"outboundShippingTimeDay\":2,\"unitCount\":0,\"adultOnly\":\"EVERYONE\",\"taxType\":\"TAX\",\"parallelImported\":\"NOT_PARALLEL_IMPORTED\",\"overseasPurchased\":\"NOT_OVERSEAS_PURCHASED\",\"pccNeeded\":false,\"images\":[{\"imageOrder\":0,\"imageType\":\"REPRESENTATION\",\"vendorPath\":\"https://qudtn0295.cafe24.com/web/product/big/thmb70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.png\"}],\"attributes\":[{\"attributeTypeName\":\"옵션\",\"attributeValueName\":\"퍼플\"},{\"attributeTypeName\":\"옵션2\",\"attributeValueName\":\"에어팟3세대\"}],\"contents\":[{\"contentsType\":\"TEXT\",\"contentDetails\":[{\"content\":\"<p style='color:red; font-size: medium;' align=\\\"center\\\">주문 제작 상품입니다. 출고 소요일 까지 약 2일 정도 소요됩니다 주문 제작 상품이라 반품이 어렵습니다! 제품에 하자가 있을 시에만 반품 가능합니다</p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.jpeg'/></p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/download.jpeg'/></p>\",\"detailType\":\"TEXT\"}]}],\"searchTags\":[\"커버핏\",\"트라이코지\",\"갤럭시\",\"아이폰\",\"케이스\"]},{\"itemName\":\"에어팟3세대 옐로우\",\"originalPrice\":20000,\"salePrice\":20000,\"maximumBuyCount\":10,\"maximumBuyForPerson\":0,\"maximumBuyForPersonPeriod\":1,\"outboundShippingTimeDay\":2,\"unitCount\":0,\"adultOnly\":\"EVERYONE\",\"taxType\":\"TAX\",\"parallelImported\":\"NOT_PARALLEL_IMPORTED\",\"overseasPurchased\":\"NOT_OVERSEAS_PURCHASED\",\"pccNeeded\":false,\"images\":[{\"imageOrder\":0,\"imageType\":\"REPRESENTATION\",\"vendorPath\":\"https://qudtn0295.cafe24.com/web/product/big/thmb70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.png\"}],\"attributes\":[{\"attributeTypeName\":\"옵션2\",\"attributeValueName\":\"에어팟3세대\"},{\"attributeTypeName\":\"옵션\",\"attributeValueName\":\"옐로우\"}],\"contents\":[{\"contentsType\":\"TEXT\",\"contentDetails\":[{\"content\":\"<p style='color:red; font-size: medium;' align=\\\"center\\\">주문 제작 상품입니다. 출고 소요일 까지 약 2일 정도 소요됩니다 주문 제작 상품이라 반품이 어렵습니다! 제품에 하자가 있을 시에만 반품 가능합니다</p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.jpeg'/></p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/download.jpeg'/></p>\",\"detailType\":\"TEXT\"}]}],\"searchTags\":[\"커버핏\",\"트라이코지\",\"갤럭시\",\"아이폰\",\"케이스\"]},{\"itemName\":\"에어팟3세대 그린\",\"originalPrice\":20000,\"salePrice\":20000,\"maximumBuyCount\":10,\"maximumBuyForPerson\":0,\"maximumBuyForPersonPeriod\":1,\"outboundShippingTimeDay\":2,\"unitCount\":0,\"adultOnly\":\"EVERYONE\",\"taxType\":\"TAX\",\"parallelImported\":\"NOT_PARALLEL_IMPORTED\",\"overseasPurchased\":\"NOT_OVERSEAS_PURCHASED\",\"pccNeeded\":false,\"images\":[{\"imageOrder\":0,\"imageType\":\"REPRESENTATION\",\"vendorPath\":\"https://qudtn0295.cafe24.com/web/product/big/thmb70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.png\"}],\"attributes\":[{\"attributeTypeName\":\"옵션2\",\"attributeValueName\":\"에어팟3세대\"},{\"attributeTypeName\":\"옵션\",\"attributeValueName\":\"그린\"}],\"contents\":[{\"contentsType\":\"TEXT\",\"contentDetails\":[{\"content\":\"<p style='color:red; font-size: medium;' align=\\\"center\\\">주문 제작 상품입니다. 출고 소요일 까지 약 2일 정도 소요됩니다 주문 제작 상품이라 반품이 어렵습니다! 제품에 하자가 있을 시에만 반품 가능합니다</p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/트라이코지 플라워드로잉_에어팟3세대,프로,프로2_아머.jpeg'/></p><p align=\\\"center\\\"><img src='https://qudtn0295.cafe24.com/detailImage70/download.jpeg'/></p>\",\"detailType\":\"TEXT\"}]}],\"searchTags\":[\"커버핏\",\"트라이코지\",\"갤럭시\",\"아이폰\",\"케이스\"]}]}\n"
        		+ "";
        //replace with your own product registration json data
        CloseableHttpClient client = null;
        try {
            //create client
            client = HttpClients.createDefault();
            //build uri
            URIBuilder uriBuilder = new URIBuilder()
                    .setPath(path);

            /********************************************************/
            //authorize, demonstrate how to generate hmac signature here
            String authorization = Hmac.generate(method, uriBuilder.build().toString(), SECRET_KEY, ACCESS_KEY);
            //print out the hmac key
            System.out.println(authorization);
            /********************************************************/

            uriBuilder.setScheme(SCHEMA).setHost(HOST).setPort(PORT);
            HttpPost requestPost = new HttpPost(uriBuilder.build().toString());

            StringEntity params =new StringEntity(strjson,"UTF-8");
            
            /********************************************************/
            // set header, demonstarte how to use hmac signature here
            requestPost.addHeader("Authorization", authorization);
            /********************************************************/
            requestPost.addHeader("content-type", "application/json");
            requestPost.setEntity(params);
            CloseableHttpResponse response = null;
            try {
                //execute post request
                response = client.execute(requestPost);
                //print result
                System.out.println("status code:" + response.getStatusLine().getStatusCode());
                System.out.println("status message:" + response.getStatusLine().getReasonPhrase());
                HttpEntity entity = response.getEntity();
                System.out.println("result:" + EntityUtils.toString(entity));
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (response != null) {
                    try {
                        response.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (client != null) {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        HmacProductCreation hmacTest = new HmacProductCreation();
        hmacTest.test();
    }
}
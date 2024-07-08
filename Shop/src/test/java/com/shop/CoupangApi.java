package com.shop;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.jayway.jsonpath.Option;
import com.shop.dto.CoupangAttributesRequestDto;
import com.shop.dto.CoupangContentsRequestDto;
import com.shop.dto.CoupangDetailsRequestDto;
import com.shop.dto.CoupangDto;
import com.shop.dto.CoupangImagesRequestDto;
import com.shop.dto.CoupangItemsDto;
import com.shop.dto.CoupangItemsRequestDto;
import com.shop.dto.CoupangRequestDto;
import com.shop.entity.CoupangAttributesRepository;
import com.shop.entity.CoupangContentsRepository;
import com.shop.entity.CoupangDetailsRepository;
import com.shop.entity.CoupangImagesRepository;
import com.shop.entity.CoupangItemsRepository;
import com.shop.entity.CoupangRepository;
import com.shop.entity.ProductAttributesRepository;
import com.shop.entity.ProductRepository;

@SpringBootTest
class CoupangApi {
    
	@Autowired CoupangRepository coupangRepository;
	@Autowired CoupangItemsRepository coupangItemsRepository;
	@Autowired CoupangAttributesRepository coupangAttributesRepository;
	@Autowired CoupangImagesRepository coupangImagesRepository;
	@Autowired CoupangContentsRepository coupangContentsRepository;
	@Autowired CoupangDetailsRepository coupangDetailsRepository;
	
	public static final String filePath= "C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\옵션.xlsx";
	/////////////////////////CAFE24 쿠팡////////////////////////////
	@Test
	//test 코드에서 transactional 사용 하지 마라
	void asd() throws InterruptedException, IOException {
		WebDriver driver;
	 	EdgeOptions options = new EdgeOptions();

	 	options.addArguments("--start-maximized");
	 	options.setPageLoadTimeout(Duration.ofSeconds(60)); // 페이지 로드 타임아웃 설정 (예: 60초)
	 	driver = new EdgeDriver(options);
	 	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        driver.get("https://trycozy.com/member/login.html");
        WebElement member_id=driver.findElement(By.id("member_id"));
        WebElement member_passwd=driver.findElement(By.id("member_passwd"));
        WebElement login=driver.findElement(By.cssSelector("form > div > div > fieldset > a > img"));
        member_id.sendKeys("qudtn0295");
        //qudtn0295 qudtn4589
        member_passwd.sendKeys("qudtn4589");
        login.click();
        Thread.sleep(5000);
//        coupangRepository.deleteAll();
//        coupangItemsRepository.deleteAll();
//        coupangAttributesRepository.deleteAll();
//        coupangContentsRepository.deleteAll();
//        coupangDetailsRepository.deleteAll();
        //22
        driver.navigate().to("https://trycozy.com/product/list.html?cate_no=482&page=1");
        
        //변수 
        List<String> urls = new ArrayList<>();

        //tcnt
        for(int i=1; i<=20;i++) {
        	WebElement nextPage=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-normalpaging > p:nth-child(4) > a"));
        	List<WebElement> elements = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-normalpackage > div.xans-element-.xans-product.xans-product-listnormal > ul > li > div > a	"));
        	for(WebElement ele : elements) {
        		urls.add(ele.getAttribute("href"));
        	}
        	nextPage.click();
        }
        
        for(String url : urls) {
        	driver.navigate().to(url);
        	//셀렉트박스는 두개값 만 가져옴
        	List<String> imgs = new ArrayList<>();
        	List<WebElement> thumbLists = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.xans-element-.xans-product.xans-product-image.imgArea > div.keyImg > a > img"));
        	
        	List<WebElement> detailImageLists  = driver.findElements(By.cssSelector("#prdDetail > div > p > img"));
            List<WebElement> detailImageLists2 = driver.findElements(By.cssSelector("#prdDetail > div > p > a > img"));
            List<WebElement> detailImageLists3 = driver.findElements(By.cssSelector("#prdDetail > div > div > p > img"));
            List<WebElement> detailImageLists4 = driver.findElements(By.cssSelector("#prdDetail > div > div > img"));
            List<WebElement> detailImageLists5 = driver.findElements(By.cssSelector("#prdDetail > div > img"));
            List<WebElement> detailImageLists6 = driver.findElements(By.cssSelector("#prdDetail > div > div > span > p > img"));
            List<WebElement> detailImageLists7 = driver.findElements(By.cssSelector("#prdDetail > div > div > div > p > img"));
        	
            String fileName = null;
            String image=null;
            
            for(WebElement thmb : thumbLists) {
        		String thbFormat = thmb.getAttribute("src").substring(thmb.getAttribute("src").length()-4,thmb.getAttribute("src").length());
        		if(thbFormat.equals(".jpg")||thbFormat.equals(".gif")||thbFormat.equals(".png")||thbFormat.equals(".jpeg")) {
        			image = thmb.getAttribute("src");
        		    fileName = thmb.getAttribute("src").substring(thmb.getAttribute("src").lastIndexOf("/") + 1);
        			fileName = URLDecoder.decode(fileName,"UTF-8").toString().substring(0, URLDecoder.decode(fileName,"UTF-8").length()-4);
        			String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\";
              		File afterFile = new File(savePath + URLDecoder.decode(fileName,"UTF-8")+".png");
              		Thread.sleep(2000);
              		saveThumbImage(image,savePath,afterFile);	
              		
        		}else {
        			image = thmb.getAttribute("src");
        			fileName = thmb.getAttribute("src").substring(thmb.getAttribute("src").lastIndexOf("/") + 1);
        			fileName =URLDecoder.decode(fileName,"UTF-8").toString().substring(0, URLDecoder.decode(fileName,"UTF-8").length()-5);
        			String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\새 폴더\\";
              		File afterFile = new File(savePath + URLDecoder.decode(fileName,"UTF-8")+".png");
              		String img =URLDecoder.decode(image,"UTF-8").toString().substring(0, URLDecoder.decode(image,"UTF-8").length()-5);
        			Thread.sleep(2000);
              		saveThumbImage(img+".gif",savePath,afterFile);	
        		}
            }

            StringBuilder sb =new StringBuilder();
            sb.append("<p style='color:red; font-size: medium;' align=\"center\">"+"주문 제작 상품입니다. 출고 소요일 까지 약 2일 정도 소요됩니다 주문 제작 상품이라 반품이 어렵습니다! 제품에 하자가 있을 시에만 반품 가능합니다"+"</p>");
            
            for(WebElement detailImage : detailImageLists) {
            	 ((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", detailImage);
            	 String extension = detailImage.getAttribute("src").substring(detailImage.getAttribute("src").length()-4,detailImage.getAttribute("src").length());
            	 String imgUrl = detailImage.getAttribute("src");
            	 if(extension.equals(".jpg")||extension.equals(".gif")||extension.equals(".png")||extension.equals(".jpeg")) {
            		 imgs.add(imgUrl);
            	 }
            }
            
		   	for(WebElement detailImage2 : detailImageLists2) {
		   		((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", detailImage2);
		   		 String extension = detailImage2.getAttribute("src").substring(detailImage2.getAttribute("src").length()-4,detailImage2.getAttribute("src").length());
           	 
	   			 String imgUrl2 = detailImage2.getAttribute("src");
	   			 if(extension.equals(".jpg")||extension.equals(".gif")||extension.equals(".png")||extension.equals(".jpeg")) {
	   				 imgs.add(imgUrl2);
	   			 }
		   	}
		   	
		   	for(WebElement detailImage3 : detailImageLists3) {
		   		((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", detailImage3);
		   		 String extension = detailImage3.getAttribute("src").substring(detailImage3.getAttribute("src").length()-4,detailImage3.getAttribute("src").length());
	           	 
	   			 String imgUrl3 = detailImage3.getAttribute("src");
	   			 if(extension.equals(".jpg")||extension.equals(".gif")||extension.equals(".png")||extension.equals(".jpeg")) {
	   				 imgs.add(imgUrl3);
	   			 }
		   	}
		   	
		   	for(WebElement detailImage4 : detailImageLists4) {
		   		((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", detailImage4);
		   		 String extension = detailImage4.getAttribute("src").substring(detailImage4.getAttribute("src").length()-4,detailImage4.getAttribute("src").length());
	           	 
	   			 String imgUrl4 = detailImage4.getAttribute("src");
	   			 if(extension.equals(".jpg")||extension.equals(".gif")||extension.equals(".png")||extension.equals(".jpeg")) {
	   				 imgs.add(imgUrl4);
	   			 }
		   	}
		   	
		   	
		   	for(WebElement detailImage5 : detailImageLists5) {
		   		((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", detailImage5);
		   		 String extension = detailImage5.getAttribute("src").substring(detailImage5.getAttribute("src").length()-4,detailImage5.getAttribute("src").length());
	           	 
	   			 String imgUrl5 = detailImage5.getAttribute("src");
	   			 if(extension.equals(".jpg")||extension.equals(".gif")||extension.equals(".png")||extension.equals(".jpeg")) {
		   			
	   				 imgs.add(imgUrl5);
	   			 }
   			 }
		   	
		   	for(WebElement detailImage6 : detailImageLists6) {
		   		((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", detailImage6);
		   		 String extension = detailImage6.getAttribute("src").substring(detailImage6.getAttribute("src").length()-4,detailImage6.getAttribute("src").length());
	           	 
	   			 String imgUrl6 = detailImage6.getAttribute("src");
	   			 if(extension.equals(".jpg")||extension.equals(".gif")||extension.equals(".png")||extension.equals(".jpeg")) {
		   			
	   				 imgs.add(imgUrl6);
	   			 }
   			 }
		   	
		   	for(WebElement detailImage7 : detailImageLists7) {
		   		((JavascriptExecutor) driver).executeScript(
                        "arguments[0].scrollIntoView();", detailImage7);
		   		 String extension = detailImage7.getAttribute("src").substring(detailImage7.getAttribute("src").length()-4,detailImage7.getAttribute("src").length());
	           	 
	   			 String imgUrl7 = detailImage7.getAttribute("src");
	   			 if(extension.equals(".jpg")||extension.equals(".gif")||extension.equals(".png")||extension.equals(".jpeg")) {
		   			
	   				 imgs.add(imgUrl7);
	   			 }
   			 }
		   	
		   	String detailImgs = null;
		   	for(String img : imgs) {
				 String imageUrl  = img;
				 String savePath ="C:\\Users\\15kso\\OneDrive\\바탕 화면\\detailimage\\";
				 detailImgs = img.substring(img.lastIndexOf("/") + 1);
				 String fileNames =  URLDecoder.decode(detailImgs,"UTF-8").toString().substring(0, URLDecoder.decode(detailImgs,"UTF-8").length()-4);
				 if(fileNames.length()<50) {
					 File afterFile = new File(savePath + fileNames +".jpeg");
					 sb.append("<p align=\"center\">"+"<img src='https://qudtn0295.cafe24.com/detailImage70/"+URLDecoder.decode(fileNames,"UTF-8").toString()+".jpeg"+"'/>"+"</p>");
					 saveDetailImage(imageUrl,savePath,afterFile);
				 }else{
					 String uuid2 = UUID.randomUUID().toString();
					 File afterFile = new File(savePath + uuid2 +".jpeg");
					 sb.append("<p align=\"center\">"+"<img src='https://qudtn0295.cafe24.com/detailImage70/"+uuid2.toString()+".jpeg"+"'/>"+"</p>");
					 saveDetailImage(imageUrl,savePath,afterFile);
				 }
			}
		   	
		   
            WebElement title=wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > h2")));
//        	WebElement realPrice=driver.findElement(By.xpath("//*[@id=\"contents\"]/div[2]/div[2]/div[2]/div[3]/table/tbody/tr[4]/td/span"));
        	WebElement price=driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > div.xans-element-.xans-product.xans-product-detaildesign > table > tbody > tr:nth-child(2) > td > strong > span > strong"));
        	WebElement productCode = driver.findElement(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > div.xans-element-.xans-product.xans-product-detaildesign > table > tbody > tr:nth-child(6) > td > span"));
       
        	String rePrice = price.getText();
        	rePrice = rePrice.replace(",","");
        	rePrice = rePrice.replace("원","");
        	int reInPrice= Integer.parseInt(rePrice);
        	if(reInPrice<10000) {
        		reInPrice = reInPrice+10500;
        	}else {
        		reInPrice = reInPrice+11500;
        	}
        	
        	String reTitle = title.getText();
        	 
        	reTitle =reTitle.replace("[TryCozy]트라이코지 ","[커버핏]");
        	reTitle =reTitle.replace("[PEANUTS]","[커버핏]");
        	reTitle =reTitle.replace("[Sanrio]","[커버핏]");
        	reTitle =reTitle.replace("[MARVEL]","[커버핏]");
        	reTitle =reTitle.replace("[Anne]","[커버핏]");
        	reTitle =reTitle.replace("[SHINCHAN]","[커버핏]");
        	reTitle =reTitle.replace("[Silver Bell]","[커버핏]");
        	reTitle =reTitle.replace("[KOKIRI]","[커버핏]");
        	reTitle =reTitle.replace("[SIMPSONS]","[커버핏]");
        	reTitle =reTitle.replace("[TM]","[커버핏]");
           	reTitle =reTitle.replace("[Rilakkuma]","[커버핏]");
           	reTitle =reTitle.replace("[Wallace&Gromit]","[커버핏]");
                   	
        	List<WebElement> allSelects1 = driver.findElements(By.cssSelector("#contents > div.xans-element-.xans-product.xans-product-detail > div.detailArea > div.infoArea > table > tbody > tr > td > select"));
        	
        	StringBuilder selectBoxEnabled = new StringBuilder();
            for(int k=1;k<=allSelects1.size();k++) {
            	selectBoxEnabled.append("F|");
            }
            
        	if(selectBoxEnabled.toString().equals("F|")||selectBoxEnabled.toString().equals("F|F|")) {
        		
	    		Coupang coupang=new Coupang();
	    		coupang.setSellerProductName(reTitle);
	        	coupang.setVendorId("A00962060");
	        	coupang.setDisplayProductName(reTitle);
	        	coupang.setSaleStartedAt("2024-01-19T00:00:00");
	        	coupang.setSaleEndedAt("2099-01-19T00:00:00");
	        	coupang.setDeliveryMethod("SEQUENCIAL");
	        	coupang.setDeliveryCompanyCode("EPOST");
	        	coupang.setDeliveryChargeType("NOT_FREE");
	        	coupang.setDeliveryCharge(3000);
	        	coupang.setFreeShipOverAmount(30000);
	        	coupang.setDeliveryChargeOnReturn(3000);
	        	coupang.setRemoteAreaDeliverable("N");
	        	coupang.setUnionDeliveryType("NOT_UNION_DELIVERY");
	        	coupang.setReturnCenterCode("1001694769");
	        	coupang.setReturnChargeName("경기도 안산시 상록구 충장로 565 111동1203호");
	        	coupang.setCompanyContactNumber("010-8900-6085");
	        	coupang.setReturnZipCode("15287");
	        	coupang.setReturnAddress("경기도 안산시 상록구 충장로 565");
	        	coupang.setReturnAddressDetail("111동1203호");
	        	coupang.setReturnCharge(3000);
	        	coupang.setOutboundShippingPlaceCode(19389011);
	        	coupang.setVendorUserId("qudtn0295");
	        	coupang.setRequested(true); 

        		coupangRepository.save(coupang);
	    		optionLists(allSelects1, 0, driver, "",coupang,reInPrice,sb.toString(),"https://qudtn0295.cafe24.com/web/product/big/thmb70/"+fileName+".png",selectBoxEnabled.toString());
        	}else if(selectBoxEnabled.toString().isEmpty()) {
        		//단일 상품
        		List<CoupangAttributes> attributes = new ArrayList<>();
        		List<CoupangItems> items = new ArrayList<>();
        		List<CoupangImages> images = new ArrayList<>();
        		List<CoupangContents> contents = new ArrayList<>();
        		List<CoupangDetails> details =new ArrayList<>();
        		
        		Coupang coupang=new Coupang();
	    		coupang.setSellerProductName(reTitle);
	        	coupang.setVendorId("A00962060");
	        	coupang.setDisplayProductName(reTitle);
	        	coupang.setSaleStartedAt("2024-01-19T00:00:00");
	        	coupang.setSaleEndedAt("2099-01-19T00:00:00");
	        	coupang.setDeliveryMethod("SEQUENCIAL");
	        	coupang.setDeliveryCompanyCode("EPOST");
	        	coupang.setDeliveryChargeType("NOT_FREE");
	        	coupang.setDeliveryCharge(3000);
	        	coupang.setFreeShipOverAmount(30000);
	        	coupang.setDeliveryChargeOnReturn(3000);
	        	coupang.setRemoteAreaDeliverable("N");
	        	coupang.setUnionDeliveryType("NOT_UNION_DELIVERY");
	        	coupang.setReturnCenterCode("1001694769");
	        	coupang.setReturnChargeName("경기도 안산시 상록구 충장로 565 111동1203호");
	        	coupang.setCompanyContactNumber("010-8900-6085");
	        	coupang.setReturnZipCode("15287");
	        	coupang.setReturnAddress("경기도 안산시 상록구 충장로 565");
	        	coupang.setReturnAddressDetail("111동1203호");
	        	coupang.setReturnCharge(3000);
	        	coupang.setOutboundShippingPlaceCode(19389011);
	        	coupang.setVendorUserId("qudtn0295");
	        	coupang.setRequested(true); 

	        	CoupangItems coupangItems = new CoupangItems();
	        	coupangItems.setCoupang(coupang);
                coupangItems.setItemName(reTitle);
                coupangItems.setMaximumBuyCount(10);
                coupangItems.setMaximumBuyForPerson(0);
                coupangItems.setMaximumBuyForPersonPeriod(1);
                coupangItems.setOutboundShippingTimeDay(2);
                coupangItems.setUnitCount(0);
                coupangItems.setAdultOnly("EVERYONE");
                coupangItems.setTaxType("TAX");
                coupangItems.setParallelImported("NOT_PARALLEL_IMPORTED");
                coupangItems.setOverseasPurchased("NOT_OVERSEAS_PURCHASED");
                coupangItems.setPccNeeded(false);
                coupangItems.setOriginalPrice(reInPrice);
                coupangItems.setSalePrice(reInPrice);
                items.add(coupangItems);

                CoupangImages coupangImages =new CoupangImages(); 
                coupangImages.setImageOrder(0);
                coupangImages.setImageType("REPRESENTATION");
                coupangImages.setVendorPath("https://qudtn0295.cafe24.com/web/product/big/thmb70/"+fileName+".png");
                coupangImages.setCoupangItems(coupangItems);
                images.add(coupangImages);

                CoupangContents coupangContents = new CoupangContents();
                coupangContents.setContentsType("TEXT");
                coupangContents.setCoupangItems(coupangItems);
                contents.add(coupangContents);

                CoupangDetails coupangDetails = new CoupangDetails();
                coupangDetails.setContent(sb.toString());
                coupangDetails.setDetailType("TEXT");
                coupangDetails.setCoupangContents(coupangContents);
                coupangContents.getContentDetails().add(coupangDetails);
                details.add(coupangDetails);
                coupangRepository.save(coupang);
                coupangItemsRepository.saveAll(items);
        	    coupangAttributesRepository.saveAll(attributes);
        	    coupangImagesRepository.saveAll(images);
        	    coupangContentsRepository.saveAll(contents);
        	    coupangDetailsRepository.saveAll(details);
        	}
    	}
        
	}
	
	private String optionLists(List<WebElement> allSelects, int index, WebDriver driver, String combinedOption, Coupang coupang, int reInPrice, String detailImages, String thmb, String selectBox) throws InterruptedException {
	    StringBuilder resultBuilder = new StringBuilder();
	    List<CoupangAttributes> attributes = new ArrayList<>();
		List<CoupangItems> items = new ArrayList<>();
		List<CoupangImages> images = new ArrayList<>();
		List<CoupangContents> contents = new ArrayList<>();
		List<CoupangDetails> details =new ArrayList<>();
		
	    if (index >= allSelects.size()) {
	        return combinedOption + "\n";
	    }

	    Select select = new Select(allSelects.get(index));
	    select.selectByIndex(2);
	    List<WebElement> options = select.getOptions();
	    options.remove(0);
	    for (WebElement option : options) {
	        if (!option.getText().equals("-------------------")) {
	            String optionValue = option.getText().trim();
	            String newCombinedOption = combinedOption.isEmpty() ? optionValue : combinedOption + " " + optionValue;
	            
	            CoupangItems coupangItems = new CoupangItems();
	            if(!newCombinedOption.contains("품절")&&!newCombinedOption.contains("단종")) {
		            if (selectBox.equals("F|") || (selectBox.equals("F|F|") && !combinedOption.isEmpty())) {
		            	
		            	coupangItems.setCoupang(coupang);
		                coupangItems.setItemName(newCombinedOption);
		                coupangItems.setMaximumBuyCount(10);
		                coupangItems.setMaximumBuyForPerson(0);
		                coupangItems.setMaximumBuyForPersonPeriod(1);
		                coupangItems.setOutboundShippingTimeDay(2);
		                coupangItems.setUnitCount(0);
		                coupangItems.setAdultOnly("EVERYONE");
		                coupangItems.setTaxType("TAX");
		                coupangItems.setParallelImported("NOT_PARALLEL_IMPORTED");
		                coupangItems.setOverseasPurchased("NOT_OVERSEAS_PURCHASED");
		                coupangItems.setPccNeeded(false);
		                coupangItems.setOriginalPrice(reInPrice);
		                coupangItems.setSalePrice(reInPrice);
		                items.add(coupangItems);
			        	
		                CoupangImages coupangImages = new CoupangImages();
		                coupangImages.setImageOrder(0);
		                coupangImages.setImageType("REPRESENTATION");
		                coupangImages.setVendorPath(thmb);
		                coupangImages.setCoupangItems(coupangItems);
		                images.add(coupangImages);
	
		                CoupangContents coupnagContents = new CoupangContents();
		                coupnagContents.setContentsType("TEXT");
		                coupnagContents.setCoupangItems(coupangItems);
		                contents.add(coupnagContents);
	
		                CoupangDetails coupangDetails = new CoupangDetails();
		                coupangDetails.setContent(detailImages);
		                coupangDetails.setDetailType("TEXT");
		                coupangDetails.setCoupangContents(coupnagContents);
		                coupnagContents.getContentDetails().add(coupangDetails);
		                details.add(coupangDetails);
		            }
		            
		            if (selectBox.equals("F|")) {
		            	if(attributes.size()<200) {
			                CoupangAttributes coupangAttributes = new CoupangAttributes();
			                coupangAttributes.setAttributeTypeName("옵션");
			                coupangAttributes.setAttributeValueName(option.getText());
			                coupangAttributes.setCoupangItems(coupangItems);
			                attributes.add(coupangAttributes);
		            	}
		            } else if (selectBox.equals("F|F|") && !combinedOption.isEmpty()) {
			            	CoupangAttributes coupangAttributes = new CoupangAttributes();
			                coupangAttributes.setAttributeTypeName("옵션");
			                coupangAttributes.setAttributeValueName(option.getText());
			                coupangAttributes.setCoupangItems(coupangItems);
			                
			                CoupangAttributes coupangAttributes2 = new CoupangAttributes();
			                coupangAttributes2.setAttributeTypeName("옵션2");
			                coupangAttributes2.setAttributeValueName(combinedOption);
			                coupangAttributes2.setCoupangItems(coupangItems);
			                
			                attributes.add(coupangAttributes);
			                attributes.add(coupangAttributes2);
	            }
	            resultBuilder.append(optionLists(allSelects, index + 1, driver, newCombinedOption, coupang, reInPrice, detailImages, thmb, selectBox));
	            }
	        }
	    }
	    coupangItemsRepository.saveAll(items);
	    coupangAttributesRepository.saveAll(attributes);
	    coupangImagesRepository.saveAll(images);
	    coupangContentsRepository.saveAll(contents);
	    coupangDetailsRepository.saveAll(details);
	    return resultBuilder.toString();
	}
	
	public static void saveDetailImage(String imageUrl, String savePath, File afterFile) throws MalformedURLException {
		URL url;
		//jpeg,png,bmp,wbmp,gif 만 지원을함. webp 지원안함 ㅡㅡ 다른 라이브러리 찾아라  개같음
		BufferedImage bi = null;

		try {
			url = new URL(imageUrl); // 다운로드 할 이미지 URL
			bi = ImageIO.read(url);
			if(bi!=null) {
				Image resizeImage = bi.getScaledInstance(bi.getWidth(), bi.getHeight(), Image.SCALE_SMOOTH);
				BufferedImage newImage = new BufferedImage(bi.getWidth(), bi.getHeight(), BufferedImage.TYPE_INT_RGB);
	            Graphics g = newImage.getGraphics();
	        	g.drawImage(resizeImage, 0, 0, null);
	            g.dispose();
				ImageIO.write(newImage, "jpeg", afterFile);
			}
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static void saveThumbImage(String imageUrl, String savePath, File afterFile) throws MalformedURLException {
		URL url;
		//jpeg,png,bmp,wbmp,gif 만 지원을함. webp 지원안함 
		BufferedImage bi = null;
		try {
			url = new URL(imageUrl); // 다운로드 할 이미지 URL
			bi = ImageIO.read(url);
			ImageIO.write(bi, "png", afterFile);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
}
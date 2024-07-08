package com.shop;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
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

import javax.imageio.ImageIO;

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
import com.shop.entity.CoupangAttributesRepository;
import com.shop.entity.CoupangContentsRepository;
import com.shop.entity.CoupangDetailsRepository;
import com.shop.entity.CoupangImagesRepository;
import com.shop.entity.CoupangItemsRepository;
import com.shop.entity.CoupangRepository;

@SpringBootTest
class ShopApplicationTests {
    
	@Test
	//test 코드에서 transactional 사용 하지 마라
	public void asd() {
	}
	
	
}
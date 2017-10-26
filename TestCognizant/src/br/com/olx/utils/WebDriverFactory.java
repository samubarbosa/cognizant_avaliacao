package br.com.olx.utils;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class WebDriverFactory {
	
	
	public static WebDriver getInstance() {
		System.setProperty("webdriver.chrome.driver", "lib/chromedriver"); // se windows substituir por lib/chromedriver.exe
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;

	}

}

package com.auto1.sample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MyUtils {
	
	Logger logger = LoggerFactory.getLogger(MyUtils.class);

	public WebDriver getChromeDriver(Properties props) {
		// TODO Auto-generated method stub
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("download.default_directory", props.get("shared.dir"));
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		//options.addArguments("--headless");
		
         
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver = new ChromeDriver(options);
		
		//System.setProperty("webdriver.chrome.driver", props.getProperty("chrome.driver")); 
		//WebDriver driver = new  ChromeDriver();

		/*
		 * ChromeOptions options = new ChromeOptions();
		 * options.addArguments("--headless"); String downloadFilepath = "./";
		 * HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		 * chromePrefs.put("profile.default_content_settings.popups", 0);
		 * chromePrefs.put("download.default_directory", downloadFilepath); //options =
		 * new ChromeOptions(); options.setExperimentalOption("prefs", chromePrefs);
		 * DesiredCapabilities cap = DesiredCapabilities.chrome();
		 * cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		 * cap.setCapability(ChromeOptions.CAPABILITY, options); WebDriver driver = new
		 * ChromeDriver(cap);
		 */

		return driver;
	}

	public boolean clickableElementID(String eleId, WebDriver driver) {
		boolean status =false;
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.id(eleId)));
			driver.findElement(By.id(eleId)).click();
		} catch (Exception e) {
			System.out.println("Could not handle "+eleId);
			e.printStackTrace();
		}
		return status;
	}

	public boolean clickableElementXPath(String elePath, WebDriver driver) {
		boolean status =false;
		try {
			new WebDriverWait(driver, 10).until(ExpectedConditions.elementToBeClickable(By.xpath(elePath)));
			driver.findElement(By.xpath(elePath)).click();
		} catch (Exception e) {
			System.out.println("Could not handle "+elePath);
			e.printStackTrace();
		}
		return status;
	}
	
	public boolean selectOptionXPathAndIndex(String elePath,int index, WebDriver driver) {
		boolean status =false;
		try {
			new WebDriverWait(driver, 15).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elePath)));
			Select operatorDropdown = new Select(driver.findElement(By.xpath(elePath)));
			operatorDropdown.selectByIndex(index);
		} catch (Exception e) {
			System.out.println("Could not set "+index+" on "+elePath);
			e.printStackTrace();
		}
		return status;
	}
	public boolean selectOptionXPathAndText(String elePath,String optionName, WebDriver driver) {
		boolean status =false;
		try {
			new WebDriverWait(driver, 150).until(ExpectedConditions.presenceOfElementLocated(By.xpath(elePath)));
			Select clsDropdown = new Select(driver.findElement(By.xpath(elePath)));
			clsDropdown.selectByVisibleText(optionName);
		} catch (Exception e) {
			System.out.println("Could not set "+optionName+" on "+elePath);
			e.printStackTrace();
		}
		return status;
	}

	public void printRow(String out, String outFile) {

		// TODO Auto-generated method stub
		BufferedWriter bw = null;
		FileOutputStream fileStream = null;
		OutputStreamWriter writer = null;
		try {
			File file = new File(outFile);
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			fileStream = new FileOutputStream(new File(outFile), true);
			writer = new OutputStreamWriter(fileStream, "utf-8");
			bw = new BufferedWriter(writer);
			bw.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)
					bw.close();
				if (writer != null)
					writer.close();
				if (fileStream != null)
					fileStream.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}

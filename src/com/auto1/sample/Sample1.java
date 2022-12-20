package com.auto1.sample;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;	

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Sample1 {

	public static final Logger LOG = LoggerFactory.getLogger(Sample1.class);
	static Properties props = new Properties();
	private static String outFile ;
	private static MyUtils mUtils = new MyUtils();
	private static String mainWindow;
	private static String agileWindow;
	private static int iter=0;
	
	public static void doAuto1() throws Exception {
		long begin = System.currentTimeMillis();
		props.load(new FileInputStream("auto1.properties"));
		if (props.isEmpty()) {
			LOG.info("properties file not loaded");
			System.exit(1);
		}

		WebDriver driver = mUtils.getChromeDriver(props);
		driver.get("https://www.google.com");
		
		//driver.quit();
		long end = System.currentTimeMillis();
		LOG.info("Total time consumed in secs is - " + (end - begin) / 1000);
		System.out.println("Total time consumed in secs is - " + (end - begin) / 1000);
	}


}

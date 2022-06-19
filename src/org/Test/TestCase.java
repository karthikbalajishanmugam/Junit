package org.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestCase {
static WebDriver driver ;
	
	@BeforeClass
	public static void Lanch() throws InterruptedException {
		System.out.println("LanchBrowser");
	
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\balas\\eclipse-workspace\\org.Test\\driver\\chromedriver.exe");
	driver = new ChromeDriver();
	driver.get("https://www.flipkart.com/");
	driver.manage().window().maximize();
	Thread.sleep(3000);
	}
	
	@AfterClass
	public static void quit() {
	driver.quit();
	
	}
	
	long StartTime;
	
	@Before
	public void Beforemethod() {
		System.out.println("Beforemethod");
		StartTime =System.currentTimeMillis();
		
	}
	
	@After 
	public void Aftermethod() {
		System.out.println("Aftermethod");
		long EndTime =System.currentTimeMillis();
		System.out.println("TimeTaken:"+ + (EndTime - StartTime));
	}
	
	
	
	
	
	@Test
	public void method1() throws InterruptedException {
		System.out.println("login");
		driver.findElement(By.xpath("(//input[@autocomplete = 'off'])[2]")).sendKeys("9443822714");
		driver.findElement(By.xpath("(//input[@autocomplete = 'off'])[3]")).sendKeys("bala@1704");
		driver.findElement(By.xpath("(//button[@type= 'submit'])[2]")).click();
		Thread.sleep(3000);
	}
	
	@Test
	
	public void method2() throws InterruptedException, IOException {
		System.out.println("search");
		driver.findElement(By.name("q")).sendKeys("realme");
		driver.findElement(By.xpath("//button[@type= 'submit']")).click();
		Thread.sleep(3000);
		
		List<WebElement> mobiles = driver.findElements(By.xpath("//div[@class='_4rR01T']"));
		File f = new File("C:\\Users\\balas\\eclipse-workspace\\org.Test\\xl file\\tv list.xlsx");
	    FileOutputStream f1 = new FileOutputStream (f);
	    HSSFWorkbook w = new HSSFWorkbook();
	    HSSFSheet sheet = w.createSheet("all mobiles");
	    
	  
			
			for (int i=0; i< mobiles.size();i++) {
				
				  WebElement allmobile = mobiles.get(i);
					 String allnames = allmobile.getText();
					 System.out.println(allnames); 
					 
				HSSFRow row = sheet.createRow(i);
			    HSSFCell cell= row.createCell(0); 
			    cell.setCellValue(allnames );
			}
			Thread.sleep(3000);
		
	
	    
		WebElement select = driver.findElement(By.xpath("(//div[@class='_4rR01T'])[4]"));
		select.click();
		Thread.sleep(3000);
		
		String s = select.getText();
		System.out.println(s);
String prwnd = driver.getWindowHandle();
		
		Set<String> allwin = driver.getWindowHandles();
		for(String x :allwin ) {
			if(!x.equals(prwnd)){
				driver.switchTo().window(x);
				
			}
		}
		
		Thread.sleep(3000);
		TakesScreenshot tk = (TakesScreenshot)driver;
		File source = tk.getScreenshotAs(OutputType.FILE);
		File trg = new File("C:\\Users\\balas\\eclipse-workspace\\org.Test\\screenshote\\mobile.png");
		FileUtils.copyFile(source,trg);
		
		
		HSSFSheet sheet2 = w.createSheet("selected mobile");
		HSSFRow row = sheet2.createRow(0);
	    HSSFCell cell= row.createCell(0); 
	    cell.setCellValue(s);
		
	    
	    FileInputStream f2 = new  FileInputStream (f);
	    HSSFWorkbook w1 = new HSSFWorkbook(f2);
	    HSSFSheet Sheet2 = w1.getSheet("selected mobile");
	    HSSFRow Row2= Sheet2.getRow(0);
	    HSSFCell cell2=Row2.getCell(0);
	    
	    String value = cell2.getStringCellValue();
	    System.out.println(value);
	    
	    w.write(f1);
	    w.close();
	    w1.close();
	    
	    if (s.equals(value)) {
	    	System.out.println("pass");
	    }else {
	    	System.out.println("fail");
	    }
		
		}

}

package FrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class MethodLibrary {

	public static WebDriver driver;
	public static String result;

	public static void controller(String methodName, String param1, String param2, String param3, String param4) 
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{

		Method methods = MethodLibrary.class.getMethod(methodName, String.class,String.class, String.class, String.class);

		methods.invoke(methodName, param1, param2, param3, param4);
	}

	public static void OpenBrowser(String browser, String url, String param3, String param4) throws  Exception{

		try{
			if (browser.equalsIgnoreCase("chrome")){
				System.setProperty("webdriver.chrome.driver", "/Users/AnmolParida/Softwares/Selenium/chromedriver");
				driver = new ChromeDriver();
				driver.get(url);
			}	
			result = "Pass";
			System.out.println(result + ": OpenBrowser");
		}
		catch(Exception e){
			result = "Fail";
			System.out.println(result + ": OpenBrowser");
			e.printStackTrace();
		}
	}

	public static void EnterText(String fieldXpath, String valueToPass, String param3, String param4) throws  Exception{

		try{
			driver.findElement(By.xpath(fieldXpath)).sendKeys(valueToPass);
			result = "Pass";
			System.out.println(result + ": EnterText");
		}
		catch(Exception e){
			result = "Fail";
			System.out.println(result + ": EnterText");
			e.printStackTrace();
		}
	}

	public static void ClickButton(String fieldXpath, String val, String param3, String param4) throws  Exception{
		try{
			driver.findElement(By.xpath(fieldXpath)).click();
			result = "Pass";
			System.out.println(result + ": ClickButton");
		}
		catch(Exception e){
			result = "Fail";
			System.out.println(result + ": ClickButton");
			e.printStackTrace();
		}
	}

	public static void ClickRadio(String fieldXpath, String val, String param3, String param4) throws  Exception{
		try{
			driver.findElement(By.xpath(fieldXpath)).click();
			result = "Pass";
			System.out.println(result + ": ClickRadio");
		}
		catch(Exception e){
			result = "Fail";
			System.out.println(result + ": ClickRadio");
			takeScreeshot();
			//e.printStackTrace();
		}
	}

	public static void SelectDropdown(String fieldXpath, String val, String param3, String param4) throws  Exception{
		try{
			if (val.contains("|")){
				String[] arr = val.split("|");
				for (int i = 0 ; i < arr.length; i ++){
					Select oSelect = new Select(driver.findElement(By.xpath(fieldXpath)));
					oSelect.selectByVisibleText(arr[i]);
				}
			}
			else {
				driver.findElement(By.xpath(fieldXpath)).click();
			}
			result = "Pass";
			System.out.println(result + ": SelectDropdown");
		}
		catch(Exception e){
			result = "Fail";
			System.out.println(result + ": SelectDropdown");
			takeScreeshot();
			//e.printStackTrace();
		}
	}

	public static void takeScreeshot() throws IOException{

		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); // gets stored into a source file, then convert it to .jpeg or.png

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_mm_yyyy_hh_m_ss");
		String formattedDate = sdf.format(date);

		FileUtils.copyFile(src, new File("/Users/AnmolParida/Eclipse_Java/TestData/" + "Fail_" + formattedDate +".png"));

	}
}

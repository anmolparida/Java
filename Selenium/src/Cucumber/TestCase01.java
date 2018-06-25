package Cucumber;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import cucumber.api.java.en.Given;

public class TestCase01 {

	WebDriver driver ;

	@Given ("the amazon website is open in chrome")
	public void environmentSetup(){

		System.setProperty("webdriver.chrome.driver", "/Users/AnmolParida/Softwares/Selenium/chromedriver");
		driver =  new ChromeDriver();
		driver.get("https://www.amazon.in/");
		System.out.println("the amazon website is open in chrome");
	}

	@Given ("the wondow is maximized")
	public void maximizeBrowserWindow(){

		driver.manage().window().maximize();
		System.out.println("the wondow is maximized");
	}

	@Given ("user enters laptops in serach box")
	public void searchBox(){
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("laptops");
		System.out.println("When user enters laptops in serach box");
	}

	@Given ("hits the search button")
	public void clickSearchButton(){
		driver.findElement(By.xpath("//input[@type='submit' and @value='Go']")).click();
		System.out.println("hits the search button");
	}
	@Given ("list of laptops of pop up")
	public void showResult(){
		System.out.println("Info: Then list of laptops of pop up");
	}

	@Given ("how many lenovo laptops come in search")
	public void resultCount(){

		List<WebElement> lenovo = driver.findElements(By.xpath("//h2[@data-attribute=.]"));
		
		int lenovo_count = 0;
		//System.out.println("No of Reults: " + listBook_Price.size());
		for (int i = 0; i < lenovo.size(); i++){
			
			System.out.println("Result " + (i+1) +" : " + lenovo.get(i).getAttribute("data-attribute"));
			
			String laptop_model = lenovo.get(i).getAttribute("data-attribute");
			if (laptop_model.toLowerCase().contains("lenovo")){
				lenovo_count ++ ;
			}
		}
		System.out.println("how many lenovo laptops came in search : " + lenovo_count );
	}

}

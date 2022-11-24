package cart;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AddingOnCar {
	SoftAssert softassert = new SoftAssert();

	WebDriver driver;
	public int numberOfTry = 5;
	//public int item_in_inventory = 4;

	@BeforeTest()
	public void beforeTest() {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://smartbuy-me.com/smartbuystore/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.findElement(By.xpath("/html/body/main/header/div[2]/div/div[2]/a")).click();

	}

	@Test()
	public void Check_The_Price_Of_The_Item() {

		for (int i = 0; i < numberOfTry; i++) {
			driver.findElement(By.xpath(
					"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[3]/div[1]/div/div/form[1]/div[1]/button"))
					.click();
			driver.findElement(By.xpath("//*[@id=\"addToCartLayer\"]/a[2]")).click();

		}
		String The_price_Of_One_Item = driver
				.findElement(By.xpath(
						"//*[@id=\"newtab-Featured\"]/div/div[1]/div/div/div/div[3]/div/div[2]/div[2]/div/div/span[3]"))
				.getText();
		String[] the_New_Price = The_price_Of_One_Item.split("JOD");
		String final_Price = the_New_Price[0].trim();
		String updated_price = final_Price.replace(",", ".");
		double final_Price_item = Double.parseDouble(updated_price);

		softassert.assertEquals(final_Price_item * numberOfTry, 5,796);
		
	}
	}



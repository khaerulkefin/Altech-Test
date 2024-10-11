package Checkout;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

public class invalidCheckout {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String userName = "standard_user";
		String password = "secret_sauce";

		EdgeDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Open Browser
		driver.get("https://www.saucedemo.com/");

		// Login
		driver.findElement(By.cssSelector("input[name='user-name']")).sendKeys(userName);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
		driver.findElement(By.cssSelector(".submit-button")).click();

		// Check cart without item
		driver.findElement(By.cssSelector(".shopping_cart_link")).click();

		// Checkout without item
		driver.findElement(By.cssSelector(".checkout_button")).click();

		// Verify
		// Url seharusnya tidak berubah
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/cart.html");

		// Saya asumsikan ada error message
		driver.findElement(By.cssSelector(".error-msg")).getText();

	}

}

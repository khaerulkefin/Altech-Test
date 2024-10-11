package Checkout;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;

public class validCheckout {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String userName = "standard_user";
		String password = "secret_sauce";

		// Select SIngle or Multiple item
		String[] selected = { "Sauce Labs Bike Light", "Sauce Labs Backpack" };

		EdgeDriver driver = new EdgeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		// Open Browser
		driver.get("https://www.saucedemo.com/");

		// Login
		driver.findElement(By.cssSelector("input[name='user-name']")).sendKeys(userName);
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys(password);
		driver.findElement(By.cssSelector(".submit-button")).click();

		// Get list item
		List<WebElement> itemList = driver.findElements(By.cssSelector(".inventory_item_name"));

		for (int i = 0; i < itemList.size(); i++) {
			String item = itemList.get(i).getText();

			// Add to cart selected item
			List<String> selectedItem = Arrays.asList(selected);

			if (selectedItem.contains(item)) {
				driver.findElement(
						By.xpath("/html/body/div/div/div/div[2]/div/div/div/div[" + (i + 1) + "]/div[2]/div[2]/button"))
						.click();
			}
		}

		driver.findElement(By.cssSelector(".shopping_cart_link")).click();

		// Verify item
		List<WebElement> cart = driver.findElements(By.cssSelector(".inventory_item_name"));

		for (int j = 0; j < cart.size(); j++) {
			String verifyCart = cart.get(j).getText();

			// Sorting for selected item
			Arrays.sort(selected);
			Assert.assertEquals(verifyCart, selected[j]);
		}

		driver.findElement(By.cssSelector(".checkout_button")).click();

		// Data information
		driver.findElement(By.cssSelector("#first-name")).sendKeys("Erul");
		driver.findElement(By.cssSelector("#last-name")).sendKeys("Test");
		driver.findElement(By.cssSelector("#postal-code")).sendKeys("54321");
		driver.findElement(By.cssSelector("#continue")).click();

		// Done checkout
		driver.findElement(By.cssSelector("#finish")).click();
	}

}

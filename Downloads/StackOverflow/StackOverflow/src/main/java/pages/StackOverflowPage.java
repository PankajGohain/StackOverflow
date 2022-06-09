package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class StackOverflowPage {
	private WebDriver driver;
	int largest = 0;
	private By MenuBtn = By.xpath("//a[@role='menuitem']");
	private By tags = By.xpath("//div[contains(text(),'Tags')]");
	private By name = By.xpath("//a[contains(text(),'Name')]");
	private By noOfQuestions = By.xpath("//div[contains(text(),'questions')]");
	private By pageHeader = By.cssSelector(".fs-headline1.mb16");

	public StackOverflowPage(WebDriver driver) {
		this.driver = driver;
	}

	public void clickOnHamburgerMenu() {
		driver.findElement(MenuBtn).click();
	}

	public void selectTag() {
		driver.findElement(tags).click();

	}

	public String verifyUserOnTagsPage() {
		return driver.findElement(pageHeader).getText();
	}

	public void clickNameTab() {
		driver.findElement(name).click();
	}

	public int getMaxQuestionCount() {
		List<WebElement> questionsNum = driver.findElements(noOfQuestions);
		for (WebElement e : questionsNum) {
			int num = Integer.parseInt(e.getText().replace("questions", "").trim());
			if (largest < num) {
				largest = num;
			}

		}
		return largest;
	}

	public String getTagwithMaxQuestionCount() {
		int count = getMaxQuestionCount();

		String tag = driver.findElement(By.xpath(
				"//div[normalize-space()='" + count + " questions']//parent::div//parent::div//a[@class='post-tag']"))
				.getText();

		return tag;

	}

}

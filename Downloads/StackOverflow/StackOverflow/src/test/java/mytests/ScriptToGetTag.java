package mytests;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import ConfigReader.ConfigPropReader;
import Factory.DriverFactory;
import pages.StackOverflowPage;

public class ScriptToGetTag {

	DriverFactory df;
	ConfigPropReader cp;
	Properties prop;
	WebDriver driver;
	StackOverflowPage homePage;

	@BeforeTest
	public void setup() throws IOException {
		cp = new ConfigPropReader();
		prop = cp.initProp();
		df = new DriverFactory();
		driver = df.initDriver(prop);
		homePage = new StackOverflowPage(driver);
	}

	@Test
	public void getHighestTagValue() {
		homePage.clickOnHamburgerMenu();
		homePage.selectTag();
		Assert.assertEquals(homePage.verifyUserOnTagsPage(), prop.get("header"), "Page header doesn't match");
		homePage.clickNameTab();
		String tag = homePage.getTagwithMaxQuestionCount();
		System.out.println("Highest Tag is :" + tag);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}

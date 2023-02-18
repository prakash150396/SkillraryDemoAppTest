package genericlibraries;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import Pompages.ContactUsPage;
import Pompages.CoreJavaForSelenium;
import Pompages.CoreJavaVideoPage;
import Pompages.HomePage;
import Pompages.SeleniumTrainingPage;
import Pompages.SkillraryDemoAppPage;
import Pompages.TestingPage;

public class BaseClass {

	 private static final WebDriver driver = null;
	protected PropertiesFileUtility property;
	 protected Excelutility excel;
	 protected WebDriverUtility web;
	 protected HomePage home;
	 protected SkillraryDemoAppPage skillraryDemo;
	 protected SeleniumTrainingPage selenium;
	 protected TestingPage testing;
	 protected CoreJavaForSelenium coreJava;
	 protected CoreJavaVideoPage javaVideo;
	 protected ContactUsPage contact;
	 protected long time;
	//@BeforeSuite
	//@BeforeTest
	@BeforeClass
	public void classConfiguration() {
		PropertiesFileUtility property = new PropertiesFileUtility();
		Excelutility excel = new Excelutility();
		WebDriverUtility web = new WebDriverUtility();
		
		
		property.propertyFileInitialization(IConstantPath.PROPERTIES_FILE_PATH);
		excel.excelInitialization(IConstantPath.EXCEL_FILE_PATH);
		
	}
	@BeforeMethod
	public void methodConfiguration() {
		time = Long.parseLong(property.fetchproperty("timeouts"));
		web.openApplications(property.fetchproperty("browser"), property.fetchproperty("url"), 0);
		
		home = new HomePage(driver);
		
		Assert.assertTrue(home.getLogo().isDisplayed());
		
		skillraryDemo = new SkillraryDemoAppPage(driver);
		selenium = new SeleniumTrainingPage(driver);
		coreJava = new CoreJavaForSelenium(driver);
		javaVideo = new CoreJavaVideoPage(driver);
		testing = new TestingPage(driver);
		contact = new ContactUsPage(driver);
	}
	
	@AfterMethod
	public void methodTearDown() {
		web.quitBrowser();
	}
	@AfterClass
	public void classTearDown() {
		excel.closeExcel();
	}
	//@AfterTest
	//@AfterSuite
}

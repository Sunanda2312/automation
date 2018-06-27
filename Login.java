package automation;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Login {
	
WebDriver driver; 
	
	@BeforeClass
	public void Instantitator() {
		System.setProperty("webdriver.chrome.driver","C:\\Users\\sunanda1\\Downloads\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
	}
	
	
	@Test
	public void Test001_LaunchGmail() {		
		driver.get("https://accounts.google.com/signin/v2/sl/pwd?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");
		
	}
	
	@Test
	public void Test002_Email() throws InterruptedException {
		
		driver.findElement(By.cssSelector("input#identifierId")).sendKeys("ssanera@gmail.com");
		driver.findElement(By.id("identifierNext")).click();
		Thread.sleep(1000);
		String screenName = driver.findElement(By.id("headingText")).getText();
		Assert.assertEquals(screenName,"Welcome");
		//Assert.assertTrue(driver.findElement(By.cssSelector("alert-error")).getText().contains("Couldn't find your Google Account"));
	}
	
	@Test
	public void Test003_Password() throws InterruptedException {
		driver.findElement(By.cssSelector("form>div>div>div>div>div>input")).sendKeys("3503sanera");
		driver.findElement(By.cssSelector("div.qhFLie>div")).click();
		Thread.sleep(1000);
		String expectedUrl = "https://mail.google.com/mail/u/0/#inbox"; 
        Assert.assertEquals(expectedUrl, driver.getCurrentUrl(), "Didn't navigate to correct webpage");		
	}
	
	@Test
	public void Test004_Compose() throws InterruptedException {
		driver.findElement(By.cssSelector("div.z0>div")).click();
		Thread.sleep(500);
		String compose = driver.findElement(By.cssSelector("table.cf.Ht>tbody tr:nth-child(1)>td>div>h2 div:nth-child(2)")).getText();
		Assert.assertEquals(compose,"New Message");		
	}
	
	@Test
	public void Test005_MailTo() throws InterruptedException {
		driver.findElement(By.cssSelector("textarea[name=\"to\"]")).sendKeys("sunanda523@gmail.com");
		driver.findElement(By.cssSelector("input.aoT[name=\"subjectbox\"]")).sendKeys("Testing");
		driver.findElement(By.cssSelector("div.Am.Al.editable.LW-avf")).sendKeys("Gmail Testing is working.");
		driver.findElement(By.cssSelector("td.gU.Up>div div:nth-child(2)")).click();
		Thread.sleep(1000);
		try{
			String emailSuccess = driver.findElement(By.cssSelector("table.cf.Ht>tbody tr:nth-child(1)>td>div>h2 div:nth-child(2)")).getText();
		}catch(NoSuchElementException e){
		Assert.assertTrue(true);		
		}
		}
	
	
	@Test
	public void Test006_Account() {
		driver.findElement(By.xpath("//*[@id=\"gb\"]/div[1]/div[1]/div[2]/div[5]/div[1]/a/span")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String account = driver.findElement(By.linkText("My Account")).getText();
		Assert.assertEquals(account,"My Account");
	}
	
	@Test
	public void Test007_Signout() {
		driver.findElement(By.id("gb_71")).click();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String accountOut = driver.findElement(By.id("profileIdentifier")).getText();
		Assert.assertEquals(accountOut,"ssanera@gmail.com");
		
		
	}

}

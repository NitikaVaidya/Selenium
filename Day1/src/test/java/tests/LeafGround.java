package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class LeafGround {

    WebDriver driver;

    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup(); //check chrome driver version and download chrome driver only when version is updated
        driver = new ChromeDriver();
        driver.manage().window().maximize();

    }

    @Test
    public void testcase1(){

        driver.get("https://www.amazon.in/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobiles");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
        String text = driver.findElement(By.xpath("// span[@class='a-color-state a-text-bold']")).getText();
        /*if(text.equals("\"mobiles\""))
            System.out.println("mobile are displayed");
        else
            System.out.println("mobile are not displayed");
            if else is replaces by assert */

        Assert.assertEquals(text,"\"mobiles\"" );

    }

    @Test
    public void textBox(){
        driver.get("https://leafground.com/input.xhtml");
        driver.findElement(By.xpath("//input[@id=\"j_idt88:name\"]")).sendKeys("test user");
        driver.findElement(By.xpath("//input[@id=\"j_idt88:j_idt91\"]")).sendKeys(" India");
        String disabled = driver.findElement(By.xpath("//input[@id=\"j_idt88:j_idt93\"]")).getAttribute("disabled");
        Assert.assertEquals(disabled,"true");
        driver.findElement(By.xpath("//input[@id=\"j_idt88:j_idt95\"]")).clear();
        String value = driver.findElement(By.xpath("//input[@id=\"j_idt88:j_idt97\"]")).getAttribute("value");

        Assert.assertEquals(value,"My learning is superb so far.");

    }

    @Test
    public void button(){
        driver.get("https://leafground.com/button.xhtml");
        driver.findElement(By.xpath("//button[@name=\"j_idt88:j_idt90\"]")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("https://leafground.com/dashboard.xhtml"));
        driver.get("https://leafground.com/button.xhtml");
        String disabled = driver.findElement(By.xpath("//button[@name=\"j_idt88:j_idt92\"]")).getAttribute("disabled");
        Assert.assertEquals(disabled,"true");

        Point point = driver.findElement(By.xpath("//button[@name=\"j_idt88:j_idt94\"]")).getLocation();
        Assert.assertEquals(point.getX(),81);
        Assert.assertEquals(point.getY(),397);

        Dimension dimension = driver.findElement(By.xpath("//button[@name=\"j_idt88:j_idt98\"]")).getSize();
        Assert.assertEquals(dimension.getWidth(),86);
        Assert.assertEquals(dimension.getHeight(),33);

        List<WebElement> buttons = driver.findElements(By.xpath("(//div[@class=\"card\"])[7]/button"));

        Assert.assertEquals(buttons.size(),4);

    }


    @Test
    public void dropDown() throws InterruptedException{
        driver.get("https://leafground.com/select.xhtml");

        Select dropDown = new Select(driver.findElement(By.xpath("//select[@class=\"ui-selectonemenu\"]")));
        dropDown.selectByIndex(1);
        dropDown.selectByVisibleText("Playwright");

        driver.findElement(By.xpath("//div[@id=\"j_idt87:country\"]")).click();
        driver.findElement(By.xpath("//li[text()='India']")).click();
        Thread.sleep(2000);
        driver.findElement(By.id("j_idt87:city")).click();


        List<WebElement> cities = driver.findElements(By.xpath("//ul[@id=\"j_idt87:city_items\"]/li"));

        List<String> citiesString = Arrays.asList("Bengaluru","Chennai","Delhi");

        for (int i = 1; i < cities.size() ; i++) {
            Assert.assertEquals(cities.get(i).getText(), citiesString.get(i-1));
        }


    }

    @Test
    public void stale(){
        driver.get("https://leafground.com/button.xhtml");
        WebElement element = driver.findElement(By.xpath("//button[@name=\"j_idt88:j_idt94\"]"));
        element.click();
        driver.navigate().refresh();
        element.click();

    }


    @Test
    public void checkBoxTest() throws InterruptedException {
        driver.get("https://leafground.com/checkbox.xhtml");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//input[@name=\"j_idt87:j_idt91_input\"]")).click();
        String info = driver.findElement(By.xpath("//span[@class=\"ui-growl-title\"]")).getText();
        Assert.assertEquals(info,"Checked");

        driver.findElement(By.xpath("//span[@class=\"ui-growl-title\"]")).click();
        Assert.assertEquals(info,"Unchecked");
    }

    public void tearDown(){
        driver.quit();
    }



}

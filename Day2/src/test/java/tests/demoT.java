package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class demoT {

    /*public static void main(String[] args) {
        testcase1();//if test case 1 fail test case 2 will not get executed
        testcase2();
    } that's why we are not using main method*/

    //testNG-library

    WebDriver driver;

    @BeforeTest
    public void setUp() {

        WebDriverManager.chromedriver().setup(); //check chrome driver version and download chrome driver only when version is updated
        driver = new ChromeDriver();
        /*by adding before and after test and test NG both test cases
        will run in same chrome */

    }

    @Test
    public void testcase1() {

        driver.get("https://www.amazon.in/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobiles");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
        String text = driver.findElement(By.xpath("// span[@class='a-color-state a-text-bold']")).getText();
        /*if(text.equals("\"mobiles\""))
            System.out.println("mobile are displayed");
        else
            System.out.println("mobile are not displayed");
            if else is replaces by assert */

        Assert.assertEquals(text, "\"mobiles\"");

    }

    @Test
    public void textBox() {
        driver.get("https://leafground.com/input.xhtml");
        driver.findElement(By.xpath("//input[@id=\"j_idt88:name.\"]")).sendKeys("test user");
        driver.findElement(By.xpath("//input[@id=\"j_idt88:j_idt91\"]"))
                .sendKeys("India");
        String disabled = driver.findElement(By.xpath("//input[@id=\"j_idt88:j_idt93\"]"))
                .getAttribute("disabled");

        Assert.assertEquals(disabled, "true");
        driver.findElement(By.xpath("//input[@id=\"j_idt88:j_idt95\"]")).clear();//clear tag will clear this textbox automatically
        String value = driver.findElement(By.xpath("//input[@id=\"j_idt88:j_idt97\"]")).getAttribute("value");
        Assert.assertEquals(value, "My learning is superb so far.");


    }

    //@AfterTest
    public void tearDown() {

        driver.quit();
    }
}

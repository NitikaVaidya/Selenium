package tests;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class DemoQA {
    public static void main(String[] args) {
        //selenium

        //open browser and navigate to amazon.in
        //enter " mobile" in search bar
        // click on search/enter key
        //mobile should be displayed

        //dont need this line now as add web driver in dependency pom.xml == System.setProperty("webdriver.chrome.driver","C:\\Users\\Nitika Vaidya\\Downloads\\chromedriver_win32.zip");
        //Step 1
        WebDriverManager.chromedriver().setup(); //check chrome driver version and download chrome driver only when version is updated
        ChromeDriver driver = new ChromeDriver();
        //webDriver driver = new FirefoxDriver(); overriding can be done
        driver.get("https://www.amazon.in/");

        //Step 2
        //delete this as mereged id paart in element == By by = By.id("twotabsearchtextbox");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("mobiles");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(Keys.ENTER);
        //element.sendKeys("mobiles");
        //element.sendKeys(Keys.ENTER);

        //step 3
        String text = driver.findElement(By.xpath("// span[@class='a-color-state a-text-bold']")).getText();

        if(text.equals("\"mobiles\""))
            System.out.println("mobile are displayed");
        else
            System.out.println("mobile are not displayed");

        driver.quit();

        //xpath
        //absolute --> /html/body/div[1]/div[2]/span/div/h1/div/div[1]/div/div/span[3]
        //relative --> tagName[@attribute="value] // span[@class="a-color-state a-text-bold"]


    }
}

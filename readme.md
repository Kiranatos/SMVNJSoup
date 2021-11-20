JSoup framework

Demo01HTMLInternetCrawler: crawling on web-page
Demo02HTMLStringCrawler: read elements from html-page
Demo03HTMLEditor: read html page -> adding code -> write updated html-page

https://jsoup.org/apidocs/org/jsoup/select/Selector.html

https://html5book.ru/html-tags/
https://jsoup.org/

CSS Selectors:
https://riptutorial.com/ru/jsoup/topic/515/%D1%81%D0%B5%D0%BB%D0%B5%D0%BA%D1%82%D0%BE%D1%80%D1%8B
https://learn.javascript.ru/css-selectors


https://www.youtube.com/watch?v=LX8QHwSyRCM
https://www.youtube.com/watch?v=QyjuoNqy9-k
https://www.youtube.com/watch?v=tI1qGwhn_bs
https://www.youtube.com/watch?v=wzh5TCVnWZQ
https://www.youtube.com/watch?v=0s8O7jfy3c0
https://www.youtube.com/watch?v=ZtXXvtI8jcs

Прочтено:
https://riptutorial.com/ru/jsoup
    
HtmlUnit & Selenium
https://en.wikipedia.org/wiki/HtmlUnit
http://htmlunit.sourceforge.net/
https://ru.wikipedia.org/wiki/Selenium
https://www.selenium.dev/


 package selenium_Methods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Bozoinabusride_Search {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "D:\\Tushar\\JARs\\selenium\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        driver.get("https://bozoinabusride.blogspot.com/");
        driver.manage().window().maximize();
        WebElement searchThis = driver.findElement(By.name("q"));

        searchThis.sendKeys("silver");
        driver.findElement(By.className("gsc-search-button")).click();
    }
} 
package org.example;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("seleniumTests")

public class SeleniumTests1 {
    WebDriver driver;

    @BeforeEach
    void setup(){
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
    }

    @Test
    @Order(1)
    void driverTest1() throws InterruptedException {
        driver.findElement(By.xpath("//a[contains(text(), 'Администрирование')]")).click();
        driver.findElement(By.id("username")).sendKeys("admin");
        driver.findElement(By.id("password")).sendKeys("secret123");
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
        Thread.sleep(2000);  // иначе падает
        driver.findElement(By.xpath("//input[@placeholder='Название']")).sendKeys("Чайник");
        driver.findElement(By.xpath("//input[@placeholder='Цена']")).sendKeys("25");
        driver.findElement(By.id("add-btn")).click();
        driver.findElement(By.xpath("//a[text()='Вернуться на сайт']")).click();

        Assertions.assertThat(driver.findElement(By.xpath("//div[@data-name='Чайник']")).getText())
                .as("'Чайник' должен быть на витрине")
                .contains("Чайник");
    }

    @Test
    @Order(2)
    void driverTest2(){
        driver.findElement(By.xpath("//div[@data-name='Чайник']//button[@data-action='add-to-cart']")).click();
        driver.findElement(By.id("open-cart-btn")).click();

        Assertions.assertThat(driver.findElement(By.xpath("//div[@class='cart-item' and .//b[text()='Чайник']]")).getText())
                .as("'Чайник' должен быть в корзине")
                .contains("Чайник");
    }

    @Test
    @Order(3)
    void driverTest3() throws InterruptedException{
        driver.findElement(By.xpath("//a[contains(text(), 'Администрирование')]")).click();
        driver.findElement(By.id("username")).sendKeys("admin1");
        driver.findElement(By.id("password")).sendKeys("secret1234");
        driver.findElement(By.xpath("//button[contains(text(), 'Sign in')]")).click();
        Thread.sleep(2000);
        Assertions.assertThat(driver.findElement(By.xpath("//div[@role='alert']")).getText())
                .as("Алерт с ошибкой")
                .contains("Неверные учетные данные пользователя");
    }

    @Test
    @Order(4)
    void driverTest4() throws InterruptedException {
        driver.findElement(By.xpath("//div[@data-name='Чайник']//button[@data-action='add-to-cart']")).click();
        driver.navigate().refresh();
        Thread.sleep(2000);
        driver.findElement(By.id("open-cart-btn")).click();

        try {
            Assertions.assertThat(driver.findElement(By.xpath("//div[@class='cart-item' and .//b[text()='Чайник']]")).getText())
                    .as("'Чайник' должен быть в корзине")
                    .contains("Чайник");
        } catch (NoSuchElementException e) {
            throw new AssertionError("'Чайник' должен быть в корзине, но не найден");
        }
    }

    @AfterEach
    void tearDown(){
        driver.quit();
    }
}

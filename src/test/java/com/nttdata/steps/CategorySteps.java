package com.nttdata.steps;

import com.nttdata.page.CategoryPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CategorySteps {

    private WebDriver driver;

    public CategorySteps(WebDriver driver) {
        this.driver = driver;
    }


    public void selectFirstProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement firstProductElement = wait.until(
                ExpectedConditions.elementToBeClickable(CategoryPage.firstProduct)
        );
        firstProductElement.click();
    }
}

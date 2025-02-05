package com.nttdata.steps;

import com.nttdata.page.ProductPage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductSteps {

    private WebDriver driver;

    public ProductSteps(WebDriver driver) {
        this.driver = driver;
    }

    public void setProductQuantity(int quantity) {
        WebElement quantityField = driver.findElement(ProductPage.quantityInput);
        quantityField.sendKeys(Keys.DELETE);
        quantityField.sendKeys(String.valueOf(quantity));
    }

    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(ProductPage.addToCartButton));
        addToCartBtn.click();
    }

    public String getTittleModal() {
        return this.driver.findElement(ProductPage.tittleModal).getText().substring(1);
    }

    public Double getProductPrice() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String precio = this.driver.findElement(ProductPage.productPrice).getAttribute("content");
        return Double.valueOf(precio);
    }

    public Double getTotalModal() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String total = this.driver.findElement(ProductPage.priceTotalModal).getText().substring(2);
        return Double.valueOf(total);
    }

    public void finishShip() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement firstProductElement = wait.until(
                ExpectedConditions.elementToBeClickable(ProductPage.finishButton)
        );
        firstProductElement.click();
    }


}

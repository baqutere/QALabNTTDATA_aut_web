package com.nttdata.steps;

import com.nttdata.page.CarritoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CarritoSteps {


    private WebDriver driver;

    public CarritoSteps(WebDriver driver) {
        this.driver = driver;
    }

    public String getTittleCarrito() {
        return this.driver.findElement(CarritoPage.tittleCarrito).getText();
    }

    public Double getTotalCarrito() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        String totalText = this.driver.findElement(CarritoPage.priceTotalCarrito).getText();
        String totalValue = totalText.replaceAll("[^0-9.]", "");
        return Double.valueOf(totalValue);
    }
}

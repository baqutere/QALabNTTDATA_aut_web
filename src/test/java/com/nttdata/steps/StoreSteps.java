package com.nttdata.steps;

import com.nttdata.page.StorePage;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class StoreSteps {

    private WebDriver driver;

    public StoreSteps(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return this.driver.findElement(StorePage.storeTittle).getText();
    }

    public int getItemSize() {
        List<WebElement> items = this.driver.findElements(StorePage.itemsProduct);
        return items.size();
    }

    public void navigateCategoryAndSubcategory(String categoria, String subcategoria) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        Actions action = new Actions(driver);

        By categoriaLocator = StorePage.getCategoriaLocator(categoria);
        By subcategoriaLocator = StorePage.getSubcategoriaLocator(subcategoria);

        try {
            // Verifica si la categoría existe y es visible
            WebElement categoriaElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(categoriaLocator));
            action.moveToElement(categoriaElemento).perform();

            // Verifica si la subcategoría existe y es visible
            WebElement subcategoriaElemento = wait.until(ExpectedConditions.elementToBeClickable(subcategoriaLocator));
            subcategoriaElemento.click();

        } catch (TimeoutException | NoSuchElementException e) {
            Assertions.fail("La categoría '" + categoria + "' o la subcategoría '" + subcategoria + "' no existen o no son visibles.", e);
        }
    }


}

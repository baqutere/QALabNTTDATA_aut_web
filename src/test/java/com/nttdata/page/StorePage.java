package com.nttdata.page;

import org.openqa.selenium.By;

public class StorePage {

    public static By storeTittle = By.cssSelector("h2.h2.products-section-title.text-uppercase");
    public static By itemsProduct = By.cssSelector("div.js-product");

    public static By getCategoriaLocator(String categoria) {
        return By.xpath(String.format("//ul[@id='top-menu']/li/a[contains(normalize-space(), '%s')]", categoria));
    }

    public static By getSubcategoriaLocator(String subcategoria) {
        return By.xpath(String.format("//ul[@id='top-menu']//a[contains(normalize-space(), '%s')]", subcategoria));
    }
}

package com.nttdata.page;

import org.openqa.selenium.By;

public class ProductPage {

    public static By quantityInput = By.id("quantity_wanted");
    public static By addToCartButton = By.cssSelector("button.add-to-cart");
    public static By tittleModal = By.cssSelector("h4.modal-title");
    public static By productPrice = By.cssSelector("span.current-price-value");
    public static By priceTotalModal = By.cssSelector("span.subtotal");
    public static By finishButton = By.xpath("//a[contains(@href, 'carrito?action=show') and contains(@class, 'btn-primary')]");
}

package com.nttdata.stepsdefinitions;

import com.nttdata.steps.*;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;

import static com.nttdata.core.DriverManager.getDriver;
import static com.nttdata.core.DriverManager.screenShot;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StoreStepsDef {

    private CarritoSteps carritoSteps;
    private CategorySteps categorySteps;
    private LoginSteps loginSteps;
    private ProductSteps productSteps;
    private StoreSteps storeSteps;

    private int cantidadGuardada;
    private Double precioGuardado;

    @Given("estoy en la página de la tienda")
    public void estoyEnLaPaginaDeLaTienda() {
        WebDriver driver = getDriver();
        carritoSteps = new CarritoSteps(driver);
        categorySteps = new CategorySteps(driver);
        loginSteps = new LoginSteps(driver);
        productSteps = new ProductSteps(driver);
        storeSteps = new StoreSteps(driver);
        driver.get("https://qalab.bensg.com/store/pe/iniciar-sesion?back=https%3A%2F%2Fqalab.bensg.com%2Fstore%2Fpe%2F");
        screenShot();
    }

    @When("me logeo con mi usuario: {string} y contraseña: {string}")
    public void meLogeoConMiUsuarioYContrasena(String usuario, String password) {
        loginSteps.typeUser(usuario);
        loginSteps.typePassword(password);
        screenShot();
        loginSteps.login();
    }

    @Then("valido que aparece el titulo {string}")
    public void validoQueApareceElTitulo(String expectedTitle) {
        String title = storeSteps.getTitle();
        assertEquals(expectedTitle, title);
    }

    @And("también valido que exista al menos un item")
    public void tambienValidoQueExistaAlMenosUnItem() {
        try {
            Thread.sleep(500);
            int itemsListSize = storeSteps.getItemSize();
            screenShot();
            assertTrue(itemsListSize > 0, "El tamaño de la lista es: " + itemsListSize);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Then("valido que aparece el mensaje de error {string}")
    public void validoQueApareceElMensajeDeError(String expectedMessageError) {
        String mensajeError = loginSteps.getMensajeError();
        screenShot();
        assertEquals(expectedMessageError, mensajeError);
    }

    @When("navego a la categoria: {string} y subcategoria: {string}")
    public void navegoALaCategoriaYSubcategoria(String categoria, String subcategoria) {
        storeSteps.navigateCategoryAndSubcategory(categoria, subcategoria);
        screenShot();
    }

    @And("agrego {int} unidades del primer producto al carrito")
    public void agregoUnidadesDelPrimerProductoAlCarrito(Integer cantidad) {
        try {
            this.cantidadGuardada = cantidad;
            categorySteps.selectFirstProduct();
            this.precioGuardado = productSteps.getProductPrice();
            productSteps.setProductQuantity(cantidad);
            Thread.sleep(1000);
            screenShot();

            productSteps.addToCart();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    @Then("valido en el popup la confirmación del producto agregado")
    public void validoEnElPopupLaConfirmacionDelProductoAgregado() {
        String expectedTitle = "Producto añadido correctamente a su carrito de compra";
        try {
            Thread.sleep(1000);
            String tittleModal = productSteps.getTittleModal();
            screenShot();
            assertEquals(expectedTitle, tittleModal);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("valido en el popup que el monto total sea calculado correctamente")
    public void validoEnElPopupQueElMontoTotalSeaCalculadoCorrectamente() {
        Double expectedTotal = this.precioGuardado * this.cantidadGuardada;
        try {
            Thread.sleep(1000);
            Double totalModal = productSteps.getTotalModal();
            assertEquals(expectedTotal, totalModal);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @When("finalizo la compra")
    public void finalizoLaCompra() {
        productSteps.finishShip();
    }

    @Then("valido el titulo de la pagina del carrito")
    public void validoElTituloDeLaPaginaDelCarrito() {
        String expectedTittle = "CARRITO";
        try {
            Thread.sleep(1000);
            String tittleCarrito = carritoSteps.getTittleCarrito();
            screenShot();
            assertEquals(expectedTittle, tittleCarrito);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @And("vuelvo a validar el calculo de precios en el carrito")
    public void vuelvoAValidarElCalculoDePreciosEnElCarrito() {
        Double expectedTotal = this.precioGuardado * this.cantidadGuardada;
        try {
            Thread.sleep(1000);
            Double totalModal = carritoSteps.getTotalCarrito();
            assertEquals(expectedTotal, totalModal);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

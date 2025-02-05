@mystore
Feature: My store

  @loginExitoso
  Scenario Outline: Iniciar Sesion
    Given estoy en la página de la tienda
    When me logeo con mi usuario: "<usuario>" y contraseña: "<password>"
    Then valido que aparece el titulo "<titulo>"
    And también valido que exista al menos un item

    Examples:
      | usuario                      | password     | titulo               |
      | teresaquintana20@hotmail.com | Teresa20$$## | PRODUCTOS DESTACADOS |

  @loginFallido
  Scenario Outline: Iniciar Sesion con credenciales incorrectas
    Given estoy en la página de la tienda
    When me logeo con mi usuario: "<usuario>" y contraseña: "<password>"
    Then valido que aparece el mensaje de error "<mensaje>"

    Examples:
      | usuario                      | password     | mensaje                 |
      | correo_no_existe@hotmail.com | Password$$## | Error de autenticación. |


  @ValidacionDePrecioDeProducto
  Scenario Outline: Validación del precio de un producto
    Given estoy en la página de la tienda
    And me logeo con mi usuario: "<usuario>" y contraseña: "<password>"
    When navego a la categoria: "<categoria>" y subcategoria: "<subcategoria>"
    And agrego <cantidad> unidades del primer producto al carrito
    Then valido en el popup la confirmación del producto agregado
    And valido en el popup que el monto total sea calculado correctamente
    When finalizo la compra
    Then valido el titulo de la pagina del carrito
    And vuelvo a validar el calculo de precios en el carrito

    Examples:
      | usuario                      | password     | categoria | subcategoria | cantidad |
      | teresaquintana20@hotmail.com | Teresa20$$## | Clothes   | Men          | 2        |
      | correo_no_existe@hotmail.com | Password$$## | Clothes   | Men          | 20       |
      | teresaquintana20@hotmail.com | Teresa20$$## | Autos     | Deportivos   | 1        |

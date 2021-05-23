package tests;

import request.TestProvider;
import model.AddToCartResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.qameta.allure.Allure.step;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static request.Request.post;

@ExtendWith(TestProvider.class)
public class WebShopTests extends TestBase {

    public String cookie;
    public AddToCartResponse response;

    @Test
    @DisplayName("Successful add product to cart via api and check from ui")
    void successfulAddProductToCartViaApiAndCheckFromUi() {
        step("Open main page", () ->
                open(""));

        step("Check that the shopping cart is empty", () ->
                $("#topcartlink .cart-qty").shouldBe(text("(0)")));

        step("Get cookie from ui webDriver", () ->
                cookie = getWebDriver().manage().getCookieNamed("Nop.customer").getValue());

        step("Add product to cart via api", () ->
                response = post("/addproducttocart/details/13/1",
                        "addtocart_13.EnteredQuantity=1", "Nop.customer", cookie)
                        .as(AddToCartResponse.class));

        step("Check that the product is added via api", () -> {
            assertThat(response.isSuccess(), is(true));
            assertThat(response.getUpdatetopcartsectionhtml(), is("(1)"));
        });

        step("Check that the product is added via ui", () -> {
            open("");
            $("#topcartlink .cart-qty").shouldBe(text("(1)"));
        });
    }

}

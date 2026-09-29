package org.example;

import com.codeborne.selenide.DragAndDropOptions;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.*;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static io.restassured.RestAssured.given;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Tag("dragAndDropTests")

public class DragAndDropTests {
    private final List<Integer> createdGoodIds = new ArrayList<>();

    @BeforeEach
    void setup(){
        Selenide.open("http://localhost:8080/");
    }

    @AfterEach
    void cleanUp() {
        if (createdGoodIds.isEmpty()) {
            System.out.println("Нечего удалять");
            return;
        }

        System.out.println("=== Удаляем " + createdGoodIds.size() + " товаров");

        for (int id : createdGoodIds) {
            given()
                    .spec(basicRQ)
                    .contentType(ContentType.JSON)
                    .auth().basic("admin", "secret123")
                    .when()
                    .delete("/goods/" + id)
                    .then()
                    .log().all();
        }

        createdGoodIds.clear();
        System.out.println("Очистка завершена");
    }

    private RequestSpecification basicRQ = new RequestSpecBuilder()
            .setBaseUri("http://localhost:8080")
            .log(LogDetail.ALL)
            .addQueryParam("page", 0)
            .build();

    @Test
    @Order(1)
    void DragAndDropTest1() {
        sleep(1000);
        $x("//*[@id='card-25']")
                .dragAndDrop(DragAndDropOptions.to($x("//*[@id='open-cart-btn']")));
        sleep(2000);
        $x("//*[@id='open-cart-btn']").click();
        $x("//*[@id='cart-item-25']").shouldBe(visible);
        $x("//button[@data-action='remove']").click();
        $x("//*[@id='cart-item-25']").shouldNotBe(visible);
    }

    @Test
    @Order(2)
    void DragAndDropTest2() {
        actions().moveToElement($x("//*[@id='card-25']"))
                .clickAndHold()
                .pause(1000)
                .moveToElement($x("//*[@id='open-cart-btn']"))
                .release()
                .pause(Duration.ofSeconds(2))
                .perform();
        $x("//*[@id='open-cart-btn']").click();
        $x("//*[@id='cart-item-25']").shouldBe(visible);
        $x("//button[@data-action='remove']").click();
        $x("//*[@id='cart-item-25']").shouldNotBe(visible);
    }

    @Test
    @Order(3)
    void DragAndDropTest3() {
        SelenideElement card = $x("//*[@id='card-25']");
        SelenideElement cartBtn = $x("//*[@id='open-cart-btn']");

        card.shouldBe(visible);
        cartBtn.shouldBe(visible);

        int cardCenterX = card.getCoordinates().inViewPort().getX() + card.getSize().getWidth() / 2;
        int cardCenterY = card.getCoordinates().inViewPort().getY() + card.getSize().getHeight() / 2;

        int cartCenterX = cartBtn.getCoordinates().inViewPort().getX() + cartBtn.getSize().getWidth() / 2;
        int cartCenterY = cartBtn.getCoordinates().inViewPort().getY() + cartBtn.getSize().getHeight() / 2;

        int diffX = cartCenterX - cardCenterX;
        int diffY = cartCenterY - cardCenterY;

        Actions dndActions = actions()
                .moveToElement(card)
                .clickAndHold();

        for (int i = 0; i < 50; i++) {
            dndActions.moveByOffset(diffX / 50, diffY / 50);
        }

        dndActions.release().perform();

        cartBtn.shouldBe(clickable).click();
        $x("//*[@id='cart-item-25']").shouldBe(visible);
        $x("//button[@data-action='remove']").shouldBe(clickable).click();
        $x("//*[@id='cart-item-25']").shouldNotBe(visible);
    }






}

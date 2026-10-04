package pages;

import io.qameta.allure.Step;
import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.visible;

public class AdminPageAssert extends AbstractAssert<AdminPageAssert, AdminPage> {

    public AdminPageAssert(AdminPage adminPage) {
        super(adminPage, AdminPageAssert.class);
    }

    @Step("Проверить, что тост 'Товар успешно добавлен' виден")
    public void toastIsVisible() {
        actual.toast.shouldBe(visible);
    }
}

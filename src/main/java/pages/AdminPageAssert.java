package pages;

import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.Condition.visible;

public class AdminPageAssert extends AbstractAssert<AdminPageAssert, AdminPage> {
    public AdminPageAssert(AdminPage authPage){
        super(authPage, AdminPageAssert.class);
    }

    public void toastIsVisible() {
        actual.toast.shouldBe(visible);
    }
}


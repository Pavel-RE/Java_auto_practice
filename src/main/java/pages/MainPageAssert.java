package pages;


import org.assertj.core.api.AbstractAssert;

import static com.codeborne.selenide.CollectionCondition.itemWithText;
import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Condition.*;

public class MainPageAssert extends AbstractAssert<MainPageAssert, MainPage> {
    public MainPageAssert(MainPage mainPage){
        super(mainPage, MainPageAssert.class);
    }

    public void cartButtonIsVisible(){
        actual.cartButton.should(visible);
    }

    public void cartButtonIsNotVisible(){
        actual.cartButton.shouldNot(visible);
    }

    public void countInputContainsValue(int index, String value){
        actual.productCountInputList.get(index)
                .should(value(value));
    }

    public void productCardSizeIsEquals(int count){
        actual.productCardList.should(size(count));
    }

    public void productNameListContainsProduct(String name){
        actual.productNameList.shouldHave(itemWithText(name));
    }

    public MainPage page(){
        return actual;
    }
}

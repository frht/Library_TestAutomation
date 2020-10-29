package com.library.pages;

import com.utils.BrowserUtils;
import com.utils.Driver;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BorrowingBooksPage extends BasePage{

    private WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

    @FindBy(xpath = "//table[@id='borrowed_list']//tbody/tr/td[2]")
    private List<WebElement> nameOfBooksBorrowedElements;

    public List<String> nameOfBooksBorrowedList(){
        return BrowserUtils.getElementsText(wait.until(ExpectedConditions.visibilityOfAllElements(nameOfBooksBorrowedElements)));
    }

    public boolean lastBorrowedBookIsDisplayed(String nameLastBookBorrowed){
        List<String> borrowedBooksNames = nameOfBooksBorrowedList();
        for (String each : borrowedBooksNames) {
            if(each.equals(nameLastBookBorrowed)){
                return true;
            }
        }
        return false;
    }
}

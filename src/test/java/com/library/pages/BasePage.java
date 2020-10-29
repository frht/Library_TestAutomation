package com.library.pages;

import com.library.utils.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class BasePage {

    private WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);


    public BasePage(){
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[@id='toast-container']/div")
    private WebElement bookBorrowedOrReturnedAlert;

    @FindBy(linkText = "Borrowing Books")
    private WebElement borrowingBooksTab;

    /**
     * book added or removed alert is displayed
     * @return boolean
     */
    public boolean alertIsDisplayed(){
        return wait.until(ExpectedConditions.visibilityOf(bookBorrowedOrReturnedAlert)).isDisplayed();
    }


    public Set<String> goToBorrowingBooksTab(){
        Set<String> windows = wait.until(WebDriver::getWindowHandles);
        for (String eachWindow : windows) {
            Driver.getDriver().switchTo().window(eachWindow);
            String currentUrl = Driver.getDriver().getCurrentUrl();
            if(currentUrl.contains("borrowing-books")){
                System.out.println("Second Window");
            }
        }
        return Driver.getDriver().getWindowHandles();
    }

    public void openBorrowingBooksInNewTab(){
       // String selectLinkOpenInNewTab = Keys.chord(Keys.CONTROL,Keys.RETURN);
        //borrowingBooksTab.sendKeys(selectLinkOpenInNewTab);

        //opening new tab with the href attribute of the elemtn
        String href = borrowingBooksTab.getAttribute("href");
        ((JavascriptExecutor) Driver.getDriver()).executeScript("window.open('"+href+"','_blank');");
    }




}

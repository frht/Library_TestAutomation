package com.library.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class BrowserUtils {

    private static WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

    public static Select getSelectDropdown(WebElement selectDropdown){
        return new Select(wait.until(ExpectedConditions.visibilityOf(selectDropdown)));
    }

    /**
     Accepts a list of Web Element
     @param list List<WebElement>
     @return List<String>
     Method should be returning a list of Strings
     */
    public synchronized static List<String> getElementsText(List<WebElement> list){
        List<String> WebElementsAsString = new ArrayList<>();

        for(WebElement each : list){
            WebElementsAsString.add(each.getText());
        }

        return WebElementsAsString;

    }

    /**
     * Method will wait for the element to be clickable and then will click on the element
     * @param element
     */
    public static void clickOnElement(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public static void doubleClickOnElement(WebElement element){

        WebElement elementToDoubleClick = wait.until(ExpectedConditions.elementToBeClickable(element));
        elementToDoubleClick.click();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(elementToDoubleClick)));
        elementToDoubleClick.click();
    }



    /**
     * Method used for explicit wait
     * @param seconds
     */
    public static void wait(int seconds){
        try {
            Thread.sleep(seconds*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method will wait for the element to be clickable and then will click on the element using JS Executor
     * @param element
     */
    public static void clickWithJS(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
        ((JavascriptExecutor) (Driver.getDriver())).executeScript("arguments[0].click()", element);
    }

    /**
     * Method used to enter text on a input box. The method will wait until all the text has entered
     * before moving to next step. (Handles possible issue with selenium entering partial text)
     * @param element to be an input box
     * @param text text that will be entered
     * For instance, selenium webdriver is trying to enter the text:
     * Expected text input  actual text input
     *
     * [java is great] --> [java is g]
     *
     * for some reason, selenium jumps to the next step without finishing text input here
     *
     * to prevent this issue, we need to make it wait until text is completely entered.
     *
     * How to do that?
     *
     * As we know, text is stored in the value attribute. So we need to wait until value attribute
     * of the element will obtain expected text.
     */
    public static void enterText(WebElement element, String text){
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
        wait.until(ExpectedConditions.attributeToBe(element, "value", text));
        System.out.println("Entering text: " + text);
    }

}

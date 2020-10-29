package com.library.pages;

import com.utils.BrowserUtils;
import com.utils.Driver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class BooksManagementPage extends BasePage{

    private WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);

    @FindBy(xpath = "//h3[contains(text(), 'Book Management')]")
    private WebElement bookManagementSubtitle;

    @FindBy(xpath = "//select[@name='tbl_books_length']")
    private WebElement showRecordsSelectDropdown;

    @FindBy(xpath = "//table[@id='tbl_books']//tbody/tr/td[5]")
    private List<WebElement> allBookCategoriesDisplayed;

    @FindBy(xpath = "//table[@id='tbl_books']//tbody/tr/td/a")
    private List<WebElement> allBooksActionsDisplayed;

    @FindBy(xpath = "//select[@id='book_categories']")
    private WebElement categoryDropdownButton;

    @FindBy(xpath = "//a[@title='Next']/..")
    private WebElement nextPageButton;



    /**
     * will select the given category from dropdown menu
     * @param category
     */
    public void selectCategory(String category){
        Select select = BrowserUtils.getSelectDropdown(categoryDropdownButton);
        select.selectByVisibleText(category);
    }

    /**
     * Clicks on the button "Next" if the button is enabled
     * @returns boolean true if the button was clicked or false if the button is not enabled
     */
    public boolean clickNextPageButton(){
        String value = wait.until(ExpectedConditions.visibilityOf(nextPageButton)).getAttribute("class");
       if (value.equals("page-item next")) {
           nextPageButton.click();
           return true;
       }
       return false;
   }

    /**
     * Checks if all the books are from the category selected
     * @param category
     */
    public void booksDisplayCategoryIsCorrect(String category){
       if(category.equalsIgnoreCase("ALL")){
           Assert.assertTrue(true);
           return;
       }

       selectNumberOfBooksToBeDisplay(500);
      do {
           List<WebElement> categories = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(allBookCategoriesDisplayed)));
           for (WebElement eachCategory : categories) {
               Assert.assertEquals(eachCategory.getText().trim(), category);
           }
       } while (clickNextPageButton());
   }

    public void clickCategoryDropDownButton(){
       BrowserUtils.clickOnElement(categoryDropdownButton);
   }

    /**
     * Gets all the options available in the category dropdown ands
     * returns them as a List
     * @return List<String>
     */
    public List<String> getCategoryOptionsList() {
        List<WebElement> options = BrowserUtils.getSelectDropdown(categoryDropdownButton).getOptions();
        return BrowserUtils.getElementsText(options);
    }

    public void bookManagementSubtitleIsDisplayed(){
        WebElement subTitle = wait.until(ExpectedConditions.visibilityOf(bookManagementSubtitle));
        Assert.assertTrue(subTitle.isDisplayed());
    }

    public List<String> getNumberOfBooksToDisplayOptionList() {
        List<WebElement> options = BrowserUtils.getSelectDropdown(showRecordsSelectDropdown).getOptions();
        return BrowserUtils.getElementsText(options);
    }

    public void clickShowNumberOfRecordDropdown(){
        BrowserUtils.clickOnElement(showRecordsSelectDropdown);
    }

    public void selectNumberOfBooksToBeDisplay(Integer optionValue){
        Select select = BrowserUtils.getSelectDropdown(showRecordsSelectDropdown);
        select.selectByVisibleText(String.valueOf(optionValue));
    }

    public int currentNumberOfBooksDisplayed(){
       return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(allBookCategoriesDisplayed))).size();
    }

    public boolean buttonNextIsEnable(){
        return !wait.until(ExpectedConditions.visibilityOf(nextPageButton)).getAttribute("class").contains("disabled");
    }

    public void expectedNumberOfBookPerPage(Integer booksPerpage){
        do{
            Integer actual = currentNumberOfBooksDisplayed();
            if(buttonNextIsEnable()){
                Assert.assertEquals(booksPerpage, actual);
            }
        }while(clickNextPageButton());
    }

    public void clickOnHeader(String HeaderOption){
        String xPath = "//th[contains(text(), '"+HeaderOption+"')]";
        WebElement headerOptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        headerOptionElement.click();
    }

    public void doubleClickOnHeader(String HeaderOption){
        String xPath = "//th[contains(text(), '"+HeaderOption+"')]";
        WebElement headerOptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
       headerOptionElement.click();
        WebElement refreshedElement = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(headerOptionElement)));
        refreshedElement.click();

    }

    public boolean booksSortedInAscendingOrderBy(String headerOption){
       String index="";
        switch (headerOption){
            case"Name":
                index ="3";
                break;
            case"Author":
                index="4";
                break;
            case"Category":
                index ="5";
                break;
            case"Year":
                index = "6";
                break;
            case"Borrowed By":
                index = "7";
        }
        String xPath="//tbody//tr/td["+index+"]";
        List<WebElement> listElements = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xPath))));
        ArrayList<String> elementsText = (ArrayList<String>) BrowserUtils.getElementsText(listElements);
        List<String> temp = new ArrayList<>(elementsText);
        Collections.sort(temp);
        System.out.println(elementsText);
        System.out.println(temp);
        return elementsText.equals(temp);

    }

    public boolean booksSortedInDescendingOrderBy(String headerOption){
        String index="";
        switch (headerOption) {
            case"ISBN":
                index ="2";
                break;
            case "Name":
                index = "3";
                break;
            case "Author":
                index = "4";
                break;
            case "Category":
                index = "5";
                break;
            case "Year":
                index = "6";
                break;
            case "Borrowed By":
                index = "7";
        }
        String xPath="//tbody//tr/td["+index+"]";
        List<WebElement> listElements = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xPath))));
        ArrayList<String> elementsText = (ArrayList<String>) BrowserUtils.getElementsText(listElements);
        List<String> temp = new ArrayList<>(elementsText);
        temp.sort(Collections.reverseOrder());
        System.out.println(elementsText);
        System.out.println(temp);
        return elementsText.equals(temp);
    }

    public void borrowFirstBookAvailable(){
        do{
            List<WebElement> listOfBooks = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(allBooksActionsDisplayed)));
            int index = 1;
            for (WebElement eachBook : listOfBooks) {
                wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(listOfBooks)));
                if(!eachBook.getAttribute("class").contains("disabled")){
                    String xPath = "//tbody/tr["+index+"]/td";
                    List<WebElement> bookInfoElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xPath)));
                    List<String> bookInfo = BrowserUtils.getElementsText(bookInfoElements);
                    bookInfo.remove(0);
                    System.out.println(bookInfo);
                    eachBook.click();

                    return;
                }
                index++;
            }

        }while (clickNextPageButton());
        throw new RuntimeException("There are not books available to borrow");
    }

    public String borrowAndGetNameFirstBookAvailable(){
        do{
            List<WebElement> listOfBooks = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(allBooksActionsDisplayed)));
            int index = 1;
            for (WebElement eachBook : listOfBooks) {
                wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(listOfBooks)));
                if(!eachBook.getAttribute("class").contains("disabled")){
                    String xPath = "//tbody/tr["+index+"]/td";
                    List<WebElement> bookInfoElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xPath)));
                    List<String> bookInfo = BrowserUtils.getElementsText(bookInfoElements);
                    bookInfo.remove(0);
                    System.out.println(bookInfo);
                    eachBook.click();
                    return bookInfo.get(1);
                }
                index++;
            }

        }while (clickNextPageButton());
        throw new RuntimeException("There are not books available to borrow");
    }








}

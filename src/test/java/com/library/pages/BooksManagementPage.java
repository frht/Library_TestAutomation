package com.library.pages;

import com.library.utils.BrowserUtils;
import com.library.utils.Driver;
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

    /**
     * asserts that the subtitle "Book Managment is Displayed
     */
    public void bookManagementSubtitleIsDisplayed(){
        WebElement subTitle = wait.until(ExpectedConditions.visibilityOf(bookManagementSubtitle));
        Assert.assertTrue(subTitle.isDisplayed());
    }

    /**
     * method to get a list of the available number of books that can be displayed
     * @return List<String>
     */
    public List<String> getNumberOfBooksToDisplayOptionList() {
        List<WebElement> options = BrowserUtils.getSelectDropdown(showRecordsSelectDropdown).getOptions();
        return BrowserUtils.getElementsText(options);
    }

    /**
     * click on the dropdown for number of books to be displayed
     * Note: I believe clicking is not needed to get the options as it is a Select dropdown
     * but I added it anywyas because it is defined as a step
     */
    public void clickShowNumberOfRecordDropdown(){
        BrowserUtils.clickOnElement(showRecordsSelectDropdown);
    }

    /**
     * Selects the number of books that we want to display per page
     * @param optionValue is an Integer that represent the value of the number of books we
     * want to display. Inside the method, I used the String value of that Integer to choose from the
     * menu
     */
    public void selectNumberOfBooksToBeDisplay(Integer optionValue){
        Select select = BrowserUtils.getSelectDropdown(showRecordsSelectDropdown);
        select.selectByVisibleText(String.valueOf(optionValue));
    }

    /**
     * Method used to get the number of book that are currently displayed
     * @return int that represents the number of books that are displayed
     */
    public int currentNumberOfBooksDisplayed(){
       return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(allBookCategoriesDisplayed))).size();
    }

    /**
     * Method will click on the next page button if the button is enabled, it will click on
     * it and return true. If it is disable, it won't click on it and it will return false
     * @return boolean
     */
    public boolean buttonNextIsEnable(){
        return !wait.until(ExpectedConditions.visibilityOf(nextPageButton)).getAttribute("class").contains("disabled");
    }

    /**
     *Method accepts the number of books we expect to see per page.
     * Method will check every page to make sure every page contains
     * that amount of books
     * @param booksPerPage that represents the amount of books we expect to see per page
     */
    public void expectedNumberOfBookPerPage(Integer booksPerPage){
        do{
            Integer actual = currentNumberOfBooksDisplayed();
            if(buttonNextIsEnable()){
                Assert.assertEquals(booksPerPage, actual);
            }
        }while(clickNextPageButton());
    }

    /**
     * This method will click on the headers of the table(clicking the header will
     * sort the books in ascending order based in the header that was clicked)
     * Note: the table is by default sorted in descending order by ISBN number
     * @param HeaderOption The name of the header that we want to click
     */
    public void clickOnTableHeaderOption(String HeaderOption){
        String xPath = "//th[contains(text(), '"+HeaderOption+"')]";
        WebElement headerOptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
        headerOptionElement.click();
    }

    /**
     * This method will double click on the headers of the table(double-clicking the header will
     * sort the books in descending order based in the header that was double clicked)
     * Note: the table is by default sorted in descending order by ISBN number
     * @param HeaderOption The name of the header that we want to double click
     */
    public void doubleClickOnHeader(String HeaderOption){
        String xPath = "//th[contains(text(), '"+HeaderOption+"')]";
        WebElement headerOptionElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xPath)));
       headerOptionElement.click();
        WebElement refreshedElement = wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(headerOptionElement)));
        refreshedElement.click();

    }

    /**
     * Method will check the first page is sorted in ascending order
     * @param headerOption that we want to check to make sure is sorted in ascending order
     * @return true if the books are in ascending order , false if they are not
     */
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
    /**
     * Method will check the first page is sorted in descending order
     * @param headerOption that we want to check to make sure is sorted in descending order
     * @return true if the books are in descending order , false if they are not
     */
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

    /**
     * Method will look through the pages for the first book available and
     * click the button to borrow it
     */
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

    /**
     * Method will look through the pages for the first book available and
     *  click the button to borrow it
     * This method will look
     * @return a string with the name of the book that was borrowed
     * Used this method to confirm the book that
     * we borrowed is present in the "Borrowing Books" page
     */
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

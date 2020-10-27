package com.library.pages;

import com.utils.BrowserUtils;
import com.utils.Driver;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BooksManagementPage extends BasePage{

    private WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 10);


    @FindBy(xpath = "//h3[contains(text(), 'Book Management')]")
    private WebElement bookManagementSubtitle;

    @FindBy(xpath = "//select[@name='tbl_books_length']")
    private WebElement showRecordsSelectDropdown;

    @FindBy(xpath = "//table[@id='tbl_books']//tbody/tr/td[5]")
    private List<WebElement> allBookCategoriesDisplayed;

   @FindBy(xpath = "//select[@id='book_categories']")
   private WebElement categoryDropdownButton;

   public void selectCategory(String category){
       Select select = BrowserUtils.getSelectDropdown(categoryDropdownButton);
       select.selectByVisibleText(category);
   }

   @FindBy(xpath = "//a[@title='Next']/..")
   private WebElement nextPageButton;

   public boolean clickNextPageButton(){
        String value = wait.until(ExpectedConditions.visibilityOf(nextPageButton)).getAttribute("class");
       if (value.equals("page-item next")) {
           nextPageButton.click();
           return true;
       }
       return false;
   }



   public void booksDisplayCategoryIsCorrect(String category){
       if(category.equalsIgnoreCase("ALL")){
           Assert.assertTrue(true);
           return;
       }

       selectShowNumberOfRecordsDisplayed(500);
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

    public List<String> getCategoryOptionsList() {
        List<WebElement> options = BrowserUtils.getSelectDropdown(categoryDropdownButton).getOptions();
        return BrowserUtils.getElementsText(options);
    }


    public void bookManagementSubtitleIsDisplayed(){
        WebElement subTitle = wait.until(ExpectedConditions.visibilityOf(bookManagementSubtitle));
        Assert.assertTrue(subTitle.isDisplayed());
    }

    public List<String> getShowNumberOfRecordsOptionsList() {
        List<WebElement> options = BrowserUtils.getSelectDropdown(showRecordsSelectDropdown).getOptions();
        return BrowserUtils.getElementsText(options);
    }

    public void clickShowNumberOfRecordDropdown(){
        BrowserUtils.clickOnElement(showRecordsSelectDropdown);
    }

    public void selectShowNumberOfRecordsDisplayed(Integer optionValue){
        Select select = BrowserUtils.getSelectDropdown(showRecordsSelectDropdown);
        select.selectByVisibleText(String.valueOf(optionValue));
    }

    public int currentNumberOfBooksDisplayed(){
       return wait.until(ExpectedConditions.visibilityOfAllElements(allBookCategoriesDisplayed)).size();
    }




}

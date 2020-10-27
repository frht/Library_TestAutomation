package com.library.pages;

import com.utils.ConfigurationReader;
import com.utils.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage{

    WebDriverWait wait = new WebDriverWait(Driver.getDriver(), 5);

    @FindBy(id = "inputEmail")
    private WebElement emailInputBox;

    @FindBy(id = "inputPassword")
    private WebElement passwordInputBox;

    @FindBy(xpath = "//button[@type='submit']")
    private WebElement  loginButtonElement;

    public void getUrl(){
        Driver.getDriver().get(ConfigurationReader.getProperty("url"));
    }



    public void login(String role){
        String username = "";
        String password = "";
        if (role.equalsIgnoreCase("librarian")) {
            username = ConfigurationReader.getProperty("librarian52Username");
            password = ConfigurationReader.getProperty("librarianPassword");
        } else if (role.equalsIgnoreCase("student")) {
            username = ConfigurationReader.getProperty("student49Username");
            password = ConfigurationReader.getProperty("student49Password");
        }
        emailInputBox.sendKeys(username);
        passwordInputBox.sendKeys(password);
        loginButtonElement.click();
    }




}

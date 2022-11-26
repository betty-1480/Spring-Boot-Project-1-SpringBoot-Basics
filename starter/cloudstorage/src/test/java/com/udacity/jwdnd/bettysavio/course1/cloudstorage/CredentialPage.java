package com.udacity.jwdnd.bettysavio.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CredentialPage {

    private WebDriver driver;

    @FindBy(id="nav-credentials-tab")
    WebElement navCredentialsTab;

    @FindBy(id="credential-url")
    WebElement credentialUrl;

    @FindBy(id="credential-username")
    WebElement credentialUsername;

    @FindBy(id="credential-password")
    WebElement credentialPassword;

    @FindBy(id="add-cred-btn")
    WebElement addCredBtn;

    @FindBy(id="btnCredentialSubmit")
    WebElement btnCredentialSubmit;

    @FindBy(id="credentialTable")
    WebElement credentialTable;

    public CredentialPage(WebDriver webDriver){
        this.driver=webDriver;
        PageFactory.initElements(driver,this);
    }

    public boolean checkCredentialPresent(String url, String userName, String password){
        List<WebElement> webElementList=credentialTable.findElements(By.tagName("th"));
        boolean credentialPresent=false;

        if(webElementList.size()>=5){
            WebElement thTitleName=webElementList.get(4);
            //The innerHTML is an attribute of a WebElement which is equal to the content that is present between the starting and ending tag.
            String urlforCred= thTitleName.getAttribute("innerHTML");
            List<WebElement> credentialTableElements=credentialTable.findElements(By.tagName("td"));
            String username=credentialTableElements.get(1).getAttribute("innerHTML");
            credentialPresent = urlforCred.equals(url)&&username.equals(userName);//&&password1.equals(password);
        }
        return credentialPresent;
    }

    public void addCredentials(String url, String userName, String password){
        JavascriptExecutor js=((JavascriptExecutor)driver);
        js.executeScript("arguments[0].click()",navCredentialsTab);
        js.executeScript("arguments[0].click()",addCredBtn);
        js.executeScript("arguments[0].click()",credentialUrl);
        js.executeScript("arguments[0].value='" + url + "';",credentialUrl);
        js.executeScript("arguments[0].click()",credentialUsername);
        js.executeScript("arguments[0].value='" + userName + "';",credentialUsername);
        js.executeScript("arguments[0].click()",credentialPassword);
        js.executeScript("arguments[0].value='" + password + "';",credentialPassword);
        js.executeScript("arguments[0].click()",btnCredentialSubmit);
    }

    public void editCredential(String url, String userName, String password){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",navCredentialsTab);
        List<WebElement> webElementList = credentialTable.findElements(By.tagName("button"));
        WebElement btnEdit=webElementList.get(0);
        JavascriptExecutor js=((JavascriptExecutor)driver);
        js.executeScript("arguments[0].click()",btnEdit);
        js.executeScript("arguments[0].click()",credentialUrl);
        js.executeScript("arguments[0].value='" + url + "';",credentialUrl);
        js.executeScript("arguments[0].click()",credentialUsername);
        js.executeScript("arguments[0].value='" + userName + "';",credentialUsername);
        js.executeScript("arguments[0].click()",credentialPassword);
        js.executeScript("arguments[0].value='" + password + "';",credentialPassword);
        js.executeScript("arguments[0].click()",btnCredentialSubmit);
    }

    public void deleteCredential(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",navCredentialsTab);
        List<WebElement> webElementList = credentialTable.findElements(By.tagName("a"));
        WebElement btnDelete=webElementList.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDelete);
    }
}

package com.udacity.jwdnd.bettysavio.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {
    @FindBy(id="homeLinkFromSuccess")
     WebElement homeLinkFromSuccess;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void backToHome() {
        homeLinkFromSuccess.click();
    }

}

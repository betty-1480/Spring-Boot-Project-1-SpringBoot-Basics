package com.udacity.jwdnd.bettysavio.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotePage {

    @FindBy(id="nav-notes-tab")
     WebElement navNotesTab;

    @FindBy(id="note-title")
     WebElement webElementNoteTitle;

    @FindBy(id="note-description")
     WebElement webElementNoteDescription;

    @FindBy(id="btnNoteSubmit")
     WebElement webElementButtonNoteSubmit;

    @FindBy(id="add-new-notes")
     WebElement webElementButtonAddNewNotes;

    @FindBy(id="userTable")
     WebElement webElementNoteTable;

    private WebDriver driver;

    //Constructor, instantiate all the elements annotated by @FindBy
    public NotePage(WebDriver webDriver){
        this.driver=webDriver;
        PageFactory.initElements(webDriver,this);
    }

    public boolean checkNotePresent(String note){
    //The tagName read-only property of the Element interface returns the tag name of the element on which it's called.
        List<WebElement> webElementList=webElementNoteTable.findElements(By.tagName("th"));
        boolean notePresent=false;
        if(webElementList.size()==4){
            WebElement thTitleName=webElementList.get(3);
            //The innerHTML is an attribute of a WebElement which is equal to the content that is present between the starting and ending tag.
            String noteTitle= thTitleName.getAttribute("innerHTML");
            notePresent = noteTitle.equals(note);
        }
        return notePresent;
    }

    public void addNewNote(String title, String description){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",navNotesTab);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElementButtonAddNewNotes);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + title + "';", webElementNoteTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + description + "';", webElementNoteDescription);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElementButtonNoteSubmit);
    }

    public void editNote(String title, String description){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",navNotesTab);
        List<WebElement> webElementList = webElementNoteTable.findElements(By.tagName("button"));
        WebElement btnEdit=webElementList.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnEdit);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + title + "';", webElementNoteTitle);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + description + "';", webElementNoteDescription);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", webElementButtonNoteSubmit);
    }

    public void deleteNote(String title, String description ){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",navNotesTab);
        List<WebElement> webElementList = webElementNoteTable.findElements(By.tagName("a"));
        WebElement btnDelete=webElementList.get(0);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btnDelete);
    }

}

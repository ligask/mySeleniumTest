package com.ultimateqa;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class UpdateSaveProfileSettings {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Launch web browser and navigate to the webpage
        String url = "https://www.ultimateqa.com/automation";
        String expectedTitle = "Automation Practice - Ultimate QA";
        driver.get(url);
        String actualTitle = driver.getTitle();
        if (actualTitle.equals(expectedTitle))
            System.out.println("Step 1 : Passed, Ultimate QA – Automation Practice page is opened");
        else
            System.out.println("Step 1 : Failed");

        // Navigate to Login automation page
        WebElement link = driver.findElement(By.partialLinkText("Login automation"));
        link.click();
        Thread.sleep(2000);
        WebElement loginEmail = driver.findElement(By.id("user[email]"));
        if (loginEmail != null)
            System.out.println("Step 2 : Passed, Sign In page is opened");
        else
            System.out.println("Step 2 : Failed");

        // Enter Email and Password for your test account and click Sign In button
        String username = "wsiztest@yopmail.com";
        String userPassword = "wsizTest";
        loginEmail.click();
        loginEmail.sendKeys(username);
        WebElement password = driver.findElement(By.id("user[password]"));
        password.click();
        password.sendKeys(userPassword);
        Thread.sleep(1000);
        WebElement btnSignIn = driver.findElement(By.xpath("//input[@value='Sign in']"));
        btnSignIn.click();

        //Time for tester to solve the captcha manually
        Thread.sleep(15000);

        // Check if user is logged in
        WebElement userDropdown = driver.findElement(By.cssSelector(".dropdown__toggle-button"));
        if (userDropdown != null)
            System.out.println("Step 3 : Passed, User is logged in to the collections page");
        else
            System.out.println("Step 3 : Failed");

        // Go to the Profile -> Your Profile section
        userDropdown.click();
        WebElement account = driver.findElement(By.xpath("//a[contains(text(),'My Account')]"));
        account.click();
        WebElement company = driver.findElement(By.id("user[profile_attributes][company]"));
        if (company != null)
            System.out.println("Step 4 : Passed, Profile -> Your Profile section is opened");
        else
            System.out.println("Step 4 : Failed");

        // Make sure there is no data in Company input field and enter your test data
        String compTestValue = "Company";
        company.click();
        company.clear();
        company.sendKeys(compTestValue);
        if (company.getAttribute("value").equals(compTestValue))
            System.out.println("Step 5 : Passed, Test data is entered in the Company input field");
        else
            System.out.println("Step 5 : Failed");

        // Make sure there is no data in Professional Title input field and enter your test data
        String profTestTitleValue = "QA specialist";
        WebElement profTitle = driver.findElement(By.id("user[profile_attributes][headline]"));
        profTitle.click();
        profTitle.clear();
        profTitle.sendKeys(profTestTitleValue);
        if (profTitle.getAttribute("value").equals(profTestTitleValue))
            System.out.println("Step 6 : Passed, Test data is entered in the Professional Title input field");
        else
            System.out.println("Step 6 : Failed");


        // Save changes
        WebElement saveChanges = driver.findElement(By.xpath("//input[@value='Save Changes']"));
        saveChanges.click();

        // Refresh webpage to check if new values are actually saved
        driver.navigate().refresh();
        Thread.sleep(2000);
        WebElement assertCompany = driver.findElement(By.id("user[profile_attributes][company]"));
        WebElement assertProfTitle = driver.findElement(By.id("user[profile_attributes][headline]"));
        if (assertCompany.getAttribute("value").equals(compTestValue) && assertProfTitle.getAttribute("value").equals(profTestTitleValue))
            System.out.println("Step 7 : Passed, Both changes are saved");
        else
            System.out.println("Step 7 : Failed");

        // Sign out
        userDropdown = driver.findElement(By.cssSelector(".dropdown__toggle-button"));
        userDropdown.click();
        WebElement signOut = driver.findElement(By.xpath("//a[contains(text(),'Sign Out')]"));
        signOut.click();
        expectedTitle = "Ultimate QA";
        if (driver.getTitle().equals(expectedTitle))
            System.out.println("Step 8 : Passed, User is signed out to the homepage");
        else
            System.out.println("Step 8 : Failed");

        // Close browser
        driver.close();
    }
}
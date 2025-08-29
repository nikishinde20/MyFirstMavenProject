package com.qa.tests;


import com.qa.utility.ExcelUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class RegistrationTest {
    WebDriver driver;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://opensource-demo.orangehrmlive.com/");
    }

    @Test(priority = 1)
    public void loginTest() throws Exception {
        String username = ExcelUtils.getCellData("LoginData", 1, 0);
        String password = ExcelUtils.getCellData("LoginData", 1, 1);

        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    
       System.out.println("******************Login Test completed******************");
    }

    @Test(priority = 2)
    public void registrationFormTest() throws Exception {
    	
    	
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='My Info']")).click();

        // Example data
        String firstName = "Nikita";
        String lastName = "Shinde";
        String contact = "9876543210";
        String country = "India";
        String state = "Maharashtra";
        String gender = "Female";

        // Fill personal details
        Thread.sleep(2000);
        WebElement firstNameField = driver.findElement(By.name("firstName"));
        firstNameField.clear();
        firstNameField.sendKeys(firstName);

        WebElement lastNameField = driver.findElement(By.name("lastName"));
        lastNameField.clear();
        lastNameField.sendKeys(lastName);

        // Select Gender
        if (gender.equalsIgnoreCase("Male")) {
            driver.findElement(By.xpath("//label[text()='Male']")).click();
        } else {
            driver.findElement(By.xpath("//label[text()='Female']")).click();
        }

        // Save button
        driver.findElement(By.xpath("//button[@type='submit']")).click();

        // 🔹 Write back to Excel
        ExcelUtils.setCellData("RegistrationData", 1, 0, firstName);
        ExcelUtils.setCellData("RegistrationData", 1, 1, lastName);
        ExcelUtils.setCellData("RegistrationData", 1, 2, contact);
        ExcelUtils.setCellData("RegistrationData", 1, 3, country);
        ExcelUtils.setCellData("RegistrationData", 1, 4, state);
        ExcelUtils.setCellData("RegistrationData", 1, 5, gender);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

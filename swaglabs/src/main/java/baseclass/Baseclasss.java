package baseclass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import genericUtility.ScreenshotUtility;

public class Baseclasss {

    protected WebDriver driver;
    protected Properties properties;

    @BeforeMethod(alwaysRun = true)
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser)
            throws IOException {

        FileInputStream fis = new FileInputStream(
                "src/main/resources/CommonData.properties");

        properties = new Properties();

        try {
            properties.load(fis);
        } finally {
            fis.close();
        }

        if (browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            driver = new FirefoxDriver();
        } else {
            throw new IllegalArgumentException(
                    "Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.get(properties.getProperty("url"));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {

        if (driver == null) {
            return;
        }

        try {
            String status;

            if (result.getStatus() == ITestResult.SUCCESS) {
                status = "PASS";
            } else if (result.getStatus() == ITestResult.FAILURE) {
                status = "FAIL";
            } else {
                status = "SKIP";
            }

            ScreenshotUtility.captureScreenshot(
                    driver,
                    result.getMethod().getMethodName(),
                    status);

        } catch (Exception e) {
            System.out.println(
                    "Screenshot error: " + e.getMessage());
        } finally {
            try {
                driver.quit();
            } catch (Exception e) {
                System.out.println(
                        "Browser already closed: " + e.getMessage());
            } finally {
                driver = null;
            }
        }
    }
}


package genericUtility;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ScreenshotUtility {

    public static void captureScreenshot(
            WebDriver driver, String testName, String status) {

        if (driver == null) {
            System.out.println("Screenshot skipped: WebDriver is null.");
            return;
        }

        try {
            File folder = new File("test-output/screenshots");
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String timestamp =
                    new SimpleDateFormat("yyyyMMdd_HHmmss_SSS")
                            .format(new Date());

            String safeTestName = testName.replaceAll("[^a-zA-Z0-9_-]", "_");

            File source = ((TakesScreenshot) driver)
                    .getScreenshotAs(OutputType.FILE);

            File destination = new File(folder,
                    safeTestName + "_" + status + "_" + timestamp + ".png");

            java.nio.file.Files.copy(
                    source.toPath(), destination.toPath());

            System.out.println("Screenshot saved: "
                    + destination.getAbsolutePath());

        } catch (IOException | RuntimeException e) {
            System.out.println("Screenshot capture failed: "
                    + e.getMessage());
        }
    }
}

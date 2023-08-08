package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Page Object encapsulates the WatchFilm page.
 */
public class WatchFilmPage {
    private final WebDriver driver;
    private WebElement playButton = null;

    // <input name="user_name" type="text" value="">
    private By playBy = By.name("video_screen");

    public WatchFilmPage(WebDriver driver) {
        this.driver = driver;

    }

    public void playVideo() throws InterruptedException {
        playButton = driver.findElement(playBy);
        waitSeconds(1);
        playButton.click();
    }

    public void waitSeconds(double seconds) throws InterruptedException {
        Thread.sleep((long) (seconds * 1000));
    }

    public double getVideoTime() {
        String secondsString = playButton.getAttribute("currentTime");
        return Double.parseDouble(secondsString);
    }
}
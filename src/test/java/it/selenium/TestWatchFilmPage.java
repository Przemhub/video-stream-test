package it.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pageobjects.WatchFilmPage;
/***
 * Tests login feature
 */
public class TestWatchFilmPage {
    private final WebDriver driver = new ChromeDriver();

    @Before
    public void setup() {
        driver.get("http://127.0.0.1:5173/film/Leonardo%20DiCaprio%20on%20Taxi%20Driver");
    }

    @Test
    public void testVideoDelayOnce() throws InterruptedException {
        playAndObserveVideoTime();
    }
    @Test
    public void testVideDelay10Times() throws InterruptedException {
        for(int i =0;i<10;i++){
            System.out.println("iteration " + i + " \n");
            playAndObserveVideoTime();
            System.out.println("---------------------------------------------------------");
        }
    }
    private void playAndObserveVideoTime() throws InterruptedException {
        WatchFilmPage page = new WatchFilmPage(driver);
        double waitTime = 5.0;
        page.playVideo();
        page.waitSeconds(waitTime + 0.4);
        double videoTime = page.getVideoTime();
        System.out.println("Wait time: " + waitTime);
        System.out.println("Video time: " + videoTime);
        System.out.println("Video time/Wait time ratio: " + videoTime/waitTime);
        System.out.println("Overall Delay in seconds: " + (waitTime-videoTime));
    }
    @After
    public void cleanUp() {
        driver.close();
    }

}

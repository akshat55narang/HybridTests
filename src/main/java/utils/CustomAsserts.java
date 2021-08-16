package utils;

import org.openqa.selenium.WebDriverException;

public class CustomAsserts {

    public static void assertEventually(int attempts, long pause, Runnable assertion) {
        for (int i=0; i<attempts; i++) {
            try {
                assertion.run();
                return;
            } catch (WebDriverException | AssertionError e) {
                System.out.println("Assertion attempt failed with exception " + e.getMessage());
            }
            try {
                Thread.sleep(pause);
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
        }
        assertion.run();
    }
}

package utils;

import org.openqa.selenium.WebElement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import static com.codeborne.selenide.Selenide.$$;

public class PageObjectUtils {

    private static final String EMAIL = "testmail%s@gmail.com";

    public static void selectRandomDropItem(List<WebElement> items) {
        $$(items).get(new Random().nextInt(items.size())).click();
    }

    public static String generateDOB() {
        Random random = new Random();
        int minDay = (int) LocalDate.of(1980, 1, 1).toEpochDay();
        int maxDay = (int) LocalDate.of(2000, 1, 1).toEpochDay();
        long randomDay = minDay + random.nextInt(maxDay - minDay);
        LocalDate randomBirthDate = LocalDate.ofEpochDay(randomDay);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        return formatter.format(randomBirthDate);
    }

    public static String generateEmail() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = formatter.format(now);
        String parsedDate = date.replaceAll("[\\s+:]","");
        return String.format(EMAIL, parsedDate);


    }

}

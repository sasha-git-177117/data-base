package utils;

import lombok.extern.slf4j.Slf4j;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class TestUtil {

    public static LocalDateTime parseMillisToLCD(long millis) {
        return Instant.ofEpochMilli(millis).atZone(ZoneId.systemDefault()).toLocalDateTime().withNano(0);
    }

    public static List<Integer> getTestsWithDuplicateDigitsInId(int countPairs) {
        log.info("Получение тестов с зеркальным id");
        List<Integer> test = new ArrayList<>();
        for (int i = 1; i<=countPairs; i++) {
            test.add(Integer.parseInt(Integer.toString(i).concat(Integer.toString(i))));
        }
        return test;
    }
}

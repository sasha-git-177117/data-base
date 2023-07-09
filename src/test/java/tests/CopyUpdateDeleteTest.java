package tests;

import consts.TestData;
import lombok.extern.slf4j.Slf4j;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import steps.Steps;
import utils.JsonUtil;
import utils.TestUtil;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CopyUpdateDeleteTest extends BaseTest{
    private static List<Integer> copiedTestId = new ArrayList<>();

    @BeforeMethod
    static void getTestsWithDuplicateDigitsInId() {
        log.info("Получение копий тестов с зеркальным id");
        List<Integer> testsWithDuplicateDigitsInId = TestUtil.getTestsWithDuplicateDigitsInId(Integer.parseInt(
                JsonUtil.getValueFromJson(TestData.TEST_DATA_PATH.label, TestData.COUNT_PAIRS.label)));
        copiedTestId = Steps.copyTests(testsWithDuplicateDigitsInId);
        Steps.updateAuthorTests(copiedTestId);
    }

    @Test
    static void updateTestsWithDuplicateDigitsInId() {
        log.info("Обновление копий тестов с зеркальным id");
        Assert.assertEquals(true,true,"true не равен true");
        List<Integer> updatedStatusTestsId = Steps.updateStatusTests(copiedTestId, ITestResult.SKIP);
        Steps.checkUpdateStatusTests(updatedStatusTestsId,ITestResult.SKIP);
    }

    @AfterMethod
    static void deleteTestsWithDuplicateDigitsInId(ITestResult result) {
        log.info("Удаление копий тестов с зеркальным id");
        List<Integer> deletedTestsId = Steps.deleteTests(copiedTestId);
        Steps.checkDeleteTests(deletedTestsId);
    }
}

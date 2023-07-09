package tests;

import lombok.extern.slf4j.Slf4j;
import model.TestInfo;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import repository.TestRepo;
import steps.Steps;

@Slf4j
public class AddTest extends BaseTest {
    @Test
    static void passedTest() {
        log.info("Добавление пройденного теста");
        Assert.assertEquals(true,true,"true не равен true");
    }

    @Test
    static void failedTest() {
        log.info("Добавление не пройденного теста");
        Assert.assertEquals(true,false,"true равен false");
    }

    @AfterMethod
    static void addResultInfo(ITestResult result) {
        log.info("Добавление данных теста");
        TestInfo addedTest = Steps.createTestWithTestData(result);
        Integer addedTestId = TestRepo.addTest(addedTest);

        Assert.assertNotNull(TestRepo.getTestById(addedTestId).getId(),"Запись с id=" + addedTestId + " не была добавлена");
    }

}

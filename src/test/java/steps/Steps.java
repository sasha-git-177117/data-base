package steps;

import consts.TestData;
import consts.TestENV;
import model.TestInfo;
import org.testng.Assert;
import org.testng.ITestResult;
import repository.AuthorRepo;
import repository.TestRepo;
import lombok.extern.slf4j.Slf4j;
import utils.JsonUtil;
import utils.TestUtil;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Steps {

    public static Integer copyAndGetIdCopiedTest(Integer id) {
        log.info("Копирование теста");
        TestInfo copiedTest = TestRepo.getTestById(id);
        return TestRepo.addTest(copiedTest);
    }

    public static List<Integer> copyTests(List<Integer> testsId) {
        List<Integer> copiedTests = new ArrayList<>();
        for (Integer id:testsId) {
            copiedTests.add(Steps.copyAndGetIdCopiedTest(id));
        }
        return copiedTests;
    }

    public static List<Integer> updateAuthorTests(List<Integer> testsId) {
        List<Integer> updatedTests = new ArrayList<>();
        for (Integer id: testsId) {
            updatedTests.add(Steps.updateAuthorAndGetIdTest(id, AuthorRepo.getAuthorByName(JsonUtil.getValueFromJson(
                    TestData.TEST_DATA_PATH.label, TestData.AUTHOR.label)).getId()));
        }
        return updatedTests;
    }

    public static Integer updateAuthorAndGetIdTest(Integer id, Integer authorId ) {
        log.info("Обновление данных author id");
        TestInfo updatedTest = TestRepo.getTestById(id);
        updatedTest.setAuthorId(authorId);
        return TestRepo.updateTest(updatedTest);
    }

    public static Integer updateStatusAndGetIdTest(Integer id, Integer statusId) {
        log.info("Обновление данных status id");
        TestInfo updatedTest = TestRepo.getTestById(id);
        updatedTest.setStatusId(statusId);
        return TestRepo.updateTest(updatedTest);
    }

    public static List<Integer> updateStatusTests(List<Integer> testsId, Integer statusId) {
        List<Integer> updatedTests = new ArrayList<>();
        for (Integer id: testsId) {
            updatedTests.add(Steps.updateStatusAndGetIdTest(id,statusId));
        }
        return updatedTests;
    }

    public static Integer deleteTestAndGetId(Integer id) {
        return TestRepo.deleteTestById(id);
    }

    public static List<Integer> deleteTests(List<Integer> testsId) {
        List<Integer> deletedTests = new ArrayList<>();
        for (Integer id: testsId) {
            deletedTests.add(Steps.deleteTestAndGetId(id));
        }
        return deletedTests;
    }

    public static TestInfo createTestWithTestData(ITestResult result) {
        Integer testResult = result.getStatus();
        LocalDateTime startTime = TestUtil.parseMillisToLCD(result.getStartMillis());
        LocalDateTime endTime =TestUtil.parseMillisToLCD(result.getEndMillis());
        String testClassName = result.getInstanceName();
        String testName = result.getName();
        String browser = JsonUtil.getValueFromJson(TestData.TEST_DATA_PATH.label, TestData.BROWSER_PATH_JSON.label);
        Integer projectId = Integer.parseInt(JsonUtil.getValueFromJson(TestData.TEST_DATA_PATH.label, TestData.PROJECT_ID.label));
        Integer sessionId = Integer.parseInt(JsonUtil.getValueFromJson(TestData.TEST_DATA_PATH.label, TestData.SESSION_ID.label));
        Integer authorId = AuthorRepo.getAuthorByName(JsonUtil.getValueFromJson(TestData.TEST_DATA_PATH.label,TestData.AUTHOR.label)).getId();
        String env = System.getenv(TestENV.USER_DOMAIN.label);

        return new TestInfo(testClassName, testResult,
                testName,projectId,sessionId,startTime,endTime,env,browser,authorId);
    }

    public static void checkUpdateStatusTests(List<Integer> updateStatusTestId, Integer newStatusId) {
        for (Integer id : updateStatusTestId) {
            Assert.assertEquals(TestRepo.getTestById(id).getStatusId(),newStatusId,"Статус теста в записи не равен новому статусу " +newStatusId);
        }
    }

    public static void checkDeleteTests(List<Integer> deletedTestId) {
        for (Integer id : deletedTestId) {
            Assert.assertNull(TestRepo.getTestById(id),"Запись с id=" + id + " не удалена");
        }
    }
}

package tests;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.*;
import util.DBConnectionUtil;

@Slf4j
public class BaseTest {

    @BeforeClass
    static void openConnection() {
        DBConnectionUtil.getConnection();
    }

    @AfterSuite
    static void close() {
        DBConnectionUtil.closeConnection();
    }
}

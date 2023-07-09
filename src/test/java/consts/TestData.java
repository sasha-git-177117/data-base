package consts;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TestData {
    TEST_DATA_PATH("src/test/resources/testData.json"),
    BROWSER_PATH_JSON("browser"),
    PROJECT_ID("projectId"),
    SESSION_ID("sessionId"),
    AUTHOR("author"),
    COUNT_PAIRS("countPairs");

    public final String label;
}

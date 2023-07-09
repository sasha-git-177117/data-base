package repository;

import model.TestInfo;
import lombok.extern.slf4j.Slf4j;
import util.DBUtil;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
public final class TestRepo {

    private static final String ADD_QUERY = "INSERT INTO test (name, status_id, method_name, project_id, session_id," +
            " start_time, end_time, env, browser, author_id) " +
            "VALUES ('%s', %d, '%s', %d, %d, '%s', '%s', '%s', '%s', %d )";
    private static final String GET_BY_ID_QUERY = "select * from test where id =%s";
    private static final String UPDATE_QUERY = "UPDATE test SET name='%s', status_id=%d" +
            ", method_name='%s', project_id=%d, session_id=%d" +
            ", start_time='%s', end_time='%s', env='%s'" +
            ", browser ='%s',author_id=%d where id=%s";
    private static final String DELETE_BY_ID_QUERY = "DELETE FROM test WHERE id=%s";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static Integer addTest(TestInfo testInfo) {
        String sql = String.format(ADD_QUERY,
                testInfo.getName(),
                testInfo.getStatusId(),
                testInfo.getMethodName(),
                testInfo.getProjectId(),
                testInfo.getSessionId(),
                testInfo.getStartTime(),
                testInfo.getEndTime(),
                testInfo.getEnv(),
                testInfo.getBrowser(),
                testInfo.getAuthorId());
        return DBUtil.executeInsert(sql);
    }

    public static TestInfo getTestById(Integer id) {
        TestInfo testInfo = new TestInfo();
        String sql = String.format(GET_BY_ID_QUERY,id.toString());
        if(DBUtil.executeQuery(sql).isEmpty()) return null;
        Map<String, String> result = DBUtil.executeQuery(sql).get(0);

        testInfo.setId(Integer.parseInt(result.get("id")));
        testInfo.setName(result.get("name"));
        testInfo.setStatusId(result.get("status_id") != null ? Integer.parseInt(result.get("status_id")):null);
        testInfo.setProjectId(Integer.parseInt(result.get("project_id")));
        testInfo.setSessionId(Integer.parseInt(result.get("session_id")));
        testInfo.setMethodName(result.get("method_name"));
        testInfo.setStartTime(result.get("start_time") != null ? LocalDateTime.parse(result.get("start_time"), DATE_FORMATTER) : null);
        testInfo.setEndTime(result.get("end_time") != null ? LocalDateTime.parse(result.get("end_time"), DATE_FORMATTER) : null);
        testInfo.setBrowser(result.get("browser") != null ? result.get("browser") : null);
        testInfo.setEnv(result.get("env"));
        testInfo.setAuthorId(result.get("author_id") != null ? Integer.parseInt(result.get("author_id")) : null);

        return testInfo;
    }

    public static Integer updateTest(TestInfo testInfo) {
        String sql = String.format(UPDATE_QUERY,
                testInfo.getName(),
                testInfo.getStatusId(),
                testInfo.getMethodName(),
                testInfo.getProjectId(),
                testInfo.getSessionId(),
                testInfo.getStartTime(),
                testInfo.getEndTime(),
                testInfo.getEnv(),
                testInfo.getBrowser(),
                testInfo.getAuthorId(),
                testInfo.getId());

        return DBUtil.executeUpdate(sql,testInfo.getId());
    }

    public static Integer deleteTestById(Integer id) {
        String sql = String.format(DELETE_BY_ID_QUERY,id);

        return DBUtil.executeUpdate(sql,id);
    }
}

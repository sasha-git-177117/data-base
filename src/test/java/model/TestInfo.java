package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TestInfo {
    private Integer id;
    private String name;
    private Integer statusId;
    private String methodName;
    private Integer projectId;
    private Integer sessionId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String env;
    private String browser;
    private Integer authorId;

    public TestInfo(String name, Integer statusId, String methodName, Integer projectId, Integer sessionId, LocalDateTime startTime, LocalDateTime endTime, String env, String browser, Integer authorId) {
        this.name = name;
        this.statusId = statusId;
        this.methodName = methodName;
        this.projectId = projectId;
        this.sessionId = sessionId;
        this.startTime = startTime;
        this.endTime = endTime;
        this.env = env;
        this.browser = browser;
        this.authorId = authorId;
    }
}

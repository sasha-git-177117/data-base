package repository;

import model.Status;
import lombok.extern.slf4j.Slf4j;
import util.DBUtil;
import java.util.Map;

@Slf4j
public final class StatusRepo {
    private static final String SQL_GET_BY_STATUS_NAME = "select * from status where name='%s'";

    public static Status getStatusByNameStatus(String statusName) {
        Status status = new Status();
        String sql = String.format(SQL_GET_BY_STATUS_NAME,statusName);
        if(DBUtil.executeQuery(sql).isEmpty()) return null;
        Map<String,String> result = DBUtil.executeQuery(sql).get(0);

        status.setId(Integer.parseInt(result.get("id")));
        status.setName(result.get("name"));

        return status;
    }

}


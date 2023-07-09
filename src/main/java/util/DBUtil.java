package util;

import lombok.extern.slf4j.Slf4j;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class DBUtil {

    public static Integer executeUpdate(String sql,Integer id) {
        DBConnectionUtil.getStatementExecuteUpdate(sql);
        return id;
    }

    public static Integer executeInsert(String sql) {
        Integer id = null;
        try {
            ResultSet generatedKeys = DBConnectionUtil.getStatementExecuteUpdate(sql).getGeneratedKeys();
                if (generatedKeys.next()) {
                    id = Integer.parseInt(String.valueOf(generatedKeys.getLong(1)));
                    generatedKeys.close();
                }

        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return id;
    }

    public static List<Map<String, String>> executeQuery( String sql) {
        List<Map<String, String>> result = new ArrayList<>();
        try {
            ResultSet rs = DBConnectionUtil.getStatementExecuteQuery(sql).getResultSet();
            while (rs.next()) {
                Map<String, String> resMap = new HashMap<>();
                for (int i = 1; i <= rs.getMetaData().getColumnCount(); i++) {
                    resMap.put(rs.getMetaData().getColumnName(i), rs.getString(i));
                }
                result.add(resMap);
            }
        } catch (SQLException e) {
            log.error(e.getMessage());
        }
        return result;
    }
}

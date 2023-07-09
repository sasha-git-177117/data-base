package repository;

import model.Author;
import lombok.extern.slf4j.Slf4j;
import util.DBUtil;
import java.util.Map;

@Slf4j
public final class AuthorRepo {
    private static final String GET_BY_NAME_QUERY = "select * from author where name ='%s'";

    public static Author getAuthorByName(String name) {
        Author author = new Author();
        String sql = String.format(GET_BY_NAME_QUERY, name);
        if(DBUtil.executeQuery(sql).isEmpty()) return null;
        Map<String,String> result = DBUtil.executeQuery(sql).get(0);

        author.setId(Integer.parseInt(result.get("id")));
        author.setName(result.get("name"));
        author.setEmail(result.get("email"));
        author.setLogin(result.get("login"));

        return author;
    }
}

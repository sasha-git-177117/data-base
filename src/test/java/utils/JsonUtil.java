package utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
public class JsonUtil {
    public static String getValueFromJson(String file,String value) {
        String element = null;
        try {
            Gson gson = new Gson();
            Reader reader = Files.newBufferedReader(Paths.get(file));
            element = gson.fromJson(reader, JsonObject.class).get(value).getAsString();
            reader.close();

        } catch (IOException ex) {
            log.error(ex.getMessage());
        }
        return element;
    }
}

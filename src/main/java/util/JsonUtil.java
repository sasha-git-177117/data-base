package util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import java.io.File;
import java.io.IOException;

@Slf4j
public class JsonUtil {

    public static  <T> T createModelFromJSON(String file, Class<T> modelClass) {
        try {
            return new ObjectMapper().getFactory().createParser(new File(file)).readValueAs(modelClass);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
}

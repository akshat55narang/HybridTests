package utils;

import pojo.request.FileAttributes;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static rest.builders.RestFactory.getFileBuilder;

public class Utils {

    private static final String CONTENT_FILES_PATH = "src/main/resources/";

    public static String getParameter(String variableName, String defaultValue) {
        String env = System.getenv(variableName);
        if (env != null) {
            defaultValue = env;
        }
        String result = System.getProperty(variableName, defaultValue);
        if (isBlank(result)) {
            result = defaultValue;
        }

        return result;
    }

    public static String getContentFromFile(String fileName) {
        File contentFile = new File(CONTENT_FILES_PATH + fileName);
        StringBuilder buider = new StringBuilder();
        try (BufferedReader bufferedInputStream = new BufferedReader(new FileReader(contentFile))) {
            bufferedInputStream.lines().forEach(line -> buider.append(line));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return buider.toString();
    }

    public static String getDefaultFileContent() {
        return getContentFromFile("hello_world.rb");
    }

    public static Map<String, FileAttributes> generateDefaultFileWithContent() {
        String defaultFileContent = getDefaultFileContent();
        Map<String, FileAttributes> fileAttributesMap = new HashMap<>();
        fileAttributesMap.put("default_creategist_request.txt", getFileBuilder().withContent(defaultFileContent).build());
        return fileAttributesMap;
    }

    public static Map<String, FileAttributes> generateFileWithDefaultContent(String fileName) {
        String defaultFileContent = getDefaultFileContent();
        Map<String, FileAttributes> fileAttributesMap = new HashMap<>();
        fileAttributesMap.put(fileName, getFileBuilder().withContent(defaultFileContent).build());
        return fileAttributesMap;
    }

}

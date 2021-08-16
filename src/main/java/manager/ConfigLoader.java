package manager;

import java.io.*;
import java.util.Properties;

import static utils.Utils.getParameter;

public class ConfigLoader {
    private static final String DEFAULT_WEB_URL = "web.url";
    private static final String DEFAULT_API_URI = "api.uri";
    private static final String IS_BROWSER_SHOWN = "is.browser.shown";
    private static final String GITHUB_TOKEN = "github.token";
    private static final String DEFAULT_USERNAME = "username";
    private static final String DEFAULT_PASSWORD = "password";
    private static final String DEFAULT_LOGIN_NAME = "login.name";


    public static Properties loadProperties() {
        Properties properties = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("default.properties"))) {
            properties = new Properties();
            properties.load(bufferedReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }

    public static String getConfig(String variableName) {
        if (!loadProperties().containsKey(variableName)) {
            throw new RuntimeException("Configuration option " + variableName + " not found");
        }
        return loadProperties().getProperty(variableName);
    }

    public static String getDefaultWebUrl() {
        return getParameter("defaultWebUrl", getConfig(DEFAULT_WEB_URL));
    }

    public static String getDefaultApiUri() {
        return getParameter("defaultApiUri", getConfig(DEFAULT_API_URI));
    }

    public static String isBrowserShown() {
        return getParameter("isBrowserShown", getConfig(IS_BROWSER_SHOWN));
    }

    public static String getGithubToken() {
        return getParameter("githubToken", getConfig(GITHUB_TOKEN));
    }

    public static String getDefaultUsername() {
        return getParameter("defaultUserName", getConfig(DEFAULT_USERNAME));
    }

    public static String getDefaultPassword() {
        return getParameter("defaultPassword", getConfig(DEFAULT_PASSWORD));
    }

    public static String getDefaultLoginName() {
        return getParameter("defaultLoginName", getConfig(DEFAULT_LOGIN_NAME));
    }
}

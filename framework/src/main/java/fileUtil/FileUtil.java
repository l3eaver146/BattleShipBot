package fileUtil;

import browserFactory.config.ApplicationConfig;
import browserFactory.config.FrameworkConfig;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import logger.Logger;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.Proxy;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class FileUtil {
    private static String pathToProjectFolder;

    private static final String LAST_PART_OF_PATH_TO_BROWSER_CONFIG_FILE = "/framework/src/main/resources/browserConfig.json";
    private static final String SYSTEM_PROPERTY = "user.dir";
    private static final int BEGIN_OF_THE_STRING = 0;
    private static final int EXTRA_CHARACTERS_IN_PATH = 12;
    private static final String LAST_PART_OF_PATH_TO_DOWNLOAD_DIRECTORY = "\\framework\\src\\main\\resources\\";

    private static String getPathToConfig() {
        return pathToProjectFolder;
    }

    public static String getPathToDownloadDirectory() {
        return FileUtil.getPathToProjFolder() + LAST_PART_OF_PATH_TO_DOWNLOAD_DIRECTORY;
    }

    public static String getPathToProjFolder() {
        pathToProjectFolder = System.getProperty(SYSTEM_PROPERTY);
        return pathToProjectFolder.substring(BEGIN_OF_THE_STRING, pathToProjectFolder.length() - EXTRA_CHARACTERS_IN_PATH);
    }

    public static FrameworkConfig getFrameworkConfig() {
        FrameworkConfig config = null;
        String path = getPathToProjFolder() + LAST_PART_OF_PATH_TO_BROWSER_CONFIG_FILE;
        try {
            Object obj = new JsonParser().parse(new FileReader(path));
            JsonObject jsonObject = (JsonObject) obj;
            var frameworkConfig = jsonObject.get("FrameworkConfig");
            config = new Gson().fromJson(frameworkConfig, FrameworkConfig.class);
            return config;
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }

    public static ApplicationConfig getApplicationConfig() {
        ApplicationConfig config = null;
        String path = getPathToProjFolder() + LAST_PART_OF_PATH_TO_BROWSER_CONFIG_FILE;
        try {
            Object obj = new JsonParser().parse(new FileReader(path));
            JsonObject jsonObject = (JsonObject) obj;
            var frameworkConfig = jsonObject.get("ApplicationConfig");
            config = new Gson().fromJson(frameworkConfig, ApplicationConfig.class);
            return config;
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }

    public static boolean isFileExists(String fileName) {
        Logger.info("Trying to find " + fileName + " in " + LAST_PART_OF_PATH_TO_DOWNLOAD_DIRECTORY);
        return new File(getPathToProjFolder() + LAST_PART_OF_PATH_TO_DOWNLOAD_DIRECTORY, fileName).exists();
    }
}
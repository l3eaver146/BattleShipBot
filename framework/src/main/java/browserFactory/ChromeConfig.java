package browserFactory;

import fileUtil.FileUtil;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;

public class ChromeConfig {
    public ChromeOptions getChromeOptions() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions= setPreferences(chromeOptions);
        chromeOptions = setCapabilities(chromeOptions);
        chromeOptions = setArguments(chromeOptions);
        return chromeOptions;
    }

    private ChromeOptions setCapabilities(ChromeOptions chromeConfig) {
        chromeConfig.setCapability("intl.accept_languages", FileUtil.getApplicationConfig().getLocalization());
        return chromeConfig;
    }

    private ChromeOptions setPreferences(ChromeOptions chromeConfig) {
        String fullPath = FileUtil.getPathToDownloadDirectory();
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("download.default_directory", fullPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("download.extensions_to_open", "application/xml");
        prefs.put("safebrowsing.enabled", true);
        chromeConfig.setExperimentalOption("prefs", prefs);
        return chromeConfig;
    }

    private ChromeOptions setArguments(ChromeOptions chromeConfig) {
        chromeConfig.addArguments("--safebrowsing-disable-download-protection");
        chromeConfig.addArguments("safebrowsing-disable-extension-blacklist");
        return chromeConfig;
    }
}

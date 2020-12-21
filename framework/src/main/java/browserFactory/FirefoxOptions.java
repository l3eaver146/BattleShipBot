package browserFactory;

import fileUtil.FileUtil;

public class FirefoxOptions {
    public org.openqa.selenium.firefox.FirefoxOptions getFirefoxOptions() {
        org.openqa.selenium.firefox.FirefoxOptions profile = new org.openqa.selenium.firefox.FirefoxOptions();
        return setCapabilities(profile);
    }

    private org.openqa.selenium.firefox.FirefoxOptions setCapabilities(org.openqa.selenium.firefox.FirefoxOptions profile) {
        profile.setCapability("browser.download.folderList", 2);
        profile.setCapability("browser.helperApps.neverAsk.saveToDisk", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        profile.setCapability("intl.accept_languages", FileUtil.getApplicationConfig().getLocalization());
        return profile;
    }
}

package browserFactory;

import logger.Logger;

public class OptionsFactory {
    public <T> T setOptions(Browsers browsers) {
        switch (browsers) {
            case CHROME -> {
                return (T) new ChromeConfig().getChromeOptions();
            }
            case FIREFOX -> {
                return (T) new FirefoxOptions().getFirefoxOptions();
            }
            default -> {
                Logger.error("Options settings failed");
                throw new RuntimeException();
            }
        }
    }
}

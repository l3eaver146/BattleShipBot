package browserFactory.config;

import lombok.Getter;

@Getter
public class FrameworkConfig {
    private String browser;
    private String pathToResources;
    private int waitingTime;
}

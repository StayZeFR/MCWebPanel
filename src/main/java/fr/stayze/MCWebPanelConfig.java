package fr.stayze;

public enum MCWebPanelConfig {

    PL_NAME("MCWebPanel");

    private final String config;

    MCWebPanelConfig(String config) {
        this.config = config;
    }

    public String getConfig() {
        return this.config;
    }

}

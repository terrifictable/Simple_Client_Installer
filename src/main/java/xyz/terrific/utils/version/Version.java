package xyz.terrific.utils.version;

public class Version {
    private String name;
    private boolean installed;

    /**
     * Version... thingy
     * @param name name of client / version
     * @param installed if client / version is installed
     */
    public Version(String name, boolean installed) {
        this.name = name;
        this.installed = installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
    }

    public boolean isInstalled() {
        return installed;
    }

    public String getName() {
        return name;
    }
}

package haw.vs.common;

public class PlayerConfigData {
    private int resolutionWidth;

    private int resolutionHeight;

    public PlayerConfigData() {
    }

    public PlayerConfigData(int resolutionWidth, int resolutionHeight) {
        this.resolutionWidth = resolutionWidth;
        this.resolutionHeight = resolutionHeight;
    }

    public int getResolutionWidth() {
        return resolutionWidth;
    }

    public void setResolutionWidth(int resolutionWidth) {
        this.resolutionWidth = resolutionWidth;
    }

    public int getResolutionHeight() {
        return resolutionHeight;
    }

    public void setResolutionHeight(int resolutionHeight) {
        this.resolutionHeight = resolutionHeight;
    }
    public PlayerConfigData copy() {
        return new PlayerConfigData(this.resolutionWidth, this.resolutionHeight);
    }
}

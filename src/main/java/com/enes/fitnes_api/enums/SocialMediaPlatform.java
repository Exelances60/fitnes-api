package com.enes.fitnes_api.enums;

public enum SocialMediaPlatform {
    FACEBOOK("Facebook"), INSTAGRAM("Instagram"), TWITTER("Twitter"), LINKEDIN("LinkedIn");

    private String platform;

    SocialMediaPlatform(String platform) {
        this.platform = platform;
    }

    public String getPlatform() {
        return platform;
    }

    public static SocialMediaPlatform fromName(String name) {
        for (SocialMediaPlatform platform : values()) {
            if (platform.getPlatform().equalsIgnoreCase(name)) {
                return platform;
            }
        }
        throw new IllegalArgumentException("Unknown platform: " + name);
    }
}

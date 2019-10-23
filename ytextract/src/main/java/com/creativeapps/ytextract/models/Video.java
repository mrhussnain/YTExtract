package com.creativeapps.ytextract.models;

public class Video {
    String SizeText, Quality, DownloadUrl, Extension, FrameRate, Tag;

    public Video(String sizeText, String quality, String downloadUrl, String extension, String frameRate, String tag) {
        SizeText = sizeText;
        Quality = quality;
        DownloadUrl = downloadUrl;
        Extension = extension;
        FrameRate = frameRate;
        Tag = tag;
    }

    public String getSizeText() {
        return SizeText;
    }

    public String getQuality() {
        return Quality;
    }

    public String getDownloadUrl() {
        return DownloadUrl;
    }

    public String getExtension() {
        return Extension;
    }

    public String getFrameRate() {
        return FrameRate;
    }

    public String getTag() {
        return Tag;
    }
}

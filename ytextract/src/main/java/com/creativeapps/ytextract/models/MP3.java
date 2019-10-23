package com.creativeapps.ytextract.models;

public class MP3 {
    String DownloadUrl,BitRate,SizeText;

    public MP3(String downloadUrl, String bitRate, String sizeText) {
        DownloadUrl = downloadUrl;
        BitRate = bitRate;
        SizeText = sizeText;
    }

    public String getDownloadUrl() {
        return DownloadUrl;
    }

    public String getBitRate() {
        return BitRate;
    }

    public String getSizeText() {
        return SizeText;
    }
}

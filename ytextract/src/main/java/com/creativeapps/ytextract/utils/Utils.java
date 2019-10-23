package com.creativeapps.ytextract.utils;

public class Utils {
    public static String getVideoID(String youtube_url) {
        if (youtube_url.contains("youtube.com")) {
            return youtube_url.split("=")[1];
        }else if (youtube_url.contains("youtu.be")) {
            return youtube_url.replace("https://youtu.be/","");
        }
        return null;
    }

}
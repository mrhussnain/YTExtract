package com.creativeapps.ytextract.models;

import java.util.ArrayList;

public class YTModel {

    String Title,VideoID,ThumbImage,Duration;
    ArrayList<MP3> MP3;
    ArrayList<Video> Video;

    public YTModel(String title, String videoID, String thumbImage, String duration, ArrayList<com.creativeapps.ytextract.models.MP3> MP3, ArrayList<com.creativeapps.ytextract.models.Video> video) {
        Title = title;
        VideoID = videoID;
        ThumbImage = thumbImage;
        Duration = duration;
        this.MP3 = MP3;
        Video = video;
    }

    public String getTitle() {
        return Title;
    }

    public String getVideoID() {
        return VideoID;
    }

    public String getThumbImage() {
        return ThumbImage;
    }

    public String getDuration() {
        return Duration;
    }

    public ArrayList<com.creativeapps.ytextract.models.MP3> getMP3() {
        return MP3;
    }

    public ArrayList<com.creativeapps.ytextract.models.Video> getVideo() {
        return Video;
    }
}

package com.creativeapps.ytextract;

import com.creativeapps.ytextract.models.MP3;
import com.creativeapps.ytextract.models.Video;
import com.creativeapps.ytextract.models.YTModel;
import com.creativeapps.ytextract.utils.HttpHandler;
import com.creativeapps.ytextract.utils.Utils;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class YTExtract {

    String key;
    public YTExtract(String apikey) {
        key = apikey;
    }

    public YTModel Extract(String YouTubeUrl) {
        String videoID = Utils.getVideoID(YouTubeUrl);
        ArrayList<MP3> mp3 = new ArrayList<>();
        ArrayList<Video> video = new ArrayList<>();

        String[] heads = new String[] { "x-rapidapi-host:ytextract.p.rapidapi.com",
                "x-rapidapi-key:"+key };

        HttpHandler handler = new HttpHandler(heads);
        String mp3json = handler.makeServiceCall(endPoint+"/@api/json/mp3/"+videoID);
        String videojson = handler.makeServiceCall(endPoint+"/@api/json/mergedstreams/"+videoID);

        String title,imageurl,duration;
        try {
            JSONObject mp3obj = new JSONObject(mp3json);
            JSONObject videoobj = new JSONObject(videojson);

            title = mp3obj.getString("vidTitle");
            imageurl = mp3obj.getString("vidThumb");
            duration = mp3obj.getString("duration");

            JSONObject array = mp3obj.getJSONObject("vidInfo");
            Iterator<String> keys = array.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                if (array.get(key) instanceof JSONObject) {
                    JSONObject obj = (JSONObject) array.get(key);

                    mp3.add(new MP3("https:"+ obj.getString("dloadUrl"),
                            obj.getString("bitrate"),obj.getString("mp3size")));
                }
            }

            array = videoobj.getJSONObject("vidInfo");
            keys = array.keys();

            while(keys.hasNext()) {
                String key = keys.next();
                if (array.get(key) instanceof JSONObject) {
                    JSONObject obj = (JSONObject) array.get(key);

                    video.add(new Video(obj.getString("rSize"),
                            obj.getString("quality"),
                            "https:"+ obj.getString("dloadUrl"),
                            obj.getString("ftype"),
                            obj.getString("framerate"),
                            obj.getString("itag")));
                }
            }

            return new YTModel(title,videoID,imageurl,duration,mp3,video);

        }catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}

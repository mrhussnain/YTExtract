package com.creativeapps.ytextract;

import android.os.AsyncTask;

import com.creativeapps.ytextract.models.MP3;
import com.creativeapps.ytextract.models.Video;
import com.creativeapps.ytextract.models.YTModel;
import com.creativeapps.ytextract.utils.HttpHandler;
import com.creativeapps.ytextract.utils.Utils;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public abstract class YTExtract extends AsyncTask<String,Void,Void> implements APISettings {

    String key;
    public YTExtract(String apikey) {
        key = apikey;
    }
    YTModel model;

    public abstract void onExtractionComplete(YTModel model);

    public void extract(String YouTubeUrl) {
        this.execute(YouTubeUrl);
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        onExtractionComplete(model);
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(String... strings) {
        String videoID = Utils.getVideoID(strings[0]);
        ArrayList<MP3> mp3 = new ArrayList<>();
        ArrayList<Video> video = new ArrayList<>();

        try {
            HttpResponse<String> response = Unirest.get("https://ytextract.p.rapidapi.com/@api/json/mp3/"+videoID)
                    .header("x-rapidapi-host", "ytextract.p.rapidapi.com")
                    .header("x-rapidapi-key", key)
                    .asString();
            String mp3json = response.getBody();

            response = Unirest.get("https://ytextract.p.rapidapi.com/@api/json/mergedstreams/"+videoID)
                    .header("x-rapidapi-host", "ytextract.p.rapidapi.com")
                    .header("x-rapidapi-key", key)
                    .asString();

            String videojson = response.getBody();

            String title,imageurl,duration;
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

            model = new YTModel(title,videoID,imageurl,duration,mp3,video);
        } catch (UnirestException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

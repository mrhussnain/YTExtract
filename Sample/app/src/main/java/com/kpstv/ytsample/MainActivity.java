package com.kpstv.ytsample;

/**
* Use of Mr Hussnain's YTExtract API..
*
* Created by KP (KaustubhPatange) on 22nd October 2019...
*
*/


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;


import com.creativeapps.ytextract.YTExtract;
import com.creativeapps.ytextract.models.YTModel;

import tcking.github.com.giraffeplayer2.GiraffePlayer;
import tcking.github.com.giraffeplayer2.VideoInfo;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    EditText editText;
    ProgressBar bar;

    static String API_KEY = "RAPID_API_KEY";

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting all views...

        editText = findViewById(R.id.edittext);
        startButton = findViewById(R.id.newbutton);
        bar = findViewById(R.id.progressBar);

        // Onclick listener for button...
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Executing API...

                String ytUrl = editText.getText().toString();

                new YTExtract(API_KEY){

                    @Override
                    protected void onPreExecute() {
                        bar.setVisibility(View.VISIBLE);
                        super.onPreExecute();
                    }

                    @Override
                    public void onExtractionComplete(YTModel model) {
                        bar.setVisibility(View.VISIBLE);

                        // Use the model data as you like for eg...
                        // I am hooking up a video player with it...

                        String videoUrl = model.getVideo().get(0).getDownloadUrl();
                        GiraffePlayer.play(MainActivity.this, new VideoInfo(videoUrl));
                    }
                }.extract(ytUrl);

            }

        });



    }
}

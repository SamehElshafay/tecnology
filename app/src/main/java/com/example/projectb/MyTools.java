package com.example.projectb;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Locale;

public class MyTools {
    private ArrayList<String> Activty ;
    private Spinner ACT ;

    MyTools(){}

    MyTools(Spinner act , ArrayList<String> activt){
        ACT = act ;
        Activty = activt;
    }

    public static TextToSpeech SelectLanguage(TextToSpeech Conv , Context THIS){
        return new TextToSpeech (THIS , new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS){
                    Conv.setLanguage(Locale.ENGLISH);
                }
                else{
                    System.out.println("Initialization failed");
                }
            }
        });
    }
    public void Spinner(Context THIS){
        ArrayAdapter<String> AD = new ArrayAdapter<String>(THIS , R.layout.custom_spinner , Activty);
        AD.setDropDownViewResource(android.R.layout.simple_spinner_item);
        AD.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        ACT.setAdapter(AD);
    }
}

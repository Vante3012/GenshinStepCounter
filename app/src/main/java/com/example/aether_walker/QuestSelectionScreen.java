package com.example.aether_walker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.content.Intent;
import android.health.connect.datatypes.StepsRecord;
import android.media.Image;
import android.os.Bundle;
import android.animation.ValueAnimator;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.PopupWindow;
import android.widget.GridLayout;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class QuestSelectionScreen extends AppCompatActivity {

    //Declare Variables
    public Intent ActiveQuestScreen;
    private TextView Quest_Select_Name_Txt,Quest_Select_Steps_Needed;
    private ImageView Quest_Select_Ico;
    private CardView selected_quest, selected_next_quest, selected_previous_quest;

    private int questSelected;

    //Declare Methods

    //Method to set the quest as user scrolls through
    String setQuestShown(){

        //PULL DATA FROM DB INTO VIEWS BELOW AND VARIABLES

        //questSelected = questIDFROM DB or something idk how to pull from db yet

        Quest_Select_Name_Txt.setText("Quest Of Berries");
        Quest_Select_Steps_Needed.setText("10steps");
        Quest_Select_Ico.setImageDrawable(getDrawable(R.drawable.shop_ico));

        String Logstring ="Quest Updated";
        return Logstring;
    }

    //onCreate Method below
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_selection_screen);

        //Initialize Views & add Related Methods:

        //Initialize Intent
        ActiveQuestScreen = new Intent(this, ActiveQuestScreen.class);

        //Initialized text views
        Quest_Select_Name_Txt = findViewById(R.id.Quest_Select_Name_Txt);
        Quest_Select_Steps_Needed = findViewById(R.id.Quest_Select_Steps_Needed);

        //Initialized image views
        Quest_Select_Ico  = findViewById(R.id.Quest_Select_Ico);

        //Initialize card views
        selected_quest = findViewById(R.id.selected_quest);
        selected_next_quest = findViewById(R.id.next_quest);
        selected_previous_quest = findViewById(R.id.previous_quest);

        //Calls "setQuestShown" method and logs it
        Log.i("SETQUEST", setQuestShown());

        //Below methods are for selecting which quest the user wants/has
        selected_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuestSelectionScreen.this.startActivity(ActiveQuestScreen);
            }
        });

        selected_next_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        selected_previous_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
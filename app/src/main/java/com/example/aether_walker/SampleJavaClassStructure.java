package com.example.aether_walker;

//Add imports
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class SampleJavaClassStructure extends AppCompatActivity {

    //Declare Variables -Examples below-
    private TextView Quest_Select_Name_Txt,Quest_Select_Steps_Needed;
    private ImageView Quest_Select_Ico;
    private CardView selected_quest, next_quest, previous_quest;

    //Declare Methods

    //onCreate Method below
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_selection_screen);

        //Initialize Views & add Related Methods:


    }
}
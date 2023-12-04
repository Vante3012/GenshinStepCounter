package com.example.aether_walker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Main_Menu extends AppCompatActivity {

    //Declare Variables

    public static SQLiteDatabase db;
    DBHelper myDBhelper;
    private Calendar calendar;
    private Intent QuestSelectionScreen;
    private ValueAnimator userTotalStepsAnim, userTotalMoriaAnim, userTotalCrystalAnim, userTotalXPAnim;
    private ImageView character_img, bckrnd_Img_stepsTaken, step_Img_Progress_Bar, step_Img_Progress_Bar_Overlay;
    private CardView daily_chest,user_quest,user_inventory,user_shop;
    private TextView total_Moria_Tv, total_Prime_Gems_Tv, daily_steps_total_Tv, total_user_Xp_Tv;
    public int userTotalSteps, userTotalMoria, userTotalCrystal, userTotalXp, currentStep;

    //Declare Method Constructors

    //This method runs the counter animation for userTotalSteps
    public String runTotalStepCounterAnim() {

        //Animator initializer
        userTotalStepsAnim = ValueAnimator.ofInt(0, userTotalSteps);
        userTotalStepsAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator.ofInt(0, currentStep);
                valueAnimator.setDuration(2000);
                daily_steps_total_Tv.setText(userTotalStepsAnim.getAnimatedValue().toString());
            }
        });
        userTotalStepsAnim.start();

        String LogString ="Updated Daily Steps | "+userTotalStepsAnim.getAnimatedValue().toString();
        return LogString;
    }

    //This method runs the counter animation for runTotalMoriaAnim
    public String runTotalMoriaAnim() {

        //Animator initializer
        userTotalMoriaAnim = ValueAnimator.ofInt(0, userTotalMoria);
        userTotalMoriaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator.ofInt(0, userTotalMoria);
                valueAnimator.setDuration(1530);
                total_Moria_Tv.setText(userTotalMoriaAnim.getAnimatedValue().toString());
            }
        });
        userTotalMoriaAnim.start();

        String LogString ="Updated User Moria | "+ userTotalMoriaAnim.getAnimatedValue().toString();
        return LogString;
    }

    //This method runs the counter animation for runTotalCrystalAnim
    public String runTotalCrystalAnim() {

        //Animator initializer
        userTotalCrystalAnim = ValueAnimator.ofInt(0, userTotalCrystal);
        userTotalCrystalAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator.ofInt(0, userTotalCrystal);
                valueAnimator.setDuration(1690);
                total_Prime_Gems_Tv.setText(userTotalCrystalAnim.getAnimatedValue().toString());
            }
        });
        userTotalCrystalAnim.start();

        String LogString ="Updated User Crystals | "+userTotalCrystalAnim.getAnimatedValue().toString();
        return LogString;
    }

    //This method runs the counter animation for runTotalMoriaAnim
    public String runTotalXPAnim() {

        //Animator initializer
        userTotalXPAnim = ValueAnimator.ofInt(0, userTotalXp);
        userTotalXPAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator.ofInt(0, userTotalXp);
                valueAnimator.setDuration(1200);
                total_user_Xp_Tv.setText("Lv."+userTotalXPAnim.getAnimatedValue().toString());
            }
        });
        userTotalXPAnim.start();

        String LogString ="Updated User Moria | "+ userTotalXPAnim.getAnimatedValue().toString();
        return LogString;
    }

    //method below checks if the user opened the daily chest in the last 12 hours
    /*
    boolean user_opened_daily_chest(Date inpt_Today){

        //Call DB and get STR of date to check if the user opened the chest a day before.
        //If not, set new open time to today in DB via STR.



        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date strDate = sdf.parse(STRINGDATEDATAFROMDB);
        if (inpt_Today.after(strDate)) {
            return true;
        }else{
            return false;
        }
    }
    */


    //OnCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Initialize Objects and Methods Below

        //Initialize DbHelper and db for this activity
        DBHelper myDbHelper = new DBHelper(this);
        try {
            myDbHelper.createDataBase();
        } catch (IOException ice) {
            throw new Error("Unable to create database");
        }
        try {
            myDbHelper.openDataBase();
        } catch (SQLException sqle) {
        }
        db = myDbHelper.getWritableDatabase();

        //NOTE:
        //Temp Values to be pulled from DB
        userTotalSteps = 14750;
        userTotalMoria = 12500;
        userTotalCrystal = 37;
        userTotalXp = 48;

        //Initialize the last date the user logged in





        //Temp Values to be pulled from DB ABOVE ------- CHANGE WITH DB Implementation!

        //Initialize calander for current date/time
        calendar = Calendar.getInstance();

        Date Today = new Date();

        //Initialize Intent
        QuestSelectionScreen = new Intent(this, QuestSelectionScreen.class);

        //Initialize Card Views
        daily_chest = this.findViewById(R.id.daily_chest);
        user_quest = this.findViewById(R.id.user_quest);
        user_inventory = this.findViewById(R.id.user_inventory);
        user_shop = this.findViewById(R.id.user_shop);

        //onClickMethods for the cardviews
        daily_chest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Fix what happens to code below

                /*
                if (user_opened_daily_chest(Today)){
                    //Code sends user to daily loot
                }
                */

                //Below code should be put in commented if section above






                //Nothing happens if the user already opened the daily
            }
        });

        user_quest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Main_Menu.this.startActivity(QuestSelectionScreen);
            }
        });

        user_inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        user_shop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //Initialize Text Views
        total_Moria_Tv = this.findViewById(R.id.total_Moria_Tv);
        total_Prime_Gems_Tv = this.findViewById(R.id.total_Prime_Gems_Tv);
        daily_steps_total_Tv = this.findViewById(R.id.daily_steps_total_Tv);
        total_user_Xp_Tv = this.findViewById(R.id.total_user_Xp_Tv);

        //Add Related Methods
        Log.i("STEPLOG", runTotalStepCounterAnim());
        Log.i("MORIALOG", runTotalMoriaAnim());
        Log.i("CrystalLOG", runTotalCrystalAnim());
        Log.i("XPLOG", runTotalXPAnim());



    }
}
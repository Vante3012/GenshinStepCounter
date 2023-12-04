package com.example.aether_walker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class QuestEndScreen extends AppCompatActivity {

    //Declare Variables
    public Intent MainActivity, ActiveQuestScreen, QuestEndScreen, Main_Menu;
    public ValueAnimator stepsAnim, XPAnim, moriaAnim, primeGemAnim;
    public static ValueAnimator CounterAnimObj;
    private TextView moria_from_quest, primeGems_from_quest,xp_from_quest,steps_from_quest;
    private ImageView treasure_ico,crystal_ico,coin_ico;
    public Integer moriaCurrency, primeGems, XPAnimVal, stepsAnimVal;
    private RecyclerView recyclerView;
    private ArrayList<RecyclerData> recyclerDataArrayList;

    //Declare Methods

    //Below are 4 blocks of codes that runs the counter animations for each view with input values
    //This method below is 1/4 of the animation counters
    public String runStepsAnim(){
        //Below block of code makes the counter animation for Steps from quest Anim
        //Animator initializer
        stepsAnim = ValueAnimator.ofInt(0, stepsAnimVal);
        stepsAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator.ofInt(0, stepsAnimVal);
                valueAnimator.setDuration(2200);
                steps_from_quest.setText("Steps: "+stepsAnim.getAnimatedValue().toString()+"+");
            }
        });
        stepsAnim.start();

        String LogString = "User earned "+stepsAnim.getAnimatedValue().toString()+
                " Extra total steps for today!";
        return LogString;
    }

    //This method below is 2/4 of the animation counters
    public String runXPAnim(){
        //Below block of code makes the counter animation for XP Anim
        //Animator initializer
        XPAnim = ValueAnimator.ofInt(0, XPAnimVal);
        XPAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator.ofInt(0, XPAnimVal);
                valueAnimator.setDuration(2600);
                xp_from_quest.setText(XPAnim.getAnimatedValue().toString()+"xp");
            }
        });
        XPAnim.start();

        String LogString = "User earned "+XPAnim.getAnimatedValue().toString()+
                " Experience Points!";
        return LogString;
    }

    //This method below is 3/4 of the animation counters
    public String runMoriaAnim(){
        //Below block of code makes the counter animation for Moria
        //Animator initializer
        moriaAnim = ValueAnimator.ofInt(0, moriaCurrency);
        moriaAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator.ofInt(0, moriaCurrency);
                valueAnimator.setDuration(1750);
                moria_from_quest.setText(moriaAnim.getAnimatedValue().toString()+"m");
            }
        });
        moriaAnim.start();

        String LogString = "User earned "+moriaAnim.getAnimatedValue().toString()+" Moria!";
        return LogString;
    }

    //This method below is 4/4 of the animation counters
    public String runPGAnim(){
        //Below block of code makes the counter animation for Prime Gems
        //Animator initializer
        primeGemAnim = ValueAnimator.ofInt(0, primeGems);
        primeGemAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator.ofInt(0, primeGems);
                valueAnimator.setDuration(2000);
                primeGems_from_quest.setText(primeGemAnim.getAnimatedValue().toString()+"c");
            }
        });
        primeGemAnim.start();

        String LogString = "User earned "+primeGemAnim.getAnimatedValue().toString()+" Prime Gems!";
        return LogString;
    }

    //This method below allows a popup window upon finished loot loading for the user.
    public void popupLootReviewComplete() {

        // Inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.loot_review_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.MATCH_PARENT;
        int height = LinearLayout.LayoutParams.MATCH_PARENT;
        boolean focusable = false; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window in this view
        popupWindow.showAtLocation(findViewById(android.R.id.content).getRootView(), Gravity.CENTER, 0, 0);

        // dismiss the popup window when screen is touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();

                //Below Code should put data into the DB!


                //--------------------------------------------------------------------------------------------------



                //Add DB update code above!

                QuestEndScreen.this.startActivity(Main_Menu);
                return true;
            }
        });
    }

    //onCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_end_screen);

        //Initialize Views & add Related Methods:

        //Initialize Intent
        QuestEndScreen = new Intent(this, QuestEndScreen.class);
        MainActivity = new Intent(this, MainActivity.class);
        ActiveQuestScreen = new Intent(this, ActiveQuestScreen.class);
        MainActivity = getIntent();
        Main_Menu = new Intent(this, Main_Menu.class);

        stepsAnimVal = MainActivity.getIntExtra("totalStepsOfQuest",0);
        Log.i("CUR_STEPS_LOG",stepsAnimVal.toString());

        //Initialize recycler view
        recyclerView = findViewById(R.id.idCourseRV);

        // created new array list..
        recyclerDataArrayList=new ArrayList<>();

        // added data to array list
        recyclerDataArrayList.add(new RecyclerData("scroll",R.drawable.scroll_ico));
        recyclerDataArrayList.add(new RecyclerData("scroll",R.drawable.scroll_ico));
        recyclerDataArrayList.add(new RecyclerData("scroll",R.drawable.scroll_ico));
        recyclerDataArrayList.add(new RecyclerData("scroll",R.drawable.scroll_ico));
        recyclerDataArrayList.add(new RecyclerData("scroll",R.drawable.scroll_ico));

        // added data from arraylist to adapter class.
        RecyclerViewAdapter adapter=new RecyclerViewAdapter(recyclerDataArrayList,this);

        // setting grid layout manager to implement grid view.
        // in this method '2' represents number of columns to be displayed in grid view.
        GridLayoutManager layoutManager=new GridLayoutManager(this,2);

        // at last set adapter to recycler view.
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        //Initialize Text Views
        steps_from_quest = findViewById(R.id.steps_from_quest);
        moria_from_quest = findViewById(R.id.moria_from_quest);
        primeGems_from_quest = findViewById(R.id.primeGems_from_quest);
        xp_from_quest = findViewById(R.id.xp_from_quest);

        //Initialize Image Views
        treasure_ico = findViewById(R.id.treasure_ico);
        crystal_ico = findViewById(R.id.crystal_ico);
        coin_ico = findViewById(R.id.coin_ico);

        //TEMP vars for quest completion
        moriaCurrency = 150;
        primeGems = 10;
        XPAnimVal = 1000;

        //Run Animations and log results
        String LogString = runStepsAnim()+
                "\n"+ runXPAnim()+
                "\n"+ runMoriaAnim()+
                "\n"+ runPGAnim();

        //Calls Java class/counter function and logs it
        Log.i("CUR_LOOT_LOG",LogString);

        //Method sends popup to the user after 5k ms via CountDownTimer
        //to send them back to the main menu & logs loot to database as well
        CountDownTimer waitFor = new CountDownTimer(3000, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                //Empty as we wait for the timer to run out
            }
            @Override
            public void onFinish() {
                //NOTE: Review and edit this method above for DB implementation
                popupLootReviewComplete();
            }
        };
        //Start the CountDownTimer
        waitFor.start();
    }
}
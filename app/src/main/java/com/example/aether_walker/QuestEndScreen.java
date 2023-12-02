package com.example.aether_walker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.animation.ValueAnimator;
import android.util.Log;
import android.widget.TextView;

public class QuestEndScreen extends AppCompatActivity {

    //Declare Variables

    public ValueAnimator moriaAnim, primeGemAnim, XPAnim;
    public static ValueAnimator CounterAnimObj;
    private TextView moria_from_quest, primeGems_from_quest,xp_from_quest;
    public Integer moriaCurrency, primeGems, XPAnimVal;

    //Declare Methods

    //Below are two blocks of codes that runs the counter animations with input values
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
    };

    //onCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quest_end_screen);

        //Initialize Views & add Related Methods:
        moria_from_quest = findViewById(R.id.moria_from_quest);
        primeGems_from_quest = findViewById(R.id.primeGems_from_quest);
        xp_from_quest = findViewById(R.id.xp_from_quest);

        //TEMP vars for quest completion
        moriaCurrency = 150;
        primeGems = 10;
        XPAnimVal = 1000;

        //Run Animations and log results
        String LogString = runMoriaAnim()+
                "\n"+ runPGAnim()+
                "\n"+ runXPAnim();

        //Calls Java class/counter function and logs it
        Log.i("CUR_LOOT_LOG",LogString);

    }
}
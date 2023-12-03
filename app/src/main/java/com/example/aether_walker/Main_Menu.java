package com.example.aether_walker;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.animation.ValueAnimator;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

public class Main_Menu extends AppCompatActivity {

    //Declare Variables
    private ValueAnimator userTotalStepsAnim, userTotalMoriaAnim, userTotalCrystalAnim, userTotalXPAnim;
    private ImageView character_img, bckrnd_Img_stepsTaken, step_Img_Progress_Bar, step_Img_Progress_Bar_Overlay;

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


    //OnCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        //NOTE:
        //Temp Values to be pulled from DB
        userTotalSteps = 14750;
        userTotalMoria = 12500;
        userTotalCrystal = 37;
        userTotalXp = 48;





        //Temp Values to be pulled from DB ABOVE ------- CHANGE WITH DB Implementation!

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        //Initialize Views

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
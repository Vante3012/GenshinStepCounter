package com.example.aether_walker;

import androidx.appcompat.app.AppCompatActivity;
import android.animation.ValueAnimator;
import android.widget.TextView;

public class ValueCounterCall extends AppCompatActivity {
    public static ValueAnimator CounterAnimObj;

    //This method runs the counter animation with input values
    public static String runCounterAnim(Integer startInput, Integer endInput, TextView tvToUpdate) {
        //Below block of code makes the counter animation
        //Animator initializer
        CounterAnimObj = ValueAnimator.ofInt(startInput, endInput);
        CounterAnimObj.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator.ofInt(startInput, endInput);
                valueAnimator.setDuration(1500);
                tvToUpdate.setText(CounterAnimObj.getAnimatedValue().toString());
            }
        });
        CounterAnimObj.start();

        String LogString ="Updated a counter";
        return LogString;
    }
}

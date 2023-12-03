package com.example.aether_walker;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //Declare Variables
    public Intent QuestEndScreen;
    private SensorManager sensorManager;
    private boolean running, loggedin;
    private float totalStepsOfQuest;
    private float previousTotalSteps;
    public TextView tv_stepsTaken, questName_tv;
    public ImageView character_img, bckrnd_Img_stepsTaken, step_Img_Progress_Bar, step_Img_Progress_Bar_Overlay;
    public Button debug_stepbutton;
    public ValueAnimator CounterAnim;
    private Sensor backgroundStepSensor, stepDetectorSensor;
    public SensorEventListener stepSensorEventListener;
    public int stepsFromQuest, reqStepsFromQuest, userGold, userCrystal, previousStep, currentStep,
            debugQuestSteps, debugCharacterImgIndxCycler;
    public String userName;

    //Temp character cycle arry
    public int[] characterCycleArry = {R.drawable.alumine_mwalk,R.drawable.alumine_lwalk,
            R.drawable.alumine_mwalk,R.drawable.alumine_rwalk,R.drawable.aluminef};

    //Declare Method Constructors
    //Temp void method for Degub(use of fakestep & Temp Sensor stepmethod)
    public void debugSenseSteps(){

        totalStepsOfQuest++;

        //temp block of code to cycle through debugCharacterImgIndxCycler if quest isn't complete
        if (totalStepsOfQuest<debugQuestSteps) {
            if (debugCharacterImgIndxCycler < 3) {
                debugCharacterImgIndxCycler++;
            } else {
                debugCharacterImgIndxCycler = 0;
            }
        }else{
            debugCharacterImgIndxCycler = 4;
        }

        currentStep = Math.round(totalStepsOfQuest);
        previousStep = currentStep-1;

        //Runs counters and logs them
        Log.i("STEPLOG", runCounterAnim());
        int questBarprog = (Math.round((totalStepsOfQuest/debugQuestSteps)*10000));   // pct goes from 0 to 100
        step_Img_Progress_Bar_Overlay.getBackground().setLevel(questBarprog);
        Log.d("DATAOFBARPROG", Integer.toString(questBarprog));

        character_img.setImageResource(characterCycleArry[debugCharacterImgIndxCycler]);
        Log.d("DATAOFCHARECTERCYCLE", Integer.toString(debugCharacterImgIndxCycler));

        if(debugQuestSteps==totalStepsOfQuest){
            popupQuestComplete();
        }
    }

    //This method allows a popup window upon quest completion
    public void popupQuestComplete() {

        // Inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.quest_sucess_popup, null);

        // create the popup window
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = false; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        // show the popup window in this view
        popupWindow.showAtLocation(findViewById(android.R.id.content).getRootView(), Gravity.CENTER, 0, 0);

        // dismiss the popup window when screen is touched
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                //Puts key intent of totalStepsOfQuest for sending to next activity
                QuestEndScreen.putExtra("totalStepsOfQuest", Math.round(totalStepsOfQuest));
                MainActivity.this.startActivity(QuestEndScreen);
                return true;
            }
        });
    }


    //This method runs the counter animation when totalSteps is updated
    public String runCounterAnim() {
        //Below block of code makes the counter animation
        //Animator initializer
        CounterAnim = ValueAnimator.ofInt(previousStep, currentStep);
        CounterAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                ValueAnimator.ofInt(previousStep, currentStep);
                valueAnimator.setDuration(150);
                tv_stepsTaken.setText(CounterAnim.getAnimatedValue().toString());
            }
        });
        CounterAnim.start();

        String LogString ="Updated Steps";
        return LogString;
    }

    //Adds a step whenever stepDetectorSensor is activated
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
            debugSenseSteps();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    //Resumes step counter if sensor is on phone
    protected void onResume() {
        super.onResume();
        this.running = true;
    }

    //OnCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Keeps app from going to sleep as long as this activity is active.
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //Code snippet below clears the flag and allow the phone to go to sleep
        //getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        //TEMP STEP PARAMS ------------------------------------------------------------





        totalStepsOfQuest = 0;
        debugQuestSteps = 10;
        debugCharacterImgIndxCycler = 0;




        //NOTE: Values above should be pulled from "Quest Select" Activity with Intent and DB!
        //The button "Quests" in main menu should take you to this. (Not implemented yet)-------

        //Initialize Intent
        QuestEndScreen = new Intent(this, QuestEndScreen.class);

        //Initialize Views & add Related Methods:
        step_Img_Progress_Bar_Overlay = this.findViewById(R.id.step_Img_Progress_Bar_Overlay);
        step_Img_Progress_Bar = this.findViewById(R.id.step_Img_Progress_Bar);
        character_img = this.findViewById(R.id.character_img);

        tv_stepsTaken = this.findViewById(R.id.tv_dailystepsTaken);
        bckrnd_Img_stepsTaken = this.findViewById(R.id.bckrnd_img_stepsTaken);

        //Initialize debug_stepbutton view
        debug_stepbutton = this.findViewById(R.id.debug_stepbutton);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //Passive step sensor
        backgroundStepSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);
        sensorManager.registerListener(this, backgroundStepSensor, SensorManager.SENSOR_DELAY_NORMAL);

        //Active step trigger sensor to update steps
        stepDetectorSensor = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR);
        sensorManager.registerListener(this, stepDetectorSensor, SensorManager.SENSOR_DELAY_NORMAL);

        //TEMP debug_stepbutton onclick listener
        debug_stepbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                debugSenseSteps();
            }
        });

    }
}
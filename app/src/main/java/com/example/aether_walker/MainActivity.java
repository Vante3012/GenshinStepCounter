package com.example.aether_walker;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    //Declare Variables
    private SensorManager sensorManager;
    private boolean running, loggedin;
    private float totalSteps;
    private float previousTotalSteps;
    public TextView tv_stepsTaken;
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
            R.drawable.alumine_mwalk,R.drawable.alumine_rwalk};

    //Declare Methods

    //Temp void method for Degub(use of fakestep)
    public void debugSenseSteps(){
        totalSteps++;

        //temp block of code to cycle through debugCharacterImgIndxCycler
        if (debugCharacterImgIndxCycler < 3){
            debugCharacterImgIndxCycler++;
        }else{
            debugCharacterImgIndxCycler = 0;
        }

        currentStep = Math.round(totalSteps);
        previousStep = currentStep-1;

        Log.i("STEPLOG", runCounterAnim());
        int questBarprog = (Math.round((totalSteps/debugQuestSteps)*10000));   // pct goes from 0 to 100
        step_Img_Progress_Bar_Overlay.getBackground().setLevel(questBarprog);
        Log.d("DATAOFBARPROG", Integer.toString(questBarprog));

        character_img.setImageResource(characterCycleArry[debugCharacterImgIndxCycler]);
        Log.d("DATAOFCHARECTERCYCLE", Integer.toString(debugCharacterImgIndxCycler));
    };


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

    //OnCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TEMP STEP PARAMS
        totalSteps = 0;
        debugQuestSteps = 20;
        debugCharacterImgIndxCycler = 0;

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
}
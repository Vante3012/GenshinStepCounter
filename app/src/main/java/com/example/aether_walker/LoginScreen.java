package com.example.aether_walker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginScreen extends AppCompatActivity {

    //Declare Variables
    public Intent Main_Menu;
    private TextInputEditText usernameInpt, passwordInpt;
    private Button submit_data;
    private SQLiteDatabase db;
    private DBHelper MyDbHelper;

    //User Data to be input from DB
    public String User_Name, User_Password;
    public Integer User_ID,Has_Gold,Has_Crystals,Total_Steps,Total_Daily_Steps;
    public int[] Unlocked_Characters, Has_Scrolls;
    public JSONArray User_Has_Items;

    public Cursor cursor;

    //Declare Method Constructors

    public int[] getIntArryFromDB(String inpt) {

        String[] parts = inpt.split(",");

        int[] array = new int[parts.length];
        for (int i = 0; i < parts.length; i++)
            array[i] = Integer.parseInt(parts[i]);

        System.out.println(Arrays.toString(array));
        return array;
    }

    //Popup for incorrect login
    public void popupWrongCredentials() {

        // Inflate the layout of the popup window
        LayoutInflater inflater = (LayoutInflater)getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.invalidlogin_popup, null);

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
                return true;
            }
        });
    }


    //OnCreate Method
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        //Initialize variables

        //Initialize db
        //BELOW IS CODE TO GET AND PREPARE DB DATA

        Log.i("sad","Plswork");
        MyDbHelper = new DBHelper(this);

        try {
            MyDbHelper.createDataBase();
        } catch (IOException e) {
            throw new Error("Unable to make DB");
        }
        try {
            MyDbHelper.openDataBase();
        }catch (SQLException sqle){

        }
        db = MyDbHelper.getWritableDatabase();


        //Initialize Intent
        Main_Menu = new Intent(this, Main_Menu.class);

        //Editable textview initialized
        usernameInpt = this.findViewById(R.id.usernameInpt);
        passwordInpt = this.findViewById(R.id.passwordInpt);

        //Submit initialized
        submit_data = this.findViewById(R.id.submit_data);

        submit_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Get view inputs and clear the form
                String username = usernameInpt.getText().toString();
                String password = passwordInpt.getText().toString();

                //Runs a DB cursor select the row with login & pass values
                String Query = "SELECT * FROM User WHERE User_Name = " + username + " AND User_Password = " + password;
                Log.i("LOGQuery",Query);
                try {
                    cursor = db.rawQuery(Query,null);
                    if (cursor.moveToFirst()) {
                        do {
                            // on below line we are adding the data from
                            // cursor parses through data

                            //SET USER DATA FOR THIS APP ACTIVITY
                            User_ID = Integer.valueOf(cursor.getString(1)) ;
                            User_Name = cursor.getString(2);
                            User_Password = cursor.getString(3);
                            Unlocked_Characters = getIntArryFromDB(cursor.getString(4));
                            Has_Scrolls = getIntArryFromDB(cursor.getString(5));
                            Has_Gold = cursor.getInt(6);
                            Has_Crystals = cursor.getInt(7);
                            Total_Steps = cursor.getInt(8);
                            Total_Daily_Steps = cursor.getInt(9);

                            //Last value JSON parser
                            User_Has_Items = new JSONArray(cursor.getString(10));
                        } while (cursor.moveToNext());
                        // moving our cursor to next.
                    }
                    // at last closing our cursor
                    // and returning our array list.
                    cursor.close();

                    //Launch Main menu on sucessful login
                    LoginScreen.this.startActivity(Main_Menu);

                }catch (SQLException sqle){
                    Log.i("DB ERROR|","Error with query for data");
                } catch (JSONException e) {
                    Log.i("JSON ERROR|","Error parsing JSON data");
                    throw new RuntimeException(e);
                }
                popupWrongCredentials();
                Log.i("LOGIN ERROR|","Possible Invalid Login");
            }
        });



    }
}
package com.example.aether_walker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterSelect extends AppCompatActivity {

    TextView charName, charbuff;
    ImageView charIco;
    CardView nextBtn, prevBtn, charSelect;

    String[] charNames = {"Lumine", "Aether", "Kaeya", "Scaramouche"};
    int[] charIcos={R.drawable.aluminef, R.drawable.aether, R.drawable.kaeya, R.drawable.scara};
    String[] cbuffs = {"3 star", "3 star", "4 star", "5 star"};
    int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select);
        charName=findViewById(R.id.Char_Select_Name_Txt);
        charbuff=findViewById(R.id.Char_Select_Buff);
        charIco=findViewById(R.id.Char_Select_Ico) ;
        nextBtn=findViewById(R.id.next_char);
        prevBtn=findViewById(R.id.previous_char);
        charSelect=findViewById(R.id.selected_char);
        charIco.setImageResource(charIcos[0]);
        charName.setText(charNames[0]);
        charbuff.setText(cbuffs[0]);

        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                if(count==4){
                    count=0;
                }
                charIco.setImageResource(charIcos[count]);
                charName.setText(charNames[count]);
                charbuff.setText(cbuffs[count]);
            }
        });
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count--;
                if(count==-1){
                    count=3;
                }
                charIco.setImageResource(charIcos[count]);
                charName.setText(charNames[count]);
                charbuff.setText(cbuffs[count]);
            }
        });
    }



}
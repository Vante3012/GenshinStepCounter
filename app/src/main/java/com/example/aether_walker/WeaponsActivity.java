package com.example.aether_walker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WeaponsActivity extends AppCompatActivity {

    TextView wpnName, wpnbuff;
    ImageView wpnIco;
    CardView nextBtn, prevBtn, wpnSelect;

    String[] wpnNames = {"Dull Blade", "Silver Sword", "Aquila Favonia", "Mistsplitter Reforged"};
    int[] wpnIcos={R.drawable.dullblade, R.drawable.silversword, R.drawable.aquilafavonia, R.drawable.mistsplitterreforged};
    String[] buffs = {"Buff1", "Buff2", "Buff3", "Buff4"};
    int counter=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weapons);
        wpnName=findViewById(R.id.Weapons_Select_Name_Txt);
        wpnbuff=findViewById(R.id.Weapons_Select_Buff);
        wpnIco=findViewById(R.id.Weapon_Select_Ico) ;
        nextBtn=findViewById(R.id.next_weapon);
        prevBtn=findViewById(R.id.previous_weapon);
        wpnSelect=findViewById(R.id.selected_weapon);
        wpnIco.setImageResource(wpnIcos[0]);
        wpnName.setText(wpnNames[0]);
        wpnbuff.setText(buffs[0]);

nextBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        counter++;
        if(counter==4){
            counter=0;
        }
        wpnIco.setImageResource(wpnIcos[counter]);
        wpnName.setText(wpnNames[counter]);
        wpnbuff.setText(buffs[counter]);
    }
});
        prevBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                if(counter==-1){
                    counter=3;
                }
                wpnIco.setImageResource(wpnIcos[counter]);
                wpnName.setText(wpnNames[counter]);
                wpnbuff.setText(buffs[counter]);
            }
        });
    }



}
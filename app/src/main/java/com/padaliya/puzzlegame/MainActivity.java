package com.padaliya.puzzlegame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button source = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> list = new ArrayList();
        list.addAll( Arrays.asList( "1", "2", "3", "4" ,"5","6","7","8","9","10","11"
                ,"12","13","14","15"));  // from literals

        Button[] button = new Button[15];
        for (int i = 0; i < 15; i++) {

            int id = getResources().getIdentifier("button"+ (i+1), "id", getPackageName());
            button[i] = (Button) findViewById(id);


            Random rand = new Random();
            String text = list.get(rand.nextInt(list.size()));
            button[i].setText(text);
            button[i].setEnabled(false);
            button[i].setBackgroundColor(getResources().getColor(R.color.colorButtonDefault));
            list.remove(text);
    }


        setAllDisabled() ;
        set4Enabled();


    }

    public void sourceButtonListner(View view)
    {
        Button button = (Button) view;

        if(source == null)
        {
            source = button;
            source.setBackgroundColor(getResources().getColor(R.color.colorSelected));
        }else
            {
                if(!source.equals(button))
                {
                    String textSource = (String) source.getText();
                    String textTarget = (String) button.getText();

                    if(textSource.equals("") && !textTarget.equals("") ||
                        !textSource.equals("") && textTarget.equals(""))
                    {
                        button.setText(textSource);



                        source.setText(textTarget);


                    }
                }
                button.setBackgroundColor(getResources().getColor(R.color.colorButtonDefault));
                source.setBackgroundColor(getResources().getColor(R.color.colorButtonDefault));

                source = null ;

                if(checkForGameSolved())
                {
                    Log.d("Help","You win");
                }
            }


        setAllDisabled() ;
        set4Enabled();
//        Log.d("Help Src",sourceText) ;

    }


    public void setAllDisabled(){
        for (int i = 0; i < 16; i++) {
            int id = getResources().getIdentifier("button"+ (i+1), "id", getPackageName());

            Button button = (Button) findViewById(id);
            button.setEnabled(false);
        }

    }

    public void set4Enabled()
    {

        for (int i = 1; i <= 16; i++) {
            int id = getResources().getIdentifier("button"+ (i), "id", getPackageName());

            Button button = (Button) findViewById(id);
            if(button.getText().equals(""))
            {
                button.setEnabled(true);

                if(i - 1 >= 1 && !((i -1 )%4 == 0))
                {
                    int id1 = getResources().getIdentifier("button"+ (i - 1), "id", getPackageName());
                    Button buttonLeft = findViewById(id1);
                    buttonLeft.setEnabled(true);
                }

                if( i + 1 <= 16 && !(i%4 == 0))
                {
                    int id1 = getResources().getIdentifier("button"+ (i + 1), "id", getPackageName());
                    Button buttonRight = findViewById(id1);
                    buttonRight.setEnabled(true);

                }

                if(i - 4 >= 1)
                {
                    int id1 = getResources().getIdentifier("button"+ (i - 4 ), "id", getPackageName());
                    Button buttonTop = findViewById(id1);
                    buttonTop.setEnabled(true);

                }

                if( i + 4 <= 16)
                {
                    int id1 = getResources().getIdentifier("button"+ (i + 4), "id", getPackageName());
                    Button buttonBottom = findViewById(id1);
                    buttonBottom.setEnabled(true);
                }


            }
        }

    }


    private boolean checkForGameSolved() {

        for (int i = 1; i <= 15; i++) {
            int id = getResources().getIdentifier("button"+ (i), "id", getPackageName());

            Button button = (Button) findViewById(id);
            if(!button.getText().equals(i+""))
            {
                return  false ;
            }
        }


        return true;
    }




}


package com.example.letsconnect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //0 for yellow
    //1 for red
    //2 for empty
    int activeplayer=0;

    int gamestate[]={2,2,2,2,2,2,2,2,2};

    boolean gameactive=true;

    int[][] winningpositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void dropIn(View view){
        ImageView ctr=(ImageView)view;

        int tappedcounter=Integer.parseInt(ctr.getTag().toString());
        int fg=0;

        if(gamestate[tappedcounter]==2 && gameactive){


        gamestate[tappedcounter]=activeplayer;

        ctr.setTranslationY(-1500);
        if(activeplayer==0) {
            ctr.setImageResource(R.drawable.yellow);
            activeplayer=1;
        }
        else
        {
            ctr.setImageResource(R.drawable.red);
            activeplayer=0;
        }
        ctr.animate().translationYBy(1500).rotation(3600).setDuration(300);

        for(int[] winningposition : winningpositions) {
            if (gamestate[winningposition[0]] == gamestate[winningposition[1]] && gamestate[winningposition[1]] == gamestate[winningposition[2]] &&
                    gamestate[winningposition[0]] != 2 && gamestate[winningposition[1]] != 2 && gamestate[winningposition[2]] != 2
            ) {
                gameactive=false;
                String winner = "";
                if (activeplayer == 1) {
                    winner = "YELLOW";
                } else {
                    winner = "RED";
                }
              //  Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();

                TextView winnertextview=(TextView)findViewById(R.id.t1);
                Button playagainbutton=(Button)findViewById(R.id.b1);

                winnertextview.setText(winner + " has won");
                playagainbutton.setVisibility(View.VISIBLE);
                winnertextview.setVisibility(View.VISIBLE);
            }
        }
        for(int m=0;m<gamestate.length;m++)
        {
            if(gamestate[m]!=2){
                fg=0;
            }
            else
            {
                fg=1;
                break;
            }
        }
        if(fg==0){
            //draw
            TextView winnertextview=(TextView)findViewById(R.id.t1);
            Button playagainbutton=(Button)findViewById(R.id.b1);

            winnertextview.setText("DRAW");
            playagainbutton.setVisibility(View.VISIBLE);
            winnertextview.setVisibility(View.VISIBLE);
        }

        }

    }
    public void playagain(View view){

        TextView winnertextview=(TextView)findViewById(R.id.t1);
        Button playagainbutton=(Button)findViewById(R.id.b1);

        playagainbutton.setVisibility(View.INVISIBLE);
        winnertextview.setVisibility(View.INVISIBLE);

        androidx.gridlayout.widget.GridLayout g = findViewById(R.id.gridLayout);

        for(int i=0;i<g.getChildCount();i++)
        {
            ImageView img=(ImageView) g.getChildAt(i);
            img.setImageDrawable(null);
        }
          activeplayer=0;

         for(int i=0;i<9;i++){
             gamestate[i]=2;
         }

         gameactive=true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

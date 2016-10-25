package com.example.administrator.srm;

import android.app.Service;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private int secondsRemaining;
    private Button[] boardButtons=new Button[2];
    private Vibrator vibrator=null;
    private int randomIndex=0;
    private int rawRandom=0;
    private int lastRandom=0;
    private Random random;
    //private boolean clickState0=false;
    //private boolean clickState3=false;
    private boolean clickFlag0;
    private boolean clickFlag1;
    int score=0;
    TextView text;
    Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        clickFlag0=false;
        clickFlag1=false;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        secondsRemaining=20000;
        vibrator=(Vibrator)getSystemService(Service.VIBRATOR_SERVICE);
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(secondsRemaining >= 0) {
                            assignColor();
                            secondsRemaining = secondsRemaining - 500;
                        }
                        else{
                            playAlertSound(R.raw.beep);
                            timer.cancel();
                        }
                    }
                });
            }
        }, 500, 2000);

//        TimerTask task = new TimerTask() {
//
//            public void run() {
//                onTick(secondsRemaining);
//            }
//        };
//        timer.schedule(task, 2000,2000);

        Button Button0 = (Button) findViewById(R.id.button0);
        Button Button3 = (Button) findViewById(R.id.button3);
        text=(TextView) findViewById(R.id.text0);
        boardButtons[0]=Button0;
        boardButtons[1]=Button3;
        assignColor();

        boardButtons[0].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boardButtons[0].setEnabled(false);
                clickFlag0=true;
                //boardButtons[0].setBackgroundColor(Color.LTGRAY);
                if (!clickFlag1 && (randomIndex==1)){
                    score=score+1;
                    text.setText(score+"");

                }
                else  if (clickFlag1 && (randomIndex==0)){
                    score=score+2;
                    text.setText(score+"");
                    playAlertSound(R.raw.ding);
                }
                else  if (clickFlag1 && (randomIndex==2)){
                    score=score-2;
                    text.setText(score+"");
                    vibrator.vibrate(new long[]{10, 50, 50, 100, 50}, -1);
                }
//                if ((randomIndex==0)&&(clickFlag1==true)){
//                    score=score+1;
//                    text.setText(score+"");
//                }
//                else if ((randomIndex==1)&&(clickFlag1==false)){
//                    score=score+1;
//                    text.setText(score+"");
//                }
//                else if ((randomIndex==1)&&(clickFlag1==true)){
//                        score=score-2;
//                    text.setText(score+"");
//                    vibrator.vibrate(new long[]{10, 50, 50, 100, 50}, -1);
//                }
//                else if ((randomIndex==2)){
//                    score=score-2;
//                    text.setText(score+"");
//                    vibrator.vibrate(new long[]{10, 50, 50, 100, 50}, -1);
//                }
//                else {
//
//                }
                }

        });

        boardButtons[1].setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                boardButtons[1].setEnabled(false);
                clickFlag1=true;
                //boardButtons[1].setBackgroundColor(Color.LTGRAY);
                if (!clickFlag0 && (randomIndex==2)){
                    score=score+1;
                    text.setText(score+"");

                }
                else  if (clickFlag0 && (randomIndex==0)){
                    score=score+2;
                    text.setText(score+"");
                    playAlertSound(R.raw.ding);
                }
                else  if (clickFlag0 && (randomIndex==1)){
                    score=score-2;
                    text.setText(score+"");
                    vibrator.vibrate(new long[]{10, 50, 50, 100, 50}, -1);
                }
//                if ((randomIndex==0)&&(clickFlag0==true)){
//                    score=score+1;
//                    text.setText(score+"");
//                }
//                else if ((randomIndex==2)&&(clickFlag0==false)){
//                    score=score+1;
//                    text.setText(score+"");
//                }
//                else if ((randomIndex==2)&&(clickFlag0==true)){
//                    score=score-2;
//                    text.setText(score+"");
//                    vibrator.vibrate(new long[]{10, 50, 50, 100, 50}, -1);
//                }
//                else if ((randomIndex==1)){
//                    score=score-2;
//                    text.setText(score+"");
//                    vibrator.vibrate(new long[]{10, 50, 50, 100, 50}, -1);
//                }
//                else {
//
//                }
            }
        });
    }


//    public void onTick(long timeLeft) {
//
//        if (timeLeft >= 5) {
//           // playAlertSound(R.raw.beep);
//           // vibrator.vibrate(new long[]{10, 50, 50, 100, 50}, -1);
//            assignColor();
//           // clickState=false;
//            secondsRemaining=secondsRemaining-2000;
//
//        } else if (timeLeft <5) {
//            playAlertSound(R.raw.beep);
//            timer.cancel();
//        }
//
//    }

    public void playAlertSound(int sound) {

        MediaPlayer mp = MediaPlayer.create(getBaseContext(), sound);
        mp.start();
        mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {

            @Override
            public void onCompletion(MediaPlayer mp) {
                mp.release();
            }
        });
    }

//    private void getScore(){
//        if (randomIndex==0){
//            if((clickFlag0==true) && (clickFlag1==true)){
//                score=score+1;
//                    text.setText(score+"");
//            }
//            else if((clickFlag0==true) && (clickFlag1==false)){
//                score=score-1;
//                text.setText(score+"");
//            }
//            else if((clickFlag0==false) && (clickFlag1==true)){
//                score=score-1;
//                text.setText(score+"");
//            }
//            else{
//
//            }
//        }
//        else if (randomIndex==1){
//            if((clickFlag0==true) && (clickFlag1==true)){
//                score=score-1;
//                text.setText(score+"");
//            }
//            else if((clickFlag0==true) && (clickFlag1==false)){
//                score=score+1;
//                text.setText(score+"");
//            }
//            else if((clickFlag0==false) && (clickFlag1==true)){
//                score=score-1;
//                text.setText(score+"");
//            }
//            else{
//
//            }
//        }
//        else if (randomIndex==2){
//            if((clickFlag0==true) && (clickFlag1==true)){
//                score=score-1;
//                text.setText(score+"");
//            }
//            else if((clickFlag0==true) && (clickFlag1==false)){
//                score=score-1;
//                text.setText(score+"");
//            }
//            else if((clickFlag0==false) && (clickFlag1==true)){
//                score=score+1;
//                text.setText(score+"");
//            }
//            else{
//
//            }
//        }
//    }

    private void checkPointDeduction(){
        if (randomIndex==1 && !clickFlag0 &&  clickFlag1) {
            score = score - 1;
            text.setText(score + "");
            vibrator.vibrate(new long[]{10, 50, 50, 100, 50}, -1);
        }
        else if (randomIndex==2 && clickFlag0 && !clickFlag1) {
            score = score - 1;
            text.setText(score + "");
            vibrator.vibrate(new long[]{10, 50, 50, 100, 50}, -1);
        }
    }
    private void assignColor(){
        checkPointDeduction();
        clickFlag0=false;
        clickFlag1=false;
        boardButtons[0].setEnabled(true);
        boardButtons[1].setEnabled(true);
        boardButtons[0].setBackgroundColor(Color.WHITE);
        boardButtons[1].setBackgroundColor(Color.WHITE);
        random=new Random();
        rawRandom=random.nextInt(2);
        if (lastRandom==0){
            if(rawRandom==0) {
                randomIndex = 1;
                lastRandom = 1;
            }
            else{
                randomIndex = 2;
                lastRandom = 2;
            }
        }
        else if (lastRandom==1){
            if(rawRandom==0) {
                randomIndex = 0;
                lastRandom = 0;
            }
            else{
                randomIndex = 2;
                lastRandom = 2;
            }
        }
        else {
            if(rawRandom==0) {
                randomIndex = 0;
                lastRandom = 0;
            }
            else{
                randomIndex = 1;
                lastRandom = 1;
            }
        }

        if (randomIndex==0)
        {
            //clickState=false;
            text.setBackgroundColor(Color.WHITE);
            //playAlertSound(R.raw.ding);
            //clickState=false;
        }
        if (randomIndex==1)
        {
            //clickState=false;
            text.setBackgroundColor(Color.CYAN);
            //playAlertSound(R.raw.ding);
            //vibrator.vibrate(new long[]{10, 50, 50, 100, 50}, -1);

        }
        if (randomIndex==2)
        {
            //clickState=false;
            text.setBackgroundColor(Color.RED);
            //playAlertSound(R.raw.dong);
            //vibrator.vibrate(new long[]{10, 50, 50, 100, 50}, -1);

        }
//        if (randomIndex==3)
//        {
//            clickState=false;
//            playAlertSound(R.raw.dong);
//
//        }
//        for (int i=0;i<boardButtons.length;i++){
//            if(i==randomIndex) {
//                boardButtons[i].setBackgroundColor(Color.BLACK);
//            }
//            else{
//                boardButtons[i].setBackgroundColor(Color.WHITE);
//            }
//        }
    }

}


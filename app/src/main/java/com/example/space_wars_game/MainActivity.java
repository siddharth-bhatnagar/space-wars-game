package com.example.space_wars_game;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    ImageView imageGun;
    ImageView imageGunShot;
    ImageView alien;

    private SoundPlayer sound;
    private int alienX;
    private int alienY;

    int score;
    TextView scoreLabel;
    int losescore;
    TextView losescoreLabel;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    private int frameHeight;
    private int boxSize;
    private int ScreenWidth;
    private int ScreenHeight;
    private TextView startLabel;
    FrameLayout layout;
    private boolean action_flag = false;
    private boolean start_flag = false;

    //BoundService class Object
    BoundService mBoundService;
    boolean mServiceBound = false;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageGun = (ImageView) findViewById(R.id.weapon);
        imageGunShot = (ImageView) findViewById(R.id.gunshot);
        layout = (FrameLayout) findViewById(R.id.frameLayout);
        startLabel = (TextView) findViewById(R.id.startLabel);

        scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        losescoreLabel = (TextView) findViewById(R.id.missedAliens);
        losescoreLabel.setText("YOU MISSED: " + losescore + " UFOs");
        sound = new SoundPlayer(this);


        layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                sound.playHitSound();
                AnimationShot.startAnimationFight(imageGun, imageGunShot);

                return false;
            }
        });
        scoreLabel.setText("SCORE: " + score);
        WindowManager wm = getWindowManager();
        Display disp = wm.getDefaultDisplay();
        Point size = new Point();
        disp.getSize(size);

        ScreenWidth = size.x;
        ScreenHeight = size.y;
        alien = (ImageView) findViewById(R.id.alien);
        alien.setX(-80);
        alien.setY(-80);
        System.out.println("The position x of Alien: " + alienX + " and Y : " + alienY);

    }

    @SuppressLint("ClickableViewAccessibility")
    private void hitCheck() {

        alien.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                score += 1;
                scoreLabel.setText("You Shot: " + score + " UFOs");

                alienX = ScreenWidth + 20;
                alienY = (int) Math.floor(Math.random() * (frameHeight - alien.getHeight()));

                return false;
            }
        });
    }


    public void changePos() {
        hitCheck();

        if (score < 7) {
            alienX -= 15;
        } else if (score >= 7 && score < 13) {
            alienX -= 25;
        } else if (score >= 13 && score <= 17) {
            alienX -= 35;
        } else if (score > 17 && score <= 22) {
            alienX -= 45;
        } else if (score > 22 && score <= 25) {
            alienX -= 55;
        } else if (score > 25 && score <= 29) {
            alienX -= 70;
        } else if (score > 29) {
            alienX -= 85;
        }

        if (alienX < 0 - alien.getWidth()) {
            losescore += 1;
            losescoreLabel.setText("You Missed: " + losescore + " UFOs");
            alienX = ScreenWidth + 20;
            alienY = (int) Math.floor(Math.random() * (frameHeight - alien.getHeight()));
        }
        alien.setX(alienX);
        alien.setY(alienY);

        if (losescore >= 5) {
            sound.playOverSound();

            if (timer != null) {
                timer.cancel();
                timer = null;
            }

            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("SCORE", score);
            intent.putExtra("SCORELOSE", losescore);

            intent.putExtra("TimeStamp", mBoundService.getTimestamp());

            startActivity(intent);
        }

    }

    public boolean onTouchEvent(MotionEvent me) {
        if (start_flag == false) {
            start_flag = true;

            Intent boundServiceIntent = new Intent(getApplicationContext(), BoundService.class);
            bindService(boundServiceIntent, mServiceConnection, Context.BIND_AUTO_CREATE);

            startLabel.setVisibility(View.GONE);

            FrameLayout frame = (FrameLayout) findViewById(R.id.frameLayout);
            frameHeight = frame.getHeight();

            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {

                            changePos();
                        }
                    });
                }
            }, 0, 20);
        } else {
            if (me.getAction() == MotionEvent.ACTION_DOWN) {
                action_flag = true;
            } else if (me.getAction() == MotionEvent.ACTION_UP) {
                action_flag = false;
            }
        }

        return true;
    }

    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(MainActivity.this, MainMenuActivity.class);
        startActivity(intent);
        MainActivity.this.finish();
    }

    private ServiceConnection mServiceConnection = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {
            mServiceBound = false;
        }

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            BoundService.BoundServiceBinder myBinder = (BoundService.BoundServiceBinder) service;
            mBoundService = myBinder.getService();
            mServiceBound = true;
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mServiceBound) {
            unbindService(mServiceConnection);
            mServiceBound = false;
        }
    }

}
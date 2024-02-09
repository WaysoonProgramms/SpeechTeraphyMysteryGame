package com.waysoonprogramms.speechteraphymysterygame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class PartTwo extends AppCompatActivity {

    private View txt1;
    private View txt2;
    private View txt3;
    private View txt4;
    private View txt1a;
    private View txt2a;
    private View txt3a;
    private View txt4a;
    private View container;
    private View trigger;
    private MediaPlayer sound;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_two);

        txt1 = findViewById(R.id.text1);
        txt1.setOnTouchListener(touchListener);

        txt2 = findViewById(R.id.text2);
        txt2.setOnTouchListener(touchListener);

        txt3 = findViewById(R.id.text3);
        txt3.setOnTouchListener(touchListener);

        txt4 = findViewById(R.id.text4);
        txt4.setOnTouchListener(touchListener);

        container = findViewById(R.id.container1);
        trigger = findViewById(R.id.trigger);

        txt1a = findViewById(R.id.text1a);
        txt2a = findViewById(R.id.text2a);
        txt3a = findViewById(R.id.text3a);
        txt4a = findViewById(R.id.text4a);
    }

    private int xDelta, yDelta, top, left, right, bottom;
    private int finalTop, finalLeft, finalRight, finalBottom;
    public int queue = 4;
    private boolean error = true;


    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            final int x = (int) event.getRawX();
            final int y = (int) event.getRawY();

            top = trigger.getTop();
            left = trigger.getLeft();
            right = trigger.getRight();
            bottom = trigger.getBottom();


            switch (event.getAction() & MotionEvent.ACTION_MASK) {
                case MotionEvent.ACTION_DOWN: {
                    finalTop = view.getTop();
                    finalLeft = view.getLeft();
                    finalRight = view.getRight();
                    finalBottom = view.getBottom();

                    FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams) view.getLayoutParams();
                    xDelta = x - lParams.leftMargin;
                    yDelta = y - lParams.topMargin;
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    pickTrigger(view);
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    if (x - xDelta + view.getWidth() <= container.getWidth()
                            && y - yDelta + view.getHeight() <= container.getHeight()
                            && x - xDelta >= 0
                            && y - yDelta >= 0) {
                        FrameLayout.LayoutParams layoutParams =
                                (FrameLayout.LayoutParams) view.getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                    }

                    break;
                }
            }
            container.invalidate();
            return true;
        }
    };

    private void pickTrigger(View view) {
        error = true;
        if (view.getLeft() >= left
                && view.getRight() <= right
                && view.getTop() >= top
                && view.getBottom() <= bottom
                && view == txt2
                && queue == 4) {
            view.setVisibility(View.INVISIBLE);
            txt2a.setVisibility(View.VISIBLE);
            queue--;
            error = false;
            sound = MediaPlayer.create(PartTwo.this, R.raw.good_answ);
            SoundPlay();
        }
        if (view.getLeft() >= left
                && view.getRight() <= right
                && view.getTop() >= top
                && view.getBottom() <= bottom
                && view == txt3
                && queue == 3) {
            view.setVisibility(View.INVISIBLE);
            txt3a.setVisibility(View.VISIBLE);
            queue--;
            error = false;
            sound = MediaPlayer.create(PartTwo.this, R.raw.good_answ);
            SoundPlay();
        }
        if (view.getLeft() >= left
                && view.getRight() <= right
                && view.getTop() >= top
                && view.getBottom() <= bottom
                && view == txt1
                && queue == 2) {
            view.setVisibility(View.INVISIBLE);
            txt1a.setVisibility(View.VISIBLE);
            queue--;
            error = false;
            sound = MediaPlayer.create(PartTwo.this, R.raw.good_answ);
            SoundPlay();
        }
        if (view.getLeft() >= left
                && view.getRight() <= right
                && view.getTop() >= top
                && view.getBottom() <= bottom
                && view == txt4
                && queue == 1) {
            view.setVisibility(View.INVISIBLE);
            txt4a.setVisibility(View.VISIBLE);
            queue--;
            error = false;
            sound = MediaPlayer.create(PartTwo.this, R.raw.good_answ);
            SoundPlay();
        }
        if (error) {
            sound = MediaPlayer.create(PartTwo.this, R.raw.bad_answ);
            SoundPlay();
            FrameLayout.LayoutParams layoutParams =
                    (FrameLayout.LayoutParams) view.getLayoutParams();
            layoutParams.leftMargin = finalLeft;
            layoutParams.topMargin = finalTop;
            layoutParams.rightMargin = 0;
            layoutParams.bottomMargin = 0;
            view.setLayoutParams(layoutParams);
        }
        if (queue == 0) {
            showWin();
        }
    }

    private void showWin() {
        AlertDialog.Builder builder = new AlertDialog.Builder(PartTwo.this);
        builder.setTitle("Умница! Так держать!")
                .setMessage("Все правильно!")
                .setCancelable(true)
                .setPositiveButton("На главную", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(PartTwo.this, MainActivity.class);
                        startActivity(intent);
                    }
                });
        builder.show();
    }

    private void SoundPlay() {
        if (sound.isPlaying()) {
            sound.stop();
        }
        sound.start();
    }
}
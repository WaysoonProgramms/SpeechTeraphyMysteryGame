package com.waysoonprogramms.speechteraphymysterygame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class PartOne extends AppCompatActivity {

    private View img1;
    private View img2;
    private View img3;
    private View img4;
    private View img5;
    private View img6;
    private View txt1;
    private View txt2;
    private View txt3;
    private View txt4;
    private View container;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_part_one);

        img1 = findViewById(R.id.img1);
        img1.setOnTouchListener(touchListener);

        img2 = findViewById(R.id.img2);
        img2.setOnTouchListener(touchListener);

        img3 = findViewById(R.id.img3);
        img3.setOnTouchListener(touchListener);

        img4 = findViewById(R.id.img4);
        img4.setOnTouchListener(touchListener);

        img5 = findViewById(R.id.img5);
        img5.setOnTouchListener(touchListener);

        img6 = findViewById(R.id.img6);
        img6.setOnTouchListener(touchListener);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);
        txt4 = findViewById(R.id.txt4);

        container = findViewById(R.id.container);
    }

    private int xDelta, yDelta, topA, leftA, rightA, bottomA;
    private int topB, leftB, rightB, bottomB;
    private int topC, leftC, rightC, bottomC;
    private int topD, leftD, rightD, bottomD;
    private int finalTop, finalLeft, finalRight, finalBottom;
    public int queue = 4;
    private MediaPlayer sound;
    private boolean error = true;


    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent event) {
            final int x = (int) event.getRawX();
            final int y = (int) event.getRawY();

            topA = img1.getTop();
            leftA = img1.getLeft();
            rightA = img1.getRight();
            bottomA = img1.getBottom();

            topB = img2.getTop();
            leftB = img2.getLeft();
            rightB = img2.getRight();
            bottomB = img2.getBottom();

            topC = img3.getTop();
            leftC = img3.getLeft();
            rightC = img3.getRight();
            bottomC = img3.getBottom();

            topD = img5.getTop();
            leftD = img5.getLeft();
            rightD = img5.getRight();
            bottomD = img5.getBottom();


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

        private void pickTrigger(View view) {
            error = true;
            if (view.getLeft() >= leftA
                    && view.getRight() <= rightA
                    && view.getTop() >= topA
                    && view.getBottom() <= bottomA
                    && view == img1
                    && queue == 4) {
                view.setVisibility(View.INVISIBLE);
                txt1.setVisibility(View.INVISIBLE);
                queue--;
                error = false;
                sound = MediaPlayer.create(PartOne.this, R.raw.good_answ);
                SoundPlay();
            }
            if (view.getLeft() >= leftB
                    && view.getRight() <= rightB
                    && view.getTop() >= topB
                    && view.getBottom() <= bottomB
                    && view == img2
                    && queue == 3) {
                view.setVisibility(View.INVISIBLE);
                txt2.setVisibility(View.INVISIBLE);
                queue--;
                error = false;
                sound = MediaPlayer.create(PartOne.this, R.raw.good_answ);
                SoundPlay();
            }
            if (view.getLeft() >= leftC
                    && view.getRight() <= rightC
                    && view.getTop() >= topC
                    && view.getBottom() <= bottomC
                    && view == img3
                    && queue == 2) {
                view.setVisibility(View.INVISIBLE);
                txt3.setVisibility(View.INVISIBLE);
                queue--;
                error = false;
                sound = MediaPlayer.create(PartOne.this, R.raw.good_answ);
                SoundPlay();
            }
            if (view.getLeft() >= leftD
                    && view.getRight() <= rightD
                    && view.getTop() >= topD
                    && view.getBottom() <= bottomD
                    && view == img5
                    && queue == 1) {
                view.setVisibility(View.INVISIBLE);
                txt4.setVisibility(View.INVISIBLE);
                queue--;
                error = false;
                sound = MediaPlayer.create(PartOne.this, R.raw.good_answ);
                SoundPlay();
            }
            if (error) {
                sound = MediaPlayer.create(PartOne.this, R.raw.bad_answ);
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
                NextPart();
            }
        }
    };

    public void NextPart() {
        Intent intent = new Intent(this, PartTwo.class);
        startActivity(intent);
    }

    private void SoundPlay() {
        if (sound.isPlaying()) {
            sound.stop();
        }
        sound.start();
    }
}
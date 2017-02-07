package com.lwd726gmail.scarnesdice;

import android.app.usage.UsageEvents;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;



public class MainActivity extends AppCompatActivity {
    private int user_score_total, user_turn_score, computer_score_total, computer_turn_score;
    private boolean user_lost = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        user_score_total = 0;
        user_turn_score = 0;
        computer_score_total = 0;
        computer_turn_score = 0;
    }

    public boolean won(int score) {
        if (score >= 20) {
            return true;
        } else {
            return false;
        }
    }

    public void roll_dice(View v) {
        Button rButton = (Button)findViewById(R.id.resetbutton);
        Button hButton = (Button)findViewById(R.id.holdbutton);
        Button rollButton = (Button)findViewById(R.id.rollbutton);
        rButton.setEnabled(true);
        hButton.setEnabled(true);
        rollButton.setEnabled(true);
        TextView current_round = (TextView) findViewById(R.id.currentround);
        TextView round_score = (TextView) findViewById(R.id.currentscore);
        TextView score_display = (TextView) findViewById(R.id.scorethisround);
        ImageView dice_pic = (ImageView) findViewById(R.id.dice_pic);
        score_display.setText(R.string.yourscorethisround);
        Random result = new Random();
        int roll_result = result.nextInt(6) + 1;
        switch (roll_result) {
            case 1:
                dice_pic.setImageResource(R.drawable.dice1);
                break;
            case 2:
                dice_pic.setImageResource(R.drawable.dice2);
                break;
            case 3:
                dice_pic.setImageResource(R.drawable.dice3);
                break;
            case 4:
                dice_pic.setImageResource(R.drawable.dice4);
                break;
            case 5:
                dice_pic.setImageResource(R.drawable.dice5);
                break;
            case 6:
                dice_pic.setImageResource(R.drawable.dice6);
                break;
        }
        if (roll_result != 1) {
            user_turn_score += roll_result;
            String score = Integer.toString(user_turn_score);
            round_score.setText(score);
            switch (roll_result) {
                case 2:
                    current_round.setText(R.string.user_roll_2);
                    break;
                case 3:
                    current_round.setText(R.string.user_roll_3);
                    break;
                case 4:
                    current_round.setText(R.string.user_roll_4);
                    break;
                case 5:
                    current_round.setText(R.string.user_roll_5);
                    break;
                case 6:
                    current_round.setText(R.string.user_roll_6);
                    break;
            }
        } else {
            current_round.setText(R.string.user_roll_1);
            round_score.setText(R.string.score_0);
            user_turn_score = 0;
            ComputerTurn();
            }
        }

    public void reset_button(View v) {
        user_lost = false;
        Button hButton = (Button) findViewById(R.id.holdbutton);
        Button rollButton = (Button) findViewById(R.id.rollbutton);
        rollButton.setEnabled(true);
        hButton.setEnabled(true);
        TextView user_total = (TextView) findViewById(R.id.yourscore);
        TextView comp_total = (TextView) findViewById(R.id.compscore);
        TextView current_round = (TextView) findViewById(R.id.currentround);
        TextView round_score = (TextView) findViewById(R.id.currentscore);
        round_score.setText(R.string.clear_string);
        current_round.setText(R.string.clear_string);
        user_score_total = 0;
        user_total.setText(R.string.score_0);
        computer_score_total = 0;
        comp_total.setText(R.string.score_0);
        user_turn_score = 0;
        computer_turn_score = 0;
    }

    public void hold_button(View v) {
        Button rButton = (Button)findViewById(R.id.resetbutton);
        Button hButton = (Button)findViewById(R.id.holdbutton);
        Button rollButton = (Button)findViewById(R.id.rollbutton);
        rButton.setEnabled(true);
        hButton.setEnabled(true);
        rollButton.setEnabled(true);
        TextView user_total = (TextView) findViewById(R.id.yourscore);
        TextView current_round = (TextView) findViewById(R.id.currentround);
        TextView round_score = (TextView) findViewById(R.id.currentscore);
        round_score.setText(R.string.score_0);
        current_round.setText(R.string.user_hold);
        user_score_total += user_turn_score;
        String score_current = Integer.toString(user_score_total);
        user_total.setText(score_current);
        user_turn_score = 0;
        if (won(user_score_total)) {
            current_round.setText(R.string.userwon);
            hButton.setEnabled(false);
            rollButton.setEnabled(false);
        } else {
            ComputerTurn();
            if (user_lost){
                TextView comp_total = (TextView) findViewById(R.id.compscore);
                round_score.setText(R.string.score_0);
                current_round.setText(R.string.clear_string);
                user_score_total = 0;
                String score_reset = Integer.toString(0);
                user_total.setText(score_reset);
                computer_score_total = 0;
                comp_total.setText(score_reset);
                user_turn_score = 0;
                computer_turn_score = 0;
            }
        }
    }


    //make ComputerTurn() recursive
    public void ComputerTurn(){
        Button rButton = (Button)findViewById(R.id.resetbutton);
        Button hButton = (Button)findViewById(R.id.holdbutton);
        Button rollButton = (Button)findViewById(R.id.rollbutton);
        rButton.setEnabled(false);
        hButton.setEnabled(false);
        rollButton.setEnabled(false);
        TextView current_round = (TextView) findViewById(R.id.currentround);
        TextView round_score = (TextView) findViewById(R.id.currentscore);
        TextView score_display = (TextView) findViewById(R.id.scorethisround);
        score_display.setText(R.string.compscorethisround);
            ImageView dice_pic = (ImageView)findViewById(R.id.dice_pic);
            Random result = new Random();
            int comp_roll_result = result.nextInt(6)+1;
            switch(comp_roll_result){
                case 1:
                    dice_pic.setImageResource(R.drawable.dice1);
                    break;
                case 2:
                    dice_pic.setImageResource(R.drawable.dice2);
                    break;
                case 3:
                    dice_pic.setImageResource(R.drawable.dice3);
                    break;
                case 4:
                    dice_pic.setImageResource(R.drawable.dice4);
                    break;
                case 5:
                    dice_pic.setImageResource(R.drawable.dice5);
                    break;
                case 6:
                    dice_pic.setImageResource(R.drawable.dice6);
                    break;
            }
            if (comp_roll_result!= 1) {
                computer_turn_score+=comp_roll_result;
                String score = Integer.toString(computer_turn_score);
                round_score.setText(score);
                switch (comp_roll_result) {
                    case 2:
                        current_round.setText(R.string.comp_roll_2);
                        break;
                    case 3:
                        current_round.setText(R.string.comp_roll_3);
                        break;
                    case 4:
                        current_round.setText(R.string.comp_roll_4);
                        break;
                    case 5:
                        current_round.setText(R.string.comp_roll_5);
                        break;
                    case 6:
                        current_round.setText(R.string.comp_roll_6);
                        break;
                }

            }else {
                current_round.setText(R.string.comp_roll_1);
                round_score.setText(R.string.score_0);
                computer_turn_score = 0;
            }
        Handler time = new Handler();
        time.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (computer_turn_score<20 && computer_turn_score!= 0) {
                    ComputerTurn();
                }
                else{
                    Button rButton = (Button)findViewById(R.id.resetbutton);
                    Button hButton = (Button)findViewById(R.id.holdbutton);
                    Button rollButton = (Button)findViewById(R.id.rollbutton);
                    rButton.setEnabled(true);
                    hButton.setEnabled(true);
                    rollButton.setEnabled(true);
                    return;
                }
            }
        },500);
        if (computer_turn_score>=20){
            Handler other_timer = new Handler();
            other_timer.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            },1000);
            TextView comp_total = (TextView) findViewById(R.id.compscore);
            round_score.setText(R.string.score_0);
            current_round.setText(R.string.comp_hold);
            computer_score_total+=computer_turn_score;
            String score_current = Integer.toString(computer_score_total);
            comp_total.setText(score_current);
            computer_turn_score = 0;
        }
        if (won(computer_score_total)){
            Handler other_timer_1 = new Handler();
            other_timer_1.postDelayed(new Runnable() {
                @Override
                public void run() {
                }
            },1000);
            current_round.setText(R.string.userlost);
            score_display.setText(R.string.clear_string);
            round_score.setText(R.string.clear_string);
            user_lost = true;
        }
    }


}

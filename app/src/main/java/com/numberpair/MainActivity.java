package com.numberpair;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    int[] btns={R.id.btn1,R.id.btn2,R.id.btn3,R.id.btn4,R.id.btn5,R.id.btn6,R.id.btn7,R.id.btn8,R.id.btn9,R.id.btn10,R.id.btn11,R.id.btn12,R.id.btn13,R.id.btn14,R.id.btn15,R.id.btn16,R.id.btn17,R.id.btn18,R.id.btn19,R.id.btn20,R.id.btn21,R.id.btn22,R.id.btn23,R.id.btn24,R.id.btn25,R.id.btn26,R.id.btn27,R.id.btn28,R.id.btn29,R.id.btn30,R.id.btn31,R.id.btn32,R.id.btn33,R.id.btn34,R.id.btn35,R.id.btn36,R.id.btn37,R.id.btn38,R.id.btn39,R.id.btn40,R.id.btn41,R.id.btn42,R.id.btn43,R.id.btn44,R.id.btn45,R.id.btn46,R.id.btn47,R.id.btn48,R.id.btn49,R.id.btn50};
    TextView Score;
    int flag =0;
    ArrayList<Integer> Numbers = new ArrayList<>();
    Button btn;
    Button fClickedButton = null;
    Button sClickedButton = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        Score= findViewById(R.id.lblScore);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        for(int i=0;i<50;i++){
            btn =(Button)findViewById(btns[i]);
            btn.setEnabled(false);
        }
    }

    int num1 = 0;
    int num2 = 0;
    int score = 0;

    boolean onGoing = false;

    public void btnClick(View view){
        if (!onGoing){
            Button Btn =(Button) findViewById(view.getId());
            int i;
            for(i=0;i<=50;i++){
                if(btns[i]==view.getId()){
                    break;
                }
            }
            Btn.setText(String.valueOf(Numbers.get(i)));

            if(num1 == 0){
                num1 = Numbers.get(i);
                fClickedButton = Btn;
                fClickedButton.setClickable(false);
            } else {
                num2 = Numbers.get(i);
                sClickedButton = Btn;

                onGoing = true;

                if(num1 == num2){
                    score += 1;

                    String formattedScore = String.format("%d", score);
                    Score.setText(formattedScore);

                    sClickedButton.setEnabled(false);
                    fClickedButton.setEnabled(false);

                    fClickedButton = null;
                    sClickedButton = null;

                    onGoing = false;

                    num1 = 0;
                    num2 = 0;

                } else {
                    Thread timer = new Thread(){
                        public void run(){
                            try {
                                Thread.sleep(750);
                            } catch (InterruptedException e) {
                                throw new RuntimeException(e);
                            } finally {
                                fClickedButton.setText("X");
                                sClickedButton.setText("X");

                                fClickedButton.setClickable(true);

                                fClickedButton = null;
                                sClickedButton = null;

                                onGoing = false;

                                num1 = 0;
                                num2 = 0;
                            }
                        }
                    }; timer.start();
                }
            }
        }
    }

    public void StartGame(View view){
        int x,y;
        Numbers.clear();
        score = 0;
        Score.setText("0");
        for(y=1;y<=2;y++){
            for(x=1; x<=25;x++){
                Numbers.add(x);
            }
        }

        for(int i=0;i<50;i++){
            btn =(Button)findViewById(btns[i]);
            btn.setEnabled(true);
            btn.setClickable(true);
            btn.setText("X");
        }

        Collections.shuffle(Numbers);

        //To put it in reset instead of play
        ((Button) view).setText("RESET");
    }

    public void ShowNum(View view){
        int i=0;
        if(Numbers.size()>0){
            btn =(Button)findViewById(view.getId());
            btn.setText("HIDE");
            if(flag==0){
                //Show
                flag=1;
                for(i=0;i<50;i++){
                    btn =(Button)findViewById(btns[i]);
                    btn.setClickable(false);
                    btn.setText(String.valueOf(Numbers.get(i)));
                }
            }else{
                //Hide
                btn =(Button)findViewById(view.getId());
                btn.setText("SHOW");
                flag=0;
                for(i=0;i<50;i++){
                    btn =(Button)findViewById(btns[i]);
                    btn.setClickable(true);

                    if (btn.isEnabled()){
                        btn.setText("X");
                    }
                }

            }
        }else{
            Toast.makeText(getApplicationContext(),"Please Click Play Button", Toast.LENGTH_LONG).show();
        }
    }
}

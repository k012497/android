package com.example.imagerating2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    final static int SIZE = 9;
    final static int REQUEST_CODE = 1000;

    ImageView[] iv = new ImageView[SIZE];
    int[] ivId = {R.id.iv01, R.id.iv02, R.id.iv03, R.id.iv04, R.id.iv05, R.id.iv06, R.id.iv07, R.id.iv08, R.id.iv09};
    private String[] imageName = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
            "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들",
            "해변에서"};
    int[] count = new int[SIZE];
    int maxIndex;

    Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnResult = findViewById(R.id.btnResult);
        btnResult.setOnClickListener(this);

        for (int i = 0 ; i < SIZE ; i++){
            iv[i] = findViewById(ivId[i]);
            iv[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv01:
                imageClicked(0);
                break;

            case R.id.iv02:
                imageClicked(1);
                break;

            case R.id.iv03:
                imageClicked(2);
                break;

            case R.id.iv04:
                imageClicked(3);
                break;

            case R.id.iv05:
                imageClicked(4);
                break;

            case R.id.iv06:
                imageClicked(5);
                break;

            case R.id.iv07:
                imageClicked(6);
                break;

            case R.id.iv08:
                imageClicked(7);
                break;

            case R.id.iv09:
                imageClicked(8);
                break;

            case R.id.btnResult:
                int rank1Id = getRank1();
                if(rank1Id == -1){
                    displayToast("아무것도 투표하지 않았습니다.");
                    break;
                }
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("rank1Id",rank1Id);
                intent.putExtra("rank1Name",imageName[maxIndex]);
                intent.putExtra("count", count);
                intent.putExtra("imageName", imageName);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    public void imageClicked(int position){
        if(count[position] == 5){
            displayToast("이미 5표를 받았습니다.");
        } else {
            count[position]++;
            displayToast(imageName[position]+"에 투표완료! \n총 " + count[position] + "표 획득!");
        }
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public int getRank1(){
        int max = count[0];
        maxIndex = 0;

        for (int i = 1 ; i < SIZE ; i++){
            if(max < count[i]){
                max = count[i];
                maxIndex = i;
            }
        }
        if(count[maxIndex] == 0) return -1;

        String picSource = "pic" + (maxIndex + 1);
        int resID = getResources().getIdentifier(picSource , "drawable", getPackageName());

        return resID;
    }
}

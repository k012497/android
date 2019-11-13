package com.example.imagerating;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    // 아이디 찾기, 이벤트 처리를 줄이기 위해 배열 사용
    private ImageView[] iv = new ImageView[9];
    private Integer[] ivId = {R.id.iv01, R.id.iv02, R.id.iv03, R.id.iv04, R.id.iv05, R.id.iv06, R.id.iv07, R.id.iv08, R.id.iv09};
    private String[] imageName = {"독서하는 소녀", "꽃장식 모자 소녀", "부채를 든 소녀",
            "이레느깡 단 베르양", "잠자는 소녀", "테라스의 두 자매", "피아노 레슨", "피아노 앞의 소녀들",
            "해변에서"};
    private int count[] = new int[9];
    final static int REQUEST_CODE = 1000;
    private Button btnResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnResult = findViewById(R.id.btnResult);

        for(int i = 0 ; i < ivId.length ; i++){
            iv[i] = findViewById(ivId[i]);
            iv[i].setOnClickListener(this);
        }

        btnResult.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnResult :
                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("count", count);
                intent.putExtra("ivName", imageName);
                startActivityForResult(intent, 1000);
                break;
            case R.id.iv01 :
                ivClicked(0);
                break;
            case R.id.iv02 :
                ivClicked(1);
                break;
            case R.id.iv03 :
                ivClicked(2);
                break;
            case R.id.iv04 :
                ivClicked(3);
                break;
            case R.id.iv05 :
                ivClicked(4);
                break;
            case R.id.iv06 :
                ivClicked(5);
                break;
            case R.id.iv07 :
                ivClicked(6);
                break;
            case R.id.iv08 :
                ivClicked(7);
                break;
            case R.id.iv09 :
                ivClicked(8);
                break;
        }// end of switch
    }

    public void ivClicked(int position){
        if(count[position] == 5){
            Toast.makeText(getApplicationContext(), "고만해라", Toast.LENGTH_SHORT).show();
        } else {
            count[position]++;
            Toast.makeText(getApplicationContext(), imageName[position] + " 투표 완료! \n총 " + count[position] +"표 획득", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent returnIntent) {
        super.onActivityResult(requestCode, resultCode, returnIntent);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            String message = returnIntent.getStringExtra("message");
            Toast.makeText(getApplicationContext(), message + "! 투표수는 초기화 됩니다.", Toast.LENGTH_SHORT).show();
            for(int i = 0 ; i < count.length ; i++){
                count[i] = 0;
            }
        }
    }
}

package com.example.userinterfacedialog2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnSignUp, btnSignIn;
    View signInView, signUpView;
    TextView edt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        edt = findViewById(R.id.edt);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUpView = View.inflate(MainActivity.this, R.layout.signup_dialog, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                final AlertDialog ad = dialog.create();
                ad.setTitle("WELCOME");
                ad.setView(signUpView);
                ad.setIcon(R.mipmap.sunny);

                final EditText edtNewId = signUpView.findViewById(R.id.edtNewId);
                final EditText edtNewPw = signUpView.findViewById(R.id.edtNewPw);
                final EditText edtNewName = signUpView.findViewById(R.id.edtNewName);
                final Button btnOk = signUpView.findViewById(R.id.btnOk);
                final Button btnCancel = signUpView.findViewById(R.id.btnCancel);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        edt.setText(edtNewName.getText().toString() + "님, \n 회원님의 아이디는 " + edtNewId.getText().toString()
                                +",\n비밀번호는 " + edtNewPw.getText().toString() + "입니다. ");
                        ad.dismiss();
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ad.dismiss();
                    }
                });


                ad.show();
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signInView = View.inflate(MainActivity.this, R.layout.signin_dialog, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                final AlertDialog ad = dialog.create();
                ad.setTitle("WELCOME");
                ad.setView(signInView);
                ad.setIcon(R.mipmap.sunny);

                final EditText edtId = signInView.findViewById(R.id.edtId);
                final EditText edtPw = signInView.findViewById(R.id.edtPw);
                final TextView tvId = signInView.findViewById(R.id.tvId);
                final TextView tvPw = signInView.findViewById(R.id.tvPw);
                final Button btnOk = signInView.findViewById(R.id.btnOk);
                final Button btnCancel = signInView.findViewById(R.id.btnCancel);

                btnOk.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if ( edtId.getText().toString().equals(tvId.getText().toString()) &&
                                edtPw.getText().toString().equals(tvPw.getText().toString())){
                            Toast.makeText(MainActivity.this, "welcome!", Toast.LENGTH_SHORT).show();
                            edt.setText(edtId.getText().toString() + "님, 반갑습니다. ");
                            ad.dismiss();
                        }else{
                            Toast.makeText(MainActivity.this, "enter correctly!", Toast.LENGTH_SHORT).show();
                            edt.setText(edtId.getText().toString() + "님, 누구세요?");
                            ad.dismiss();
                        }
                    }
                });

                btnCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ad.dismiss();
                    }
                });

                ad.show();


            }
        });
    }
}

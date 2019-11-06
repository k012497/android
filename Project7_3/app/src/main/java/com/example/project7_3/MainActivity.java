package com.example.project7_3;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtInputName, edtInputEmail;
    Button btnClick;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInputName = findViewById(R.id.edtName);
        edtInputEmail = findViewById(R.id.edtEmail);
        btnClick = findViewById(R.id.btnClick);

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogView = View.inflate(MainActivity.this, R.layout.dialog_activity, null);
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("user information");
                dialog.setIcon(R.mipmap.star_with_eye);
                dialog.setView(dialogView);

                final EditText edtName = dialogView.findViewById(R.id.edtName);
                final EditText edtEmail = dialogView.findViewById(R.id.edtEmail);
                edtName.setText(edtInputName.getText().toString().trim());
                edtEmail.setText(edtInputEmail.getText().toString().trim());

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        edtInputName.setText(edtName.getText().toString());
                        edtInputEmail.setText(edtEmail.getText().toString());
                    }
                });

                dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        toastView = View.inflate(MainActivity.this, R.layout.toast_activity,null);
                        Toast toast = new Toast(MainActivity.this);
                        toast.setView(toastView);

                        Display display = ((WindowManager)getSystemService(WINDOW_SERVICE)).getDefaultDisplay();

                        int x = (int)(Math.random() * display.getWidth());
                        int y = (int)(Math.random() * display.getHeight());
                        toast.setGravity(Gravity.TOP | Gravity.LEFT, x, y);
                        toast.show();
                    }
                });

                dialog.show();
            }
        });
    }
}

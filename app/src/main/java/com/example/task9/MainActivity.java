package com.example.task9;

import androidx.appcompat.app.AppCompatActivity;

import android.app.TaskStackBuilder;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.Buffer;

import javax.sql.StatementEvent;

public class MainActivity extends AppCompatActivity {
EditText ed_email , ed_password,ed_phone;
Button btn_save ,btn_restore, external;
public static final String FILE_NAME="users";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ed_email = findViewById(R.id.ed_email);
        ed_phone = findViewById(R.id.ed_phone);
        ed_password = findViewById(R.id.ed_password);
        btn_save = findViewById(R.id.btn_save);
        btn_restore = findViewById(R.id.btn_restore);
        external = findViewById(R.id.btn_external);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email.getText().toString();
                String phone = ed_phone.getText().toString();
                String password = ed_password.getText().toString();

                try {
                    FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                    PrintWriter pw = new PrintWriter(fos);
                    pw.println(email + " || " + phone + " || " + password + "||");
                    pw.close();
                    fos.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        btn_restore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FileInputStream fis = null;
                try {
                    fis = openFileInput(FILE_NAME);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();

                    Toast.makeText(getBaseContext(), "ملف مش موجود", Toast.LENGTH_LONG).show();
                }
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                String allText = "";
                String temp = "";
                while (true) {
                    try {
                        if (!((temp = br.readLine()) != null)) break;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    allText += temp;
                }
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    isr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplication(), allText, Toast.LENGTH_LONG).show();
            }
        });
        external.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in=new Intent(getBaseContext(),External.class);
                startActivity(in);
            }
        });


    }

    }

package com.example.task9;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Magnifier;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class External extends AppCompatActivity {
    EditText ed_email , ed_password;
    Button btn_save ,btn_restore;
    public static final String FILE_NAME="users";
    public static final int WRITE_EX_REQ_CODE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_external);
        ed_email = findViewById(R.id.ed_email);
        ed_password = findViewById(R.id.ed_password);
        btn_save = findViewById(R.id.btn_save);
        btn_restore = findViewById(R.id.btn_restore);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
        != PackageManager.PERMISSION_GRANTED) {

        }
        else{
            String [] permission={Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this,permission,WRITE_EX_REQ_CODE);
        }



        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ed_email.getText().toString();
                String password = ed_password.getText().toString();
                if(isExternalStorageWritable()){
                    try{
                    File st_f=Environment.getExternalStoragePublicDirectory
                            (Environment.DIRECTORY_DOWNLOADS);
                    File f=new File(st_f , FILE_NAME);
                    try {
                        f.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }

            }
        });

    }

    public boolean isExternalStorageWritable(){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED)){
            return true;}
        return false;
    }
    public boolean isExternalStorageReadable(){
        String state = Environment.getExternalStorageState();
        if(state.equals(Environment.MEDIA_MOUNTED) ||
                state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            return true;}
        return false;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case WRITE_EX_REQ_CODE:
                if(grantResults.length>0&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                    Toast.makeText(getBaseContext(),"allow this permission",Toast.LENGTH_LONG).show();
                }
                return;
        }

    }
}
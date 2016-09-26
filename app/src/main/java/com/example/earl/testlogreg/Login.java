package com.example.earl.testlogreg;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Earl on 22/09/2016.
 */
public class Login extends Activity {
    TestDb db;
    EditText emailtxt, passtxt;
    Button btnLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log);
        emailtxt = (EditText)findViewById(R.id.emailLogText);
        passtxt = (EditText)findViewById(R.id.passLogText);
        btnLog = (Button)findViewById(R.id.logBtn);
        final Context CTX = this;

        btnLog.setOnClickListener(new View.OnClickListener() {
            String email = emailtxt.getText().toString().trim();
            String pass = passtxt.getText().toString().trim();
            @Override
            public void onClick(View view) {
                String email = emailtxt.getText().toString();
                String pass = passtxt.getText().toString();
                TestDb db = new TestDb(CTX);
                Cursor cr = db.getInfo(db);
                cr.moveToFirst();
                boolean logstat = false;

                do
                {
                    if(email.equals(cr.getString(0))&&(pass.equals(cr.getString(1))))
                    {
                        logstat = true;
                    }
                }while(cr.moveToNext());
                if(logstat == true)
                {
                    Toast.makeText(Login.this, "Log in Success", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Login.this, Test.class);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(Login.this, "Log in Failed", Toast.LENGTH_LONG).show();
                    finish();
                }

            }
        });
    }
}

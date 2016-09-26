package com.example.earl.testlogreg;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends AppCompatActivity {
    TestDb myDb;
    EditText editEmail, editPassword, editCPassword, editFname, editLname, editUname;
    Button btnAddData;
    String email, pass, cpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new TestDb(this);

        editEmail = (EditText)findViewById(R.id.emailText);
        editPassword = (EditText)findViewById(R.id.pwText);
        editCPassword = (EditText)findViewById(R.id.cpwText);
        btnAddData = (Button)findViewById(R.id.regBtn);
        editFname = (EditText)findViewById(R.id.fnameText);
        editLname = (EditText)findViewById(R.id.lnameText);
        editUname = (EditText)findViewById(R.id.unameText);
        editFname.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                        if (charSequence.equals("")){
                            return charSequence;
                        }
                        if (charSequence.toString().matches("[a-zA-Z ]+")){
                            return charSequence;
                        }
                        return "";
                    }
                }
        });
        editLname.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                        if (charSequence.equals("")){
                            return charSequence;
                        }
                        if (charSequence.toString().matches("[a-zA-Z ]+")){
                            return charSequence;
                        }
                        return "";
                    }
                }
        });
        AddData();
    }

    public void AddData() {
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        email = editEmail.getText().toString();
                        pass = editPassword.getText().toString();
                        cpass = editCPassword.getText().toString();

                            if (!(pass.equals(cpass))) {
                                Toast.makeText(Register.this, "Passwords do not match", Toast.LENGTH_LONG).show();
                                editEmail.setText("");
                                editPassword.setText("");
                                editCPassword.setText("");
                            } else {

                                boolean isInserted = myDb.insetData(editFname.getText().toString(), editLname.getText().toString(), editUname.getText().toString(), editEmail.getText().toString(), editPassword.getText().toString());
                                if (isInserted == true)
                                    Toast.makeText(Register.this, "Data Inserted", Toast.LENGTH_LONG).show();

                                Intent intent = new Intent(Register.this, Login.class);
                                startActivity(intent);
                                finish();
                            }

                        }


                });
    }
}

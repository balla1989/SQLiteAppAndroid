package com.example.sqliteapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks;
    Button btnAddData, btnViewAll;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb= new DatabaseHelper(this);
        //editName=(EditText)findViewById(R.i)
        editName=(EditText)findViewById(R.id.editTextName);
        editSurname=(EditText)findViewById(R.id.editTextSurname);
        editMarks=(EditText)findViewById(R.id.editTextMarks);
        btnAddData=(Button)findViewById(R.id.button_Add);
        btnViewAll=(Button)findViewById(R.id.btnViewAllData);
        AddData();
    }

    public void AddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Boolean isInserted= myDb.insertData(editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());
                        if(isInserted==true)
                            Toast.makeText(MainActivity.this, "Data Inserted",Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not inserted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void viewAll()
    {
        btnViewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor result= myDb.getAllData();
                        if(result.getCount()==0) {
                            // show message
                            return;
                        }
                        else
                        {
                            StringBuffer buffer = new StringBuffer();
                            while(result.moveToNext()){
                                buffer.append("ID:"+result.getString(0)+"\n");
                                buffer.append("Name:"+result.getString(1)+"\n");
                                buffer.append("Surname:"+result.getString(2)+"\n");
                                buffer.append("Marks:"+result.getString(3)+"\n");

                            }
                        }
                    }
                }
        );
    }
}



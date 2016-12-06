package com.example.cozma.socializareusv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cozma.socializareusv.CustomTextViews.BorderEditText;
import com.example.cozma.socializareusv.DataBase.Client;
import com.example.cozma.socializareusv.DataBase.DataBaseHandler;
import com.example.cozma.socializareusv.TabActivity.ClientTabActivity;
import com.example.cozma.socializareusv.Utils.Util;

public class RegisterActiviry extends AppCompatActivity implements View.OnClickListener {

    BorderEditText name,lastName,phone,university;
    Button save;
    String dataBase_name,daraBase_lastname,dataBase_university,dataBase_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        initComponent();
    }

    private void initComponent() {
        name = (BorderEditText) findViewById(R.id.registerActivity_field_name);
        lastName= (BorderEditText) findViewById(R.id.registerActivity_field_lastName);
        phone= (BorderEditText) findViewById(R.id.registerActivity_field_phone);
        university = (BorderEditText) findViewById(R.id.registerActivity_field_university);
        save= (Button) findViewById(R.id.registerActivity_button_save);
    }
    public void addInDataBase(){
        dataBase_name = name.getText().toString();
        daraBase_lastname=lastName.getText().toString();
        dataBase_phone= phone.getText().toString();
        dataBase_university= university.getText().toString();

        DataBaseHandler db = new DataBaseHandler(this);
        db.addStudnet(new Client(daraBase_lastname,dataBase_name,dataBase_university,dataBase_phone));
        Util.openActivityClosingStack(this,ClientTabActivity.class);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case   R.id.registerActivity_button_save:
             //   addInDataBase();
//                Util.openActivityClosingStack(this,ClientTabActivity.class);
                break;
        }
    }
}

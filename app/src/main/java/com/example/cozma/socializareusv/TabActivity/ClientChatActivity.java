package com.example.cozma.socializareusv.TabActivity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.cozma.socializareusv.DataBase.Client;
import com.example.cozma.socializareusv.DataBase.DataBaseHandler;
import com.example.cozma.socializareusv.R;

import java.util.ArrayList;
import java.util.List;

public class ClientChatActivity extends Fragment {
    View mainView;
     RecyclerView recyclerViewStudent;
     RecyclerView.LayoutManager layoutManager;

    List<Client> clients = new ArrayList<>();
    DataBaseHandler db =new DataBaseHandler(getContext());


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (mainView == null) {
            mainView = inflater.inflate(R.layout.activity_client_chat, container, false);
            ViewListStudents();
        }
        return mainView;
    }


    public void ViewListStudents(){
        recyclerViewStudent =(RecyclerView)mainView.findViewById(R.id.activity_chat_recyclerView);

        recyclerViewStudent.setHasFixedSize(true);
        layoutManager =  new LinearLayoutManager(getContext());
        recyclerViewStudent.setLayoutManager(layoutManager);
    }

    public void readDataBase(){
        clients = db.getAllStudents();
        for (Client cl : clients){
            String log = "ID"+cl.get_id() + ",Name: " + cl.get_firstname()
                    + " ,Prenume: " + cl.get_lastName() + ",Telefon: " + cl.get_phone() + ", University" + cl.get_university();
            Log.d("Studnet",log);
        }
    }
}

package com.example.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Activity2 extends AppCompatActivity {
    private RecyclerView userRecyclerView;
    private UserAdapter userAdapter;
    private List<User> userList;
    private Button backButton;

    public Activity2() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        //ánh xạ
        userRecyclerView = findViewById(R.id.userRecyclerView);
        backButton = findViewById(R.id.backButton);
        //khởi tạo
        userList = new ArrayList<>();
        // nhận dữ liệu từ Activity Main sang
        Intent intent = getIntent();
        List<User> userList = intent.getParcelableArrayListExtra("userList");
        userAdapter = new UserAdapter(userList);
        userRecyclerView.setAdapter(userAdapter);
        userRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        //sự kiện click vào nút Back
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}

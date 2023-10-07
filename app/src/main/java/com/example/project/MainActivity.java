package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText tvname, tvdate;
    Button btnadd;
    List<User> userList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //ánh xạ
        tvname = findViewById(R.id.tvname);
        tvdate = findViewById(R.id.tvdate);
        btnadd = findViewById(R.id.btnadd);
        // Khởi tạo userList chứa danh sách User
        userList = new ArrayList<>();

        tvdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Lấy ngày tháng năm từ bàn phím
                final Calendar calendar = Calendar.getInstance();
                int mYear = calendar.get(Calendar.YEAR);
                int mMonth = calendar.get(Calendar.MONTH);
                int mDay = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                tvdate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tvname.getText().toString().trim();
                String birthdate = tvdate.getText().toString();
                // Xuất năm từ ngày tháng năm sinh
                int year = Integer.parseInt(birthdate.split("/")[2]); //từ ngày tháng năm tạo thành mảng, lấy phần tử thứ 3 của mảng
                // Tính tuổi
                int age = calculateAge(year);
                //Thêm tuổi vào lớp User
                User newUser = new User(name,age);
                userList.add(newUser);
                // Clear các trường sau khi thêm
                tvname.getText().clear();
                tvdate.getText().clear();
                // Gửi dữ liệu sang Activity 2
                Intent intent = new Intent(MainActivity.this, Activity2.class);
                intent.putParcelableArrayListExtra("userList", (ArrayList<User>) userList);
                startActivity(intent);
            }
        });
    }
    private int calculateAge(int year) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - year;
    }


}
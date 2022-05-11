package com.example.appdatban;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {
    Connection connect;
    EditText edtTenKH,edtSDT;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        ActionLogin();
    }
    public void init()
    {
        edtTenKH = findViewById(R.id.edtTenKhachHang);
        edtSDT = findViewById(R.id.edtSDT);
        btnLogin = findViewById(R.id.btnDangNhap);
    }
    public void ActionLogin()
    {
        try {
            btnLogin.setOnClickListener(v -> {
                if(edtTenKH.length()>0&&edtSDT.length()>0){
                try {
                    ConnectionHelper connnectionHelper = new ConnectionHelper();
                    connect = connnectionHelper.getconnection();
                    String query = "insert into KhachHang values('" + edtTenKH.getText().toString() + "','" + edtSDT.getText().toString() + "')";
                    Statement st = connect.createStatement();
                    st.executeUpdate(query);
                } catch (Exception ex) {
                    Log.e("error", ex.getMessage());
                }
                Intent intent = new Intent(this,TrangChu.class);
                intent.putExtra("TenKH",edtTenKH.getText().toString());
                intent.putExtra("Sdt",edtSDT.getText().toString());
                startActivity(intent);
                finish();
            }
                else Toast.makeText(MainActivity.this,"Chưa nhập đủ thông tin",Toast.LENGTH_LONG).show();
            });
        }catch (Exception ex)
        {
            Log.e("Error",ex.getMessage());
        }
    }
}
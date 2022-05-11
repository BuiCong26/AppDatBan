package com.example.appdatban;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appdatban.Adapter.AdapterMonAn;
import com.example.appdatban.Adapter.MonAn;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TrangChu extends AppCompatActivity {
    TextView txtTenKH,txtSdt;
    private ArrayList<MonAn> arrayList;
    private AdapterMonAn monanAdapter;
    private RecyclerView rcMonAn;
    Connection connect;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trang_chu);
        txtTenKH = findViewById(R.id.txtTenKH);
        txtSdt = findViewById(R.id.txtsdt);
        txtTenKH.setText("Xin Chào: " + getIntent().getStringExtra("TenKH"));
        txtSdt.setText(getIntent().getStringExtra("Sdt"));
        txtSdt.setVisibility(View.GONE);
        Toast.makeText(TrangChu.this,"Đăng Nhập Thành Công",Toast.LENGTH_LONG).show();
        prepareMovieData();
        AdapterMonAn monanAdapter = new AdapterMonAn(this, arrayList, new AdapterMonAn.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                MonAn getitem = arrayList.get(position);
                Intent intent = new Intent(TrangChu.this,ThongTinDatBan.class);
                intent.putExtra("sdt",txtSdt.getText().toString());
                intent.putExtra("tenMonAn",getitem.getTenMonAn());
                startActivity(intent);
            }
        });
        rcMonAn = (RecyclerView) findViewById(R.id.rcMonAn);
        rcMonAn.setAdapter(monanAdapter);
        rcMonAn.setLayoutManager(new LinearLayoutManager(this));


    }
    private void prepareMovieData() {

        try {
            ConnectionHelper connnectionHelper = new ConnectionHelper();
            connect = connnectionHelper.getconnection();
            String query = "select TenMon,Mota,Photo from MonAn_DoUong";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);
            arrayList = new ArrayList<>();
            while (rs.next())
            {
                MonAn monan = new MonAn(rs.getString("TenMon"),rs.getString("Mota"),rs.getString("Photo"));
                arrayList.add(monan);
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
    }
}
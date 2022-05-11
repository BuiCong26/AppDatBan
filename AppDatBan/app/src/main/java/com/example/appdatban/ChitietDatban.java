package com.example.appdatban;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class ChitietDatban extends AppCompatActivity {
    private TextView idMonAn,idBan,idKH,txtNgayOrders,txtOrders;
    private EditText GhiChu;
    Connection connect;
    private Button btnXacNhan1;
    SimpleDateFormat NgaDatBan = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chitiet_datban);
        idMonAn = findViewById(R.id.txtidMonAn1);
        idBan = findViewById(R.id.txtidBan1);
        idKH = findViewById(R.id.txtidKH1);
        GhiChu = findViewById(R.id.edtGhiChu);
        Calendar myCalendar = Calendar.getInstance();
        txtNgayOrders = findViewById(R.id.txtNgayOrders1);
        txtNgayOrders.setText(NgaDatBan.format(myCalendar.getTime()));
        idMonAn.setText(getIntent().getStringExtra("idMonAn"));
        idBan.setText(getIntent().getStringExtra("idBan"));
        idKH.setText(getIntent().getStringExtra("idKH"));
        txtOrders = findViewById(R.id.txtidOrders);
        btnXacNhan1 = findViewById(R.id.btnXacNhan1);
        txtOrders.setVisibility(View.GONE);
        ActionXacNhan1();
    }
    public void ActionXacNhan1()
    {

        btnXacNhan1.setOnClickListener(v -> {
            try {
                ConnectionHelper connnectionHelper = new ConnectionHelper();
                connect = connnectionHelper.getconnection();
                String query = "insert into Orders values('" + txtNgayOrders.getText().toString() + "','" + idKH.getText().toString() + "','" + idBan.getText().toString() + "')";
                Statement st = connect.createStatement();
                st.executeUpdate(query);


                String query1 = "select idOrder from Orders where NgaytaoOrder ='" + txtNgayOrders.getText().toString() + "'";
                Statement st1 = connect.createStatement();
                ResultSet rs1 = st1.executeQuery(query1);
                while (rs1.next())
                {
                    txtOrders.setText(rs1.getString("idOrder"));
                }


                String query2 = "insert into ChitietOrders values('" + txtOrders.getText().toString() + "','" + idMonAn.getText().toString() + "','" + GhiChu.getText().toString() + "')";
                Statement st2 = connect.createStatement();
                st2.executeUpdate(query2);

            }catch (Exception ex)
            {
                Log.e("Error",ex.getMessage());
            }
            Intent intent = new Intent();
            finish();

        });
    }
}
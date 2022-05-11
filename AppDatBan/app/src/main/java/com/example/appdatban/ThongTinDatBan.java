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
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Locale;


public class ThongTinDatBan extends AppCompatActivity {
    TextView txtTenban,txtDateAndTime,txthetTime,sdt,IdKH,idBan,tenmonan,idMonan;
    Connection connect;
    Calendar myCalendar = Calendar.getInstance();
    SimpleDateFormat fmtDateAndTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
    Button btnXacNhan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thong_tin_dat_ban);
        txtTenban = findViewById(R.id.txtTenban);
        Date currentTime = Calendar.getInstance().getTime();
        txtDateAndTime = (TextView) findViewById(R.id.txtSetTime);
        txthetTime = (TextView) findViewById(R.id.txthetTime);
        Button btnDate = (Button) findViewById(R.id.btnNgayDatBan);
        btnDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new DatePickerDialog(ThongTinDatBan.this, d,
                        myCalendar.get(Calendar.YEAR),
                        myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        Button btnTime = (Button) findViewById(R.id.btnTimeDatBan);
        btnTime.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                new TimePickerDialog(ThongTinDatBan.this, t,
                        myCalendar.get(Calendar.HOUR_OF_DAY),
                        myCalendar.get(Calendar.MINUTE), true).show();
            }
        });
        /////
        try {
            ConnectionHelper connnectionHelper = new ConnectionHelper();
            connect = connnectionHelper.getconnection();
            String query = "select tenBan,Thoigian_Hethan from Ban INNER JOIN DatBan ON Datban.idBan = Ban.idBan";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next())
            {
                if(currentTime.after(rs.getTime("Thoigian_Hethan")))
                {
                    txtTenban.setText(rs.getString("tenBan"));
                };
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }
        ////SoDienThoai
        sdt = findViewById(R.id.sdt);
        sdt.setText(getIntent().getStringExtra("sdt"));
        sdt.setVisibility(View.GONE);
        IdKH = findViewById(R.id.idKhachHang);
        IdKH.setVisibility(View.GONE);
        idBan = findViewById(R.id.idBan);
        idBan.setVisibility(View.GONE);
        tenmonan = findViewById(R.id.Tenmonan);
        tenmonan.setText(getIntent().getStringExtra("tenMonAn"));
        tenmonan.setVisibility(View.GONE);
        idMonan = findViewById(R.id.idMonan);
        idMonan.setVisibility(View.GONE);
        btnXacNhan = findViewById(R.id.btnXacNhan);
        try {
            ConnectionHelper connnectionHelper = new ConnectionHelper();
            connect = connnectionHelper.getconnection();
            String query = "select idKhachHang from KhachHang where PhoneNumber ='" + sdt.getText().toString() + "'";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(query);
            while(rs.next()) {
                IdKH.setText(rs.getString("idKhachHang"));
            }
            String query1 = "select idBan from Ban where tenBan ='" + txtTenban.getText().toString() + "'";
            ResultSet rs1 = st.executeQuery(query1);
            while(rs1.next()) {
                idBan.setText(rs1.getString("idBan"));
            }
            String query2 = "select idMonAn from MonAn_DoUong where TenMon = N'" + tenmonan.getText().toString() + "'";
            ResultSet rs2 = st.executeQuery(query2);
            while(rs2.next()) {
                idMonan.setText(rs2.getString("idMonAn"));
            }
        } catch (Exception ex) {
            Log.e("error", ex.getMessage());
        }

        updateLabel();
        ActionXacNhan();
    }
    public void ActionXacNhan()
    {

        btnXacNhan.setOnClickListener(v -> {
            try {
            ConnectionHelper connnectionHelper = new ConnectionHelper();
            connect = connnectionHelper.getconnection();
            String query = "insert into Datban values('" + IdKH.getText().toString() + "','" + idBan.getText().toString() + "','" + txtDateAndTime.getText().toString() + "','" + txthetTime.getText().toString() + "')";
            Statement st = connect.createStatement();
            st.executeUpdate(query);
            }catch (Exception ex)
            {
                Log.e("Error",ex.getMessage());
            }
            Intent intent = new Intent(this,ChitietDatban.class);
            intent.putExtra("idKH",IdKH.getText().toString());
            intent.putExtra("idBan",idBan.getText().toString());
            intent.putExtra("idMonAn",idMonan.getText().toString());
            startActivity(intent);
            finish();
        });
    }
    DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
    {
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
            updateLabel1();
        }
    };
    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener()
    {
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            myCalendar.set(Calendar.MINUTE, minute);
            updateLabel();
            myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay+2);
            updateLabel1();
        }
    };
    private void updateLabel() {
        txtDateAndTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
    }
    private void updateLabel1()
    {
        txthetTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
    }
}
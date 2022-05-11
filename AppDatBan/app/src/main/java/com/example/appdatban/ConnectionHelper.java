package com.example.appdatban;

import android.annotation.SuppressLint;
import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {
    String Ip,DB,UseName,Pass,Port;
    @SuppressLint("NewApi")
    public Connection getconnection()
    {
        Ip="192.168.1.7";
        DB="DatBan_db";
        UseName="zupsu";
        Pass="1";


        StrictMode.ThreadPolicy threadPolicy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(threadPolicy);
        java.sql.Connection connection=null;
        String ConnectionURL=null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            ConnectionURL = "jdbc:jtds:sqlserver://" + Ip + "/" + DB + ";user=" + UseName + ";"+"password=" + Pass + ";";
            connection = DriverManager.getConnection(ConnectionURL);

        }catch (SQLException ex)
        {
            Log.e("error Sql",ex.getMessage());
        }catch (ClassNotFoundException se)
        {
            Log.e("error class", se.getMessage());
        }catch (Exception e)
        {
            Log.e("error", e.getMessage());
        }
        return connection;
    }
}

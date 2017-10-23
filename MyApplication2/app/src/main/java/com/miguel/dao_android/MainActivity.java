package com.miguel.dao_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.miguel.dao_android.DataAccessLayer.DataAccessObjects.UserDAO;
import com.miguel.dao_android.Domain_Layer.User;

import java.util.Date;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        User u = new User();
        u.setemail("ef@hotmail.com");
        u.setusername("lofws");
        u.setcreatedDate(new Date());
        UserDAO.getInstance().setContext(this).create(u);

    }
}

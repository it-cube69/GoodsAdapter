package ru.itcube.myapplication;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        createSchema();

        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .replace(R.id.frame, new GoodsListFragment(), "goodsList")
                .commit();
    }

    private void createSchema() {
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("goods", MODE_PRIVATE, null);
        sqLiteDatabase.execSQL("drop table if exists goods");
        sqLiteDatabase.execSQL("create table if not exists goods\n" +
                "(\n" +
                "\ttitle varchar(1000),\n" +
                "\tprice int,\n" +
                "\timage varchar(1000)\n" +
                ");");

        sqLiteDatabase.execSQL("insert into goods(title,price,image) values('Milk',65,'milk')");
        sqLiteDatabase.execSQL("insert into goods(title,price,image) values('Bread',40,'bread')");
        sqLiteDatabase.execSQL("insert into goods(title,price,image) values('Cheese',800,'cheese')");
        sqLiteDatabase.execSQL("insert into goods(title,price,image) values('Eggs',60,'eggs')");
    }
}
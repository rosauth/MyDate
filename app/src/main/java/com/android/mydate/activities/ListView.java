package com.android.mydate.activities;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;

import com.android.mydate.R;
import com.android.mydate.sql.DatabaseHelper;

public class ListView extends AppCompatActivity{

    private DatabaseHelper mHelper;
    private ArrayAdapter mAdapter;
    private android.widget.ListView lv;
    EditText inputSearch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mHelper = new DatabaseHelper(this);


        lv = (android.widget.ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);
        //updateUI();
        filterData();

    }

    public void filterData(){
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                ListView.this.mAdapter.getFilter().filter(cs);
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }
        });
    }


//    @SuppressLint("ResourceType")
//    private void updateUI(){
//        ArrayList<String> taskList = new ArrayList<>();
//        SQLiteDatabase db = mHelper.getReadableDatabase();
//        Cursor cursor = db.query(DatabaseHelper.TABLE_USER, new String[] {DatabaseHelper.COLUMN_USER_ID,
//                DatabaseHelper.COLUMN_USER_NAME},null,null,null,null,null);
//        mAdapter = new ArrayAdapter<>(this, R.id.product_name, taskList);
//        while (cursor.moveToNext()){
//            int idx = cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_NAME);
//            taskList.add(cursor.getString(idx));
//        }
//        if (mAdapter == null){
//            mAdapter = new ArrayAdapter<>(this,
//                    R.layout.activity_main,
//                    R.id.list_view,
//                    taskList);
//            lv.setAdapter(mAdapter);
//        }
//        else {
//            mAdapter.clear();
//            mAdapter.addAll(taskList);
//            mAdapter.notifyDataSetChanged();
//        }
//
//        cursor.close();
//        db.close();
//    }

}

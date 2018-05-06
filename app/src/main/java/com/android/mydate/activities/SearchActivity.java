package com.android.mydate.activities;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.SimpleCursorAdapter;

import com.android.mydate.R;
import com.android.mydate.sql.DatabaseHelper;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private DatabaseHelper mHelper;
    private ListAdapter mAdapter;
    private android.widget.ListView lv;
    EditText inputSearch;
    Button search_button;
    Cursor cursor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        mHelper = new DatabaseHelper(this);

        lv = (android.widget.ListView) findViewById(R.id.list_view);
        inputSearch = (EditText) findViewById(R.id.inputSearch);

        search_button = (Button) findViewById(R.id.search_button);
        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateUI();
            }
        });
//        filterData();

    }


//    public void filterData(){
//        inputSearch.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
//                SearchActivity.this.mAdapter.getFilter().filter(cs);
//            }
//
//            @Override
//            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable arg0) {
//
//            }
//        });
//    }


    @SuppressLint("ResourceType")
    private void updateUI() {
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        String  a = inputSearch.getText().toString();

        try {
            cursor = db.rawQuery("Select user_name, user_gender FROM user WHERE user_name LIKE ?",
                    new String[]{"%" + a + "%"});
            mAdapter = new SimpleCursorAdapter(SearchActivity.this, R.layout.content_list_search, cursor,
                    new String[]{"user_name", "user_gender"}, new int[]{R.id.search_name, R.id.search_gender});
            lv.setAdapter(mAdapter);
            lv.setTextFilterEnabled(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
//                DatabaseHelper.COLUMN_USER_NAME},null,null,null,null,null);
//        mAdapter = new ArrayAdapter<>(this, R.id.product_name, taskList);
//        while (cursor.moveToNext()){
//            int idx = cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_NAME);
//            taskList.add(cursor.getString(idx));
//        }
//        if (mAdapter == null){
//            mAdapter = new ArrayAdapter<>(this,
//                    R.layout.activity_search,
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

}

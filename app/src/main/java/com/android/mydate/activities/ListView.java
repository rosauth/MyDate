package com.android.mydate.activities;

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

import java.util.ArrayList;

public class ListView extends AppCompatActivity{

    public DatabaseHelper mHelper;
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
        updateUI();
        filterData();
//        getJSON();

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

//    private void getJSON() {
//        @SuppressLint("StaticFieldLeak")
//        class GetJSON extends AsyncTask<Void, Void, String> {
//
//            @Override
//            protected void onPreExecute() {
//                super.onPreExecute();
//            }
//
//            @Override
//            protected void onPostExecute(String s) {
//                super.onPostExecute(s);
//
//                try {
//                    loadIntoListView(s);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            protected String doInBackground(Void... voids) {
//                try {
//                    URL url = new URL("http://172.16.112.125/Android/getData.php");
//                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
//                    StringBuilder sb = new StringBuilder();
//                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));
//                    String json;
//                    while ((json = bufferedReader.readLine()) != null) {
//                        sb.append(json).append("\n");
//                    }
//                    return sb.toString().trim();
//                } catch (Exception e) {
//                    return null;
//                }
//            }
//        }
//        GetJSON getJSON = new GetJSON();
//        getJSON.execute();
//    }
//
//    private void loadIntoListView(String json) throws JSONException {
//        JSONArray jsonArray = new JSONArray(json);
//        String[] name = new String[jsonArray.length()];
//        for (int i = 0; i < jsonArray.length(); i++) {
//            JSONObject obj = jsonArray.getJSONObject(i);
//            name[i] = obj.getString("name");
//        }
//        mAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, name);
//        lv.setAdapter(mAdapter);
//    }


    @SuppressLint("ResourceType")
    private void updateUI(){
        ArrayList<String> taskList = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER, new String[] {
                DatabaseHelper.COLUMN_USER_NAME},null,null,null,null,null);
        mAdapter = new ArrayAdapter<>(this, R.id.product_name, taskList);
        while (cursor.moveToNext()){
            int idx = cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_NAME);
            taskList.add(cursor.getString(idx));
        }
        if (mAdapter == null){
            mAdapter = new ArrayAdapter<>(this,
                   android.R.layout.simple_list_item_1,
                    taskList);
            lv.setAdapter(mAdapter);
        }
        else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

        cursor.close();
        db.close();
    }


}

package com.android.mydate.activities;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.mydate.R;
import com.android.mydate.sql.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by rosa on 5/4/2018.
 */

public class MyProfileActivity extends AppCompatActivity {
    ImageView mUploadImage;
    Integer REQUEST_CAMERA=1, SELECT_FILE=0;
    FloatingActionButton fab, fab_chat, fab_poke;
    Animation FabOpen, FabClose, FabRClockwise, FabRanticlockwise;
    boolean isOpen = false;

    private DatabaseHelper mHelper;
    private ArrayAdapter<String> mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

//        mUploadImage = (ImageView) findViewById(R.id.upload_image);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab_chat = (FloatingActionButton) findViewById(R.id.fab_chat);
        fab_poke = (FloatingActionButton) findViewById(R.id.fab_poke);
        //FabOpen = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.);
        //FabClose = AnimationUtils.loadAnimation(getApplicationContext(), R.anim. );
        //FabRClockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.);
        //FabRanticlockwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim. );


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              SelectImage();
                Intent searchIntent = new Intent(MyProfileActivity.this, SearchActivity.class);
                startActivity(searchIntent);
                Toast.makeText(getBaseContext(), "Search Profile", Toast.LENGTH_SHORT).show();
            }
        });

        mHelper = new DatabaseHelper(this);
    }

//    private void SelectImage() {
//        final CharSequence[] items = {"Camera", "Gallery", "Cancel"};
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(MyProfileActivity.this);
//        builder.setTitle("Add Image");
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                if(items[i].equals("Camera")){
//                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                    startActivityForResult(intent, REQUEST_CAMERA);
//
//                } else if (items[i].equals("Gallery")) {
//                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                    intent.setType("image/*");
//                    startActivityForResult(intent.createChooser(intent, "Select File"), SELECT_FILE);
//
//                } else if (items[i].equals("Cancel")){
//                    dialogInterface.dismiss();
//                }
//            }
//        });
//        builder.show();
//    }
//
//    @Override
//    public void onActivityResult(int requestCode, int resultCode, Intent data){
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if(resultCode==Activity.RESULT_OK){
//
//            if(requestCode == REQUEST_CAMERA){
//                Bundle bundle = data.getExtras();
//                final Bitmap bmp = (Bitmap) bundle.get("data");
//                mUploadImage.setImageBitmap(bmp);
//
//            } else if (requestCode ==SELECT_FILE) {
//                Uri selectedImageUri = data.getData();
//                mUploadImage.setImageURI(selectedImageUri);
//            }
//        }
//    }

    private void updateUI(){
        ArrayList<String> list_profile = new ArrayList<>();
        SQLiteDatabase db = mHelper.getReadableDatabase();
        Cursor cursor = db.query(DatabaseHelper.TABLE_USER,
                new String[]{DatabaseHelper.COLUMN_USER_NAME, DatabaseHelper.COLUMN_USER_GENDER,
                        DatabaseHelper.COLUMN_USER_AGE, DatabaseHelper.COLUMN_USER_INTEREST},
                null,null,null,null, null);

        while (cursor.moveToNext()){
            int index = cursor.getColumnIndex(DatabaseHelper.COLUMN_USER_NAME);
            list_profile.add(cursor.getString(index));
        }

        if ( mAdapter == null){
            mAdapter = new ArrayAdapter<String>(this, R.layout.content_profile, R.id.tv_name,
                    list_profile);

        }

        cursor.close();
        db.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_logout) {
            Intent logout =  new Intent(MyProfileActivity.this, LoginActivity.class);
            startActivity(logout);
//            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }

}


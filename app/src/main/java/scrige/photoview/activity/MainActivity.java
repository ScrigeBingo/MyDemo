package scrige.photoview.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import scrige.photoview.R;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);


    }

    public void onImageClick(View view) {

        Intent addPhoto = new Intent(this, FolderListActivity.class);

        startActivity(addPhoto);
    }

    public void onVideoClick(View view) {

        Toast.makeText(this, "暂未开放此功能", Toast.LENGTH_SHORT).show();
    }

    public void onMusicClick(View view) {

        Toast.makeText(this, "暂未开放此功能", Toast.LENGTH_SHORT).show();
    }


}

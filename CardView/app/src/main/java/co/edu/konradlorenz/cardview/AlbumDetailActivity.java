package co.edu.konradlorenz.cardview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class AlbumDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album_detail);
        ImageView img = findViewById(R.id.ImagenAlbum);

        Intent intent= getIntent();
        Bundle b =intent.getExtras();

        if(b!=null){

            img.setImageResource(b.getInt("IMG"));
        }






    }
}

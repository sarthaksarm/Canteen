package com.example.sarthakmishra.canteen1;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.Toast;

public class DetailActivity extends AppCompatActivity {

    Toolbar mToolbar;
    ImageView mImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mToolbar = (Toolbar) findViewById(R.id.toolbar2);
        mImageView = (ImageView) findViewById(R.id.imageView2);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle mBundle = getIntent().getExtras();

        if (mBundle != null) {
            mToolbar.setTitle(mBundle.getString("countryName"));
            mImageView.setImageResource(mBundle.getInt("countryFlag"));
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main3,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==android.R.id.home)
            onBackPressed();
        if (id == R.id.about3) {
            Intent i = new Intent(DetailActivity.this, about.class);
            startActivity(i);
            return true;
        }
        if(id==R.id.contact3){
            Toast.makeText(DetailActivity.this,"Now You can write to developers directly!",Toast.LENGTH_LONG).show();
            Intent i= new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:08095030481"));
            i.putExtra("address", "08095030481");
            startActivity(i);
            return true;
        }
        if (id == R.id.exit3) {
            Intent i = new Intent(DetailActivity.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(DetailActivity.this, "Click again to exit from the app!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
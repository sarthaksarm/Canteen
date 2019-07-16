package com.example.sarthakmishra.canteen1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class developers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
            Intent i = new Intent(developers.this, about.class);
            startActivity(i);
            return true;
        }
        if(id==R.id.contact3){
            Toast.makeText(developers.this,"Now You can write to developers directly!",Toast.LENGTH_LONG).show();
            Intent i= new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:08095030481"));
            i.putExtra("address", "08095030481");
            startActivity(i);
            return true;
        }
        if (id == R.id.exit3) {
            Intent i = new Intent(developers.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(developers.this, "Click again to exit from the app!", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
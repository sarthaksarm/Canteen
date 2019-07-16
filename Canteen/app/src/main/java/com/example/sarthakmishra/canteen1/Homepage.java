package com.example.sarthakmishra.canteen1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class Homepage extends AppCompatActivity {
    private CardView ordcard,developcard,feedbackcard,aboutcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        ordcard=findViewById(R.id.ordercard);
        developcard=findViewById(R.id.devcard);
        feedbackcard=findViewById(R.id.feedcard);
        aboutcard=findViewById(R.id.abtcard);

        ordcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Homepage.this,MainActivity.class);
                startActivity(i);
            }
        });
        developcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Homepage.this,developers.class);
                startActivity(i);
            }
        });
        feedbackcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Homepage.this,"Now You can write to developers directly!",Toast.LENGTH_LONG).show();
                Intent i= new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:08095030481"));
                i.putExtra("address", "08095030481");
                startActivity(i);
            }
        });
        aboutcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Homepage.this,about.class);
                startActivity(i);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.developer2) {
            Intent i = new Intent(Homepage.this, developers.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.about2) {
            Intent i = new Intent(Homepage.this, about.class);
            startActivity(i);
            return true;
        }
        if(id==R.id.contact2){
            Toast.makeText(Homepage.this,"Now You can write to developers directly!",Toast.LENGTH_LONG).show();
            Intent i= new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:08095030481"));
            i.putExtra("address", "08095030481");
            startActivity(i);
            return true;
        }
        if (id == R.id.exit2) {
            finishAffinity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
package com.example.sarthakmishra.canteen1;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class showheap extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    ListView mListView;
    int i=0,j;
    int count;
    MyAdapter myAdapter;

    String[] dbplatesNames=new String[50];
    String[] cpyarr=new String[50];
    int[] dbplatespics=new int[50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showheap);
        firebaseDatabase= FirebaseDatabase.getInstance();
        ref=firebaseDatabase.getReference("plates");
        mListView = (ListView) findViewById(R.id.listview);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        j=0;

        ref.child("no").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    int c=j+1;
                    dbplatesNames[j]="Plate "+ c;
                    dbplatespics[j]= R.drawable.plates;
                    cpyarr[j]="Plate "+ c;
                    j++;
                }
               int t=j;
                for(int k=0;k<j;k++)
                {
                    dbplatesNames[k]=cpyarr[--t];
                }
                myAdapter=new MyAdapter(showheap.this, dbplatesNames, dbplatespics);
                mListView.setAdapter(myAdapter);
                j=0;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
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
            Intent i = new Intent(showheap.this, about.class);
            startActivity(i);
            return true;
        }
        if(id==R.id.contact3){
            Toast.makeText(showheap.this,"Now You can write to developers directly!",Toast.LENGTH_LONG).show();
            Intent i= new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:08095030481"));
            i.putExtra("address", "08095030481");
            startActivity(i);
            return true;
        }
        if (id == R.id.exit3) {
            Intent i = new Intent(showheap.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(showheap.this, "Click again to exit from the app!", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
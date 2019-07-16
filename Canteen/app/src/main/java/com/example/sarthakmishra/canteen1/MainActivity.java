package com.example.sarthakmishra.canteen1;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.flaviofaria.kenburnsview.RandomTransitionGenerator;
import com.flaviofaria.kenburnsview.Transition;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    Button ins,rem,showheap,topbtn;
    ListView lv;
    Toolbar mToolbar;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;
    int i=0,j;
    int y;
    int count,count1;
    KenBurnsView kenBurnsView;
    private boolean moving=true;
    String[] dbplatesNames=new String[50];
    int[] dbplatespics=new int[50];
    private CardView inscard,remcard,showcard,pickcard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inscard=findViewById(R.id.insertcard);
        remcard=findViewById(R.id.removecard);
        showcard=findViewById(R.id.showcard);
        pickcard=findViewById(R.id.pickcard);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

//        mToolbar = (Toolbar) findViewById(R.id.toolbar);
//        mToolbar.setTitle("CHOOSE OPTION");
        firebaseDatabase= FirebaseDatabase.getInstance();
        ref=firebaseDatabase.getReference("plates");

        ref.child("no").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                j=0;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    int c=j+1;
                    dbplatesNames[j]="Plate "+ c;
                    dbplatespics[j]= R.drawable.plates;
                    j++;
                }
                //j=0;
            y=j-1;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        inscard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i=0;
                count=0;
                ref.child("no").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            i++;
                        }
                        if(count==0)
                        {
                            ref.child("no").child(i+"").setValue(System.currentTimeMillis());
                            count++;
                            final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                            builder.setMessage("Plate inserted successfully!\nWould you like to see its effect?");
                            builder.setCancelable(true);

                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                            builder.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent i=new Intent(MainActivity.this,showheap.class);
                                    startActivity(i);
                                }
                            });
                            AlertDialog alertdialog=builder.create();
                            alertdialog.show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

        remcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count1=0;
                ref.child("no").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        j=0;
                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            int c=j+1;
                            dbplatesNames[j]="Plate "+ c;
                            dbplatespics[j]= R.drawable.plates;
                            j++;
                        }

                        if(count1==0) {
                        y=j-1;

                            if (y == -1)
                                Toast.makeText(MainActivity.this, "All plates have been removed!", Toast.LENGTH_SHORT).show();

                            else {
                                final AlertDialog.Builder builder=new AlertDialog.Builder(MainActivity.this);
                                builder.setMessage("Plate removed successfully!\nWould you like to see its effect?");
                                builder.setCancelable(true);

                                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();
                                    }
                                });

                                builder.setPositiveButton("Yes!", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        Intent i=new Intent(MainActivity.this,showheap.class);
                                        startActivity(i);
                                    }
                                });
                                AlertDialog alertdialog=builder.create();
                                alertdialog.show();


                                ref.child("no").child(y + "").removeValue();
                                y--;

                            }
                            count1++;
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });


            }
        });

        showcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(MainActivity.this,"Showing Plates",Toast.LENGTH_SHORT).show();
              Intent i=new Intent(MainActivity.this,com.example.sarthakmishra.canteen1.showheap.class);
              startActivity(i);

            }
        });

        pickcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//              Toast.makeText(MainActivity.this,"Last plate: "+dbplatesNames[j-1],Toast.LENGTH_SHORT).show();
//                Toast.makeText(MainActivity.this,"j: "+j,Toast.LENGTH_SHORT).show();

                if(j==0)
                {
                    Toast.makeText(MainActivity.this,"Regrets! No plate is available right now.",Toast.LENGTH_SHORT).show();

                }
                    else{

              Intent mIntent = new Intent(MainActivity.this, DetailActivity.class);

                mIntent.putExtra("countryName", dbplatesNames[j-1]);
                mIntent.putExtra("countryFlag", dbplatespics[j-1]);
                startActivity(mIntent);

                }
            }
        });

        kenBurnsView=(KenBurnsView)findViewById(R.id.image);
        AccelerateDecelerateInterpolator ACCELERATE_DECELERATE = new AccelerateDecelerateInterpolator();
        RandomTransitionGenerator generator = new RandomTransitionGenerator(10000, ACCELERATE_DECELERATE);
        kenBurnsView.setTransitionGenerator(generator);

        kenBurnsView.setTransitionListener(onTransittionListener());

        kenBurnsView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(moving){
                    kenBurnsView.pause();
                    moving=false;
                }
                else{
                    kenBurnsView.resume();;
                    moving=true;
                }
            }
        });
    }
    private KenBurnsView.TransitionListener onTransittionListener() {
        return new KenBurnsView.TransitionListener() {

            @Override
            public void onTransitionStart(Transition transition) {

                // Toast.makeText(MainActivity.this, "start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTransitionEnd(Transition transition) {
                // Toast.makeText(MainActivity.this, "end", Toast.LENGTH_SHORT).show();
            }
        };
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main2,menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id==android.R.id.home)
            onBackPressed();

        if (id == R.id.developer2) {
            Intent i = new Intent(MainActivity.this, developers.class);
            startActivity(i);
            return true;
        }

        if (id == R.id.about2) {
            Intent i = new Intent(MainActivity.this, about.class);
            startActivity(i);
            return true;
        }
        if(id==R.id.contact2){
            Toast.makeText(MainActivity.this,"Now You can write to developers directly!",Toast.LENGTH_LONG).show();
            Intent i= new Intent(Intent.ACTION_SENDTO, Uri.parse("sms:08095030481"));
            i.putExtra("address", "08095030481");
            startActivity(i);
            return true;
        }
        if (id == R.id.exit2) {
            Intent i = new Intent(MainActivity.this, Homepage.class);
            startActivity(i);
            Toast.makeText(MainActivity.this,"Press exit again to quit the app!",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
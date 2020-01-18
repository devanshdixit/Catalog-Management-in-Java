package com.example.afinal;

import android.app.ActionBar;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;


public class MainActivity extends AppCompatActivity {
    FloatingActionButton fabSettings, fabRent, fabOwner;
    private FirebaseAnalytics mFirebaseAnalytics;
    AlertDialog.Builder builder;
    RecyclerView recyclerView;
    HorizontalScrollView h;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference notebookRef = db.collection("Properties");
    private LinearLayout layoutFabSave;
    private LinearLayout layoutFabEdit;
    private boolean fabExpanded = false;
    private NoteAdapter adapter, adapter1;
    private SwipeRefreshLayout swipeContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_round);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        setTitle("J Estate");
        setContentView(R.layout.activity_main);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        fabSettings = findViewById(R.id.fabSetting);
        fabRent = findViewById(R.id.fabSave);
        fabOwner = findViewById(R.id.fabEdit);
        final Chip rentchip = findViewById(R.id.rentchip);
        final Chip ownerchip = findViewById(R.id.ownerchip);
        final Chip sellchip = findViewById(R.id.sellchip);
        final Chip agentchip = findViewById(R.id.agentchip);
        final Chip allchip = findViewById(R.id.allchip);

        allchip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Query g = notebookRef.whereEqualTo("avail", "Available");
                    FirestoreRecyclerOptions<Note> option = new FirestoreRecyclerOptions.Builder<Note>()
                            .setQuery(g, Note.class).build();
                    adapter1 = new NoteAdapter(option);
                    adapter1.startListening();
                    setUpRecyclerView(adapter1);
                } else
                {
                    h.setVisibility(View.INVISIBLE);
                    setUpRecyclerView(adapter);
                }
            }
        });
        allchip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allchip.setChecked(false);
                h.setVisibility(View.INVISIBLE);
                setUpRecyclerView(adapter);
            }
        });

        sellchip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Query g = notebookRef.whereEqualTo("property_Type", "Sell")
                            .whereEqualTo("avail", "Available");
                    FirestoreRecyclerOptions<Note> option = new FirestoreRecyclerOptions.Builder<Note>()
                            .setQuery(g, Note.class).build();
                    adapter1 = new NoteAdapter(option);
                    adapter1.startListening();
                    setUpRecyclerView(adapter1);
                } else
                {
                    h.setVisibility(View.INVISIBLE);
                    setUpRecyclerView(adapter);
                }
            }
        });
        sellchip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sellchip.setChecked(false);
                h.setVisibility(View.INVISIBLE);
                setUpRecyclerView(adapter);
            }
        });

        ownerchip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Query g = notebookRef.whereEqualTo("referencefrom", "Owner")
                            .whereEqualTo("avail", "Available");
                    FirestoreRecyclerOptions<Note> option = new FirestoreRecyclerOptions.Builder<Note>()
                            .setQuery(g, Note.class).build();
                    adapter1 = new NoteAdapter(option);
                    adapter1.startListening();
                    setUpRecyclerView(adapter1);
                } else
                {
                    h.setVisibility(View.INVISIBLE);
                    setUpRecyclerView(adapter);
                }
            }
        });
        ownerchip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ownerchip.setChecked(false);
                h.setVisibility(View.INVISIBLE);
                setUpRecyclerView(adapter);
            }
        });

        rentchip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Query g = notebookRef.whereEqualTo("property_Type", "Rent")
                            .whereEqualTo("avail", "Available");
                    FirestoreRecyclerOptions<Note> option = new FirestoreRecyclerOptions.Builder<Note>()
                            .setQuery(g, Note.class).build();
                    adapter1 = new NoteAdapter(option);
                    adapter1.startListening();
                    setUpRecyclerView(adapter1);
                } else
                {
                    h.setVisibility(View.INVISIBLE);
                    setUpRecyclerView(adapter);
                }
            }
        });
        rentchip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rentchip.setChecked(false);
                h.setVisibility(View.INVISIBLE);
                setUpRecyclerView(adapter);
            }
        });

        agentchip.setOnCheckedChangeListener(new Chip.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                {
                    Query g = notebookRef.whereEqualTo("referencefrom", "Agent")
                            .whereEqualTo("avail", "Available");
                    FirestoreRecyclerOptions<Note> option = new FirestoreRecyclerOptions.Builder<Note>()
                            .setQuery(g, Note.class).build();
                    adapter1 = new NoteAdapter(option);
                    adapter1.startListening();
                    setUpRecyclerView(adapter1);

                } else
                    {
                        h.setVisibility(View.INVISIBLE);
                        setUpRecyclerView(adapter);
                    }
            }
        });
        agentchip.setOnCloseIconClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                agentchip.setChecked(false);
                h.setVisibility(View.INVISIBLE);
                setUpRecyclerView(adapter);
            }
        });

        //  Chip config ends

        // this is to setup the recycler view
        final Query query = notebookRef.whereEqualTo("avail", "Available");
        FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                .setQuery(query, Note.class)
                .build();
        adapter = new NoteAdapter(options);
        setUpRecyclerView(adapter);
        //  Recycler view Setup ends

        // Floating button setup
        layoutFabSave = findViewById(R.id.layoutFabSave);
        layoutFabEdit = findViewById(R.id.layoutFabEdit);

        fabSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (fabExpanded) {
                    closeSubMenusFab();
                } else {
                    openSubMenusFab();
                }
            }
        });

        //Only main FAB is visible in the beginning
        closeSubMenusFab();
        swipeContainer = findViewById(R.id.refresh);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setUpRecyclerView(adapter);
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);
            }
        });

        // Floating button setup ends

    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }


    //closes FAB submenus
    private void closeSubMenusFab() {
        layoutFabSave.setVisibility(View.INVISIBLE);
        layoutFabEdit.setVisibility(View.INVISIBLE);
        fabSettings.setImageResource(R.drawable.ic_add_black_24dp);
        fabExpanded = false;
    }

    //Opens FAB submenus
    private void openSubMenusFab() {
        layoutFabSave.setVisibility(View.VISIBLE);
        layoutFabEdit.setVisibility(View.VISIBLE);
        //Change settings icon to 'X' icon
        fabSettings.setImageResource(R.drawable.ic_close);
        fabExpanded = true;
        fabRent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RentActivity.class));
            }
        });
        fabOwner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SellActivity.class));
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.popup_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.sellll) {
            h = findViewById(R.id.hori);
            h.setVisibility(View.VISIBLE);
            /*
            AlertDialog.Builder builder1 = new AlertDialog.Builder(MainActivity.this);
            builder1.setTitle("Sort By:")
                    .setItems(R.array.planets_array, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            if (which==2){
                                final Query query1= notebookRef.whereEqualTo("avail", "Available")
                                        .whereEqualTo("property_Type","Rent");
                                FirestoreRecyclerOptions<Note> options = new FirestoreRecyclerOptions.Builder<Note>()
                                        .setQuery(query1, Note.class)
                                        .build();

                                adapter1 = new NoteAdapter(options);
                                setUpRecyclerView(query1,adapter1);
                            }
                            else if (which==3)
                            {

                            }
                        }
                    });
            AlertDialog d =builder1.create();
            d.show();
            return true;*/
        }
        return super.onOptionsItemSelected(item);
    }


    private void setUpRecyclerView(final NoteAdapter adapter) {

        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull final RecyclerView.ViewHolder viewHolder, int direction) {
                builder = new AlertDialog.Builder(MainActivity.this);
                // Add the buttons
                builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        adapter.deleteItem(viewHolder.getAdapterPosition());
                    }
                });
                builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        Dialog dd = new Dialog(MainActivity.this);
                        dd.cancel();
                        recyclerView.setAdapter(adapter);
                    }
                });
                builder.setMessage("Are you sure?")
                        .show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NoteAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(DocumentSnapshot documentSnapshot, int position) {
                String path1 = documentSnapshot.getReference().getPath();
                Intent nn = new Intent(MainActivity.this, ViewActivity.class);
                nn.putExtra("key1",path1);
                startActivity(nn);
            }
        });
        adapter.setOnItemLongClickListener(new NoteAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(DocumentSnapshot documentSnapshot, int position) {
                String path = documentSnapshot.getReference().getPath();
                Intent n = new Intent(MainActivity.this, EditActivity.class);
                n.putExtra("key", path);
                startActivity(n);
            }
        });

    }


}
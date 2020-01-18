package com.example.afinal;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class RentActivity extends AppCompatActivity {
    private FirebaseAnalytics mFirebaseAnalytics;

    private static final String TAG = "RentActivity";
    FirebaseFirestore db;
    String Agent, Ownner, Avail, Navail, Mb, Acres, Direct, Bachelors, Family, Rentamount, Deposit, Carpetarea, OwnerAgentName, PropertyAddress, Furnished, Nfurnished, Empty, MobileNo;
    private Spinner Bhk, Floor, Parking;
    private RadioButton owner, agent, avail, navail, mb, acres, direct, bachelors, family, furn, nfurn, empty;
    private EditText rentamount, deposit, carpertarea, owneragentname, propertyaddress, mobileno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rent);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bhk = findViewById(R.id.spinner);
        db = FirebaseFirestore.getInstance();
        Floor = findViewById(R.id.spinner1);
        Parking = findViewById(R.id.spinner2);
        RadioGroup r = findViewById(R.id.r);

        RadioGroup raa = findViewById(R.id.raa);
        owner = findViewById(R.id.owner);
        agent = findViewById(R.id.agent);
        avail = findViewById(R.id.avail);
        navail = findViewById(R.id.navail);
        mb = findViewById(R.id.mb);
        acres = findViewById(R.id.acres);
        direct = findViewById(R.id.direct);
        RadioGroup raaa = findViewById(R.id.raaa);
        RadioGroup raaaa = findViewById(R.id.raaaa);
        RadioGroup raaaaa = findViewById(R.id.raaaaa);
        bachelors = findViewById(R.id.bachelors);
        family = findViewById(R.id.family);
        rentamount = findViewById(R.id.rentamount);
        deposit = findViewById(R.id.depositAmount);
        carpertarea = findViewById(R.id.CarpetArea);
        owneragentname = findViewById(R.id.owneragentName);
        propertyaddress = findViewById(R.id.propertyAddress);
        mobileno = findViewById(R.id.mobileNo);

        furn = findViewById(R.id.furn);
        nfurn = findViewById(R.id.nfurn);
        empty = findViewById(R.id.empty);


        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("Add Rent Property");

        String[] items = new String[]{"1RK", "1BHK", "2BHK", "3BHK", "4BHK"};
        String[] items2 = new String[]{"None", "1", "2", "3"};
        String[] items1 = new String[]{"Ground", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        Bhk.setAdapter(adapter);
        Floor.setAdapter(adapter1);
        Parking.setAdapter(adapter2);
        r.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.avail:
                        Avail = avail.getText().toString();
                        break;
                    case R.id.navail:
                        Navail = navail.getText().toString();
                        break;
                }
            }
        });

        raa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.owner:
                        Ownner = owner.getText().toString();
                        break;
                    case R.id.agent:
                        Agent = agent.getText().toString();
                        break;
                }
            }
        });

        raaa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.mb:
                        Mb = mb.getText().toString();
                        break;
                    case R.id.acres:
                        Acres = acres.getText().toString();
                        break;
                    case R.id.direct:
                        Direct = direct.getText().toString();
                        break;
                }
            }
        });

        raaaaa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.bachelors:
                        Bachelors = bachelors.getText().toString();
                        break;
                    case R.id.family:
                        Family = family.getText().toString();
                        break;
                }
            }
        });

        raaaa.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.furn:
                        Furnished = furn.getText().toString();
                    case R.id.nfurn:
                        Nfurnished = nfurn.getText().toString();
                    case R.id.empty:
                        Empty = empty.getText().toString();
                }
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void saveNote() {
        String bhk = Bhk.getSelectedItem().toString();
        String floor = Floor.getSelectedItem().toString();
        String parking = Parking.getSelectedItem().toString();
        int bhkid = Bhk.getSelectedItemPosition();
        int floorid = Floor.getSelectedItemPosition();
        int parkingid = Parking.getSelectedItemPosition();
        String Property_Type = "Rent", referencefrom, Available, referenceType, roomType,tenantType;

        if (Family == null){
            tenantType = Bachelors;
        }
        else{
            tenantType = Family;
        }
        if (Ownner == null) {
            referencefrom = Agent;
        } else {
            referencefrom = Ownner;
        }
        if (Avail == null) {
            Available = Navail;
        } else {
            Available = Avail;
        }
        if (Mb == null && Acres == null) {
            referenceType = Direct;
        } else if (Mb == null && Direct == null) {
            referenceType = Acres;
        } else {
            referenceType = Mb;
        }
        if (Furnished == null && Empty == null) {
            roomType = Nfurnished;
        } else if (Nfurnished == null && Empty == null) {
            roomType = Furnished;
        } else {
            roomType = Empty;
        }
        Rentamount = rentamount.getText().toString();
        Deposit = deposit.getText().toString();
        Carpetarea = carpertarea.getText().toString();
        OwnerAgentName = owneragentname.getText().toString();
        PropertyAddress = propertyaddress.getText().toString();
        MobileNo = mobileno.getText().toString();
        db.collection("Properties")
                .add(new Note(Property_Type, referencefrom, referenceType, bhk, floor, parking, bhkid, floorid, parkingid, Available, Rentamount, Deposit, Carpetarea, OwnerAgentName, PropertyAddress, roomType, MobileNo,tenantType))
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(RentActivity.this, "Property Added", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });
    }
}



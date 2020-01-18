package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Arrays;
import java.util.List;

public class EditActivity extends AppCompatActivity {

    private FirebaseAnalytics mFirebaseAnalytics;
    private static final String TAG ="EditActivity" ;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final String property_Type = "property_Type";
    final String Rent = "Rent";
    String value,type;
    RadioButton sell,rent,owner,agent,avail,navail;
    Spinner spinner,spinner1,spinner2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        owner = findViewById(R.id.owner);
        agent = findViewById(R.id.agent);
        spinner = findViewById(R.id.spinner);
        spinner1 = findViewById(R.id.spinner1);
        spinner2 = findViewById(R.id.spinner2);
        avail = findViewById(R.id.avail);
        navail = findViewById(R.id.navail);

        String[] items = new String[]{"1RK", "1BHK", "2BHK", "3BHK", "4BHK"};
        String[] items2 = new String[]{"None", "1", "2", "3"};
        String[] items1 = new String[]{"Ground", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items2);
        spinner.setAdapter(adapter);
        spinner1.setAdapter(adapter1);
        spinner2.setAdapter(adapter2);



        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("key");
        }

        DocumentReference docRef = db.document(value);
        Log.d(TAG, "DocumentSnapshot dataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa: " + docRef);

        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "DocumentSnapshot dataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa: " + document.getData());
                        Note note = document.toObject(Note.class);
                        if (note.getAvail().equals("Available"))
                            {   avail.setChecked(true); }
                        else
                            { navail.setChecked(true); }
                       // if (note.getProperty_Type().equals("Rent"))
                         //   { rent.setChecked(true); }
                        //else
                          //  { sell.setChecked(true); }
                        if (note.getReferencefrom().equals("Owner"))
                            { owner.setChecked(true); }
                        else
                            { agent.setChecked(true); }
                        spinner.setSelection(note.getBhkid());
                        spinner1.setSelection(note.getFloorid());
                        spinner2.setSelection(note.getParkingid());

                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });

    }


}

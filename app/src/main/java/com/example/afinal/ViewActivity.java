package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ViewActivity extends AppCompatActivity {
    private static final String TAG ="ViewActivity" ;
    private FirebaseAnalytics mFirebaseAnalytics;
    TextView propertyType,referencefrom, referenceType,roomType,tenantType,amount,deposit,carpetarea,oaname,mobileno,floor,bhk,parking,address;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    String value;
    DocumentReference documentReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        propertyType = findViewById(R.id.propertytype);
        referencefrom = findViewById(R.id.Referencefrom);
        referenceType = findViewById(R.id.referencetype);
        roomType = findViewById(R.id.roomType);
        tenantType = findViewById(R.id.tenantType);
        amount = findViewById(R.id.amount);
        deposit = findViewById(R.id.depositAmount);
        carpetarea = findViewById(R.id.carpet);
        oaname = findViewById(R.id.owneragentname);
        mobileno = findViewById(R.id.mobileno);
        floor = findViewById(R.id.floor);
        bhk = findViewById(R.id.bhk);
        parking = findViewById(R.id.parking);
        address = findViewById(R.id.addresses);

        Bundle extras = getIntent().getExtras();
        try {
            if (extras !=null)
            {
                value = extras.getString("key1");
            }
        }
        catch (Exception e)
        {
            Log.d(TAG, "onCreate: 9999999999999999"+e);
        }


              documentReference = db.document(value);
                System.out.println("hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii:"+documentReference);
        documentReference.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {

                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document!=null) {
                            Note note = document.toObject(Note.class);

                            Log.d(TAG, "hiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii" + document.getData());
                            propertyType.setText(note.getProperty_Type());
                            referencefrom.setText(note.getReferencefrom());
                            referenceType.setText(note.getReferenceType());
                            roomType.setText(note.getRoomType());
                            tenantType.setText(note.getTenantType());
                            amount.setText(note.getRentAmount());
                            carpetarea.setText(note.getCarpetArea());
                            oaname.setText(note.getOwnerAgentName());
                            mobileno.setText(note.getMobileNo());
                            floor.setText(note.getFloor());
                            bhk.setText(note.getBhk());
                            parking.setText(note.getParking());
                            address.setText(note.getPropertyAddress());
                        }
                    }
            }
        });

    }
}

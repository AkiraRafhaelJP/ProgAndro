package com.example.progandro;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private EditText noMhs;
    private EditText namaMhs;
    private EditText phoneMhs;
    private Button buttonSimpan;
    private Button buttonHapus;
    private FirebaseFirestore firebaseFirestoreDb = FirebaseFirestore.getInstance();
    private FirestoreRecyclerAdapter adapter;
    private RecyclerView recyclerView;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noMhs = findViewById(R.id.noMhs);
        namaMhs = findViewById(R.id.namaMhs);
        phoneMhs = findViewById(R.id.phoneMhs);
        buttonSimpan = findViewById(R.id.simpanButton);
        buttonHapus = findViewById(R.id.hapusButton);

        firebaseFirestoreDb = FirebaseFirestore.getInstance();
        recyclerView = findViewById(R.id.recyclerView);

        buttonSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //sanity check
                if (!noMhs.getText().toString().isEmpty() && !namaMhs.getText().toString().isEmpty()) {
                    tambahMahasiswa();
                } else {
                    Toast.makeText(getApplicationContext(), "No dan Nama Mhs tidak boleh kosong",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        buttonHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteDataMahasiswa();
            }
        });

        //query
        Query query = firebaseFirestoreDb.collection("DaftarMhs");

        //recycleroptions
        FirestoreRecyclerOptions<Mahasiswa> options = new FirestoreRecyclerOptions.Builder<Mahasiswa>()
                .setQuery(query, Mahasiswa.class)
                .build();

        adapter = new FirestoreRecyclerAdapter<Mahasiswa, MahasiswaViewHolder>(options) {
            @NonNull
            @Override
            public MahasiswaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_mahasiswa_single, parent, false);
                return new MahasiswaViewHolder(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull MahasiswaViewHolder holder, int position, @NonNull Mahasiswa model) {
                holder.listNim.setText(model.getNim());
                holder.listName.setText(model.getNama());
                holder.listNoHp.setText(model.getPhone());
            }
        };


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onStart(){
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }

    private class MahasiswaViewHolder extends RecyclerView.ViewHolder{

        private TextView listNim;
        private TextView listName;
        private TextView listNoHp;
        public MahasiswaViewHolder(@NonNull View itemView) {
            super(itemView);

            listNim = itemView.findViewById(R.id.noMhs);
            listName = itemView.findViewById(R.id.namaMhs);
            listNoHp = itemView.findViewById(R.id.nomorHp);
        }
    }

    private void tambahMahasiswa() {

        Mahasiswa mhs = new Mahasiswa(noMhs.getText().toString(),
                namaMhs.getText().toString(),
                phoneMhs.getText().toString());

        firebaseFirestoreDb.collection("DaftarMhs").document().set(mhs)
            .addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Toast.makeText(getApplicationContext(), "Mahasiswa berhasil didaftarkan",
                            Toast.LENGTH_SHORT).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "ERROR" + e.toString(),
                            Toast.LENGTH_SHORT).show();
                    Log.d("TAG", e.toString());
                }
            });
    }

    private void getDataMahasiswa() {
        DocumentReference docRef = firebaseFirestoreDb.collection("DaftarMhs").document("mhs1");
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Mahasiswa mhs = document.toObject(Mahasiswa.class);
                        noMhs.setText(mhs.getNim());
                        namaMhs.setText(mhs.getNama());
                        phoneMhs.setText(mhs.getPhone());
                    } else {
                        Toast.makeText(getApplicationContext(), "Document tidak ditemukan",
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Document error : " + task.getException(),
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteDataMahasiswa() {
        firebaseFirestoreDb.collection("DaftarMhs").document("mhs1")
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        noMhs.setText("");
                        namaMhs.setText("");
                        phoneMhs.setText("");
                        Toast.makeText(getApplicationContext(), "Mahasiswa berhasil dihapus",
                                Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Error deleting document: " + e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
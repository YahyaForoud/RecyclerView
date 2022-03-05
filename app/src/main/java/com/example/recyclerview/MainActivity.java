package com.example.recyclerview;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Created by Yahya Foroud in 3/5/2022
 */

public class MainActivity extends AppCompatActivity implements ContactsAdapter.ItemEventListener {

    private ContactsAdapter adapter;
    private int editingItemPosition = -1;
    private EditText fullNameEt;
    private ImageView addNewContactBtn;

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final RecyclerView recyclerView = findViewById(R.id.rv_main);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL, false));
        adapter = new ContactsAdapter(this);
        recyclerView.setAdapter(adapter);

        fullNameEt = findViewById(R.id.et_main_contactFullName);
        addNewContactBtn = findViewById(R.id.iv_main_addNewContact);
        addNewContactBtn.setOnClickListener(view -> {
            if (fullNameEt.length() >= 2) {
                // Add New contact
                if (editingItemPosition > -1) {
                    // درحالت ادیت هستیم
                    adapter.updateContact(fullNameEt.getText().toString(), editingItemPosition);
                    editingItemPosition = -1;
                    addNewContactBtn.setImageResource(R.drawable.ic_add_white_24dp);
                } else {
                    //در حالت ادیت نیستیم =< مخاطب جدید اضافه میشه
                    adapter.addNewContact(fullNameEt.getText().toString());
                    recyclerView.scrollToPosition(0);
                }
                fullNameEt.setText("");
            } else {
                fullNameEt.setError("FullName cant be empty");
            }
        });
    }

    @Override
    public void onItemClick(String fullName, int position) {
        // edit contact fullName
        editingItemPosition = position;
        fullNameEt.setText(fullName);
        addNewContactBtn.setImageResource(R.drawable.ic_done_white_24dp);
    }
}
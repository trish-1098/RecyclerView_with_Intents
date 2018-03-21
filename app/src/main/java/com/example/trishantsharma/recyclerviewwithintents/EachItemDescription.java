package com.example.trishantsharma.recyclerviewwithintents;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class EachItemDescription extends AppCompatActivity {

    private TextView mMessageTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_each_item_description);
        mMessageTextView = findViewById(R.id.message_text_view);
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("Message")){
                mMessageTextView.setText(intent.getStringExtra("Message"));
            }
        }
    }
}

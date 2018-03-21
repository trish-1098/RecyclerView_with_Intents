package com.example.trishantsharma.recyclerviewwithintents;

import android.content.Intent;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements RecyclerViewAdapter.ShareButtonClickListener,RecyclerViewAdapter.ListItemClickListener{

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;
    private ArrayList<String> stringArrayList;
    private static final int NUM_OF_ITEMS = 10;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view_in_main);
        stringArrayList = new ArrayList<>();
        stringArrayList.add("Hello");
        stringArrayList.add("Hi");
        stringArrayList.add("How are you?");
        stringArrayList.add("I am fine");
        stringArrayList.add("Are you coming?");
        stringArrayList.add("Yes,ofcourse");
        stringArrayList.add("Bring a toy also");
        stringArrayList.add("Okay,anything else?");
        stringArrayList.add("No, come fast");
        /*
            In the below line the adapter object is being created in which the context is passed as
            the first param, the NUM_OF_ITEMS is passed as the second param,the third param states
            that this activity will handle the button clicks in the list_item_layout, and finally
            the fourth param is the data passed which is to be shown inside the items of the
            RecyclerView.
            NOTE: It is not mandatory to pass the data in the constructor but rather a setter method
            can also be created in the Adapter class which will accept the data to be set. The data
            be received/retrieved through anything like through a network request or a database
            content provider.BY using these ways the setter method can be called within a loader or
            an AsyncTask to be provided after the data has been loaded/received/retrieved.
         */
        recyclerViewAdapter = new RecyclerViewAdapter(this,
                                                        NUM_OF_ITEMS,
                                                        this,
                                                        this,
                                                        stringArrayList);
        /*
            In the below line the RecyclerView has to be given a layout manager to manage the type
            of layout of it. Whether it will be linear vertically or horizontally, or will it be
            grid type or more.
         */
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        //This tells that the layout doesn't change even if the content inside it changes
        recyclerView.setHasFixedSize(true);
        /*
            Finally here we are setting the Adapter to the RecyclerView or in better words we are
            connecting RecyclerView and the Adapter class.
            NOTE: Remember that the RecyclerView calls the onCreateViewHolder method to create a
            ViewHolder object with all references already cached within the ViewHolder to save the
            number of calls to findViewById() and when the object is returned to the RecyclerView
            then the RecyclerView calls the onBindViewHolder to populate the ViewHolder with the
            data set through the constructor(if it is available previously/beforehand) OR through
            the setter method if it has to be loaded.
         */
        recyclerView.setAdapter(recyclerViewAdapter);
    }
    /*
        Below is the implementation of the onShareButtonClicked method to handle the clicks made
        on the Share Button of a single item in RecyclerView. In this we create an implicit Intent
        for sharing the data in the form of plain text through ShareCompat.IntentBuilder class.
     */
    @Override
    public void onShareButtonClicked(String msgFromClickedPart, int positionOfClicked) {
        /*
            Here the Intent is created using ShareCompat.IntentBuilder class which is used to
            transfer some type of data with the an intent.
        */
        Intent share = ShareCompat.IntentBuilder.from(this)
                .setChooserTitle("Share Message")
                .setType("text/plain")
                .setText(msgFromClickedPart)
                .getIntent();
        if(share.resolveActivity(getPackageManager())!=null){
            startActivity(share);
        }
    }

    @Override
    public void onListItemClicked(String msg) {
        /*
            Here an explicit Intent is being used to open the activity EachItemDescription
            with a single key value pair of a String with key name being "Message" and value being
            the msg received. It is received by the EachItemDescription activity.
         */
        Intent intent = new Intent(MainActivity.this,EachItemDescription.class);
        intent.putExtra("Message",msg);
        if(intent.resolveActivity(getPackageManager())!=null){
            startActivity(intent);
        }
    }
}

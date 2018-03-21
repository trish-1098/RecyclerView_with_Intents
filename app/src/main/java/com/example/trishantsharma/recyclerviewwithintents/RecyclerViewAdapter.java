package com.example.trishantsharma.recyclerviewwithintents;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by trishantsharma on 21/03/18.
 */
    /*
        This class is used for creating the ViewHolder objects and setting the data in those
        ViewHolder objects. The ViewHolder object is created after the RecyclerView calls the
        onCreateViewHolder() method which calls the ArrayListItemViewHolder constructor by passing
        the view object(which contains the list_item_view object and the reference to all the child
        views of that list_item_view).Then after the ViewHolder object is created then the
        onBindViewHolder() method is called to insert/set the data in the ViewHolder object.

        Inside onCreateViewHolder() explanation in words:-
        Inside this the ViewHolder object is created by creating the view(which contains the java
        object made of the recycler_view_list_item.xml file by inflating it and returning the
        reference to the view). In code the line is on line no:73. After that the view
        is passed to the constructor of ArrayListItemViewHolder which in turn calls super(itemView)
        (where itemView is the received view object) and through this the list_item_layout object is
        made as the single child(kind Of) of the ViewHolder and then the ViewHolder object is
        returned which contains the list_item_layout object to be inflated and the references to all
        the child views of the list_item_layout to be inflated. What this does is since in the
        ViewHolder object the references to child views of the list_iem_layout is already there or
        in better words we can say cached therefore while setting the data the findViewById() method
        is not called and hence we save the number of calls to findViewById() by a large extent
        which in turn improves the performance of the application.

        Inside onBindViewHolder() explanation in words:-
        Now after the ViewHolder object is returned to the RecyclerView then the RecyclerView calls
        the onBindViewHolder() method in which the code to set the data in that list_item_view
        is written.
        NOTE: The data to be inserted can be received through the constructor or can be set by
        making a setter method in this class and calling that method in the activity which contains
        the RecyclerView.

        There are two interfaces defined one to handle the clicks on the button in the layout and
        one to handle the the clicks on items in the RecyclerView.
     */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ArrayListItemViewHolder> {

    Context context;
    private int mNumOfItems;
    private ArrayList<String > stringArrayList;
    private String shareMessage;
    public interface ShareButtonClickListener{
        void onShareButtonClicked(String msgFromClickedPart,int positionOfClicked);
    }
    ShareButtonClickListener shareButtonClickListener;
    public interface ListItemClickListener{
        void onListItemClicked(String msg);
    }
    ListItemClickListener listItemClickListener;
    public RecyclerViewAdapter(Context context,
                               int mNumOfItems,
                               ShareButtonClickListener shareButtonClickListener,
                               ListItemClickListener listItemClickListener,
                               ArrayList<String> stringArrayList){
        this.context = context;
        this.mNumOfItems = mNumOfItems;
        this.shareButtonClickListener = shareButtonClickListener;
        this.listItemClickListener = listItemClickListener;
        this.stringArrayList = stringArrayList;
    }
    public class ArrayListItemViewHolder extends RecyclerView.ViewHolder{ // implements View.OnClickListener{

        private TextView mInformationTextView;
        private Button mInformationShareButton;
        public ArrayListItemViewHolder(View itemView) {
            super(itemView);
            mInformationTextView = itemView.findViewById(R.id.info_text_view);
            mInformationShareButton = itemView.findViewById(R.id.share_info_button);
        }
        void bind(final String msgOfItem, final int position){
            shareMessage = msgOfItem + " " + context.getString(R.string.with_pos) + " " + position;
            mInformationTextView.setText(shareMessage);
            mInformationShareButton.setText(R.string.share_info);
            /*
                Here an onClickListener is set on the ViewHolder object's Share button(in this case
                only) to handle the clicks on the button. The onClickMethod then invokes the
                onShareButtonClicked method implied in the MainActivity.
            */
            mInformationShareButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("onClick: ","List Item Button Clicked");
                    shareButtonClickListener.onShareButtonClicked(msgOfItem,position);
                }
            });
            mInformationTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("onClick: ","List Item TextView Clicked");
                    listItemClickListener.onListItemClicked(mInformationTextView.getText().toString());
                }
            });
        }

        /*@Override
        public void onClick(View view) {
            Log.d("onClick: ","List Item Clicked");
            listItemClickListener.onListItemClicked(mInformationTextView.getText().toString());
        }*/
    }
    @Override
    public ArrayListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_view_list_item,parent,false);
        ArrayListItemViewHolder arrayListItemViewHolder = new ArrayListItemViewHolder(view);
        return arrayListItemViewHolder;
    }

    @Override
    public void onBindViewHolder(ArrayListItemViewHolder holder, int position) {
        holder.bind(stringArrayList.get(position),position);
    }

    @Override
    public int getItemCount() {
        if(stringArrayList != null && !stringArrayList.isEmpty()){
            return stringArrayList.size();
        } else {
            return 0;
        }
    }
}

package com.example.nourelislamsaidi.adapters;

import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nourelislamsaidi.numbertowords.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nourelislam.saidi on 08/08/2017.
 */

public class LangAdapter extends RecyclerView.Adapter<LangAdapter.MyViewHolder> {


    private List<String> mDataList = new ArrayList<>();
    private String mLangueChecked;

    public LangAdapter(List<String> dataList, String langueChecked) {

        mDataList = dataList;
        mLangueChecked = langueChecked;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView mItemTitle;
        public AppCompatImageView mCheckedItem;

        public MyViewHolder(View view) {
            super(view);
            mItemTitle = itemView.findViewById(R.id.list_item_title);
            mCheckedItem = itemView.findViewById(R.id.list_item_checked);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.mItemTitle.setText(mDataList.get(position));
        holder.mCheckedItem.setVisibility(mDataList.get(position).equals(mLangueChecked) ? View.VISIBLE : View.INVISIBLE);
    }


    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}

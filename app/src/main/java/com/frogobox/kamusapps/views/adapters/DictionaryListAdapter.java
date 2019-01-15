package com.frogobox.kamusapps.views.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.frogobox.kamusapps.R;
import com.frogobox.kamusapps.models.Dictionary;
import com.frogobox.kamusapps.views.activities.DetailActivity;

import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * ic_icon Inc License
 * =========================================
 * KamusApps
 * Copyright (C) 15/01/2019.
 * All rights reserved
 * -----------------------------------------
 * Name     : Muhammad Faisal Amir
 * E-mail   : faisalamircs@gmail.com
 * Line     : bullbee117
 * Phone    : 081357108568
 * Majors   : D3 Teknik Informatika 2016
 * Campus   : Telkom University
 * -----------------------------------------
 * id.amirisback.frogobox
 */
public class DictionaryListAdapter extends RecyclerView.Adapter<DictionaryListAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<Dictionary> data;

    public DictionaryListAdapter(Context mContext, ArrayList<Dictionary> data) {
        this.mContext = mContext;
        this.data = data;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mTextViewDictionaryItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewDictionaryItem = itemView.findViewById(R.id.textDictionary);
        }
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_list_dictionary, parent, false);
        return new DictionaryListAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        viewHolder.mTextViewDictionaryItem.setText(data.get(i).getWord());

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentData = new Intent(mContext, DetailActivity.class);
                Dictionary parcelDictionary = new Dictionary();
                parcelDictionary.setWord(data.get(i).getWord());
                parcelDictionary.setDescription(data.get(i).getDescription());
                intentData.putExtra(DetailActivity.EXTRA_DICTIONARY, parcelDictionary);
                mContext.startActivity(intentData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}

package com.frogobox.kamusapps.views.fragments;


import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.frogobox.kamusapps.R;
import com.frogobox.kamusapps.helpers.DictionaryHelper;
import com.frogobox.kamusapps.models.database.DataContract;
import com.frogobox.kamusapps.models.dataclass.Dictionary;
import com.frogobox.kamusapps.views.adapters.DictionaryListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EnglishFragment extends Fragment {

    private DictionaryHelper mDictionaryHelper;
    private DictionaryListAdapter adapter;
    private ArrayList<Dictionary> mArrayDictionary;

    public EnglishFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_english, container, false);
        setHasOptionsMenu(true);
        RecyclerView mRecyclerView = rootView.findViewById(R.id.recylerViewEnglish);
        mDictionaryHelper = new DictionaryHelper(getContext());
        adapter = new DictionaryListAdapter(getContext());
        // -----------------------------------------------------------------------------------------
        mDictionaryHelper.open();
        mArrayDictionary = mDictionaryHelper.getAllData(DataContract.DataEntry.TABLE_EN_TO_IN);
        mDictionaryHelper.close();
        adapter.addItem(mArrayDictionary);
        // -----------------------------------------------------------------------------------------
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), mLayoutManager.getOrientation());
        // -----------------------------------------------------------------------------------------
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
        // -----------------------------------------------------------------------------------------
        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);
        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search_hint));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    mDictionaryHelper.open();
                    mArrayDictionary = mDictionaryHelper.getDataByWord(DataContract.DataEntry.TABLE_EN_TO_IN, query);
                    mDictionaryHelper.close();
                    adapter.addItem(mArrayDictionary);
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    if (newText!=null){
                        mDictionaryHelper.open();
                        mArrayDictionary = mDictionaryHelper.getDataByWord(DataContract.DataEntry.TABLE_EN_TO_IN, newText);
                        mDictionaryHelper.close();
                        adapter.addItem(mArrayDictionary);
                    } else {
                        mDictionaryHelper.open();
                        mArrayDictionary = mDictionaryHelper.getAllData(DataContract.DataEntry.TABLE_EN_TO_IN);
                        mDictionaryHelper.close();
                        adapter.addItem(mArrayDictionary);
                    }
                    return true;
                }
            });
        } else {
            mDictionaryHelper.open();
            mArrayDictionary = mDictionaryHelper.getAllData(DataContract.DataEntry.TABLE_EN_TO_IN);
            mDictionaryHelper.close();
            adapter.addItem(mArrayDictionary);
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }

}
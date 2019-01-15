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
import android.widget.Toast;

import com.frogobox.kamusapps.R;
import com.frogobox.kamusapps.models.Dictionary;
import com.frogobox.kamusapps.views.adapters.DictionaryListAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndonesiaFragment extends Fragment {

    private RecyclerView mRecyclerView;

    public IndonesiaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootView = inflater.inflate(R.layout.fragment_indonesia, container, false);
        setHasOptionsMenu(true);
        mRecyclerView = rootView.findViewById(R.id.recylerViewIndonesia);
        ArrayList<Dictionary> mArrayDictionary = new ArrayList<>();

        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));
        mArrayDictionary.add(new Dictionary("AMIR", "Amir"));

        DictionaryListAdapter adapter = new DictionaryListAdapter(getContext(), mArrayDictionary);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        DividerItemDecoration divider = new DividerItemDecoration(getContext(), mLayoutManager.getOrientation());
        // -----------------------------------------------------------------------------------------
        mRecyclerView.addItemDecoration(divider);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);



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
                    Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
                    return true;
                }
                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return false;
    }
}

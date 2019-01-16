package com.frogobox.kamusapps.helpers;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import com.frogobox.kamusapps.R;
import com.frogobox.kamusapps.models.database.DataContract;
import com.frogobox.kamusapps.models.dataclass.Dictionary;
import com.frogobox.kamusapps.presenters.AppPreference;
import com.frogobox.kamusapps.views.activities.MainActivity;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Faisal Amir
 * FrogoBox Inc License
 * =========================================
 * KamusApps
 * Copyright (C) 16/01/2019.
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
public class LoadDataHelper extends AsyncTask<Void, Integer, Void> {

    // ---------------------------------------------------------------------------------------------
    private Context mContext;
    private ProgressBar mProgressBar;
    private DictionaryHelper mDictionaryHelper;
    private AppPreference appPreference;
    // ---------------------------------------------------------------------------------------------
    private final String TAG = LoadDataHelper.class.getSimpleName();
    private double maxprogress = 100;
    // ---------------------------------------------------------------------------------------------

    public LoadDataHelper(Context mContext, ProgressBar mProgressBar) {
        this.mContext = mContext;
        this.mProgressBar = mProgressBar;
    }

    @Override
    protected void onPreExecute() {
        mDictionaryHelper = new DictionaryHelper(mContext);
        appPreference = new AppPreference(mContext);
    }

    @Override
    protected Void doInBackground(Void... params) {
        // -----------------------------------------------------------------------------------------
        Boolean firstRun = appPreference.getFirstRun();
        // -----------------------------------------------------------------------------------------
        if (firstRun) {
            ArrayList<Dictionary> mArrayIndonesia = preLoadRaw(R.raw.indonesia_english);
            ArrayList<Dictionary> mArrayEnglish = preLoadRaw(R.raw.english_indonesia);
            // -------------------------------------------------------------------------------------
            mDictionaryHelper.open();
            // -------------------------------------------------------------------------------------
            double progress = 30;
            publishProgress((int) progress);
            Double progressMaxInsert = 80.0;
            Double progressDiff = (progressMaxInsert - progress) / mArrayIndonesia.size();
            // -------------------------------------------------------------------------------------
            mDictionaryHelper.beginTransaction();
            // -------------------------------------------------------------------------------------
            try {
                for (Dictionary model : mArrayIndonesia) {
                    mDictionaryHelper.insertTransaction(DataContract.DataEntry.TABLE_IN_TO_EN, model);
                    progress += progressDiff;
                    publishProgress((int) progress);
                }

                for (Dictionary model : mArrayEnglish) {
                    mDictionaryHelper.insertTransaction(DataContract.DataEntry.TABLE_EN_TO_IN, model);
                    progress += progressDiff;
                    publishProgress((int) progress);
                }

                // Jika semua proses telah di set success maka akan di commit ke database
                mDictionaryHelper.setTransactionSuccess();
            } catch (Exception e) {
                // Jika gagal maka do nothing
                Log.e(TAG, "GAGAL IMPORT DATA");
            }
            // -------------------------------------------------------------------------------------
            mDictionaryHelper.endTransaction();
            // -------------------------------------------------------------------------------------
            mDictionaryHelper.close();
            // -------------------------------------------------------------------------------------
            appPreference.setFirstRun(false);
            publishProgress((int) maxprogress);
        } else {
            try {
                synchronized (this) {
                    this.wait(2000);
                    publishProgress(50);
                    this.wait(2000);
                    publishProgress((int) maxprogress);
                }
            } catch (Exception e) {
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.setProgress(values[0]);
    }

    @Override
    protected void onPostExecute(Void result) {
        Intent i = new Intent(mContext, MainActivity.class);
        mContext.startActivity(i);
        ((Activity) mContext).finish();
    }

    private ArrayList<Dictionary> preLoadRaw(int sourceRaw) {
        // -----------------------------------------------------------------------------------------
        int count = 0;
        String line;
        ArrayList<Dictionary> mDictionary = new ArrayList<>();
        // -----------------------------------------------------------------------------------------
        try {
            // -------------------------------------------------------------------------------------
            Resources res = mContext.getResources();
            InputStream raw_dict = res.openRawResource(sourceRaw);
            BufferedReader reader = new BufferedReader(new InputStreamReader(raw_dict));
            // -------------------------------------------------------------------------------------
            do {
                // ---------------------------------------------------------------------------------
                line = reader.readLine();
                String[] splitstr = line.split("\t");
                // ---------------------------------------------------------------------------------
                Dictionary mmDictionary = new Dictionary(splitstr[0], splitstr[1]);
                mDictionary.add(mmDictionary);
                count++;
            } while (line != null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mDictionary;
    }
}

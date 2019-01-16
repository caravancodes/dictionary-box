package com.frogobox.kamusapps.views.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.frogobox.kamusapps.R;
import com.frogobox.kamusapps.models.dataclass.Dictionary;

public class DetailActivity extends AppCompatActivity {

    public static String EXTRA_DICTIONARY = "extra_dictionary";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        setTitle(R.string.title_activity_detail);

        TextView mTextViewTitle = findViewById(R.id.textTitle);
        TextView mTextViewDesc = findViewById(R.id.textDesc);

        Dictionary extraDictionary = getIntent().getParcelableExtra(EXTRA_DICTIONARY);
        String title = extraDictionary.getWord();
        String desc = extraDictionary.getDescription();

        mTextViewTitle.setText(title);
        mTextViewDesc.setText(desc);

    }

}
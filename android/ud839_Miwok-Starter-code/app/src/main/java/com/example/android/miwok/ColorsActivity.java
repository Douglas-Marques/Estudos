package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ColorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.category_colors);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.drawable.color_red, "red", "weṭeṭṭi"));
        words.add(new Word(R.drawable.color_green, "green", "chokokki"));
        words.add(new Word(R.drawable.color_brown, "brown", "ṭakaakki"));
        words.add(new Word(R.drawable.color_gray, "gray", "ṭopoppi"));
        words.add(new Word(R.drawable.color_black, "black", "kululli"));
        words.add(new Word(R.drawable.color_white, "white", "kelelli"));
        words.add(new Word(R.drawable.color_dusty_yellow, "dusty yellow", "ṭopiisә"));
        words.add(new Word(R.drawable.color_mustard_yellow, "mustard yellow", "chiwiiṭә"));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_colors);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);
    }
}

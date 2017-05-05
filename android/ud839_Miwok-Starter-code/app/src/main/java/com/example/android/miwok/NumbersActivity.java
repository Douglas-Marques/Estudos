package com.example.android.miwok;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class NumbersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.category_numbers);
        setContentView(R.layout.word_list);

        ArrayList<Word> words = new ArrayList<>();
        words.add(new Word(R.drawable.number_one, "one", "lutti"));
        words.add(new Word(R.drawable.number_two, "two", "otiiko"));
        words.add(new Word(R.drawable.number_three, "three", "toloosoku"));
        words.add(new Word(R.drawable.number_four, "four", "oyyisa"));
        words.add(new Word(R.drawable.number_five, "five", "massokka"));
        words.add(new Word(R.drawable.number_six, "six", "temmokka"));
        words.add(new Word(R.drawable.number_seven, "seven", "kenekaku"));
        words.add(new Word(R.drawable.number_eight, "eight", "kawinta"));
        words.add(new Word(R.drawable.number_nine, "nine", "wo'e"));
        words.add(new Word(R.drawable.number_ten, "ten", "na'aacha"));

        WordAdapter adapter = new WordAdapter(this, words, R.color.category_numbers);
        ListView listView = (ListView)findViewById(R.id.list);
        listView.setAdapter(adapter);

    }
}

package com.domenico.wordle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import com.domenico.wordle.UI.HDTMButtonAdapter;

public class MainActivity extends AppCompatActivity {

    private GridView gridView;
    private HDTMButtonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new HDTMButtonAdapter(this);
        adapter.setElements(new WordList(this).getMediumWords());
        if (getIntent().hasExtra("lastItemClickable"))
            adapter.setLastItemClickable(getIntent().getExtras().getInt("lastItemClickable"));
        gridView = (GridView) findViewById(R.id.gridview);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Log.i("Main", "Bottone "+(position+1));
                play(position+1);
            }
        });
    }

    public void play(int position) {
        Intent intent = new Intent(this, GameActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("rowToGuess", position);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        this.finish();
    }
}

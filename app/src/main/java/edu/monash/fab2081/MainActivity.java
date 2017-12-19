package edu.monash.fab2081;

import android.os.Bundle;
import android.support.design.widget.BaseTransientBottomBar;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> listItems = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    private ListView myListView;
    private Snackbar snaker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        myListView = (ListView) findViewById(R.id.listView);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listItems);
        myListView.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addListItem();
                snaker=Snackbar.make(view, listItems.get(listItems.size()-1).toString()+"Item added to list", Snackbar.LENGTH_LONG);
                        snaker.setAction("Undo", undoOnClickListener).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.breaker:
                addBreaker();
                adapter.notifyDataSetChanged();
                return true;
            case R.id.clearList:
                clearList();
                adapter.notifyDataSetChanged();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
//        if (item.getItemId() == R.id.breaker) {
//            listItems.add("123");
//            adapter.notifyDataSetChanged();
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
    }

    public void addBreaker(){
        listItems.add("=====breaker======");
    }

    public void clearList(){
        listItems.clear();
    }

    View.OnClickListener undoOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            snackbar.setText("123");
            Snackbar.make(view,"123",Snackbar.LENGTH_LONG).setText("456");
//            snaker.setText("123");
            listItems.remove(listItems.size() -1);
            adapter.notifyDataSetChanged();
        }
    };



    private void addListItem() {
        SimpleDateFormat dateformat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy", Locale.ENGLISH);
        listItems.add(dateformat.format(new Date()));
        adapter.notifyDataSetChanged();
    }
}

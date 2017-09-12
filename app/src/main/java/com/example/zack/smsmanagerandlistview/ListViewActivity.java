package com.example.zack.smsmanagerandlistview;

import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import android.app.FragmentManager;
import android.app.ListActivity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.os.SystemClock;
import android.provider.ContactsContract;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;

import static com.example.zack.smsmanagerandlistview.R.layout.fragment_custom_alertdialog;

/**
 * Created by Zack on 9/9/2017.
 */

public class ListViewActivity extends AppCompatActivity {

    private static final String TAG = "ListViewActivityTag";
    private ListView listView;

    final ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        listView = (ListView) findViewById(R.id.list_view);
        list.add("This is a list item");
        list.add("And so is this");
        list.add("And so is this");
        list.add("And this is too");
        list.add("Just like this");
        list.add("If you bet this is a list item...");
        list.add("you'd be right.");
        list.add("That thingabob above the thingabob above this...");
        list.add("...is also a list item.");
        list.add("Woah.");
        Iterator itr = list.iterator();

        while (itr.hasNext()) {
            Log.d(TAG, "onCreate: " + itr.next());
        }
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        listView.setAdapter(adapter);
        FragmentManager fm = getFragmentManager();
        final ImageDialogFragment dialogFragment = new ImageDialogFragment();
        dialogFragment.show(fm, "Sample Fragment");
        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                dialogFragment.dismiss();
                t.cancel();
            }
        }, 3000);
    }


    public void goToShortMessageService(View view) {
        final Intent intent = new Intent(this, ShortMessageServiceActivity.class);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Are you sure you want to go to the Short Message Service?");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        startActivity(intent);
                    }
                });
        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

    public void goToShortMessageService2(View view) {
        final Intent intent = new Intent(this, ShortMessageServiceActivity.class);
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setCancelable(true);
        builder1.setView(fragment_custom_alertdialog);
        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        startActivity(intent);
                    }
                });
        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.show();
    }


}
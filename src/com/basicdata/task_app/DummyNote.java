package com.basicdata.task_app;

import android.app.ListActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;

/**
 * Created by jky on 15-7-20.
 */
public class DummyNote extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            setAdapter();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private NotesDbAdapter mDbHelper;
    private Cursor mNotesCursor;

    private void setAdapter() throws SQLException {
        mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();
        fillData();
    }

    @SuppressWarnings("deprecation")
    private void fillData() {
        mNotesCursor = mDbHelper.getall();
        startManagingCursor(mNotesCursor);
        String[] from = new String[] {NotesDbAdapter.KEY_NOTE};

        int[] to = new int[] {android.R.id.text1};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,mNotesCursor, from, to);
        setListAdapter(adapter);

    }

    /*    private static final String[] note_array =  new String[] {
            "gasolin",
            "crota",
            "louk",
            "magicion"
    };*/
/*    private ListView list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummynote);

        try {
            createData();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        list = (ListView) findViewById(R.id.android_list);
        list.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, note_array));

    }

    private NotesDbAdapter mDbHelper;

    private void createData() throws SQLException {
        mDbHelper = new NotesDbAdapter(this);
        mDbHelper.open();
    }*/
}
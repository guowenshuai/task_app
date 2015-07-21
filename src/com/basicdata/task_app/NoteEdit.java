package com.basicdata.task_app;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.sql.SQLException;

/**
 * Created by jky on 15-7-21.
 */
public class NoteEdit extends Activity {

    private NotesDbAdapter mDbHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDbHelper = new NotesDbAdapter(this);
        try {
            mDbHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        setContentView(R.layout.note_edit);
        findViews();
        showViews(savedInstanceState);
    }

    private EditText field_note;
    private Button button_confirm;

    private void findViews() {
        field_note = (EditText) findViewById(R.id.note);
        button_confirm = (Button) findViewById(R.id.confirm);
    }

    private Long mRowId;
    private void showViews(Bundle savedInstanceState) {
        mRowId = savedInstanceState != null ? savedInstanceState.getLong(NotesDbAdapter.KEY_ROWID) : null;
        if (mRowId == null) {
            Bundle extras = getIntent().getExtras();
            mRowId = extras != null ? extras.getLong(NotesDbAdapter.KEY_ROWID) : null;
        }
        populateFields();

        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDbHelper.update(mRowId, field_note.getText().toString());
                /*test for return Intent*/
                Intent ret = new Intent();
                ret.putExtra("back_values", "Back Data");
                setResult(RESULT_OK, ret);

                /*  返回给父亲Activity的结果，setResult是重载函数
                *   setResult(int resultCode, Intent data);
                *   setResult(int resultCode);
                */
//                setResult(RESULT_OK);
                finish();
            }
        });
    }

    private void populateFields() {
        if (mRowId != null) {
            Cursor note = mDbHelper.get(mRowId);
            //noinspection deprecation
            startManagingCursor(note);
            field_note.setText(note.getString(note.getColumnIndexOrThrow(NotesDbAdapter.KEY_NOTE)));
            Log.v("NOTE EDIT", String.valueOf(note.getColumnIndexOrThrow(NotesDbAdapter.KEY_NOTE)));
        }
    }
}
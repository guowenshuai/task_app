package com.basicdata.task_app;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.sql.SQLException;

/**
 * Created by jky on 15-7-20.
 */
public class DummyNote extends ListActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dummynote);
        getListView().setEmptyView(findViewById(R.id.empty));
        registerForContextMenu(getListView());
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
        String[] from = new String[]{NotesDbAdapter.KEY_NOTE};

        int[] to = new int[]{android.R.id.text1};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1, mNotesCursor, from, to);
        setListAdapter(adapter);

    }

    private int mNoteNumber = 1;
    protected static final int MENU_INSERT = Menu.FIRST;
    protected static final int MENU_DELETE = Menu.FIRST+1;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        menu.add(0, MENU_INSERT, 0, getString(R.string.add_notes));
        menu.add(0, MENU_DELETE, 0, getString(R.string.del_notes));
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_INSERT:
                String noteName = "Note  " + mNoteNumber++;
                mDbHelper.create(noteName);
                fillData();
                return true;
            case MENU_DELETE:
                mDbHelper.delete(clickItemId);
                clickItemId = -1;
                fillData();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private static long clickItemId = -1;
    private static final int ACTIVITY_EDIT = 0x1001;/*一个用于识别intent Activity的自定义数字标识符*/

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {

        clickItemId = id;
        super.onListItemClick(l, v, position, id);
        Intent intent = new Intent(this, NoteEdit.class);
        intent.putExtra(NotesDbAdapter.KEY_ROWID, id);
        startActivityForResult(intent, ACTIVITY_EDIT);/*startActivityForResult启动的intent，
                            *第二个参数数标识新的Activity的标识符，该函数和onActivityResult是共生的关系
                            *onActivityResult负责处理从其他Activity返回的消息*/
    }

    /*onActivityResult根据接收到的requestCode来判断是哪个调用的Activity返回的数据*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        fillData();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info;
        info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case MENU_DELETE:
                Log.d("MENU", "item" + info.id);
                mDbHelper.delete(info.id);
                fillData();
                break;
        }

        return super.onContextItemSelected(item);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        menu.add(0, MENU_INSERT, 0, getString(R.string.add_notes));
        menu.setHeaderTitle("Detail view");
        menu.add(0, MENU_DELETE, 0, getString(R.string.del_notes));
        super.onCreateContextMenu(menu, v, menuInfo);
    }
}
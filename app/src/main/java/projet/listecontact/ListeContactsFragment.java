package projet.listecontact;

import android.app.ListFragment;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;

import static projet.listecontact.R.layout.liste_contacts_fragment;

public class ListeContactsFragment extends ListFragment {

    private ContactDbAdapter mdbHelper;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(liste_contacts_fragment, null);
        //dbHelper
        mdbHelper=new ContactDbAdapter(this.getActivity());
        mdbHelper.open();
        fillData();
        return rootView;
    }

    private void fillData(){
        Cursor c = mdbHelper.fetchAll();
        getActivity().startManagingCursor(c);

        String[] from = new String[]{ContactDbAdapter.KEY_ROWNOM,ContactDbAdapter.KEY_ROWPRENOM};

        int[] to = new int[]{R.id.text1,R.id.text2};

        SimpleCursorAdapter contacts = new SimpleCursorAdapter(getActivity(), R.layout.row, c, from, to);
        setListAdapter(contacts);
    }

    
}
package projet.listecontact;

import android.app.ListFragment;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Debug;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.List;

import static projet.listecontact.R.layout.liste_contacts_fragment;

public class ListeContactsFragment extends ListFragment {

    private ContactDbAdapter mdbHelper;

    String nomContactSelected, prenomContactSelected, telContactSelected, mailContactSelected;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(liste_contacts_fragment, null);
        //dbHelper
        mdbHelper = new ContactDbAdapter(this.getActivity());
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


    public void onListItemClick (ListView l, View v, int position, long id){
        //On utilise un cursor pour chercher le contact avec l'id recherchée
        Cursor c = mdbHelper.fetch(id);
        //Stockage des infos de la BDD dans les variables globales
        nomContactSelected = c.getString(c.getColumnIndex("_nom"));
        prenomContactSelected = c.getString(c.getColumnIndex("_prenom"));
        telContactSelected = c.getString(c.getColumnIndex("_telephone"));
        mailContactSelected = c.getString(c.getColumnIndex("_email"));

        registerForContextMenu(v);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        CreateMenu(menu);
    }
    private void CreateMenu(Menu menu)
    {
        menu.add(0, 0,0, "voir contact");
        menu.add(0, 1,1, "Appeler");
        menu.add(0, 2,2, "Envoyer un message");
        menu.add(0, 3,3, "Supprimer");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId())
        {
            //voir contact
            case 0:
                //Création d'un itent qui passe les valeurs de la BDD à la page profil
                Intent intent = new Intent(getActivity(), ProfilActivity.class);
                intent.putExtra("nom", nomContactSelected);
                intent.putExtra("prenom", prenomContactSelected);
                intent.putExtra("tel", telContactSelected);
                intent.putExtra("mail", mailContactSelected);
                startActivity(intent);
                break;

            //appeler
            case 1:

            //envoyer un message
            case 2:

            //supprimer
            case 3:
        }
        return false;
    }
}

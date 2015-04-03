package projet.listecontact;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends Activity {

    ActionBar.Tab tabListeContacts, tabAjoutContact;

    Fragment listeContactsFragment = new ListeContactsFragment();
    Fragment ajoutContactFragment = new AjoutContactFragment();

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projet);

        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // onglet liste des contacts
        tabListeContacts = actionBar.newTab().setText("Liste de contacts");
        tabListeContacts.setTabListener(new TabListener(listeContactsFragment));
        actionBar.addTab(tabListeContacts);

        // onglet ajout d'un contact
        tabAjoutContact = actionBar.newTab().setText("Ajouter contact");
        tabAjoutContact.setTabListener(new TabListener(ajoutContactFragment));
        actionBar.addTab(tabAjoutContact);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_projet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch(item.getItemId()){

        }

        return super.onOptionsItemSelected(item);
    }

}

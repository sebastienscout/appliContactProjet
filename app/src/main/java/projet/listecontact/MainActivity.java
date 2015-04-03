package projet.listecontact;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;

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
}

package projet.listecontact;

import android.app.AlertDialog;
import android.app.ListFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.List;

import static projet.listecontact.R.layout.liste_contacts_fragment;

public class ListeContactsFragment extends ListFragment {

    private ContactDbAdapter mdbHelper;

    String nomContactSelected, prenomContactSelected, telContactSelected, mailContactSelected, adresseContactSelected;
    private Long idl;

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
        adresseContactSelected=c.getString(c.getColumnIndex("_adresse"));
        idl= new Long(c.getInt(c.getColumnIndex("_id")));

        registerForContextMenu(v);
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            //voir contact
            case R.id.see:
                //Création d'un itent qui passe les valeurs de la BDD à la page profil
                Intent intent = new Intent(getActivity(), ProfilActivity.class);
                intent.putExtra("nom", nomContactSelected);
                intent.putExtra("prenom", prenomContactSelected);
                intent.putExtra("tel", telContactSelected);
                intent.putExtra("mail", mailContactSelected);
                startActivity(intent);
                break;

            case R.id.call:
                Uri uriCall = Uri.parse("tel:"+telContactSelected.trim());
                Intent callIntent = new Intent(Intent.ACTION_DIAL, uriCall);
                PackageManager packageManagerTel = getActivity().getPackageManager();
                List<ResolveInfo> activitiesTel = packageManagerTel.queryIntentActivities(callIntent, 0);
                boolean isIntentSafeTel = activitiesTel.size() > 0;

                // demarre l'activite s'il existe des applications capables de l'ouvrir
                if (isIntentSafeTel) {
                    startActivity(callIntent);
                    this.getActivity().recreate();//on recrée l'activité afin de pouvoir selectionner un autre item par la suite
                }
                return true;

            //envoyer un message (code Ok)
            case R.id.text:
                Uri uriText = Uri.parse("smsto:"+telContactSelected);
                Intent it = new Intent(Intent.ACTION_SENDTO, uriText);
                // Verify it resolves
                PackageManager packageManagerText = getActivity().getPackageManager();
                List<ResolveInfo> activitiesText = packageManagerText.queryIntentActivities(it, 0);
                boolean isIntentSafeText = activitiesText.size() > 0;

                // demarre l'activite s'il existe des applications capables de l'ouvrir
                if (isIntentSafeText) {
                    startActivity(it);
                    this.getActivity().recreate();//on recrée l'activité afin de pouvoir selectionner un autre item par la suite
                }

                return true;

            //supprimer (code Ok)
            case R.id.supp:

                // Création d'un AlertDialog de demande de confirmation
                AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
                // Choix du titre
                ad.setTitle(R.string.titre_alertdialog_confirm);
                // mise en place du bouton de validation
                ad.setPositiveButton(R.string.bouton_alertdialog_ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                mdbHelper.deleteContact(idl);//suppression par l'ID de la ligne
                                fillData();

                            }
                        }
                );
                // mise en place du bouton d'annulation
                ad.setNegativeButton(R.string.bouton_alertdialog_annuler,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        }
                );
                // rendre le dialog annulable (en cliquant en dehors)
                ad.setCancelable(true);
                // faire apparaître le dialog
                ad.show();
                return true;
        }
        return false;
    }
}

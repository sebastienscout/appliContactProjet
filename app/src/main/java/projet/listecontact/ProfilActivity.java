package projet.listecontact;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class ProfilActivity extends Activity {

    private TextView nomView, prenomView, telView, mailView, adresseView, villeView, cpView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        // Bouton retour sur la barre d'action
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Instanciation des Textviews
        nomView = (TextView)findViewById(R.id.profilNom);
        prenomView = (TextView)findViewById(R.id.profilPrenom);
        telView = (TextView)findViewById(R.id.profilTelephone);
        mailView = (TextView)findViewById(R.id.profilMail);
        adresseView = (TextView)findViewById(R.id.profilAdresse);
        villeView = (TextView)findViewById(R.id.profilVille);
        cpView = (TextView)findViewById(R.id.profilCP);

        //On recupère les données passées avec le intent
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //On donne la valeur de chaque extra à un textView correspondant
            nomView.setText(extras.getString("nom"));
            prenomView.setText(extras.getString("prenom"));
            telView.setText(extras.getString("tel"));
            mailView.setText(extras.getString("mail"));
            adresseView.setText(extras.getString("adresse"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_projet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


}

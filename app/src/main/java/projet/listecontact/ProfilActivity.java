package projet.listecontact;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ProfilActivity extends Activity {

    private TextView nomView, prenomView, telView, mailView, adresseView;

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
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_projet, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Bundle extras = getIntent().getExtras();
        switch (item.getItemId()) {
            case R.id.menu_modify:
                Intent intent = new Intent(this, ModifierActivity.class);
                intent.putExtra("id", extras.getLong("id"));
                intent.putExtra("nom", extras.getString("nom"));
                intent.putExtra("prenom", extras.getString("prenom"));
                intent.putExtra("tel", extras.getString("tel"));
                intent.putExtra("mail", extras.getString("mail"));
                intent.putExtra("adresse", extras.getString("adresse"));
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}

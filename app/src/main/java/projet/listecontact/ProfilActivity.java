package projet.listecontact;

import android.app.Activity;
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
        nomView = (TextView)findViewById(R.id.nom);
        prenomView = (TextView)findViewById(R.id.prenom);
        telView = (TextView)findViewById(R.id.telephone);
        mailView = (TextView)findViewById(R.id.mail);
        adresseView = (TextView)findViewById(R.id.profilAdresse);
        villeView = (TextView)findViewById(R.id.profilVille);
        cpView = (TextView)findViewById(R.id.profilCP);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_projet, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*int id = item.getItemId();
        switch(item.getItemId()){
            case R.id.

        }*/
        //noinspection SimplifiableIfStatement
       /* if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}

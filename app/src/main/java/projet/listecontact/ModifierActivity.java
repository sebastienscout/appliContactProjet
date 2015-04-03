package projet.listecontact;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class ModifierActivity extends Activity {

    private EditText nom,prenom,tel,email,adresse;
    private Long id;

    private ContactDbAdapter mdbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifier);

        // Bouton retour sur la barre d'action
        getActionBar().setDisplayHomeAsUpEnabled(true);

        nom= (EditText) findViewById(R.id.nom);
        prenom=(EditText) findViewById(R.id.prenom);
        tel=(EditText) findViewById(R.id.telephone);
        email=(EditText) findViewById(R.id.mail);
        adresse=(EditText) findViewById(R.id.localisation);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            //On donne la valeur de chaque extra par defaut aux editText
            id = extras.getLong("id");
            nom.setText(extras.getString("nom"));
            prenom.setText(extras.getString("prenom"));
            tel.setText(extras.getString("tel"));
            email.setText(extras.getString("mail"));
            adresse.setText(extras.getString("adresse"));
        }

        //dbHelper
        mdbHelper=new ContactDbAdapter(this);
        mdbHelper.open();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_modifier, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_enregistrer:
                modification();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void modification(){
        //test les inputs
        if(!(nom.getText().toString().trim().length()==0)&&!(tel.getText().toString().trim().length()==0)){
            mdbHelper.updateContact(id,nom.getText().toString(), prenom.getText().toString(), tel.getText().toString(), email.getText().toString(), adresse.getText().toString());
            recreate();
        }
        else{
            // Création d'un AlertDialog d'interpellation
            AlertDialog.Builder ad = new AlertDialog.Builder(this);
            // Choix du titre
            ad.setTitle(R.string.titre_err);
            // mise en place du bouton de validation
            ad.setPositiveButton(R.string.bouton_recommencer,
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // pas d'actions particulieres sur le clic
                        }
                    }
            );
            // rendre le dialog annulable (en cliquant en dehors)
            ad.setCancelable(true);
            // faire apparaître le dialog
            ad.show();
        }
    }
}

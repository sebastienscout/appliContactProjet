package projet.listecontact;
import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class AjoutContactFragment extends Fragment{

    //attributs du formulaire de création
    private EditText nom;
    private EditText prenom;
    private EditText tel;
    private EditText email;
    private EditText numrue;
    private EditText ville;
    private EditText cp;

    private ContactDbAdapter mdbHelper;
    private Button monButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ajout_contact_fragment, null);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        //instanciation des elements du formulaire
        nom= (EditText) view.findViewById(R.id.nom);
        prenom=(EditText) view.findViewById(R.id.prenom);
        tel=(EditText) view.findViewById(R.id.telephone);
        email=(EditText) view.findViewById(R.id.mail);
        numrue=(EditText) view.findViewById(R.id.localisation);
        ville=(EditText) view.findViewById(R.id.ville);
        cp=(EditText) view.findViewById(R.id.cp);

        monButton = (Button) view.findViewById(R.id.valider);

        monButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creation();

            }
        });

        //dbHelper
        mdbHelper=new ContactDbAdapter(this.getActivity());
        mdbHelper.open();
        System.out.println("attr"+nom+prenom+tel+email+mdbHelper);
    }

    //code OK
    public void creation(){
        //avant d'inserer on teste si les inputs requis (nom et telephone) sont nuls
        if(!(nom.getText().toString().trim().length()==0)&&!(tel.getText().toString().trim().length()==0)){
            mdbHelper.createContact(nom.getText().toString(), prenom.getText().toString(), tel.getText().toString(), email.getText().toString(), numrue.getText().toString()+" "+ville.getText().toString()+" "+cp.getText().toString());
            this.getActivity().recreate();//on recrée l'activité si le contact est créé
        }
        else{
            // Création d'un AlertDialog d'interpellation
            AlertDialog.Builder ad = new AlertDialog.Builder(getActivity());
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
        } }



}

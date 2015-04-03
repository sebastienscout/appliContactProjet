package projet.listecontact;
import android.app.Fragment;
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
    private EditText nrue;
    private EditText ville;
    private EditText cp;
    private String adresse;
    private ContactDbAdapter mdbHelper;
    private Button monButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.ajout_contact_fragment, null);
        //instanciation des elements du formulaire
        nom= (EditText) rootView.findViewById(R.id.nom);
        prenom=(EditText) rootView.findViewById(R.id.prenom);
        tel=(EditText) rootView.findViewById(R.id.telephone);
        email=(EditText) rootView.findViewById(R.id.mail);
        nrue=(EditText) rootView.findViewById(R.id.localisation);
        ville=(EditText) rootView.findViewById(R.id.ville);
        cp=(EditText) rootView.findViewById(R.id.cp);
        adresse=new String(nrue.getText().toString()+ville.getText().toString()+cp.getText().toString());
        monButton = (Button) rootView.findViewById(R.id.valider);

        monButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                creation();

            }
        });

        //dbHelper
        mdbHelper=new ContactDbAdapter(this.getActivity());
//        mdbHelper.close();
        mdbHelper.open();
        System.out.println("attr"+nom+prenom+tel+email+mdbHelper);
        return rootView;
    }

    //code OK
    public void creation(){
        mdbHelper.createContact(nom.getText().toString(),prenom.getText().toString(),tel.getText().toString(),email.getText().toString(),adresse);
    }



}

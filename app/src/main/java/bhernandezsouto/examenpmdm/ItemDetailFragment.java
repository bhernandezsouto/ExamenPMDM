package bhernandezsouto.examenpmdm;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import bhernandezsouto.examenpmdm.dummy.DummyContent;

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a {@link ItemListActivity}
 * in two-pane mode (on tablets) or a {@link ItemDetailActivity}
 * on handsets.
 */
public class ItemDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The dummy content this fragment is presenting.
     */
    private DummyContent.DummyItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            mItem = DummyContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));

            Activity activity = this.getActivity();
            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
            if (appBarLayout != null) {
                appBarLayout.setTitle(mItem.content);
            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_item_detail, container, false);


        Button boton = (Button) rootView.findViewById(R.id.Borrar);//Buscamos el boton del fragment por id y lo guardamos en una variable de tipo boton
        boton.setOnClickListener(new View.OnClickListener() {//Enlazamos el boton a un listener
            @Override
            //Metodo onClick del boton, esto se usara cada que se le haga click al boton
            public void onClick(View v) {
                //se recoge el fragment de la lista
                ItemListFragment frag = (ItemListFragment) getFragmentManager().findFragmentById(R.id.item_list);
                // Este if evalua si el fragment esta cargado en pantalla, si no lo esta cierra la activity
                // y si lo esta (else) hace un set del texto dejandolo en blanco

                // Para mandar un resultado a la activity anterior es necesario usar un intent
                // En este caso con el PutExtra mandamos el "resultado" y su contenido y se manda
                // y se cierra la actividad
                if (frag == null || !frag.isInLayout()) {
                    Intent intentResultado = new Intent();
                    intentResultado.putExtra("resultado", "Activity Cerrada");
                    getActivity().setResult(Activity.RESULT_OK, intentResultado);
                    getActivity().finish();
                } else {
                    ((TextView) rootView.findViewById(R.id.item_detail)).setText("");
                }

            }
        });

        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.item_detail)).setText(mItem.details);
        }

        return rootView;
    }
}

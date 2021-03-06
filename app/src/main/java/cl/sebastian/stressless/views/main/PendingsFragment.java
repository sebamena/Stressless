package cl.sebastian.stressless.views.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cl.sebastian.stressless.R;
import cl.sebastian.stressless.adapters.PendingClickListener;
import cl.sebastian.stressless.adapters.PendingsAdapter;
import cl.sebastian.stressless.models.Pending;
import cl.sebastian.stressless.views.details.DetailsActivity;

/**
 * A placeholder fragment containing a simple view.
 */
public class PendingsFragment extends Fragment implements PendingClickListener {

    private PendingsAdapter adapter;

    public static final String PENDING_ID  = "cl.sebastian.stressless.views.main.KEY.PENDING_ID";


    public PendingsFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.pendingRecyclerView);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);


//        for (int i=0;i<20;i++){
//
//            Pending pending = new Pending();
//            pending.setName(String.valueOf(i));
//            pending.setDone(false);
//            pending.save();
//        }

        adapter = new PendingsAdapter(this);
        recyclerView.setAdapter(adapter);

    }

    public void updateList(String name){
        adapter.updateByName(name);



    }


    public void updatelist(Pending pending) {

        adapter.update(pending);

    }

    @Override
    public void clickedID(long id) {

        //Toast.makeText(getContext(), String.valueOf(id), Toast.LENGTH_SHORT).show();

        Intent myIntent = new Intent(getActivity(), DetailsActivity.class);
        myIntent.putExtra(PENDING_ID, id);
        startActivity(myIntent);



    }
}

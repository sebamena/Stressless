package cl.sebastian.stressless.adapters;

import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.List;

import cl.sebastian.stressless.PendingClickListener;
import cl.sebastian.stressless.R;
import cl.sebastian.stressless.data.Queries;
import cl.sebastian.stressless.models.Pending;

/**
 * Created by Sebastián Mena on 17-08-2017.
 */

public class PendingsAdapter extends RecyclerView.Adapter<PendingsAdapter.ViewHolder> {

    private List<Pending> pendings = new Queries().pendings();

    private PendingClickListener listener;

    public PendingsAdapter(PendingClickListener listener) {
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_pending, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Pending pending = pendings.get(position);
        holder.textView.setText(pending.getName());
        holder.checkbox.setChecked(pending.isDone());

        holder.checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            int auxPosition = holder.getAdapterPosition();
                            Pending auxPending = pendings.get(auxPosition);
                            auxPending.setDone(true);
                            auxPending.save();
                            pendings.remove(auxPosition);
                            notifyItemRemoved(auxPosition);

                        }
                    }, 400);

                }


            }
        });


        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pending auxPending = pendings.get(holder.getAdapterPosition());
                listener.clickedID(auxPending.getId());

            }
        });

    }

    @Override
    public int getItemCount() {
        return pendings.size();
    }


    public void update(Pending pending) {

        pendings.add(pending);
        notifyDataSetChanged();

    }

    public void updateByName(String name){

    List<Pending> byName = new Queries().byName(name);
        pendings.clear();
        pendings.addAll(byName);
        notifyDataSetChanged();


    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        private CheckBox checkbox;
        private TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);

            checkbox = (CheckBox) itemView.findViewById(R.id.pendingCb);
            textView = (TextView) itemView.findViewById(R.id.pendingTv);
        }
    }

}

package cl.sebastian.stressless.views.details;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.EditText;

import cl.sebastian.stressless.R;
import cl.sebastian.stressless.data.Queries;
import cl.sebastian.stressless.models.Pending;

public class DetailsActivity extends AppCompatActivity {

    public static final String PENDING_ID  = "cl.sebastian.stressless.views.main.KEY.PENDING_ID";

    private long pending_id;
    private Pending pending;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        pending_id = getIntent().getLongExtra(PENDING_ID, 0);

        pending = new Queries().byId(pending_id);

        Log.d("PENDING_ID", pending.getName());

        getSupportActionBar().setTitle(pending.getName());

        editText = (EditText) findViewById(R.id.descriptionEt);



        //Toast.makeText(this, pending.getName(), Toast.LENGTH_SHORT).show();

    }


    @Override
    protected void onResume() {
        super.onResume();

        String description = pending.getDescription();

        if(description!=null){

            editText.setText(description);

        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        String description = editText.getText().toString();
        pending.setDescription(description);
        pending.save();

    }
}

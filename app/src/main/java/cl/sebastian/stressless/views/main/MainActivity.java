package cl.sebastian.stressless.views.main;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import cl.sebastian.stressless.R;
import cl.sebastian.stressless.SearchListener;
import cl.sebastian.stressless.models.Pending;

public class MainActivity extends AppCompatActivity implements SearchListener {

    private PendingsFragment pendingsFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        pendingsFragment = (PendingsFragment) getSupportFragmentManager().findFragmentById(R.id.pendingsFragment);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(MainActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialog_pending);

                ImageButton button = (ImageButton) dialog.findViewById(R.id.savependingBtn);

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        EditText input = (EditText) dialog.findViewById(R.id.pendingEt);
                        String name = input.getText().toString();

                        if (name.trim().length() > 0) {

                            Pending pending = new Pending();
                            pending.setName(name);
                            pending.setDone(false);
                            pending.save();

                            pendingsFragment.updatelist(pending);

                        }

                        dialog.dismiss();


                    }
                });


                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.show();
            }
        });
    }

    @Override
    public void searched(String name) {
        pendingsFragment.updateList(name);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}

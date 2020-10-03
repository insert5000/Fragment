package com.example.fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity
        implements simpleFragment.OnFragmentInteractionListener {
    int sumar =1;
    int sumar2 =1;

    private  Button mButton;


    private boolean isFragmentDisplayed = false;
    static final String STATE_FRAGMENT = "state_of_fragment";
    private int mRadioButtonChoice = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = findViewById(R.id.open_button);
        if (savedInstanceState != null) {
            isFragmentDisplayed =
                    savedInstanceState.getBoolean(STATE_FRAGMENT);
            if (isFragmentDisplayed) {
                // If the fragment is displayed, change button to "close".
                mButton.setText(R.string.close);
            }
        }

    mButton.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            if (!isFragmentDisplayed) {
                displayFragment();
            } else {
                closeFragment();
            }
        }
    });


    }
    public void displayFragment() {
        simpleFragment simpleFragment = com.example.fragment.simpleFragment.newInstance(mRadioButtonChoice);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager
                .beginTransaction();
        fragmentTransaction.add(R.id.fragment_container,
                simpleFragment).addToBackStack(null).commit();
        mButton.setText(R.string.close);
        isFragmentDisplayed = true;
    }

    public void closeFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        simpleFragment simpleFragment = (com.example.fragment.simpleFragment) fragmentManager
                .findFragmentById(R.id.fragment_container);
        if (simpleFragment != null) {
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            fragmentTransaction.remove(simpleFragment).commit();
        }
        mButton.setText(R.string.open);

        isFragmentDisplayed = false;
        TextView tv_ganandor = (TextView)findViewById(R.id.tv_ganador);
        if(sumar>sumar2){
            tv_ganandor.setText("Ganador Narnia");
        }
        if(sumar == sumar2){
            tv_ganandor.setText("Empate");
        }
        if(sumar<sumar2){
            tv_ganandor.setText("Ganador El padrino");
        }

        ////////////////////////////

    }

    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putBoolean(STATE_FRAGMENT, isFragmentDisplayed);
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void onRadioButtonChoice(int choice) {
        TextView tv_narnia = (TextView)findViewById(R.id.tv_narnia);
        TextView tv_padrino = (TextView)findViewById(R.id.tv_padrino);



        if(choice==0){

            tv_narnia.setText("Narnia:"+sumar++);
            Toast.makeText(this, "La elección es " + "Narnia",
                    Toast.LENGTH_SHORT).show();
            closeFragment();
        }else{
            tv_padrino.setText("Padrino:"+sumar2++);
            Toast.makeText(this, "La elección es " + "El padrino",
                    Toast.LENGTH_SHORT).show();
            closeFragment();
        }


    }
}

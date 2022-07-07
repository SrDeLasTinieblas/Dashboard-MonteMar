package com.dashboard.montemar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public ImageButton openFragment;
    Button primerFragment, segundoFragment ;
    //public Button openFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        primerFragment = findViewById(R.id.fragment1Btn);
        segundoFragment = findViewById(R.id.fragment2Btn);


        openFragment = findViewById(R.id.ImageButtonPanel);
    }
    public void setPrimerFragment(View view){
        replaceFragment(new Fragment1());
    }
    public void setSegundoFragment(View view){
        replaceFragment(new Fragment2());
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout, fragment);
        fragmentTransaction.commit();
    }

    public void onClickpanel(View view){
        try {
            FragmentPanel fragmentPanel = new FragmentPanel();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FragmentIniciio, fragmentPanel);
            fragmentTransaction.commit();
        }catch (Exception e){
            System.out.println(e);
        }

    }
    public void onClickInicio(View view){
        try{
            FragmentIniciio fragmentIniciio = new FragmentIniciio();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.FragmentPanel, fragmentIniciio);
            fragmentTransaction.commit();
        }catch (Exception e){
            System.out.println(e);
        }

    }
    /*public void onClickUsuario(View view){
        FragmentUsuario fragmentIniciio = new FragmentUsuario();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FragmentPanel, fragmentIniciio);
        fragmentTransaction.commit();
    }*/
    /*public void onClickventanaOpenFragment(View view){
        FragmentIniciio FragmentIniciio = new FragmentIniciio();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.FragmentPanel, FragmentIniciio);
        FragmentIniciio.getActivity();
    }*/
    @Override
    public void onClick(View view) {

    }
}





















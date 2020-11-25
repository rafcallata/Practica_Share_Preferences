package com.example.almacenamientosp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toolbar;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private SharedPreferences mPreferences;
    private SharedPreferences.Editor mEditor;

    private EditText mUsuario, mClave;
    private Button btnLogin;
    private CheckBox mCheckBox;

    private Button btnColor1;
    private Button btnColor2;
    private Button btnColor3;
    private Button btnColor4;
    private Toolbar mtoolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsuario = (EditText) findViewById(R.id.edtUsuario);
        mClave = (EditText) findViewById(R.id.edtClave);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox);
        btnColor1= (Button) findViewById(R.id.btncolor1);
        btnColor2= (Button) findViewById(R.id.btncolor2);
        btnColor3= (Button) findViewById(R.id.btncolor3);
        btnColor4= (Button) findViewById(R.id.btncolor4);
        mtoolbar=(Toolbar)findViewById(R.id.toolbar);

        mPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mPreferences.edit();


        if (getcolor()!=getResources().getColor(R.color.colorPrimary)){
            mtoolbar.setBackgroundColor(getcolor());
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getcolor());
        }
        }

        btnColor1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mtoolbar.setBackgroundColor(getResources().getColor(R.color.colorRed));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1){
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorRed));
                }
                colorPreferences(getResources().getColor(R.color.colorRed));
            }
        });
        btnColor2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mtoolbar.setBackgroundColor(getResources().getColor(R.color.colorBlue));
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP_MR1){
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorBlue));
                }
                colorPreferences(getResources().getColor(R.color.colorBlue));
            }
        });
        btnColor3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mtoolbar.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP_MR1){
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorGreen));
                }
                colorPreferences(getResources().getColor(R.color.colorGreen));
            }
        });
        btnColor4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mtoolbar.setBackgroundColor(getResources().getColor(R.color.colorYellow));
                if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP_MR1){
                    getWindow().setStatusBarColor(getResources().getColor(R.color.colorYellow));
                }
                colorPreferences(getResources().getColor(R.color.colorYellow));
            }
        });

        checkSharedPreferences();
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mCheckBox.isChecked()) {
                    mEditor.putString(getString(R.string.checkbox), "True");
                    mEditor.commit();

                    String name = mUsuario.getText().toString();
                    mEditor.putString(getString(R.string.name),name);
                    mEditor.commit();

                    String password = mClave.getText().toString();
                    mEditor.putString(getString(R.string.password), password);
                    mEditor.commit();

                    Intent intent = new Intent(MainActivity.this, SegundaActividad.class);
                    startActivity(intent);

                } else {

                    mEditor.putString(getString(R.string.checkbox), "False");
                    mEditor.commit();
                    mEditor.putString(getString(R.string.name), "");
                    mEditor.commit();
                    mEditor.putString(getString(R.string.password), "");
                    mEditor.commit();
                }
            }
        });

    }
    private void checkSharedPreferences(){
        String checkbox = mPreferences.getString(getString(R.string.checkbox), "False");
        String name = mPreferences.getString(getString(R.string.name), "");
        String password = mPreferences.getString(getString(R.string.password), "");
        mUsuario.setText(name);
        mClave.setText(password);

        if(checkbox.equals("True")){
            mCheckBox.setChecked(true);
        }else{
            mCheckBox.setChecked(false);
        }
    }
    private void colorPreferences(int color){
        SharedPreferences mPreference = getSharedPreferences("toolbarColor",MODE_PRIVATE);
        SharedPreferences.Editor mEdito = mPreference.edit();
        mEdito.putInt("color",color);
        mEdito.apply();
    }
    private int getcolor(){
        SharedPreferences msharedPreference = getSharedPreferences("toolbarColor",MODE_PRIVATE);
        int colorseleccionado = msharedPreference.getInt("color",getResources().getColor(R.color.colorPrimary));
        return colorseleccionado;
    }
}
package curso.clases.splashscreensharedpreference;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import curso.clases.splashscreensharedpreference.Activities.InsideActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnLogin, btnClear;
    EditText editTextUser, editTextPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.Theme_SplashScreenSharedPreference);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        btnLogin = findViewById(R.id.btnLogin);
        btnClear = findViewById(R.id.btnClear);

        editTextUser = findViewById(R.id.editTextUsuario);
        editTextPassword = findViewById(R.id.editTextPassword);

        btnClear.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        ValidatePreferences();
    }

    private void ValidatePreferences() {
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = preferences.getString("user","");
        String password = preferences.getString("password","");

        editTextUser.setText(user);
        editTextPassword.setText(password);
        if(user != null && !user.equals("") && password != null && !password.equals("")){
            ChangeActivity();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnLogin:
                    ValidateInputs();
                break;
            case R.id.btnClear:
                    ClearInputs();
                break;
            default:
                break;
        }
    }

    private void ValidateInputs() {
        String user = editTextUser.getText().toString();
        String password = editTextPassword.getText().toString();

        if(!(user.equals(""))  && user != null && !(password.equals(""))  && password != null){
            SavePreferences(user,password);
            ChangeActivity();
        }
        else{
            Toast.makeText(MainActivity.this,"Credenciales Erroneas",Toast.LENGTH_LONG).show();
        }
    }

    private void ChangeActivity() {
        Intent intent = new Intent(this, InsideActivity.class);
        startActivity(intent);
    }

    private void SavePreferences(String user,String password) {
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("user",user);
        editor.putString("password",password);
        editor.commit();
    }

    private void ClearInputs() {
        editTextUser.setText("");
        editTextPassword.setText("");
    }
}
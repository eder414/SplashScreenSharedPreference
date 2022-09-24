package curso.clases.splashscreensharedpreference.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import curso.clases.splashscreensharedpreference.MainActivity;
import curso.clases.splashscreensharedpreference.R;

public class InsideActivity extends AppCompatActivity {
    TextView textViewUser, textViewPassword;
    Button deleteData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inside);

        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        String user = preferences.getString("user","");
        String password = preferences.getString("password","");

        textViewUser = findViewById(R.id.textViewUser);
        textViewPassword = findViewById(R.id.textViewPass);

        textViewUser.setText(user);
        textViewPassword.setText(password);

        deleteData = findViewById(R.id.deleteData);

        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteData();
            }
        });
    }

    private void DeleteData() {
        SharedPreferences preferences = getSharedPreferences("Credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.remove("user");
        editor.remove("password");
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
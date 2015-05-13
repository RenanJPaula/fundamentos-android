package br.com.example.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextUser;
    private EditText mEditTextPassword;
    private Button mButtonSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Procura a referência dos componentes
        findViewById(R.id.editTextUser);
        findViewById(R.id.editTextPassword);
        mButtonSignin = (Button) findViewById(R.id.buttonSignIn);

        // Adiciona o evento de click no botão
        mButtonSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Pega o nome do usuário digitado
                String userName = mEditTextUser.getText().toString();

                // Exibe o nome em um Toast
                Toast.makeText(v.getContext(), userName, Toast.LENGTH_LONG).show();
            }
        });
    }

}

package com.example.examensegundafase.ui.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.examensegundafase.MainActivity;
import com.example.examensegundafase.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    EditText usuario, contrasenia;
    AlertDialog.Builder builder;

    // Firebase
    FirebaseAuth mAuth;
    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Firebase
        mAuth = FirebaseAuth.getInstance();

        btn_login = (Button) findViewById(R.id.btnLogin);
        usuario = (EditText) findViewById(R.id.loginUsuario);
        contrasenia = (EditText) findViewById(R.id.loginContrasena);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailUser = usuario.getText().toString().trim();
                String passUser = contrasenia.getText().toString().trim();

                if (emailUser.isEmpty()) {
                    usuario.setError("Ingrese un usuario");
                    usuario.requestFocus();
                } else if (passUser.isEmpty()) {
                    contrasenia.setError("Ingrese una contraseña");
                    contrasenia.requestFocus();
                } else {
                    loginUser(emailUser, passUser);
                }
            }
        });
    }

    private void loginUser(String emailUser, String passUser) {
        mAuth.signInWithEmailAndPassword(emailUser,passUser).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    finish();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else {
                    builder = new AlertDialog.Builder(LoginActivity.this);
                    builder.setTitle("Se ha producido un error");
                    builder.setMessage("Intentalo más tarde");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Aceptar", null);
                    builder.show();
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                builder = new AlertDialog.Builder(LoginActivity.this);
                builder.setTitle("Error de autenticación");
                builder.setMessage("Usuario o contraseña incorrectos");
                builder.setCancelable(false);
                builder.setPositiveButton("Aceptar", null);
                builder.show();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            /*Toast.makeText(this, user.getDisplayName(), Toast.LENGTH_SHORT).show();*/
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
        }
    }
}
package com.gpm.ptgpm;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;


public class signin extends AppCompatActivity {
    EditText nama, email, password;
    TextView loginScreen;
    Button btnSign;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set View to register.xml
        setContentView(R.layout.activity_signin);

        nama = (EditText) findViewById(R.id.reg_fullname);
        email = (EditText) findViewById(R.id.reg_email);
        password = (EditText) findViewById(R.id.reg_password);
        btnSign = (Button) findViewById(R.id.btnRegister);
        loginScreen = (TextView) findViewById(R.id.link_to_login);

        // Listening to Login Screen link
        loginScreen.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Closing registration screen
                // Switching to Login Screen/closing register screen
                finish();
            }
        });
    }

    private void addMember() {

        final String memberName = nama.getText().toString();
        final String memberEmail = email.getText().toString();
        final String memberPassword = password.getText().toString();

        class AddMember extends AsyncTask<Void, Void, String> {
            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(signin.this, "Saving data...", "Please wait...", false, false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(signin.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String, String> params = new HashMap<>();

                params.put(Config.KEY_MEMBER_NAME, memberName);
                params.put(Config.KEY_MEMBER_EMAIL, memberEmail);
                params.put(Config.KEY_MEMBER_PASSWORD, memberPassword);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(Config.URL_ADD, params);
                return res;
            }
        }
        AddMember am = new AddMember();
        am.execute();
    }

}

package com.nguyendinhdoan.androidprochatdemo;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.quickblox.auth.session.QBSettings;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class MainActivity extends AppCompatActivity {

    static final String APP_ID = "77088";
    static final String AUTH_KEY = "8wsrhOhuDZmu4kd";
    static final String AUTH_SECRET = "A5SUqQujWAZh5HC";
    static final String ACCOUNT_KEY = "w_up-cko3qg8LYWYycBf";

    private Button btnLogin, btnSignUp;
    private TextInputEditText edtUser, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeFrameWork();

        btnLogin = findViewById(R.id.main_btnLogin);
        btnSignUp = findViewById(R.id.main_btnSignUp);

        edtUser = findViewById(R.id.main_editLogin);
        edtPassword = findViewById(R.id.main_editPassword);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignUpActivity.class));
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String password = edtPassword.getText().toString();

                QBUser qbUser = new QBUser(user, password);

                QBUsers.signIn(qbUser).performAsync(
                        new QBEntityCallback<QBUser>() {
                            @Override
                            public void onSuccess(QBUser qbUser, Bundle bundle) {
                                Toast.makeText(MainActivity.this, "Login successful", Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(QBResponseException e) {
                                Toast.makeText(MainActivity.this, "Login failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });


    }

    private void initializeFrameWork() {
        QBSettings.getInstance().init(getApplicationContext(), APP_ID, AUTH_KEY, AUTH_SECRET);
        QBSettings.getInstance().setAccountKey(ACCOUNT_KEY);
    }

}

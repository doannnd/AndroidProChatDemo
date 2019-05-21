package com.nguyendinhdoan.androidprochatdemo;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.quickblox.auth.QBAuth;
import com.quickblox.auth.session.QBSession;
import com.quickblox.core.QBEntityCallback;
import com.quickblox.core.exception.QBResponseException;
import com.quickblox.users.QBUsers;
import com.quickblox.users.model.QBUser;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    private Button btnSignUp, btnCancel;
    private TextInputEditText edtUser, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        registerSession();

        btnSignUp = findViewById(R.id.signup_btnSignUp);
        btnCancel = findViewById(R.id.signup_btnCancel);

        edtUser = findViewById(R.id.signup_editLogin);
        edtPassword = findViewById(R.id.signup_editPassword);

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUser.getText().toString();
                String password = edtPassword.getText().toString();

                QBUser qbUser = new QBUser(user, password);

                QBUsers.signUp(qbUser).performAsync(
                        new QBEntityCallback<QBUser>() {
                            @Override
                            public void onSuccess(QBUser qbUser, Bundle bundle) {
                                Toast.makeText(SignUpActivity.this, "sign up successful", Toast.LENGTH_SHORT).show();
                                finish();
                            }

                            @Override
                            public void onError(QBResponseException e) {
                                Toast.makeText(SignUpActivity.this, "sign up failed" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                );
            }
        });
    }

    private void registerSession() {
        QBAuth.createSession().performAsync(
                new QBEntityCallback<QBSession>() {
                    @Override
                    public void onSuccess(QBSession qbSession, Bundle bundle) {
                        
                    }

                    @Override
                    public void onError(QBResponseException e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }
                }
        );
    }
}

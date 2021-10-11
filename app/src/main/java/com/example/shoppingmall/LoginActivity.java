package com.example.shoppingmall;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";
    private FirebaseAuth mAuth;   //파이어베이스 인증
    private DatabaseReference mDatabaseRef;  //실시간 데이터베이스
    private EditText mEtEmail, mEtPwd;
    private Button mBtnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance(); //초기화

        findViewById(R.id.btn_register).setOnClickListener(onClickListener);
        findViewById(R.id.btn_login).setOnClickListener(onClickListener);
    }
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_register:
                    startRegisterActivity();
                    break;
                case R.id.btn_login:
                    Register();
            }
        }
    };

    private  void Register() {
        String email = ((EditText)findViewById(R.id.et_email)).getText().toString();
        String password = ((EditText)findViewById(R.id.et_pwd)).getText().toString();



        if(email.length() > 0 && password.length() > 0 ) {
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                FirebaseUser user = mAuth.getCurrentUser();
                                startToast("로그인에 성공하였습니다.");
                                startMainActivity(); //로그인 성공시 메인액티비티로 이동

                            } else {
                                if (task.getException() != null) {
                                    startToast(task.getException().toString());
                                }
                            }
                        }
                    });
            }else {
                startToast("이메일 또는 비밀번호를 입력해 주세요.");
            }

        }



    private void startToast(String msg) {   //토스트 메시지 메소드
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    private void startRegisterActivity(){   //회원가입 액티비티로 가는 메소드
        Intent intent =  new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void startMainActivity(){   //메인액티비티로 가는 메소드
        Intent intent =  new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
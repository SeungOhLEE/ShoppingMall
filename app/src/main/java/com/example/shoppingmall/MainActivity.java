package com.example.shoppingmall;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;



import com.google.firebase.auth.FirebaseAuth;



public class MainActivity extends AppCompatActivity {

    private FirebaseAuth mFirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(FirebaseAuth.getInstance().getCurrentUser() == null){ //로그인된 유저가 없으면 회원가입창으로 보내기
            startRegisterActivity();
        }

        findViewById(R.id.btn_logout).setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btn_logout:    //로그아웃 버튼 클릭시 로그아웃 버튼으로 돌아가기
                    FirebaseAuth.getInstance().signOut();
                    startLoginActivity();
                    break;
            }
        }
    };
    private void startRegisterActivity(){
        Intent intent =  new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    private void startLoginActivity(){
        Intent intent =  new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}


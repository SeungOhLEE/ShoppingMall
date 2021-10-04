package com.example.shoppingmall;

/**
 *  사용자 계정 정보 모델 클래스
 */

public class UserAccount
{
    private String idToken;   //Firebase Uid (고유 토큰정보)
    private String emailId;   //이메일 아이디
    private String password;  // 비밀번호

    //모델클래스를 이용하여 데이터를 가져올때 빈 생성자 필요
    public UserAccount() { }

    public String getIdToken() {
        return idToken;
    }

    public void setIdToken(String idToken) {
        this.idToken = idToken;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }
}

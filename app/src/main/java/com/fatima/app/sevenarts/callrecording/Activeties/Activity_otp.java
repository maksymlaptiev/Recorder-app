package com.fatima.app.sevenarts.callrecording.Activeties;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.fatima.app.sevenarts.callrecording.R;
import com.fatima.app.sevenarts.callrecording.databinding.ActivityOtpBinding;
import com.fatima.app.sevenarts.callrecording.databinding.ActivitySplashBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

public class Activity_otp extends AppCompatActivity {

    ActivityOtpBinding binding;


    String  enteredotp;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding=ActivityOtpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth=FirebaseAuth.getInstance();


        binding.otpAuthVerifyotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enteredotp = binding.otpAuthGetotp.getText().toString();
                if (enteredotp.isEmpty()){
                    Toast.makeText(Activity_otp.this, "Enter your  OTP first", Toast.LENGTH_SHORT).show();
                }
                else {
                    binding.otpAuthProgress.setVisibility(View.VISIBLE);
                    String coderecieved = getIntent().getStringExtra("otp");
                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(coderecieved,enteredotp);
                    signInwithphoneAuthCredential(credential);
                }
            }
        });

    }


    private void signInwithphoneAuthCredential(PhoneAuthCredential credential)
    {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){

                    binding.otpAuthProgress.setVisibility(View.INVISIBLE);
                    Toast.makeText(Activity_otp.this, "Login sucess", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Activity_otp.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }else {

                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException){

                        binding.otpAuthProgress.setVisibility(View.INVISIBLE);
                        Toast.makeText(Activity_otp.this, "Login Faild", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }
}
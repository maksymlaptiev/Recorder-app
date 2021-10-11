package com.fatima.app.sevenarts.callrecording.Activeties.Activety;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.fatima.app.sevenarts.callrecording.databinding.ActivityLoginNumberBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthSettings;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class Login_number extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    String codesent;
    String phonenumber;

    String phoneNum = "+16505554567";
    String testVerificationCode = "123456";

    ActivityLoginNumberBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginNumberBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        firebaseAuth = FirebaseAuth.getInstance();

        binding.loginButSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number;

                number = binding.loginEtNumber.getText().toString();
                if (number.isEmpty()) {
                    Toast.makeText(Login_number.this, " please Enter your number", Toast.LENGTH_SHORT).show();
                } else if (number.length() < 10) {
                    Toast.makeText(Login_number.this, " please Enter correct number", Toast.LENGTH_SHORT).show();

                } else {

                    binding.loginProgress.setVisibility(View.VISIBLE);
                    phonenumber =  number;

                    firebaseAuth.setLanguageCode("fr");

                    String phoneNumber = "+16505554567";
                    String smsCode = "123456";
                    FirebaseAuthSettings firebaseAuthSettings = firebaseAuth.getFirebaseAuthSettings();
//
                    firebaseAuthSettings.setAutoRetrievedSmsCodeForPhoneNumber(phoneNumber, smsCode);



                    PhoneAuthOptions options = PhoneAuthOptions.newBuilder(firebaseAuth)
                            .setPhoneNumber("+972"+phonenumber)
                            .setTimeout(60L, TimeUnit.SECONDS)
                            .setActivity(Login_number.this)
                            .setCallbacks(mCallbacks)
                            .build();

                    PhoneAuthProvider.verifyPhoneNumber(options);



                    FirebaseAuth.getInstance().signOut();

                }
            }
        });


        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                Toast.makeText(Login_number.this, phoneAuthCredential+" تم ", Toast.LENGTH_SHORT).show();


                signInWithCredential(phoneAuthCredential);


            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {

                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
Log.d("jma",e.getMessage());
                    // ...
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // ...

                }
            }


            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);

                Log.d("TAG", "onCodeSent:" + s);

                // Save verification ID and resending token so we can use them later
//                mVerificationId = verificationId;
//                mResendToken = forceResendingToken;

//                MainActivity.this.enableUserManuallyInputCode();

                Toast.makeText(Login_number.this, "OTP is sent", Toast.LENGTH_SHORT).show();

                binding.loginProgress.setVisibility(View.INVISIBLE);
                codesent = s;
                Intent i = new Intent(Login_number.this, Activity_otp.class);
                i.putExtra("otp", codesent);
                startActivity(i);

//                mVerificationId = s;
//                mResendToken = forceResendingToken;
            }

//            onCodeAutoRetrievalTimeOut

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);

                Toast.makeText(Login_number.this, s + "hi", Toast.LENGTH_SHORT).show();

            }
        };

    }

    private void signInWithCredential(PhoneAuthCredential credential) {

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(Login_number.this, "yes", Toast.LENGTH_SHORT).show();

                            FirebaseUser user = task.getResult().getUser();

                            // Sign in success, update UI with the signed-in user's information
                            // Log.d(TAG, "signInWithCredential:success");

                        } else {
                            Toast.makeText(Login_number.this, "no", Toast.LENGTH_SHORT).show();

                            // Sign in failed, display a message and update the UI
                            // Log.w(TAG, "signInWithCredential:failure", task.getException());

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid

                                Toast.makeText(Login_number.this, "no no", Toast.LENGTH_SHORT).show();


                            }
                        }

                    }

                });
    }



    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null) {

            Intent intent = new Intent(Login_number.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }
}
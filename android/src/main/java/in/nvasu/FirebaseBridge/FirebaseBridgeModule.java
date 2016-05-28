package com.ruchira.RNFirebase;

import android.widget.Toast;

import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

import com.facebook.AccessToken;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Map;

public class RNFirebaseModule extends ReactContextBaseJavaModule {

  private FirebaseAuth mAuth;
  private FirebaseAuth.AuthStateListener mAuthListener;
  
  public ToastModule(ReactApplicationContext reactContext) {
    super(reactContext);
    mAuth = FirebaseAuth.getInstance();
    mAuthListener = new FirebaseAuth.AuthStateListener() {
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if (user != null) {
            Log.d("Firebase Login","onAuthStateChanged:signed_in:"+user.getUid());
        } else {
            Log.d("Firebase Login","onAuthStateChanged:signed_out:");
        }
    }
  }

  @Override
  public String getName() {
    return "RNFirebaseModule";
  }

  @Override
  public Map<String, Object> getConstants() {
    final Map<String, Object> constants = new HashMap<>();
    return constants;
  }

  @Override
  public void onStart() {
    super.onStart();
    mAuth.addAuthStateListener(mAuthListener);
  }

  @Override
  public void onStop() {
    super.onStop();
    mAuth.addAuthStateListener(mAuthListener);
  }

  @ReactMethod
  public void handleFacebookAccessToken(AccessToken token) {
    Log.d("Firebase Login", "handleFacebookAccessToken:" + token);

    AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
    mAuth.signInWithCredential(credential)
      .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
        @Override
        public void onComplete(@NonNull Task<AuthResult> task) {
          Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful());

          // If sign in fails, display a message to the user. If sign in succeeds
          // the auth state listener will be notified and logic to handle the
          // signed in user can be handled in the listener.
          if (!task.isSuccessful()) {
            Log.w(TAG, "signInWithCredential", task.getException());
            Toast.makeText(FacebookLoginActivity.this, "Authentication failed.",
            Toast.LENGTH_SHORT).show();
          }
        }
    });
  }
}

package com.sourcey.materiallogindemo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
//import android.widget.AdapterView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;
import java.util.List;
//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener {
    public EditText emailId, passwd,repass, Name,department,phoneno,semester;
    private Spinner spinner1, spinner2;
    Button btnSignUp;
    TextView signIn;
    FirebaseAuth firebaseAuth;
//    String userID;
//    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        firebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.input_email);
        passwd = findViewById(R.id.input_password);
        Name = findViewById(R.id.input_name);

        phoneno = findViewById(R.id.input_mobile);
        repass = findViewById(R.id.input_reEnterPassword);
        btnSignUp = findViewById(R.id.btn_signup);
        signIn = findViewById(R.id.link_login);
        Spinner spinner = (Spinner) findViewById(R.id.input_sem);
        List<String> sem = new ArrayList<String>();
        sem.add("1");
        sem.add("2");
        sem.add("3");
        sem.add("4");
        sem.add("5");
        sem.add("6");
        Spinner spinner2 = (Spinner) findViewById(R.id.input_dept);
        List<String> dept = new ArrayList<String>();
        dept.add("CE");
        dept.add("ME");
        dept.add("CIVIL");
        dept.add("EE");
        dept.add("IT");
        ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, sem);
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dept);
        spinner.setAdapter(dataAdapter1);
        spinner.setAdapter(dataAdapter2);
        btnSignUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                String emailID = emailId.getText().toString();
                String paswd = passwd.getText().toString();
                String repas = repass.getText().toString();
                final String name = Name.getText().toString();
                final String dept = department.getText().toString();
                final String phone = phoneno.getText().toString();
                if (emailID.isEmpty()) {
                    emailId.setError("Provide your Email first!");
                    emailId.requestFocus();
                } else if (paswd.isEmpty()) {
                    passwd.setError("Set your password");
                    passwd.requestFocus();
                } else if (emailID.isEmpty() && paswd.isEmpty()) {
                    Toast.makeText(SignupActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
                } else if (!(emailID.isEmpty() && paswd.isEmpty())&& (paswd.equals(repas)) ) {
                    firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(SignupActivity.this, new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {

                            if (!task.isSuccessful()) {
                                if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                    Toast.makeText(SignupActivity.this.getApplicationContext(),
                                            "You are already registered " + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignupActivity.this.getApplicationContext(),
                                            "SignUp unsuccessful: " + task.getException().getMessage(),
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                if(name.isEmpty()){
                                    Name.setError("Name required");
                                    Name.requestFocus();
                                    return;
                                }
                                if(dept.isEmpty()){
                                    department.setError("Name required");
                                    department.requestFocus();
                                    return;
                                }
                                if(phone.isEmpty()){
                                    phoneno.setError("Name required");
                                    phoneno.requestFocus();
                                    return;
                                }

                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(name).build();
                                user.updateProfile(profileUpdates);
//                                mDatabase = FirebaseDatabase.getInstance().getReference();
//                                DatabaseReference userNameRef =  mDatabase.child("Phone");
//                                userNameRef.setValue(phone);
//                                userNameRef =  mDatabase.child("Department");
//                                userNameRef.setValue(dept);


                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(SignupActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent I = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(I);
            }
        });
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id){
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
//import android.util.Log;
//import android.view.View;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import butterknife.BindView;
//import butterknife.ButterKnife;
//import com.google.android.gms.tasks.OnCompleteListener;
//import com.google.android.gms.tasks.Task;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;
//
//public class SignupActivity extends AppCompatActivity {
//    private static final String TAG = "SignupActivity";
//
//
//
//    @Override
////    protected void onCreate(Bundle savedInstanceState) {
////        super.onCreate(savedInstanceState);
////        setContentView(R.layout.activity_signup);
////        firebaseAuth = FirebaseAuth.getInstance();
//////        String Name = _nameText.getText().toString();
//////        String department = _deptText.getText().toString();
//////        String emailId = _emailText.getText().toString();
//////        String mobile = _mobileText.getText().toString();
//////        String passwd = _passwordText.getText().toString();
//////        String reEnterPassword = _reEnterPasswordText.getText().toString();
////       //signIn = findViewById(R.id.TVSignIn);
////        _signupButton.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                String emailID = _emailText.getText().toString();
////                String paswd = _passwordText.getText().toString();
//////                String name = Name.getText().toString();
//////                String mob = mobile.getText().toString();
//////                String dept = department.getText().toString();
//////                String reEnterPsswd = reEnterPassword.getText().toString();
////                if (emailID.isEmpty()) {
////                    _emailText.setError("Provide your Email first!");
////                    _emailText.requestFocus();
////                } else if (paswd.isEmpty()) {
////                    _passwordText.setError("Set your password");
////                    _passwordText.requestFocus();
////                } else if (emailID.isEmpty() && paswd.isEmpty()) {
////                    Toast.makeText(SignupActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
////                } else if (!(emailID.isEmpty() && paswd.isEmpty())) {
////                    firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(SignupActivity.this, new OnCompleteListener() {
////                        @Override
////                        public void onComplete(@NonNull Task task) {
////
////                            if (!task.isSuccessful()) {
////                                Toast.makeText(SignupActivity.this.getApplicationContext(),
////                                        "SignUp unsuccessful: " + task.getException().getMessage(),
////                                        Toast.LENGTH_SHORT).show();
////                            } else {
////                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
////                            }
////                        }
////                    });
////                } else {
////                    Toast.makeText(SignupActivity.this, "Error", Toast.LENGTH_SHORT).show();
////                }
////            }
////        });
////        _loginLink.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                Intent I = new Intent(SignupActivity.this, LoginActivity.class);
////                startActivity(I);
////            }
////        });
////    }
////}
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_signup);
//        //ButterKnife.bind(this);
//
//        _signupButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String emailID = _emailText.getText().toString();
//                String paswd = _passwordText.getText().toString();
////                String name = Name.getText().toString();
////                String mob = mobile.getText().toString();
////                String dept = department.getText().toString();
////                String reEnterPsswd = reEnterPassword.getText().toString();
//                if (emailID.isEmpty()) {
//                    _emailText.setError("Provide your Email first!");
//                    _emailText.requestFocus();
//                } else if (paswd.isEmpty()) {
//                    _passwordText.setError("Set your password");
//                    _passwordText.requestFocus();
//                } else if (emailID.isEmpty() && paswd.isEmpty()) {
//                    Toast.makeText(SignupActivity.this, "Fields Empty!", Toast.LENGTH_SHORT).show();
//                } else if (!(emailID.isEmpty() && paswd.isEmpty())) {
//                    firebaseAuth.createUserWithEmailAndPassword(emailID, paswd).addOnCompleteListener(SignupActivity.this, new OnCompleteListener() {
//                        @Override
//                        public void onComplete(@NonNull Task task) {
//
//                            if (!task.isSuccessful()) {
//                                Toast.makeText(SignupActivity.this.getApplicationContext(),
//                                        "SignUp unsuccessful: " + task.getException().getMessage(),
//                                        Toast.LENGTH_SHORT).show();
//                            } else {
//                                startActivity(new Intent(SignupActivity.this, LoginActivity.class));
//                            }
//                        }
//                    });
//                } else {
//                    Toast.makeText(SignupActivity.this, "Error", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//
//        _loginLink.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Finish the registration screen and return to the Login activity
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//                finish();
//                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
//            }
//        });
//    }
//}
//

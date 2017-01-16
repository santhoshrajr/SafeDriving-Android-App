package com.imperium.autobeacon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;




import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.lang.String;
import java.util.regex.Matcher;
import java.util.Map;
import java.util.regex.Pattern;


public class RegisterActivity extends Activity  {


    //@Password(order = 3)

    // set the rule for password field that minimum length is 6 and maximum lenght is 15.
    //@TextRule(order = 4, minLength = 6, maxLength = 15, message = "Enter at least 6 characters.")
   // @ConfirmPassword(order = 5)
    //EditText confirmPassword;
    EditText email,password,lname,fname,driverid,rptpass,newpass,res_email,code;
    Button login,register2,cont_code,cancel1, cancel,cont;
    String emailtxt,passwordtxt,lnametxt,fnametxt,rptpasstxt,driveridtxt,email_res_txt,code_txt,npass_txt,email_trim;
    HashMap<String, String> params;
    Dialog reset2;
    ServerRequest sr;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        driverid=(EditText)findViewById(R.id.etDriverID);
        rptpass=(EditText)findViewById(R.id.etRePwdAdult);
        fname=(EditText)findViewById(R.id.etFirstName);
        lname=(EditText)findViewById(R.id.etLastName);
        email = (EditText)findViewById(R.id.etEMail);
        password = (EditText)findViewById(R.id.etPwdAdult);
        register2 = (Button)findViewById(R.id.bReg_2);

        /*validator = new Validator(this);
        // Call the validation listener method.
        validator.setValidationListener(this);*/
/*        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent regactivity = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(regactivity);
                finish();
            }
        });*/



        register2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                 emailtxt = email.getText().toString();
                passwordtxt = password.getText().toString();
                lnametxt = lname.getText().toString();
                fnametxt = fname.getText().toString();
                driveridtxt = driverid.getText().toString();
                rptpasstxt = rptpass.getText().toString();

                //InputValidatorHelper inputValidatorHelper = new InputValidatorHelper();
               // StringBuilder errMsg = new StringBuilder("Unable to save. Please fix the following errors and try again.\n");
//Validate and Save
              boolean allowSave = true;
                if (!isValidEmail(emailtxt) ) {
                    email.setError("Invalid Email");
                    allowSave = false;
                }
                if (!isValidPassword(passwordtxt) ) {
                    password.setError("Invalid Password,must have one upper & lower cases,one digit and one symbol");
                    allowSave = false;
                }
                if( fnametxt.length() == 0 ) {
                    fname.setError("First name is required!");
                    allowSave = false;
                }
                if( lnametxt.length() == 0 ) {
                    lname.setError("Last name is required!");
                    allowSave = false;
                }

              /*  if (inputValidatorHelper.isNullOrEmpty(fnametxt)) {
                    errMsg.append("- First name should not be empty.\n");
                    allowSave = false;
                }*/
                if(!rptpasstxt.equals(passwordtxt)) {
                    Toast.makeText(getApplicationContext(),"Passwords do not match",Toast.LENGTH_LONG).show();
                    allowSave = false;
                }
                if(allowSave) {
                    //Proceed with your save logic here



               /* //final String email = emailEditText.getText().toString();
                if (!isValidEmail(emailtxt)) {
                    email.setError("Invalid Email");
                }
                if (!isValidPassword(passwordtxt)) {
                    password.setError("Invalid Password");
                }
*/
                    //final String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
               /* if (Pattern.matches("[a-zA-Z0-9._-]+@[a-z]+\\\\.+[a-z]+",email_trim))
                {
                    Toast.makeText(getApplicationContext(),"valid email address",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Invalid email address", Toast.LENGTH_SHORT).show();
                }*/



                    params = new HashMap<String, String>();
                    params.put("email", emailtxt);
                    params.put("password", passwordtxt);
                    params.put("lastName", lnametxt);
                    params.put("firstName", fnametxt);

                    Intent intent = new Intent(RegisterActivity.this,Register2ndActivity.class);
                    intent.putExtra("email", emailtxt);
                    intent.putExtra("password", passwordtxt);
                    intent.putExtra("lastName", lnametxt);
                    intent.putExtra("firstName", fnametxt);

                    startActivity(intent);
                }

               /* params.put("rptpass",rptpasstxt);
                params.put("driverid",driveridtxt);*/
               /* sr = new ServerRequest();
                JSONObject json = sr.getJSON("http://autobeacon.herokuapp.com/api/register",params);
                //JSONObject json = sr.getJSON("http://192.168.56.1:8080/register",params);
                if(json != null) {
                    try {
                        String jsonstr = json.getString("response");
                        Toast.makeText(getApplicationContext(),jsonstr,Toast.LENGTH_LONG).show();
                        Log.d("Hello", jsonstr);
                        reset2 = new Dialog(RegisterActivity.this);
                        reset2.setTitle("Reset Password");
                        reset2.setContentView(R.layout.reset_pass_init);
                        cont = (Button) reset2.findViewById(R.id.resbtn);
                        cancel = (Button) reset2.findViewById(R.id.cancelbtn);
                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                reset2.dismiss();
                            }
                        });
                        res_email = (EditText) reset2.findViewById(R.id.email);
                        cont.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                email_res_txt = res_email.getText().toString();

                                params = new HashMap<String, String>();
                                params.put("email", email_res_txt);
                                //ServerRequest sr = new ServerRequest();
                                //  JSONObject json = sr.getJSON("http://192.168.56.1:8080/api/resetpass", params);
                                JSONObject json = sr.getJSON("http://10.0.2.2:8080/api/resetpass", params);

                                if (json != null) {
                                    try {
                                        String jsonstr = json.getString("response");
                                        if (json.getBoolean("res")) {
                                            Log.e("JSON", jsonstr);
                                            Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();
                                            reset2.setContentView(R.layout.reset_pass_code);
                                            cont_code = (Button) reset2.findViewById(R.id.conbtn);
                                            code = (EditText) reset2.findViewById(R.id.code);
                                            //newpass = (EditText) reset2.findViewById(R.id.npass);
                                            cancel1 = (Button) reset2.findViewById(R.id.cancel);
                                            cancel1.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    reset2.dismiss();
                                                }
                                            });
                                            cont_code.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    code_txt = code.getText().toString();
                                                    //npass_txt = newpass.getText().toString();
                                                    Log.e("Code", code_txt);
                                                    //Log.e("New pass", npass_txt);
                                                    params = new HashMap<String, String>();
                                                    params.put("email", email_res_txt);
                                                    params.put("code", code_txt);
                                                    //params.put("newpass", npass_txt);
                                                    ServerRequest sr = new ServerRequest();
                                                    JSONObject json = sr.getJSON("http://10.0.2.2:8080/api/resetpass/chg", params);
                                                    //   JSONObject json = sr.getJSON("http://192.168.56.1:8080/api/resetpass/chg", params);

                                                    if (json != null) {
                                                        try {

                                                            String jsonstr = json.getString("response");
                                                            if (json.getBoolean("res")) {
                                                                reset2.dismiss();
                                                                Intent myintent=new Intent(RegisterActivity.this,Register2ndActivity.class);
                                                                startActivity(myintent);
                                                                Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();

                                                            } else {
                                                                Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();

                                                            }
                                                        } catch (JSONException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }

                                                }
                                            });
                                        } else {
                                            Toast.makeText(getApplication(), jsonstr, Toast.LENGTH_LONG).show();

                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                            }
                        });


                    }

                    catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                reset2.show();*/
            }



        });


    }

   // @Override
   /* public void onValidationSucceeded() {
        Toast.makeText(this, "Yay! we got it right!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onValidationFailed(View failedView, Rule<?> failedRule) {
        final String failureMessage = failedRule.getFailureMessage();
        if (failedView instanceof Button) {
            Button failed = (Button) failedView;
            failed.requestFocus();
            failed.setError(failureMessage);
        } else {
            Toast.makeText(getApplicationContext(), failureMessage, Toast.LENGTH_SHORT).show();
        }

    }*/
    private boolean isValidEmail(String emailtxt) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(emailtxt);
        return matcher.matches();
    }
    private boolean isValidPassword(String passwordtxt) {
        String PASSWORD_PATTERN="((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*[@#$%!]).{6,20})";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        Matcher matcher = pattern.matcher(passwordtxt);
        return matcher.matches();
    }
}

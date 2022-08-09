package com.edu.fpoly.quan_cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {
    EditText edEmail, edPassword;
    String strEmail, strPassword;
    String url = "https://luongngochai.000webhostapp.com/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edEmail = findViewById(R.id.edEmail);
        edPassword = findViewById(R.id.edPassWord);
    }

    public void Login(View view) {

        if(edEmail.getText().toString().equals("")){
            edEmail.setError("Không được để trống email!");
        }
        else if(edPassword.getText().toString().equals("")){
           edPassword.setError("Không được để trống mật khẩu!");
        }
        else{


            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setMessage("Đang đăng nhập...");

            progressDialog.show();

            strEmail = edEmail.getText().toString().trim();
            strPassword = edPassword.getText().toString().trim();


            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if(response.equalsIgnoreCase("Đăng nhập thành công!")){

                        edEmail.setText("");
                        edPassword.setText("");
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(LoginActivity.this, response, Toast.LENGTH_SHORT).show();
                    }

                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(LoginActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }

            ){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("EmailND",strEmail);
                    params.put("MatKhau",strPassword);
                    return params;

                }
            };

            RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
            requestQueue.add(request);




        }
    }

    public void moveToRegister(View view) {
        startActivity(new Intent(getApplicationContext(), RegisterActivity.class));
        finish();
    }
}
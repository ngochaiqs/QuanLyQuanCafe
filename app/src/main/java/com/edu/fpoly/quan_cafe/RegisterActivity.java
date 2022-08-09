package com.edu.fpoly.quan_cafe;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class RegisterActivity extends AppCompatActivity {
    EditText edtName, edtEmail, edtPassword;
    String str_name, str_email, str_password;
    String url = "https://luongngochai.000webhostapp.com/register.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtName = findViewById(R.id.edtName);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassWord);
    }

    public void moveToLogin(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }

    public void Register(View view) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Đang đăng ký...");

        if(edtName.getText().toString().equals("")){
            edtName.setError("Không được để trống tên!");
        }
        else if(edtEmail.getText().toString().equals("")){
            edtEmail.setError("Không được để trống email!");
        } else if(edtPassword.getText().toString().equals("")){
            edtPassword.setError("Không được để trống mật khẩu!");
        }
        else {

            progressDialog.show();
            str_name = edtName.getText().toString().trim();
            str_email = edtEmail.getText().toString().trim();
            str_password = edtPassword.getText().toString().trim();

            StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();
                    edtName.setText("");
                    edtEmail.setText("");
                    edtPassword.setText("");
                    Toast.makeText(RegisterActivity.this, response, Toast.LENGTH_SHORT).show();
 
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(RegisterActivity.this, error.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            }
            ){
                @Override
                protected java.util.Map<String, String> getParams() {
                    java.util.Map<String, String> params = new java.util.HashMap<>();
                    params.put("TenND", str_name);
                    params.put("EmailND", str_email);
                    params.put("MatKhau", str_password);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(RegisterActivity.this);
            requestQueue.add(request);
            }
        }

    }
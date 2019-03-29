package com.example.pc.sample;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;


public class LoginActivity extends Activity {
    private EditText usernameET;
    private EditText passwordET;
    private String username;
    private String password;
    //登录按钮
    private Button btn_login;
    private LoginActivity context = LoginActivity.this;
    // 创建登录等待框
    private ProgressDialog dialog;
    // 创建用户名或密码错误提示框
    private AlertDialog.Builder NOPMAlertDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.layout_login);
        dialogLogin();
        nameOrPassMissDialog();
        usernameET = (EditText) findViewById(R.id.usernameET);
        passwordET = (EditText) findViewById(R.id.passwordET);
        btn_login = (Button) findViewById(R.id.btn_login);


        // 登录
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  显示提示弹窗
                dialog.show();
                username = usernameET.getText().toString().trim();
                password = passwordET.getText().toString().trim();
                Log.e("nameAndPass",username+"---"+password);
                if("root".equals(username)&&"123456".equals(password)){
                    Intent MainIntent = new Intent(context, WebviewActivity.class);
                    startActivity(MainIntent);
                    dialog.dismiss();
                    finish();
                }else{
                    dialog.dismiss();
                    NOPMAlertDialog.show();
                }

            }
        });
    }




    /**
     * 登录进程提示框
     */
    private void dialogLogin() {
        // 提示框
        dialog = new ProgressDialog(context);
        dialog.setTitle("提示");
        dialog.setMessage("正在登陆，请稍后...");
        dialog.setCancelable(true);
    }


    /**
     * 用户名密码错误提示框
     */
    private void nameOrPassMissDialog() {
        dialog.dismiss();
        NOPMAlertDialog = new AlertDialog.Builder(context);
        NOPMAlertDialog.setTitle("提示");
        NOPMAlertDialog.setMessage("用户名或密码错误");
        NOPMAlertDialog.setCancelable(false);

        //  确认按钮
        NOPMAlertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
    }
}

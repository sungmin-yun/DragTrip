package sungmincompany.dragtrip2;

import android.app.AlertDialog;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.regex.Pattern;

import static sungmincompany.dragtrip2.R.id.nameText;

public class RegisterActivity extends AppCompatActivity {
    private ArrayAdapter adapter1, adapter2, adapter3;
    private Spinner spinner1, spinner2, spinner3;
    private String userName;
    private String userID;
    private String userPassword;
    private String userGender;
    private String userEmail;
    private int userYear;
    private int userMonth;
    private int userDay;
    private AlertDialog dialog;
    private boolean validate = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        spinner1 = (Spinner) findViewById(R.id.yearspinner);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.year, android.R.layout.simple_dropdown_item_1line);
        spinner1.setAdapter(adapter1);
        adapter1.setDropDownViewResource(R.layout.spinner);

        spinner2 = (Spinner) findViewById(R.id.monthspinner);
        adapter2 = ArrayAdapter.createFromResource(this, R.array.month, android.R.layout.simple_dropdown_item_1line);
        adapter2.setDropDownViewResource(R.layout.spinner);
        spinner2.setAdapter(adapter2);


        spinner3 = (Spinner) findViewById(R.id.dayspinner);
        adapter3 = ArrayAdapter.createFromResource(this, R.array.day, android.R.layout.simple_dropdown_item_1line);
        spinner3.setAdapter(adapter3);
        adapter3.setDropDownViewResource(R.layout.spinner);

        final EditText idText = (EditText) findViewById(R.id.idText);
        final EditText nameText = (EditText) findViewById(R.id.nameText);
        final EditText passwordText = (EditText) findViewById(R.id.passwordText);
        final EditText emailText = (EditText) findViewById(R.id.emailText);
        final EditText passwordText2 = (EditText) findViewById(R.id.passwordText2);

        RadioGroup gendergroup = (RadioGroup) findViewById(R.id.genderGroup);
        int genderGroupID = gendergroup.getCheckedRadioButtonId();
        userGender = ((RadioButton) findViewById(genderGroupID)).getText().toString();

        gendergroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                RadioButton genderButton = (RadioButton) findViewById(checkedId);
                userGender = genderButton.getText().toString();

            }
        });




        final Button validateButton = (Button) findViewById(R.id.validateButton);
        validateButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                if (validate) {
                    return;
                }
                if (userID.equals("")) {
                    Toast toast = Toast.makeText(RegisterActivity.this,"아이디는 빈 칸일 수 없습니다.",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast toast = Toast.makeText(RegisterActivity.this,"사용 가능한 아이디 입니다",Toast.LENGTH_SHORT);
                                toast.show();
                                idText.setEnabled(false);
                                validate = true;
                                validateButton.setText("확인 완료");

                            }
                            else {
                                Toast toast = Toast.makeText(RegisterActivity.this,"사용할 수 없는 아이디 입니다",Toast.LENGTH_SHORT);
                                toast.show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                };
                ValidateRequest validateRequest = new ValidateRequest(userID, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(validateRequest);

            }

        });

        Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String userID = idText.getText().toString();
                String userPassword = passwordText.getText().toString();
                String userEmail = emailText.getText().toString();
                String userName = nameText.getText().toString();
                String passwordTest = passwordText2.getText().toString();

                int userYear = Integer.parseInt(spinner1.getSelectedItem().toString());
                int userMonth = Integer.parseInt(spinner2.getSelectedItem().toString());
                int userDay = Integer.parseInt(spinner3.getSelectedItem().toString());

                if (!validate) {
                    Toast toast = Toast.makeText(RegisterActivity.this,"먼저 중복 체크를 해주세요",Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }
                if (userID.equals("") || userPassword.equals("") || userEmail.equals("") || userGender.equals("")||userName.equals("")) {
                    Toast toast = Toast.makeText(RegisterActivity.this,"빈칸 없이 입력해주세요",Toast.LENGTH_SHORT);
                    toast.show();
                    return;

                }

                if(!Pattern.matches("^[A-Za-z0-9_@./#&+-]*.{8,20}$",userPassword)){
                    Toast toast = Toast.makeText(RegisterActivity.this,"비밀번호가 형식에 맞지 않습니다. 비밀번호는 영어 대소문자, 특수문자만 가능합니다",Toast.LENGTH_SHORT);
                    toast.show();
                    return;

                }
                if(!userPassword.equals(passwordTest)){
                    Toast toast = Toast.makeText(RegisterActivity.this,"비밀번호가 일치하지 않습니다",Toast.LENGTH_SHORT);
                    toast.show();
                    return;

                }
                if(!Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                    Toast toast = Toast.makeText(RegisterActivity.this, "이메일 형식이 아닙니다", Toast.LENGTH_SHORT);
                    toast.show();
                    return;
                }

                Response.Listener<String> responseListener = new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Toast toast = Toast.makeText(RegisterActivity.this,"회원등록에 성공했습니다",Toast.LENGTH_SHORT);
                                toast.show();
                                finish();


                            } else {
                                Toast toast = Toast.makeText(RegisterActivity.this,"회원등록에 실패했습니다",Toast.LENGTH_SHORT);
                                toast.show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                };
                RegisterRequest registerRequest = new RegisterRequest(userName, userID, userPassword, userEmail, userGender, userYear, userMonth, userDay, responseListener);
                RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
                queue.add(registerRequest);
            }

        });
    }

}




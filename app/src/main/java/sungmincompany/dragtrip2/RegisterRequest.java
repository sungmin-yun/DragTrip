package sungmincompany.dragtrip2;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yunseongmin on 2017. 8. 8..
 */

public class RegisterRequest extends StringRequest {
    final static private String URL = "http://ymss98.cafe24.com/UserRegister.php";
    private Map<String, String> parameters;

    public RegisterRequest(String userName, String userID, String userPassword, String userEmail,  String userGender,int userYear, int userMonth, int userDay, Response.Listener<String> listener){
        super(Method.POST, URL, listener, null);
        parameters = new HashMap<>();
        parameters.put("userName", userName);
        parameters.put("userID", userID);
        parameters.put("userPassword", userPassword);
        parameters.put("userGender", userGender);
        parameters.put("userEmail", userEmail);
        parameters.put("userYear", userYear+"");
        parameters.put("userMonth", userMonth+"");
        parameters.put("userDay", userDay+"");

    }



    @Override
    public Map<String, String> getParams(){
        return parameters;
    }
}

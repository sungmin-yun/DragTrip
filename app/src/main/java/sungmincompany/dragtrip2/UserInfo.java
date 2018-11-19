package sungmincompany.dragtrip2;

import android.content.SharedPreferences;

/**
 * Created by yunseongmin on 2017. 8. 11..
 */

public class UserInfo {

    private SharedPreferences UserInform;

    public UserInfo(String id, String pd) {

        SharedPreferences.Editor editor = UserInform.edit();
        editor.putBoolean("LoginState",true);
        editor.putString("Id", id);
        editor.putString("Password",pd);
        editor.apply();
    }

}

package sungmincompany.dragtrip2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    boolean login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final TextView startButton = (TextView) findViewById(R.id.startButton);

        SharedPreferences pref = getSharedPreferences("UserInform",MODE_PRIVATE);
        final SharedPreferences.Editor editor = pref.edit();

        if(login=pref.getBoolean("LoginState", false)){
            startButton.setText("로그아웃");
        }


        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    MainActivity.this.startActivity(loginIntent);
                if(login){
                    editor.clear();
                    editor.commit();
                    finish();
                }
            }
        });

        final TextView noticeButton = (TextView) findViewById(R.id.noticeButton);
        noticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent noticeIntent = new Intent(MainActivity.this, NoticeActivity.class);
                MainActivity.this.startActivity(noticeIntent);
            }
        });

        final TextView mainButton = (TextView) findViewById(R.id.mainButton);
        final TextView pathButton = (TextView) findViewById(R.id.pathButton);
        final TextView socialButton = (TextView) findViewById(R.id.socialButton);
        final LinearLayout mainFrame = (LinearLayout) findViewById(R.id.mainFrame);
        final LinearLayout mainLinear = (LinearLayout)  findViewById(R.id.mainLinear);
        final LinearLayout pathLinear = (LinearLayout)  findViewById(R.id.pathLinear);
        final LinearLayout socialLinear = (LinearLayout)  findViewById(R.id.socialLinear);


        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFrame.setVisibility(View.GONE);
                mainLinear.setBackgroundColor(Color.parseColor("#FF8000"));
                pathLinear.setBackgroundColor(Color.parseColor("#FFFFFF"));
                socialLinear.setBackgroundColor(Color.parseColor("#FFFFFF"));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new MainFragment());
                fragmentTransaction.commit();


            }
        });

        pathButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFrame.setVisibility(View.GONE);
                mainLinear.setBackgroundColor(Color.parseColor("#FFFFFF"));
                pathLinear.setBackgroundColor(Color.parseColor("#FF8000"));
                socialLinear.setBackgroundColor(Color.parseColor("#FFFFFF"));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new PathFragment());
                fragmentTransaction.commit();


            }
        });

        socialButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainFrame.setVisibility(View.GONE);
                mainLinear.setBackgroundColor(Color.parseColor("#FFFFFF"));
                pathLinear.setBackgroundColor(Color.parseColor("#FFFFFF"));
                socialLinear.setBackgroundColor(Color.parseColor("#FF8000"));
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.fragment, new SocialFragment());
                fragmentTransaction.commit();


            }
        });
    }
}

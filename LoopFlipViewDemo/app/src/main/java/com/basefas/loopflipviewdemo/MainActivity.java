package com.basefas.loopflipviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

import com.basefas.loopflipview.LoopFlipView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private LoopFlipView loopFlipView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loopFlipView = (LoopFlipView) findViewById(R.id.loopflipview);

        List<String> info = new ArrayList<>();
        info.add("抵制不良游戏，拒绝盗版游戏。");
        info.add("注意自我保护，谨防受骗上当。");
        info.add("适度游戏益脑，沉迷游戏伤身。");
        info.add("合理安排时间，享受健康生活。");

        loopFlipView.setTextColor(R.color.colorAccent)
                .setInfo(info)
                .setTextSize(18)
                .setInterval(3000)
                .setSingleLine(true)
                .setGravity(Gravity.START | Gravity.CENTER_VERTICAL)
                .setDefaultAnimation(true)
                .setOnItemClickListener(new LoopFlipView.OnItemClickListener() {
                    @Override
                    public void onItemClick(TextView textView, int position) {
                        Toast.makeText(getApplicationContext(), textView.getText() + "", Toast.LENGTH_SHORT).show();
                    }
                })
                .start();
    }
}

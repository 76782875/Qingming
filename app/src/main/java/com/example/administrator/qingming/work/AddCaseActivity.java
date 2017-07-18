package com.example.administrator.qingming.work;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.MainApi;

/**
 * Created by Administrator on 2017/5/10 0010.
 */

public class AddCaseActivity extends Activity {
    private LinearLayout inputcasename,inputcase;
    private Button nextbtn;
    private TextView casename,casecontent;
    private ImageView backbtn;
    boolean OME = false;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);

        initView();
    }

    private void initView(){
        inputcasename = (LinearLayout) findViewById(R.id.input_case_name);
        inputcase = (LinearLayout) findViewById(R.id.input_case);
        casename = (TextView) findViewById(R.id.case_name);
        casecontent = (TextView) findViewById(R.id.case_content);
        nextbtn = (Button) findViewById(R.id.next_btn);
        backbtn = (ImageView) findViewById(R.id.back_btn);

        inputcasename.setOnClickListener(onClickListener);
        inputcase.setOnClickListener(onClickListener);
        backbtn.setOnClickListener(onClickListener);
        nextbtn.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()){
                case R.id.input_case_name:
                    showworkDialog();
                    break;
                case R.id.input_case:
                    showDialog();
                    break;
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.next_btn:
                    if(casename.getText().equals("选择案件的类型")){
                        Toast.makeText(AddCaseActivity.this,"请选择案件的类型",Toast.LENGTH_SHORT).show();
                    }else {
                        intent = new Intent(AddCaseActivity.this,CaseRegisterActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt("index",101);
                        bundle.putInt("ajlx",ajlx);
                        bundle.putInt("ajfl",ajfl);
                        bundle.putString("word",""+wordsize);
                        bundle.putString("num",""+num);
                        bundle.putString("name",""+name);
                        intent.putExtras(bundle);
                        Log.e("ajlx====》",""+ajlx);
                        Log.e("ajfl=======》",""+ajfl);
                        startActivity(intent);
                    }
                    break;
            }
        }
    };

    String wordsize;
    String num;
    String name;
    int ajfl;
    int ajlx;
    String[] clx={"民事案件","刑事案件","行政案件","非诉讼法律事务","法律顾问","法律援助","执行案件","中保案件","仲裁案件","破产案件","咨询代写文书"};
    private void showworkDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCaseActivity.this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("请选择案件类型");
        builder.setItems(clx, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                OME = true;
                casename.setText(clx[which]);
                casename.setTextColor(getResources().getColor(R.color.black));
                switch (which){
                    case 0:
                        ajlx = 1;
                        name = "民事案件";
                        wordsize = "(民)";
                        num ="第 0071 号";
                        cfl= getResources().getStringArray(R.array.one);
                        break;
                    case 1:
                        ajlx = 2;
                        name = "刑事案件";
                        wordsize = "(刑)";
                        num ="第 0011 号";
                        cfl= getResources().getStringArray(R.array.two);
                        break;
                    case 2:
                        ajlx = 3;
                        name = "行政案件";
                        wordsize = "(行)";
                        num ="第 0007 号";
                        cfl= getResources().getStringArray(R.array.three);
                        break;
                    case 3:
                        ajlx = 4;
                        name = "非诉讼法律事务";
                        wordsize = "(非)";
                        num ="第 0003 号";
                        cfl= getResources().getStringArray(R.array.four);
                        break;
                    case 4:
                        ajlx = 5;
                        name = "法律顾问";
                        wordsize = "(顾)";
                        num ="第 0004 号";
                        cfl= getResources().getStringArray(R.array.five);
                        break;
                    case 5:
                        ajlx = 6;
                        name = "法律援助";
                        wordsize = "(援)";
                        num ="第 0001 号";
                        cfl= getResources().getStringArray(R.array.six);
                        break;
                    case 6:
                        ajlx = 7;
                        name = "执行案件";
                        wordsize = "(执)";
                        num ="第 0002 号";
                        cfl= getResources().getStringArray(R.array.six);
                        break;
                    case 7:
                        ajlx = 8;
                        name = "中保案件";
                        wordsize = "(保)";
                        num ="第 0001 号";
                        cfl= getResources().getStringArray(R.array.six);
                        break;
                    case 8:
                        ajlx = 9;
                        name = "仲裁案件";
                        wordsize = "(仲)";
                        num ="第 0001 号";
                        cfl= getResources().getStringArray(R.array.seven);
                        break;
                    case 9:
                        ajlx = 10;
                        name = "破产案件";
                        wordsize = "(破)";
                        num ="第 0001 号";
                        cfl= getResources().getStringArray(R.array.eight);
                        break;
                    case 10:
                        ajlx = 11;
                        name = "咨询代写文书";
                        cfl= getResources().getStringArray(R.array.nine);
                        break;
                }

                casecontent.setText(cfl[0]);
                casecontent.setTextColor(getResources().getColor(R.color.black));
            }
        });
        builder.show();
    }

    String[] cfl = {};
    private void showDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(AddCaseActivity.this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("请选择案件分类");
        builder.setItems(cfl, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                casecontent.setText(cfl[i]);
                ajfl = i+1;
                Log.e("ajfl",""+ajfl);
                Log.e("casecontent",""+casecontent.getText().toString());
                casecontent.setTextColor(getResources().getColor(R.color.black));
            }
        });
        builder.show();
    }

    private void post(){
//        MainApi.getInstance(this).getqiandaoApi();
    }
}

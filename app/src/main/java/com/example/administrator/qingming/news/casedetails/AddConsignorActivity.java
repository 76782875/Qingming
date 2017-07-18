package com.example.administrator.qingming.news.casedetails;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.qingming.R;
import com.example.administrator.qingming.work.CaseRegisterActivity;
import com.lljjcoder.citypickerview.widget.CityPicker;

/**
 * Created by Administrator on 2017/5/23 0023.
 */

public class AddConsignorActivity extends Activity {
    private EditText consignor,party,business_contact,duty,principal,regional_influence,consignorph,
    consignor_gd,consignor_em,shenfen,site,remark;
    private TextView company,sheng,city,industry,scale,nature;
    private Button submit;
    private ImageView backbtn;
    private CityPicker cityPicker ;
    private LinearLayout choosecity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_consignor);

        initView();
    }

    private void initView() {
        consignor = (EditText) findViewById(R.id.consignor);
        party = (EditText) findViewById(R.id.party);
        business_contact = (EditText) findViewById(R.id.business_contact);
        duty = (EditText) findViewById(R.id.duty);
        principal = (EditText) findViewById(R.id.principal);
        regional_influence = (EditText) findViewById(R.id.regional_influence);
        consignorph = (EditText) findViewById(R.id.consignor_ph);
        consignor_gd = (EditText) findViewById(R.id.consignor_gd);
        consignor_em = (EditText) findViewById(R.id.consignor_em);
        shenfen = (EditText) findViewById(R.id.shenfen);
        consignor = (EditText) findViewById(R.id.consignor);
        site = (EditText) findViewById(R.id.site);
        remark = (EditText) findViewById(R.id.remark);
        company = (TextView) findViewById(R.id.company);
        sheng = (TextView) findViewById(R.id.sheng);
        city = (TextView) findViewById(R.id.city);
//        industry = (TextView) findViewById(R.id.industry);
//        scale = (TextView) findViewById(R.id.scale);
        nature = (TextView) findViewById(R.id.nature);
        submit = (Button) findViewById(R.id.submit);
        backbtn = (ImageView) findViewById(R.id.back_btn);
        choosecity = (LinearLayout) findViewById(R.id.choose_city);

        consignor.addTextChangedListener(new newtextWatcher(consignor));
        party.addTextChangedListener(new newtextWatcher(party));
        business_contact.addTextChangedListener(new newtextWatcher(business_contact));
        duty.addTextChangedListener(new newtextWatcher(duty));
        principal.addTextChangedListener(new newtextWatcher(principal));
        regional_influence.addTextChangedListener(new newtextWatcher(regional_influence));
        consignorph.addTextChangedListener(new newtextWatcher(consignorph));
        consignor_gd.addTextChangedListener(new newtextWatcher(consignor_gd));
        consignor_em.addTextChangedListener(new newtextWatcher(consignor_em));
        shenfen.addTextChangedListener(new newtextWatcher(shenfen));
        consignor.addTextChangedListener(new newtextWatcher(consignor));
        site.addTextChangedListener(new newtextWatcher(site));
        remark.addTextChangedListener(new newtextWatcher(remark));

        submit.setOnClickListener(onclicklistener);
        backbtn.setOnClickListener(onclicklistener);
        company.setOnClickListener(onclicklistener);
        nature.setOnClickListener(onclicklistener);
        choosecity.setOnClickListener(onclicklistener);
    }

    View.OnClickListener onclicklistener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.submit:
                    Log.i("=========>",""+consignor.getText()+party.getText()+business_contact.getText());
                    break;
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.company:
                    str = "请选择";
                    final String[] lssf = {"公民个人", "机关单位", "事业单位", "社会团体、社会组织",
                            "公司企业", "涉外的法人、公民或其他组织", "个体工商户", "村（社区）","其他"};
                    showDialog();
                    builder.setItems(lssf, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            company.setText(lssf[which]);
                            company.setTextColor(getResources().getColor(R.color.black));
                        }
                    }).show();
                    break;
                case R.id.nature:
                    str = "请选择";
                    final String[] xz = {"妇女", "未成年人", "残疾人", "老年人", "农名",
                            "下岗职工", "城市居民","外来人员"};
                    showDialog();
                    builder.setItems(xz, new DialogInterface.OnClickListener()
                    {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            nature.setText(xz[which]);
                            nature.setTextColor(getResources().getColor(R.color.black));
                        }
                    }).show();
                    break;
                case R.id.choose_city:
                    cityPickerChoose();
                    break;
            }
        }
    };

    String str;
    AlertDialog.Builder builder;
    private void showDialog(){
        builder = new AlertDialog.Builder(AddConsignorActivity.this,AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle(str);
    }

    /**
     * 选择城市
     */
    public void cityPickerChoose(){
        cityPicker = new CityPicker.Builder(AddConsignorActivity.this).textSize(16)
                .title("城市选择")
                .titleBackgroundColor("#234Dfa")
                .confirTextColor("#000000")
                .cancelTextColor("#000000")
                .onlyShowProvinceAndCity(true)//两级联动：省+市
                .province("山东省")
                .city("烟台市")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(false)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(10)
                .build();
        cityPicker.show();
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                String province = citySelected[0];
                String cityi = citySelected[1];
                sheng.setText(province);
                sheng.setTextColor(getResources().getColor(R.color.black));
                city.setText(cityi);
                city.setTextColor(getResources().getColor(R.color.black));
            }
        });
    }

    class  newtextWatcher implements TextWatcher {
        private EditText edit;
        public newtextWatcher(EditText editText){
            this.edit = editText;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String msg4 = edit.getText().toString();
            Log.i("",""+msg4);
        }
    }

}

package com.example.administrator.qingming.activity;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.example.administrator.qingming.R;
import com.example.administrator.qingming.api.BaseApi;
import com.example.administrator.qingming.api.MainApi;
import com.example.administrator.qingming.interfaces.GetResultCallBack;
import com.example.administrator.qingming.model.Constants;
import com.example.administrator.qingming.model.ModelFile;
import com.example.administrator.qingming.model.ModelSealImage;
import com.example.administrator.qingming.qinminutils.GsonUtil;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/9/8 0008.
 */

public class SealPngActivity extends Activity implements View.OnTouchListener {
    private ImageView imageView, image1;
    private String dz;
    private Button company_seal, cw_seal, save;
    private int index;
    private List<ModelSealImage.ResultBean> list;
    int lastX, lastY;
    private RelativeLayout containerView;
    private String filename;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_png);

        SharedPreferences sharedPreferences = getSharedPreferences("qinmin", Context.MODE_PRIVATE);
        gsid = sharedPreferences.getString("cid", "");
        createid = sharedPreferences.getString("id", "");

        checkPermisson();
        initView();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            String dzz = bundle.getString("dz", "").replace("\\", "/");
            index = bundle.getInt("index");
            filename = bundle.getString("file", "");
            id = bundle.getString("id", "");
            dz = "http://yunlvsi.cn/files/" + dzz;
                                                         //跳过内存缓存               //跳过硬盘缓存
            Glide.with(this).load(dz).asBitmap().centerCrop().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.mipmap.ic_launcher).fitCenter().into(imageView);
            //loadIntoUseFitWidth(this, dz, R.drawable.ic_launcher,imageView);
            if (index == 1) {
                company_seal.setVisibility(View.GONE);
                cw_seal.setVisibility(View.GONE);
                save.setVisibility(View.GONE);
            } else if (index == 2) {
                company_seal.setOnClickListener(onClickListener);
                cw_seal.setOnClickListener(onClickListener);
                save.setOnClickListener(onClickListener);
            } else {
                company_seal.setOnClickListener(onClickListener);
                cw_seal.setOnClickListener(onClickListener);
                save.setOnClickListener(onClickListener);
            }
        }

        findseal();
    }

    private void initView() {
        containerView = (RelativeLayout) findViewById(R.id.containerView);
        list = new ArrayList<>();
        imageView = (ImageView) findViewById(R.id.image);
        image1 = (ImageView) findViewById(R.id.image1);
        company_seal = (Button) findViewById(R.id.company_seal);
        cw_seal = (Button) findViewById(R.id.cw_seal);
        save = (Button) findViewById(R.id.save);
        image1.setDrawingCacheEnabled(true);
        image1.setOnTouchListener(this);

    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.back_btn:
                    finish();
                    break;
                case R.id.company_seal:
                    image1.setVisibility(View.VISIBLE);
                    Glide.with(SealPngActivity.this).load(companyseal).asBitmap().override(120,120).
                            skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).
                            fitCenter().into(image1);
                    break;
                case R.id.cw_seal:
                    image1.setVisibility(View.VISIBLE);
                    if(cwseal == null){
                        Toast.makeText(SealPngActivity.this,"请上传财务公章！",Toast.LENGTH_SHORT).show();
                    }else {
                        Glide.with(SealPngActivity.this).load(cwseal).asBitmap().override(120,120).
                                skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).
                                fitCenter().into(image1);
                    }
                    break;
                case R.id.save:
//                    imageView.setDrawingCacheEnabled(true);
//                    image1.setDrawingCacheEnabled(true);
                    dialog();
//                    imageView.setDrawingCacheEnabled(false);
//                    image1.setDrawingCacheEnabled(false);
                    break;
            }
        }
    };

    private void dialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, AlertDialog.THEME_HOLO_LIGHT);
        builder.setTitle("提示"); //设置标题
        builder.setMessage("是否确认盖章?"); //设置内容
        //builder.setIcon(R.mipmap.ic_launcher);//设置图标，图片id即可
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() { //设置确定按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                confirm(containerView);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() { //设置取消按钮
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        //参数都设置完成了，创建并显示出来
        builder.create().show();
    }

    /**
     * 上传图片并修改状态
     */
    String id;
    String state = "1";
    String createid;
    private void savesealApi() {
        MainApi.getInstance(this).savesealApi(id, createid, filename, state, filePath, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    Intent intent = new Intent(SealPngActivity.this, SealShenPiActivity.class);
                    startActivity(intent);
                } else BaseApi.showErrMsg(SealPngActivity.this, result);
            }
        });
    }

    //确认，生成图片
    String filePath;
    public void confirm(View view) {
        Bitmap bm = loadBitmapFromView(containerView);
        filePath = Environment.getExternalStorageDirectory() + "/" + "image.png";//保存路径
        try {
            bm.compress(Bitmap.CompressFormat.PNG, 100, new FileOutputStream(filePath));
            savesealApi();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //以图片形式获取View显示的内容（类似于截图）
    public static Bitmap loadBitmapFromView(View view) {
        Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.draw(canvas);
        return bitmap;
    }


    String gsid;
    String companyseal;
    String cwseal;
    private void findseal() {
        MainApi.getInstance(this).findsealApi(gsid, new GetResultCallBack() {
            @Override
            public void getResult(String result, int type) {
                if (type == Constants.TYPE_SUCCESS) {
                    List<ModelSealImage.ResultBean> resultbean = GsonUtil.fromJsonList(new Gson(), result, ModelSealImage.ResultBean.class);
                    list.addAll(resultbean);
                    for (int i = 0; i < resultbean.size(); i++) {
                        list.get(i).getGz_lx();
                        //公章类型：1、公司公章；2、财务公章
                        if (list.get(i).getGz_lx().equals("1")) {
                            companyseal = "http://yunlvsi.cn/files/" + list.get(i).getDz();
                        } else if (list.get(i).getGz_lx().equals("2")) {
                            cwseal = "http://yunlvsi.cn/files/" + list.get(i).getDz();
                        }
                    }
                } else BaseApi.showErrMsg(SealPngActivity.this, result);
            }
        });
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int dy = (int) event.getRawY() - lastY;

                int left = v.getLeft() + dx;
                int top = v.getTop() + dy;
                int right = v.getRight() + dx;
                int bottom = v.getBottom() + dy;
                v.layout(left, top, right, bottom);

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                Log.e("", "你松手了" + lastX + "    " + lastY);
                break;
        }
        return true;
    }

    /**
     * 自适应宽度加载图片。保持图片的长宽比例不变，通过修改imageView的高度来完全显示图片。
     */
    public static void loadIntoUseFitWidth(Context context, final String imageUrl, int errorImageId, final ImageView imageView) {
        Glide.with(context)
                .load(imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        if (imageView == null) {
                            return false;
                        }
                        if (imageView.getScaleType() != ImageView.ScaleType.FIT_XY) {
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                        }
                        ViewGroup.LayoutParams params = imageView.getLayoutParams();
                        int vw = imageView.getWidth() - imageView.getPaddingLeft() - imageView.getPaddingRight();
                        float scale = (float) vw / (float) resource.getIntrinsicWidth();
                        int vh = Math.round(resource.getIntrinsicHeight() * scale);
                        params.height = vh + imageView.getPaddingTop() + imageView.getPaddingBottom();
                        imageView.setLayoutParams(params);
                        return false;
                    }
                })
                .placeholder(errorImageId)
                .error(errorImageId)
                .into(imageView);
    }

    /**
     * 动态权限的请求
     */
    public void checkPermisson() {
        if (Build.VERSION.SDK_INT >= 23) {
            ActivityCompat.requestPermissions(this,//上下文
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE
                    },//权限数组
                    1001);
        }
    }

    /**
     * 动态权限的回调函数
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}

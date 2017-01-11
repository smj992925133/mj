package com.example.administrator.hday1_02;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private ImageView iv;
   private Bitmap bitmap,bitmapCache;
    private LruCache<String,Bitmap> lruCache;
    private int maxSize=4*1024*1024;
    private String imgUrl="https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=4155302816,1201715785&fm=116&gp=0.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv= (ImageView) findViewById(R.id.imageId);
        lruCache=new LruCache<>(maxSize);
    }

    public void downloadImg(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] imageByte = OkHttpUtils.getBytesByUrl(imgUrl);
                bitmap= BitmapFactory.decodeByteArray(imageByte,0,imageByte.length);
                bitmapCache=bitmap;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                      //  iv.setImageBitmap(bitmap);
                        Toast.makeText(MainActivity.this,"下载成功",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }).start();
    }

    public void saveImg(View view) {
        //首先判断缓存中是否有图片，没有存入
        if (lruCache.get(imgUrl)==null && bitmapCache!=null){
            lruCache.put(imgUrl,bitmapCache);

        }
    }

    public void getImg(View view) {
        //
        if (lruCache.get(imgUrl)!=null){
            Bitmap bitmap = lruCache.get(imgUrl);
            iv.setImageBitmap(bitmapCache);

        }else {
            iv.setImageResource(R.mipmap.ic_launcher);
        }
    }

    public void deleteImg(View view) {
        if (lruCache!=null){
            lruCache.remove(imgUrl);

        }
    }
}

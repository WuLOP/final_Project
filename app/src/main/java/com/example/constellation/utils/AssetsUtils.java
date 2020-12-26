package com.example.constellation.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.constellation.bean.StarInfoBean;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//读取Assets文件夹数据的工具类
public class AssetsUtils {
    //强引用
    private static Map<String,Bitmap>logoImgMap;
    private static Map<String,Bitmap>contentlogoImgMap;

//    读取assets文件夹中的内容 存放在字符串当中
    public static String getJsonFromAssets(Context context,String filename){
        //获取ASSETS文件夹管理器
        AssetManager am = context.getResources().getAssets();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        //获取文件输入流
        try  {
            InputStream is = am.open(filename);
//            读取内容存放在内存流当中
            int hasRead =0;
            byte[]buf =new byte[1024];
            while (true){
                hasRead =is.read(buf);
                if (hasRead==-1){
                    break;
                }
                baos.write(buf,0,hasRead);
            }
            String msg = baos.toString();
            is.close();
            return msg;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

//    读取Assets文件夹下的图片，返回Bitmap对象
    public static Bitmap getBitmapFromAssets(Context context,String filenema){
        Bitmap bitmap=null;
//        获取文件夹管理者
        AssetManager am = context.getResources().getAssets();
        try {
            InputStream is =am.open(filenema);
            //通过位图管理器，将输入流转成位图对象
            bitmap= BitmapFactory.decodeStream(is);
            is.close();
        }
        catch (Exception e){

        }
        return bitmap;
    }
//    @des将Assets文件夹中的图片一起读取 防止到内存中 便于管理
    public static  void saveBitmapFromAssets(Context context, StarInfoBean starInfoBean){
        logoImgMap =new HashMap<>();
        contentlogoImgMap =new HashMap<>();
        List<StarInfoBean.StarinfoDTO> starList = starInfoBean.getStarinfo();
        for (int i = 0; i <starList.size() ; i++) {
            String logoname = starList.get(i).getLogoname();
            String filename ="xzlogo/"+logoname+".png";
            Bitmap logoBm = getBitmapFromAssets(context, filename);
            logoImgMap.put(logoname,logoBm);
            String contentName ="xzcontentlogo/"+logoname+".png";
            Bitmap bitmap = getBitmapFromAssets(context, contentName);
            contentlogoImgMap.put(logoname,bitmap);

        }
    }

    public static Map<String, Bitmap> getLogoImgMap() {
        return logoImgMap;
    }

    public static Map<String, Bitmap> getContentlogoImgMap() {
        return contentlogoImgMap;
    }
}

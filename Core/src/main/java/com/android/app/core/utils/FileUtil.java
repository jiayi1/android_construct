package com.android.app.core.utils;

import android.os.Environment;
import android.text.TextUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by yulong.liu on 2016/12/15.
 */

public class FileUtil {

    public static boolean isSDAva(){
        boolean isSdAva = false;
        if(Environment.getExternalStorageDirectory().exists()
                || Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
                isSdAva = true;
        }
        return isSdAva;
    }

    public static void saveTextToFile(String saveText,String path){
        try {
            LogUtil.i("app",saveText);
            FileOutputStream fos = new FileOutputStream(path);
            fos.write(saveText.getBytes());
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getContentBytes(String path){
        String content =getFileContext(path);
        return content.getBytes();
    }

    public static String getFileContext(String path){
        try {
            StringBuffer sb = new StringBuffer();
            FileInputStream ios = new FileInputStream(path);
            int len;
            byte[] bytes = new byte[2048];
            while ((len = ios.read(bytes))!= -1){
                String string= new String(bytes,0,len,"UTF-8");
                sb.append(string);
            }
            ios.close();
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void saveInputStream(InputStream is, String path, DownLoadListener downLoadListener) {
        if(!TextUtils.isEmpty(path)){
            try {
                throw new Exception("the file savepath can not be null!!!");
            } catch (Exception e) {
                 return;
            }
        }
        byte[] bytes = new byte[2048];
        int len = 0;
        int currentSize = 0;
        int lastSize = 0;
        try {
            int totalSize = is.available();
            LogUtil.i("app","download file size :"+totalSize);
            FileOutputStream fos = new FileOutputStream(path);
            if (downLoadListener != null) {
                downLoadListener.start();
            }
            LogUtil.i("app","download start :"+ currentSize);
            while ((len = is.read(bytes)) != -1) {
                currentSize += len;
                LogUtil.i("info","downloadint current size :"+ currentSize);
                if(downLoadListener != null){
                    if(currentSize -lastSize > 5000){
                        downLoadListener.progress(currentSize,totalSize,0);
                        lastSize = currentSize;
                    }
                }
                fos.write(bytes, 0, len);
            }
            fos.flush();
            fos.close();
            LogUtil.i("app","download complete:"+ currentSize);
            if(downLoadListener != null){
                downLoadListener.finish();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public interface DownLoadListener {
        void start();

        void progress(int currentSize, int totalSize, int percent);

        void finish();
    }
}

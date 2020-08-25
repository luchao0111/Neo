package com.simonalong.neo.phantomjs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * 根据网页地址转换成图片
 */
public class PhantomTools {

    private static String BLANK = " ";

    // 执行cmd命令
    public static String cmd(String imgagePath, String url,String binPath,String jsPath) {
        return binPath + BLANK + jsPath + BLANK + url + BLANK + imgagePath;
    }

    //关闭命令
    public static void close(Process process, BufferedReader bufferedReader) throws IOException {
        if (bufferedReader != null) {
            bufferedReader.close();
        }
        if (process != null) {
            process.destroy();
            process = null;
        }
    }

    /**
     * @param url 要截取的网页地址
     * @param tempPath 生成图片的存放地址
     * @param binPath phantomjs插件安装地址
     * @param jsPath js文件引入地址
     * @throws IOException
     */
    public static String printUrlScreen2jpg(String url, String tempPath,String binPath,String jsPath) throws IOException{
        String imgName = System.currentTimeMillis() + ".png";
        String imagePath = tempPath + "/" + imgName;//图片路径
        //Java中使用Runtime和Process类运行外部程序
        Process process = Runtime.getRuntime().exec(cmd(imagePath,url,binPath,jsPath));
        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String tmp = "";
        while ((tmp = reader.readLine()) != null) {
            close(process,reader);
        }
        System.out.println("success");
        return imgName;
    }

}

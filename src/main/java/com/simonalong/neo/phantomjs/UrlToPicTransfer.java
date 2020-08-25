package com.simonalong.neo.phantomjs;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.util.FileCopyUtils;

import java.io.File;
import java.util.concurrent.TimeUnit;

/**
 * @author shizi
 * @since 2020/8/25 9:52 AM
 */
@Slf4j
public class UrlToPicTransfer {

    public static String PHANTOM_JS_BIN_PATH;

    public String trans(String url, String fileName) {
        try {
            //设置必要参数
            DesiredCapabilities dcaps = new DesiredCapabilities();
            //ssl证书支持
            dcaps.setCapability("acceptSslCerts", true);
            //截屏支持
            dcaps.setCapability("takesScreenshot", true);
            //css搜索支持
            dcaps.setCapability("cssSelectorsEnabled", true);
            //js支持
            dcaps.setJavascriptEnabled(true);
            //驱动支持（第二参数表明的是你的phantomjs引擎所在的路径）
            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOM_JS_BIN_PATH);

            //创建无界面浏览器对象
            PhantomJSDriver driver = new PhantomJSDriver(dcaps);
            //设置隐性等待（作用于全局）
            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            long start = System.currentTimeMillis();

            //打开页面
            driver.get(url);

            File srcFile = driver.getScreenshotAs(OutputType.FILE);
            fileName += ".png";
            FileCopyUtils.copy(srcFile, new File(fileName));
            log.info("图片生成完毕,耗时:{}ms", System.currentTimeMillis() - start);
            return fileName;
        } catch (Exception e) {
            log.error("保存异常,e=", e);
            return null;
        }
    }

    @Test
    public void test1(){
        UrlToPicTransfer.PHANTOM_JS_BIN_PATH = "/Users/zhouzhenyong/software/phantomjs/phantomjs-2.1.1-macosx/bin/phantomjs";
        new UrlToPicTransfer().trans("http://192.168.10.233:8080/jianshan-operation/notice?id=1", "/Users/zhouzhenyong/tem/pic/3");
    }
}

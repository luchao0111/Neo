//package com.simonalong.neo.phantomjs;
//
//import lombok.extern.slf4j.Slf4j;
//import org.junit.Test;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
//import org.openqa.selenium.phantomjs.PhantomJSDriver;
//import org.openqa.selenium.phantomjs.PhantomJSDriverService;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.springframework.util.FileCopyUtils;
//
//import java.io.File;
//import java.util.UUID;
//import java.util.concurrent.TimeUnit;
//
///**
// * @author shizi
// * @since 2020/8/25 8:11 AM
// */
//@Slf4j
//public class HtmlToImageService {
//    //phantomjs的并目录，绝对路径
//    private static String PHANTOM_JS_BIN_PATH = "/Users/zhouzhenyong/software/phantomjs/phantomjs-2.1.1-macosx/bin/phantomjs";
//    //图片保存路径
//    private static String SAVE_PATH = "/Users/zhouzhenyong/tem/pic/";
//
//    public static String saveHtml(SaveHtmlParam saveHtmlParam) {
//        try {
//            //设置必要参数
//            DesiredCapabilities dcaps = new DesiredCapabilities();
//            //ssl证书支持
//            dcaps.setCapability("acceptSslCerts", true);
//            //截屏支持
//            dcaps.setCapability("takesScreenshot", true);
//            //css搜索支持
//            dcaps.setCapability("cssSelectorsEnabled", true);
//            //js支持
//            dcaps.setJavascriptEnabled(true);
//            //驱动支持（第二参数表明的是你的phantomjs引擎所在的路径）
//            dcaps.setCapability(PhantomJSDriverService.PHANTOMJS_EXECUTABLE_PATH_PROPERTY, PHANTOM_JS_BIN_PATH);
//            //创建无界面浏览器对象
//            PhantomJSDriver driver = new PhantomJSDriver(dcaps);
//
//            //设置隐性等待（作用于全局）
//            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
//            long start = System.currentTimeMillis();
//            //打开页面
//            driver.get(saveHtmlParam.getUrl());
//            Thread.sleep(saveHtmlParam.getOpenWaitMillis());
//            //页面过长执行滚动
//            if(saveHtmlParam.getScrollScreen()){
//                JavascriptExecutor js = driver;
//                for (int i = 0; i < saveHtmlParam.getScrollTimes(); i++) {
//                    js.executeScript("window.scrollBy(0,"+saveHtmlParam.getScrollStep()+")");
//                    Thread.sleep(saveHtmlParam.getScrollWaitMillis());
//                }
//            }
//            //指定了OutputType.FILE做为参数传递给getScreenshotAs()方法，其含义是将截取的屏幕以文件形式返回。
//            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
//            Thread.sleep(saveHtmlParam.getSaveWaitMillis());
//            //利用FileUtils工具类的copyFile()方法保存getScreenshotAs()返回的文件对象
//            String fileName = UUID.randomUUID().toString()+".png";
//            FileCopyUtils.copy(srcFile, new File(SAVE_PATH + fileName));
//            log.info("图片生成完毕,耗时:{}ms",System.currentTimeMillis() - start);
//            return fileName;
//        }catch (Exception e){
//            log.error("保存异常,e=",e);
//            return null;
//        }
//    }
//
//
//
//    @Test
//    public void test1(){
//        UrlToPicTransfer.PHANTOM_JS_BIN_PATH = "/Users/zhouzhenyong/software/phantomjs/phantomjs-2.1.1-macosx/bin/phantomjs";
//        UrlToPicTransfer toPicTransfer = new UrlToPicTransfer();
//        toPicTransfer.getPicFileFromUrl("https://www.jianshu.com/p/9bc18be9b129", "/Users/zhouzhenyong/tem/pic/1");
//    }
//}

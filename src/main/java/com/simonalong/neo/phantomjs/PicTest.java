package com.simonalong.neo.phantomjs;

import org.junit.Test;

/**
 * @author shizi
 * @since 2020/8/25 8:04 AM
 */
public class PicTest {

//    @Test
//    public void tet1(){
//            XxlJobLogger.log("生成XX图片start..." + DateUtil.date2String(new Date()));
//            ///-------业务代码--------
//            List<Test> testList = testService.findByImg();
//            for(Test test:testList ){
//                //生成图片
//                String url = urlStr + test.getId();//拼接url(我这里是动态页面)
//                String nameStr = PhantomTools.printUrlScreen2jpg(url,imgUrl,binPath,jsPath);
//                String name = imgUrl + "/" + nameStr;
//
//                //上传图片
//                byte[] bytes = ImgUtil.image2Bytes(name);
//                QFile file = new QFile();
//                file.setContent(bytes);
//                file.setName("test");
//                file.setFileType(".png");
//                String uid = fileSDKClient.saveToUid(file);
//
//                //保存图片地址到数据库
//                test.setXXImg("/" + uid);
//                testService.update(test);
//            }
//            ///---------------
//
//            XxlJobLogger.log("生成XX图片end..." + DateUtil.date2String(new Date()));
//            ReturnT<String> returnT = new ReturnT<String>(200, "执行成功!");
//            return returnT;
//
//    }
    @Test
    public void test2(){
//        # phantomjs 插件安装地址
//        phantomjs.binPath=/opt/phantomjs-2.1.1/bin/phantomjs
//# js文件存放地址
//        phantomjs.jsPath=/opt/phantomjs-2.1.1/phantomjs.js
//# 要截取的网页url
//        phantomjs.urlStr=http://xxx:8080/xxx.html?xxxId=
//# 生成图片存放地址
//        phantomjs.imgUrl=/opt/phantomImg
//
//        String url = "https://www.jianshu.com/p/9bc18be9b129";
//        String imgUrl = "/Users/zhouzhenyong/tem";
//        String binPath = "/Users/zhouzhenyong/software/phantomjs/phantomjs-2.1.1-macosx";
//        String jsPath = "/Users/zhouzhenyong/software/phantomjs/phantomjs-2.1.1-macosx/bin/phantomjs";
//        PhantomTools.printUrlScreen2jpg(url,imgUrl,binPath,jsPath);
    }

}

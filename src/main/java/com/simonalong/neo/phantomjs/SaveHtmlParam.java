package com.simonalong.neo.phantomjs;


import lombok.Data;

@Data
public class SaveHtmlParam {
    /**
     * 目标html地址
     */
    private String url;
    /**
     * 页面渲染需要的时间
     */
    private Long openWaitMillis;

    /**
     * 保存文件需要的时间
     */
    private Long saveWaitMillis;
}

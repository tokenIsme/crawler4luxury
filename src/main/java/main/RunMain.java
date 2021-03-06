package main;

import absCompone.BaseCrawler;
import common.DbUtil;
import absCompone.Component;
import componentImpl.CrawlerComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yang 【youtulu.cn】
 * @Date: 2017/12/1.17:17
 * @desc 运行主入口
 */


public class RunMain {
    public static void main(String[] args) {
        DbUtil.init();
        Component component = CrawlerComponent.create("《《《《【luxury job start】》》》》");
        List<BaseCrawler> crawlerList = new ArrayList<>();
        component.AddJob(crawlerList)
                .run();
    }
}

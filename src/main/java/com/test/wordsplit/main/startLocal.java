package com.test.wordsplit.main;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.generated.StormTopology;

/**
 * Created by Administrator on 2017/8/14.
 */
public class startLocal {
    public static void main(String[] args) {
        LocalCluster cluster = new LocalCluster();
        StormTopology builder = new StormTopology();

        Config conf = new Config();
        cluster.submitTopology("Local", conf, builder);
    }
}



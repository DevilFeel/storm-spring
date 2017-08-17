package com.test.wordsplit.main;

import com.test.wordsplit.bolt.ReportBolt;
import com.test.wordsplit.bolt.WordCountBolt;
import com.test.wordsplit.bolt.SplitSentenceBolt;
import com.test.wordsplit.spout.SentenceSpout;
import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;
import org.apache.storm.tuple.Fields;
import org.apache.storm.utils.Utils;

public class TopologyMain {
    private static final String SENTENCE_SPOUT_ID = "sentence-spout";
    private static final String SPLIT_BOLT_ID = "split-bolt";
    private static final String COUNT_BOLT_ID = "count-bolt";
    private static final String REPORT_BOLT_ID = "report-bolt";
    private static final String TOPOLOGY_NAME = "word-count-topology";

    public static void main(String[] args) {
        SentenceSpout spout = new SentenceSpout();
        SplitSentenceBolt spiltBolt = new SplitSentenceBolt();
        WordCountBolt countBolt = new WordCountBolt();
        ReportBolt reportBolt = new ReportBolt();

        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout(SENTENCE_SPOUT_ID, spout); //注册数据源
        builder.setBolt(SPLIT_BOLT_ID, spiltBolt) //注册bolt
                .shuffleGrouping(SENTENCE_SPOUT_ID); //该bolt订阅spout随机均匀发射来的数据流
        builder.setBolt(COUNT_BOLT_ID, countBolt, 4)
                .fieldsGrouping(SPLIT_BOLT_ID, new Fields("word")); //该bolt订阅spiltBolt发射来的数据流，并且保证"word"字段值相同的tuple会被路由到同一个countBolt
        builder.setBolt(REPORT_BOLT_ID, reportBolt)
                .globalGrouping(COUNT_BOLT_ID); //该bolt订阅countBolt发射来的数据流，并且所有的tuple都会被路由到唯一的一个reportBolt中

        Config config = new Config();

        //本地模式启动
        LocalCluster cluster = new LocalCluster();
        cluster.submitTopology(TOPOLOGY_NAME, config, builder.createTopology());
        Utils.sleep(50000);
        cluster.shutdown();
//        try {
//            Thread.sleep(10 * 1000);
//        } catch (InterruptedException e) {
//        }
//        cluster.killTopology(TOPOLOGY_NAME);
//        cluster.shutdown();
    }
}

package com.test.wordsplit.spout;

import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.util.Map;

public class SentenceSpout extends BaseRichSpout {

    private String[] sentences = {
            "storm integrates with the queueing",
            "and database technologies you already use",
            "a storm topology consumes streams of data",
            "and processes those streams in arbitrarily complex ways",
            "repartitioning the streams between each stage of the computation however needed"
    };
    private int index = 0;

    private SpoutOutputCollector collector;

    @Override
    public void declareOutputFields(OutputFieldsDeclarer outputFieldsDeclarer) {
        outputFieldsDeclarer.declare(new Fields("sentence"));
    }

    @Override
    public void open(Map map, TopologyContext topologyContext, SpoutOutputCollector spoutOutputCollector) {
        this.collector = spoutOutputCollector;
    }

    @Override
    public void nextTuple() {
        if (index >= sentences.length){
            return;
        }
        this.collector.emit(new Values(sentences[index]));
        index++;
//        if (index >= sentences.length) {
//            index = 0;
//        }

        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
        }
    }
}

package com.stormproject;


import org.apache.storm.spout.SpoutOutputCollector;
import org.apache.storm.task.TopologyContext;
import org.apache.storm.topology.base.BaseRichSpout;
import org.apache.storm.tuple.Fields;
import org.apache.storm.tuple.Values;

import java.io.*;
import java.util.Map;

public class LogSpout extends BaseRichSpout {
    private SpoutOutputCollector collector;
    private BufferedReader reader;

    @Override
    public void open(Map conf, TopologyContext context, SpoutOutputCollector collector) {
        this.collector = collector;
        try {
            reader = new BufferedReader(new FileReader("logs.txt"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void nextTuple() {
        try {
            String line = reader.readLine();
            if (line != null) {
                collector.emit(new Values(line));
                Thread.sleep(100); // Simule un flux lent
            }
        } catch (Exception ignored) {}
    }

    @Override
    public void declareOutputFields(org.apache.storm.topology.OutputFieldsDeclarer declarer) {
        declarer.declare(new Fields("logline"));
    }
}

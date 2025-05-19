package com.stormproject;

import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.tuple.Tuple;

public class ErrorBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        int status = input.getIntegerByField("status");
        String ip = input.getStringByField("ip");
        if (status >= 400) {
            System.out.println("⚠️ ERREUR " + status + " détectée depuis IP " + ip);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {}
}

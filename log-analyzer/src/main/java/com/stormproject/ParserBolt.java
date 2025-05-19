package com.stormproject;

import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.tuple.Tuple;
import org.apache.storm.tuple.Values;

public class ParserBolt extends BaseBasicBolt {
    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        String line = input.getStringByField("logline");
        try {
            String[] parts = line.split(" ");
            String ip = parts[0];
            String page = parts[2];
            int status = Integer.parseInt(parts[3]);
            collector.emit(new Values(ip, page, status));
        } catch (Exception e) {
            System.out.println("Erreur parsing : " + line);
        }
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {
        declarer.declare(new org.apache.storm.tuple.Fields("ip", "page", "status"));
    }
}

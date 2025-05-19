package com.stormproject;

import org.apache.storm.topology.OutputFieldsDeclarer;
import org.apache.storm.topology.base.BaseBasicBolt;
import org.apache.storm.topology.BasicOutputCollector;
import org.apache.storm.tuple.Tuple;

import java.util.concurrent.atomic.AtomicInteger;

public class CountBolt extends BaseBasicBolt {
    private AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void execute(Tuple input, BasicOutputCollector collector) {
        int count = counter.incrementAndGet();
        System.out.println("Nombre total de requêtes : " + count);
    }

    @Override
    public void declareOutputFields(OutputFieldsDeclarer declarer) {}
}

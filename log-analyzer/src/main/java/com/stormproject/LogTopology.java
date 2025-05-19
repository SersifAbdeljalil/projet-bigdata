package com.stormproject;

import org.apache.storm.Config;
import org.apache.storm.LocalCluster;
import org.apache.storm.topology.TopologyBuilder;

public class LogTopology {
    public static void main(String[] args) {
        TopologyBuilder builder = new TopologyBuilder();

        builder.setSpout("log-reader", new LogSpout());
        builder.setBolt("parser", new ParserBolt()).shuffleGrouping("log-reader");
        builder.setBolt("counter", new CountBolt()).shuffleGrouping("parser");
        builder.setBolt("errors", new ErrorBolt()).shuffleGrouping("parser");

        Config conf = new Config();
        conf.setDebug(true);

        try {
LocalCluster cluster = new LocalCluster(); // et non StormSubmitter
            try {
                cluster.submitTopology("log-analysis", conf, builder.createTopology());
                Thread.sleep(10000); // Attendre pour observer le flux
            } finally {
                cluster.shutdown(); // ✅ Résout le "Resource leak"
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

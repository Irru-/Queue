/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dockdocker.queuetester;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import net.greghaines.jesque.Config;
import net.greghaines.jesque.ConfigBuilder;

import static net.greghaines.jesque.utils.JesqueUtils.entry;
import static net.greghaines.jesque.utils.JesqueUtils.map;
import net.greghaines.jesque.worker.MapBasedJobFactory;
import net.greghaines.jesque.worker.Worker;
import net.greghaines.jesque.worker.WorkerImpl;

/**
 *
 * @author nick
 */
public class QueueTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        final ConfigBuilder configBuilder = new ConfigBuilder();
        try {
          URI redisUrl = new URI(System.getProperty("REDIS_PROVIDER", "127.0.0.1"));
          String redisHost = "95.85.1.60";
          int redisPort = 6379;
          String redisUserInfo = redisUrl.getUserInfo();
          if (redisHost != null) {
            configBuilder.withHost(redisHost);
          }
          if (redisPort > -1) {
            configBuilder.withPort(redisPort);
          }
          if (redisUserInfo != null) {
            configBuilder.withPassword(redisUserInfo.split(":",2)[1]);
          }
        } catch (URISyntaxException use) {
          // Handle error
        }
        final Config config = configBuilder.build();
        
//        final Job job = new Job("ToggleContainer",
//          new Object[]{ "HELLO WORLD!" });
//        final Client Client = new ClientImpl(config);
//        Client.enqueue("foo", job);
//        Client.end();

        // Start a worker to run jobs from the queue
        final Worker worker = new WorkerImpl(config,
          Arrays.asList("1"), new MapBasedJobFactory(map(entry("ToggleContainer", ToggleContainerJob.class))));

        final Thread workerThread = new Thread(worker);
        workerThread.start();
        
//        worker.end(true);
//        try { workerThread.join(); } catch (Exception e){ e.printStackTrace(); }
    }
    
}

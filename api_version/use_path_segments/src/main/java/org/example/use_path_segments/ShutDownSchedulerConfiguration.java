package org.example.use_path_segments;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ShutDownSchedulerConfiguration {

    @Scheduled(cron = "0 */2 * * * *")
    public void onShutDown() {
        System.out.println("******************Shutting down******************");
        Runtime.getRuntime().halt(0);
    }
}

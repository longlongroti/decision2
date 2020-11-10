package com.shanxi.coal.scheduler;


import com.shanxi.coal.jedis.JedisCache;
//import com.shanxi.coal.utils.MyServerStatusUtils;
import com.shanxi.coal.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

@Component
public class TaskSyncServerStatusRunnable {

    @Scheduled(cron = "0 59 23 ? * *")
    public void syncServerStatus() {
        System.out.println("12点了。。");
    }

}

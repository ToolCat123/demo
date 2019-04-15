package com.cn.aliyunlogdemo.service.impl;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogGroupData;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.common.LogStore;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.response.BatchGetLogResponse;
import com.aliyun.openservices.log.response.CreateLogStoreResponse;
import com.aliyun.openservices.log.response.GetCursorResponse;
import com.cn.aliyunlogdemo.service.MyLogService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author yc
 */
@Service
public class MyLogServiceImpl implements MyLogService {

    @Value("${aliyun.log.endpoint:''}")
    private String endpoint;
    @Value("${aliyun.log.accessKeyId:''}")
    private String accessId;
    @Value("${aliyun.log.accessKeySecret:''}")
    private String accessKey;
    @Value("${aliyun.log.logStore:''}")
    private String logStore;
    @Value("${aliyun.log.projectName:''}")
    private String projectName;
    @Value("${aliyun.log.topic:''}")
    private String topic;


    /**
     * @return Client
     */
    private Client getClient() {
        return new Client(endpoint, accessId, accessKey);
    }

    /**
     * @return CreateLogStoreResponse
     */
    private CreateLogStoreResponse getLogStore() {
        LogStore store = new LogStore(logStore, 3, 10);
        CreateLogStoreResponse res = null;
        try {
            res = getClient().CreateLogStore(projectName, store);
        } catch (LogException e) {
            e.printStackTrace();
            System.out.println(res);
        }
        return res;
    }

    /**
     * 向log service发送2个日志包，每个日志包中，有2行日志
     */
    @Override
    public void writeLog() {
        Client client = getClient();
        for (int i = 0; i <= 1; i++) {
            List<LogItem> logGroup = new ArrayList<>();
            LogItem logItem = new LogItem((int) (System.currentTimeMillis() / 1000));
            logItem.PushBack("level", "info");
            logItem.PushBack("name", UUID.randomUUID().toString().replace("-", ""));
            logItem.PushBack("message", UUID.randomUUID().toString().replace("-", ""));
            logGroup.add(logItem);
            try {
                client.PutLogs(projectName, logStore, topic, logGroup, "");
                System.out.println("logGroup = " + logGroup);
            } catch (LogException e) {
                System.out.println("error code :" + e.GetErrorCode());
                System.out.println("error message :" + e.GetErrorMessage());
                System.out.println("error requestId :" + e.GetRequestId());
            }
        }
    }

    /**
     * 读取数据
     */
    @Override
    public void readLog() throws LogException {
        Client client = getClient();
        // 只读取0号shard的数据
        int shardId = 0;
        GetCursorResponse res = null;
        try {
            // 获取最近1个小时接收到的第一批日志的cursor位置
            long fromTime = (int) (System.currentTimeMillis() / 1000.0 - 3600);
            res = client.GetCursor(projectName, logStore, shardId, fromTime);
            System.out.println("shard_id:" + shardId + " Cursor:" + res.GetCursor());
        } catch (LogException e) {
            e.printStackTrace();
        }

        assert res != null;
        String cursor = res.GetCursor();
        while (true) {
            BatchGetLogResponse logDataRes = client.BatchGetLog(projectName, logStore, shardId, 100, cursor);
//            List<LogGroupData> logGroups = logDataRes.GetLogGroups();
            String nextCursor = logDataRes.GetNextCursor();
            System.out.print("下次读取的位置: " + nextCursor);
            if (cursor.equals(nextCursor)) {
                break;
            }
            cursor = nextCursor;
        }

    }

}

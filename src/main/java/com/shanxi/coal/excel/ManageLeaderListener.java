package com.shanxi.coal.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.shanxi.coal.dao.ManageLeaderMapper;
import com.shanxi.coal.domain.ManageLeader;
import liquibase.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManageLeaderListener  extends AnalysisEventListener<ManageLeader> {
    private static final int BATCH_COUNT = 5;
    ManageLeaderMapper manageLeaderMapper = null;
    List<ManageLeader> list = new ArrayList<ManageLeader>();

    public ManageLeaderListener(ManageLeaderMapper orgBaseInfoMapper) {
        this.manageLeaderMapper = orgBaseInfoMapper;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ManageLeader manageLeader, AnalysisContext analysisContext) {
        list.add(manageLeader);
        // 达到BATCH_COUNT了，需要去存储一次数据库，防止数据几万条数据在内存，容易OOM
        if (list.size() >= BATCH_COUNT) {
            saveData();
            // 存储完成清理 list
            list.clear();
        }
    }

    /**
     * 所有数据解析完成了 都会来调用
     *
     * @param context
     */
    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        // 这里也要保存数据，确保最后遗留的数据也存储到数据库
        saveData();
    }

    /**
     * 加上存储数据库
     */
    private void saveData() {
        for (ManageLeader manageLeader : list) {
            if (StringUtils.isEmpty(manageLeader.getUuid())) {
                manageLeader.setUuid(UUID.randomUUID().toString());
                manageLeaderMapper.insertSelective(manageLeader);
            } else {
                manageLeaderMapper.updateByPrimaryKeySelective(manageLeader);
            }
        }
    }
}
package com.shanxi.coal.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.shanxi.coal.dao.ManageLeaderMapper;
import com.shanxi.coal.dao.ManageSystemItemsMapper;
import com.shanxi.coal.domain.ManageLeader;
import com.shanxi.coal.domain.ManageSystemItems;
import liquibase.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ManageSystemItemsListener extends AnalysisEventListener<ManageSystemItems> {
    private static final int BATCH_COUNT = 5;
    ManageSystemItemsMapper manageSystemItemsMapper = null;
    List<ManageSystemItems> list = new ArrayList<ManageSystemItems>();

    public ManageSystemItemsListener(ManageSystemItemsMapper manageSystemItemsMapper) {
        this.manageSystemItemsMapper = manageSystemItemsMapper;
    }

    /**
     * 这个每一条数据解析都会来调用
     *
     * @param data    one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     */
    @Override
    public void invoke(ManageSystemItems manageSystemItems, AnalysisContext analysisContext) {
        list.add(manageSystemItems);
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
        for (ManageSystemItems manageSystemItems : list) {
            if (StringUtils.isEmpty(manageSystemItems.getUuid())) {
                manageSystemItems.setUuid(UUID.randomUUID().toString());
                manageSystemItemsMapper.insertSelective(manageSystemItems);
            } else {
                manageSystemItemsMapper.updateByPrimaryKeySelective(manageSystemItems);
            }
        }
    }
}
//package com.shanxi.coal;
//
//import com.alibaba.excel.EasyExcel;
//import com.shanxi.coal.dao.AssetInvestmentCompletionMapper;
//import com.shanxi.coal.domain.AssetInvestmentCompletion;
//import com.shanxi.coal.controller.Listener;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import javax.annotation.Resource;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.util.List;
//import java.util.UUID;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = {SpringBootWebApplication.class})// 指定启动类
//@WebAppConfiguration
//public class ImportExcel {
//
//    @Resource
//    private AssetInvestmentCompletionMapper assetInvestmentCompletionMapper;
//
//    @Test
//    public void test() throws FileNotFoundException {
//        FileInputStream fis = null;
//        fis = new FileInputStream("C:\\Users\\15225\\Desktop\\1.xls");
//        Listener l = new Listener();
//        EasyExcel.read(fis, AssetInvestmentCompletion.class, l)
//                .sheet().headRowNumber(0).doRead();
//        List<AssetInvestmentCompletion> list = l.list;
//        String batchId = UUID.randomUUID().toString();
//        String basicOutDate = null;
//        String basicFillPerson = null;
//        String basicStatisticPerson = null;
//        String basicManager = null;
//        String basicOrgName = null;
//        String basicStatisticDate = null;
//        for (int i = 0; i < list.size(); i++) {
//            if(i == 1){
//                basicOutDate = list.get(i).getStartTimeC();
//                basicFillPerson = list.get(i).getDesignCapabilityE();
//                basicStatisticPerson = list.get(i).getCoalBrandG();
//                basicManager = list.get(i).getInvestmentCompletedI();
//                basicOrgName = list.get(i).getTotalK();
//                basicStatisticDate = list.get(i).getStructurePointsM();
//            }
//            if (i >= 6) {
//                list.get(i).setBatchId(batchId);
//                list.get(i).setUuid(UUID.randomUUID().toString());
//                list.get(i).setNumber(i);
//                list.get(i).setBasicOutDate(basicOutDate);
//                list.get(i).setBasicFillPerson(basicFillPerson);
//                list.get(i).setBasicStatisticPerson(basicStatisticPerson);
//                list.get(i).setBasicManager(basicManager);
//                list.get(i).setBasicOrgName(basicOrgName);
//                list.get(i).setBasicStatisticDate(basicStatisticDate);
//                assetInvestmentCompletionMapper.insertSelective(list.get(i));
//            }
//        }
//
//    }
//}

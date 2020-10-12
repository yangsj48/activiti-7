package com.carol.test;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricActivityInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;

import java.util.List;

/**
 * @program:activiti
 * @description:HistoryService act_hi_*相关表的操作 历史信息查询
 * @author: carol
 * @date 2020/10/9 11:24
 */
public class ActivitiHistoryService {
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        HistoryService historyService = processEngine.getHistoryService();

        /*//查询活动节点
        HistoricActivityInstanceQuery historicActivityInstanceQuery =
                historyService.createHistoricActivityInstanceQuery();

        //构造查找并对数据排序

        List<HistoricActivityInstance> list = historicActivityInstanceQuery.processInstanceId("22501")
                .orderByHistoricActivityInstanceStartTime()
                .asc()
                .list();

        for (HistoricActivityInstance instance : list) {
            System.out.println(instance.getActivityId());
            System.out.println(instance.getActivityName());
            System.out.println(instance);
        }*/
      //查询的任务节点
        List<HistoricTaskInstance> list1 = historyService.createHistoricTaskInstanceQuery()
                .processInstanceBusinessKey("test-02")
                .orderByHistoricTaskInstanceStartTime()
                .asc()
                .list();

        for(HistoricTaskInstance instance: list1){
            System.out.println(instance.getName());
            System.out.println(instance.getAssignee());
        }
    }
}

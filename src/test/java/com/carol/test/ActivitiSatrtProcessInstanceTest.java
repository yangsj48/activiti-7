package com.carol.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.HashMap;
import java.util.Map;

/**
 * @program:activiti01
 * @description:流程实例启动 测试类
 * @author: carol
 * @date 2020/9/30 13:28
 * 受影响的表：
 *  act_hi_actinst 已完成的活动信息
 *  act_hi_indentitylink 参与者信息
 *  act_hi_procinst  流程实例
 *  act_hi_tackinst  任务实例表
 *  act_ru_execution 执行表
 *  act_ru_indentitylink 参与者信息
 *  act_ru_task  任务表
 */
public class ActivitiSatrtProcessInstanceTest {
    public static void main(String[] args) {

        //1. 获取到ProcessEngine
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2. 获取到RunTimeService
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //3 创建流程实例 key或者流程id
        // key 的获取方式： 1.bpmn文件中的id的值 2 act_re_procdef 表中key字段 根据ID_查询
        // key = holiday

        ProcessInstance instance = runtimeService.startProcessInstanceByKey("holiday");
        //runtimeService.startProcessInstanceByKey("holiday","business_01"); 第二个参数为业务ID 启动流程实例时， 添加业务主键

        //4 输出实例的相关信息
        System.out.println("流程部署id："+instance.getDeploymentId());//null
        System.out.println("流程定义Id:"+instance.getProcessDefinitionId());
        System.out.println("流程实例id:"+instance.getId());
        System.out.println("活动Id:"+instance.getActivityId());
    }

    public void startProcessInstanceTest(String key, String businessKey){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();


        ProcessInstance instance = runtimeService.startProcessInstanceByKey(key,businessKey);

        System.out.println("流程部署id："+instance.getDeploymentId());//null
        System.out.println("流程定义Id:"+instance.getProcessDefinitionId());
        System.out.println("流程实例id:"+instance.getId());
        System.out.println("活动Id:"+instance.getActivityId());

        runtimeService.signalEventReceived("sa");
    }
    //启动流程实例时， 设置流程变量
    public void startProcessInstanceVariable(String key, String businessKey){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();
        Map<String, Object> variableMap = new HashMap<>();
        variableMap.put("num", 200);



        ProcessInstance instance = runtimeService.startProcessInstanceByKey(key,businessKey,variableMap);


        runtimeService.signalEventReceived("sa");
    }
}

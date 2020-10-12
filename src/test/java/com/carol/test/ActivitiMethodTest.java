package com.carol.test;


import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.junit.Test;

/**
 * @program:activiti
 * @description:测试类
 * @author: carol
 * @date 2020/10/10 14:34
 */
public class ActivitiMethodTest {

    /**
     * 流程部署
     */
    @Test
    public void testDeployment() throws InterruptedException {
        ActivitiDeploymentTest activitiDeploymentTest = new ActivitiDeploymentTest();
        activitiDeploymentTest.deployment("diagram/listener.bpmn", "diagram/listener.png", "任务监听器测试", "listener-02",false);
    }


    /**
     * 流程实例启动
     */
    @Test
    public void testStartProcessInstance() {
        ActivitiSatrtProcessInstanceTest satrtProcessInstanceTest = new ActivitiSatrtProcessInstanceTest();
        satrtProcessInstanceTest.startProcessInstanceVariable("listener", "listener-04");
    }

    /**
     * 任务查询与处理
     */
    @Test
    public void testQueryAndCompleteTask() {
        ActivitiTaskQueryTest queryTest = new ActivitiTaskQueryTest();
        String taskId = queryTest.taskQuery("listener-02");

        TaskCompleteTest taskCompleteTest = new TaskCompleteTest();
        taskCompleteTest.completeTask(taskId);

    }

    /**
     * 在执行排他网关的前一个流程时， 需要指定变量值
     * @param args
     */
    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RuntimeService runtimeService = processEngine.getRuntimeService();
        //第一个参数 executionId 为执行编号  可从Task实体中获得，如果是不需要计算得到的变量值，则可以在启动流程实例时就设置流程变量
        runtimeService.setVariable("25002","price",9000);
    }


}

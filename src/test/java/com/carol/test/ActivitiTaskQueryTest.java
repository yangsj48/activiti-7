package com.carol.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

import java.util.List;

/**
 * @program:activiti
 * @description:当前用户任务列表查询 测试类
 * @author: carol
 * @date 2020/9/30 13:59
 */
public class ActivitiTaskQueryTest {

    public static void main(String[] args) {
        //1. 创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2 得到TaskService 对象
        TaskService taskService = processEngine.getTaskService();

        // 3 根据流程定义的key, 负责人assignee来实现当前用户任务列表查询
        List<Task> list = taskService.createTaskQuery()
                .processDefinitionKey("holiday")
                .taskAssignee("wangwu")
                .list();

        // 4任务列表的展示
        for (Task task : list) {
            System.out.println("流程实例id:" + task.getProcessInstanceId());
            System.out.println("任务Id:" + task.getId());
            System.out.println("任务负责人:" + task.getAssignee());
            System.out.println("任务名称:" + task.getName());

        }


    }


    public String taskQuery(String key, String assignee, String businessKey) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .processDefinitionKey(key)
                .taskAssignee(assignee)
                .processInstanceBusinessKey(businessKey)
                .singleResult();


        return null != task ? task.getId() : "";

    }
    public String taskQuery( String businessKey) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();

        Task task = taskService.createTaskQuery()
                .processInstanceBusinessKey(businessKey)
                .singleResult();
        System.out.println("excludeId"+ task.getId());


        return null != task ? task.getId() : "";

    }
}

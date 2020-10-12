package com.carol.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;

/**
 * @program:activiti
 * @description:任务处理
 * @author: carol
 * @date 2020/10/9 13:23
 */
public class TaskCompleteTest {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();
        Task task = taskService.createTaskQuery().taskAssignee("zhangsan").processInstanceId("5001").singleResult();
/*
        boolean suspended = task.isSuspended();
        System.out.println(suspended);*/
        taskService.complete(task.getId());
    }


    public  void completeTask(String taskId){
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        TaskService taskService = processEngine.getTaskService();
        taskService.complete(taskId);


    }
}

package com.carol.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;

/**
 * @program:activiti01
 * @description:流程实例的挂起或激活
 * @author: carol
 * @date 2020/10/9 14:47
 */
public class SuspendOrActivateProcessInstance {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        RuntimeService runtimeService = processEngine.getRuntimeService();


        ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
                .processInstanceId("5001")
                .singleResult();

        boolean suspended = processInstance.isSuspended();
        if(suspended){
            runtimeService.activateProcessInstanceById(processInstance.getId());
            System.out.println("流程实例激活");
        }else{
            runtimeService.suspendProcessInstanceById(processInstance.getId());
            System.out.println("流程实例挂起");
        }
    }
}

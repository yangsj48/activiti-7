package com.carol.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;

/**
 * @program:activiti
 * @description:流程定义的挂起或激活 流程定义为挂起状态，则不允许创建新的流程实例，
 *              且该流程定义下所有的流程实例全部被挂起，暂停执行
 *             主要影响的是act_ru_task表中 SUSPENSION_STATE_字段 1： 激活  2： 挂起
 *
 *
 * @author: carol
 * @date 2020/10/9 14:28
 */
public class SuspendOrActivateProcessDefintion {

    public static void main(String[] args) {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        RepositoryService repositoryService = processEngine.getRepositoryService();
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
                .processDefinitionKey("holiday")
                .singleResult();
        //流程定义状态(是否挂起)
        boolean suspended = processDefinition.isSuspended();
        if(suspended){
            repositoryService.activateProcessDefinitionByKey("holiday");
            System.out.println("激活流程定义");
        }else{
            repositoryService.suspendProcessDefinitionByKey("holiday");
            System.out.println("挂起流程定义");
        }

    }
}

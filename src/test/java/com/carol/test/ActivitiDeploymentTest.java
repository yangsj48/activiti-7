package com.carol.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;

import java.util.Map;
import java.util.HashMap;
/**
 * @program:activiti
 * @description:activiti 流程定义 部署测试
 * @author: carol
 * @date 2020/9/30 11:20
 * 受影响的表：
 * act_re_deployment  部署信息
 * act_re_procdef  流程定义信息
 * act_ge_bytearray  流程定义的bpmn文件以及png文件
 */
public class ActivitiDeploymentTest {
    //流程定义部署
    public static void main(String[] args) {
        //1. 创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2. 得到部署相关的Service(RepositoryService)实例
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3. 进行部署

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("diagram/holiday.bpmn") //添加bpmn资源
                .addClasspathResource("diagram/holiday.png")  // 添加图片资源
                .name("请假申请流程")
                .deploy();


        //4 输出部署信息
        System.out.println(deployment.getName());
        System.out.println(deployment.getId());




    }
    public void deployment(String bpmnStr,String pngStr,String name,String key, boolean timer) throws InterruptedException {
        //1. 创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2. 得到部署相关的Service(RepositoryService)实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        RuntimeService runtimeService = processEngine.getRuntimeService();

        //3. 进行部署

        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource(bpmnStr) //添加bpmn资源
                .addClasspathResource(pngStr)  // 添加图片资源
                .name(name)
                .key(key)
                .deploy();

        // 启动定时器
        if(timer){
            processEngine.getProcessEngineConfiguration().getAsyncExecutor().start();
            Thread.sleep(30 * 1000);

           long  count = runtimeService.createProcessInstanceQuery().processDefinitionKey("timer").count();
            System.out.println("sleep后的流程实例个数：" + count);

            processEngine.close();
        }

    }

}

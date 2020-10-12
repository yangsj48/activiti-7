package com.carol.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;

/**
 * @program:activiti01
 * @description:流程定义删除
 * @author: carol
 * act_re_deployment  部署信息
 * act_re_procdef  流程定义信息
 * act_ge_bytearray  流程定义的bpmn文件以及png文件
 * @date 2020/10/9 9:18
 */
public class DeleteDeploymentTest {
    public static void main(String[] args) {
        //1. 创建ProcessEngine对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2. 得到部署相关的Service(RepositoryService)实例
        RepositoryService repositoryService = processEngine.getRepositoryService();
        //3. 删除流程定义  传入参数为流程定义部署id
        String deploymentId = "1";
        //如果删除的流程定义存在未完成的流程， 则不能删除成功

        repositoryService.deleteDeployment(deploymentId);
        //删除的流程定义存在未完成的流程 ，采用以下方法才能删除成功(级联删除)
        //repositoryService.deleteDeployment(deploymentId, true);


    }
}
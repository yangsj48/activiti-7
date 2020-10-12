package com.carol.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.zip.ZipInputStream;


/**
 * @program:activiti
 * @description:activiti 的基础操作 流程部署，流程实例的启动等
 * @author: carol
 * @date 2020/10/12 14:26
 */
public class DeploymentTest {

    private ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    private String bpmnStr = "diagram/holiday.bpmn";
    private String pngStr = "diagram/holiday.png";
    private String key = "holiday";
    private String name = "请假流程";


    /**
     * 流程部署： 方式1
     *
     * @param bpmnStr bpmn文件路径
     * @param pngStr  png文件路径
     * @param key     流程定义的key
     * @param name    流程名称
     */
    public void deployment(String bpmnStr, String pngStr, String key, String name) {
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .key(key)
                .name(name)
                .addClasspathResource(bpmnStr)
                .addClasspathResource(pngStr)
                .deploy();

        System.out.println(deploy.getId() + deploy.getName() + deploy.getKey());

    }

    /**
     * 流程部署： 方式2
     *
     * @param zipFileStr 资源压缩包文件路径
     * @param key        流程定义key
     * @param name       流程定义名称
     * @throws FileNotFoundException
     */
    public void deploymentByZip(String zipFileStr, String key, String name) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(zipFileStr);

        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deploy = repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .key(key)
                .name(name)
                .deploy();
        System.out.println(deploy.getId() + deploy.getName() + deploy.getKey());
    }

    @Test
    public void test() {
        deployment(bpmnStr, pngStr, key, name);
    }

}

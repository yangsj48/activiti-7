package com.carol.test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.apache.commons.io.IOUtils;

import java.io.*;

/**
 * @program:activiti
 * @description:获取资源文件 从act_ge_bytearray表中获取bpmn，png文件， 并回写到本地
 * 应用场景： 查看流程的具体步骤
 * 技术方案：
 *      1.使用Activiti自带的api
 *      2.jdbc对blob类型，clob类型数据的读取并保存(IO流的转换，使用commons-io.jar包来解决)
 * @author: carol
 * @date 2020/10/9 10:26
 */
public class QueryBpmnFileTest {
    public static void main(String[] args) throws IOException {
        //1.获取ProcessEngine对象

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

        //2.得到RepositoryService对象
        RepositoryService repositoryService = processEngine.getRepositoryService();

        //3. 得到查询器ProcessDefinitionQuery对象
        ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

        //4. 设置查询条件

        processDefinitionQuery.processDefinitionKey("holiday"); //参数为流程定义id

        //5.执行查询操作，得到想要的流程定义
        ProcessDefinition processDefinition = processDefinitionQuery.singleResult();

        //6. 通过流程定义信息得到部署id
      String deploymentId =   processDefinition.getDeploymentId();


        //7. 通过RepositoryService的方法，实现读取png,bpmn文件信息
        //getResourceAsStream() 部署id, 资源名称
        //processDefinition.getDiagramResourceName()获取png图片资源的名称
        //processDefinition.getResourceName()获取bpmn文件的名称
        InputStream pngIs = repositoryService.getResourceAsStream(deploymentId,processDefinition.getDiagramResourceName());

        InputStream bpmnIs = repositoryService.getResourceAsStream(deploymentId,processDefinition.getResourceName());
        //8. 构建输出流 OutputStream
        try {
            File pngFile = new File("E:\\gitDemo\\write\\"+ processDefinition.getDiagramResourceName());
            File bpmnFile = new File("E:\\gitDemo\\write\\"+ processDefinition.getResourceName());
            OutputStream pngOs = new FileOutputStream(pngFile);
            OutputStream bpmnOs = new FileOutputStream(bpmnFile);

            //9. 输入流与输出流的转换
            IOUtils.copy(pngIs, pngOs);
            IOUtils.copy(bpmnIs, bpmnOs);

            //10.输出流的关闭
            pngOs.close();
            bpmnOs.close();
            pngIs.close();
            bpmnIs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}

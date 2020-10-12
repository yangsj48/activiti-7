package com.carol.test;

import org.activiti.engine.*;
import org.junit.Test;

/**
 * @program:activiti01
 * @description:Activiti 测试类 测试Activiti所需要25张表的生成
 * @author: carol
 * @date 2020/9/29 13:41
 */
public class ActivitiTest {
    @Test
    public void testGenTable() {
        /*
        1创建ProcessEngineConfiguration对象
        默认的bean id 为 'processEngineConfiguration'
        如果在activit.cfg.xml 中对ProcessEngine配置对象 id进行了修改，则采用第二种方式创建对象
        */

        //1.1
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.
                createProcessEngineConfigurationFromResource("activiti.cfg.xml");
        //1.2
      /* ProcessEngineConfiguration configuration = ProcessEngineConfiguration.
               createProcessEngineConfigurationFromResource("activiti.cfg.xml", "processEngineConfiguration");*/
        //2 创建ProcessEngine对象
        ProcessEngine processEngine = configuration.buildProcessEngine();

        //3 输出ProcessEngine对象
        System.out.println(processEngine);

        RuntimeService runtimeService = processEngine.getRuntimeService();
        HistoryService historyService = processEngine.getHistoryService();
        TaskService taskService = processEngine.getTaskService();

    }

    @Test
    public void testGenTable01 (){

        /*采用这种方式具备的条件：
        1 activiti的配置文件名称： activiti.cfg.xml
        2 bean 的id  ProcessEngineConfiguration
        */

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }

}


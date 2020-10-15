package com.carol.activiti;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.Test;

/**
 * @program:activiti
 * @description:activit表创建测试
 * @author: carol
 * @date 2020/10/12 14:47
 */
public class CreateTableTest {


    //默认的配置文件名称
    private String resourceStr = "activiti.cfg.xml";
    //默认的流程引擎beanName
    private String beanName = "processEngineConfiguration";




    /**
     * 数据库表创建： 方式1(默认创建方式)
     *
     * 采用这种方式具备的条件：
     1 activiti的配置文件名称： activiti.cfg.xml
     2 bean 的id  ProcessEngineConfiguration
     */

    public void createTableDefault(){

        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        System.out.println(processEngine);
    }

    /**
     * 数据库表创建： 方式2
     * @param resourceStr
     * @param beanNameStr
     */
    public void createTable(String resourceStr,String beanNameStr){
        ProcessEngineConfiguration configuration = ProcessEngineConfiguration.
                createProcessEngineConfigurationFromResource(resourceStr,beanNameStr);

        ProcessEngine processEngine = configuration.buildProcessEngine();
        System.out.println(processEngine.getName());
    }


    @Test
    public void test(){
        createTableDefault();
        //createTable(resourceStr,beanName);
    }
}

package com.carol.test;

import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;

/**
 * @program:activiti
 * @description:监听器实现类
 * @author: carol
 * @date 2020/10/12 9:50
 */
public class TaskListenerTest implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {
        System.out.println("----监听方法调用,设置任务执行人----------");
        delegateTask.setAssignee("lisi"); //设置任务执行人
        //delegateTask.setVariable("assignee","lisi");  设置变量

    }
}

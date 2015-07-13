package org.activiti.designer.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;
import java.io.FileInputStream;

import org.activiti.engine.FormService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.test.ActivitiRule;
import org.junit.Rule;
import org.junit.Test;

public class ProcessTestFygkqqingjia {

	private String filename = "D:/git-repository/fyg-kq-process/fyg-kq-qingjia/src/main/resources/diagrams/fyg-kq-qingjia.bpmn";

	@Rule
	public ActivitiRule activitiRule = new ActivitiRule();

	@Test
	public void startProcess() throws Exception {
		RepositoryService repositoryService = activitiRule.getRepositoryService();
		repositoryService.createDeployment().addInputStream("fyg-kq-qingjia.bpmn20.xml",
				new FileInputStream(filename)).deploy();
		
		RuntimeService runtimeService = activitiRule.getRuntimeService();
		Map<String, Object> variableMap = new HashMap<String, Object>();
		variableMap.put("v1", "the val of v1");
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("fyg-kq-qingjia", variableMap);
		
		assertNotNull(processInstance.getId());
		System.out.println("id " + processInstance.getId() + " "
				+ processInstance.getProcessDefinitionId());
		
		

		TaskService taskService = activitiRule.getTaskService();
		Task task = taskService.createTaskQuery().taskAssignee("chenzw").singleResult();
		showformkey(task.getId());
		assertEquals("dm_check",task.getTaskDefinitionKey());
		taskService.complete(task.getId());
		
		task = taskService.createTaskQuery().taskAssignee("chenzw").singleResult();
		assertEquals("vp_check",task.getTaskDefinitionKey());
		showformkey(task.getId());
		taskService.complete(task.getId());
	}

	private void showformkey(String taskid) {
		FormService formService = activitiRule.getFormService();
		String formKey= formService.getTaskFormData(taskid).getFormKey();
		System.out.println("formkey:"+formKey);
	}
}
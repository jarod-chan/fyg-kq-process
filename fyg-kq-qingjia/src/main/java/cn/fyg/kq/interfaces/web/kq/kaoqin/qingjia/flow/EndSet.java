package cn.fyg.kq.interfaces.web.kq.kaoqin.qingjia.flow;

import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.JavaDelegate;

public class EndSet implements JavaDelegate {

	@Override
	public void execute(DelegateExecution execution) throws Exception {
		System.out.println("\n\n\n\n流程执行完成\n\n\n\n");
	}

}

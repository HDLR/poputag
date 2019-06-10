package com.eastern.jinxin.sys.aop.log;

import java.util.TimerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.alibaba.fastjson.JSON;
import com.eastern.jinxin.sys.operation.log.entity.SysLogEntity;
import com.eastern.jinxin.sys.operation.log.service.SysLogService;
import com.eastern.jinxin.sys.utils.SpringContextHolder;

public class LogTaskFactory {

	private static Logger logger = LoggerFactory.getLogger(LogManager.class);
	private static SysLogService sysLogService = SpringContextHolder.getBean(SysLogService.class);
	
	public static TimerTask operatorLog(SysLogEntity sysLog) {
		return new TimerTask() {
			@Override
			public void run() {
				try {
					sysLogService.insert(sysLog);
					logger.info(JSON.toJSONString(sysLog));
				} catch (Exception e) {
					logger.error("保存操作日志异常：", e);
				}
			}
		};
	}
}

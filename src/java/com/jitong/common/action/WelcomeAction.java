package com.jitong.common.action;

import org.apache.log4j.Logger;



public class WelcomeAction extends JITActionBase {
	private static Logger logger = Logger.getLogger(WelcomeAction.class);
	@Override
	public String execute() throws Exception {
		logger.debug("in welcome action.");
		logger.debug(this.session);
		return SUCCESS;
	}

}

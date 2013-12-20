<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="org.quartz.*"%>
<%@page import="org.quartz.impl.*"%>
<%@page import="com.jitong.common.exception.*"%>
<%@page import="com.jitong.common.timer.*"%>
<%@page import="java.util.Date"%>
<%!
public void startJob(String jobName,String group,String triggerName,String exp) throws Exception {
	try {
		SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		JobDetail job = new JobDetail(jobName, group, TMemoryJob.class);
		CronTrigger trigger = new CronTrigger(triggerName, group, jobName, group, exp);
		sched.addJob(job, true);
		Date ft = sched.scheduleJob(trigger);
		System.out.println(TMemoryJob.MEM);
	} catch (Exception e) {
		throw e;
	}
}
%>
<%
String jobName = request.getParameter("jobName");
String group = request.getParameter("group");
String triggerName = request.getParameter("triggerName");
String exp = request.getParameter("exp");
if(jobName!=null&jobName.length()>0){
  startJob(jobName, group, triggerName, exp);
}
%>
<br></br>
<form action=${root}/jsp/sysaddjob.jsp>
	<input type='text' name="jobName" value="jobName">
	<input type='text' name="group" value="group">
	<input type='text' name="triggerName" value="triggerName">
	<input type='text' name="exp" value="exp">
	<input type='submit' value="ok">
</form>

<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp" %>
<%@page import="org.quartz.*"%>
<%@page import="org.quartz.impl.*"%>
<%!
public void pauseTrigger(String triggerName, String group) {
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler sched = sf.getScheduler();
			sched.pauseTrigger(triggerName, group);//停止触发器   
		} catch (SchedulerException e) {
			throw new RuntimeException(e);
		}
	}

public void resumeTrigger(String triggerName,String group){        
    try {   
    	SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.resumeTrigger(triggerName, group);//重启触发器   
     } catch (SchedulerException e) {   
        throw new RuntimeException(e);   
     }   
 }  

public boolean removeTrigger(String triggerName,String group){        
    try {   
    	SchedulerFactory sf = new StdSchedulerFactory();
		Scheduler sched = sf.getScheduler();
		sched.pauseTrigger(triggerName, group);//停止触发器   
        return sched.unscheduleJob(triggerName, group);//移除触发器   
     } catch (SchedulerException e) {   
        throw new RuntimeException(e);   
     }   
 }  


%>
<%
	SchedulerFactory sf = new StdSchedulerFactory();
	Scheduler sched = sf.getScheduler();
	out.print("Scheduler Name = " + sched.getSchedulerName() + "<br>"); 
	out.print("Scheduler is shutdown = " + sched.isShutdown() + "<br>"); 
	out.print("Scheduler is started = " + sched.isStarted() + "<br>"); 
	
	
	String act = request.getParameter("act");
	String triggerName = request.getParameter("triggerName");
	String triggerGroup = request.getParameter("triggerGroup");
	if("pause".equals(act)){
		pauseTrigger(triggerName,triggerGroup);
		out.print("------------------"+triggerName+triggerGroup);
	}else if("resume".equals(act)){
		resumeTrigger(triggerName,triggerGroup);
		out.print("------------------"+triggerName+triggerGroup);
	}else if("remove".equals(act)){
		removeTrigger(triggerName,triggerGroup);
		out.print("------------------"+triggerName+triggerGroup);
	}
	
	String[] triggerGroupNames = sched.getTriggerGroupNames();
	if (triggerGroupNames != null && triggerGroupNames.length > 0) {
		for (String trgGrpName : triggerGroupNames) {
			out.print("<br><b>===[Group Name=" + trgGrpName + "]=======================================</b><br>");

			String[] trgNames = sched.getTriggerNames(trgGrpName);
			int i=0;
			for (String trgName : trgNames) {
				out.print("(Trigger-"+i+++")----------------------<br>");
				Trigger trigger = sched.getTrigger(trgName, trgGrpName);
				out.print("&nbsp;&nbsp;Trigger Name=" + trgName + "<br>");
				out.print("&nbsp;&nbsp;Trigger Class=" + trigger.getClass() + "<br>");
				out.print("&nbsp;&nbsp;mayFireAgain()=" + trigger.mayFireAgain() + "<br>");
				out.print("&nbsp;&nbsp;isVolatile()=" + trigger.isVolatile() + "<br>");
				out.print("&nbsp;&nbsp;getNextFireTime()=" + trigger.getNextFireTime() + "<br>");
				
%>
			<a href="${root}/jsp/sysjobs.jsp?act=pause&triggerName=<%=trgName%>&triggerGroup=<%=trgGrpName%>">Pause</a>
			<a href="${root}/jsp/sysjobs.jsp?act=resume&triggerName=<%=trgName%>&triggerGroup=<%=trgGrpName%>">Resume</a>
			<a href="${root}/jsp/sysjobs.jsp?act=remove&triggerName=<%=trgName%>&triggerGroup=<%=trgGrpName%>">Remove</a>
			<br>
			<%
				}
					}

				}
			%>
<br></br>

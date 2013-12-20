<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@include file="/WEB-INF/jsp/jquery-include.jsp"%>
<head>
  <meta charset="utf-8" />
</head>


<body>
<s:actionerror />
<s:actionmessage />
<table cellpadding="0" cellspacing="0">
<tr>
	<td>
	<input border="0" name="node2" src="${root}/images/flownode/0.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/mynewitems.do'">
	</td>
	
	<td><img src="${root}/images/flownode/array-right.gif"/></td>
	
	<s:if test="status_2_pending_approval==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/2-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itemapprove.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/2.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itemapprove.do'"></td>
	</s:else>
	
	<td><img src="${root}/images/flownode/array-right.gif"/></td>
	
	<s:if test="status_5_on_survey==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/3-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onsurvey.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/3.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onsurvey.do'"></td>
	</s:else>	
	<%-- 
	<td><img src="${root}/images/flownode/array-right.gif"/></td>
	<s:if test="status_5_on_survey==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/4-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itemonsurvey.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/4.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itemonsurvey.do'"></td>
	</s:else> --%>
	
	<td><img src="${root}/images/flownode/array-right.gif"/></td>
	<s:if test="status_6_report_pending_approval==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/5-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itemreportapprove.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/5.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itemreportapprove.do'"></td>
	</s:else>
	
	<td><img src="${root}/images/flownode/array-right.gif"/></td>
	<s:if test="status_7_setup==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/1-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onsetup_setup.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/1.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onsetup_setup.do'"></td>
	</s:else>
	
</tr>
<tr>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td><img src="${root}/images/flownode/array-down.gif"/></td>
</tr>
<tr>
	
	<s:if test="status_12_on_pay==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/8-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itempayview.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/8.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itempayview.do'"></td>
	</s:else>
	<td><img src="${root}/images/flownode/array-left.gif"/></td>
	

	<s:if test="status_11_on_finish==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/on_finish-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onfinish.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/on_finish.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onfinish.do'"></td>
	</s:else>
	<td><img src="${root}/images/flownode/array-left.gif"/></td>
	
	<s:if test="status_10_on_accept==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/7-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itemaccept.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/7.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itemaccept.do'"></td>
	</s:else>
	
	<td><img src="${root}/images/flownode/array-left.gif"/></td>
	
	<s:if test="status_9_on_bid==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/6-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onbid.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/6.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onbid.do'"></td>
	</s:else>	
	
	<td><img src="${root}/images/flownode/array-left.gif"/></td>
	
	
	<s:if test="status_7_setup_pending_approve==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/2-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onsetup_approve.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/2.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onsetup_approve.do'"></td>
	</s:else>
	
 <%-- 	
	<td><img src="${root}/images/flownode/array-left.gif"/></td>
	
	<s:if test="status_7_setup==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/1-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onsetup_setup.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/1.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/onsetup_setup.do'"></td>
	</s:else>  --%>
	
</tr>

<tr>
	<td><img src="${root}/images/flownode/array-down.gif"/></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr>
<tr>
	<s:if test="status_13_close==\"0\"">
	<td><input border="0" name="node2" src="${root}/images/flownode/9-bw.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itemfinished.do'"></td>
	</s:if>
	<s:else>
	<td><input border="0" name="node2" src="${root}/images/flownode/9.gif" type="image" width="100" height="100" onclick="location.href='${root}/workflow/itemfinished.do'"></td>
	</s:else>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
	<td></td>
</tr> 
</table>

</body>

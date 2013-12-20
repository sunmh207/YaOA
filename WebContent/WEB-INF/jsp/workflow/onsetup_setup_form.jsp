<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>
<script type="text/javascript">
function insertRecommendBidder(){
	var con = document.getElementById("bidder");
	var br = document.createElement("br");
	var docName =document.createElement("input");
	docName.name = "bidderName";
	docName.type = "text";
	var file = document.createElement("input");
	file.name = "bidderDoc";
	file.type = "file";
	con.appendChild(br);
	con.appendChild(docName);
	con.appendChild(file);
}


function submitSetup() {
	var frm =document.getElementById('bidForm');
	if(frm.useridStr.value==null||frm.useridStr.value==""){
		alert("请指定审批领导！");
		return false;
	}
	if(window.confirm("确定提交?")){
		frm.action="${root}/workflow/onsetup_setupinput!submitSetup.do";  
	 	frm.submit();
	}
}

</script>
</html:pageHead>
<body>
<html:pagetitle title="活动立项"/>

<s:actionerror />
<s:actionmessage />
<s:form id="bidForm" action="onsetup_setupinput!submitSetup.do" method="post"  enctype="multipart/form-data">
<s:hidden name="bid.itemId" value="%{bid.itemId}" />
<s:hidden name="item.id" value="%{item.id}" />
<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
     <tr>
        <td height="24" align="right" class="td_lable">主办部门</td>
        <td class="td_edit">		
			<s:textfield  name="bid.hostDept" value="%{bid.hostDept}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">日期</td>
        <td class="td_edit">		
			<s:textfield  name="bid.applyDate" value="%{bid.applyDate}" cssClass="date" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">项目名称</td>
        <td class="td_edit">		
			<s:textfield  name="bid.bidItemName" value="%{bid.bidItemName}" /> 	 
        </td>
        <td height="24" align="right" class="td_lable">计划投资金额</td>
        <td class="td_edit">		
			<s:textfield  name="bid.planAmount" value="%{bid.planAmount}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">批准文号或立项依据</td>
        <td class="td_edit">		
			<s:textfield  name="bid.refNumber" value="%{bid.refNumber}" /> 	 
        </td>
        <td height="24" align="right" class="td_lable">资金来源</td>
        <td class="td_edit">		
			<s:textfield  name="bid.moneySource" value="%{bid.moneySource}"/> 	 
        </td>
    </tr>

     <tr>
        <td height="24" align="right" class="td_lable">主要工作项目及工作量、产品型号和规格</td>
        <td class="td_edit" colspan="3">		
			<s:textarea name="bid.itemSummary" value="%{bid.itemSummary}"  cols="80" rows="3"/>
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">计划竞标时间</td>
        <td class="td_edit">		
			<s:textfield  name="bid.planBidDate" value="%{bid.planBidDate}"  cssClass="date" /> 	 
        </td>
        <td height="24" align="right" class="td_lable">经办人电话</td>
        <td class="td_edit">		
			<s:textfield  name="bid.agentPersonPhone" value="%{bid.agentPersonPhone}"/> 	 
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">推荐投标单位</td>
        <td class="td_edit" colspan="3">	
        	<s:iterator value="recommendBidderList" status="st">
        		<s:property value='#st.index+1'/>.<s:property value="name"/><br>
        	</s:iterator> 
	        <div id="bidder">
	        	 <s:textfield  name="bidderName"/> 
	        	<s:file name="bidderDoc"/> 
	        	<a href="javascript:insertRecommendBidder()">新增投标单位</a>
	        </div>	
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">议标说明</td>
        <td class="td_edit" colspan="3">	
        <s:file name="bidNegotiation"/>	&nbsp;&nbsp;
        <a href="${root}/oa/download!downloadBidNegotiation.do?item.id=${item.id}">下载议标说明</a>	
        </td>
    </tr>
    <%-- <tr>
        <td height="24" align="right" class="td_lable">主办单位(部门)意见</td>
        <td class="td_edit" colspan="3">		
			<s:property value="%{bid.hostDeptComments}"/>
        </td>
    </tr>  --%>
     <tr>
        <td height="24" align="right" class="td_lable">市场调研报告</td>
        <td class="td_edit" colspan="3">	
        <s:property value="%{item.surveyReportSummary}"/><br>
        <a href="${root}/oa/download!downloadReport.do?item.id=${item.id}">下载调研报告</a>	
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">招标文件</td>
        <td class="td_edit" colspan="3">		
        <s:file name="bidDoc"/>
        	&nbsp;&nbsp;
        <a href="${root}/oa/download!downloadBidDoc.do?item.id=${item.id}">下载招标文件</a>
        </td>
    </tr>
    <tr>
    	<td height="24" align="right" class="td_lable">主管部门审批流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="setupApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr> 
    <tr>
        <td height="24" align="right" class="td_lable">审批领导</td>
        <td class="td_edit" colspan="3">		
			<html:person idName="useridStr"  nameName="userNameStr" height="50"/>		
        </td>
    </tr>
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit" colspan="3">	
      <input type="button" onclick="submitSetup()"  value="提交主办单位领导审核">
       <input type="button" onclick="location.href='${root}/workflow/onsetup_setup.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
    
</s:form>
</body>
</html>

<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<%@include file="/WEB-INF/jsp/jquery-include.jsp"%>
<html:pageHead />
<body>
<center>

<html:pagetitle title="立项表"/>
<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">    
    <tr>
        <td colspan="5" class="td_header">招标</td>
    </tr>
     <tr>
     <td height="24" rowspan="8" class="td_lable">主办部门填写</td>
        <td height="24" align="right" class="td_lable">主办部门</td>
        <td class="td_edit">		
			<s:property  value="%{bid.hostDept}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">日期</td>
        <td class="td_edit">		
			<s:property  value="%{bid.applyDate}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">项目名称</td>
        <td class="td_edit">		
			<s:property  value="%{bid.bidItemName}" /> 	 
        </td>
        <td height="24" align="right" class="td_lable">计划投资金额</td>
        <td class="td_edit">		
			<s:property  value="%{bid.planAmount}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">批准文号或立项依据</td>
        <td class="td_edit">		
			<s:property  value="%{bid.refNumber}" /> 	 
        </td>
        <td height="24" align="right" class="td_lable">资金来源</td>
        <td class="td_edit">		
			<s:property  value="%{bid.moneySource}"/> 	 
        </td>
    </tr>

     <tr>
        <td height="24" align="right" class="td_lable">主要工作项目及工作量、产品型号和规格</td>
        <td class="td_edit" colspan="3">		
			<s:property  value="%{bid.itemSummary}" />
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">计划竞标时间</td>
        <td class="td_edit">		
			<s:property value="%{bid.planBidDate}"  /> 	 
        </td>
        <td height="24" align="right" class="td_lable">经办人电话</td>
        <td class="td_edit">		
			<s:property value="%{bid.agentPersonPhone}"/> 	 
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">主办部门负责人</td>
        <td class="td_edit" colspan="3">		
			<s:property value="%{bid.responsiblePerson}"  /> 	 
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">推荐投标单位</td>
        <td class="td_edit" colspan="3">	
        	<s:iterator value="recommendBidderList" status="st">
        	<s:property value='#st.index+1'/>.<s:property value="name"/>  <a href="${root}/oa/download!downloadRecommendBidderDoc.do?recommendBidder.id=<s:property value="id"/>">下载</a><br>
        	</s:iterator> 
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">主办单位(部门)意见</td>
        <td class="td_edit" colspan="3">		
			<s:property  value="%{bid.hostDeptComments}" />
        </td>
    </tr>
    <tr>
    	<td height="24" rowspan="7" class="td_lable">招标管理部门填写</td>
    	<td height="24" align="right" class="td_lable">主管部门审批流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="setupApproveList" status="st">
				[<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr> 
    <tr>
         <td height="24" align="right" class="td_lable">竞标时间</td>
        <td class="td_edit">		
			<s:property value="%{bid.bidDate}"  /> 	 
        </td>
        <td height="24" align="right" class="td_lable">招竞标形式</td>
        <td class="td_edit">		
			<s:property   value="%{bid.bidType}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">经办人</td>
        <td class="td_edit">		
			<s:property  value="%{bid.zhaobiaoDeptAgentPerson}"/> 	 
        </td>
         <td height="24" align="right" class="td_lable">招标管理部门负责人</td>
        <td class="td_edit">		
			<s:property   value="%{bid.zhaobiaoDeptResponsiblePerson}" /> 	 
        </td>
    </tr>
    <tr>
    	<td height="24" align="right" class="td_lable">招标管理部门审批流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="zbdeptApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">招标管理部门意见</td>
        <td class="td_edit" colspan="3">		
			<s:property  value="%{bid.zhaobiaoDeptComments}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">负责人</td>
        <td class="td_edit" colspan="3">		
			<s:property value="%{bid.zhaobiaoDeptResponsiblePerson}"  /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">备注</td>
        <td class="td_edit" colspan="3">	
        <a href="${root}/oa/download!downloadReport.do?item.id=${item.id}">下载调研报告</a>	
        <a href="${root}/oa/download!downloadBidDoc.do?item.id=${item.id}">下载招标文件</a>	
        </td>
    </tr>
     <tr>
        <td height="24" rowspan="3" class="td_lable">纪检监察机关填写</td>
    	<td height="24" align="right" class="td_lable">纪检监察机关审批流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="jjwApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">纪检监察机关意见</td>
        <td class="td_edit" colspan="3">		
			<s:property  value="%{bid.jjwDeptComments}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">负责人</td>
        <td class="td_edit" colspan="3">		
			<s:property value="%{bid.jjwDeptResponsiblePerson}"  /> 	 
        </td>
    </tr>
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit" colspan="3">	
       <input type="button" onclick="location.href='${root}/oa/exportword!exportSetup.do?item.id=<s:property value="bid.itemId"/>'" value="导出Word"> 	
       <input type="button" onclick="location.href='${root}/oa/item.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
</center>
</body>
</html>
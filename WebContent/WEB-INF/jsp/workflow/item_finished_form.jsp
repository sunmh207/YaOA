<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<s:actionerror />
<s:actionmessage />
<html:pagetitle title="项目详细信息"/>

<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="" method="post"  enctype="multipart/form-data">
<s:hidden name="bid.itemId" value="%{bid.itemId}" />
<s:hidden name="item.id" value="%{item.id}" />
<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
     <tr>
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
        <td height="24" align="right" class="td_lable">推荐投标单位</td>
        <td class="td_edit" colspan="3">	
        	<s:iterator value="recommendBidderList" status="st">
        	<s:property value='#st.index+1'/>.<s:property value="name"/><br>
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
        <td height="24" align="right" class="td_lable">竞标时间</td>
        <td class="td_edit" colspan="3">		
			<s:property  value="%{bid.bidDate}"  /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">招竞标形式</td>
        <td class="td_edit">		
			<s:property   value="%{bid.bidType}" /> 	 
        </td>
        <td height="24" align="right" class="td_lable">经办人</td>
        <td class="td_edit">		
			<s:property  value="%{bid.zhaobiaoDeptAgentPerson}"/> 	 
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
        <a href="${root}/workflow/itemfinishedinput!downloadReport.do?item.id=${item.id}">下载调研报告</a>	
        <a href="${root}/workflow/itemfinishedinput!downloadBidDoc.do?item.id=${item.id}">下载招标文件</a>	
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
        <td height="24" align="right" class="td_lable">会议记录</td>
        <td class="td_edit" colspan="3">	
			 <a href="${root}/workflow/itemfinishedinput!downloadReport.do?item.id=${item.id}">2013-09-12研讨会</a>	
			 <a href="${root}/workflow/itemfinishedinput!downloadReport.do?item.id=${item.id}">2013-09-15研讨会</a>	
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">中标通知书</td>
        <td class="td_edit" colspan="3">	
			<a href="${root}/workflow/itemfinishedinput!downloadReport.do?item.id=${item.id}">下载</a>	
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">合同</td>
        <td class="td_edit" colspan="3">	
			<a href="${root}/workflow/itemfinishedinput!downloadReport.do?item.id=${item.id}">下载</a>	
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">验收记录</td>
        <td class="td_edit">		
			<a href="${root}/workflow/itemfinishedinput!downloadAcceptInfo.do?item.id=${item.id}">下载</a>	 
        </td>
        <td height="24" align="right" class="td_lable">付款单位</td>
        <td class="td_edit">		
			<s:property  value="%{bid.payCompany}" /> 	 
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">验收付款日期</td>
        <td class="td_edit">		
			<s:property  value="%{bid.payDate}" />  
        </td>
        <td height="24" align="right" class="td_lable">付款金额</td>
        <td class="td_edit">		
			<s:property  value="%{bid.payAmount}" /> 	 
        </td>
    </tr>
    <tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit" colspan="3">	
       <input type="button" onclick="location.href='${root}/workflow/itemfinished.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>
</table>
</s:form>
<br>
</body>
</html>
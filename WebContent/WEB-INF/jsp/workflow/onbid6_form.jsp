<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>
<script type="text/javascript">
function submitBid() {
	var frm =document.getElementById('bidForm');
	frm.action="${root}/workflow/onbid6!submitBid.do";  
 	frm.submit();
}
function finishBid() {
	if(window.confirm("结束招标后数据将不能修改。请确保数据正确，并且已经保存。确定要结束招标?")){
		var frm =document.getElementById('bidForm');
		frm.action="${root}/workflow/onbid6!finishBid.do";  
	 	frm.submit();
	}
}
</script>
</html:pageHead>
<body>
<html:pagetitle title="项目招标->开标会记录"/>

<s:actionerror />
<s:actionmessage />
<s:form id="bidForm" action="onbid6!submitDoc.do" method="post"  enctype="multipart/form-data">
<s:hidden name="bid.itemId" value="%{bid.itemId}" />
<s:hidden name="item.id" value="%{item.id}" />
<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
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
			<s:property  value="%{bid.responsiblePerson}" /> 	 
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">推荐投标单位</td>
        <td class="td_edit" colspan="3">	
        	<s:iterator value="recommendBidderList" status="st">
        	<s:property value='#st.index+1'/>.<s:property value="name"/>
        	<a  href="${root}/workflow/onbid6!recommendBidderInput.do?item.id=<s:property value="item.id"/>&recommendBidder.id=<s:property value="id"/>">编辑</a>
        		<a href="${root}/oa/download!downloadRecommendBidderDoc.do?recommendBidder.id=<s:property value="id"/>">下载附件</a>&nbsp;
        		
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
      <td height="24" rowspan="5" class="td_lable">招标管理部门填写</td>
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
      <td height="24" rowspan="2" class="td_lable">纪检监察机关填写</td>
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
       <td height="24" rowspan="3" class="td_lable"> </td>
        <td height="24" align="right" class="td_lable">开标会议记录</td>
        <td class="td_edit" colspan="3">	
        <a href="${root}/workflow/onbid6!editBidMeetingRecord.do?item.id=<s:property value="item.id"/>">增加会议记录</a>&nbsp;&nbsp;<br>
        	<s:iterator value="bidMeetingRecordList" status="st">
        		<s:property value='#st.index+1'/>.日期<s:property value="date"/>&nbsp;&nbsp;
	        	<a href="${root}/workflow/onbid6!editBidMeetingRecord.do?item.id=<s:property value="item.id"/>&bidMeetingRecord.id=<s:property value="id"/>">编辑</a>&nbsp;&nbsp;
	        	<a href="${root}/oa/download!downloadBidMeetingRecordNote.do?bidMeetingRecord.id=<s:property value="id"/>">下载附件</a>&nbsp;
	        	<a href="${root}/workflow/onbid6!deleteBidMeetingRecord.do?item.id=<s:property value="item.id"/>&bidMeetingRecord.id=<s:property value="id"/>">删除</a>&nbsp;&nbsp;<br>
        	</s:iterator>  
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">中标通知书</td>
        <td class="td_edit" colspan="3">	
			<s:file name="bidWinInfo"/>
			<s:if test="bid.bidWinInfo!=null">	 
			 <a href="${root}/oa/download!downloadBidWinInfo.do?item.id=${item.id}">下载中标通知书</a>
			 </s:if>
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">合同</td>
        <td class="td_edit" colspan="3">	
			<s:file name="contract"/>	
			<s:if test="bid.contract!=null">	 
			 <a href="${root}/oa/download!downloadBidContract.do?item.id=${item.id}">下载合同</a>
			 </s:if> 
        </td>
    </tr>
    <tr>
      <td class="td_edit" colspan="5" align="center">	
       <input type="button" onclick="submitBid()"  value="保存">
       <input type="button" onclick="finishBid()"  value="结束招标">
        <input type="button" onclick="location.href='${root}/workflow/onbid.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>
</table>
    
</s:form>
</body>
</html>
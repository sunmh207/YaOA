<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead />
<body>
<s:actionerror />
<s:actionmessage />
<html:pagetitle title="项目详细信息"/>
<input type="button" value="返回" onclick="window.history.go(-1);"> 
<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="itemdetail" method="post"  enctype="multipart/form-data">
<s:hidden name="bid.itemId" value="%{bid.itemId}" />
<s:hidden name="item.id" value="%{item.id}" />
<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
 	<tr>
        <td colspan="4" class="td_header">项目申请/审批</td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">项目申请名</td>
        <td class="td_edit">		
			<s:property  value="%{item.itemName}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">申请人</td>
        <td class="td_edit">		
			<s:property  value="%{item.requesterName}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">项目日期</td>
        <td class="td_edit">		
			<s:property  value="%{item.applyDate}" /> 		 
        </td>
        <td height="24" align="right" class="td_lable">状态</td>
        <td class="td_edit">		
			<s:property  value="%{item.statusTXT}" /> 	 
        </td>
    </tr>
    <tr>
    	<td height="24" align="right" class="td_lable">分类</td>
        <td colspan="3" class="td_edit"><s:property  value="%{item.itemType}" /> </td>
    </tr>
    <tr>
    	<td height="24" align="right" class="td_lable">申请内容</td>
        <td colspan="3" class="td_edit"><s:property escapeHtml="false"  value="%{item.description}" /> </td>
    </tr>
    <tr>
    	<td height="24" align="right" class="td_lable">项目审批流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="itemApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
 </table>  
     <br>
 <table  width="99%" class="table" cellpadding="0" cellspacing="0" align="center">  
    <tr>
        <td colspan="4" class="td_header">调研</td>
    </tr>
    <tr>
    	<td align="right" class="td_lable">调研通知标题</td>
        <td  class="td_edit"><s:property value="%{item.surveyInfoTitle}" /> </td>
    	<td align="right" class="td_lable">通知对象</td>
        <td class="td_edit"><s:property value="%{item.surveyInfoReceiverNames}" /> </td>
    </tr>
    <tr>
    	<td align="right" class="td_lable">通知内容</td>
        <td colspan="3" class="td_edit"><s:property  escapeHtml="false"  value="%{item.surveyInfoBody}" /> </td>
    </tr>
     <tr>
    	<td height="24" align="right" class="td_lable">通知签收流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="mktSignApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
     <tr>
    	<td align="right" class="td_lable">调研报告</td>
        <td colspan="3" class="td_edit"><a href="${root}/oa/download!downloadReport.do?item.id=${item.id}">下载调研报告</a></td>
    </tr>
      <tr>
    	<td height="24" align="right" class="td_lable">报告确认流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="mktConfirmApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
    <tr>
    	<td height="24" align="right" class="td_lable">报告审批流程</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="rptApproveList" status="st">
				[<s:property value='#st.index+1'/>.<s:property value="operationTime"/>] 
				审批人:<s:property  value="approverName"/> &nbsp;&nbsp;
				审批结果:<s:property  value="statusTXT"/>&nbsp;&nbsp; 
				意见:<s:property  value="comments"/> <br>
        </s:iterator>
        </td>
    </tr>
</table>
<br>
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
 </table>
 <br>
 <table  width="99%" class="table" cellpadding="0" cellspacing="0" align="center">   
    <tr>
        <td height="24" align="right" class="td_lable">招标部门会议记录上传人员</td>
        <td class="td_edit" colspan="3">		
			<s:property value="%{bid.zbdeptMeetingRecorderName}"  /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">会议记录</td>
        <td class="td_edit" colspan="3">	
			<s:iterator value="bidMeetingRecordList" status="st">
        		<table width="99%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
				<tr>
					<td class="td_header">日期</td>
					<td class="td_header">地点</td>
					<td class="td_header">招标形式</td>
					<td class="td_header">主持人</td>
					<td class="td_header">评委</td>
					<td class="td_header">纪委</td>
					<td class="td_header">评标办法</td>
					<td class="td_header">标底</td>
					<td class="td_header">评委廉政承诺书</td>
					<td class="td_header">中标单位</td>
					<td class="td_header">中标金额</td>
					<td class="td_header">会议记录</td>
				</tr>
				<tr>
					<td align="center" class="td_body"><s:property value='date'/> </td>
					<td align="center" class="td_body"><s:property value='place'/> </td>
					<td align="center" class="td_body"><s:property value='bidType'/> </td>
					<td align="center" class="td_body"><s:property value='host'/> </td>
					<td align="center" class="td_body"><s:property value='judge'/> </td>
					<td align="center" class="td_body"><s:property value='jjwJudge'/> </td>
					<td align="center" class="td_body"> <a href="${root}/oa/download!downloadMeetingRecord_JudgeSummary.do?bidMeetingRecord.id=<s:property value="id"/>">下载附件</a> </td>
					<td align="center" class="td_body"><s:property value='bidBase'/> </td>
					<td align="center" class="td_body"><a href="${root}/oa/download!downloadMeetingRecord_JudgeChengnuoshu.do?bidMeetingRecord.id=<s:property value="id"/>">下载附件</a><br></td>
					<td align="center" class="td_body"><s:property value='winner'/> </td>
					<td align="center" class="td_body"><s:property value='winAmount'/> </td>
					<td align="center" class="td_body"><a href="${root}/oa/download!downloadBidMeetingRecordNote.do?bidMeetingRecord.id=<s:property value="id"/>">下载附件</a></td>
				</tr>
				</table>
					
	        	<%-- <a href="${root}/oa/download!downloadBidMeetingRecordNote.do?bidMeetingRecord.id=<s:property value="id"/>">下载附件</a><br> --%>
        	</s:iterator> 
        </td>
    </tr> 
    <tr>
        <td height="24" align="right" class="td_lable">推荐投标单位</td>
        <td class="td_edit" colspan="3">	
			<table width="100%" class="table" align="center" cellpadding="1" cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td class="td_header" rowspan="2">投标书编号</td>
				<td class="td_header" rowspan="2">投标单位</td>
				<td class="td_header" rowspan="2">营业执照注册号</td>
				<td class="td_header" rowspan="2">单位法人</td>
				<td class="td_header" rowspan="2">委托人</td>
				<td class="td_header" rowspan="2">身份证号</td>
				<td class="td_header" colspan="4">投标单位报价</td>
			</tr>
			<tr>
				<td class="td_header">第一次</td>
				<td class="td_header">第二次</td>
				<td class="td_header">第三次</td>
				<td class="td_header">得分</td>
			</tr>
			<s:iterator value="recommendBidderList" status="st">
				<tr>
				<td class='td_body'><s:property value="%{recommendBidderList[#st.index].biaoshubianhao}"/></td>
				<td class='td_body'><s:property value="%{recommendBidderList[#st.index].name}"/></td>
				<td class='td_body'><s:property  value="%{recommendBidderList[#st.index].yingyezhizhao}"/></td>
				<td class='td_body'><s:property  value="%{recommendBidderList[#st.index].farendaibiaoName}"/></td>
				<td class='td_body'><s:property  value="%{recommendBidderList[#st.index].weituorenName}"/></td>
				<td class='td_body'><s:property value="%{recommendBidderList[#st.index].weituorenId}" /></td>
				<td class='td_body'><s:property  value="%{recommendBidderList[#st.index].baojia1}" /></td>
				<td class='td_body'><s:property  value="%{recommendBidderList[#st.index].baojia2}" /></td>
				<td class='td_body'><s:property  value="%{recommendBidderList[#st.index].baojia3}" /></td>
				<td class='td_body'><s:property  value="%{recommendBidderList[#st.index].score}" /></td>
				</tr>
			</s:iterator>
			</table>			

        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">议标说明</td>
        <td class="td_edit" colspan="3">	
			<a href="${root}/oa/download!downloadBidNegotiation.do?item.id=${item.id}&bid.itemId=${bi.itemId}">下载</a>	
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">中标通知书</td>
        <td class="td_edit" colspan="3">	
			<a href="${root}/oa/download!downloadBidWinInfo.do?item.id=${item.id}&bid.itemId=${bi.itemId}">下载</a>	
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">合同</td>
        <td class="td_edit" colspan="3">	
			<a href="${root}/oa/download!downloadBidContract.do?item.id=${item.id}&&bid.itemId=${bi.itemId}">下载</a>	
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">验收记录</td>
        <td class="td_edit" colspan="3">		
			<table width="99%" class="table" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td class="td_header">序号</td>
				<td class="td_header">验收日期</td>
				<td class="td_header">操作</td>
			</tr>
			<s:iterator value="itemAcceptList" status="st">
				<tr>
				<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
					<td align="left" class='td_body'> <s:property  value="acceptDate"/></td>
					<td align="center" class='td_body'>
						<a href="${root}/oa/download!downloadItemAcceptInfo.do?item.id=<s:property  value="itemId"/>&itemAccept.id=<s:property value="id"/>">下载验收记录</a>&nbsp;
					</td>
				</tr>
			</s:iterator>
			</table>	 
        </td>        
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">结项</td>
        <td colspan="3" class="td_edit">
        <s:iterator value="itemFinishtList" status="st">
        <table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
		    <tr>
		        <td height="24" align="right" class="td_lable">结项名称</td>
		        <td class="td_edit" colspan="3">		
					<s:property value="%{finishItemName}"/> 	 
		        </td>
		        <td height="24" align="right" class="td_lable">状态</td>
		        <td class="td_edit">		
					<s:property  value="%{statusTXT}" /> 	 
		        </td>
		    </tr>
		    <tr>
		    	<td height="24" align="right" class="td_lable">投资金额</td>
		        <td class="td_edit">		
					<s:property  value="%{investAmount}" /> 	 
		        </td>
		        <td height="24" align="right" class="td_lable">标   底</td>
		        <td class="td_edit">		
					<s:property value="%{biaodiAmount}"/> 	 
		        </td>
		        <td height="24" align="right" class="td_lable">中 标 价</td>
		        <td class="td_edit">		
					<s:property  value="%{zhongbiaoPrice}" /> 	 
		        </td>
		       
		    </tr> 
		    <tr>
		        <td height="24" align="right" class="td_lable">中标单位</td>
		        <td class="td_edit">		
					<s:property  value="%{zhongbiaoCompany}"/> 	 
		        </td>
		        <td height="24" align="right" class="td_lable">招竞标时间</td>
		        <td class="td_edit">		
					<s:property value="%{jingbiaoDate}" /> 	 
		        </td>
		        <td height="24" align="right" class="td_lable">参加招 标人员</td>
		        <td class="td_edit" >		
					<s:property  value="%{bidParticipants}" />
		        </td>
		    </tr>
		    <tr>
		        <td height="24" align="right" class="td_lable">完成工期或购销时间</td>
		        <td class="td_edit">		
					<s:property value="%{finishDate}" /> 	 
		        </td>
		        <td height="24" align="right" class="td_lable">合同签订时间</td>
		        <td class="td_edit">		
					<s:property   value="%{contractSignDate}"  /> 	 
		        </td>
		         <td height="24" align="right" class="td_lable">主办部门负责人</td>
		        <td class="td_edit">		
					<s:property  value="%{sponsorDeptRespPers}"/> 	 
		        </td>
		    </tr> 
		    <tr>
		        <td height="24" align="right" class="td_lable">经办人</td>
		        <td class="td_edit" colspan="5">		
					<s:property value="%{jingbanPers}" /> 	 
		        </td>
		    </tr>
		    <tr>
		        <td height="24" align="right" class="td_lable">结项主要内容</td>
		        <td class="td_edit" colspan="5">		
					<s:property value="%{finishSummary}" />
		        </td>
		    </tr>
		    <tr>
		        <td height="24" align="right" class="td_lable">主管领导审批意见</td>
		        <td class="td_edit" colspan="5">		
					<s:property value="%{leadComments}" />
		        </td>
		    </tr>
		    <tr>
		        <td height="24" align="right" class="td_lable">纪检监察机关意见</td>
		        <td class="td_edit" colspan="5">		
					<s:property value="%{jjwComments}" />
		        </td>
		    </tr>
		    <tr>
		        <td height="24" align="right" class="td_lable">备注</td>
		        <td class="td_edit" colspan="5">		
					<s:property  value="%{finishNote}" />
		        </td>
		    </tr> 
		</table>
		<br>
        </s:iterator>
        </td>
    </tr>
    
     <tr>
        <td height="24" align="right" class="td_lable">付款记录</td>
        <td class="td_edit" colspan="3">		
			<table width="99%" class="table" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td class="td_header">序号</td>
				<td class="td_header">付款单位</td>
				<td class="td_header">付款日期</td>
				<td class="td_header">付款金额</td>
			</tr>
			<s:iterator value="itemFinishtList" status="st">
				<tr>
				<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
					<td align="left" class='td_body'> <s:property  value="payCompany"/></td>
					<td align="left" class='td_body'> <s:property  value="payDate"/></td>
					<td align="left" class='td_body'> <s:property  value="payAmount"/></td>
				</tr>
			</s:iterator>
			</table>
        </td>
    </tr>
    <tr>
        <td colspan="4" class="td_header">相关报表</td>
    </tr>
     <tr>       
        <td class="td_edit" colspan="4">	
        	<a href="${root}/oa/itemdetail!biddersReport.do?item.id=<s:property  value="item.id"/>">竞标监督登记表</a>	
        </td>
    </tr>
</table>
</s:form>
<br>
</body>
</html>
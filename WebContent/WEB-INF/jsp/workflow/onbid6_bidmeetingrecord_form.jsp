<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>

</html:pageHead>
<body>
<html:pagetitle title="项目招标->招标文件->项目的开标会记录"/>

<s:actionerror />
<s:actionmessage />
<s:form id="bidForm" action="onbid6!saveBidMeetingRecord.do" method="post"  enctype="multipart/form-data">
<s:hidden name="bid.itemId" value="%{bid.itemId}" />
<s:hidden name="item.id" value="%{item.id}" />
<s:hidden name="bidMeetingRecord.id" value="%{bidMeetingRecord.id}" />
<table width="100%" class="table" cellpadding="0" cellspacing="0" align="center">
    
    <tr>
        <td height="24" align="right" class="td_lable">会议标题</td>
        <td class="td_edit" colspan=3"">		
			关于<<<s:property value="item.itemName"/>>>项目开标会记录
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">日期</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.date" value="%{bidMeetingRecord.date}" cssClass="date"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">地点</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.place" value="%{bidMeetingRecord.place}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">招标形式</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.bidType" value="%{bidMeetingRecord.bidType}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">主持人</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.host" value="%{bidMeetingRecord.host}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">评委</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.judge" value="%{bidMeetingRecord.judge}" size="60"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">纪委</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.jjwJudge" value="%{bidMeetingRecord.jjwJudge}"/> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">评标办法</td>
        <td class="td_edit" colspan="3">	
        <s:file name="judgeSummary"/>	
        <a href="${root}/oa/download!downloadMeetingRecord_JudgeSummary.do?bidMeetingRecord.id=<s:property value="bidMeetingRecord.id"/>">下载附件</a><br>
			<%-- <s:textarea  name="bidMeetingRecord.judgeSummary" value="%{bidMeetingRecord.judgeSummary}" cols="80" rows="3"/> --%> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">标底</td>
        <td class="td_edit" colspan="3">		
			<s:textfield  name="bidMeetingRecord.bidBase" value="%{bidMeetingRecord.bidBase}" /> 	 
        </td>
    </tr>
      <tr>
        <td height="24" align="right" class="td_lable">评委廉政承诺书 </td>
        <td class="td_edit" colspan="3">	
         <s:file name="judgeChengnuoshu"/>	
        <a href="${root}/oa/download!downloadMeetingRecord_JudgeChengnuoshu.do?bidMeetingRecord.id=<s:property value="bidMeetingRecord.id"/>">下载附件</a><br>
			
			<%-- <s:textarea  name="bidMeetingRecord.judgeChengnuoshu" value="%{bidMeetingRecord.judgeChengnuoshu}" cols="80" rows="3"/> --%> 	 
        </td>
    </tr>
    </table>
    
  
	<%-- <tr>
        <td height="24" align="right" class="td_lable">投标单位</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.bidderName" value="%{bidMeetingRecord.bidderName}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">投标书编号</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.bidderBiaoshuNo" value="%{bidMeetingRecord.bidderBiaoshuNo}" /> 	 
        </td>
    </tr>
	<tr>
        <td height="24" align="right" class="td_lable">单位法人</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.bidderFarendaibiaoName" value="%{bidMeetingRecord.bidderFarendaibiaoName}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">委托人</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.bidderWeituorenName" value="%{bidMeetingRecord.bidderWeituorenName}" /> 	 
        </td>
    </tr>
	<tr>
        <td height="24" align="right" class="td_lable">委托人身份证号</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.bidderWeituorenId" value="%{bidMeetingRecord.bidderWeituorenId}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">投标单位报价</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.bidderBaojia" value="%{bidMeetingRecord.bidderBaojia}" /> 	 
        </td>
    </tr> --%>
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
		<td class='td_body'><s:textfield name="recommendBidderList[%{#st.index}].biaoshubianhao" value="%{recommendBidderList[#st.index].biaoshubianhao}"/></td>
		<td class='td_body'><s:textfield name="recommendBidderList[%{#st.index}].name" value="%{recommendBidderList[#st.index].name}"/></td>
		<td class='td_body'><s:textfield name="recommendBidderList[%{#st.index}].yingyezhizhao" value="%{recommendBidderList[#st.index].yingyezhizhao}"/></td>
		<td class='td_body'><s:textfield name="recommendBidderList[%{#st.index}].farendaibiaoName" value="%{recommendBidderList[#st.index].farendaibiaoName}"/></td>
		<td class='td_body'><s:textfield name="recommendBidderList[%{#st.index}].weituorenName" value="%{recommendBidderList[#st.index].weituorenName}"/></td>
		<td class='td_body'><s:textfield name="recommendBidderList[%{#st.index}].weituorenId" value="%{recommendBidderList[#st.index].weituorenId}" size="5"/></td>
		<td class='td_body'><s:textfield name="recommendBidderList[%{#st.index}].baojia1" value="%{recommendBidderList[#st.index].baojia1}" size="5"/></td>
		<td class='td_body'><s:textfield name="recommendBidderList[%{#st.index}].baojia2" value="%{recommendBidderList[#st.index].baojia2}" size="5"/></td>
		<td class='td_body'><s:textfield name="recommendBidderList[%{#st.index}].baojia3" value="%{recommendBidderList[#st.index].baojia3}" size="5"/></td>
		<td class='td_body'><s:textfield name="recommendBidderList[%{#st.index}].score" value="%{recommendBidderList[#st.index].score}" size="5"/></td>
		</tr>
	</s:iterator>
	
    <tr>
        <td height="24" align="right" class="td_lable">附件</td>
        <td class="td_edit" colspan="3">	
        	<s:file name="meetingDoc"/>
        	<a href="${root}/oa/download!downloadBidMeetingRecordNote.do?bidMeetingRecord.id=<s:property value="bidMeetingRecord.id"/>">下载附件</a><br>
        </td>
    </tr>
     <tr>
        <td height="24" align="right" class="td_lable">中标单位</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.winner" value="%{bidMeetingRecord.winner}" /> 	 
        </td>
     <tr>
        <td height="24" align="right" class="td_lable">中标金额</td>
        <td class="td_edit">		
			<s:textfield  name="bidMeetingRecord.winAmount" value="%{bidMeetingRecord.winAmount}" /> 	 
        </td>
    </tr>
	<tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit" colspan="3">	
      <input type="submit"  value="保存">
       <input type="button" onclick="location.href='${root}/workflow/onbid6!input.do?item.id=<s:property value="item.id"/>'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>	
</table>
    
</s:form>
</body>
</html>
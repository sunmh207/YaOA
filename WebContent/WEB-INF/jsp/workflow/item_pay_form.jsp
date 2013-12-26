﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>
<link rel="stylesheet" href="${root}/css/redmond/jquery-ui-1.10.3.custom.css" />
<link rel="stylesheet" href="${root}/css/style.css" />

<script type="text/javascript" src="${root}/js/jquery-1.9.1.js"></script>  
<script type="text/javascript" src="${root}/js/jquery-ui-1.10.3.custom.js"></script> 
<script type="text/javascript" src="${root}/js/jtmobile.js"></script>

<script>
	function save() {
		if (confirm("确定要付款？")) {
			var frm =document.getElementById('itemForm');
			var finishId=document.getElementById('itemForm_itemFinish_id');
			
			if(finishId.value==null||finishId.value==""){
				alert("结项名称不能为空！");
				return false;
			}
			
			var payComany=document.getElementById('itemForm_itemFinish_payCompany');
			if(payComany.value==null||payComany.value==""){
				alert("付款单位不能为空！");
				return false;
			}
			var payDate=document.getElementById('itemForm_itemFinish_payDate');
			if(payDate.value==null||payDate.value==""){
				alert("付款日期不能为空！");
				return false;
			}
			
			frm.action="${root}/workflow/itempayinput!save.do";  
		 	frm.submit();
		}
	}
	function finishPay() {
		if (confirm("项目结束后数据将不能修改，请确保所有付款都已填写并保存，确定要结束项目？")) {
			var frm =document.getElementById('itemForm');
			frm.action="${root}/workflow/itempayinput!finishPay.do";  
		 	frm.submit();
		}
	}
</script>

	<script type="text/javascript">
	 $(function() {
		    $( "#tabs" ).tabs();
	});
	</script>
</html:pageHead>
<body>
<s:actionerror />
<s:actionmessage />
<s:form id="itemForm" action="" method="post" >
<s:hidden name="item.id" value="%{item.id}"/>
<div id="tabs">
  <ul>
    <li><a href="#tabs-1">付款信息</a></li>
  </ul>
  <div id="tabs-1">
    <p>
    <table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td height="24" align="right" class="td_lable">结项名称</td>
        <td class="td_edit">		
        <s:select  name="itemFinish.id" list="paymentMap"/>        
		<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>">项目详细信息</a>&nbsp;				 
        </td>
    </tr>

    <tr>
        <td height="24" align="right" class="td_lable">付款单位</td>
        <td class="td_edit" colspan="3">	
        <s:textfield name="itemFinish.payCompany" value="%{itemFinish.payCompany}" />
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">付款日期</td>
        <td class="td_edit" colspan="3">	
        <s:textfield name="itemFinish.payDate" value="%{itemFinish.payDate}" cssClass="date"/>
        </td>
    </tr>   
    <tr>
        <td height="24" align="right" class="td_lable">付款金额</td>
        <td class="td_edit" colspan="3">	
        <s:textfield name="itemFinish.payAmount" value="%{itemFinish.payAmount}"/>
        </td>
    </tr>   
     <tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit" colspan="3">	
       <input type="button" onclick="save()"  value="保存付款">
       <input type="button" onclick="finishPay()"  value="结束项目">
        <input type="button" onclick="location.href='${root}/workflow/itempay.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>
	</table> 
	
	<table width="99%" class="table" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
	
	<tr>
		<td class="td_header">序号</td>
		<td class="td_header">结项名称</td>
		<td class="td_header">中标单位</td>
		<td class="td_header">中标价</td>
		<td class="td_header">完成工期或购销时间</td>
		<td class="td_header">付款单位</td>
		<td class="td_header">付款日期</td>
		<td class="td_header">付款金额</td>
		<td class="td_header">状态</td>
	</tr>
	<s:iterator value="itemPaymentList" status="st">
		<tr>
		<td align="center" class="td_header"><s:property value='#st.index+1'/> </td>
		<td align="left" class='td_body'><s:property value="finishItemName"/></td>
			<td align="left" class='td_body'><s:property value="zhongbiaoCompany"/></td>
			<td align="left" class='td_body'><s:property value="zhongbiaoPrice"/></td>
			<td align="left" class='td_body'><s:property value="finishDate"/></td>
			<td align="left" class='td_body'> <s:property  value="payCompany"/></td>
			<td align="left" class='td_body'> <s:property  value="payDate"/></td>
			<td align="left" class='td_body'> <s:property  value="payAmount"/></td>
			<td align="left" class='td_body'><s:property value="item.statusTXT"/>-><s:property value="statusTXT"/></td>
		</tr>
	</s:iterator>
	</table>
	</p>
  </div>
  
</div>
    
</s:form>
</body>
</html>
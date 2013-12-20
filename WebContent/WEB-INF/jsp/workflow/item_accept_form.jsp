﻿<%@ page contentType="text/html; charset=utf-8" %>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>
<link rel="stylesheet" href="${root}/css/redmond/jquery-ui-1.10.3.custom.css" />
<link rel="stylesheet" href="${root}/css/style.css" />

<script type="text/javascript" src="${root}/js/jquery-1.9.1.js"></script>  
<script type="text/javascript" src="${root}/js/jquery-ui-1.10.3.custom.js"></script> 
<script type="text/javascript" src="${root}/js/jtmobile.js"></script>

<script>
	function uploadAcceptInfo() {
		var frm =document.getElementById('itemForm');
		frm.action="${root}/workflow/itemacceptinput!uploadAcceptInfo.do";  
	 	frm.submit();
	}
	function finishAccept() {
		if(window.confirm("验收完成后，本页面将不再显示本项目，您可以到查询功能查看本项目，确定完成验收?")){
		var frm =document.getElementById('itemForm');
		frm.action="${root}/workflow/itemacceptinput!finishAccept.do";  
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
<s:form id="itemForm" action="" method="post" enctype="multipart/form-data">
<s:hidden name="item.id" value="%{item.id}" />
<s:hidden name="bid.itemId" value="%{bid.itemId}" />
<div id="tabs">
  <ul>
    <li><a href="#tabs-1">验收报告</a></li>
  </ul>
  <div id="tabs-1">
    <p>
    <table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    <tr>
        <td height="24" align="right" class="td_lable">项目名称</td>
        <td class="td_edit">		
			<s:property  value="%{item.itemName}" /> 	&nbsp;&nbsp;
			<a href="${root}/oa/itemdetail!view.do?item.id=<s:property value="item.id"/>">项目详细信息</a>&nbsp;		 
        </td>
    </tr>
 	<tr>
        <td height="24" align="right" class="td_lable">验收日期</td>
        <td class="td_edit">		
			<s:textfield name="itemAccept.acceptDate"  value="%{itemAccept.acceptDate}" cssClass="date"/> 			 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">验收报告</td>
        <td class="td_edit" colspan="3">	
			<s:file name="itemAcceptInfo"/>	
        </td>
    </tr>
     <tr>
      <td  height="24" align="right" class="td_lable"></td>
      <td class="td_edit" colspan="3">	
       <input type="button" onclick="uploadAcceptInfo()"  value="上传报告">
       <input type="button" onclick="finishAccept()"  value="验收完毕">
        <input type="button" onclick="location.href='${root}/workflow/itemaccept.do'" value="返回">
      </td>
      <td>&nbsp;</td>
    </tr>
	</table> 
	
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
				<a href="${root}/workflow/itemacceptinput!delete.do?item.id=<s:property  value="itemId"/>&itemAccept.id=<s:property value="id"/>" onclick="return fDelCheck()">删除</a>&nbsp;
			</td>
		</tr>
	</s:iterator>
	</table>
	</p>
  </div>
  
</div>
    
</s:form>
</body>
</html>
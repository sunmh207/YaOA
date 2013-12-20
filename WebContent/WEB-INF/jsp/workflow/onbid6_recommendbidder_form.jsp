<%@ page contentType="text/html; charset=utf-8"%>
<%@include file="/WEB-INF/jsp/include.jsp"%>
<html:pageHead>

</html:pageHead>
<body>
<html:pagetitle title="项目招标->招标文件->投标单位账号信息"/>

<s:actionerror />
<s:actionmessage />
<s:form id="bidForm" action="onbid6!recommendBidderSave.do" method="post"  enctype="multipart/form-data">
<s:hidden name="bid.itemId" value="%{bid.itemId}" />
<s:hidden name="item.id" value="%{item.id}" />
<s:hidden name="recommendBidder.id" value="%{recommendBidder.id}" />
<table width="99%" class="table" cellpadding="0" cellspacing="0" align="center">
    
    <tr>
        <td height="24" align="right" class="td_lable">单位名称</td>
        <td class="td_edit">		
			<s:textfield  name="recommendBidder.name" value="%{recommendBidder.name}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">企业资质</td>
        <td class="td_edit">		
			<s:textfield  name="recommendBidder.qiyezizhi" value="%{recommendBidder.qiyezizhi}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">营业执照</td>
        <td class="td_edit">		
			<s:textfield  name="recommendBidder.yingyezhizhao" value="%{recommendBidder.yingyezhizhao}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">法人代表</td>
        <td class="td_edit">		
			<s:textfield  name="recommendBidder.farendaibiaoName" value="%{recommendBidder.farendaibiaoName}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">委托人姓名</td>
        <td class="td_edit">		
			<s:textfield  name="recommendBidder.weituorenName" value="%{recommendBidder.weituorenName}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">委托人身份证</td>
        <td class="td_edit">		
			<s:textfield  name="recommendBidder.weituorenId" value="%{recommendBidder.weituorenId}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">第一次报价</td>
        <td class="td_edit">		
			<s:textfield  name="recommendBidder.baojia1" value="%{recommendBidder.baojia1}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">第二次报价</td>
        <td class="td_edit">		
			<s:textfield  name="recommendBidder.baojia2" value="%{recommendBidder.baojia2}" /> 	 
        </td>
    </tr>
    <tr>
        <td height="24" align="right" class="td_lable">第三次报价</td>
        <td class="td_edit">		
			<s:textfield  name="recommendBidder.baojia3" value="%{recommendBidder.baojia3}"/> 	 
        </td>
        <td height="24" align="right" class="td_lable">得分</td>
        <td class="td_edit">		
			<s:textfield  name="recommendBidder.score" value="%{recommendBidder.score}" /> 	 
        </td>
    </tr>

    <tr>
        <td height="24" align="right" class="td_lable">附件</td>
        <td class="td_edit" colspan="3">	
        	<s:file name="recommendBidderDoc"/>
        	<a href="${root}/oa/download!downloadRecommendBidderDoc.do?recommendBidder.id=<s:property value="recommendBidder.id"/>">下载附件</a><br>
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
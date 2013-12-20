<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<html>
    <head>
        <sx:head />
        <base href="<%=basePath%>">
        <title>Tree Example(Static)</title>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <script language="JavaScript" type="text/javascript">
    dojo.event.topic.subscribe("treeSelected", function treeNodeSelected(node) {
        dojo.io.bind({
            url: "<s:url value='/info/staticTreeAction.action'/>?nodeId="+node.node.title,
            load: function(type, data, evt) {
                var divDisplay = dojo.byId("displayIt");
                divDisplay.innerHTML=data;
            },
            mimeType: "text/html"
        });
    });
</script>
    </head>
    <body>        
        <div id="displayIt">
            Please click on any node on the tree.
        </div>
        <div style="float: left; margin-right: 50px;">
            <sx:tree label="软件工程" templateCssPath="/struts/tree.css"
                showRootGrid="true" showGrid="true" treeSelectedTopic="treeSelected">
                <sx:treenode label="开发工具">
                    <sx:treenode label="java" id="grandchild1Id" />
                    <sx:treenode label="c++" id="grandchild2Id" />
                    <sx:treenode label="delphi" id="grandchild3Id" />
                </sx:treenode>
                <sx:treenode label="编程思想" id="child2Id" />
                <sx:treenode label="数据结构" id="child3Id" />
                <sx:treenode label="设计模式" id="child4Id" />
                <sx:treenode label="相关框架插件" id="child5Id">
                    <sx:treenode label="Dojo" id="gChild1Id" />
                    <sx:treenode label="Hibernate" id="gChild2Id" />
                </sx:treenode>
            </sx:tree>
        </div>
    </body>
</html>
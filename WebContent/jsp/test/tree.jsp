<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <title>Struts 2 AJAX - Tree</title>
        <s:head theme="ajax" debug="true" />
        <script type="text/javascript">
            function treeNodeSelected(arg) {
                alert(arg.source.title + ' selected');
            }
            dojo.addOnLoad(function() {                
                var s = dojo.widget.byId('parentId').selector;                
                dojo.event.connect(s, 'select', 'treeNodeSelected');
            });
        </script>
    </head>
    <body>
        <h2>
            Tree
        </h2>
        <div style="float:left; margin-right: 50px;">
            <sx:tree label="parent" id="parentId" showRootGrid="true"  showGrid="true">
                <sx:treenode label="child1" id="child1Id">
                    <sx:treenode label="grandchild1" id="grandchild1Id" />
                    <sx:treenode label="grandchild2" id="grandchild2Id" />
                    <sx:treenode label="grandchild3" id="grandchild3Id" />
                </sx:treenode>
                <sx:treenode label="child2" id="child2Id" />
                <sx:treenode label="child3" id="child3Id" />
                <sx:treenode label="child4" id="child4Id" />
                <sx:treenode label="child5" id="child5Id">
                    <sx:treenode label="gChild1" id="gChild1Id" />
                    <sx:treenode label="gChild2" id="gChild2Id" />
                </sx:treenode>
            </sx:tree>
        </div>
    </body>
</html>
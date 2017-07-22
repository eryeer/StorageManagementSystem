<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>收货登记</title>
<link rel="stylesheet" type="text/css" href="<c:url value='/css/maple.css'/>"></link>
<link href="${pageContext.request.contextPath}/css/smoothness/jquery-ui-1.9.2.custom.css" rel="stylesheet">
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-1.8.3.min.js"></script>
<script src="${pageContext.request.contextPath}/js/jquery-ui-1.9.2.custom.min.js"></script>
<script type="text/javascript">
jQuery(function($){
	$.get("${pageContext.request.contextPath}/store_findAllAjax",function(data){
		$(data).each(function(){
			$("<option value='" + this.id+"'>" + this.name + "</option>").appendTo("#store_id");
		});
	});
	var flag = false;
	$("#nm_id").on("blur",function(){
		if (!this.value) {
			return;
		}
		$.post("${pageContext.request.contextPath}/goods_findGoodsAjax","nm=" + this.value, function(data){
			if (data) {
				$("input[name=name]").val(data.name).attr("disabled",true);
				$("input[name=unit]").val(data.unit).attr("disabled",true);
				$("#store_id").val(data.store.id).attr("disabled",true);
				$("input[name=id]").val(data.id);
				flag = true;
			} else {
				if (flag) {
					$("input[name=name]").val("").removeAttr("disabled");
					$("input[name=unit]").val("").removeAttr("disabled");
					$("#store_id").removeAttr("disabled");
					$("input[name=id]").val("");
					flag = false;
				}
			}
		});
	});

	$("#name_id").autocomplete({
		source: function( request, response ) {
			$("input[name=nm]").val("").removeAttr("disabled");
			$("input[name=unit]").val("").removeAttr("disabled");
			$("#store_id").removeAttr("disabled");
			$("input[name=id]").val("");
        	$.post("${pageContext.request.contextPath}/goods_findByNameLikeAjax",{"name":request.term},function(data){
        		response(data);
        	});
        },
   		select: function( event, ui ) {
   			$("input[name=nm]").val(ui.item.nm).attr("disabled",true);
			$("input[name=unit]").val(ui.item.unit).attr("disabled",true);
			$("#store_id").val(ui.item.store.id).attr("disabled",true);
			$("input[name=id]").val(ui.item.id);
   		}
	});
});
</script>
<style type="text/css">
	.tx td{
		padding:3px;
	}
</style>
</head>
<body>
	<!-- 中间内容（开始） -->
	<table border="0" class="tx" width="100%">
		<tr>
			<td>当前位置&gt;&gt;首页&gt;&gt;入库</td>
		</tr>
	</table>
	<br>
	<table border="0" width="100%" cellpadding="0" cellspacing="0">
		<tr valign="top">
			<td rowspan="2">
				<s:form action="goods_saveGoods" namespace="/" method="post" name="select">
					<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tx" align="center">
						<colgroup>
							<col width="20%" align="right">
							<col width="*%" align="left">
						</colgroup>
						<tr>
							<td bgcolor="a0c0c0" style="padding-left:10px;" colspan="2" align="left">
								<b>货物入库登记：</b>
							</td>
						</tr>
						<tr>
							<td>
								简记码：
							</td>
							<td>
								<s:hidden name="id"></s:hidden>
								<s:textfield name="nm" cssClass="tx" id="nm_id"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>
								货物名称：
							</td>
							<td>
								<s:textfield name="name" cssClass="tx" id="name_id"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>
								计量单位：
							</td>
							<td>
								<s:textfield name="unit" cssClass="tx"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>
								入库数量：
							</td>
							<td>
								<s:textfield name="amount" cssClass="tx"></s:textfield>
							</td>
						</tr>
						<tr>
							<td>
								选择仓库：
							</td>
							<td>
								<s:select list="{}" id="store_id" name="store.id" cssStyle="width:120px;" ccsClass="tx"></s:select>
								(此信息从数据库中加载)
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center" style="padding-top:10px;">
								<input class="tx" style="width:120px;margin-right:30px;" type="button" onclick="document.forms[0].submit();" value="入库">
								<input class="tx" style="width:120px;margin-right:30px;" type="reset" value="取消">
							</td>
						</tr>
					</table>
				</s:form>
			</td>
			<td valign="top" width="20%">
				<table width="100%" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td background="<c:url value='/picture/loginpage.gif'/>" align="center"><br>
						<font color="red">操作步骤</font>
						</td>
					</tr>
					<tr>
						<td background="<c:url value='/picture/bg1.jpg'/>" style="padding-left:10px;">
							1.输入简记码从数据库<br/>查询是否已经存在此
							<br/>货物
							<br/>
							2.没有则直接输入货物名称
							<br>
							3.从数据库选择仓库
							<br>
							4.向仓库中新添加或是追加货物
							<br/>
							5.记录入库历史记录
						</td>
					</tr>
					<tr>
						<td><img src="<c:url value='/picture/bottom.jpg'/>"></td>
					</tr>
				</table>
		</tr>
	</table>
</body>
</html>


<#assign base=request.contextPath /> 
<div class="bjui-pageContent">
	<form action="${base}/user/userLogLogin/update" data-toggle="validate" method="post">
		<input type="hidden" name="id" value="${bean.id}"/>
		<div class="form-group">
            <label class="control-label x85">创建时间：</label>
            <input type="text" name="gmtCreate" value="${bean.gmtCreate!}" placeholder="创建时间" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">用户编号：</label>
            <input type="text" name="userNo" value="${bean.userNo!}" placeholder="用户编号" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">客户端ID：</label>
            <input type="text" name="platformId" value="${bean.platformId!}" placeholder="客户端ID" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">登录状态(1成功，0失败)：</label>
            <input type="text" name="loginStatus" value="${bean.loginStatus!}" placeholder="登录状态(1成功，0失败)" size="20">
        </div>
		<div class="form-group">
            <label class="control-label x85">登录IP：</label>
            <input type="text" name="loginIp" value="${bean.loginIp!}" placeholder="登录IP" size="20">
        </div>
	</form>
</div>
<div class="bjui-pageFooter">
    <ul>
        <li><button type="button" class="btn-close">取消</button></li>
        <li><button type="submit" class="btn-default">修改</button></li>
    </ul>
</div>
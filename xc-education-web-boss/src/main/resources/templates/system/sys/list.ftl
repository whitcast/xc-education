<#include "/macro/base.ftl" />
<div class="bjui-pageHeader">
	<form id="pagerForm" data-toggle="ajaxsearch" action="${base}/system/sys/list" method="post">
		<@pageHeadr />
		<div class="bjui-searchBar">
			<label>账号：</label>
			<input type="text" name="number" value="" class="form-control" size="10">&nbsp;
			
			<button type="submit" class="btn-default" data-icon="search">查询</button>&nbsp;
			<a class="btn btn-orange" href="javascript:;" data-toggle="reloadsearch" data-clear-query="true" data-icon="undo">清空查询</a>
			
			<br />
			<br />
			<a href="${base}/system/sys/add" class="btn btn-default" data-toggle="dialog" data-icon="plus" data-id="sys-add" data-options="{title:'添加', height:500}">添加 </a>
		</div>
	</form>
</div>
<div class="bjui-pageContent tableContent">
	<table class="table table-bordered table-hover table-top" data-toggle="tablefixed" data-selected-multi="true">
		<thead>
			<tr>
				<th width="30">序号</th>
				<th>创建时间</th>
				<th>修改时间</th>
				<th>状态(1有效, 0无效)</th>
				<th>视频存储平台（1保利威视，2七牛）</th>
				<th>useid</th>
				<th>writetoken</th>
				<th>readtoken</th>
				<th>secretkey</th>
				<th>文件存储类型（1阿里云，2七牛）</th>
				<th>access_key_id</th>
				<th>access_key_secret</th>
				<th>支付通道（1龙果支付，2其他）</th>
				<th>xc_key</th>
				<th>xc_secret</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
			<#if page??>
			<#list page.list as bean>
			<tr>
				<td align="center">${bean_index+1}</td>
				<td>${bean.gmtCreate!}</td>
				<td>${bean.gmtModified!}</td>
				<td>${bean.statusId!}</td>
				<td>${bean.videoType!}</td>
				<td>${bean.polyvUseid!}</td>
				<td>${bean.polyvWritetoken!}</td>
				<td>${bean.polyvReadtoken!}</td>
				<td>${bean.polyvSecretkey!}</td>
				<td>${bean.fileType!}</td>
				<td>${bean.aliyunAccessKeyId!}</td>
				<td>${bean.aliyunAccessKeySecret!}</td>
				<td>${bean.payType!}</td>
				<td>${bean.xcKey!}</td>
				<td>${bean.xcSecret!}</td>
				<td>
					<a href="${base}/system/sys/view?id=${bean.id}" class="btn btn-blue" data-toggle="dialog" data-id="sys-view" data-options="{title:'查看', height:500}">查看</a>
					<a href="${base}/system/sys/edit?id=${bean.id}" class="btn btn-green" data-toggle="dialog" data-id="sys-edit" data-options="{title:'修改', height:500}">修改</a>
					<a href="${base}/system/sys/delete?id=${bean.id}" class="btn btn-red" data-toggle="doajax" data-id="sys-delete" data-confirm-msg="确定要删除吗？">删除</a>
				</td>
			</tr>
			</#list>
			</#if>
		</tbody>
	</table>
</div>
<@pageFooter />

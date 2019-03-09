<#import "/layout/header.ftl" as h/>
<#import "/layout/head.ftl" as b/>
<!DOCTYPE html>
<html>
	
	<@h.header title="课程播放"/>

<@b.head ></@b.head> 
<link rel="stylesheet" href="/styles/vadio.css" />
<link rel="stylesheet" href="/styles/video-grade.css" />
<link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
<script src="/scripts/youmei/video-grade.js"></script>
<style>
</style>
<body>
<div style="100%;height:50px;background:#FEF8EC;"></div>
<div style="width:100%;min-width:600px;display: flex;">
<div class="vadio">
<video src="" width="100%" height="100%;" controls="controls"></video>
</div>
<div class="mulu">
<p><span>课程目录</span></p>
<dl>
		<dt class="first">
			<div class="d-firstNav s-firstNav clearfix">
				<i class="fa fa-bars"></i>
				<span>一级菜单</span>
				<i class="fa fa-caret-right fr "></i>
			</div>
			<ul class="d-firstDrop s-firstDrop">
				<dt>
					<dd class="d-secondNav s-secondNav">
						<i class="fa fa-minus-square-o"></i>
						<span>二级菜单</span>
						<i class="fa fa-caret-right fr"></i>
					</dd>
					<dd class="d-secondDrop s-secondDrop">
						<li class="s-thirdItem">
							<a href="#">三级导航</a>
						</li>
						<li class="s-thirdItem">
							<a href="#">三级导航</a>
						</li>
					</dd>
				</li>
		</dl>		
</div>
</div>
</body>

</html>
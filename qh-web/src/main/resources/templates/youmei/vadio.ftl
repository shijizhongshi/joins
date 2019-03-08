<#import "/layout/header.ftl" as h/>
<#import "/layout/head.ftl" as b/>
<!DOCTYPE html>
<html>
	
	<@h.header title="课程播放"/>

<@b.head ></@b.head> 
<link rel="stylesheet" href="/styles/vadio.css" />
<body>

<div style="100%;height:50px;background:#FEF8EC;"></div>
<div style="width:100%;min-width:600px;display: flex;">
<div class="vadio">
<video src="" width="100%" height="100%;" controls="controls"></video>
</div>
<div class="mulu">
<p><span>课程目录</span></p>
<dl>
<dt><img src="/images/logHead.png" style="height:22px;float:left;"/>药理学精讲</dt>
<!-- ng-class background:#DDDDDD; 图片playIs.gif  -->
<dd style="background:#DDDDDD;"><img src="/images/playIs.gif" />药精讲</dd>
<dd><img src="/images/playList.png" />药理学精讲</dd>
<dd> <img src="/images/playList.png" />临床技能 第三站</dd>
</dl>
</div>
</div>
</body>

</html>
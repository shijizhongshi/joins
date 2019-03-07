<#import "/layout/header.ftl" as h/>
<#import "/layout/head.ftl" as b/>
<!DOCTYPE html>
<html>
	
	<@h.header title="优渼教育"/>

<link rel="stylesheet" href="/styles/youmei.css" />
<<<<<<< HEAD
<@b.head ></@b.head> 
<script src="/scripts/youmei/index.js"></script>
<body ng-app="app" ng-controller="indexController">
<div class="nav">
<img src="/images/banner.png"/>
</div>
	<!--企业文化-->
	<div class="wenhua">
	<b class="biaoti"><img src="/images/wenhua.png" /></b>
	<p class="xiaobiaoti">ENTERPRIS&nbsp;CULTURE</p>
	<p  class="miaoshu">中医具有完整的理论体系，其独特之处，在于“天人合一”、“天人感应”<br/>的整体观及辨证论治</p>
	<div class="wenhua-center">
	<div class="wenhua-left">
	<p style="margin-bottom:56px;"><img src="/images/jianjie.png" /></p>
	<p>&nbsp;&nbsp;优渼教育网是知名教育集团中师传承中医药研究院旗下的一大品牌之一，是致力于小儿推拿、中医正骨、中医针灸等中医教育为一体的专业化、规模化的中医适宜技术推广公司。有专业的中医讲师团队，并且每位名师都是丰厚的中医临床经验，学习过程中能够真正做到“言简意赅”的效果。网上学习、线下授课，两站式服务，让中医适宜技术学习更简单。</p>
	</div>
	
	<div class="wenhua-right"><img src="/images/wenhua-right.png" /></div>
	</div>
	</div>
	<!--新闻资讯-->
<div class="zixun">
<b class="biaoti"><img src="/images/zixun.png"/></b>
<p class="xiaobiaoti">ENTERPRIS&nbsp;CULTURE</p>
<p  class="miaoshu">中医具有完整的理论体系，其独特之处，在于“天人合一”、“天人感应”<br/>的整体观及辨证论治</p>

<div class="zixun-center" >
<ul>

<li style="position: relative;height:100%;" >
<img src="{{newsList[0].imgUrl}}" style="height:" />
<div class="fuyuansu" style="position: absolute;bottom:0;text-align: center;line-height: 40px;width:100%;height:40px;background: black;padding:0;color:white;opacity: 0.7;">
<b style="margin:0 auto;font-size:1.3rem;letter-spacing:2px;overflow:hidden;text-overflow:ellipsis;white-space: nowrap;">{{newsList[0].title}}</b>
</div>
</li>
<li style="position: relative;height:100%;">
<img src="{{newsList[1].imgUrl}}" />
<div class="fuyuansu" style="position: absolute;bottom:0;text-align: center;line-height: 40px;width:100%;height:40px;background: black;padding:0;color:white;opacity: 0.7;">
<b style="margin:0 auto;font-size:1.3rem;letter-spacing:2px;overflow:hidden;text-overflow:ellipsis;white-space: nowrap;">{{newsList[1].title}}</b>
</div>
</li>

</ul>
<!--新闻资讯下边不调缩略图的六个-->
<ul style="margin-top:40px;">
<li ng-repeat = "news in news">
<div class="fuyuansu">
<div class="neirong-zuo" >
<b >{{news.title}}</b>

</div>
<div class="shijian">
<b>21</b>
<span>2019-08</span>
</div>
</div>
</li>
</ul>

</div>

</div>	
<!--诊疗项目,循环六次-->
<div class="xiangmu">
<b class="biaoti"><img src="/images/xiangmu.png" /></b>
	<p class="xiaobiaoti">ENTERPRIS&nbsp;CULTURE</p>
	<p  class="miaoshu">通过“望闻问切” 四诊和珅的方法，探求<br/>病因、病性、病位</p>
	<div class="xiangmu-center">
	
	<dl>
	<dt>标题<span style="float:right;font-size:1.5rem;" >></span></dt>
	<img src="/images/xiangm.png"/>
	<dd>内容</dd>
	</dl>
	</div>
</div>
<!--师资团队-->
<div class="shizi">
<div class="shizi-center">
<dl>
<dt>师</dt>
<p><span>师资团队</span></br>ONLINE&nbsp;BOOKING</p>
<dd>挑选理论知识扎实</br>
临床技能经验丰富的</br>
高年资中医师担任培训老师</dd>
</dl>
<dl>
<dt>师</dt>
<p><span>师资团队</span></br>ONLINE&nbsp;BOOKING</p>
<dd>挑选理论知识扎实</br>
临床技能经验丰富的</br>
高年资中医师担任培训老师</dd>
</dl>
<dl>
<dt>师</dt>
<p><span>师资团队</span></br>ONLINE&nbsp;BOOKING</p>
<dd>挑选理论知识扎实</br>
临床技能经验丰富的</br>
高年资中医师担任培训老师</dd>
</dl>
</div>
</div>
<!--联系我们-->
<div class="lianxi">
<div class="fangshi">
<p><img src="/images/weixin.png" /><span>&nbsp;官方微信</span></p>
<p>微信号:sjzs0531</p>
</div>
<p style="height:1px;width:100%;background:black;"></p>
<div class="fangshi" style="margin-top:-5px;">
<p><img src="/images/dianhua.png" /><span>&nbsp;联系我们</span></p>
<p>微信号:400-662-9001</p>
</div>
</div>
<!--关于我们-->
<div class="guanyu">
<div class="guanyu-centent">
<div style="float:left;">
<ul class="youlian">
			<li><a href="#">网站首页</a></li>
			<li><a href="#">关于我们</a></li>
			<li><a href="#">诚聘英才</a></li>
			<li><a href="#">帮助中心</a></li>
		</ul>
	<div id="district">
		 <ul>
		 <li onmousemove="font(0)">济南分校</li>		  
		    <li onmousemove="font(1)">德州分校</li>
		 </ul>
		 </div>
		 <div id="address">
		 <p style="font-size:1.3rem;color:red;">地址：</p>
		 <ul>
		 
		  <li style="display: block;font-size:1.2rem;">济南市天桥区名泉广场C座1904</li>
		    <li style="display: none;font-size:1.2rem;">德州市德城区青年路贵都综合9楼905室</li>
		 </ul>
		 </div>
</div>
<div style="float:right;width:200px;font-size:1.3rem;line-height: 30px;text-align: center;">
<img src="/images/youmeierweima.jpg" style="width:200px;"/>
微信公众号
</div>
</div>
</div>
<!--备案号-->
<div class="beian">@9999感冒灵</div>
	</body>
	 <script type="text/javascript">
 function font(s){
 			var tabs=document.getElementById("district").getElementsByTagName("li");
 			var show=document.getElementById("address").getElementsByTagName("li");
            for(var x=0;x<tabs.length;x++){
		
            	show[x].style.display='none';
                if(x==s){
                	
                    show[x].style.display='block';
					
                }

            }


        }
 
</script>
</html>
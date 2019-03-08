<#import "/layout/header.ftl" as h/>
<#import "/layout/head.ftl" as b/>
<!DOCTYPE html>
<html>
	
	<@h.header title="优渼教育"/>

<link rel="stylesheet" href="/styles/youmei.css" />
<style>

#index{
	background:#CB0101 !important;
}
</style>
<script src="/scripts/youmei/index.js"></script>
<@b.head ></@b.head> 

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
<b>{{news.addtime | date:'dd'}}</b>
<span>{{news.addtime | date:'yyyy-MM'}}</span>
</div>
</div>
</li>
</ul>

</div>

</div>	
<div class="xiangmu">
<b class="biaoti"><img src="/images/xiangmu.png" /></b>
	<p class="xiaobiaoti">ENTERPRIS&nbsp;CULTURE</p>
	<p  class="miaoshu">通过“望闻问切” 四诊和珅的方法，探求<br/>病因、病性、病位</p>
	<div class="xiangmu-center">
	
	<dl>
	<dt>中医针灸<span style="float:right;font-size:1.5rem;" >></span></dt>
	<img src="/images/xiangm.png"/>
	<dd>针灸由"针"和"灸"构成，是东方医学的重要组成部分之一，其内容包括针灸理论、腧穴、针灸技术以及相关器具，在形成、应用和发展的过程中，具有鲜明的中华民族文化与地域特征，是基于中华民族文化和科学传统产生的宝贵遗产</dd>
	</dl>
	<dl>
	<dt>中医舌诊<span style="float:right;font-size:1.5rem;" >></span></dt>
	<img src="/images/xiangm.png"/>
	<dd>中医四诊中望诊的一部分，临床上对于各种疾病，都常结合辨舌来决定诊断和治疗，它标志着中医诊病的传统经验和特色，也是中医临床上占重要地位不可缺少的检查常规。</dd>
	</dl>
	<dl>
	<dt>小儿推拿<span style="float:right;font-size:1.5rem;" >></span></dt>
	<img src="/images/xiangm.png"/>
	<dd>小儿推拿是以中医辩证理论为基础，通过穴位点按推拿、调节脏腑、疏通经络、调和气血、平衡阴阳的方式来改善儿童体质、提高机体免疫力的一种保健、治疗方式。</dd>
	</dl>
	<dl>
	<dt>正骨<span style="float:right;font-size:1.5rem;" >></span></dt>
	<img src="/images/xiangm.png"/>
	<dd>正骨，中医指用推、拽、按、捺等手法治疗骨折、脱臼等疾病。对象主要是外力作用所致的骨、关节和软组织的损伤，但也包括同类原因引致的体内脏器损伤正骨主要分为骨折和脱位，骨折系指由于外伤或病理等原因致使骨质部分地或完全地断裂的一种疾病。</dd>
	</dl>
	<dl>
	<dt>拔罐<span style="float:right;font-size:1.5rem;" >></span></dt>
	<img src="/images/xiangm.png"/>
	<dd>拔罐法古称角法，又名火罐气、吸筒疗法，是以罐为工具，利用燃烧排除罐内空气，造成负压，使之吸附于腧穴或应拔部位的体表，产生刺激，使被拔部位的皮肤充血、淤血，以达到防治疾病的目的。</dd>
	</dl>
	<dl>
	<dt>康复理疗<span style="float:right;font-size:1.5rem;" >></span></dt>
	<img src="/images/xiangm.png"/>
	<dd>康复理疗又称为康复物理治疗，是集运动治疗、作业治疗、言语治疗、物理治疗、针灸、拨罐、按摩于一体的综合性治疗室</dd>
	</dl>
	</div>
</div>
<!--师资团队-->
<div class="shizi">
<div class="shizi-center">
<dl>
<dt>免</dt>
<p><span>免费重学</span></br>ONLINE&nbsp;BOOKING</p>
<dd>包教包会</br>
第一次未过，第二次免费培训</br>
</dd>
</dl>
<dl>
<dt>师</dt>
<p><span>师资团队</span></br>ONLINE&nbsp;BOOKING</p>
<dd>挑选理论知识扎实</br>
临床技能经验丰富的</br>
高年资中医师担任培训老师</dd>
</dl>
<dl>
<dt>赠</dt>
<p><span>会员赠送</span></br>ONLINE&nbsp;BOOKING</p>
<dd>购买单项中医适宜技术课程</br>
就可成为优渼教育会员</br>
免费获得价值1980元的名师面授课一天</dd>
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
<!--footer-->
<#include "footer.ftl"/>
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
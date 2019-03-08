<#import "/layout/header.ftl" as h/>
<#import "/layout/head.ftl" as b/>
<!DOCTYPE html>
<html>
<@h.header title="订单详情"/>
<link rel="stylesheet" href="/styles/order-form.css" />
<@b.head ></@b.head> 
<body>
<div style="background:#C6A57F;width:100%;height:50px;"></div>
<div class="order-form">
 <p style="padding-bottom: 20px;border-bottom: 2px solid red;font-size: 1.2rem;">提交订单</p>

</div>
<!--footer-->
<#include "/layout/footer.ftl"/>
</body>
</html>
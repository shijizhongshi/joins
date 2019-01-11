package com.ola.qh.entity;

public class OrdersStatus {

	public static final String NEW="NEW";//创建的初始化订单
    
    public static final String PAID="PAID";/////支付的订单
    
    public static final String DELIVERED="DELIVERED";/////发货的订单
    
    public static final String RECEIVED="RECEIVED";/////完成的订单
    
    //////////////////////////////对于服务的退款=====================
    public static final String CANCELSERVE="CANCELSERVE";///取消服务
    
    public static final String CANCELSERVEED="CANCELSERVEED";///已取消服务(钱已退订单已完成)
    
    public static final String REJECTCANCELSERVEED="REJECTCANCELSERVEED";///已拒绝取消服务
    
    public static final String CANCELAPPLY="CANCELAPPLY";///取消申请
    
    public static final String CLONSESERVE="CLONSESERVE";///关闭服务
    
    ////////////////对于订单商品的退货===============================
    
    public static final String REFUNED="REFUNED";//////完成退货
    
    public static final String APPLYREFUNED="APPLYREFUNED";/////申请退款
    ////(只有未发货的可以申请退款)
    
    public static final String AGREEREFUNED="AGREEREFUNED";////同意退款
    
    public static final String REJECTREFUNED="REJECTREFUNED";/////拒绝退款
    	
}

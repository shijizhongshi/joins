package com.ola.qh.entity;

public class OrdersStatus {

	public static final String NEW="NEW";//创建的初始化订单
    
    public static final String PAID="PAID";/////支付的订单
    
    public static final String DELIVERED="DELIVERED";/////发货的订单
    
    public static final String RECEIVED="RECEIVED";/////完成的订单
    
    ////////////////对于订单商品的退货===============================
    
    public static final String REFUNED="REFUNED";//////完成退货
    
    public static final String APPLYREFUNED="APPLYREFUNED";/////申请退款
    ////(只有未发货的可以申请退款)
    
    public static final String AGREEREFUNED="AGREEREFUNED";////同意退款
    
    public static final String REJECTREFUNED="REJECTREFUNED";/////拒绝退款
    	
}

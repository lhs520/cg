package scut.legend.cg.vo;


public enum Result {
	
	SUCCESS(0,"成功"),
	UNKNOWN_ACCOUNT_ERROR(1,"账号不存在"),
	INCORRECT_CREDENTIALS_ERROR(2,"密码错误"),
	INCORRECT_STATE_ERROR(3,"该员工已离职"),
	OTHER_LOGIN_ERRROR(4,"请联系管理员"),//登录时发生的其他错误
	PARAM_ERROR(5,"参数错误"),
	ID_NUM_FORMAT_ERROR(6,"身份证格式错误"),
	STAFF_NOT_FOUND(7,"没有该员工"),
	USER_NOT_LOGIN(8,"用户未登录"),
	MATERIAL_NOT_EXISTED(9,"原料不存在"),
	RECORD_NOT_EXISTED(10,"记录不存在"),
	PASSWORD_FORMAT_ERROR(11,"密码格式错误(6~30位)"),
	INVENTORY_ERROR(100,"库存数量有误"),
	PRODUCT_NOT_EXISTED(101,"该产品不存在，请检查产品信息是否正确"),
	DELIVERY_QUANTITY_TOO_LARGH(102,"配送量大于订单需配送量"),
	INVENTORY_SHORTAGE(103,"产品库存不足"),
	ORDER_NOT_EXISTED(104,"该订单不存在"),
	DELIVERYRECORD_NOT_EXISTED(105,"该配送记录不存在"),
	NEED_BIG_TOTAL(106,"订单需配送量大于总配送量"),
	UNITPRICE_TOO_SMALL(107,"产品单价太小"),
	NO_WAREHOUSEMANAGER(108,"该员工不是仓库管理员"),
	DELIVERY_NEED_TOO_SMALL(109,"订单总量小于已配送量"),
	CREATE_ORDER_SUCCESS(0,"下单成功"),
	UPDATE_ORDER_SUCCESS(0,"修改订单成功"),
	CREATE_WAREHOUSEINSTORAGE_SUCCESS(0,"入库成功"),
	UPDATE_WAREHOUSEINSTORAGE_SUCCESS(0,"修改入库记录成功"),
	CREATE_DELIVERY_SUCCESS(0,"配送成功"),
	UPDATE_DELIVERY_SUCCESS(0,"修改配送记录成功"),
	CREATE_STAFF_SUCCESS(0,"创建员工成功"),
	UPDATE_STAFF_SUCCESS(0,"修改员工成功"),
	CREATE_MateralPurchaseRecord_SUCCESS(0,"创建采购记录成功"),
	UPDATE_MateralPurchaseRecord_SUCCESS(0,"修改采购记录成功"),
	
	
	
	TIME_PARSE_ERROR(201,"字符串转换为时间出错"),
	OUT_STORAGE_TOO_LARGE(202,"产品库存不足，无法出库"),
	PRODUCT_NOT_EXIST(203,"此产品不存在，无法出库"),
	CANNOT_CREATE_NUM(204,"创建编号失败"),
	NULL_LIST(205,"批量插入记录列表为空，插入失败"),
	MATERIAL_NOT_ENOUGH(206,"原料库存不足，无法开炉"),
	BLANKRECORD_TODAY_EXISTED(207,"当天该型号的胚料记录已存在"),
	BLOWONRECORD_TODAY_NOT_EXISTED(208,"当天该型号产品未开炉"),
	CREATE_BLOWONRECORD_SUCCESS(0,"添加开炉记录成功"),
	UPDATE_BLOWONRECORD_SUCCESS(0,"修改开炉记录成功"),
	CREATE_BLANKRECORD_SUCCESS(0,"添加胚料记录成功"),
	UPDATE_BLANKRECORD_SUCCESS(0,"修改胚料记录成功"),
	CREATE_CHECKWOR_SUCCESS(0,"添加其他清仓记录成功"),
	UPDATE_CHECKWOR_SUCCESS(0,"修改其他清仓记录成功"),
	CREATE_CHECKWPR_SUCCESS(0,"添加产品清仓记录成功"),
	UPDATE_CHECKWPR_SUCCESS(0,"修改产品清仓记录成功"),
	CREATE_FACTORYOSR_SUCCESS(0,"添加工厂出库记录成功"),
	UPDATE_FACTORYOSR_SUCCESS(0,"修改工厂出库记录成功"),
	CREATE_PRODUCTMODELINFO_SUCCESS(0,"添加产品型号记录成功"),
	UPDATE_PRODUCTMODELINFO_SUCCESS(0,"修改产品型号记录成功"),
	CREATE_PRODUCTPRODUCE_SUCCESS(0,"添加产品产出记录成功"),
	UPDATE_PRODUCTPRODUCE_SUCCESS(0,"修改产品产出记录成功"),
	
	SYSTEM_EXCEPTION(500,"系统异常，请联系管理员"), 
	
	UNAUTHORIZED(401,"未授权");
	
	
	private int code;
	private String msg;
	
	private Result(){
		
	}
	
	private Result(int code,String msg){
		this.code=code;
		this.msg=msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
	
	
	
}

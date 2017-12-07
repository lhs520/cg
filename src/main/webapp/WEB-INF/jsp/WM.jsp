<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>仓库</title>

<!-- Bootstrap -->
<link href="../static/css/lib/bootstrap.min.css" rel="stylesheet">
<!--jQuery UI-->
<link href="../static/css/lib/jquery-ui.min.css" rel="stylesheet" type="text/css">

<link href="../static/css/common.css" rel="stylesheet">
</head>
<body>
<header>
<nav class="navbar navbar-default fixed" id="header-nav" role="navigation" style="width:100%">
<div class="container-fluid">
<div class="navbar-header">
<a class="navbar-brand" href="#">Legend仓库管理系统</a>
</div>
<div class="fl">
<ul class="nav navbar-nav">
<li class="role-navbar-nav active" id="warehouse-navbar-nav"><a href="#">仓库</a></li>
</ul>
</div>
<div class="fr">
<ul class="nav navbar-nav navbar-right">
<li><a id="nav-staff-name" href="#"><span class="glyphicon glyphicon-user"></span> staff-name</a></li>
<li><a id="logout" href="/cg/staff/logout"><span class="glyphicon glyphicon-log-out"></span> 退出</a>
</li>
</ul>
</div>
</div>
</nav>
</header>
<div class="container wrap">
<div class="row">
<div class="col-md-1">
<nav class="block-display block-shadow" id="feature-nav">
<ul class="nav nav-tabs nav-stacked nav-pills nav-click" id="nav-tabs">
<li class="warehouse-navtabs active"><a name="orderManagement" href="#order-management" role="tab"
data-toggle="tab">订单查询</a></li>
<li class="warehouse-navtabs"><a name="inStorageRecord"
href="#in-storage-record"
role="tab" data-toggle="tab">入库登记</a></li>
<li class="warehouse-navtabs"><a id="delivery-record-atag"
name="deliveryRecord"
href="#delivery-record"
role="tab" data-toggle="tab">配送记录</a></li>
<li class="warehouse-navtabs"><a name="warehouseInventory"
href="#warehouse-inventory" role="tab"
data-toggle="tab">仓库库存</a></li>
<li class="warehouse-navtabs"><a name="warehouseStatistics"
href="#warehouse-statistics" role="tab"
data-toggle="tab">仓库统计</a></li>
</ul>
</nav>
</div>
<div class="col-md-11">
<div class="" id="feature-display">
<div class="tab-content">
<div class="tab-pane active" id="order-management">
<!--订单记录-->
<div class="panel panel-default block-shadow factory-order-record-table table-data"
data-url="WarehouseOrders" data-div=".factory-order-record-table" data-abbreviation="for"
data-function="order">
<div class="panel-heading">
<div class="panel-title">订单记录</div>
</div>
<div class="panel-body panel-body-border">
<label for="for-start-date">起始时间 </label><input type="text" name="start-time"
id="for-start-date" value="">
<label for="for-end-date">终止时间 </label><input type="text" name="end-time"
id="for-end-date"
value="">
<label for="for-order-num">订单编号 </label><input type="text" name="product-model"
id="for-order-num" value="">
<label for="for-customer-name">收货人姓名 </label><input type="text" name="product-size"
id="for-customer-name" value="">
<label for="for-order-status">配送状态 </label>
<select name="product-shape" id="for-order-status">
<option value="none"></option>
<option value="未配送">未配送</option>
<option value="部分配送">部分配送</option>
<option value="已配送">已配送</option>
</select>
<button id="for-select" type="button" class="btn btn-primary">筛选</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" data-modal-id="modal-order-modify">
<thead>
<tr>
<th class="hide" name="id" data-key="id">ID</th>
<th name="orderNum" data-key="orderNum">订单编号</th>
<th name="orderDate" data-key="orderDate">订单日期</th>
<th name="productModel" data-key="product.productModelInfo.productModel">产品型号
</th>
<th name="productSize" data-key="product.productSize">产品规格</th>
<th name="productShape" data-key="product.productShape">产品形态</th>
<th name="deliveryQuantityTotal" data-key="deliveryQuantityTotal">总量(kg)</th>
<th name="deliveryQuantityNeed" data-key="deliveryQuantityNeed">需配送(kg)</th>
<th name="unitPrice" data-key="unitPrice">单价(元/kg)</th>
<th name="totalPrice" data-key="totalPrice">总价(元)</th>
<th name="customerName" data-key="customer.customerName">收货人</th>
<th class="hide" name="customerAddress" data-key="customer.customerAddress">配送地址
</th>
<th class="hide" name="warehouseManagerId" data-key="warehouseManagerId">仓库管理员ID
</th>
<th name="warehouseManagerName" data-key="warehouseManagerName">仓库管理员
</th>
<th name="orderStatus" data-key="orderStatus">订单状态</th>
<th name="deliveryDate" data-key="deliveryDate">送达日期</th>
<th name="staffName" data-key="staffName">登记人</th>
</tr>
</thead>
<tbody>
</tbody>
</table>
<!-- 订单详细信息模态 -->
<div class="modal fade" id="order-modal" tabindex="-1" role="dialog"
aria-labelledby="myModalLabel" aria-hidden="true">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button type="button" class="close" data-dismiss="modal"
aria-hidden="true">
&times;
</button>
<h4 class="modal-title" id="myModalLabel">详细信息</h4>
</div>
<div class="modal-body order-detail">
<div>
<label for="order-id-detail">订单ID:</label>
<input type="text" id="order-id-detail" readonly>
</div>
<div>
<label for="order-num-detail">订单编号：</label>
<input type="text" id="order-num-detail" readonly>
</div>
<div>
<label for="order-date-detail">订单日期：</label>
<input type="text" id="order-date-detail" readonly>
</div>
<div>
<label for="order-product-model-detail">产品型号：</label>
<input type="text" id="order-product-model-detail" readonly>
</div>
<div>
<label for="order-product-size-detail">产品规格(厚*长*宽)：</label>
<input id="order-product-size-detail" readonly>
</div>
<div>
<label for="order-product-shape-detail">产品形态：</label>
<input id="order-product-shape-detail" readonly>
</div>
<div>
<label for="order-deliveryQuantityTotal-detail">总量(kg)：</label>
<input type="text" id="order-deliveryQuantityTotal-detail" readonly>
</div>
<div>
<label for="order-deliveryQuantityNeed-detail">需配送(kg)：</label>
<input type="text" id="order-deliveryQuantityNeed-detail" readonly>
</div>
<div>
<label for="order-unitPrice-detail">单价(元/kg)：</label>
<input type="text" id="order-unitPrice-detail" readonly>
</div>
<div>
<label for="order-totalPrice-detail">总价(元/kg)：</label>
<input type="text" id="order-totalPrice-detail" readonly>
</div>
<div>
<label for="order-customerName-detail">收货人：</label>
<input type="text" id="order-customerName-detail" readonly>
</div>
<div>
<label for="order-customerAddress-detail">配送地址：</label>
<input type="text" id="order-customerAddress-detail" readonly>
</div>
<div>
<label for="order-orderStatus-detail">仓库管理员ID:</label>
<input type="text" id="order-orderStatus-detail" readonly>
</div>
<div>
<label for="order-deliverer-id-detail">仓库管理员:</label>
<input type="text" id="order-deliverer-id-detail" readonly>
</div>
<div>
<label for="order-deliverer-name-detail">订单状态：</label>
<input type="text" id="order-deliverer-name-modify" readonly>
</div>
<div>
<label for="order-deliveryDate-detail-arrival-date">送达日期：</label>
<input type="text" id="order-deliveryDate-detail-arrival-date"
readonly>
</div>
<div>
<label for="order-registrant-detail">登记人：</label>
<input type="text" id="order-registrant-detail" readonly>
</div>
</div>
<div class="modal-footer">
<button type="button" class="btn btn-default" data-dismiss="modal">关闭
</button>
</div>
</div><!-- /.modal-content -->
</div><!-- /.modal -->
</div>
</div>
</div>
</div>
<div class="tab-pane" id="in-storage-record">
<!--仓库入库登记-->
<div class="panel panel-default block-shadow add-form" id="add-in-storage-record"
data-url="WarehouseInStorageRecord">
<div class="panel-heading">
<div class="panel-title">入库登记</div>
</div>
<div class="panel-body add-table panel-body-border">
<table class="table table-bordered" style="width:750px;margin:0">
<thead>
<tr>
<th>入库日期</th>
<th>型号</th>
<th>规格（厚*长*宽 单位：mm）</th>
<th>形态</th>
<th>入库总量（kg）</th>
</tr>
</thead>
<tbody>
<tr>
<!-- 时间戳 -->
<td><input class="add-form-item" type="text" id="in-storage-end-date"></td>
<td class="hidden"><input class="add-form-item" type="text"
id="in-storage-end-time"
name="inStorageDate">
</td>
<td><select class="add-form-item" id="in-storage-product-model"
name="product.productModelInfo.productModel"></select></td>
<td><input class="add-form-item size-input-editor" type="text"
id="in-storage-product-size" name="product.productSize"
placeholder="厚*长*宽"></td>
<td><select class="add-form-item" id="in-storage-product-shape"
name="product.productShape">
<option value="直" selected>直</option>
<option value="弯">弯</option>
</select>
</td>
<td><input class="add-form-item number-judge" type="text"
id="in-storage-quantity"
placeholder="保留三位小数"
name="inStorageQuantity"></td>
</tr>
</tbody>
</table>
</div>
<div class="panel-body add-header">
<button type="button" class="btn btn-info btn-submit-in-add-form"
id="btn-submit-in-storage">提交
</button>
<button type="button" class="btn btn-danger btn-cancel-in-add-form"
id="btn-cancel-in-storage">取消
</button>
<span class="hiddenMsg"></span>
</div>
<div id="modal-in-storage" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true"
data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">请确认添加的信息是否正确？</h4>
</div>
<div class="modal-body">
<label>入库日期：</label><span id="modal-in-storage-end-date"></span><br>
<!-- 时间戳 -->
<label class="hidden">时间戳：</label><span class="hidden"
id="modal-in-storage-end-time"></span>
<label>型号：</label><span id="modal-in-storage-product-model"></span><br>
<label>规格(mm)：</label><span id="modal-in-storage-product-size"></span><br>
<label>形态：</label><span id="modal-in-storage-product-shape"></span><br>
<label>入库总量：</label><span id="modal-in-storage-quantity"></span> kg<br>
</div>
<div class="modal-footer">
<button class="btn btn-default btn-confirm-in-modal" type="button"
id="modal-btn-submit-in-storage">是
</button>
<button class="btn btn-default" type="button" data-dismiss="modal">否</button>
</div>
</div>
</div>
</div>
</div>
<!--仓库入库记录-->
<div class="panel panel-default block-shadow warehouse-in-storage-record-table table-data"
data-url="WarehouseInStorageRecords"
data-div=".warehouse-in-storage-record-table" data-abbreviation="wisr"
data-function="common">
<div class="panel-heading">
<div class="panel-title">入库记录</div>
</div>
<div class="panel-body panel-body-border">
<label for="wisr-start-date">起始时间 </label><input type="text" name="start-time"
id="wisr-start-date" value="">
<label for="wisr-end-date">终止时间 </label><input type="text" name="end-time"
id="wisr-end-date" value="">
<button id="wisr-select" type="button" class="btn btn-primary">筛选</button>
<button id="wisr-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="wisr-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="wisr-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="wisr-aMonth" type="button" class="btn btn-primary">近一个月</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" data-modal-id="modal-in-storage-modify">
<thead>
<tr>
<th name="id" data-key="id">ID</th>
<th name="inStorageNum" data-key="inStorageNum">入库编号</th>
<th name="inStorageDate" data-key="inStorageDate">入库日期</th>
<th name="productModel" data-key="product.productModelInfo.productModel">产品型号
</th>
<th name="productSize" data-key="product.productSize">产品规格</th>
<th name="productShape" data-key="product.productShape">产品形态</th>
<th name="inStorageQuantity" data-key="inStorageQuantity">入库重量(kg)</th>
<th name="staffName" data-key="staffName">登记人</th>
</tr>
</thead>
<tbody>
<tr>
</tr>
</tbody>
</table>
</div>
<!--修改模态框-->
<div id="modal-in-storage-modify" class="modal fade modal-modify" role="dialog"
aria-hidden="true" data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">修改仓库入库记录</h4>
</div>
<div class="modal-body" data-url="WarehouseInStorageRecord/">
<div>
<label for="in-storage-id-modify">ID:</label>
<input class="data-modify input-num" type="text"
id="in-storage-id-modify"
data-key="id"
data-type="int" disabled>
</div>
<div>
<label for="in-storage-num-modify">入库编号：</label>
<input class="data-modify" type="text"
id="in-storage-num-modify"
data-key="inStorageNum" data-type="string" disabled>
</div>
<div>
<label for="in-storage-date-modify">入库日期：</label>
<input class="data-modify" type="text" id="in-storage-date-modify"
data-key="inStorageDate"
data-type="time" disabled>
</div>
<div>
<label for="in-storage-product-model-modify">产品型号：</label>
<select class="data-modify" id="in-storage-product-model-modify"
data-key="product.productModelInfo.productModel"
data-type="string">
</select>
</div>
<div>
<label for="in-storage-product-size-modify">产品规格（厚*长*宽）：</label>
<input class="data-modify size-input-editor" type="text"
id="in-storage-product-size-modify"
data-key="product.productSize" data-type="string">
</div>
<div>
<label for="in-storage-product-shape-modify">产品形态：</label>
<select class="data-modify" id="in-storage-product-shape-modify"
data-key="product.productShape" data-type="string">
<option value="直">直</option>
<option value="弯">弯</option>
</select>
</div>
<div>
<label for="in-storage-quantity-modify">入库重量(kg)：</label>
<input class="number-judge data-modify" type="number"
id="in-storage-quantity-modify"
data-key="inStorageQuantity" data-type="float">
</div>
<div>
<label for="in-storage-registrant-modify">登记人：</label>
<input class="data-modify" type="text"
id="in-storage-registrant-modify"
data-key="staffName" data-type="string" disabled>
</div>
</div>
<div class="modal-footer">
<button type="button" id="btn-commit-in-storage"
class="btn btn-default btn-commit-in-modal">提交
</button>
<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
</div>
</div>
</div>
</div>
</div>
</div>
<div class="tab-pane" id="delivery-record">
<!--配送登记-->
<div class="panel panel-default block-shadow add-form" id="add-delivery-record" data-url="">
<div class="panel-heading">
<div class="panel-title">配送登记</div>
</div>
<div class="panel-body add-table panel-body-border">
<table class="table table-bordered" style="width:1000px;margin:0">
<thead>
<tr>
<th>配送日期</th>
<th style="width:200px;margin:0">订单编号</th>
<th>型号</th>
<th>规格（厚*长*宽 单位：mm）</th>
<th>形态</th>
<th>配送量（kg）</th>
</tr>
</thead>
<tbody>
<tr>
<td><input class="add-form-item" type="text" id="add-delivery-end-date"></td>
<!-- 时间戳 -->
<td class="hidden"><input class="add-form-item" type="text"
id="add-delivery-end-time" name="deliveryDate"></td>
<td><select class="add-form-item" id="add-delivery-order-num" style="width:200px;margin:0"></select></td>
<!-- 产品信息及收货人信息根据订单编号自动生成 -->
<td><input class="add-form-item" type="text" id="add-delivery-product-model">
</td>
<td><input class="add-form-item" type="text" id="add-delivery-product-size">
</td>
<td><input class="add-form-item" type="text" id="add-delivery-product-shape">
</td>
<td class="hidden"><input class="add-form-item" type="text"
id="add-delivery-customer-name"></td>

<td><input class="add-form-item number-judge" type="text"
id="add-delivery-quantity"
placeholder="保留三位小数" name="deliveryQuantity"></td>
</tr>
</tbody>
</table>
</div>
<div class="panel-body add-header">
<button type="button" class="btn btn-info btn-submit-in-add-form"
id="btn-submit-add-delivery">提交
</button>
<button type="button" class="btn btn-danger btn-cancel-in-add-form"
id="btn-cancel-add-delivery">取消
</button>
<span class="hiddenMsg"></span>
</div>
<div id="modal-add-delivery" class="modal fade" tabindex="-1" role="dialog"
aria-hidden="true"
data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">请确认添加的信息是否正确？</h4>
</div>
<div class="modal-body">
<label>配送日期：</label><span id="modal-add-delivery-end-date"></span><br>
<!-- 时间戳 -->
<label class="hidden">时间戳：</label><span class="hidden"
id="modal-add-delivery-end-time"></span>
<label>订单编号：</label><span id="modal-add-delivery-order-num"></span><br>
<label>型号：</label><span id="modal-add-delivery-product-model"></span><br>
<label>规格（mm）：</label><span id="modal-add-delivery-product-size"></span><br>
<label>形态：</label><span id="modal-add-delivery-product-shape"></span><br>
<label>收货人：</label><span id="modal-add-delivery-customer-name"></span><br>
<label>配送量：</label><span id="modal-add-delivery-quantity"></span> kg<br>
</div>
<div class="modal-footer">
<button class="btn btn-default" type="button" id="modal-btn-submit-add-delivery">确认</button>
<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
</div>
</div>
</div>
</div>
</div>
<!-- 仓库配送记录 -->
<div class="panel panel-default block-shadow warehouse-delivery-record-table table-data"
data-url="warehouseDeliveryRecord" data-div=".warehouse-delivery-record-table"
data-abbreviation="wdr" data-function="common">
<div class="panel-heading">
<div class="panel-title">仓库配送记录</div>
</div>
<div class="panel-body panel-body-border">
<label for="wdr-start-date">起始时间 </label><input type="text" name="start-time"
id="wdr-start-date" value="">
<label for="wdr-end-date">终止时间 </label><input type="text" name="end-time"
id="wdr-end-date"
value="">
<button id="wdr-select" type="button" class="btn btn-primary">筛选</button>
<button id="wdr-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="wdr-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="wdr-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="wdr-aMonth" type="button" class="btn btn-primary">近一个月</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" data-modal-id="modal-warehouse-delivery-modify">
<thead>
<tr>
<th name="id" data-key="id">配送ID</th>
<th name="orderNum" data-key="warehouseOrder.orderNum">订单编号</th>
<th name="deliveryNum" data-key="deliveryNum">配送编号</th>
<th name="deliveryDate" data-key="deliveryDate">配送日期</th>
<th name="customerName" data-key="customer.customerName">收货人</th>
<th name="productModel"
data-key="warehouseOrder.product.productModelInfo.productModel">产品型号
</th>
<th name="productSize" data-key="warehouseOrder.product.productSize">产品规格</th>
<th name="productShape" data-key="warehouseOrder.product.productShape">产品形态</th>
<th name="deliveryQuantity" data-key="deliveryQuantity">配送数量</th>
<th name="staffName" data-key="staffName">登记人</th>
</tr>
</thead>
<tbody>
</tbody>
</table>
</div>
<!-- warehouse_delivery_racord_table仓库配送记录，table对应的th缺少data-key属性-->
<div id="modal-warehouse-delivery-modify" class="modal fade modal-modify" tabindex="-1"
role="dialog" aria-hidden="true" data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">修改仓库配送记录</h4>
</div>
<div class="modal-body" data-url="warehouseDeliveryRecord/">
<div>
<label for="warehouse-delivery-id-modify">配送ID:</label>
<input class="data-modify input-num" type="text"
id="warehouse-delivery-id-modify" data-key="id"
data-type="int" disabled>
</div>
<div>
<label for="warehouse-order-num-modify">订单编号：</label>
<input class="data-modify" type="text"
id="warehouse-order-num-modify"
data-key="warehouseOrder.orderNum" data-type="string" disabled>
</div>
<div>
<label for="warehouse-delivery-num-modify">配送编号：</label>
<input class="data-modify" type="text"
id="warehouse-delivery-num-modify"
data-key="deliveryNum" data-type="string" disabled>
</div>
<div>
<label for="warehouse-delivery-date-modify">配送日期：</label>
<input class="data-modify" type="text"
id="warehouse-delivery-date-modify" data-key="deliveryDate"
data-type="time" disabled>
</div>
<div>
<label for="warehouse-customer-modify">收货人</label>
<input class="data-modify" type="text"
id="warehouse-customer-modify" data-key="customer.customerName"
data-type="string" disabled>
</div>
<div>
<label for="warehouse-delivery-product-model-modify">产品型号：</label>
<input class="data-modify"
id="warehouse-delivery-product-model-modify"
data-key="warehouseOrder.product.productModelInfo.productModel"
data-type="string" disabled>
</div>
<div>
<label for="warehouse-delivery-product-size-modify">产品规格（厚*长*宽）：</label>
<input class="data-modify"
id="warehouse-delivery-product-size-modify"
data-key="warehouseOrder.product.productSize" data-type="string"
disabled>
</div>
<div>
<label for="warehouse-delivery-product-shape-modify">产品形态：</label>
<input class="data-modify"
id="warehouse-delivery-product-shape-modify"
data-key="warehouseOrder.product.productShape" data-type="string"
disabled>
</div>
<div>
<label for="warehouse-deliveryQuantity-modify">配送数量(kg)：</label>
<input class="data-modify number-judge" type="text"
id="warehouse-deliveryQuantity-modify"
data-key="deliveryQuantity" data-type="string">
</div>
<div>
<label for="warehouse-registrant-modify">登记人：</label>
<input class="data-modify" type="text"
id="warehouse-registrant-modify"
data-key="staffName" data-type="string" disabled>
</div>
</div>
<div class="modal-footer">
<button type="button" class="btn btn-default btn-commit-in-modal">提交</button>
<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
</div>
</div>
</div>
</div>
</div>
</div>
<div class="tab-pane" id="warehouse-inventory">
<!-- 仓库产品库存 -->
<div class="panel panel-default block-shadow warehouse-product-inventory-table table-data"
data-url="WarehouseProductInventory" data-div=".warehouse-product-inventory-table"
data-abbreviation="wpi" data-function="productInventory">
<div class="panel-heading">
<div class="panel-title">仓库产品库存</div>
</div>
<div class="panel-body panel-body-border">
<label for="wpi-product-model">产品型号 </label>
<select type="text" name="product-model"
id="wpi-product-model" value="">
<option value=""></option>
}
</select>
<label for="wpi-product-size">产品规格（厚*长*宽）： </label>
<input class="size-input-editor"
type="text" name="product-size"
id="wpi-product-size" value="">
<label for="wpi-product-shape">产品形态 </label>
<select name="product-shape" id="wpi-product-shape">
<option value="none"></option>
<option value="直">直</option>
<option value="弯">弯</option>
</select>
<button id="wpi-select" type="button" class="btn btn-primary">筛选</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" style="width:750px;">
<thead>
<tr>
<th name="productModel" data-key="product.productModelInfo.productModel">产品型号
</th>
<th name="productSize" data-key="product.productSize">产品规格</th>
<th name="productShape" data-key="product.productSize">产品形态</th>
<th name="productInventory" data-key="productInventory">库存总量(kg)</th>
</tr>
</thead>
<tbody>
<tr>
</tr>
</tbody>
</table>
</div>
</div>
</div>
<div class="tab-pane" id="warehouse-statistics">
<!-- 出库总重量统计 -->
<div class="panel panel-default block-shadow warehouse-out-storage-total-weight-statistics-table table-data"
data-url="warehouseStatistics/total"
data-div=".warehouse-out-storage-total-weight-statistics-table"
data-abbreviation="wostws" data-function="total">
<div class="panel-heading">
<div class="panel-title">出库总重量统计</div>
</div>
<div class="panel-body panel-body-border">
<label for="wostws-start-date">起始时间 </label><input type="text" name="start-time"
id="wostws-start-date" value="">
<label for="wostws-end-date">终止时间 </label><input type="text" name="end-time"
id="wostws-end-date"
value="">
<button id="wostws-select" type="button" class="btn btn-primary">查看</button>
<button id="wostws-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="wostws-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="wostws-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="wostws-aMonth" type="button" class="btn btn-primary">近一个月</button>
<button id="wostws-total" type="button" class="btn btn-primary">所有统计</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" style="width:750px;">
<thead>
<tr>
<th>产出总重量</th>
<th>配送总重量</th>
</tr>
</thead>
<tbody>
<tr>
<td data-total="totalInstorage"></td>
<td data-total="totalDelivery"></td>
</tr>
</tbody>
</table>
</div>
</div>
<!-- 仓库产品库存统计 -->
<div class="panel panel-default block-shadow warehouse-product-inventory-statistics-table table-data"
data-url="warehouseStatistics" data-div=".warehouse-product-inventory-statistics-table"
data-abbreviation="wpis" data-function="common">
<div class="panel-heading">
<div class="panel-title">仓库库存统计</div>
</div>
<div class="panel-body panel-body-border">
<label for="wpis-start-date">起始时间 </label><input type="text" name="start-time"
id="wpis-start-date" value="">
<label for="wpis-end-date">终止时间 </label><input type="text" name="end-time"
id="wpis-end-date" value="">
<button id="wpis-select" type="button" class="btn btn-primary">查看</button>
<button id="wpis-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="wpis-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="wpis-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="wpis-aMonth" type="button" class="btn btn-primary">近一个月</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" style="width:750px;">
<thead>
<tr>
<th name="productModel" data-key="product.productModelInfo.productModel">产品型号
</th>
<th name="productSize" data-key="product.productSize">产品规格</th>
<th name="productShape" data-key="product.productShape">产品形态</th>
<th name="allInStorageQuantity" data-key="allInStorageQuantity">总入库量</th>
<th name="allDeliveryQuantity" data-key="allDeliveryQuantity">总配送量</th>
</tr>
</thead>
<tbody>
</tbody>
</table>
</div>
</div>
</div>
</div>
</div>
<!--各类提示框-->
<div id="return-message-modal">
<!-- 各种提交确认框的模版,一定要放在触发按钮所处的div之后 -->
<div id="modal-confirm" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true"
data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-body">
<span class="modal-title">请确认添加的信息是否正确？</span>
<!-- 以下两个按钮都需要定义事件 -->
<button class="btn btn-default" type="button" id="btn-confirm" data-dismiss="modal">确认</button>
<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
</div>
</div>
</div>
</div>
<!-- 提交确认框结束 -->

<!-- 提交成功信息提示框 -->
<div id="modal-success" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true"
data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-body">
<span class="modal-title">添加成功！</span>
<button type="button" data-dismiss="modal">返回</button>
</div>
</div>
</div>
</div>

<!-- 提交失败信息提示框 -->
<div id="modal-fail" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true"
data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-body">
<span class="modal-title">提交失败，请检查输入的内容是否为空或错误。</span>
<button type="button" data-dismiss="modal">返回</button>
</div>
</div>
</div>
</div>

</div>
</div>
</div>
</div>

<!--反馈信息动画提示框-->
<div class="block-display" id="server-status">
<span class="server-title"></span>
</div>

<!--产品规格添加框-->
<div class="block-display hide" id="product-size-editor">
<div>
<label for="thickness-editor">厚度(mm):</label>
<input class="input-editor" id="thickness-editor" type="number" min="0.1" step="0.1">
</div>
<div>
<label for="length-editor">长度(mm):</label>
<input class="input-editor" id="length-editor" type="number" min="0.1" step="0.1">
</div>
<div>
<label for="width-editor">宽度(mm):</label>
<input class="input-editor" id="width-editor" type="number" min="0.1" step="0.1">
</div>
<div style="text-align:right;padding:5px">
<button class="btn btn-info btn-sm" id="btn-confirm-editor" type="button">确认</button>
<button class="btn btn-default btn-xs" id="btn-cancel-editor" type="button">取消</button>
<button class="btn btn-default btn-xs" id="btn-reset-editor" type="button">重置</button>
</div>
</div>

<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="../static/js/lib/jquery.min.js"></script>
<!-- Include all compiled plugins (below), or include individual files as needed -->
<script src="../static/js/lib/bootstrap.min.js"></script>
<script src="../static/js/lib/jquery-ui.min.js"></script>
<script src="../static/js/warehouse.js"></script>
</body>
</html>
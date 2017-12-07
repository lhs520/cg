<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1"/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
<title>工厂</title>

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
<li class="role-navbar-nav active" id="factory-navbar-nav"><a href="#">工厂</a></li>
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
<li class="factory-navtabs active"><a name="materialPurchase"
href="#material-purchase"
role="tab" data-toggle="tab">原料采购</a></li>
<li class="factory-navtabs"><a name="blowOn" href="#blow-on" role="tab"
data-toggle="tab">开炉登记</a></li>
<li class="factory-navtabs"><a name="productModel" href="#product-model"
role="tab" data-toggle="tab">产品型号</a></li>
<li class="factory-navtabs"><a name="productProduce" href="#product-produce"
role="tab" data-toggle="tab">产品产出</a></li>
<li class="factory-navtabs"><a name="outStorageRecord"
href="#out-storage-record"
role="tab" data-toggle="tab">出库记录</a></li>
<li class="factory-navtabs"><a name="factoryInventory"
href="#factory-inventory"
role="tab" data-toggle="tab">工厂库存</a></li>
<li class="factory-navtabs"><a name="checkWarehouse" href="#check-warehouse"
role="tab" data-toggle="tab">清仓登记</a></li>
<li class="factory-navtabs"><a name="factoryStatistics"
href="#factory-statistics" role="tab"
data-toggle="tab">工厂统计</a></li>
</ul>
</nav>
</div>
<div class="col-md-11">
<div class="" id="feature-display">
<div class="tab-content">
<div class="tab-pane active" id="material-purchase">
<!--&lt;!&ndash;原料采购登记&ndash;&gt;-->
<!--<div class="panel panel-default block-shadow add-form" id="add-material-purchase-record"-->
<!--data-url="materialPurchaseRecord">-->
<!--<div class="panel-heading">-->
<!--<div class="panel-title">原料采购</div>-->
<!--</div>-->
<!--<div class="panel-body add-table panel-body-border">-->
<!--<table class="table table-bordered" style="width:750px;margin:0">-->
<!--<thead>-->
<!--<tr>-->
<!--<th>采购日期</th>-->
<!--<th>原料种类</th>-->
<!--<th>采购量(kg)</th>-->
<!--<th>单价(元/kg)</th>-->
<!--<th>总价(元)</th>-->
<!--</tr>-->
<!--</thead>-->
<!--<tbody>-->
<!--<tr>-->
<!--<td><input class="add-form-item" type="text"-->
<!--id="material-purchase-purchase-end-date"></td>-->
<!--&lt;!&ndash; 时间戳 &ndash;&gt;-->
<!--<td class="hidden"><input class="add-form-item" type="text"-->
<!--id="material-purchase-purchase-end-time"-->
<!--name="purchaseDate"></td>-->
<!--<td>-->
<!--<select class="add-form-item" id="material-purchase-material-class">-->
<!--<option value="银">银</option>-->
<!--<option value="铜">铜</option>-->
<!--<option value="锌">锌</option>-->
<!--<option value="镉">镉</option>-->
<!--<option value="锡">锡</option>-->
<!--<option value="" class="blank-option" selected></option>-->
<!--</select>-->
<!--</td>-->
<!--<td class="hidden"><input class="add-form-item" type="text"-->
<!--id="material-purchase-material-id"-->
<!--name="material.materialId"></td>-->
<!--<td><input class="add-form-item" type="text"-->
<!--id="material-purchase-purchase-quantity" placeholder="保留三位小数"-->
<!--name="purchaseQuantity" data-smart="multiplication"></td>-->
<!--<td><input class="add-form-item" type="text"-->
<!--id="material-purchase-unit-price"-->
<!--placeholder="保留三位小数" name="unitPrice" data-smart="multiplication">-->
<!--</td>-->
<!--<td><input class="add-form-item" type="text" id="material-purchase-total-price"-->
<!--name="totalPrice" data-smart="target"></td>-->
<!--</tr>-->
<!--</tbody>-->
<!--</table>-->
<!--</div>-->
<!--<div class="panel-body add-header">-->
<!--<button type="button" class="btn btn-info btn-submit-in-add-form"-->
<!--id="btn-submit-material">-->
<!--提交-->
<!--</button>-->
<!--<button type="button" class="btn btn-danger btn-cancel-in-add-form"-->
<!--id="btn-cancel-material">取消-->
<!--</button>-->
<!--<span class="hiddenMsg"> </span>-->
<!--</div>-->
<!--<div id="modal-material-purchase" class="modal fade" tabindex="-1" role="dialog"-->
<!--aria-hidden="true" data-show="false">-->
<!--<div class="modal-dialog">-->
<!--<div class="modal-content">-->
<!--<div class="modal-header">-->
<!--<button class="close" data-dismiss="modal">&times;</button>-->
<!--<h4 class="modal-title">请确认添加的信息是否正确？</h4>-->
<!--</div>-->
<!--<div class="modal-body">-->

<!--<label>入库时间：</label><span-->
<!--id="modal-material-purchase-purchase-end-date"></span><br>-->
<!--&lt;!&ndash; 时间戳 &ndash;&gt;-->
<!--<label class="hidden">时间戳：</label><span class="hidden"-->
<!--id="modal-material-purchase-purchase-end-time"></span>-->
<!--<label>原料种类：</label><span-->
<!--id="modal-material-purchase-material-class"></span><br>-->
<!--<label class="hidden">原料ID：</label><span class="hidden"-->
<!--id="modal-material-purchase-material-id"></span>-->
<!--<label>原料总量：</label><span-->
<!--id="modal-material-purchase-purchase-quantity"></span>-->
<!--kg<br>-->
<!--<label>单价：</label><span id="modal-material-purchase-unit-price"></span> 元/kg<br>-->
<!--<label>总价：</label><span id="modal-material-purchase-total-price"></span>-->
<!--元<br>-->
<!--</div>-->
<!--<div class="modal-footer">-->
<!--<button class="btn btn-default btn-confirm-in-modal" type="button"-->
<!--id="modal-btn-submit-material">是-->
<!--</button>-->
<!--<button class="btn btn-default" type="button" data-dismiss="modal">否</button>-->
<!--<span class="hiddenMsg"></span>-->
<!--</div>-->
<!--</div>-->
<!--</div>-->
<!--</div>-->
<!--</div>-->

<!-- 原料采购记录 -->
<div class="panel panel-default block-shadow material-purchase-record-table table-data"
data-url="materialPurchaseRecords/searchResult" data-div=".material-purchase-record-table"
data-abbreviation="mpr"
data-function="common">
<div class="panel-heading">
<div class="panel-title">采购记录</div>
</div>
<div class="panel-body panel-body-border">
<label for="mpr-start-date">起始时间 </label><input type="text" name="start-time"
id="mpr-start-date" value="">
<label for="mpr-end-date">终止时间 </label><input type="text" name="end-time"
id="mpr-end-date"
value="">
<button id="mpr-select" type="button" class="btn btn-primary">筛选</button>
<button id="mpr-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="mpr-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="mpr-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="mpr-aMonth" type="button" class="btn btn-primary">近一个月</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" data-modal-id="modal-material-purchase-modify">
<thead>
<tr>
<th name="id" data-key="id">采购ID</th>
<th name="purchaseNum" data-key="purchaseNum">采购编号</th>
<th name="purchaseDate" data-key="purchaseDate">采购日期</th>
<th name="materialClass" data-key="material.materialClass">原料种类</th>
<th name="purchaseQuantity" data-key="purchaseQuantity">采购量(kg)</th>
<th name="staffName" data-key="staffName">登记人</th>
</tr>
</thead>
<tbody>
<tr>
</tr>
</tbody>
</table>
</div>
<!-- 原料采购记录修改 -->
<div id="modal-material-purchase-modify" class="modal fade modal-modify" tabindex="-1"
role="dialog" aria-hidden="true" data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">修改原料采购记录</h4>
</div>
<div class="modal-body" data-url="materialPurchaseRecord/">
<div>
<label for="material-purchase-id-modify">采购ID:</label>
<input class="data-modify input-num" type="text"
id="material-purchase-id-modify" data-key="id"
data-type="int" disabled>
</div>
<div>
<label for="material-purchase-num-modify">采购编号：</label>
<input class="data-modify" type="text"
id="material-purchase-num-modify"
data-key="purchaseNum" data-type="string" disabled>
</div>
<div>
<label for="material-purchase-date-modify">采购日期：</label>
<input class="data-modify" type="text"
id="material-purchase-date-modify" data-key="purchaseDate"
data-type="time" disabled>
</div>
<div>
<label for="material-purchase-class-modify">原料种类：</label>
<input class="data-modify" type="text"
id="material-purchase-class-modify"
data-key="material.materialClass"
data-type="time" disabled>
</div>
<div>
<label for="material-purchase-purchaseQuantity-modify">采购量(kg)：</label>
<input class="data-modify" type="text"
id="material-purchase-purchaseQuantity-modify"
data-key="purchaseQuantity" data-type="string"
data-smart="multiplication">
</div>
<div>
<label for="material-purchase-unitPrice-modify">单价(元/kg)：</label>
<input class="data-modify" type="text"
id="material-purchase-unitPrice-modify"
data-key="unitPrice" data-type="string"
data-smart="multiplication">
</div>
<div>
<label for="material-purchase-totalPrice-modify">总价(元/kg)：</label>
<input class="data-modify" type="text"
id="material-purchase-totalPrice-modify"
data-key="totalPrice" data-type="string"
data-smart="target" disabled>
</div>
<div>
<label for="material-purchase-registrant-modify">登记人：</label>
<input class="data-modify" type="text"
id="material-purchase-registrant-modify"
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
<div class="tab-pane" id="blow-on">
<!-- 开炉记录模态 -->
<div class="blowon-record-modal">
<!-- 开炉登记的模态框 -->
<div id="modal-blowon" class="modal fade" tabindex="-1" role="dialog"
aria-hidden="true" data-show="false"
data-url="BlowonRecord/createBlowonRecord">
<div class="modal-dialog">
<div class="modal-content" style="width:1000px;left:-35%">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">开炉登记 (单位：kg)</h4>
</div>
<div class="modal-body">
<div>
<label for="blow-on-data-input">开炉日期：</label>
<!-- data-date属性用于标记日期 -->
<input id="blow-on-data-input" name="blowonDate" type="text" data-date=""
data-key="blowonDate">
<button class="btn btn-info btn-xs btn-add-record-row" type="button">增加一项记录
</button>
<button class="btn btn-info btn-xs btn-delete-last-row" type="button">删除最后一项
</button>
<button class="btn btn-info btn-xs btn-clear-record" type="button">清空
</button>
</div>
<div class="table-content table-in-modal">
<table class="table table-bordered">
<tr>
<th data-key="productModelInfo.productModel">
产品型号
</th>
<th data-key="consumeAg">银(kg)</th>
<th data-key="consumeCu">铜(kg)</th>
<th data-key="consumeZn">锌(kg)</th>
<th data-key="consumeCd">镉(kg)</th>
<th data-key="consumeSn">锡(kg)</th>
<th data-key="materialConsume">原料总量(kg)</th>
<th data-key="wasteConsume">废料(kg)</th>
<th data-key="leftoverConsume">边角料(kg)</th>
<th data-key="blankProduce">胚料产出量(kg)</th>
<th data-key="lossRatio">开炉损耗比(%)</th>
</tr>
<tr>
<td data-model><select
data-model="product-model"></select>
</td>
<td><input class="row-append input-in-td"
type="text" data-smart="sum"></td>
<td><input class="row-append input-in-td"
type="text" data-smart="sum"></td>
<td><input class="row-append input-in-td"
type="text" data-smart="sum"></td>
<td><input class="row-append input-in-td"
type="text" data-smart="sum"></td>
<td><input class="row-append input-in-td"
type="text" data-smart="sum"></td>
<td data-auto="blowon"><input class="input-in-td" type="text" data-smart="target">
</td>
<td data-smart="wasteConsume"><input class="row-append input-in-td" type="text" data-smart="wasteConsume"></td>
<td data-smart="leftoverConsume"><input class="row-append input-in-td" type="text" data-smart="leftoverConsume"></td>
<td data-smart="blankProduce"><input class="row-append input-in-td" type="text" data-smart="blankProduce"></td>
<td data-ratio="lossRatio"><input class="input-in-td" type="text" data-smart="lossRatio"></td>
</tr>
</table>
</div>
</div>
<div class="modal-footer">
<button class="btn btn-default emit-modal-confirm" type="button">提交</button>
<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
</div>
</div>
</div>
</div>
</div>
<!--开炉记录-->
<div class="panel panel-default block-shadow lossratio-record-table table-data" data-url="BlowonRecord/getBlowonRecord" data-div=".lossratio-record-table" data-abbreviation="lr" data-function="common">
<div class="panel-heading">
<div class="panel-title">开炉查询</div>
</div>
<div class="panel-body panel-body-border">
<label for="lr-start-date">起始时间 </label><input type="text" name="start-time"
id="lr-start-date" value="">
<label for="lr-end-date">终止时间 </label><input type="text" name="end-time"
id="lr-end-date"
value="">
<button id="lr-select" type="button" class="btn btn-primary">筛选</button>
<button id="lr-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="lr-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="lr-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="lr-aMonth" type="button" class="btn btn-primary">近一个月</button>
<button class="btn btn-success emit-modal-blowon">开炉登记</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" data-modal-id="modal-blank-modify">
<thead>
<tr>
<th name="id" data-key="id">开炉ID</th>
<th name="blowonNum" data-key="blowonNum">开炉编号</th>
<th name="blowonDate" data-key="blowonDate">开炉日期</th>
<th name="productModel" data-key="productModelInfo.productModel">产品型号</th>
<th name="consumeAg" data-key="consumeAg">银(kg)</th>
<th name="consumeCu" data-key="consumeCu">铜(kg)</th>
<th name="consumeZn" data-key="consumeZn">锌(kg)</th>
<th name="consumeCd" data-key="consumeCd">镉(kg)</th>
<th name="consumeSn" data-key="consumeSn">锡(kg)</th>
<th name="materialConsume" data-key="materialConsume">原料消耗总量(kg)</th>
<th name="wasteConsume" data-key="wasteConsume">废料(kg)</th>
<th name="leftoverConsume" data-key="leftoverConsume">边角料(kg)</th>
<th name="blankProduce" data-key="blankProduce">胚料产出总量(kg)</th>
<th name="lossRatio" data-key="lossRatio">开炉损耗比(%)</th>
<th name="staffName" data-key="staffName">登记人</th>
</tr>
</thead>
<tbody>
</tbody>
</table>
</div>
<div id="modal-blank-modify" class="modal fade modal-modify" tabindex="-1" role="dialog"
aria-hidden="true" data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">修改工厂胚料记录</h4>
</div>
<div class="modal-body" data-url="BlowonRecord/updateBlowonRecord/">
<div>
<label for="blank-id-modify">开炉ID:</label>
<input class="data-modify input-num" type="text"
id="blank-id-modify" data-key="id"
data-type="int" disabled>
</div>
<div>
<label for="consume-num-modify">开炉编号：</label>
<input class="data-modify" type="text" id="consume-num-modify"
data-key="blowonNum" data-type="string" disabled>
</div>
<div>
<label for="blank-blowon-date-modify">开炉日期：</label>
<input class="data-modify" type="text"
id="blank-blowon-date-modify" data-key="blowonDate"
data-type="time" disabled>
</div>
<div>
<label for="blank-product-model-modify">产品型号：</label>
<select class="data-modify" id="blank-product-model-modify"
data-key="productModelInfo.productModel" data-type="string"
disabled>
</select>
</div>
<div>
<label for="blank-consumeAg-modify">银(kg)：</label>
<input class="data-modify row-append" type="text"
id="blank-consumeAg-modify"
data-key="consumeAg" data-type="string" disabled>
</div>
<div>
<label for="blank-consumeCu-modify">铜(kg)：</label>
<input class="data-modify row-append" type="text"
id="blank-consumeCu-modify"
data-key="consumeCu" data-type="string" disabled>
</div>
<div>
<label for="blank-consumeZn-modify">锌(kg)：</label>
<input class="data-modify row-append" type="text"
id="blank-consumeZn-modify"
data-key="consumeZn" data-type="string" disabled>
</div>
<div>
<label for="blank-consumeCd-modify">镉(kg)：</label>
<input class="data-modify row-append" type="text"
id="blank-consumeCd-modify"
data-key="consumeCd" data-type="string" disabled>
</div>
<div>
<label for="blank-consumeSn-modify">锡(kg)：</label>
<input class="data-modify row-append" type="text"
id="blank-consumeSn-modify"
data-key="consumeSn" data-type="string" disabled>
</div>
<div>
<label for="blank-materialConsume-modify">原料消耗总量(kg)：</label>
<input class="data-modify row-append" type="text"
id="blank-materialConsume-modify"
data-key="materialConsume" data-type="string"
data-smart="material"
disabled>
</div>
<div>
<label for="blank-wasteConsume-modify">废料(kg)：</label>
<input class="data-modify row-append" type="text"
id="blank-wasteConsume-modify"
data-key="wasteConsume" data-type="string"
data-smart="wasteConsume"
disabled>
</div>
<div>
<label for="blank-leftoverConsume-modify">边角料(kg)：</label>
<input class="data-modify row-append" type="text"
id="blank-leftoverConsume-modify"
data-key="leftoverConsume" data-type="string"
data-smart="leftoverConsume"
disabled>
</div>
<div>
<label for="blank-blank-produce-modify">胚料产出总量(kg)：</label>
<input class="data-modify row-append" type="text"
id="blank-blank-produce-modify"
data-key="blankProduce" data-smart="blank" data-type="string">
</div>
<div>
<label for="blank-lossRatio-modify">开炉损耗比(%)：</label>
<input class="data-modify row-append" type="text"
id="blank-lossRatio-modify"
data-key="lossRatio" data-type="string" data-smart="target"
readonly>
</div>
<div>
<label for="blank-registrant-modify">登记人：</label>
<input class="data-modify" type="text" id="blank-registrant-modify"
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
<div class="tab-pane" id="product-model">
<!--产品型号添加-->
<div class="panel panel-default block-shadow add-form" id="add-product"
data-url="ProductModelInfo/createProductModelInfo">
<div class="panel-heading">
<div class="panel-title">产品型号添加</div>
</div>
<div class="panel-body panel-body-border add-table">
<table class="table table-bordered" style="width:750px;margin:0">
<thead>
<tr>
<th>添加日期</th>
<th>产品型号</th>
<th>银占比(%)</th>
<th>铜占比(%)</th>
<th>锌占比(%)</th>
<th>镉占比(%)</th>
<th>锡占比(%)</th>
</tr>
</thead>
<tbody>
<tr>
<td><input class="add-form-item" type="text" id="add-product-end-date"></td>
<!-- 时间戳 -->
<td class="hide"><input class="add-form-item" type="text"
id="add-product-end-time" name="addDate"></td>
<td><input class="add-form-item" type="text" id="add-product-model"
placeholder="大写字母+数字" name="productModel"></td>
<td><input class="add-form-item" type="text" id="add-product-ratioAg"
placeholder="保留两位小数" name="ratioAg" data-smart="ratio"></td>
<td><input class="add-form-item" type="text" id="add-product-ratioCu"
placeholder="保留两位小数" name="ratioCu" data-smart="ratio"></td>
<td><input class="add-form-item" type="text" id="add-product-ratioZn"
placeholder="保留两位小数" name="ratioZn" data-smart="ratio"></td>
<td><input class="add-form-item" type="text" id="add-product-ratioCd"
placeholder="保留两位小数" name="ratioCd" data-smart="ratio"></td>
<td><input class="add-form-item" type="text" id="add-product-ratioSn"
placeholder="保留两位小数" name="ratioSn" data-smart="ratio"></td>
</tr>
</tbody>
</table>
</div>
<div class="panel-body add-header">
<button type="button" class="btn btn-info btn-submit-in-add-form"
id="btn-submit-add-product">提交
</button>
<button type="button" class="btn btn-danger btn-cancel-in-add-form"
id="btn-cancel-add-product">取消
</button>
<span class="hiddenMsg"></span>
</div>
<div id="modal-product-model" class="modal fade" tabindex="-1" role="dialog"
aria-hidden="true"
data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">请确认添加的信息是否正确？</h4>
</div>
<div class="modal-body">

<label>添加日期：</label><span id="modal-add-product-end-date"></span><br>
<!-- 时间戳 -->
<label class="hidden">时间戳：</label><span class="hidden"
id="modal-add-product-end-time"></span>
<label>型号：</label><span id="modal-add-product-model"></span><br>
<label>银占比(%)：</label><span id="modal-add-product-ratioAg"></span><br>
<label>铜占比(%)：</label><span id="modal-add-product-ratioCu"></span><br>
<label>锌占比(%)：</label><span id="modal-add-product-ratioZn"></span><br>
<label>镉占比(%)：</label><span id="modal-add-product-ratioCd"></span><br>
<label>锡占比(%)：</label><span id="modal-add-product-ratioSn"></span><br>
</div>
<div class="modal-footer">
<button class="btn btn-default btn-confirm-in-modal" type="button" id="">是</button>
<button class="btn btn-default" type="button" data-dismiss="modal">否</button>
</div>
</div>
</div>
</div>
</div>
<!-- 产品型号添加记录 -->
<div class="panel panel-default block-shadow product-model-info-record-table table-data"
data-url="ProductModelInfo/getProductModelInfo" data-div=".product-model-info-record-table"
data-abbreviation="pmir"
data-function="common">
<div class="panel-heading">
<div class="panel-title">产品型号信息</div>
</div>
<div class="panel-body panel-body-border">
<label for="pmir-start-date">起始时间 </label><input type="text" name="start-time"
id="pmir-start-date" value="">
<label for="pmir-end-date">终止时间 </label><input type="text" name="end-time"
id="pmir-end-date" value="">
<button id="pmir-select" type="button" class="btn btn-primary">筛选</button>
<button id="pmir-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="pmir-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="pmir-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="pmir-aMonth" type="button" class="btn btn-primary">近一个月</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" data-modal-id="modal-product-model-modify">
<thead>
<tr>
<th name="id" data-key="id">ID</th>
<th name="productModel" data-key="productModel">产品型号</th>
<th name="addDate" data-key="addDate">添加日期</th>
<th name="ratioAg" data-key="ratioAg">银</th>
<th name="ratioCu" data-key="ratioCu">铜</th>
<th name="ratioZn" data-key="ratioZn">锌</th>
<th name="ratioCd" data-key="ratioCd">镉</th>
<th name="ratioSn" data-key="ratioSn">锡</th>
<th name="staffName" data-key="staffName">登记人</th>
</tr>
</thead>
<tbody>
<tr>
</tr>
</tbody>
</table>
</div>
<!-- product_model_info_record_table修改产品型号信息，产品型号是不能修改的吧？ -->
<div id="modal-product-model-modify" class="modal fade modal-modify" tabindex="-1"
role="dialog"
aria-hidden="true" data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">修改产品型号记录</h4>
</div>
<div class="modal-body" data-url="ProductModelInfo/updateProductModelInfo/">
<div>
<label for="product-model-id-modify">产品ID:</label>
<input class="data-modify input-num" type="text"
id="product-model-id-modify" data-key="id"
data-type="int" disabled>
</div>
<div>
<label for="product-model-add-date-modify">添加日期：</label>
<input class="data-modify" type="text"
id="product-model-add-date-modify" data-key="addDate"
data-type="time" disabled>
</div>
<div>
<label for="product-model-modify">产品型号：</label>
<input class="data-modify" id="product-model-modify"
data-key="productModel" data-type="string"
data-smart="productModel" disabled>
</div>
<div>
<label for="product-model-ratioAg-modify">银占比(%)：</label>
<input class="data-modify" type="number" min="0" step="0.01"
id="product-model-ratioAg-modify"
data-key="ratioAg" data-type="string" data-smart="ratio">
</div>
<div>
<label for="product-model-ratioCu-modify">铜占比(%)：</label>
<input class="data-modify" type="number" min="0" step="0.01"
id="product-model-ratioCu-modify"
data-key="ratioCu" data-type="string" data-smart="ratio">
</div>
<div>
<label for="product-model-ratioZn-modify">锌占比(%)：</label>
<input class="data-modify" type="number" min="0" step="0.01"
id="product-model-ratioZn-modify"
data-key="ratioZn" data-type="string" data-smart="ratio">
</div>
<div>
<label for="product-model-ratioCd-modify">镉占比(%)：</label>
<input class="data-modify" type="number" min="0" step="0.01"
id="product-model-ratioCd-modify"
data-key="ratioCd" data-type="string" data-smart="ratio">
</div>
<div>
<label for="product-model-ratioSn-modify">锡占比(%)：</label>
<input class="data-modify" type="number" min="0" step="0.01"
id="product-model-ratioSn-modify"
data-key="ratioSn" data-type="string" data-smart="ratio">
</div>
<div>
<span id="ratioErrorMsg" style="color:red"></span>
</div>
<div>
<label for="product-model-registrant-modify">登记人：</label>
<input class="data-modify" type="text"
id="product-model-registrant-modify"
data-key="staffName" data-type="string" disabled>
</div>
</div>
<div class="modal-footer">
<button type="button" class="btn btn-default btn-commit-in-modal"
id="btn-submit-product-model-modify">提交
</button>
<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
</div>
</div>
</div>
</div>
</div>
</div>
<div class="tab-pane" id="product-produce">
<!--产品产出登记-->
<div class="panel panel-default block-shadow add-form" id="add-product-produce-record"
data-url="ProductProduce/createProductProduce">
<div class="panel-heading">
<div class="panel-title">产品产出登记</div>
</div>
<div class="panel-body add-table panel-body-border">
<table class="table table-bordered" style="width:750px;margin:0">
<thead>
<tr>
<th>加工日期</th>
<th>型号</th>
<th>规格（厚*长*宽 单位：mm）</th>
<th>形态</th>
<th>产出总量（kg）</th>
</tr>
</thead>
<tbody>
<tr>
<td><input class="add-form-item" type="text"
id="product-produce-produce-end-date">
</td>
<!-- 时间戳 -->
<td class="hidden"><input class="add-form-item" type="text"
id="product-produce-produce-end-time"
name="produceDate">
</td>
<td><select class="add-form-item" id="product-produce-product-model"
name="product.productModelInfo.productModel"></select></td>
<td><input class="add-form-item size-input-editor" type="text"
id="product-produce-product-size" placeholder="厚*长*宽"
name="product.productSize"></td>
<td><select class="add-form-item" id="product-produce-product-shape"
name="product.productShape">
<option value="直" selected>直</option>
<option value="弯">弯</option>
</select>
</td>
<td><input class="add-form-item number-judge" type="text"
id="product-produce-produce-quantity" placeholder="保留三位小数"
name="produceQuantity"></td>
</tr>
</tbody>
</table>
</div>
<div class="panel-body add-header">
<button type="button" class="btn btn-info btn-submit-in-add-form"
id="btn-submit-product-produce">提交
</button>
<button type="button" class="btn btn-danger btn-cancel-in-add-form"
id="btn-cancel-product-produce">取消
</button>
<span class="hiddenMsg"></span>
</div>
<div id="modal-product-produce" class="modal fade" tabindex="-1" role="dialog"
aria-hidden="true" data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">请确认添加的信息是否正确？</h4>
</div>
<div class="modal-body">

<label>产出日期：</label><span
id="modal-product-produce-produce-end-date"></span>
<br>
<!-- 时间戳 -->
<label class="hidden">时间戳：</label><span class="hidden"
id="modal-product-produce-produce-end-time"></span>
<label>型号：</label><span id="modal-product-produce-product-model"></span><br>
<label>规格(mm)：</label><span
id="modal-product-produce-product-size"></span><br>
<label>形态：</label><span id="modal-product-produce-product-shape"></span><br>
<label>产出总量：</label><span
id="modal-product-produce-produce-quantity"></span> kg<br>
</div>
<div class="modal-footer">
<button class="btn btn-default btn-confirm-in-modal" type="button" id="">是</button>
<button class="btn btn-default" type="button" data-dismiss="modal">否</button>
</div>
</div>
</div>
</div>
</div>
<!-- 产品产出记录 -->
<div class="panel panel-default block-shadow product-produce-record-table table-data"
data-url="ProductProduce/getProductProduce" data-div=".product-produce-record-table"
data-abbreviation="ppr" data-function="common">
<div class="panel-heading">
<div class="panel-title">产品产出查询</div>
</div>
<div class="panel-body panel-body-border">
<label for="ppr-start-date">起始时间 </label><input type="text" name="start-time"
id="ppr-start-date" value="">
<label for="ppr-end-date">终止时间 </label><input type="text" name="end-time"
id="ppr-end-date"
value="">
<button id="ppr-select" type="button" class="btn btn-primary">筛选</button>
<button id="ppr-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="ppr-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="ppr-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="ppr-aMonth" type="button" class="btn btn-primary">近一个月</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" data-modal-id="modal-product-produce-modify">
<thead>
<tr>
<th name="id" data-key="id">ID</th>
<th name="produceNum" data-key="produceNum">加工编号</th>
<th name="produceDate" data-key="produceDate">加工日期</th>
<th name="productModel" data-key="product.productModelInfo.productModel">产品型号
</th>
<th name="productSize" data-key="product.productSize">产品规格</th>
<th name="productShape" data-key="product.productShape">产品形态</th>
<th name="produceQuantity" data-key="produceQuantity">产出总量(kg)</th>
<th name="staffName" data-key="staffName">登记人</th>
</tr>
</thead>
<tbody>
<tr>
</tr>
</tbody>
</table>
</div>
<!-- product_produce_record_table修改产品产出记录 -->
<div id="modal-product-produce-modify" class="modal fade modal-modify"
role="dialog" aria-hidden="true" data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">修改产品产出记录</h4>
</div>
<div class="modal-body" data-url="ProductProduce/updateProductProduce/">
<div>
<label for="product-produce-id-modify">产出ID:</label>
<input class="data-modify input-num" type="text"
id="product-produce-id-modify" data-key="id"
data-type="int" disabled>
</div>
<div>
<label for="product-produce-num-modify">加工编号：</label>
<input class="data-modify" type="text"
id="product-produce-num-modify"
data-key="produceNum" data-type="string" disabled>
</div>
<div>
<label for="product-produce-date-modify">加工日期：</label>
<input class="data-modify" type="text"
id="product-produce-date-modify" data-key="produceDate"
data-type="time" disabled>
</div>
<div>
<label for="product-produce-product-model-modify">产品型号：</label>
<select class="data-modify"
id="product-produce-product-model-modify"
data-key="product.productModelInfo.productModel"
data-type="string"
>
</select>
</div>
<div>
<label for="product-produce-product-size-modify">产品规格(厚*长*宽)：</label>
<input class="data-modify size-input-editor"
id="product-produce-product-size-modify"
data-key="product.productSize" data-type="string">
</div>
<div>
<label for="product-produce-product-shape-modify">产品形态：</label>
<input class="data-modify"
id="product-produce-product-shape-modify"
data-key="product.productShape" data-type="string">
</div>
<div>
<label for="product-produce-quantity-modify">产出总量(kg)：</label>
<input class="number-judge data-modify" type="text"
id="product-produce-quantity-modify"
data-key="produceQuantity" data-type="string">
</div>
<div>
<label for="product-produce-registrant-modify">登记人：</label>
<input class="data-modify" type="text"
id="product-produce-registrant-modify"
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
<div class="tab-pane" id="out-storage-record">
<!--工厂出库登记-->
<div class="panel panel-default block-shadow add-form" id="add-out-storage-record"
data-url="FactoryOutStorageRecord/createFactoryOutStorageRecord">
<div class="panel-heading">
<div class="panel-title">出库登记</div>
</div>
<div class="panel-body add-table panel-body-border">
<table class="table table-bordered" style="width:750px;margin:0">
<thead>
<tr>
<th>出库日期</th>
<th>型号</th>
<th>规格（厚*长*宽 单位：mm）</th>
<th>形态</th>
<th>出库总量（kg）</th>
</tr>
</thead>
<tbody>
<tr>
<!-- 时间戳 -->
<td><input class="add-form-item" type="text" id="out-storage-end-date"></td>
<td class="hidden"><input class="add-form-item" type="text"
id="out-storage-end-time" name="outStorageDate">
</td>
<td><select class="add-form-item" id="out-storage-product-model"
name="product.productModelInfo.productModel"></select></td>
<td><input class="add-form-item size-input-editor" type="text"
id="out-storage-product-size" name="product.productSize"
placeholder="厚*长*宽"></td>
<td><select class="add-form-item" id="out-storage-product-shape"
name="product.productShape">
<option value="直" selected>直</option>
<option value="弯">弯</option>
</select>
</td>
<td><input class="add-form-item number-judge" type="text"
id="out-storage-quantity"
placeholder="保留三位小数"
name="outStorageQuantity"></td>
</tr>
</tbody>
</table>
</div>
<div class="panel-body add-header">
<button type="button" class="btn btn-info btn-submit-in-add-form"
id="btn-submit-out-storage">提交
</button>
<button type="button" class="btn btn-danger btn-cancel-in-add-form"
id="btn-cancel-out-storage">取消
</button>
<span class="hiddenMsg"></span>
</div>
<div id="modal-out-storage" class="modal fade" tabindex="-1" role="dialog"
aria-hidden="true"
data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">请确认添加的信息是否正确？</h4>
</div>
<div class="modal-body">

<label>出库日期：</label><span id="modal-out-storage-end-date"></span><br>
<!-- 时间戳 -->
<label class="hidden">时间戳：</label><span class="hidden"
id="modal-out-storage-end-time"></span>
<label>型号：</label><span id="modal-out-storage-product-model"></span><br>
<label>规格(mm)：</label><span id="modal-out-storage-product-size"></span><br>
<label>形态：</label><span id="modal-out-storage-product-shape"></span><br>
<label>出库总量：</label><span id="modal-out-storage-quantity"></span> kg<br>
</div>
<div class="modal-footer">
<button class="btn btn-default btn-confirm-in-modal" type="button"
id="modal-btn-submit-out-storage">是
</button>
<button class="btn btn-default" type="button" data-dismiss="modal">否</button>
</div>
</div>
</div>
</div>
</div>
<!-- 工厂出库记录 -->
<div class="panel panel-default block-shadow factory-out-storage-record-table table-data"
data-url="FactoryOutStorageRecord/getFactoryOutStorageRecord"
data-div=".factory-out-storage-record-table" data-abbreviation="fosr"
data-function="common">
<div class="panel-heading">
<div class="panel-title">出库记录</div>
</div>
<div class="panel-body panel-body-border">
<label for="fosr-start-date">起始时间 </label><input type="text" name="start-time"
id="fosr-start-date" value="">
<label for="fosr-end-date">终止时间 </label><input type="text" name="end-time"
id="fosr-end-date" value="">
<button id="fosr-select" type="button" class="btn btn-primary">筛选</button>
<button id="fosr-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="fosr-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="fosr-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="fosr-aMonth" type="button" class="btn btn-primary">近一个月</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" data-modal-id="modal-factory-out-storage-modify">
<thead>
<tr>
<th name="id" data-key="id">ID</th>
<th name="outStorageNum" data-key="outStorageNum">出库编号</th>
<th name="outStorageDate" data-key="outStorageDate">出库日期</th>
<th name="productModel" data-key="product.productModelInfo.productModel">产品型号
</th>
<th name="productSize" data-key="product.productSize">产品规格</th>
<th name="productShape" data-key="product.productShape">产品形态</th>
<th name="outStorageQuantity" data-key="outStorageQuantity">出库总量(kg)</th>
<th name="staffName" data-key="staffName">登记人</th>
</tr>
</thead>
<tbody>
</tbody>
</table>
</div>
<!-- factory_out_storage_record_table工厂出库记录， -->
<div id="modal-factory-out-storage-modify" class="modal fade modal-modify"
role="dialog" aria-hidden="true" data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">修改工厂出库记录</h4>
</div>
<div class="modal-body"
data-url="FactoryOutStorageRecord/updateFactoryOutStorageRecord/">
<div>
<label for="factory-out-storage-id-modify">出库ID:</label>
<input class="data-modify input-num" type="text"
id="factory-out-storage-id-modify" data-key="id"
data-type="int" disabled>
</div>
<div>
<label for="factory-out-storage-num-modify">出库编号：</label>
<input class="data-modify" type="text"
id="factory-out-storage-num-modify"
data-key="outStorageNum" data-type="string" disabled>
</div>
<div>
<label for="factory-out-storage-date-modify">出库日期：</label>
<input class="data-modify" type="text"
id="factory-out-storage-date-modify" data-key="outStorageDate"
data-type="time" disabled>
</div>
<div>
<label for="factory-out-storage-product-model-modify">产品型号：</label>
<select class="data-modify"
id="factory-out-storage-product-model-modify"
data-key="product.productModelInfo.productModel"
data-type="string"></select>
</div>
<div>
<label for="factory-out-storage-product-size-modify">产品规格（厚*长*宽）：</label>
<input class="data-modify size-input-editor"
id="factory-out-storage-product-size-modify"
data-key="product.productSize" data-type="string">
</div>
<div>
<label for="factory-out-storage-product-shape-modify">产品形态：</label>
<input class="data-modify"
id="factory-out-storage-product-shape-modify"
data-key="product.productShape" data-type="string">
</div>
<div>
<label for="factory-out-storage-quantity-modify">出库总量(kg)：</label>
<input class="number-judge data-modify" type="text"
id="factory-out-storage-quantity-modify"
data-key="outStorageQuantity" data-type="string">
</div>
<div>
<label for="factory-out-storage-registrant-modify">登记人：</label>
<input class="data-modify" type="text"
id="factory-out-storage-registrant-modify"
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
<div class="tab-pane" id="factory-inventory">
<!-- 原料库存记录 -->
<div class="panel panel-default block-shadow material-inventory-record-table table-data"
data-url="material/inventories" data-div=".material-inventory-record-table"
data-abbreviation="material-inventory-refresh" data-function="materialInventory">
<div class="panel-heading">
<div class="panel-title">原料库存信息</div>
</div>
<div class="panel-body panel-body-border">
<button id="material-inventory-refresh" type="button" class="btn btn-primary">刷新
</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" style="width:750px;">
<thead>
<tr>
<th name="materialClass">原料</th>
<th name="银">银</th>
<th name="铜">铜</th>
<th name="锌">锌</th>
<th name="镉">镉</th>
<th name="锡">锡</th>
</tr>
</thead>
<tbody>
<tr>
<td>库存(kg)</td>
<td></td>
<td></td>
<td></td>
<td></td>
<td></td>
</tr>
</tbody>

</table>
</div>
</div>
<!-- 工厂产品库存 -->
<div class="panel panel-default block-shadow factory-product-inventory-table table-data"
data-url="FactoryProductInventory/getFactoryProductInventory"
data-div=".factory-product-inventory-table" data-abbreviation="fpi"
data-function="productInventory">
<div class="panel-heading">
<div class="panel-title">工厂产品库存</div>
</div>
<div class="panel-body panel-body-border">
<label for="fpi-product-model">产品型号： </label>
<select type="text" name="product-model"
id="fpi-product-model" value="">
<option value=""></option>
}
</select>
<label for="fpi-product-size ">产品规格（厚*长*宽）： </label>
<input class="size-input-editor"
type="text" name="product-size"
id="fpi-product-size" value="">
<label for="fpi-product-shape">产品形态： </label>
<select name="product-shape" id="fpi-product-shape">
<option value="none"></option>
}
<option value="直">直</option>
<option value="弯">弯</option>
</select>
<button id="fpi-select" type="button" class="btn btn-primary">筛选</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered">
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
<div class="tab-pane" id="check-warehouse">
<!--清仓按钮模态-->
<div class="panel panel-default block-shadow">
<div class="panel-body">
<div class="row">
<div class="col-md-3"></div>
<div class="col-md-6">
<!-- 清仓记录 -->
<div class="check-warehouse-record-modal row">
<!-- “其他清仓”的模态框 -->
<div class="col-md-3">
<!-- id已改 -->
<button class="btn btn-success emit-modal-check-warehouse-others">其他清仓登记
</button>
</div>
<div id="modal-check-warehouse-others" class="modal fade" tabindex="-1"
role="dialog" aria-hidden="true" data-show="false"
data-url="CheckWarehouseOthersRecord/createCheckWarehouseOthersRecord">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">其他清仓 (单位：kg)</h4>
</div>
<div class="modal-body">
<div>
<label>清仓日期：</label>
<input name="checkDate" type="text" data-date=""
data-key="checkDate">
<button class="btn btn-info btn-xs btn-add-record-row" type="button">增加一项记录
</button>
<button class="btn btn-info btn-xs btn-delete-last-row" type="button">删除最后一项
</button>
<button class="btn btn-info btn-xs btn-clear-record" type="button">清空
</button>
</div>
<div class="table-content table-in-modal">
<table class="table table-bordered">
<tr>
<th data-key="productModelInfo.productModel">
产品型号
</th>
<th data-key="blankInventory">胚料</th>
<th data-key="semifinishedProductInventory">
半成品
</th>
<th data-key="wasteInventory">废料</th>
<th data-key="leftoverInventory">边角料</th>
</tr>
<tr>
<td data-model><select
data-model="product-model"></select>
</td>
<td><input class="row-append input-in-td"
type="text"></td>
<td><input class="row-append input-in-td"
type="text"></td>
<td><input class="row-append input-in-td"
type="text"></td>
<td><input class="row-append input-in-td"
type="text"></td>
</tr>
</table>
</div>
</div>
<div class="modal-footer">
<button class="btn btn-default emit-modal-confirm" type="button">提交</button>
<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
</div>
</div>
</div>
</div>
<!-- “其他清仓”的模态框结束 -->
<!-- “产品清仓”的模态框-->
<div class="col-md-3">
<!-- 触发模态框的按钮或链接 ，名字自改-->
<button class="btn btn-success emit-modal-check-warehouse-product">
产品清仓登记
</button>
</div>
<div id="modal-check-warehouse-product" class="modal fade" tabindex="-1"
role="dialog" aria-hidden="true" data-show="false"
data-url="CheckWarehouseOthersRecord/createCheckWarehouseProductRecord">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">产品清仓 (单位：kg)</h4>
</div>
<div class="modal-body">
<div>
<label>清仓日期：</label>
<input name="checkDate" type="text" data-date=""
data-key="checkDate">
<button class="btn btn-info btn-xs btn-add-record-row" type="button">增加一项记录
</button>
<button class="btn btn-info btn-xs btn-delete-last-row" type="button">删除最后一项
</button>
<button class="btn btn-info btn-xs btn-clear-record" type="button">清空
</button>
</div>
<div class="table-content table-in-modal">
<table class="table table-bordered">
<tr>
<th data-key="product.productModelInfo.productModel">
产品型号
</th>
<th data-key="product.productSize">产品规格</th>
<th data-key="product.productShape">产品形态</th>
<th data-key="productInventory">产品重量</th>
</tr>
<tr>
<td data-model-cwp><select
data-model-cwp="product-model-cwp"
id="cwp-model"></select></td>
<td data-size><select
data-size="product-size"></select></td>
<td data-shape><select
data-shape="product-shape">
</select></td>
<td><input class="row-append input-in-td"
type="text"></td>
</tr>
</table>
</div>
</div>
<div class="modal-footer">
<button class="btn btn-default emit-modal-confirm" type="button">提交</button>
<button class="btn btn-default" type="button" data-dismiss="modal">取消</button>
</div>
</div>
</div>
</div>
<!-- “产品清仓”的模态框结束 -->

<div class="col-md-3">
<button class="btn btn-primary" id="cwor-record-button" type="button">
其他清仓查询
</button>
</div>
<div class="col-md-3">
<button class="btn btn-primary" id="cwpr-record-button" type="button">
产品清仓查询
</button>
</div>

</div>
</div>
<div class="col-md-3"></div>
</div>
</div>
</div>
<!-- 其他清仓记录 -->
<div class="panel panel-default block-shadow check-warehouse-others-record-table table-data"
data-url="CheckWarehouseOthersRecord/getCheckWarehouseOthersRecord "
data-div=".check-warehouse-others-record-table " data-abbreviation="cwor"
data-function="common" style="display: block;">
<div class="panel-heading">
<div class="panel-title">其他清仓记录</div>
</div>
<div class="panel-body panel-body-border">
<label for="cwor-start-date">起始时间 </label><input type="text" name="start-time"
id="cwor-start-date" value="">
<label for="cwor-end-date">终止时间 </label><input type="text" name="end-time"
id="cwor-end-date" value="">
<button id="cwor-select" type="button" class="btn btn-primary">筛选</button>
<button id="cwor-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="cwor-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="cwor-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="cwor-aMonth" type="button" class="btn btn-primary">近一个月</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" data-modal-id="modal-check-warehouse-modify">
<thead>
<tr>
<th name="id" data-key="id">ID</th>
<th name="checkNum" data-key="checkNum">清仓编号</th>
<th name="checkDate" data-key="checkDate">清仓日期</th>
<th name="productModel" data-key="productModelInfo.productModel">产品型号</th>
<th name="blankInventory" data-key="blankInventory">胚料</th>
<th name="semifinishedProductInventory" data-key="semifinishedProductInventory">
半成品
</th>
<th name="wasteInventory" data-key="wasteInventory">废料</th>
<th name="leftoverInventory" data-key="leftoverInventory">边角料</th>
<th name="staffName" data-key="staffName">登记人</th>
</tr>
</thead>
<tbody>
<tr>
</tr>
</tbody>
</table>
</div>
<!-- check_warehouse_record_table修改清仓记录，对应的data-url路径上传的是其他清仓的？？ -->
<div id="modal-check-warehouse-modify" class="modal fade modal-modify" tabindex="-1"
role="dialog" aria-hidden="true" data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">修改工厂清仓记录</h4>
</div>
<div class="modal-body"
data-url="CheckWarehouseOthersRecord/updateCheckWarehouseOthersRecord/">
<div>
<label for="check-warehouse-id-modify">清仓ID:</label>
<input class="data-modify input-num" type="text"
id="check-warehouse-id-modify" data-key="id"
data-type="int" disabled>
</div>
<div>
<label for="check-warehouse-num-modify">清仓编号：</label>
<input class="data-modify" type="text"
id="check-warehouse-num-modify"
data-key="checkNum" data-type="string" disabled>
</div>
<div>
<label for="check-warehouse-date-modify">清仓日期：</label>
<input class="data-modify" type="text"
id="check-warehouse-date-modify" data-key="checkDate"
data-type="time" disabled>
</div>
<div>
<label for="check-warehouse-product-model-modify">产品型号：</label>
<select class="data-modify"
id="check-warehouse-product-model-modify"
data-key="productModelInfo.productModel" data-type="string"
disabled>
</select>
</div>
<div>
<label for="check-warehouse-blankInventory-modify">胚料：</label>
<input class="data-modify row-append" type="text"
id="check-warehouse-blankInventory-modify"
data-key="blankInventory" data-type="string">
</div>
<div>
<label for="check-warehouse-semifinishedProductInventory-modify">半成品：</label>
<input class="data-modify row-append" type="text"
id="check-warehouse-semifinishedProductInventory-modify"
data-key="semifinishedProductInventory" data-type="string">
</div>
<div>
<label for="check-warehouse-wasteInventory-modify">废料：</label>
<input class="data-modify row-append" type="text"
id="check-warehouse-wasteInventory-modify"
data-key="wasteInventory" data-type="string">
</div>
<div>
<label for="check-warehouse-leftoverInventory-modify">边角料：</label>
<input class="data-modify row-append" type="text"
id="check-warehouse-leftoverInventory-modify"
data-key="leftoverInventory" data-type="string">
</div>
<div>
<label for="check-warehouse-registrant-modify">登记人：</label>
<input class="data-modify" type="text"
id="check-warehouse-registrant-modify"
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
<!-- 产品清仓记录 -->
<div class="panel panel-default block-shadow check-warehouse-product-record-table table-data"
data-url="CheckWarehouseOthersRecord/getCheckWarehouseProductRecord"
data-div=".check-warehouse-product-record-table" data-abbreviation="cwpr"
data-function="common"
style="display: none;">
<div class="panel-heading">
<div class="panel-title">产品清仓查询</div>
</div>
<div class="panel-body panel-body-border">
<label for="cwpr-start-date">起始时间 </label><input type="text" name="start-time"
id="cwpr-start-date" value="">
<label for="cwpr-end-date">终止时间 </label><input type="text" name="end-time"
id="cwpr-end-date" value="">
<button id="cwpr-select" type="button" class="btn btn-primary">筛选</button>
<button id="cwpr-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="cwpr-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="cwpr-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="cwpr-aMonth" type="button" class="btn btn-primary">近一个月</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered"
data-modal-id="modal-check-warehouse-product-modify">
<thead>
<tr>
<th name="id" data-key="id">ID</th>
<th name="checkNum" data-key="checkNum">产品清仓编号</th>
<th name="checkDate" data-key="checkDate">清仓日期</th>
<th name="productModel" data-key="product.productModelInfo.productModel">产品型号
</th>
<th name="productSize" data-key="product.productSize">产品规格</th>
<th name="productShape" data-key="product.productShape">产品形态</th>
<th name="productInventory" data-key="productInventory">产品数量(kg)</th>
<th name="staffName" data-key="staffName">登记人</th>
</tr>
</thead>
<tbody>
<tr>
</tr>
</tbody>
</table>
</div>
<!-- check_warehouse_record_table修改产品清仓记录 -->
<div id="modal-check-warehouse-product-modify" class="modal fade modal-modify"
role="dialog" aria-hidden="true" data-show="false">
<div class="modal-dialog">
<div class="modal-content">
<div class="modal-header">
<button class="close" data-dismiss="modal">&times;</button>
<h4 class="modal-title">修改产品清仓记录</h4>
</div>
<div class="modal-body"
data-url="CheckWarehouseOthersRecord/updateCheckWarehouseProductRecord/">
<div>
<label for="check-warehouse-product-id-modify">产品清仓ID:</label>
<input class="data-modify input-num" type="text"
id="check-warehouse-product-id-modify" data-key="id"
data-type="int" disabled>
</div>
<div>
<label for="check-warehouse-product-num-modify">产品清仓编号：</label>
<input class="data-modify" type="text"
id="check-warehouse-product-num-modify"
data-key="checkNum" data-type="string" disabled>
</div>
<div>
<label for="check-warehouse-product-date-modify">产品清仓日期：</label>
<input class="data-modify" type="text"
id="check-warehouse-product-date-modify" data-key="checkDate"
data-type="time" disabled>
</div>
<div>
<label for="check-warehouse-product-product-model-modify">产品型号：</label>
<select class="data-modify"
id="check-warehouse-product-product-model-modify"
data-key="product.productModelInfo.productModel"
data-type="string">
</select>
</div>
<div>
<label for="check-warehouse-product-product-size-modify">产品规格（厚*长*宽）：</label>
<input class="data-modify size-input-editor"
id="check-warehouse-product-product-size-modify"
data-key="product.productSize" data-type="string">
</div>
<div>
<label for="check-warehouse-product-product-shape-modify">产品形态：</label>
<input class="data-modify"
id="check-warehouse-product-product-shape-modify"
data-key="product.productShape" data-type="string">
</div>
<div>
<label for="check-warehouse-product-inventory-modify">产品总量(kg)：</label>
<input class="data-modify row-append" type="text"
id="check-warehouse-product-inventory-modify"
data-key="productInventory" data-type="string">
</div>
<div>
<label for="check-warehouse-product-registrant-modify">登记人：</label>
<input class="data-modify" type="text"
id="check-warehouse-product-registrant-modify"
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
<div class="tab-pane" id="factory-statistics">
<!-- 出库总重量统计 -->
<div class="panel panel-default block-shadow factory-out-storage-total-weight-statistics-table table-data"
data-url="FactoryStatistics/outputs"
data-div=".factory-out-storage-total-weight-statistics-table"
data-abbreviation="fostws" data-function="total">
<div class="panel-heading">
<div class="panel-title">出库总重量统计</div>
</div>
<div class="panel-body panel-body-border">
<label for="fostws-start-date">起始时间 </label><input type="text" name="start-time"
id="fostws-start-date" value="">
<label for="fostws-end-date">终止时间 </label><input type="text" name="end-time"
id="fostws-end-date"
value="">
<button id="fostws-select" type="button" class="btn btn-primary">查看</button>
<button id="fostws-threeDays" type="button" class="btn btn-primary">近三天</button>
<button id="fostws-aWeek" type="button" class="btn btn-primary">近一周</button>
<button id="fostws-twoWeeks" type="button" class="btn btn-primary">近两周</button>
<button id="fostws-aMonth" type="button" class="btn btn-primary">近一个月</button>
<button id="fostws-total" type="button" class="btn btn-primary">所有统计</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" style="width:750px;">
<thead>
<tr>
<th>产出总重量</th>
<th>出库总重量</th>
</tr>
</thead>
<tbody>
<tr>
<td data-total="produceTotal"></td>
<td data-total="outStorageTotal"></td>
</tr>
</tbody>
</table>
</div>
</div>
<!--产品库存统计-->
<div class="panel panel-default block-shadow product-inventory-statistics-table table-data"
data-url="FactoryStatistics/getFactoryStatistics"
data-div=".product-inventory-statistics-table" data-abbreviation="pis"
data-function="common">
<div class="panel-heading">
<div class="panel-title">产品库存统计</div>
</div>
<div class="panel-body panel-body-border">
<label for="pis-start-date">起始时间 </label><input type="text" name="start-time"
id="pis-start-date" value="">
<label for="pis-end-date">终止时间 </label><input type="text" name="end-time"
id="pis-end-date"
value="">
<button id="pis-select" type="button" class="btn btn-primary">查看</button>
</div>
<div class="panel-body table-content">
<table class="table table-bordered" style="width:750px;">
<thead>
<tr>
<th name="productModel" data-key="product.productModelInfo.productModel">产品型号
</th>
<th name="productSize" data-key="product.productSize">产品规格</th>
<th name="productShape" data-key="product.productShape">产品形态</th>
<th name="allProduceQuantity" data-key="allProduceQuantity">产出总重量</th>
<th name="allOutStorageQuantity" data-key="allOutStorageQuantity">出库总重量</th>
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
<script src="../static/js/factory.js"></script>
</body>
</html>
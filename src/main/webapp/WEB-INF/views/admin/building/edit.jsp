<%--
  Created by IntelliJ IDEA.
  User: ductai
  Date: 15/2/25
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
  <title>Thông tin tòa nhà</title>
</head>
<body>
  <div class="main-content">
    <div class="main-content-inner">
      <div class="breadcrumbs" id="breadcrumbs">
        <script type="text/javascript">
            try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
        </script>

        <ul class="breadcrumb">
          <li>
            <i class="ace-icon fa fa-home home-icon"></i>
            <a href="#">Home</a>
          </li>
          <li class="active">Dashboard</li>
        </ul><!-- /.breadcrumb -->

      </div>

      <div class="page-content">

        <div class="page-header">
          <h1>
            Thông tin tòa nhà
          </h1>
        </div><!-- /.page-header -->

        <!-- Form edit -->
        <div class="row">
          <div class="col-xs-12">
            <form class="form-horizontal" role="form" id="form-edit">
              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Tên toà nhà</label>
                </div>
                <div class="col-xs-9">
                  <input type="text" class="form-control" id="name" name="name"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Quận</label>
                </div>
                <div class="col-xs-9">
                  <select name="districtId" id="districtId">
                    <option value="">--Chọn quận--</option>
                    <option value="1">Quận 1</option>
                    <option value="2">Quận 2</option>
                    <option value="3">Quận 3</option>
                    <option value="4">Quận 4</option>
                  </select>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Phường</label>
                </div>
                <div class="col-xs-9">
                  <input type="text" class="form-control" id="ward" name="ward"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Đường</label>
                </div>
                <div class="col-xs-9">
                  <input type="text" class="form-control" id="street" name="street"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Số tầng hầm</label>
                </div>
                <div class="col-xs-9">
                  <input type="number" class="form-control" id="numberofbasement" name="numberofbasement"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Hướng</label>
                </div>
                <div class="col-xs-9">
                  <input type="text" class="form-control" id="direction" name="direction"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Hạng</label>
                </div>
                <div class="col-xs-9">
                  <input type="number" class="form-control" id="rank" name="rank"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Diện tích sàn</label>
                </div>
                <div class="col-xs-9">
                  <input type="number" class="form-control" id="floorarea" name="floorarea"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Diện tích trống</label>
                </div>
                <div class="col-xs-9">
                  <input type="number" class="form-control" id="emptyarea" name="emptyarea"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Diện tích thuê</label>
                </div>
                <div class="col-xs-9">
                  <input type="text" class="form-control" id="rentarea" name="rentarea"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Giá thuê</label>
                </div>
                <div class="col-xs-9">
                  <input type="number" class="form-control" id="rentprice" name="rentprice"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Phí môi giới</label>
                </div>
                <div class="col-xs-9">
                  <input type="number" class="form-control" id="brokeragefee" name="brokeragefee"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Tên quản lý</label>
                </div>
                <div class="col-xs-9">
                  <input type="text" class="form-control" id="managername" name="managername"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">SĐT quản lý</label>
                </div>
                <div class="col-xs-9">
                  <input type="text" class="form-control" id="managerphonenumber" name="managerphonenumber"/>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Nhân viên</label>
                </div>
                <div class="col-xs-9">
                  <select class="form-control">
                    <option value="">--Chọn nhân viên--</option>
                    <option value="">Nguyễn Văn A</option>
                    <option value="">Nguyễn Văn B</option>
                    <option value="">Nguyễn Văn C</option>
                  </select>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3">
                  <label class="name">Loại tòa nhà</label>
                </div>
                <div class="col-xs-4" style="display: flex; flex-direction: row; justify-content: space-between;">
                  <label class="checkbox-inline">
                    <input type="checkbox" name="typecode" value="noi-that"/> Nội thất
                  </label>
                  <label class="checkbox-inline">
                    <input type="checkbox" name="typecode" value="nguyen-can"/> Nguyên căn
                  </label>
                  <label class="checkbox-inline">
                    <input type="checkbox" name="typecode" value="tang-tret"/> Tầng trệt
                  </label>
                </div>
              </div>

              <div class="form-group" role="form">
                <div class="col-xs-3"></div>
                <div class="col-xs-9">
                  <button class="btn btn-xs btn-info" id="btnAddBuilding">
                    <span class="bigger-110 no-text-shadow">Thêm tòa nhà</span>
                  </button>
                  <button class="btn btn-xs btn-danger" id="btnCancel">
                    <span class="bigger-110 no-text-shadow">Hủy thao tác</span>
                  </button>
                </div>
              </div>
            </form>

          </div>
        </div>



      </div><!-- /.page-content -->
    </div>
  </div><!-- /.main-content -->
</body>
</html>

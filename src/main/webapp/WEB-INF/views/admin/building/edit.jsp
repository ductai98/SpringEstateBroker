<%--
  Created by IntelliJ IDEA.
  User: ductai
  Date: 15/2/25
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingApi" value="/api/building" />
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
          <form:form modelAttribute="buildingInfo" id="info-form">
            <div class="col-xs-12">
              <form class="form-horizontal" role="form">
                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Tên toà nhà</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input class="form-control" path="name"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Quận</label>
                  </div>
                  <div class="col-xs-9">
                    <form:select path="district">
                      <form:option value="">--Chọn quận--</form:option>
                      <form:options items="${districts}" />
                    </form:select>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Phường</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="text" class="form-control" path="ward"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Đường</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="text" class="form-control" path="street"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Số tầng hầm</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="number" class="form-control" path="numberOfBasement"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Hướng</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="text" class="form-control" path="direction"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Hạng</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="number" class="form-control" path="level"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Diện tích sàn</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="number" class="form-control" path="floorArea"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Diện tích trống</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="number" class="form-control" path="emptyArea"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Diện tích thuê</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="text" class="form-control" path="rentArea"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Giá thuê</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="number" class="form-control" path="rentPrice"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Phí môi giới</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="number" class="form-control" path="brokerageFee"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Tên quản lý</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="text" class="form-control" path="managerName"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">SĐT quản lý</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="text" class="form-control" path="managerPhoneNumber"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Loại tòa nhà</label>
                  </div>
                  <div class="col-xs-4" style="display: flex; flex-direction: row; justify-content: space-between;">
                    <form:checkboxes items="${typeCodes}" path="typeCode"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3"></div>
                  <div class="col-xs-9">
                    <c:if test="${not empty buildingInfo.id}">
                      <button class="btn btn-xs btn-info" id="btnAddOrUpdate">
                        <span class="bigger-110 no-text-shadow">Cập nhật thông tin</span>
                      </button>
                    </c:if>

                    <c:if test="${empty buildingInfo.id}">
                      <button class="btn btn-xs btn-info" id="btnAddOrUpdate">
                        <span class="bigger-110 no-text-shadow">Thêm tòa nhà</span>
                      </button>
                    </c:if>
                    <button class="btn btn-xs btn-danger" id="btnCancel">
                      <span class="bigger-110 no-text-shadow">Hủy thao tác</span>
                    </button>
                  </div>
                </div>
                <form:hidden path="id"/>
              </form>
            </div>
          </form:form>
        </div>
      </div><!-- /.page-content -->
    </div>
  </div><!-- /.main-content -->
  <script>
    $('#btnAddOrUpdate').click(function (e) {
      e.preventDefault();
      var data = {};

      var typecode = [];

      var formData = $('#info-form').serializeArray();

      $.each(formData, function (index, v) {
        if (v.name != 'typeCode') {
            data[v.name] = v.value;
        } else {
            typecode.push(v.value);
        }
      });

      data['typeCode'] = typecode;

      if (typecode.length === 0) {
        if (data.id == null) {
          window.location.href = "/admin/building-edit?typeCode=required";
        } else {
          window.location.href = "/admin/building-edit-" + data.id + "?typeCode=required";
        }
      } else {
        addOrUpdateBuilding(data);
      }
    });

    function addOrUpdateBuilding(data) {
      $.ajax({
        type: "POST",
        url: "${buildingApi}",
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: "JSON",
        success: function (response) {
            console.log({"success": response});
            console.log(JSON.stringify(data));
        },
        error: function (error) {
            console.log({"error" : error});
        }
        });
    }

    $('#btnCancel').click(function (e) {
      e.preventDefault();
      window.location.href = "/admin/building-search";
    });
  </script>
</body>
</html>
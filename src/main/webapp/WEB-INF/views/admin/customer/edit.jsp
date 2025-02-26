<%--
  Created by IntelliJ IDEA.
  User: ductai
  Date: 15/2/25
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customer" value="/api/customer" />
<html>
<head>
  <title>Thông tin khách hàng</title>
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
          <form:form modelAttribute="customerInfo" id="info-form">
            <div class="col-xs-12">
              <form class="form-horizontal" role="form">
                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Tên khách hàng</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input class="form-control" path="fullname"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Số điện thoại</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="text" class="form-control" path="phone"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Email</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="text" class="form-control" path="email"/>
                  </div>
                </div>

                <div class="form-group" role="form">
                  <div class="col-xs-3">
                    <label class="name">Nhu cầu</label>
                  </div>
                  <div class="col-xs-9">
                    <form:input type="text" class="form-control" path="demand"/>
                  </div>
                </div>


                <div class="form-group" role="form">
                  <div class="col-xs-3"></div>
                  <div class="col-xs-9">
                    <c:if test="${not empty customerInfo.id}">
                      <button class="btn btn-xs btn-info" id="btnAddOrUpdate">
                        <span class="bigger-110 no-text-shadow">Cập nhật thông tin</span>
                      </button>
                    </c:if>

                    <c:if test="${empty customerInfo.id}">
                      <button class="btn btn-xs btn-info" id="btnAddOrUpdate">
                        <span class="bigger-110 no-text-shadow">Thêm khách hàng</span>
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

      var formData = $('#info-form').serializeArray();

      $.each(formData, function (index, v) {
        data[v.name] = v.value;
      });

      var phone = data.phone;

      if (phone === '' || phone == null) {
        if (data.id == null) {
          window.location.href = "/admin/customer-edit?phone=required";
        } else {
          window.location.href = "/admin/customer-edit-" + data.id + "?phone=required";
        }
      } else {
        addOrUpdateCustomer(data);
      }
    });

    function addOrUpdateCustomer(data) {
      $.ajax({
        type: "POST",
        url: "${customer}",
        data: JSON.stringify(data),
        contentType: "application/json",
        dataType: "JSON",
        success: function (response) {
          if (response.data) {
            window.location.href = response.data;
          }
        },
        error: function (error) {
            console.log({"error" : error});
        }
        });
    }

    $('#btnCancel').click(function (e) {
      e.preventDefault();
      window.location.href = "/admin/customer-search";
    });
  </script>
</body>
</html>
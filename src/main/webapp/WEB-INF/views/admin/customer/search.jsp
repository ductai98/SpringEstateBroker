<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customerSearchUrl" value="/admin/customer-search"/>
<html>
<head>
  <title>Quản lý khách hàng</title>
</head>
<body>
<div class="main-content">
  <div class="main-content-inner">
    <div class="breadcrumbs" id="breadcrumbs">
      <script type="text/javascript">
        try {
          ace.settings.check('breadcrumbs', 'fixed')
        } catch (e) {
        }
      </script>

      <ul class="breadcrumb">
        <li>
          <i class="ace-icon fa fa-home home-icon"></i>
          <a href="#">Home</a>
        </li>
        <li class="active">Customer</li>
      </ul><!-- /.breadcrumb -->

    </div>

    <div class="page-content">

      <div class="page-header">
        <h1>
          Tìm kiếm khách hàng
          <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            overview &amp; stats
          </small>
        </h1>
      </div><!-- /.page-header -->

      <!-- Form tìm kiếm -->
      <div class="row">
        <div class="col-xs-12">
          <div class="widget-box">
            <div class="widget-header">
              <h5 class="widget-title">Tìm kiếm</h5>

              <div class="widget-toolbar">
                <a href="#" data-action="collapse">
                  <i class="ace-icon fa fa-chevron-up"></i>
                </a>
              </div>
            </div>

            <div class="widget-body" style="font-family: 'Times New Roman', Times, serif;">
              <div class="widget-main">
                <form:form modelAttribute="searchModel" action="${customerSearchUrl}" method="get" id="search-form">
                  <div class="row">
                    <div class="form-group" style="display: contents">
                      <div class="row">
                        <div class="col-xs-12">
                          <div class="col-xs-4">
                            <label class="name">Tên khách hàng</label>
                            <form:input class="form-control" path="fullname"/>
                          </div>
                          <div class="col-xs-4">
                            <label class="name">Số điện thoại</label>
                            <form:input type="text" class="form-control" path="phone"/>
                          </div>

                          <div class="col-xs-4">
                            <label class="name">Email</label>
                            <form:input type="text" class="form-control" path="email"/>
                          </div>
                        </div>

                        <security:authorize access="hasRole('ADMIN')">
                          <div class="col-xs-12">
                            <div class="col-xs-4">
                              <label class="name">Nhân viên</label>
                              <form:select class="form-control" path="staffId">
                                <form:option value="">--Chọn nhân viên--</form:option>
                                <form:options items="${staffs}"/>
                              </form:select>
                            </div>
                          </div>
                        </security:authorize>

                        <div class="col-xs-12" style="margin: 1em 0 1.5em;">
                          <div class="col-xs-6">
                            <button class="btn btn-xs btn-success" id="btnSearch">
                              <i class="ace-icon fa fa-search bigger-110"></i>
                              <span class="bigger-110 no-text-shadow">Tìm kiếm</span>
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </div>
                </form:form>
              </div>
            </div>
          </div>

          <security:authorize access="hasAnyRole('ADMIN', 'STAFF')">
            <div class="pull-right" style="margin: -3px 0 0 0;">
              <a href="/admin/customer-edit">
                <button class="btn btn-sm btn-info" title="Thêm khách hàng">
                  <i class="ace-icon fa fa-plus bigger-120"></i>
                </button>
              </a>
              <button class="btn btn-sm btn-danger" title="Xóa khách hàng" id="btnDelete">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
              </button>
            </div>
          </security:authorize>

        </div>
      </div>

      <!-- Bảng kết quả tìm kiếm -->
      <div class="row">
        <div class="col-xs-12">
          <table id="customer-table" class="table table-striped table-bordered table-hover"
                 style="margin: 3em 0 1.5em;">
            <thead>
            <tr>
              <th class="center">
                <label class="pos-rel">
                  <input type="checkbox" class="ace">
                  <span class="lbl"></span>
                </label>
              </th>
              <th>Tên khách hàng</th>
              <th>Số điện thoại</th>
              <th>Email</th>
              <th>Nhu cầu</th>
              <th>Người thêm</th>
              <th>Ngày thêm</th>
              <th>Thao tác</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${customers}" var="customer">
              <tr>
                <td class="center">
                  <label class="pos-rel">
                    <input type="checkbox" class="ace" name="check-list" value="${customer.id}">
                    <span class="lbl"></span>
                  </label>
                </td>

                <td>
                  <a href="#">${customer.fullname}</a>
                </td>

                <td>${customer.phone}</td>

                <td>${customer.email}</td>

                <td>${customer.demand}</td>

                <td>${customer.createdBy}</td>

                <td>${customer.createdDate}</td>

                <td>

                  <div class="hidden-sm hidden-xs btn-group">
                    <security:authorize access="hasRole('ADMIN')">
                      <button
                          class="btn btn-xs btn-success"
                          title="Giao toa nha"
                          onclick="assignmentcustomer(${customer.id})">
                        <i class="ace-icon glyphicon glyphicon-list bigger-120"></i>
                      </button>
                    </security:authorize>
                    <security:authorize access="hasAnyRole('STAFF', 'ADMIN')">
                      <a class="btn btn-xs btn-info" title="Sua thong tin" href="/admin/customer-edit-${customer.id}">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                      </a>
                    </security:authorize>
                    <security:authorize access="hasRole('ADMIN')">
                    <button class="btn btn-xs btn-danger" title="Xoa toa nha"
                            onclick="deletecustomer(${customer.id})">
                      <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>
                  </div>
                  </security:authorize>

                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
        </div><!-- /.span -->
      </div>

    </div><!-- /.page-content -->
  </div>
</div><!-- /.main-content -->

<%--  Assign staff modal--%>
<div class="modal fade" id="assignmentcustomerModal" role="dialog">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">Danh sách nhân viên</h5>
      </div>
      <div class="modal-body" id="modal-staff">
        <table id="staff-list" class="table table-striped table-bordered table-hover">
          <thead>
          <tr>
            <th class="center">
              Chọn
            </th>
            <th class="center">Tên nhân viên</th>
          </tr>
          </thead>

          <tbody>

          </tbody>
        </table>
        <input type="hidden" id="customerId" name="customerId" value="1"/>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" data-dismiss="modal" id="btnAssign">Save changes</button>
      </div>
    </div>
  </div>
</div>

<!-- Confirmation Modal -->
<div class="modal fade" id="deleteConfirmModal" tabindex="-1" role="dialog" aria-labelledby="deleteConfirmModalLabel"
     aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deleteConfirmModalLabel">Confirm</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        Are you sure you want to delete the selected customer(s)?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
        <button type="button" class="btn btn-danger" id="confirmDelete">Yes, Delete</button>
      </div>
    </div>
  </div>
</div>
<script>

  function getStaffs(customerId) {
    $.ajax({
      type: "GET",
      url: "/api/customer/" + customerId + "/staffs",
      contentType: "application/json",
      dataType: "JSON",
      success: function (response) {
        console.log(response);
        var row = '';
        let staffs = response.data;
        $.each(staffs, function (index, value) {
          row += '<tr class="center">';
          row += '<td class="center">';
          row += '<label class="pos-rel">';
          row += '<input type="checkbox" class="ace" value="' + value.staffId + '" ' + value.checked + '/>';
          row += '<span class="lbl"></span>';
          row += '</label>';
          row += '</td>';

          row += '<td>';
          row += value.fullName;
          row += '</td>';
          row += '</tr>';
        });

        $('#staff-list tbody').html(row);
      },
      error: function (error) {
        console.log(error);
      }
    });
  }

  function assignmentcustomer(customerId) {
    $('#assignmentcustomerModal').modal();
    getStaffs(customerId);
    $('#customerId').val(customerId);
  }

  $('#btnAssign').click(function (e) {
    e.preventDefault();
    var data = {};
    data['id'] = $('#customerId').val();
    var staffs = $('#staff-list').find('tbody input[type = checkbox]:checked').map(function () {
      return $(this).val();
    }).get();
    data['staffIds'] = staffs;

    $.ajax({
      url: "/api/customer/assign",
      type: "POST",
      contentType: "application/json",
      dataType: "JSON",
      data: JSON.stringify(data),
      success: function (response) {
        console.log({"success": response});
      },
      error: function (error) {
        console.log({"error": error});
      }
    });
  });

  $('#btnSearch').click(function (e) {
    e.preventDefault();
    $('#search-form').submit();
  });

  var customerIdToDelete = [];

  function deletecustomer(customerId) {
    customerIdToDelete = [customerId];
    $('#deleteConfirmModal').modal('show');
  };

  function deletecustomers(data) {

    console.log(data);

    if (data.length === 0) {
      console.log("no customer select");
      return;
    }

    $.ajax({
      url: "/api/customer/" + data,
      type: "DELETE",
      contentType: "application/json",
      dataType: "JSON",
      success: function (response) {
        console.log({"success": response});
        location.reload();
      },
      error: function (error) {
        console.log({"error": error});
        alert("An error occurred while deleting. Please try again.");
      }
    });
  }

  $('#btnDelete').click(function (e) {
    e.preventDefault();
    var ids = $('#customer-table').find('tbody input[type = checkbox]:checked').map(function () {
      return $(this).val();
    }).get();

    if (ids.length !== 0) {
      $('#deleteConfirmModal').modal('show');
    }
  });

  $('#confirmDelete').click(function () {
    if (customerIdToDelete.length !== 0) {
      deletecustomers(customerIdToDelete);
      customerIdToDelete = [];
      $('#deleteConfirmModal').modal('hide');
    } else {
      var ids = $('#customer-table').find('tbody input[type = checkbox]:checked').map(function () {
        return $(this).val();
      }).get();
      deletecustomers(ids);
      $('#deleteConfirmModal').modal('hide');
    }
  });
</script>
</body>
</html>

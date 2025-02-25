<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="buildingSearchUrl" value="/admin/building-search"/>
<html>
<head>
  <title>Tìm kiếm tòa nhà</title>
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
        <li class="active">Dashboard</li>
      </ul><!-- /.breadcrumb -->

    </div>

    <div class="page-content">

      <div class="page-header">
        <h1>
          Tìm kiếm toà nhà
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
                <form:form modelAttribute="searchModel" action="${buildingSearchUrl}" method="get" id="search-form">
                  <div class="row">
                    <div class="form-group">
                      <div class="row">
                        <div class="col-xs-12">
                          <div class="col-xs-6">
                            <label class="name">Tên toà nhà</label>
                            <form:input class="form-control" path="name"/>
                          </div>
                          <div class="col-xs-6">
                            <label class="name">Diện tích sàn</label>
                            <form:input type="number" class="form-control" path="floorArea"/>
                          </div>
                        </div>

                        <div class="col-xs-12">
                          <div class="col-xs-2">
                            <label class="name">Quận</label>
                            <form:select class="form-control" path="district">
                              <form:option value="">--Chọn quận--</form:option>
                              <form:options items="${districts}"/>
                            </form:select>
                          </div>
                          <div class="col-xs-5">
                            <label class="name">Phường</label>
                            <form:input class="form-control" path="ward"/>
                          </div>
                          <div class="col-xs-5">
                            <label class="name">Đường</label>
                            <form:input class="form-control" path="street"/>
                          </div>
                        </div>

                        <div class="col-xs-12">
                          <div class="col-xs-4">
                            <label class="name">Số tầng hầm</label>
                            <form:input type="number" class="form-control" path="numberOfBasement"/>
                          </div>
                          <div class="col-xs-4">
                            <label class="name">Hướng</label>
                            <form:input class="form-control" path="direction"/>
                          </div>
                          <div class="col-xs-4">
                            <label class="name">Hạng</label>
                            <form:input type="number" class="form-control" path="level"/>
                          </div>
                        </div>

                        <div class="col-xs-12">
                          <div class="col-xs-3">
                            <label class="name">Diện tích từ</label>
                            <form:input type="number" class="form-control" path="fromArea"/>
                          </div>
                          <div class="col-xs-3">
                            <label class="name">Diện tích đến</label>
                            <form:input type="number" class="form-control" path="toArea"/>
                          </div>
                          <div class="col-xs-3">
                            <label class="name">Giá thuê từ</label>
                            <form:input type="number" class="form-control" path="fromPrice"/>
                          </div>
                          <div class="col-xs-3">
                            <label class="name">Giá thuê đến</label>
                            <form:input type="number" class="form-control" path="toPrice"/>
                          </div>
                        </div>

                        <div class="col-xs-12">
                          <div class="col-xs-5">
                            <label class="name">Tên quản lý</label>
                            <form:input class="form-control" path="managerName"/>
                          </div>
                          <div class="col-xs-5">
                            <label class="name">SĐT quản lý</label>
                            <form:input type="number" class="form-control" path="managerPhoneNumber"/>
                          </div>

                          <security:authorize access="hasRole('ADMIN')">
                            <div class="col-xs-2">
                              <label class="name">Nhân viên</label>
                              <form:select class="form-control" path="staffId">
                                <form:option value="">--Chọn nhân viên--</form:option>
                                <form:options items="${staffs}"/>
                              </form:select>
                            </div>
                          </security:authorize>

                        </div>

                        <div class="col-xs-12">
                          <div class="col-xs-6"
                               style="display: flex; flex-direction: row; justify-content: space-between;">
                            <form:checkboxes items="${typeCodes}" path="typeCode"/>
                          </div>
                        </div>

                        <div class="col-xs-12">
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

          <security:authorize access="hasRole('ADMIN')">
            <div class="pull-right" style="margin: -3px 0 0 0;">
              <a href="/admin/building-edit">
                <button class="btn btn-sm btn-info" title="Thêm tòa nhà">
                  <i class="ace-icon fa fa-plus bigger-120"></i>
                </button>
              </a>
              <button class="btn btn-sm btn-danger" title="Xóa tòa nhà" id="btnDelete">
                <i class="ace-icon fa fa-trash-o bigger-120"></i>
              </button>
            </div>
          </security:authorize>

        </div>
      </div>

      <!-- Bảng kết quả toà nhà -->
      <div class="row">
        <div class="col-xs-12">
          <table id="building-table" class="table table-striped table-bordered table-hover"
                 style="margin: 3em 0 1.5em;">
            <thead>
            <tr>
              <th class="center">
                <label class="pos-rel">
                  <input type="checkbox" class="ace">
                  <span class="lbl"></span>
                </label>
              </th>
              <th>Tên tòa nhà</th>
              <th>Địa chỉ</th>
              <th>Loại tòa nhà</th>
              <th>Số tầng hầm</th>
              <th>Tên quản lý</th>
              <th>SĐT quản lý</th>
              <th>Diện tích sàn</th>
              <th>Diện tích trống</th>
              <th>Diện tích thuê</th>
              <th>Giá thuê</th>
              <th>Phí môi giới</th>
              <th>Thao tác</th>
            </tr>
            </thead>

            <tbody>
            <c:forEach items="${buildings}" var="building">
              <tr>
                <td class="center">
                  <label class="pos-rel">
                    <input type="checkbox" class="ace" name="check-list" value="${building.id}">
                    <span class="lbl"></span>
                  </label>
                </td>

                <td>
                  <a href="#">${building.name}</a>
                </td>

                <td>${building.address}</td>

                <td>${building.rentType}</td>

                <td>${building.numberOfBasement}</td>

                <td>${building.managerName}</td>

                <td>${building.managerPhone}</td>

                <td>${building.floorArea}</td>

                <td>${building.emptyArea}</td>

                <td>${building.rentArea}</td>

                <td>${building.rentPrice}</td>

                <td>${building.brokerageFee}</td>

                <td>
                  <security:authorize access="hasRole('ADMIN')">
                    <div class="hidden-sm hidden-xs btn-group">
                      <button
                          class="btn btn-xs btn-success"
                          title="Giao toa nha"
                          onclick="assignmentBuilding(${building.id})">
                        <i class="ace-icon glyphicon glyphicon-list bigger-120"></i>
                      </button>

                      <a class="btn btn-xs btn-info" title="Sua thong tin" href="/admin/building-edit-${building.id}">
                        <i class="ace-icon fa fa-pencil bigger-120"></i>
                      </a>

                      <button class="btn btn-xs btn-danger" title="Xoa toa nha"
                              onclick="deleteBuilding(${building.id})">
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
<div class="modal fade" id="assignmentBuildingModal" role="dialog">
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
        <input type="hidden" id="buildingId" name="buildingId" value="1"/>
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
        Are you sure you want to delete the selected building(s)?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">No</button>
        <button type="button" class="btn btn-danger" id="confirmDelete">Yes, Delete</button>
      </div>
    </div>
  </div>
</div>
<script>

  function getStaffs(buildingId) {
    $.ajax({
      type: "GET",
      url: "/api/building/" + buildingId + "/staffs",
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

  function assignmentBuilding(buildingId) {
    $('#assignmentBuildingModal').modal();
    getStaffs(buildingId);
    $('#buildingId').val(buildingId);
  }

  $('#btnAssign').click(function (e) {
    e.preventDefault();
    var data = {};
    data['id'] = $('#buildingId').val();
    var staffs = $('#staff-list').find('tbody input[type = checkbox]:checked').map(function () {
      return $(this).val();
    }).get();
    data['staffIds'] = staffs;

    $.ajax({
      url: "/api/building/assign",
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
    var typeCode = $('input[name="typeCode"]:checked').map(function () {
      return $(this).val();
    }).get();

    if (typeCode.length !== 0) {
      $('#search-form').submit();
    } else {
      var currentUrl = window.location.href;
      window.location.href = currentUrl + '?typeCode=required';
    }
  });

  var buildingIdToDelete = [];

  function deleteBuilding(buildingId) {
    buildingIdToDelete = [buildingId];
    $('#deleteConfirmModal').modal('show');
  };

  function deleteBuildings(data) {

    console.log(data);

    if (data.length === 0) {
      console.log("no building select");
      return;
    }

    $.ajax({
      url: "/api/building/" + data,
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
    var ids = $('#building-table').find('tbody input[type = checkbox]:checked').map(function () {
      return $(this).val();
    }).get();

    if (ids.length !== 0) {
      $('#deleteConfirmModal').modal('show');
    }
  });

  $('#confirmDelete').click(function () {
    if (buildingIdToDelete.length !== 0) {
      deleteBuildings(buildingIdToDelete);
      buildingIdToDelete = [];
      $('#deleteConfirmModal').modal('hide');
    } else {
      var ids = $('#building-table').find('tbody input[type = checkbox]:checked').map(function () {
        return $(this).val();
      }).get();
      deleteBuildings(ids);
      $('#deleteConfirmModal').modal('hide');
    }
  });
</script>
</body>
</html>

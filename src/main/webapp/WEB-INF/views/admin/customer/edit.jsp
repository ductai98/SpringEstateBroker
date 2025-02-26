<%--
  Created by IntelliJ IDEA.
  User: ductai
  Date: 15/2/25
  Time: 19:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@include file="/common/taglib.jsp" %>
<c:url var="customer" value="/api/customer"/>
<html>
<head>
  <title>Thông tin khách hàng</title>
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
              <form:hidden id="customerId" path="id"/>
            </form>
          </div>
        </form:form>
      </div>

      <c:forEach items="${transactionTypes}" var="type">
        <div class="row">
          <div class="col-xs-12">
            <h3>${type.name}</h3>
            <button class="btn btn-primary" id="btnAddTransaction" onclick="addTransaction(${type.id})">
              <i class="fa fa-plus"></i> Thêm
            </button>
            <table class="table table-bordered table-striped">
              <thead>
              <tr>
                <th>Ngày tạo</th>
                <th>Người tạo</th>
                <th>Chi tiết giao dịch</th>
                <th>Thao tác</th>
              </tr>
              </thead>
              <tbody>
              <c:if test="${type.code == 'CARE'}">
                <c:forEach items="${careTransactions}" var="transaction">
                  <tr>
                    <td>${transaction.createdDate}</td>
                    <td>${transaction.createdBy}</td>
                    <td>${transaction.note}</td>
                    <td>
                      <button class="btn btn-xs btn-danger" onclick="deleteTransaction(${transaction.id})">
                        <i class="fa fa-trash"></i> Xóa
                      </button>
                    </td>
                  </tr>
                </c:forEach>
              </c:if>
              <c:if test="${type.code == 'VIEW'}">
                <c:forEach items="${viewTransactions}" var="transaction">
                  <tr>
                    <td>${transaction.createdDate}</td>
                    <td>${transaction.createdBy}</td>
                    <td>${transaction.note}</td>
                    <td>
                      <button class="btn btn-xs btn-danger" onclick="deleteTransaction(${transaction.id})">
                        <i class="fa fa-trash"></i> Xóa
                      </button>
                    </td>
                  </tr>
                </c:forEach>
              </c:if>

              </tbody>
            </table>
          </div>
        </div>
      </c:forEach>

    </div><!-- /.page-content -->
  </div>
</div><!-- /.main-content -->

<!-- Modal -->
<div class="modal fade" id="transactionModal" tabindex="-1" role="dialog" aria-labelledby="transactionModalLabel"
     aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="transactionModalLabel">Thêm giao dịch</h5>
      </div>
      <div class="modal-body">
        <form id="transactionForm">
          <div class="form-group">
            <label for="transactionDetails">Chi tiết giao dịch</label>
            <textarea class="form-control" id="transactionDetails" rows="3"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Đóng</button>
        <button type="button" class="btn btn-primary" id="addTransactionBtn">Thêm giao dịch</button>
      </div>
    </div>
  </div>
  <hidden id="typeId" value="1"></hidden>
</div>

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
        console.log({"error": error});
      }
    });
  }

  $('#btnCancel').click(function (e) {
    e.preventDefault();
    window.location.href = "/admin/customer-search";
  });

  function addTransaction(typeId) {
    $('#transactionModal').modal();
    $('#typeId').val(typeId);
  }

  $('#addTransactionBtn').click(function (e) {
    e.preventDefault();

    var customerId = $('#customerId').val();
    var typeId = $('#typeId').val();
    var data = {};

    var details = $('#transactionDetails').val();

    data['customerId'] = customerId;
    data['note'] = details;
    data['typeId'] = typeId;

    if (details !== '' || details != null) {
      addTransactionDetails(data);
    }
  });

  function addTransactionDetails(data) {
    $.ajax({
      type: "POST",
      url: "${customer}" + "/transaction",
      data: JSON.stringify(data),
      contentType: "application/json",
      dataType: "JSON",
      success: function (response) {
        console.log({"success": response});
        location.reload();
      },
      error: function (error) {
        console.log({"error": error});
      }
    });
  }

</script>
</body>
</html>
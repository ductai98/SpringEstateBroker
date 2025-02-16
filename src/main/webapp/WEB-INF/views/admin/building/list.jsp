<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp" %>
<html>
<head>
  <title>Tìm kiếm tòa nhà</title>
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
              <div class="widget-main" id="search-form">
                <div class="row">
                  <div class="form-group">
                    <div class="row">
                      <div class="col-xs-12">
                        <div class="col-xs-6">
                          <label class="name">Tên toà nhà</label>
                          <input type="text" class="form-control"/>
                        </div>
                        <div class="col-xs-6">
                          <label class="name">Diện tích sàn</label>
                          <input type="number" class="form-control"/>
                        </div>
                      </div>

                      <div class="col-xs-12">
                        <div class="col-xs-2">
                          <label class="name">Quận</label>
                          <select class="form-control">
                            <option value="">--Chọn quận--</option>
                            <option value="">Quận 1</option>
                            <option value="">Quận 2</option>
                            <option value="">Quận 3</option>
                            <option value="">Quận 4</option>
                          </select>
                        </div>
                        <div class="col-xs-5">
                          <label class="name">Phường</label>
                          <input type="number" class="form-control"/>
                        </div>
                        <div class="col-xs-5">
                          <label class="name">Đường</label>
                          <input type="number" class="form-control"/>
                        </div>
                      </div>

                      <div class="col-xs-12">
                        <div class="col-xs-4">
                          <label class="name">Số tầng hầm</label>
                          <input type="number" class="form-control"/>
                        </div>
                        <div class="col-xs-4">
                          <label class="name">Hướng</label>
                          <input type="text" class="form-control"/>
                        </div>
                        <div class="col-xs-4">
                          <label class="name">Hạng</label>
                          <input type="number" class="form-control"/>
                        </div>
                      </div>

                      <div class="col-xs-12">
                        <div class="col-xs-3">
                          <label class="name">Diện tích từ</label>
                          <input type="number" class="form-control"/>
                        </div>
                        <div class="col-xs-3">
                          <label class="name">Diện tích đến</label>
                          <input type="number" class="form-control"/>
                        </div>
                        <div class="col-xs-3">
                          <label class="name">Giá thuê từ</label>
                          <input type="number" class="form-control"/>
                        </div>
                        <div class="col-xs-3">
                          <label class="name">Giá thuê đến</label>
                          <input type="number" class="form-control"/>
                        </div>
                      </div>

                      <div class="col-xs-12">
                        <div class="col-xs-5">
                          <label class="name">Tên quản lý</label>
                          <input type="text" class="form-control"/>
                        </div>
                        <div class="col-xs-5">
                          <label class="name">SĐT quản lý</label>
                          <input type="number" class="form-control"/>
                        </div>
                        <div class="col-xs-2">
                          <label class="name">Nhân viên</label>
                          <select class="form-control">
                            <option value="">--Chọn nhân viên--</option>
                            <option value="">Nguyễn Văn A</option>
                            <option value="">Nguyễn Văn B</option>
                            <option value="">Nguyễn Văn C</option>
                          </select>
                        </div>
                      </div>

                      <div class="col-xs-12">
                        <div class="col-xs-6">
                          <label class="checkbox-inline">
                            <input type="checkbox"/> Nội thất
                          </label>
                          <label class="checkbox-inline">
                            <input type="checkbox"/> Nguyên căn
                          </label>
                          <label class="checkbox-inline">
                            <input type="checkbox"/> Tầng trệt
                          </label>
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
              </div>
            </div>
          </div>
          <div class="pull-right" style="margin: -3px 0 0 0;">
            <a href="/admin/building-edit">
              <button class="btn btn-sm btn-info" title="Thêm tòa nhà">
                <i class="ace-icon fa fa-plus bigger-120"></i>
              </button>
            </a>
            <button class="btn btn-sm btn-danger" title="Xóa tòa nhà">
              <i class="ace-icon fa fa-trash-o bigger-120"></i>
            </button>
          </div>
        </div>
      </div>

      <!-- Bảng kết quả toà nhà -->
      <div class="row">
        <div class="col-xs-12">
          <table id="simple-table" class="table table-striped table-bordered table-hover" style="margin: 3em 0 1.5em;">
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
              <tr>
                <td class="center">
                  <label class="pos-rel">
                    <input type="checkbox" class="ace">
                    <span class="lbl"></span>
                  </label>
                </td>

                <td>
                  <a href="#">Building A</a>
                </td>

                <td>142 Huỳnh Văn Bánh, Phú Nhuận</td>

                <td>2</td>

                <td>Nguyễn Văn A</td>

                <td>0838474773</td>

                <td>1000</td>

                <td>800</td>

                <td>800</td>

                <td>500</td>

                <td>200</td>

                <td>
                  <div class="hidden-sm hidden-xs btn-group">
                    <button
                        class="btn btn-xs btn-success"
                        title="Giao toa nha"
                        onclick="assignmentBuilding(1)">
                      <i class="ace-icon glyphicon glyphicon-list bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-info" title="Sua thong tin">
                      <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-danger" title="Xoa toa nha">
                      <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>
                  </div>
                </td>
              </tr>

              <tr>
                <td class="center">
                  <label class="pos-rel">
                    <input type="checkbox" class="ace">
                    <span class="lbl"></span>
                  </label>
                </td>

                <td>
                  <a href="#">Building B</a>
                </td>

                <td>142 Huỳnh Văn Bánh, Phú Nhuận</td>

                <td>2</td>

                <td>Nguyễn Văn A</td>

                <td>0838474773</td>

                <td>1000</td>

                <td>800</td>

                <td>800</td>

                <td>500</td>

                <td>200</td>

                <td>
                  <div class="hidden-sm hidden-xs btn-group">
                    <button
                        class="btn btn-xs btn-success"
                        onclick="assignmentBuilding(2)"
                        title="Giao toa nha">
                      <i class="ace-icon fa fa-bars bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-info" title="Sua thong tin">
                      <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-danger" title="Xoa toa nha">
                      <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>
                  </div>
                </td>
              </tr>

              <tr>
                <td class="center">
                  <label class="pos-rel">
                    <input type="checkbox" class="ace">
                    <span class="lbl"></span>
                  </label>
                </td>

                <td>
                  <a href="#">Building C</a>
                </td>

                <td>142 Huỳnh Văn Bánh, Phú Nhuận</td>

                <td>2</td>

                <td>Nguyễn Văn A</td>

                <td>0838474773</td>

                <td>1000</td>

                <td>800</td>

                <td>800</td>

                <td>500</td>

                <td>200</td>

                <td>
                  <div class="hidden-sm hidden-xs btn-group">
                    <button
                        class="btn btn-xs btn-success"
                        title="Giao toa nha"
                        onclick="assignmentBuilding(3)">
                      <i class="ace-icon fa fa-bars bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-info" title="Sua thong tin">
                      <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-danger" title="Xoa toa nha">
                      <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>
                  </div>
                </td>
              </tr>

              <tr>
                <td class="center">
                  <label class="pos-rel">
                    <input type="checkbox" class="ace">
                    <span class="lbl"></span>
                  </label>
                </td>

                <td>
                  <a href="#">Building D</a>
                </td>

                <td>142 Huỳnh Văn Bánh, Phú Nhuận</td>

                <td>2</td>

                <td>Nguyễn Văn A</td>

                <td>0838474773</td>

                <td>1000</td>

                <td>800</td>

                <td>800</td>

                <td>500</td>

                <td>200</td>

                <td>
                  <div class="hidden-sm hidden-xs btn-group">
                    <button class="btn btn-xs btn-success">
                      <i class="ace-icon fa fa-bars bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-info">
                      <i class="ace-icon fa fa-pencil bigger-120"></i>
                    </button>

                    <button class="btn btn-xs btn-danger">
                      <i class="ace-icon fa fa-trash-o bigger-120"></i>
                    </button>

                  </div>
                </td>
              </tr>
            </tbody>
          </table>
        </div><!-- /.span -->
      </div>

      </div><!-- /.page-content -->
    </div>
  </div><!-- /.main-content -->
  <div class="modal fade" id="assignmentBuildingModal" role="dialog">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">Danh sách nhân viên</h5>
        </div>
        <div class="modal-body">
          <table id="staff-list" class="table table-striped table-bordered table-hover" style="margin: 3em 0 1.5em;">
            <thead>
            <tr>
              <th class="center">
                Chọn
              </th>
              <th class="center">Tên nhân viên</th>
            </tr>
            </thead>

            <tbody>
            <tr class="center">
              <td class="center">
                <label class="pos-rel">
                  <input type="checkbox" class="ace" value="1" checked>
                  <span class="lbl"></span>
                </label>
              </td>

              <td>
                Nguyễn Văn A
              </td>
            </tr>

            <tr class="center">
              <td class="center">
                <label class="pos-rel">
                  <input type="checkbox" class="ace" value="2">
                  <span class="lbl"></span>
                </label>
              </td>

              <td>
                Nguyễn Văn B
              </td>
            </tr>
            </tbody>
          </table>
          <input type="hidden" id="buildingId" name="buildingId" value="1"/>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
          <button type="button" class="btn btn-primary" data-dismiss="modal" id="assignBuilding">Save changes</button>
        </div>
      </div>
    </div>
  </div>
</body>
</html>

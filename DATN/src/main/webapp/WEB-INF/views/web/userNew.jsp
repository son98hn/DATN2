<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en" class="no-js">

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Quản lí bài viết cá nhân</title>
    <script src="<c:url value='/ckfinder/ckfinder.js'/>"></script>
    <link rel="stylesheet" href="<c:url value='/template/web/css/media_query.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/template/web/css/bootstrap.css'/>"/>
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
          crossorigin="anonymous">
    <link rel="stylesheet" href="<c:url value='/template/web/css/animate.css'/>"/>
    <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
    <link rel="stylesheet" href="<c:url value='/template/web/css/owl.carousel.css'/>"/>
    <link rel="stylesheet" href="<c:url value='/template/web/css/owl.theme.default.css'/>"/>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<c:url value='/template/web/css/style_1.css'/>"/>
    <!-- Modernizr JS -->
    <script src="<c:url value='/template/web/js/modernizr-3.5.0.min.js'/>"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
    <link href="<c:url value='/template/quan-li-bai-viet/style1.css' />" rel="stylesheet" type="text/css"
          media="all"/>
</head>

<body>
<div class="container-fluid bg-faded fh5co_padd_mediya padding_786">
    <div class="container padding_786">
        <nav class="navbar navbar-toggleable-md navbar-light ">
            <button class="navbar-toggler navbar-toggler-right mt-3" type="button" data-toggle="collapse"
                    data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                    aria-expanded="false" aria-label="Toggle navigation"><span
                    class="fa fa-bars"></span></button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="navbar-brand" href="/trang-chu"><img
                                src="https://static-znews.zadn.vn/images/logo-zing-home.svg" alt="img"
                                class="fh5co_logo_width"/></a>
                    </li>
                    <c:forEach var="categoryP" items="${categoryParent}" begin="${categoryP.size()}"
                               end="${categoryP.size()+4}">
                        <li class="nav-item ">
                            <a class="nav-link"
                               href='<c:url value ="/nhom-bai-viet/${categoryP.code}"/>'>${categoryP.name}<span
                                    class="sr-only">(current)</span></a>
                        </li>
                    </c:forEach>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="dropdownMenuButton2"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><span
                                class="sr-only">(current)</span></a>
                        <div class="dropdown-menu" aria-labelledby="dropdownMenuLink_1">
                            <c:forEach var="categoryP" items="${categoryParent}"
                                       begin="${categoryP.size()+6}"><a class="dropdown-item"
                                                                        href='<c:url value ="/nhom-bai-viet/${categoryP.code}"/>'>${categoryP.name}</a>
                            </c:forEach>
                        </div>
                    </li>
                    <li class="nav-item">
                        <li class="nav-item dropdown">
                            <c:if test="${not empty USERMODEL}">
                                <a class="nav-link dropdown-toggle" href="#" id="dropdownMenuButton2"
                                    data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img
                                        style="width: 30px; margin-top: 5px;" src="${USERMODEL.avatar}"
                                        alt="img" class="fh5co_logo_width" /></a>
                                <div class="dropdown-menu" aria-labelledby="dropdownMenuLink_1">
                                    <a class="dropdown-item" href='/userNew'>Quản lí bài
                                        viết</a>
                                    <a class="dropdown-item" href='/profile'>Cập nhật thông
                                        tin
                                        cá nhân</a>
                                    <a class="dropdown-item" href='/resetPassword'>Đổi mật khẩu</a>
                                    <a class="dropdown-item" href='/logout'>Logout</a>
                                </div>
                        </li>
                        </c:if>
                        <c:if test="${empty USERMODEL}">
                            <a class="nav-link" href="/login">LOGIN</a>
                        </c:if>
                        </li>
                </ul>
            </div>
        </nav>
    </div>
</div>

<div class="container-fluid pb-4 pt-4 paddding">
    <div class="container paddding" style="width: 75%;">
        <div class="row mx-0">
            <div class="col-md-12 animate-box" data-animate-effect="fadeInLeft">
                <div class="login-form">
                    <div class="main-div">
                        <c:if test="${not empty message}">
                            <div class="alert alert-${alert}">${message}</div>
                        </c:if>
                        <form action="/addnew" id="formSubmit" method="GET">
                            <div class="main-content-inner">
                                <div class="page-content">
                                    <div class="row" style="margin-left: 80px;">
                                        <div class="col-xs-12">
                                            <c:if test="${not empty messageResponse}">
                                                <div class="alert alert-${alert}">${messageResponse}</div>
                                            </c:if>
                                            <div class="widget-box table-filter">
                                                <div class="table-btn-controls">
                                                    <div class="pull-right tableTools-container">
                                                        <div class="dt-buttons btn-overlap btn-group">
                                                            <a flag="info" id="test" class="dt-button buttons-colvis btn btn-white btn-primary btn-bold" data-toggle="tooltip"
                                                               title='Thêm bài viết' href="/addOrUpdateNew" 
															   <c:if test="${userFunction.contains('add-new') == true}">
                                                                style="visibility: visible;"</c:if>
                                                                    <c:if test="${userFunction.contains('add-new') == false}">
                                                                        style="visibility: hidden;"</c:if>>
																		<span><i class="fa fa-plus-circle bigger-110 purple"></i></span>
                                                            </a>
                                                            <button id="btnDelete" type="button" <c:if test="${userFunction.contains('delete-new') == true}">
                                                                style="visibility: visible;"</c:if>
                                                                    <c:if test="${userFunction.contains('delete-new') == false}">
                                                                    style="visibility: hidden;"</c:if>
                                                                    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                                    data-toggle="tooltip" title='Xóa bài viết'>
                                                                <span> <i class="fa fa-trash-o bigger-110 pink"></i> </span>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="row" style="margin-left: 80px;">
                                        <div class="col-xs-12">
                                            <div class="table-responsive">
                                                <table class="table table-bordered">
                                                    <thead>
                                                    <tr>
                                                        <th><input type="checkbox" id="checkAll">
                                                        </th>
                                                        <th>Tên bài viết</th>
                                                        <th>Người duyệt</th>
                                                        <th>Ngày tạo</th>
                                                        <th>Ngày duyệt</th>
                                                        <th>Lượt xem</th>
                                                        <th>Thể loại</th>
                                                        <th>Thao tác</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="item" items="${listNew}"
                                                               varStatus="loop">
                                                        <tr>
                                                            <td><input type="checkbox"
                                                                       id="checkbox_${item.id}"
                                                                       value="${item.id}"></td>
                                                            <td><a
                                                                    href="/bai-viet/${item.id}">${item.title}</a>
                                                            </td>
                                                            <td>${item.modifiedBy}</td>
                                                            <td>${item.createdDate}</td>
                                                            <td>${item.modifiedDate}</td>
                                                            <td>${item.views}</td>
                                                            <td>${listCategory[loop.index]}</td>
                                                            <td>
                                                                <c:url var="editURL"
                                                                       value="/addOrUpdateNew">
                                                                    <c:param name="id"
                                                                             value="${item.id}"/>
                                                                </c:url> <a
                                                                    class="btn btn-sm btn-primary btn-edit"
                                                                    <c:if
                                                                            test="${userFunction.contains('edit-new') == true}">
                                                                        style="visibility:
                                                                        visible;"</c:if>
                                                                    <c:if
                                                                            test="${userFunction.contains('edit-new') == false}">
                                                                        style="visibility:
                                                                        hidden;"</c:if>
                                                                    data-toggle="tooltip"
                                                                    title="Cập nhật
																					bài viết"
                                                                    href='${editURL}'><i
                                                                    class="fa fa-pencil-square-o"
                                                                    aria-hidden="true"></i>
                                                            </a>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                                
                                              
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <form action="<c:url value='/userNew/${USERMODEL.id}'/>" id="formSubmit1" method="GET">
                            <ul class="pagination" id="pagination" style="margin-left: 80px;"></ul>
                            <input type="hidden" value="" id="page" name="page"/>
                        </form>
                    </div>
                </div>
                <br>
            </div>
        </div>
    </div>
</div>
<div class="container-fluid fh5co_footer_bg pb-3">
    <div class="container animate-box">
        <div class="row">
            <div class="col-12 spdp_right py-5">
            </div>
            <div class="clearfix"></div>
            <div class="col-12 col-md-4 col-lg-3">
                <div class="footer_main_title py-3"> About</div>
                <div class="footer_sub_about pb-3"> Lorem Ipsum is simply dummy text of the printing and
                    typesetting
                    industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s,
                    when an
                    unknown printer took a galley of type and scrambled it to make a type specimen book.
                </div>
                <div class="footer_mediya_icon">
                    <div class="text-center d-inline-block"><a class="fh5co_display_table_footer">
                        <div class="fh5co_verticle_middle"><i class="fa fa-linkedin"></i></div>
                    </a></div>
                    <div class="text-center d-inline-block"><a class="fh5co_display_table_footer">
                        <div class="fh5co_verticle_middle"><i class="fa fa-google-plus"></i></div>
                    </a></div>
                    <div class="text-center d-inline-block"><a class="fh5co_display_table_footer">
                        <div class="fh5co_verticle_middle"><i class="fa fa-twitter"></i></div>
                    </a></div>
                    <div class="text-center d-inline-block"><a class="fh5co_display_table_footer">
                        <div class="fh5co_verticle_middle"><i class="fa fa-facebook"></i></div>
                    </a></div>
                </div>
            </div>
            <div class="col-12 col-md-3 col-lg-2">
                <div class="footer_main_title py-3"> Category</div>
                <ul class="footer_menu">
                    <c:forEach var="categoryP" items="${categoryParent}" begin="${categoryP.size()}"
                               end="${categoryP.size()+5}">
                        <li><a href='<c:url value ="/nhom-bai-viet/${categoryP.code}"/>' class=""><i
                                class="fa fa-angle-right"></i>&nbsp;&nbsp;
                                ${categoryP.name}</a></li>
                    </c:forEach>
                </ul>
            </div>
            <div class="col-12 col-md-3 col-lg-2">
                <div class="footer_main_title py-3"></div>
                <ul class="footer_menu">
                    <c:forEach var="categoryP" items="${categoryParent}" begin="${categoryP.size()+6}"
                               end="${categoryP.size()+11}">
                        <li><a href='<c:url value ="/nhom-bai-viet/${categoryP.code}"/>' class=""><i
                                class="fa fa-angle-right"></i>&nbsp;&nbsp;
                                ${categoryP.name}</a></li>
                    </c:forEach>
                </ul>
            </div>
        </div>
        <div class="row justify-content-center pt-2 pb-4">
            <div class="col-12 col-md-8 col-lg-7 ">
                <div class="input-group">
								<span class="input-group-addon fh5co_footer_text_box" id="basic-addon1"><i
                                        class="fa fa-envelope"></i></span>
                    <input type="text" class="form-control fh5co_footer_text_box"
                           placeholder="Enter your email..." aria-describedby="basic-addon1">
                    <a href="#" class="input-group-addon fh5co_footer_subcribe" id="basic-addon12"> <i
                            class="fa fa-paper-plane-o"></i>&nbsp;&nbsp;Subscribe</a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="gototop js-top">
    <a href="#" class="js-gotop"><i class="fa fa-arrow-up"></i></a>
</div>
</body>
<script src="<c:url value='/template/web/js/owl.carousel.min.js'/>"></script>
<!--<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n" crossorigin="anonymous"></script>-->
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
        integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
        integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
        crossorigin="anonymous"></script>
<!-- Waypoints -->
<script src="<c:url value='/template/web/js/jquery.waypoints.min.js'/>"></script>
<!-- Main -->
<script src="<c:url value='/template/web/js/main.js'/>"></script>
<script>
    var totalPages = ${ totalPage };
    var currentPage = ${ page };
    var limit = 15;
    $(function () {
        window.pagObj = $('#pagination').twbsPagination({
            totalPages: totalPages,
            visiblePages: 10,
            startPage: currentPage,
            onPageClick: function (event, page) {
                if (currentPage != page) {
                    $('#size').val(limit);
                    $('#page').val(page);
                    $('#formSubmit1').submit();
                }
            }
        });
    });

    $("#btnDelete").click(function () {
        var ids = $('tbody input[type=checkbox]:checked').map(function () {
            return $(this).val();
        }).get();
        deleteNew(ids);
    });

    function deleteNew(data) {
        $.ajax({
            url: '/api-admin-new',
            type: 'DELETE',
            contentType: 'application/json',
            data: JSON.stringify(data),
            dataType: 'json',
            success: function (result) {
                window.location.href = "/userNew?message=delete_success";
            },
            error: function (error) {
                window.location.href = "/userNew?message=delete_success";
            }
        });
    }
</script>

</html>
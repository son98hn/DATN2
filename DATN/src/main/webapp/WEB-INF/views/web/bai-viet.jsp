<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
        <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
        <html lang="en" class="no-js">

        <head>
            <!-- Required meta tags -->
            <meta charset="utf-8">
            <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
            <title></title>
            <link rel="stylesheet" href="<c:url value='/template/web/css/media_query.css'/>" />
            <link rel="stylesheet" href="<c:url value='/template/web/css/bootstrap.css'/>" />
            <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
                integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
                crossorigin="anonymous">
            <link rel="stylesheet" href="<c:url value='/template/web/css/animate.css'/>" />
            <link href="https://fonts.googleapis.com/css?family=Poppins" rel="stylesheet">
            <link rel="stylesheet" href="<c:url value='/template/web/css/owl.carousel.css'/>" />
            <link rel="stylesheet" href="<c:url value='/template/web/css/owl.theme.default.css'/>" />
            <!-- Bootstrap CSS -->
            <link rel="stylesheet" href="<c:url value='/template/web/css/style_1.css'/>" />
            <!-- Modernizr JS -->
            <script src="<c:url value='/template/web/js/modernizr-3.5.0.min.js'/>"></script>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
            <script src="<c:url value='/template/paging/jquery.twbsPagination.js' />"></script>
            <script src="<c:url value='/ckeditor/ckeditor.js' />"></script>
            <script src="<c:url value='/ckfinder/ckfinder.js'/>"></script>
            <!-- <link rel="stylesheet" href="<c:url value='/template/web/bai-viet/style_1.css'/>" /> -->
        </head>

        <body class="single">
            <!-- <div class="container-fluid fh5co_header_bg">
                <div class="container">
                    <div class="row">
                        <div class="col-12 fh5co_mediya_center"><a href="#" class="color_fff fh5co_mediya_setting"><i
                                    class="fa fa-clock-o"></i>&nbsp;&nbsp;&nbsp;Friday, 5 January 2018</a>
                            <div class="d-inline-block fh5co_trading_posotion_relative"><a href="#"
                                    class="treding_btn">Trending</a>
                                <div class="fh5co_treding_position_absolute"></div>
                            </div>
                            <a href="#" class="color_fff fh5co_mediya_setting">Instagram’s big redesign goes live with
                                black-and-white app</a>
                        </div>
                    </div>
                </div>
            </div> -->
            <div class="container-fluid bg-faded fh5co_padd_mediya padding_786">
                <div class="container padding_786">
                    <nav class="navbar navbar-toggleable-md navbar-light ">
                        <button class="navbar-toggler navbar-toggler-right mt-3" type="button" data-toggle="collapse"
                            data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent"
                            aria-expanded="false" aria-label="Toggle navigation"><span
                                class="fa fa-bars"></span></button>
                        <a class="navbar-brand" href="/trang-chu"><img
                                src="https://static-znews.zadn.vn/images/logo-zing-home.svg" alt="img"
                                class="mobile_logo_width" /></a>
                        <div class="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul class="navbar-nav mr-auto">
                                <li class="nav-item active">
                                    <a href="/trang-chu"><img
                                            src="https://static-znews.zadn.vn/images/logo-zing-home.svg" alt="img"
                                            class="fh5co_logo_width" /></a>
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
                                    <c:if test="${not empty USERMODEL}">
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" href="#" id="dropdownMenuButton2"
                                        data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"><img
                                            style="width: 30px; margin-top: 5px;"
                                            src="https://png.pngtree.com/png-vector/20190223/ourlarge/pngtree-profile-line-black-icon-png-image_691051.jpg"
                                            alt="img" class="fh5co_logo_width" /></a>
                                    <div class="dropdown-menu" aria-labelledby="dropdownMenuLink_1">
                                        <a class="dropdown-item" href='/userNew/${USERMODEL.id}'>Quản lí bài viết</a>
                                        <a class="dropdown-item" href='/profile?id=${USERMODEL.id}'>Cập nhật thông tin
                                            cá nhân</a>
                                        <a class="dropdown-item" href='/logout'>Logout</a>
                                    </div>
                                </li>
                                </c:if>
                                <c:if test="${empty USERMODEL}">
                                    <a class="nav-link" href="/login">LOGIN</a>
                                    <!-- <img style="width: 30px; margin-top: 5px;"
                                            src="https://png.pngtree.com/png-vector/20190223/ourlarge/pngtree-profile-line-black-icon-png-image_691051.jpg"
                                            alt="img" class="fh5co_logo_width" /></a> -->
                                </c:if>
                                </li>
                            </ul>
                        </div>

                    </nav>
                </div>
            </div>
            <hr>
            <!-- <div id="fh5co-title-box"
                style="background-image: url(images/camila-cordeiro-114636.jpg); background-position: 50% 90.5px;"
                data-stellar-background-ratio="0.5">
                <div class="overlay"></div>
                <div class="page-title">
                    <img src="images/person_1.jpg" alt="Free HTML5 by FreeHTMl5.co">
                    <span>January 1, 2018</span>
                    <h2>How to write interesting articles</h2>
                </div>
            </div> -->
            <div id="fh5co-single-content" class="container-fluid pb-4 pt-4 paddding">
                <div class="container paddding">
                    <div class="row mx-0">
                        <div class="col-md-8 animate-box" data-animate-effect="fadeInLeft">
                            <h3>
                                ${bv.title}
                            </h3>
                            <p>${bv.createdBy} ${bv.createdDate}</p>
                            <p>
                                ${bv.content}
                            </p>
                            <hr>
                            <div class="cmt1">
                                <form id="formSubmit">
                                    <div class="cmt11">
                                        <img src="${USERMODEL.avatar}" id="avatar"
                                            style="width: 50px; border-radius: 50%;">
                                        <span>${USERMODEL.name}</span>
                                    </div>
                                    <div class="cmt12">
                                        <input type="text" id="content" name="content" placeholder="Nội dung ...">
                                        <input type="hidden" value="${bv.id}" name="newId" id="newId">
                                    </div>
                                    <div class="cmt13">
                                        <button type="button" class="btn btn-dark" id="btnComment">Gửi</button>
                                    </div>
                                </form>
                            </div>
                            <br>
                            <div class="cmt2">
                                <c:forEach var="bl" items="${comments}" varStatus="loop">
                                    <div class="cmt20">
                                        <div class="cmt21">
                                            <img src="${listUser[loop.index].avatar}"
                                                style="width: 40px; border-radius: 50%;">
                                        </div>
                                        <div class="cmt22">
                                            <span>${listUser[loop.index].name}</span>
                                            <!-- <span>${bl.date}</span> -->
                                            <p>${bl.content}</p>
                                        </div>
                                        <div class="cmt23">
                                            <c:if
                                                test="${(bl.userEntity.id == USERMODEL.id) || userfunction.contains('delete-comment') == true}">
                                                <button type="button"
                                                    class="dt-button buttons-html5 btn btn-white btn-dark btn-bold"
                                                    title='Xóa bình luận' id="btn${bl.id}"
                                                    onclick="deleteComment(this.value)" value="${bl.id}">
                                                    <span><i class="fa fa-times-circle" aria-hidden="true"></i>
                                                    </span></button>
                                            </c:if>
                                        </div>
                                    </div>
                                    <hr>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="col-md-3 animate-box" data-animate-effect="fadeInRight">
                            <div>
                                <div class="fh5co_heading fh5co_heading_border_bottom pt-3 py-2 mb-4">Bài viết liên quan
                                </div>
                            </div>
                            <c:forEach var="item" items="${listNewTag}" begin="${item.size()}"
                            end="${item.size()+7}">
                                <div class="row pb-3">
                                    <div class="col-5 align-self-center">
                                        <a href="/bai-viet/${item.id}"><img src="${item.thumbnail}" alt="img" class="fh5co_most_trading" /></a>
                                    </div>
                                    <div class="col-7 paddding">
                                        <div class="most_fh5co_treding_font"><a href="/bai-viet/${item.id}">${item.title}</a></div>
                                        <div class="most_fh5co_treding_font_123">${item.createdDate}</div>
                                    </div>
                                </div>
                            </c:forEach>
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
                            <div class="footer_main_title py-3"> </div>
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
        <script>if (!navigator.userAgent.match(/Android|BlackBerry|iPhone|iPad|iPod|Opera Mini|IEMobile/i)) { $(window).stellar(); }</script>
        <script>
            $('#btnComment').click(function (e) {
                e.preventDefault();
                var data = {};
                var formData = $('#formSubmit').serializeArray();
                $.each(formData, function (i, v) {
                    data["" + v.name + ""] = v.value;
                });
                var newId = $('#newId').val();
                addComment(data);
            });
            function addComment(data) {
                $.ajax({
                    url: '/api-user-comment',
                    type: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify(data),
                    dataType: 'json',
                    success: function () {
                        window.location.href = "/bai-viet/${bv.id}";
                    },
                    error: function (error) {
                        window.location.href = "/bai-viet/${bv.id}";
                    }
                });
            }
            function deleteComment(data) {
                // console.log(data);
                // var id = data.value();

                deleteComment1(data);
                // alert(id);
            };
            function deleteComment1(data) {
                $.ajax({
                    url: '/api-user-comment/' + data,
                    type: 'DELETE',
                    contentType: 'application/json',
                    // data: JSON.stringify(data),
                    // dataType: 'json',
                    success: function () {
                        window.location.href = "/bai-viet/${bv.id}";
                    },
                    error: function (error) {
                        window.location.href = "/bai-viet/${bv.id}";
                    }
                });
            };
        </script>

        </html>
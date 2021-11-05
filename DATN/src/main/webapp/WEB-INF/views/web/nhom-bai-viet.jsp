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
                <div class="container paddding">
                    <div class="row mx-0">
                        <div class="col-md-8 animate-box" data-animate-effect="fadeInLeft">
                            <div>
                                <div class="fh5co_heading fh5co_heading_border_bottom py-2 mb-4">${nameCategory}</div>
                            </div>
                            <c:forEach var="listNew" items="${listNews}">
                                <div class="row pb-4">
                                    <div class="col-md-5">
                                        <div class="fh5co_hover_news_img">
                                            <div class="fh5co_news_img"><img src="${listNew.thumbnail}" alt="" /></div>
                                            <div></div>
                                        </div>
                                    </div>
                                    <div class="col-md-7 animate-box">
                                        <a href='<c:url value ="/bai-viet/${listNew.id}"/>' class="fh5co_magna py-2">
                                            ${listNew.title}</a> <a href="#" class="fh5co_mini_time py-3">
                                            ${listNew.createdDate} </a>
                                        <div class="fh5co_consectetur"> ${listNew.shortDescription}
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                        <div class="col-md-3 animate-box" data-animate-effect="fadeInRight">
                            <div>
                                <div class="fh5co_heading fh5co_heading_border_bottom pt-3 py-2 mb-4">Phổ biến nhất
                                </div>
                            </div>
                            <c:forEach var="popularNew" items="${popularNews}">
                                <div class="row pb-3">
                                    <div class="col-5 align-self-center">
                                        <img src="${popularNew.thumbnail}" alt="img" class="fh5co_most_trading" />
                                    </div>
                                    <div class="col-7 paddding">
                                        <div class="most_fh5co_treding_font"><a
                                                href="/bai-viet/${popularNew.id}">${popularNew.title}</a>
                                        </div>
                                        <div class="most_fh5co_treding_font_123"><a
                                                href="/bai-viet/${popularNew.id}">${popularNew.createdDate}</a></div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                    <form action="<c:url value='/nhom-bai-viet/${codeCategory}'/>" id="formSubmit" method="GET">
                        <ul class="pagination" id="pagination"></ul>
                        <input type="hidden" value="" id="page" name="page" />
                    </form>
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
        <script>
            var totalPages = ${ totalPage };
            var currentPage = ${ page };
            var limit = 8;
            $(function () {
                window.pagObj = $('#pagination').twbsPagination({
                    totalPages: totalPages,
                    visiblePages: 10,
                    startPage: currentPage,
                    onPageClick: function (event, page) {
                        if (currentPage != page) {
                            $('#size').val(limit);
                            $('#page').val(page);
                            $('#formSubmit').submit();
                        }
                    }
                });
            });
        </script>

        </html>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>My ubercool eshop!!!:)</title>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">

    <!-- Fonts -->
    <link href="/resources/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
    <link href="/resources/css/nivo-lightbox.css" rel="stylesheet"/>
    <link href="/resources/css/nivo-lightbox-theme/default/default.css" rel="stylesheet" type="text/css"/>
    <link href="/resources/css/animate.css" rel="stylesheet"/>
    <!-- Squad theme CSS -->
    <link href="/resources/css/style.css" rel="stylesheet">
    <link href="/resources/color/default.css" rel="stylesheet">

</head>

<div class="container">


    <c:forEach begin="0" end="${fn:length(pictureList)-1}" varStatus="loop">

        <img src="data:image/jpeg;base64,${pictureList[loop.index]}" alt="..."/>
        <br>
        <a href="${urlList[loop.index]}"> ${urlList[loop.index]} </a>
        <c:out value="${promotionTextList[loop.index]}" />       <br>
        <c:out value="${promotionHeaderList[loop.index]}" />       <br>
    </c:forEach>




    <ul id="gn-menu" class="gn-menu-main">
        <li class="gn-trigger">
            <a class="gn-icon gn-icon-menu"><span>Menu</span></a>
            <nav class="gn-menu-wrapper">
                <div class="gn-scroller">
                    <ul class="gn-menu">
                        <li class="gn-search-item">
                            <input placeholder="Search" type="search" class=" gn-icon gn-icon-search form-control">
                            <a><span>Search</span></a>
                        </li>
                        <li>
                            <a href="#about" class="gn-icon gn-icon-download">About</a>
                        </li>
                        <li><a href="#service" class="gn-icon gn-icon-cog">Service</a></li>
                        <li><a href="#works" class="gn-icon gn-icon-help">Works</a></li>
                        <li>
                            <a href="#contact" class="gn-icon gn-icon-archive">Contact</a>
                        </li>
                    </ul>
                </div>
                <!-- /gn-scroller -->
            </nav>
        </li>
        <%@include file="/WEB-INF/jspf/menu.jspf" %>
    </ul>
</div>

<!-- Section: intro -->
<section id="intro" class="intro">
    <div class="slogan">
        <h1>Welcome to my eshop</h1>

        <p class="intro-text">Here you can buy some high quality booze</p>
        <a href="#about" class="btn btn-skin scroll"><spring:message code="label.test"/></a>
    </div>
</section>
<!-- /Section: intro -->

<!-- Section: about -->
<section id="about" class="home-section text-center bg-gray">
    <div class="heading-about marginbot-50">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">

                    <div class="section-heading">
                        <h2>About us</h2>

                        <p>Lorem ipsum dolor sit amet, no nisl mentitum recusabo per, vim at blandit qualisque
                            dissentiunt. Diam efficiantur conclusionemque ut has</p>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
            <div class="col-xs-6 col-sm-3 col-md-3">

                <div class="team boxed-grey">
                    <div class="inner">
                        <h5>Anna Hanaceck</h5>

                        <p class="subtitle">Pixel Crafter</p>

                        <div class="avatar"><img src="/resources/img/team/1.jpg" alt="" class="img-responsive"/></div>
                    </div>
                </div>

            </div>
            <div class="col-xs-6 col-sm-3 col-md-3">

                <div class="team boxed-grey">
                    <div class="inner">
                        <h5>Maura Daniels</h5>

                        <p class="subtitle">Ruby on Rails</p>

                        <div class="avatar"><img src="/resources/img/team/2.jpg" alt="" class="img-responsive"/></div>
                    </div>

                </div>
            </div>
            <div class="col-xs-6 col-sm-3 col-md-3">

                <div class="team boxed-grey">
                    <div class="inner">
                        <h5>Jack Briane</h5>

                        <p class="subtitle">jQuery Ninja</p>

                        <div class="avatar"><img src="/resources/img/team/3.jpg" alt="" class="img-responsive"/></div>
                    </div>
                </div>

            </div>
            <div class="col-xs-6 col-sm-3 col-md-3">

                <div class="team boxed-grey">
                    <div class="inner">
                        <h5>Tom Petterson</h5>

                        <p class="subtitle">Typographer</p>

                        <div class="avatar"><img src="/resources/img/team/4.jpg" alt="" class="img-responsive"/></div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
<!-- /Section: about -->


<!-- Section: services -->
<section id="service" class="home-section text-center">

    <div class="heading-about marginbot-50">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">

                    <div class="section-heading">
                        <h2>Our Services</h2>

                        <p>Lorem ipsum dolor sit amet, no nisl mentitum recusabo per, vim at blandit qualisque
                            dissentiunt. Diam efficiantur conclusionemque ut has</p>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="container">
        <div class="row">
            <div class="col-sm-3 col-md-3">

                <div class="service-box">
                    <div class="service-icon">
                        <i class="fa fa-code fa-3x"></i>
                    </div>
                    <div class="service-desc">
                        <h5>Print</h5>

                        <p>Vestibulum tincidunt enim in pharetra malesuada. Duis semper magna metus electram
                            accommodare.</p>
                    </div>
                </div>

            </div>
            <div class="col-sm-3 col-md-3">

                <div class="service-box">
                    <div class="service-icon">
                        <i class="fa fa-suitcase fa-3x"></i>
                    </div>
                    <div class="service-desc">
                        <h5>Web Design</h5>

                        <p>Vestibulum tincidunt enim in pharetra malesuada. Duis semper magna metus electram
                            accommodare.</p>
                    </div>
                </div>

            </div>
            <div class="col-sm-3 col-md-3">

                <div class="service-box">
                    <div class="service-icon">
                        <i class="fa fa-cog fa-3x"></i>
                    </div>
                    <div class="service-desc">
                        <h5>Photography</h5>

                        <p>Vestibulum tincidunt enim in pharetra malesuada. Duis semper magna metus electram
                            accommodare.</p>
                    </div>
                </div>

            </div>
            <div class="col-sm-3 col-md-3">

                <div class="service-box">
                    <div class="service-icon">
                        <i class="fa fa-rocket fa-3x"></i>
                    </div>
                    <div class="service-desc">
                        <h5>Cloud System</h5>

                        <p>Vestibulum tincidunt enim in pharetra malesuada. Duis semper magna metus electram
                            accommodare.</p>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
<!-- /Section: services -->


<!-- Section: works -->
<section id="works" class="home-section text-center bg-gray">
    <div class="heading-works marginbot-50">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">

                    <div class="section-heading">
                        <h2>Recent Works</h2>

                        <p>Lorem ipsum dolor sit amet, no nisl mentitum recusabo per, vim at blandit qualisque
                            dissentiunt. Diam efficiantur conclusionemque ut has</p>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
            <div class="col-sm-12 col-md-12 col-lg-12">

                <div class="row gallery-item">
                    <div class="col-md-3">
                        <a href="/resources/img/works/1.jpg" title="This is an image title"
                           data-lightbox-gallery="gallery1" data-lightbox-hidpi="img/works/1@2x.jpg">
                            <img src="/resources/img/works/1.jpg" class="img-responsive" alt="img">
                        </a>
                    </div>
                    <div class="col-md-3">
                        <a href="/resources/img/works/2.jpg" title="This is an image title"
                           data-lightbox-gallery="gallery1" data-lightbox-hidpi="img/works/1@2x.jpg">
                            <img src="/resources/img/works/2.jpg" class="img-responsive" alt="img">
                        </a>
                    </div>
                    <div class="col-md-3">
                        <a href="/resources/img/works/3.jpg" title="This is an image title"
                           data-lightbox-gallery="gallery1" data-lightbox-hidpi="img/works/1@2x.jpg">
                            <img src="/resources/img/works/3.jpg" class="img-responsive" alt="img">
                        </a>
                    </div>
                    <div class="col-md-3">
                        <a href="/resources/img/works/4.jpg" title="This is an image title"
                           data-lightbox-gallery="gallery1" data-lightbox-hidpi="img/works/1@2x.jpg">
                            <img src="/resources/img/works/4.jpg" class="img-responsive" alt="img">
                        </a>
                    </div>
                    <div class="col-md-3">
                        <a href="/resources/img/works/5.jpg" title="This is an image title"
                           data-lightbox-gallery="gallery1" data-lightbox-hidpi="img/works/1@2x.jpg">
                            <img src="/resources/img/works/5.jpg" class="img-responsive" alt="img">
                        </a>
                    </div>
                    <div class="col-md-3">
                        <a href="/resources/img/works/6.jpg" title="This is an image title"
                           data-lightbox-gallery="gallery1" data-lightbox-hidpi="img/works/1@2x.jpg">
                            <img src="/resources/img/works/6.jpg" class="img-responsive" alt="img">
                        </a>
                    </div>
                    <div class="col-md-3">
                        <a href="/resources/img/works/7.jpg" title="This is an image title"
                           data-lightbox-gallery="gallery1" data-lightbox-hidpi="img/works/1@2x.jpg">
                            <img src="/resources/img/works/7.jpg" class="img-responsive" alt="img">
                        </a>
                    </div>
                    <div class="col-md-3">
                        <a href="/resources/img/works/8.jpg" title="This is an image title"
                           data-lightbox-gallery="gallery1" data-lightbox-hidpi="img/works/1@2x.jpg">
                            <img src="/resources/img/works/8.jpg" class="img-responsive" alt="img">
                        </a>
                    </div>
                </div>

            </div>
        </div>
    </div>
</section>
<!-- /Section: works -->

<!-- Section: contact -->
<section id="contact" class="home-section text-center">
    <div class="heading-contact marginbot-50">
        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">

                    <div class="section-heading">
                        <h2>Get in touch</h2>

                        <p>Lorem ipsum dolor sit amet, no nisl mentitum recusabo per, vim at blandit qualisque
                            dissentiunt. Diam efficiantur conclusionemque ut has</p>
                    </div>

                </div>
            </div>
        </div>
    </div>
    <div class="container">

        <div class="row">
            <div class="col-lg-8 col-md-offset-2">
                <div class="boxed-grey">
                    <form id="contact-form">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="name">
                                        Name</label>
                                    <input type="text" class="form-control" id="name" placeholder="Enter name"
                                           required="required"/>
                                </div>
                                <div class="form-group">
                                    <label for="email">
                                        Email Address</label>

                                    <div class="input-group">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-envelope"></span>
                                </span>
                                        <input type="email" class="form-control" id="email" placeholder="Enter email"
                                               required="required"/></div>
                                </div>
                                <div class="form-group">
                                    <label for="subject">
                                        Subject</label>
                                    <select id="subject" name="subject" class="form-control" required="required">
                                        <option value="na" selected="">Choose One:</option>
                                        <option value="service">General Customer Service</option>
                                        <option value="suggestions">Suggestions</option>
                                        <option value="product">Product Support</option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="form-group">
                                    <label for="name">
                                        Message</label>
                            <textarea name="message" id="message" class="form-control" rows="9" cols="25"
                                      required="required"
                                      placeholder="Message"></textarea>
                                </div>
                            </div>
                            <div class="col-md-12">
                                <button type="submit" class="btn btn-skin pull-right" id="btnContactUs">
                                    Send Message
                                </button>
                            </div>
                        </div>
                    </form>
                </div>

                <div class="widget-contact row">
                    <div class="col-lg-6">
                        <address>
                            <strong>Ninestars Ltd.</strong><br>
                            Big Villa 334 Awesome, Beautiful Suite 1200<br>
                            San Francisco, CA 94107<br>
                            <abbr title="Phone">P:</abbr> (123) 456-7890
                        </address>
                    </div>

                    <div class="col-lg-6">
                        <address>
                            <strong>Email</strong><br>
                            <a href="/resources/mailto:#">email.name@example.com</a><br/>
                            <a href="/resources/mailto:#">name.name@example.com</a>
                        </address>

                    </div>
                </div>
            </div>

        </div>

    </div>
</section>

<!-- /Section: contact -->
<%@include file="/WEB-INF/jspf/modal.jspf" %>
<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>

</html>


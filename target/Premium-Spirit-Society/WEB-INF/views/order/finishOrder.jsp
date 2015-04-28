<%--
  Created by IntelliJ IDEA.
  User: Martin
  Date: 23. 2. 2015
  Time: 20:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
Diky za objednavku<br>
volume:  ${productFormWrapperBOs[0].volume}<br>
orderNumber:  ${order.orderNumber}<br>
<form action='expresscheckout' METHOD='POST'>
  <input type='image' name='submit' src='https://www.paypal.com/en_US/i/btn/btn_xpressCheckout.gif' border='0'
         align='top' alt='Check out with PayPal'/>
</form>
</body>
</html>

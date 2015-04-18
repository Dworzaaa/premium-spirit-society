package com.premium.spirit.society.core.presentationLayer;

import com.premium.spirit.society.core.businessLayer.BO.display.ProductDisplayBO;
import com.premium.spirit.society.core.businessLayer.BO.form.OrderFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormBO;
import com.premium.spirit.society.core.businessLayer.BO.form.ProductFormWrapperBO;
import com.premium.spirit.society.core.businessLayer.BO.form.UserFormBO;
import com.premium.spirit.society.core.businessLayer.service.*;
import com.premium.spirit.society.core.dataLayer.entity.OrderEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductEntity;
import com.premium.spirit.society.core.dataLayer.entity.ProductSubcategoryEntity;
import com.premium.spirit.society.core.dataLayer.entity.UserEntity;
import com.premium.spirit.society.core.util.PictureLoader;
import com.premium.spirit.society.core.util.paypalfunctions;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import  javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by Martin on 11. 1. 2015.
 */
@Controller
@Scope("request")
public class OrderController {

    private final OrderFormBO order;
    private List<ProductFormWrapperBO> productFormWrapperBOs;

    private final ProductService productService;
    private final ExportToPdfService exportService;
    private final OrderService orderService;
    private final UserService userService;
    private final MailService mailService;

    private final Mapper dozer;

    @Autowired
    public OrderController(OrderFormBO order, ProductService productService, ExportToPdfService exportService, OrderService orderService, UserService userService, MailService mailService, Mapper dozer) {
        this.order = order;
        this.productService = productService;
        this.exportService = exportService;
        this.orderService = orderService;
        this.userService = userService;
        this.mailService = mailService;
        this.dozer = dozer;
    }


    @RequestMapping(value = "/order/addToCart/{productId}", method = RequestMethod.POST)
    public String orderAddToCartPOST(@PathVariable("productId") int productId, @ModelAttribute("product") @Valid ProductFormBO product, HttpServletRequest request, Model model) {
        if (order.getProducts() == null)
            order.setProducts(new ArrayList<ProductFormBO>());
        for (int i = 0; i != product.getOrderAmount(); i++)
            order.getProducts().add(product);

        String referer = request.getHeader("Referer");
        return "redirect:" + referer;
    }

    @RequestMapping(value = "/order/addToCartAjax/{productId}", method = RequestMethod.POST)
    public void orderAddToCartAjaxPOST(@PathVariable("productId") int productId, @ModelAttribute("product") @Valid ProductFormBO product, HttpServletRequest request, Model model,
                                       @RequestParam("amount") int amount) {
        if (order.getProducts() == null)
            order.setProducts(new ArrayList<ProductFormBO>());
        for (int i = 0; i != product.getOrderAmount(); i++)
            order.getProducts().add(product);
    }


    @RequestMapping(value = "/order/removeFromCartAjax/{productId}", method = RequestMethod.POST)
    public void removeFromCartAjaxPOST(@PathVariable("productId") int productId, @ModelAttribute("product") @Valid ProductFormBO product, HttpServletRequest request, Model model,
                                       @RequestParam("amount") int amount) {
        if (order.getProducts() == null)
            order.setProducts(new ArrayList<ProductFormBO>());

        for (int i = 0; i != product.getOrderAmount(); i++) {
            if (order.getProducts().get(i).getId() == productId) {
                order.getProducts().remove(i);
                break;
            }
        }
    }


    @RequestMapping(value = "/order/removeAllFromCartAjax/{productId}", method = RequestMethod.POST)
    public void removeAllFromCartAjaxPOST(@PathVariable("productId") int productId, @ModelAttribute("product") @Valid ProductFormBO product, HttpServletRequest request, Model model,
                                          @RequestParam("amount") int amount) {
        if (order.getProducts() == null)
            order.setProducts(new ArrayList<ProductFormBO>());

        for (int i = 0; i != product.getOrderAmount(); i++) {
            if (order.getProducts().get(i).getId() == productId) {
                order.getProducts().remove(i);
            }
        }
    }

    @RequestMapping(value = "/shopping-cart", method = RequestMethod.GET)
    public String shoppingCartGET(HttpServletRequest request, Model model) {
        List<String> pictureList = new ArrayList<>();

        if (order.getProducts() != null)
            for (int i = 0; i != order.getProducts().size(); i++) {

                ProductFormBO product = productService.getById(order.getProducts().get(i).getId(), ProductFormBO.class, ProductEntity.class);
                order.getProducts().get(i).setProductSubcategory(dozer.map(product.getProductSubcategory(), ProductSubcategoryEntity.class));

                if (!pictureList.contains(new PictureLoader(dozer.map(product, ProductDisplayBO.class), true).loadPictures().get(0)))
                    pictureList.add(new PictureLoader(dozer.map(product, ProductDisplayBO.class), true).loadPictures().get(0));
            }
        else
            order.setProducts(new ArrayList<ProductFormBO>());


        productFormWrapperBOs = new ArrayList<>();
        for (ProductFormBO productFormBO : order.getProducts()) {
            boolean wrapperContainsCurrentProduct = false;
            for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                if (productFormWrapperBO.getId() == productFormBO.getId()) {
                    wrapperContainsCurrentProduct = true;
                    productFormWrapperBO.setAmount(productFormWrapperBO.getAmount() + 1);
                    break;
                }

            }
            if (!wrapperContainsCurrentProduct) {
                productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO));
            }
        }
        model.addAttribute("order", order);
        model.addAttribute("pictureList", pictureList);
        model.addAttribute("productWrappers", productFormWrapperBOs);
        return "order/shoppingCartView";
    }


    @RequestMapping(value = "/order/finishOrder", method = RequestMethod.POST)
    public String orderFinsihOrderPOST(HttpServletRequest request, Locale locale, Model model, @ModelAttribute("order") @Valid OrderFormBO order, HttpSession session, HttpServletResponse response) {
        productFormWrapperBOs = new ArrayList<>();
        for (ProductFormBO productFormBO : this.order.getProducts()) {
            boolean wrapperContainsCurrentProduct = false;
            for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                if (productFormWrapperBO.getId() == productFormBO.getId()) {
                    wrapperContainsCurrentProduct = true;
                    productFormWrapperBO.setAmount(productFormWrapperBO.getAmount() + 1);
                    break;
                }

            }
            if (!wrapperContainsCurrentProduct) {
                productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO));
            }
        }
        // Just a sample code simulating finish of the order
        String name = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();
        UserFormBO user = userService.getUserByUsername(name);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.format(new Date()).toString();
        long dateLong = Long.parseLong(sdf.format(new Date()).toString());
        dateLong = dateLong * 100000;
        int orderCount = user.getOrders().size() + 1;
        String orderNumber = Long.toString(dateLong) + Long.toString(order.getUserID()) + Long.toString(orderCount);
        order.setOrderNumber(orderNumber);

        order.setDate(new Date());
        order.setState(1);
        order.setUser(dozer.map(user, UserEntity.class));
        order.setUserID(user.getId());
        System.out.println(request.getHeader("paymentMethod"));
        order.setProducts(this.order.getProducts());
        String pdfFilename = orderService.createFileName(user.getUsername(), orderNumber) + ".pdf";
        order.setInvoice(pdfFilename);

        if (orderService.getOrdersByUserId(user.getId()).size() == 0)
            orderService.save(order, OrderEntity.class);  //orderService.save(order, OrderEntity.class);
        else
            orderService.merge(order, OrderEntity.class);


        orderService.createPdf(pdfFilename, productFormWrapperBOs, order, locale);

        productFormWrapperBOs = new ArrayList<>();
        for (ProductFormBO productFormBO : order.getProducts()) {
            boolean wrapperContainsCurrentProduct = false;
            for (ProductFormWrapperBO productFormWrapperBO : productFormWrapperBOs) {
                if (productFormWrapperBO.getId() == productFormBO.getId()) {
                    wrapperContainsCurrentProduct = true;
                    productFormWrapperBO.setAmount(productFormWrapperBO.getAmount() + 1);
                    break;
                }

            }
            if (!wrapperContainsCurrentProduct) {
                productFormWrapperBOs.add(new ProductFormWrapperBO(productFormBO));
            }
        }


        mailService.notifyOrderCreated(user, productFormWrapperBOs, pdfFilename, locale);

        this.order.setProducts(new ArrayList<ProductFormBO>());




        /*
 ==================================================================
 PayPal Express Checkout - MARK FLOW : START SNIPPET
 ===================================================================
*/
//IMPORTANT NOTE: Please import Class paypalfunctions if not in the same package level.
// import paypalfunctions;
// TODO: temp solution
        String PaymentOption = "PayPal";
        paypalfunctions paypalfunctions = new paypalfunctions();

        if (PaymentOption == "PayPal") {
    /*
    '------------------------------------
	' The paymentAmount is the total value of
	' the shopping cart, that was set
	' earlier in a session variable
	' by the shopping cart page
	'------------------------------------
	*/

            String paymentAmount = (String) session.getAttribute("Payment_Amount");

	/*
    '------------------------------------
	' The currencyCodeType and paymentType
	' are set to the selections made on the Integration Assistant
	'------------------------------------
	*/

            String currencyCodeType = "EUR";
            String paymentType = "Sale";

	/*
    '------------------------------------
	' The returnURL is the location where buyers return to when a
	' payment has been succesfully authorized.
	'
	' This is set to the value entered on the Integration Assistant
	'------------------------------------
	*/

            String returnURL = "http://www.premium-spirit-society.com/OrderConfirmPage.xxx";

	/*
	'------------------------------------
	' The cancelURL is the location buyers are sent to when they hit the
	' cancel button during authorization of payment during the PayPal flow
	'
	' This is set to the value entered on the Integration Assistant
	'------------------------------------
	*/
            String cancelURL = "http://www.premium-spirit-society.com/";

	/*
	'------------------------------------
	' When you integrate this code
	' set the variables below with
	' shipping address details
	' entered by the user on the
	' Shipping page.
	'------------------------------------
	*/

            String shipToName = "<<ShiptoName>>";
            String shipToStreet = "<<ShipToStreet>>";
            String shipToStreet2 = "<<ShipToStreet2>>"; //'Leave it blank if there is no value
            String shipToCity = "<<ShipToCity>>";
            String shipToState = "<<ShipToState>>";
            String shipToCountryCode = "<<ShipToCountryCode>>"; //' Please refer to the PayPal country codes in the API documentation
            String shipToZip = "<<ShipToZip>>";
            String phoneNum = "<<PhoneNumber>>";

	/*
	'------------------------------------
	' Calls the SetExpressCheckout API call
	'
	' The CallMarkExpressCheckout function is defined in the file PayPalFunctions.asp,
	' it is included at the top of this file.
	'-------------------------------------------------
	*/
            paypalfunctions ppf = new paypalfunctions();
            java.util.HashMap nvp = paypalfunctions.CallMarkExpressCheckout(paymentAmount, returnURL, cancelURL,
                    shipToName, shipToStreet, shipToStreet2, shipToCity, shipToState,
                    shipToCountryCode, shipToZip, phoneNum);

            String strAck = nvp.get("ACK").toString();
            if (strAck != null && !(strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning"))) {
                session.setAttribute("token", nvp.get("TOKEN").toString());
                //' Redirect to paypal.com

                paypalfunctions.RedirectURL(response, nvp.get("TOKEN").toString());
            } else {
                // Display a user friendly Error on the page using any of the following error information returned by PayPal

                String ErrorCode = nvp.get("L_ERRORCODE0").toString();
                String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
                String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
                String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
            }
        } //else {
        //if (((PaymentOption == "Visa") || (PaymentOption == "MasterCard") || (PaymentOption == "Amex") || (PaymentOption = "Discover")) &&           (PaymentProcessorSelected == "PayPal Direct Payment"))

	/*
	'------------------------------------
	' The paymentAmount is the total value of
	' the shopping cart, that was set
	' earlier in a session variable
	' by the shopping cart page
	'------------------------------------
	*/
        String paymentAmount = (String) session.getAttribute("Payment_Amount");

	/*
	'------------------------------------
	' The paymentType that was selected earlier
	'------------------------------------
	*/
        String paymentType = "Sale";

	/*
	' Set these values based on what was selected by the user on the Billing page Html form
	*/

        String creditCardType = "<<Visa/MasterCard/Amex/Discover>>"; //' Set this to one of the acceptable values (Visa/MasterCard/Amex/Discover) match it to what was selected on your Billing page
        String creditCardNumber = "<<CC number>>"; // ' Set this to the string entered as the credit card number on the Billing page
        String expDate = "<<Expiry Date>>"; // ' Set this to the credit card expiry date entered on the Billing page
        String cvv2 = "<<cvv2>>"; // ' Set this to the CVV2 string entered on the Billing page
        String firstName = "<<firstName>>"; // ' Set this to the customer's first name that was entered on the Billing page
        String lastName = "<<lastName>>"; // ' Set this to the customer's last name that was entered on the Billing page
        String street = "<<street>>"; // ' Set this to the customer's street address that was entered on the Billing page
        String city = "<<city>>"; // ' Set this to the customer's city that was entered on the Billing page
        String state = "<<state>>"; // ' Set this to the customer's state that was entered on the Billing page
        String zip = "<<zip>>"; // ' Set this to the zip code of the customer's address that was entered on the Billing page
        String countryCode = "<<PayPal Country Code>>"; // ' Set this to the PayPal code for the Country of the customer's address that was entered on the Billing page
        String currencyCode = "<<PayPal Currency Code>>"; // ' Set this to the PayPal code for the Currency used by the customer

	/*
	'------------------------------------------------
	' Calls the DoDirectPayment API call
	'
	' The DirectPayment function is defined in PayPalFunctions.jsp
	' included at the top of this file.
	'-------------------------------------------------

            nvp = DirectPayment(paymentType, paymentAmount, creditCardType, creditCardNumber,
                    expDate, cvv2, firstName, lastName, street, city, state, zip,
                    countryCode, currencyCode);

            String strAck = nvp.get("ACK").toString();
            if (strAck == null || strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")) {
                // Display a user friendly Error on the page using any of the following error information returned by PayPal
                String ErrorCode = nvp.get("L_ERRORCODE0").toString();
                String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
                String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
                String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
            }
        }
        */
/*
 ==================================================================
 PayPal Express Checkout - MARK FLOW : END SNIPPET
 ===================================================================
*/





        return "/order/thanksView";
    }

    private void test (HttpSession session,HttpServletRequest request){
        /*
 ==================================================================
 PayPal Express Checkout - ORDER CONFIRM : START SNIPPET
 ===================================================================
*/
        String token = (String) session.getAttribute("token");
        if ( token != null)
        {

//IMPORTANT NOTE: Please import Class paypalfunctions if not in the same package level.
// import paypalfunctions;

    /*
	'------------------------------------
	' Get the token parameter value stored in the session
	' from the previous SetExpressCheckout call
	'------------------------------------
	*/
          //  String token =  session.getAttribute("TOKEN");

	/*
	'------------------------------------
	' The paymentAmount is the total value of
	' the shopping cart, that was set
	' earlier in a session variable
	' by the shopping cart page
	'------------------------------------
	*/

            String serverName =  request.getServerName();
            String payerId = (String) session.getAttribute("payerId");
            token = (String) session.getAttribute("token");
            String finalPaymentAmount = (String) session.getAttribute("Payment_Amount");
	/*
	'------------------------------------
	' Calls the DoExpressCheckoutPayment API call
	'
	' The ConfirmPayment function is defined in the file PayPalFunctions.jsp,
	' that is included at the top of this file.
	'-------------------------------------------------
	*/

            paypalfunctions ppf = new paypalfunctions();
            java.util.HashMap nvp = ppf.ConfirmPayment ( token, payerId, finalPaymentAmount, serverName );
            String strAck = nvp.get("ACK").toString();
            if(strAck !=null && !(strAck.equalsIgnoreCase("Success") || strAck.equalsIgnoreCase("SuccessWithWarning")))
            {
		/*
		'********************************************************************************************************************
		'
		' THE PARTNER SHOULD SAVE THE KEY TRANSACTION RELATED INFORMATION LIKE
		'                    transactionId & orderTime
		'  IN THEIR OWN  DATABASE
		' AND THE REST OF THE INFORMATION CAN BE USED TO UNDERSTAND THE STATUS OF THE PAYMENT
		'
		'********************************************************************************************************************
		*/

                String transactionId	= nvp.get("PAYMENTINFO_0_TRANSACTIONID").toString(); // ' Unique transaction ID of the payment. Note:  If the PaymentAction of the request was Authorization or Order, this value is your AuthorizationID for use with the Authorization & Capture APIs.
                String transactionType 	= nvp.get("PAYMENTINFO_0_TRANSACTIONTYPE").toString(); //' The type of transaction Possible values: l  cart l  express-checkout
                String paymentType		= nvp.get("PAYMENTINFO_0_PAYMENTTYPE").toString();  //' Indicates whether the payment is instant or delayed. Possible values: l  none l  echeck l  instant
                String orderTime 		= nvp.get("PAYMENTINFO_0_ORDERTIME").toString();  //' Time/date stamp of payment
                String amt				= nvp.get("PAYMENTINFO_0_AMT").toString();  //' The final amount charged, including any shipping and taxes from your Merchant Profile.
                String currencyCode		= nvp.get("PAYMENTINFO_0_CURRENCYCODE").toString();  //' A three-character currency code for one of the currencies listed in PayPay-Supported Transactional Currencies. Default: USD.
                String feeAmt			= nvp.get("PAYMENTINFO_0_FEEAMT").toString();  //' PayPal fee amount charged for the transaction
                String settleAmt		= nvp.get("PAYMENTINFO_0_SETTLEAMT").toString();  //' Amount deposited in your PayPal account after a currency conversion.
                String taxAmt			= nvp.get("PAYMENTINFO_0_TAXAMT").toString();  //' Tax charged on the transaction.
                String exchangeRate		= nvp.get("PAYMENTINFO_0_EXCHANGERATE").toString();  //' Exchange rate if a currency conversion occurred. Relevant only if your are billing in their non-primary currency. If the customer chooses to pay with a currency other than the non-primary currency, the conversion occurs in the customer’s account.

		/*
		' Status of the payment:
				'Completed: The payment has been completed, and the funds have been added successfully to your account balance.
				'Pending: The payment is pending. See the PendingReason element for more information.
		*/

                String paymentStatus	= nvp.get("PAYMENTINFO_0_PAYMENTSTATUS").toString();

		/*
		'The reason the payment is pending:
		'  none: No pending reason
		'  address: The payment is pending because your customer did not include a confirmed shipping address and your Payment Receiving Preferences is set such that you want to manually accept or deny each of these payments. To change your preference, go to the Preferences section of your Profile.
		'  echeck: The payment is pending because it was made by an eCheck that has not yet cleared.
		'  intl: The payment is pending because you hold a non-U.S. account and do not have a withdrawal mechanism. You must manually accept or deny this payment from your Account Overview.
		'  multi-currency: You do not have a balance in the currency sent, and you do not have your Payment Receiving Preferences set to automatically convert and accept this payment. You must manually accept or deny this payment.
		'  verify: The payment is pending because you are not yet verified. You must verify your account before you can accept this payment.
		'  other: The payment is pending for a reason other than those listed above. For more information, contact PayPal customer service.
		*/

                String pendingReason	= nvp.get("PAYMENTINFO_0_PENDINGREASON").toString();

		/*
		'The reason for a reversal if TransactionType is reversal:
		'  none: No reason code
		'  chargeback: A reversal has occurred on this transaction due to a chargeback by your customer.
		'  guarantee: A reversal has occurred on this transaction due to your customer triggering a money-back guarantee.
		'  buyer-complaint: A reversal has occurred on this transaction due to a complaint about the transaction from your customer.
		'  refund: A reversal has occurred on this transaction because you have given the customer a refund.
		'  other: A reversal has occurred on this transaction due to a reason not listed above.
		*/

                String reasonCode		= nvp.get("PAYMENTINFO_0_REASONCODE").toString();
            }
            else
            {
                // Display a user friendly Error on the page using any of the following error information returned by PayPal

                String ErrorCode = nvp.get("L_ERRORCODE0").toString();
                String ErrorShortMsg = nvp.get("L_SHORTMESSAGE0").toString();
                String ErrorLongMsg = nvp.get("L_LONGMESSAGE0").toString();
                String ErrorSeverityCode = nvp.get("L_SEVERITYCODE0").toString();
            }
        }

/*
 ==================================================================
 PayPal Express Checkout - ORDER CONFIRM : START SNIPPET
 ===================================================================
*/
    }


}
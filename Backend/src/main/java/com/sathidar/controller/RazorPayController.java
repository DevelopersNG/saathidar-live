package com.sathidar.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.sathidar.model.RazorPayModel;
import com.sathidar.model.RazorPayResponse;
import com.sathidar.model.UpdateMember;
import com.sathidar.service.RazorPayService;

//@CrossOrigin(origins = "http://localhost:4200" , methods = {RequestMethod.OPTIONS, RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}, allowedHeaders = "*", allowCredentials = "true")

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class RazorPayController {

	private RazorpayClient client;
	private static Gson gson = new Gson();

	@Autowired
	private RazorPayService razorPayService;
	
	/**
	 * add your secretId and secretValue you got from your RazorPay account.
	 */
//	private static final String SECRET_ID = "rzp_test_QHOsVAlUo7NNwl";
//	private static final String SECRET_KEY = "kilJp0Ez5I45gXHraysPxKUW";

	
	@PostMapping(path = "/member/upgrade/plan")
	public String updateMember(@Validated @RequestBody RazorPayModel razorPayModel) {
		
		JSONObject jsObject = new JSONObject();  
		HashMap<String, String> map = new HashMap<>();
		
			 int status=0;
			 status=razorPayService.updatePremiumMemberDetails(razorPayModel);
			 if(status!=0) {
			 jsObject.put("data",map);
			 jsObject.put("results","1");
			 jsObject.put("message","payment done, plan is upgrade...");
		}else {
			jsObject.put("results","0");
			jsObject.put("message","Something wrong , please try again...");
//			map.put("message", "Something wrong , please try again....");
//			map.put("results", "0"); 
		}
		return jsObject.toString();
	}
	
	
	
	
	@RequestMapping(value="/createPayment", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<String> createOrder(@RequestBody RazorPayModel razorPayModel) {
			
		try {
		
			/**
			 * creating an order in RazorPay.
			 * new order will have order id. you can get this order id by calling  order.get("id")
			 */
			Order order = createRazorPayOrder( razorPayModel.getAmount() );
			RazorPayModel razorPay = getRazorPay((String)order.get("id"), razorPayModel);
			
			// sms
			
			// email
			
			
			return new ResponseEntity<String>(gson.toJson(getResponse(razorPay, 200)),
					HttpStatus.OK);
		} catch (RazorpayException e) {
			e.printStackTrace();
		}
		return new ResponseEntity<String>(gson.toJson(getResponse(new RazorPayModel(), 500)),
				HttpStatus.EXPECTATION_FAILED);
	}
	
	private RazorPayResponse getResponse(RazorPayModel razorPayModel, int statusCode) {
		RazorPayResponse response = new RazorPayResponse();
		response.setStatusCode(statusCode);
		response.setRazorPay(razorPayModel);
		return response;
	}	
	
	private RazorPayModel getRazorPay(String orderId, RazorPayModel razorPayModel) {
		RazorPayModel razorPay = new RazorPayModel();
		razorPay.setApplicationFee(convertRupeeToPaise(razorPayModel.getAmount()));
		razorPay.setCustomerName(razorPayModel.getCustomerName());
		razorPay.setCustomerEmail(razorPayModel.getEmail());
		razorPay.setMerchantName("Live");
		razorPay.setPurchaseDescription("LIVE PURCHASES");
		razorPay.setRazorpayOrderId(orderId);
//		razorPay.setSecretKey(SECRET_ID);
		razorPay.setImageURL("/logo");
		razorPay.setTheme("#F37254");
		razorPay.setNotes("notes"+orderId);
		
		return razorPay;
	}
	
	private Order createRazorPayOrder(String amount) throws RazorpayException {
		
		JSONObject options = new JSONObject();
		options.put("amount", convertRupeeToPaise(amount));
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 1); // You can enable this if you want to do Auto Capture. 
		return client.Orders.create(options);
	}
	
	private String convertRupeeToPaise(String paise) {
		BigDecimal b = new BigDecimal(paise);
		BigDecimal value = b.multiply(new BigDecimal("100"));
		return value.setScale(0, RoundingMode.UP).toString();
		 
	}
}

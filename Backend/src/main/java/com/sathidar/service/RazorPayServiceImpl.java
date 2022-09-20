package com.sathidar.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sathidar.EntityMangerFactory.GetNameByIDMangerFactory;
import com.sathidar.model.RazorPayModel;
import com.sathidar.repository.RazorPayRepository;
import com.sathidar.util.Constant;
import com.sathidar.util.EmailBodyClass;

@Service
public class RazorPayServiceImpl implements RazorPayService {

	@Autowired
	private RazorPayRepository razorPayRepository;

	@Autowired
	private GetNameByIDMangerFactory getNameByIDMangerFactory;

	@Autowired
	private EmailService mailSender;
	
	@Autowired
	private ServerEmailService serverEmailService;
	
	@Override
	public int updatePremiumMemberDetails(RazorPayModel razorPayModel) {
		
		String member_id=razorPayModel.getMember_id();
		String plan_name=razorPayModel.getPlan_name();
		String plan_amount=razorPayModel.getPlan_amount();
		
		int plan_id= getNameByIDMangerFactory.getUpgradePlanIdByName(plan_name);
		
		int status=razorPayRepository.updatePremiumMemberDetails(member_id,plan_id);
		int status_update_member=razorPayRepository.updatePremiumMemberDetailsInMemberTable(member_id,plan_id);
		
		System.out.println("status plan "+status);
		if(status>0) {
		List lst=new ArrayList();
		lst= getDetailsMemberByMember_id(""+plan_id);
		
		String fullName="",emailId_to="";
		List<Object[]> results = razorPayRepository.getUserNameEmailId(Integer.parseInt(member_id));
		if (results != null) {
			for (Object[] obj : results) {
				int i=0;
				fullName= convertNullToBlank(String.valueOf(obj[i])) +" " + convertNullToBlank(String.valueOf(obj[++i]));
				emailId_to= convertNullToBlank(String.valueOf(obj[++i]));
			}
		}
		String  response="";
		if(lst!=null) {
//			 response=sentPlanDetailsByEmail(lst,emailId_to,fullName);
			 Constant constant=new Constant();
			 EmailBodyClass emailBodyClass =new EmailBodyClass();
					 
			String imageLink=constant.project_logo;
			String email_body=emailBodyClass.BuyPremiumPlanMail(lst,emailId_to,fullName,imageLink);		
			try {
				serverEmailService.send(emailId_to, "Saathidaar-Plan Purchase", email_body);					
			} catch (Exception e) {
				e.printStackTrace();
			}
			 
		}
	}
		return status;
	}

	private String sentPlanDetailsByEmail(List lst, String emailId_to, String fullName) {
		String respons="";
		try {
				String plan_name = convertNullToBlank(lst.get(0).toString());
				String plan_amount = convertNullToBlank(lst.get(1).toString());
				String plan_durations = convertNullToBlank(lst.get(2).toString());
				
				 String email_body="<html>\r\n" + 
						"  <head>\r\n" + 
						"    <meta name=\"viewport\" content=\"width=device-width\" />\r\n" + 
						"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
						"    <style>\r\n" + 
						"      /* -------------------------------------\r\n" + 
						"          GLOBAL RESETS\r\n" + 
						"      ------------------------------------- */\r\n" + 
						"      img {\r\n" + 
						"        border: none;\r\n" + 
						"        -ms-interpolation-mode: bicubic;\r\n" + 
						"        max-width: 100%; }\r\n" + 
						"      body {\r\n" + 
						"        background-color: #f6f6f6;\r\n" + 
						"        font-family: sans-serif;\r\n" + 
						"        -webkit-font-smoothing: antialiased;\r\n" + 
						"        font-size: 14px;\r\n" + 
						"        line-height: 1.4;\r\n" + 
						"        margin: 0;\r\n" + 
						"        padding: 0; \r\n" + 
						"        -ms-text-size-adjust: 100%;\r\n" + 
						"        -webkit-text-size-adjust: 100%; }\r\n" + 
						"      table {\r\n" + 
						"        border-collapse: separate;\r\n" + 
						"        mso-table-lspace: 0pt;\r\n" + 
						"        mso-table-rspace: 0pt;\r\n" + 
						"        width: 100%; }\r\n" + 
						"        table td {\r\n" + 
						"          font-family: sans-serif;\r\n" + 
						"          font-size: 14px;\r\n" + 
						"          vertical-align: top; }\r\n" + 
						"      /* -------------------------------------\r\n" + 
						"          BODY & CONTAINER\r\n" + 
						"      ------------------------------------- */\r\n" + 
						"      .body {\r\n" + 
						"        background-color: #f6f6f6;\r\n" + 
						"        width: 100%; }\r\n" + 
						"      /* Set a max-width, and make it display as block so it will automatically stretch to that width, but will also shrink down on a phone or something */\r\n" + 
						"      .container {\r\n" + 
						"        display: block;\r\n" + 
						"        Margin: 0 auto !important;\r\n" + 
						"        /* makes it centered */\r\n" + 
						"        max-width: 580px;\r\n" + 
						"        padding: 10px;\r\n" + 
						"        width: 580px; }\r\n" + 
						"      /* This should also be a block element, so that it will fill 100% of the .container */\r\n" + 
						"      .content {\r\n" + 
						"        box-sizing: border-box;\r\n" + 
						"        display: block;\r\n" + 
						"        Margin: 0 auto;\r\n" + 
						"        max-width: 580px;\r\n" + 
						"        padding: 10px; }\r\n" + 
						"      /* -------------------------------------\r\n" + 
						"          HEADER, FOOTER, MAIN\r\n" + 
						"      ------------------------------------- */\r\n" + 
						"      .main {\r\n" + 
						"        background: #fff;\r\n" + 
						"        border-radius: 3px;\r\n" + 
						"        width: 100%; }\r\n" + 
						"      .wrapper {\r\n" + 
						"        box-sizing: border-box;\r\n" + 
						"        padding: 20px; }\r\n" + 
						"      .footer {\r\n" + 
						"        clear: both;\r\n" + 
						"        padding-top: 10px;\r\n" + 
						"        text-align: center;\r\n" + 
						"        width: 100%; }\r\n" + 
						"        .footer td,\r\n" + 
						"        .footer p,\r\n" + 
						"        .footer span,\r\n" + 
						"        .footer a {\r\n" + 
						"          color: #999999;\r\n" + 
						"          font-size: 12px;\r\n" + 
						"          text-align: center; }\r\n" + 
						"      /* -------------------------------------\r\n" + 
						"          TYPOGRAPHY\r\n" + 
						"      ------------------------------------- */\r\n" + 
						"      h1,\r\n" + 
						"      h2,\r\n" + 
						"      h3,\r\n" + 
						"      h4 {\r\n" + 
						"        color: #000000;\r\n" + 
						"        font-family: sans-serif;\r\n" + 
						"        font-weight: 400;\r\n" + 
						"        line-height: 1.4;\r\n" + 
						"        margin: 0;\r\n" + 
						"        Margin-bottom: 30px; }\r\n" + 
						"      h1 {\r\n" + 
						"        font-size: 35px;\r\n" + 
						"        font-weight: 300;\r\n" + 
						"        text-align: center;\r\n" + 
						"        text-transform: capitalize; }\r\n" + 
						"      p,\r\n" + 
						"      ul,\r\n" + 
						"      ol {\r\n" + 
						"        font-family: sans-serif;\r\n" + 
						"        font-size: 14px;\r\n" + 
						"        font-weight: normal;\r\n" + 
						"        margin: 0;\r\n" + 
						"        Margin-bottom: 15px; }\r\n" + 
						"        p li,\r\n" + 
						"        ul li,\r\n" + 
						"        ol li {\r\n" + 
						"          list-style-position: inside;\r\n" + 
						"          margin-left: 5px; }\r\n" + 
						"      a {\r\n" + 
						"        color: #3498db;\r\n" + 
						"        text-decoration: underline; }\r\n" + 
						"      /* -------------------------------------\r\n" + 
						"          BUTTONS\r\n" + 
						"      ------------------------------------- */\r\n" + 
						"      .btn {\r\n" + 
						"        box-sizing: border-box;\r\n" + 
						"        width: 100%; }\r\n" + 
						"        .btn > tbody > tr > td {\r\n" + 
						"          padding-bottom: 15px; }\r\n" + 
						"        .btn table {\r\n" + 
						"          width: auto; }\r\n" + 
						"        .btn table td {\r\n" + 
						"          background-color: #ffffff;\r\n" + 
						"          border-radius: 5px;\r\n" + 
						"          text-align: center; }\r\n" + 
						"        .btn a {\r\n" + 
						"          background-color: #ffffff;\r\n" + 
						"          border: solid 1px #3498db;\r\n" + 
						"          border-radius: 5px;\r\n" + 
						"          box-sizing: border-box;\r\n" + 
						"          color: #3498db;\r\n" + 
						"          cursor: pointer;\r\n" + 
						"          display: inline-block;\r\n" + 
						"          font-size: 14px;\r\n" + 
						"          font-weight: bold;\r\n" + 
						"          margin: 0;\r\n" + 
						"          padding: 12px 25px;\r\n" + 
						"          text-decoration: none;\r\n" + 
						"          text-transform: capitalize; }\r\n" + 
						"      .btn-primary table td {\r\n" + 
						"        background-color: #3498db; }\r\n" + 
						"      .btn-primary a {\r\n" + 
						"        background-color: #3498db;\r\n" + 
						"        border-color: #3498db;\r\n" + 
						"        color: #ffffff; }\r\n" + 
						"      /* -------------------------------------\r\n" + 
						"          OTHER STYLES THAT MIGHT BE USEFUL\r\n" + 
						"      ------------------------------------- */\r\n" + 
						"      .last {\r\n" + 
						"        margin-bottom: 0; }\r\n" + 
						"      .first {\r\n" + 
						"        margin-top: 0; }\r\n" + 
						"      .align-center {\r\n" + 
						"        text-align: center; }\r\n" + 
						"      .align-right {\r\n" + 
						"        text-align: right; }\r\n" + 
						"      .align-left {\r\n" + 
						"        text-align: left; }\r\n" + 
						"      .clear {\r\n" + 
						"        clear: both; }\r\n" + 
						"      .mt0 {\r\n" + 
						"        margin-top: 0; }\r\n" + 
						"      .mb0 {\r\n" + 
						"        margin-bottom: 0; }\r\n" + 
						"      .preheader {\r\n" + 
						"        color: transparent;\r\n" + 
						"        display: none;\r\n" + 
						"        height: 0;\r\n" + 
						"        max-height: 0;\r\n" + 
						"        max-width: 0;\r\n" + 
						"        opacity: 0;\r\n" + 
						"        overflow: hidden;\r\n" + 
						"        mso-hide: all;\r\n" + 
						"        visibility: hidden;\r\n" + 
						"        width: 0; }\r\n" + 
						"      .powered-by a {\r\n" + 
						"        text-decoration: none; }\r\n" + 
						"      hr {\r\n" + 
						"        border: 0;\r\n" + 
						"        border-bottom: 1px solid #f6f6f6;\r\n" + 
						"        Margin: 20px 0; }\r\n" + 
						"        table {\r\n" + 
						"  font-family: arial, sans-serif;\r\n" + 
						"  border-collapse: collapse;\r\n" + 
						"  width: 100%;\r\n" + 
						"}\r\n" + 
						"td, th {\r\n" + 
						" font-size: 12px;\r\n" + 
						"  text-align: left;\r\n" + 
						"  padding: 8px;\r\n" + 
						"}\r\n" + 
						"       img\r\n" + 
						"       {\r\n" + 
						"        height: 150px;\r\n" + 
						"       }\r\n" + 
						"       .bg\r\n" + 
						"       {\r\n" + 
						"        background-color: #742041;\r\n" + 
						"       }\r\n" + 
						"       button\r\n" + 
						"       {\r\n" + 
						"        background-color: #742041;color: #ffff;margin: 5px;\r\n" + 
						"       }\r\n" + 
						"      /* -------------------------------------\r\n" + 
						"          RESPONSIVE AND MOBILE FRIENDLY STYLES\r\n" + 
						"      ------------------------------------- */\r\n" + 
						"      @media only screen and (max-width: 620px) {\r\n" + 
						"        table[class=body] h1 {\r\n" + 
						"          font-size: 28px !important;\r\n" + 
						"          margin-bottom: 10px !important; }\r\n" + 
						"        table[class=body] p,\r\n" + 
						"        table[class=body] ul,\r\n" + 
						"        table[class=body] ol,\r\n" + 
						"        table[class=body] td,\r\n" + 
						"        table[class=body] span,\r\n" + 
						"        table[class=body] a {\r\n" + 
						"          font-size: 16px !important; }\r\n" + 
						"        table[class=body] .wrapper,\r\n" + 
						"        table[class=body] .article {\r\n" + 
						"          padding: 10px !important; }\r\n" + 
						"        table[class=body] .content {\r\n" + 
						"          padding: 0 !important; }\r\n" + 
						"        table[class=body] .container {\r\n" + 
						"          padding: 0 !important;\r\n" + 
						"          width: 100% !important; }\r\n" + 
						"        table[class=body] .main {\r\n" + 
						"          border-left-width: 0 !important;\r\n" + 
						"          border-radius: 0 !important;\r\n" + 
						"          border-right-width: 0 !important; }\r\n" + 
						"        table[class=body] .btn table {\r\n" + 
						"          width: 100% !important; }\r\n" + 
						"        table[class=body] .btn a {\r\n" + 
						"          width: 100% !important; }\r\n" + 
						"        table[class=body] .img-responsive {\r\n" + 
						"          height: auto !important;\r\n" + 
						"          max-width: 100% !important;\r\n" + 
						"          width: auto !important; }}\r\n" + 
						"      @media all {\r\n" + 
						"        .ExternalClass {\r\n" + 
						"          width: 100%; }\r\n" + 
						"        .ExternalClass,\r\n" + 
						"        .ExternalClass p,\r\n" + 
						"        .ExternalClass span,\r\n" + 
						"        .ExternalClass font,\r\n" + 
						"        .ExternalClass td,\r\n" + 
						"        .ExternalClass div {\r\n" + 
						"          line-height: 100%; }\r\n" + 
						"        .apple-link a {\r\n" + 
						"          color: inherit !important;\r\n" + 
						"          font-family: inherit !important;\r\n" + 
						"          font-size: inherit !important;\r\n" + 
						"          font-weight: inherit !important;\r\n" + 
						"          line-height: inherit !important;\r\n" + 
						"          text-decoration: none !important; } \r\n" + 
						"        .btn-primary table td:hover {\r\n" + 
						"          background-color: #34495e !important; }\r\n" + 
						"        .btn-primary a:hover {\r\n" + 
						"          background-color: #34495e !important;\r\n" + 
						"          border-color: #34495e !important; } }\r\n" + 
						"    </style>\r\n" + 
						"  </head>\r\n" + 
						"  <body class=\"\">\r\n" + 
						"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\">\r\n" + 
						"      <tr>\r\n" + 
						"        <td>&nbsp;</td>\r\n" + 
						"        <td class=\"container\">\r\n" + 
						"          <div class=\"content\">\r\n" + 
						"            <table class=\"main\">\r\n" + 
						"\r\n" + 
		
						
						"              <tr>\r\n" + 
						"                <td class=\"wrapper\">\r\n" + 
						"                  <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
						"                    <tr>\r\n" + 
						"                      <td>\r\n" + 
						"                        <h1><img src=\"http://103.174.102.195:8080/saathidaar_logo/saathidaar_logo.jpeg\" alt=\"\"></h1>\r\n" + 
						"                        <h2> "+fullName+"</h2>\r\n" + 
						"                        <h5>Thanks you purchasing "+plan_name+" from <a href=\"saathidaar.com\">Saathidaar.com</h5>\r\n" + 
						"                        <h5> Here are your purchase details : </h5>\r\n" + 
						"                  \r\n" + 
						"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\r\n" + 
						"                          <tbody>\r\n" + 
						"                            <tr>\r\n" + 
						"                              <td align=\"left\">\r\n" + 
						"                              </td>\r\n" + 
						"                            </tr>\r\n" + 
						"                          </tbody>\r\n" + 
						"                        </table>\r\n" + 
						"                        <!-- <p>If you received this email by mistake, simply delete it. You won't be subscribed if you don't click the confirmation link above.</p> -->\r\n" + 
						"                        <table style=\"width: 100%;border: #742041 1px solid;\" class=\"table\">\r\n" + 
						"                          <thead>\r\n" + 
						"                            <tr >\r\n" + 
						"                              <th  style=\"color: #000000;\" scope=\"col\">Plan Name</th>\r\n" + 
						"                              <th  style=\"color: #000000;\" scope=\"col\">: "+plan_name+"</th>\r\n" + 
						"                            </tr>\r\n" + 
						"                            <tr>\r\n" + 
						"                              <th style=\"color: #000000;\" scope=\"col\">Premium Membership</th>\r\n" + 
						"                              <th style=\"color: #000000;\" scope=\"col\">: "+plan_amount+"</th>\r\n" + 
						"                            </tr>\r\n" + 
						"                          </thead>\r\n" + 
						"                        </table>\r\n" + 
						"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">\r\n" + 
						"                          <tbody>\r\n" + 
						"                            <tr>\r\n" + 
						"\r\n" + 
						"                             <h5 style=\"color: #000000;\"> Your "+plan_name+" of Rs."+plan_amount+" is for the duration of "+plan_durations+" starting from today.\r\n" + 
						"                                If you have any questions please visit our pages - <a href=\"http://103.150.186.33:8080/saathidaar/#/help-mobile\">HELP</a>, <a href=\"http://103.150.186.33:8080/saathidaar/#/privacy-policy\">Privacy Policy</a>, <a href=\"http://103.150.186.33:8080/saathidaar/#/term-mobile\">T&C</a>, \r\n" + 
						"                              </h5>\r\n" + 
						"                            </tr>\r\n" + 
						"                          </tbody>\r\n" + 
						"                        </table>\r\n" + 
						"                      </td>\r\n" + 
						"                    </tr>\r\n" + 
						"                  </table>\r\n" + 
						"                </td>\r\n" + 
						"              </tr>\r\n" + 
						"\r\n" + 
						"            <!-- END MAIN CONTENT AREA -->\r\n" + 
						"            </table>\r\n" + 
						"\r\n" + 
						"         \r\n" + 
						"            \r\n" + 
						"          <!-- END CENTERED WHITE CONTAINER -->\r\n" + 
						"          </div>\r\n" + 
						"        </td>\r\n" + 
						"        <td>&nbsp;</td>\r\n" + 
						"      </tr>\r\n" + 
						"    </table>\r\n" + 
						"  </body>\r\n" + 
						"</html>";
				
				
				mailSender.send(emailId_to, "Plan Purchases", email_body);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return respons;
	}

	private List getDetailsMemberByMember_id(String plan_id) {
		List lstAdd = new ArrayList();
		try {
			List<Object[]> results = razorPayRepository.getMemberPlanDetailsForMail(plan_id);
			if (results != null) {
				for (Object[] obj : results) {
					int i = 0;
					String plan_name = convertNullToBlank(String.valueOf(obj[i]));
					String plan_durations = convertNullToBlank(String.valueOf(obj[++i]));
					String plan_amount = convertNullToBlank(String.valueOf(obj[++i]));
					System.out.println("in");
					lstAdd.add(plan_name);
					lstAdd.add(plan_amount);
					lstAdd.add(plan_durations);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return lstAdd;
	}

	public String convertNullToBlank(String value) {
		if (value != null && !value.equals("")) {
			return value;
		}
		return "";
	}

}

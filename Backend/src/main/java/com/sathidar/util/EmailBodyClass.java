package com.sathidar.util;

import java.util.List;

import javax.imageio.spi.ImageWriterSpi;

public class EmailBodyClass {

	public Constant constant=new Constant();

	public String getForgotPasswordEmailBody(String firstName, String lastName, String otp, String imageLink) {
		
		String emailBody="\r\n" + 
				"<!doctype html>\r\n" + 
				"<html>\r\n" + 
				"  <head>\r\n" + 
				"    <meta name=\"viewport\" content=\"width=device-width\" />\r\n" + 
				"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
				"    <title>Saathidaar</title>\r\n" + 
				"    <style>\r\n" + 
				"      /* -------------------------------------\r\n" + 
				"          GLOBAL RESETS\r\n" + 
				"      ------------------------------------- */\r\n" + 
				"      *\r\n" + 
				"      {\r\n" + 
				"            font-family: ABeeZee;font-size: 17px;\r\n" + 
				"      }\r\n" + 
				"      img {\r\n" + 
				"        border: none;\r\n" + 
				"        -ms-interpolation-mode: bicubic;\r\n" + 
				"        max-width: 100%; }\r\n" + 
				"      body {\r\n" + 
				"        background-color: rgba(128, 128, 128, 0.377);\r\n" + 
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
				"        width: 100%;\r\n" + 
				"      }\r\n" + 
				"      .bodymain\r\n" + 
				"      {\r\n" + 
				"        -webkit-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);-moz-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);\r\n" + 
				"        box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);border-radius:10px;\r\n" + 
				"      }\r\n" + 
				"\r\n" + 
				"        table td {\r\n" + 
				"          font-family: sans-serif;\r\n" + 
				"          font-size: 14px;\r\n" + 
				"          vertical-align: top;  color: #ffffff; }\r\n" + 
				"      /* -------------------------------------\r\n" + 
				"          BODY & CONTAINER\r\n" + 
				"      ------------------------------------- */\r\n" + 
				"      .body {\r\n" + 
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
				"        background: none;\r\n" + 
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
				"          padding-bottom: 15px;  color: #ffffff; }\r\n" + 
				"        .btn table {\r\n" + 
				"          width: auto; color: #ffffff;  }\r\n" + 
				"        .btn table td {\r\n" + 
				"          background-color: #742041;\r\n" + 
				"          border-radius: 5px;\r\n" + 
				"          text-align: center;  color: #ffffff; }\r\n" + 
				"        .btn a {\r\n" + 
				"          background-color: #de3277;\r\n" + 
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
				" font-size: 14px;\r\n" + 
				"  text-align: left;\r\n" + 
				"  padding: 8px;\r\n" + 
				"}\r\n" + 
				"       img\r\n" + 
				"       {\r\n" + 
				"        height: 150px;\r\n" + 
				"       }\r\n" + 
				"       .bg\r\n" + 
				"       {\r\n" + 
				"        background-color: #de3277;\r\n" + 
				"       }\r\n" + 
				"       button\r\n" + 
				"       {\r\n" + 
				"        background-color: #742041;color: #ffff;margin: 5px;\r\n" + 
				"       }\r\n" + 
				"       hr\r\n" + 
				"       {\r\n" + 
				"        color: #f6f6f6;\r\n" + 
				"       }\r\n" + 
				"       h2{\r\n" + 
				"        color: #ffffff;\r\n" + 
				"       }\r\n" + 
				"       h5{\r\n" + 
				"        color: #ffffff;\r\n" + 
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
				"  <body  class=\"\">\r\n" + 
				"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" >\r\n" + 
				"      <tr>\r\n" + 
				"        <td>&nbsp;</td>\r\n" + 
				"        <td class=\"container\" >\r\n" + 
				"          <div class=\"content\">\r\n" + 
				"            <table class=\"main\">\r\n" + 
				"\r\n" + 
				"              <!-- START MAIN CONTENT AREA -->\r\n" + 
				"              <tr>\r\n" + 
				"                <td class=\"wrapper\">\r\n" + 
				"                  <table style=\"background-image: linear-gradient(to right,#D11861,#680f33d1);\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"bodymain\">\r\n" + 
				"                    <tr>\r\n" + 
				"                      <td>\r\n" + 
//				"                        <h1><img src=\""+imageLink+"\" alt=\"\"></h1>\r\n" + 
				"                        <h1><img src=\"http://103.174.102.195:8080/saathidaar_logo/saathidaar_logo.jpeg\" alt=\"\"></h1>\r\n" + 
				"                        <hr>\r\n" + 
				"                        <h2>Hi "+firstName+ "&nbsp;"+lastName+" </h2>\r\n" + 
				"                        <h5> Your verification code is <strong>"+otp+"</strong></h5>\r\n" + 
				"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"\">\r\n" + 
				"                          <tbody>\r\n" + 
				"                            <tr style=\"width: 100%;border-radius: 5px;background-color: #ffffff;margin-top: 10px;\">\r\n" + 
				"                              <td align=\"left\">\r\n" + 
				"                                <a href=\"\">Saathidaar.com </a><br>\r\n" + 
				"                              </td>\r\n" + 
				"                                <td>\r\n" + 
//				"                                <a href=\"http://htmlemail.io\" target=\"_blank\">View Full Profile</a> \r\n" + 
				"                                </td>\r\n" + 
				"                            </tr>\r\n" + 
				"                          </tbody>\r\n" + 
				"                        </table>\r\n" + 
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
				"</html>\r\n" + 
				"\r\n" + 
				"\r\n" + 
				"";

		return emailBody;
	}

	
	public String sendInvitationsMailToMember(List lst, String fullName, String emailId_to, int request_from_id,
			String imageLink) {
		String emailBody="";
		try {
			String email_id = constant.convertNullToBlank(lst.get(0).toString());
			String first_name = constant.convertNullToBlank(lst.get(1).toString());
			String last_name = constant.convertNullToBlank(lst.get(2).toString());
			String member_id = constant.convertNullToBlank(lst.get(3).toString());
			String height = constant.convertNullToBlank(lst.get(4).toString());
			String age = constant.convertNullToBlank(lst.get(5).toString());
			String marital_status = constant.convertNullToBlank(lst.get(6).toString());
			String education = constant.convertNullToBlank(lst.get(7).toString());
			String profession = constant.convertNullToBlank(lst.get(8).toString());
			String religions = constant.convertNullToBlank(lst.get(9).toString());
			String city = constant.convertNullToBlank(lst.get(10).toString());
			String mother_tongue = constant.convertNullToBlank(lst.get(11).toString());

			
			
			emailBody="\r\n" + 
					"<!doctype html>\r\n" + 
					"<html>\r\n" + 
					"  <head>\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width\" />\r\n" + 
					"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
					"    <title>Saathidaar</title>\r\n" + 
					"    <style>\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          GLOBAL RESETS\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      *\r\n" + 
					"      {\r\n" + 
					"            font-family: ABeeZee;font-size: 17px;\r\n" + 
					"      }\r\n" + 
					"      img {\r\n" + 
					"        border: none;\r\n" + 
					"        -ms-interpolation-mode: bicubic;\r\n" + 
					"        max-width: 100%; }\r\n" + 
					"      body {\r\n" + 
					"        background-color: rgba(128, 128, 128, 0.377);\r\n" + 
					"        font-family: sans-serif;\r\n" + 
					"        -webkit-font-smoothing: antialiased;\r\n" + 
					"        font-size: 17px;\r\n" + 
					"        line-height: 1.4;\r\n" + 
					"        margin: 0;\r\n" + 
					"        padding: 0; \r\n" + 
					"        -ms-text-size-adjust: 100%;\r\n" + 
					"        -webkit-text-size-adjust: 100%; }\r\n" + 
					"      table {\r\n" + 
					"        border-collapse: separate;\r\n" + 
					"        mso-table-lspace: 0pt;\r\n" + 
					"        mso-table-rspace: 0pt;\r\n" + 
					"        width: 100%;\r\n" + 
					"      }\r\n" + 
					"      .bodymain\r\n" + 
					"      {\r\n" + 
					"        -webkit-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);-moz-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);\r\n" + 
					"        box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);border-radius:10px;\r\n" + 
					"      }\r\n" + 
					"\r\n" + 
					"        table td {\r\n" + 
					"          font-family: sans-serif;\r\n" + 
					"          font-size: 17px;\r\n" + 
					"          vertical-align: top;  color: #ffffff; }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          BODY & CONTAINER\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      .body {\r\n" + 
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
					"        background: none;\r\n" + 
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
					"          font-size: 17px;\r\n" + 
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
					"          padding-bottom: 15px;  color: #ffffff; }\r\n" + 
					"        .btn table {\r\n" + 
					"          width: auto; color: #ffffff;  }\r\n" + 
					"        .btn table td {\r\n" + 
					"          background-color: #742041;\r\n" + 
					"          border-radius: 5px;\r\n" + 
					"          text-align: center;  color: #ffffff; }\r\n" + 
					"        .btn a {\r\n" + 
					"          background-color: #de3277;\r\n" + 
					"          border: solid 1px #3498db;\r\n" + 
					"          border-radius: 5px;\r\n" + 
					"          box-sizing: border-box;\r\n" + 
					"          color: #3498db;\r\n" + 
					"          cursor: pointer;\r\n" + 
					"          display: inline-block;\r\n" + 
					"          font-size: 17px;\r\n" + 
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
					" font-size: 14px;\r\n" + 
					"  text-align: left;\r\n" + 
					"  padding: 8px;\r\n" + 
					"}\r\n" + 
					"       img\r\n" + 
					"       {\r\n" + 
					"        height: 150px;\r\n" + 
					"       }\r\n" + 
					"       .bg\r\n" + 
					"       {\r\n" + 
					"        background-color: #de3277;\r\n" + 
					"       }\r\n" + 
					"       button\r\n" + 
					"       {\r\n" + 
					"        background-color: #742041;color: #ffff;margin: 5px;\r\n" + 
					"       }\r\n" + 
					"       hr\r\n" + 
					"       {\r\n" + 
					"        color: #f6f6f6;\r\n" + 
					"       }\r\n" + 
					"       h2{\r\n" + 
					"        color: #ffffff;\r\n" + 
					"       }\r\n" + 
					"       h5{\r\n" + 
					"        color: #ffffff;\r\n" + 
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
					"  <body  class=\"\">\r\n" + 
					"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" >\r\n" + 
					"      <tr>\r\n" + 
					"        <td>&nbsp;</td>\r\n" + 
					"        <td class=\"container\" >\r\n" + 
					"          <div class=\"content\">\r\n" + 
					"            <table class=\"main\" style=\"border-radius: 50px;\">\r\n" + 
					"\r\n" + 
					"              <!-- START MAIN CONTENT AREA -->\r\n" + 
					"              <tr>\r\n" + 
					"                <td class=\"wrapper\">\r\n" + 
					"                  <table  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"bodymain\">\r\n" + 
					"                    <tr style=\"  background-image: linear-gradient(to right,#D11861,#680f33d1);\">\r\n" + 
					"                      <td>\r\n" + 
					"                        <h1><img  src=\""+imageLink+"\" alt=\"\"></h1>\r\n" + 
					"                        <hr>\r\n" + 
					"                        <h2>Hi "+fullName+"</h2>\r\n" + 
					"                        <h5>"+first_name+"&nbsp;"+last_name+" has invited you to connect. Let\'s Respond</h5>\r\n" + 
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <td align=\"left\">\r\n" + 
					"                              </td>\r\n" + 
					"                            </tr>\r\n" + 
					"                          </tbody>\r\n" + 
					"                        </table>\r\n" + 
					"                        <table style=\"width: 100%; background-color: #ffffff;color: #000000;border-radius: 5px;\" class=\"table\">\r\n" + 
					"                          <thead>\r\n";
			if (!age.equals("")) {
					emailBody=	emailBody+	"                            <tr >\r\n" + 
						"                              <th  scope=\"col\">Age  </th>\r\n" + 
						"                              <th  scope=\"col\">: "+age+" </th>\r\n" + 
						"                            </tr>\r\n" ;
			}
			
			if (!height.equals("")) {
				emailBody=	emailBody+"                            <tr >\r\n" + 
						"                              <th  scope=\"col\"> Height </th>\r\n" + 
						"                              <th  scope=\"col\">: "+height+"</th>\r\n" + 
						"                            </tr>\r\n" ;
			}
			
			if (!marital_status.equals("")) {
				emailBody=	emailBody+"                            <tr>\r\n" + 
						"                              <th scope=\"col\">Marital Status </th>\r\n" + 
						"                              <th scope=\"col\">: "+marital_status+"</th>\r\n" + 
						"                      \r\n" ;
			}
			
			if (!education.equals("")) {
				emailBody=	emailBody+"                            </tr>\r\n" + 
						"                            <tr >\r\n" + 
						"                              <th  scope=\"col\"> PG Education </th>\r\n" + 
						"                              <th  scope=\"col\">: "+education+"</th>\r\n" + 
						"                      \r\n" ;
			}
			if (!profession.equals("")) {
				emailBody=	emailBody+"                            </tr>\r\n" + 
						"                            <tr>\r\n" + 
						"                              <th scope=\"col\">Profession </th>\r\n" + 
						"                              <th scope=\"col\">: "+profession+"</th>\r\n" + 
						"                            </tr>\r\n" ;	
			}
			if (!religions.equals("")) {
				emailBody=	emailBody+"                            <tr >\r\n" + 
						"                              <th  scope=\"col\"> Religion </th>\r\n" + 
						"                              <th  scope=\"col\">: "+religions+"</th>\r\n" + 
						"                            </tr>\r\n" ;
				
			}
			if (!mother_tongue.equals("")) {
				emailBody=	emailBody+"                            <tr >\r\n" + 
						"                              <th  scope=\"col\"> Mother Tongue </th>\r\n" + 
						"                              <th  scope=\"col\">: "+mother_tongue+"</th>\r\n" + 
						"                            </tr>\r\n" ;
			}
			if (!city.equals("")) {
				emailBody=	emailBody+"                            <tr >\r\n" + 
						"                              <th  scope=\"col\"> City </th>\r\n" + 
						"                              <th  scope=\"col\">: "+city+"</th>\r\n" + 
						"                            </tr>\r\n" ;
			}
			emailBody=	emailBody+"  </thead>\r\n" + 
					"                          \r\n" + 
					"                        </table>\r\n" + 
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  style=\"width: 100%;border-radius: 5px;background-color: #ffffff;margin-top: 10px;\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <td> <a href=\"http://103.174.102.195:8080/saathidaar/#/member-profile/" + member_id+"\" target=\"_blank\">View Full Profile</a> </td>\r\n" + 
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
					"            \r\n" + 
					"          <!-- END CENTERED WHITE CONTAINER -->\r\n" + 
					"          </div>\r\n" + 
					"        </td>\r\n" + 
					"        <td>&nbsp;</td>\r\n" + 
					"      </tr>\r\n" + 
					"    </table>\r\n" + 
					"  </body>\r\n" + 
					"</html>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailBody;
	}


	
	public String accceptRequestMemberEmail(List lst, String fullName, String emailId_to, int request_from_id,
			String imageLink) {
		String emailBody="";
		
		try {
			String email_id = constant.convertNullToBlank(lst.get(0).toString());
			String first_name = constant.convertNullToBlank(lst.get(1).toString());
			String last_name = constant.convertNullToBlank(lst.get(2).toString());
			String member_id = constant.convertNullToBlank(lst.get(3).toString());
			String height = constant.convertNullToBlank(lst.get(4).toString());
			String age = constant.convertNullToBlank(lst.get(5).toString());
			String marital_status = constant.convertNullToBlank(lst.get(6).toString());
			String education = constant.convertNullToBlank(lst.get(7).toString());
			String profession = constant.convertNullToBlank(lst.get(8).toString());
			String religions = constant.convertNullToBlank(lst.get(9).toString());
			String city = constant.convertNullToBlank(lst.get(10).toString());
			String mother_tongue = constant.convertNullToBlank(lst.get(11).toString());
			
			emailBody="\r\n" + 
					"<!doctype html>\r\n" + 
					"<html>\r\n" + 
					"  <head>\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width\" />\r\n" + 
					"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
					"    <title>Saathidaar</title>\r\n" + 
					"    <style>\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          GLOBAL RESETS\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      *\r\n" + 
					"      {\r\n" + 
					"            font-family: ABeeZee;font-size: 17px;\r\n" + 
					"      }\r\n" + 
					"      img {\r\n" + 
					"        border: none;\r\n" + 
					"        -ms-interpolation-mode: bicubic;\r\n" + 
					"        max-width: 100%; }\r\n" + 
					"      body {\r\n" + 
					"        background-color: rgba(128, 128, 128, 0.377);\r\n" + 
					"        font-family: sans-serif;\r\n" + 
					"        -webkit-font-smoothing: antialiased;\r\n" + 
					"        font-size: 17px;\r\n" + 
					"        line-height: 1.4;\r\n" + 
					"        margin: 0;\r\n" + 
					"        padding: 0; \r\n" + 
					"        -ms-text-size-adjust: 100%;\r\n" + 
					"        -webkit-text-size-adjust: 100%; }\r\n" + 
					"      table {\r\n" + 
					"        border-collapse: separate;\r\n" + 
					"        mso-table-lspace: 0pt;\r\n" + 
					"        mso-table-rspace: 0pt;\r\n" + 
					"        width: 100%;\r\n" + 
					"      }\r\n" + 
					"      .bodymain\r\n" + 
					"      {\r\n" + 
					"        -webkit-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);-moz-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);\r\n" + 
					"        box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);border-radius:10px;\r\n" + 
					"      }\r\n" + 
					"\r\n" + 
					"        table td {\r\n" + 
					"          font-family: sans-serif;\r\n" + 
					"          font-size: 17px;\r\n" + 
					"          vertical-align: top;  color: #ffffff; }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          BODY & CONTAINER\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      .body {\r\n" + 
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
					"        background: none;\r\n" + 
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
					"          font-size: 17px;\r\n" + 
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
					"          padding-bottom: 15px;  color: #ffffff; }\r\n" + 
					"        .btn table {\r\n" + 
					"          width: auto; color: #ffffff;  }\r\n" + 
					"        .btn table td {\r\n" + 
					"          background-color: #742041;\r\n" + 
					"          border-radius: 5px;\r\n" + 
					"          text-align: center;  color: #ffffff; }\r\n" + 
					"        .btn a {\r\n" + 
					"          background-color: #de3277;\r\n" + 
					"          border: solid 1px #3498db;\r\n" + 
					"          border-radius: 5px;\r\n" + 
					"          box-sizing: border-box;\r\n" + 
					"          color: #3498db;\r\n" + 
					"          cursor: pointer;\r\n" + 
					"          display: inline-block;\r\n" + 
					"          font-size: 17px;\r\n" + 
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
					" font-size: 14px;\r\n" + 
					"  text-align: left;\r\n" + 
					"  padding: 8px;\r\n" + 
					"}\r\n" + 
					"       img\r\n" + 
					"       {\r\n" + 
					"        height: 150px;\r\n" + 
					"       }\r\n" + 
					"       .bg\r\n" + 
					"       {\r\n" + 
					"        background-color: #de3277;\r\n" + 
					"       }\r\n" + 
					"       button\r\n" + 
					"       {\r\n" + 
					"        background-color: #742041;color: #ffff;margin: 5px;\r\n" + 
					"       }\r\n" + 
					"       hr\r\n" + 
					"       {\r\n" + 
					"        color: #f6f6f6;\r\n" + 
					"       }\r\n" + 
					"       h2{\r\n" + 
					"        color: #ffffff;\r\n" + 
					"       }\r\n" + 
					"       h5{\r\n" + 
					"        color: #ffffff;\r\n" + 
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
					"  <body  class=\"\">\r\n" + 
					"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" >\r\n" + 
					"      <tr>\r\n" + 
					"        <td>&nbsp;</td>\r\n" + 
					"        <td class=\"container\" >\r\n" + 
					"          <div class=\"content\">\r\n" + 
					"            <table class=\"main\" style=\"border-radius: 50px;\">\r\n" + 
					"\r\n" + 
					"              <!-- START MAIN CONTENT AREA -->\r\n" + 
					"              <tr>\r\n" + 
					"                <td class=\"wrapper\">\r\n" + 
					"                  <table  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"bodymain\">\r\n" + 
					"                    <tr style=\"  background-image: linear-gradient(to right,#D11861,#680f33d1);\">\r\n" + 
					"                      <td>\r\n" + 
					"                        <h1><img  src=\""+imageLink+"\" alt=\"\"></h1>\r\n" + 
					"                        <hr>\r\n" + 
					"						<h2 style=\"color: #ffff;text-align: center;\">It\'s a Match</h2>" + 
					"                        <h2>Hi "+fullName+"</h2>\r\n" + 
					"                        <h5>"+first_name+"&nbsp;"+last_name+" has accepted your request to connect. Let\'s take this forward.</h5>\r\n" + 
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <td align=\"left\">\r\n" + 
					"                              </td>\r\n" + 
					"                            </tr>\r\n" + 
					"                          </tbody>\r\n" + 
					"                        </table>\r\n" + 
					"                        <table style=\"width: 100%; background-color: #ffffff;color: #000000;border-radius: 5px;\" class=\"table\">\r\n" + 
					"                          <thead>\r\n";
			if (!age.equals("")) {
					emailBody=	emailBody+	"                            <tr >\r\n" + 
						"                              <th  scope=\"col\">Age  </th>\r\n" + 
						"                              <th  scope=\"col\">: "+age+" </th>\r\n" + 
						"                            </tr>\r\n" ;
			}
			
			if (!height.equals("")) {
				emailBody=	emailBody+"                            <tr >\r\n" + 
						"                              <th  scope=\"col\"> Height </th>\r\n" + 
						"                              <th  scope=\"col\">: "+height+"</th>\r\n" + 
						"                            </tr>\r\n" ;
			}
			
			if (!marital_status.equals("")) {
				emailBody=	emailBody+"                            <tr>\r\n" + 
						"                              <th scope=\"col\">Marital Status </th>\r\n" + 
						"                              <th scope=\"col\">: "+marital_status+"</th>\r\n" + 
						"                      \r\n" ;
			}
			
			if (!education.equals("")) {
				emailBody=	emailBody+"                            </tr>\r\n" + 
						"                            <tr >\r\n" + 
						"                              <th  scope=\"col\"> PG Education </th>\r\n" + 
						"                              <th  scope=\"col\">: "+education+"</th>\r\n" + 
						"                      \r\n" ;
			}
			if (!profession.equals("")) {
				emailBody=	emailBody+"                            </tr>\r\n" + 
						"                            <tr>\r\n" + 
						"                              <th scope=\"col\">Profession </th>\r\n" + 
						"                              <th scope=\"col\">: "+profession+"</th>\r\n" + 
						"                            </tr>\r\n" ;	
			}
			if (!religions.equals("")) {
				emailBody=	emailBody+"                            <tr >\r\n" + 
						"                              <th  scope=\"col\"> Religion </th>\r\n" + 
						"                              <th  scope=\"col\">: "+religions+"</th>\r\n" + 
						"                            </tr>\r\n" ;
				
			}
			if (!mother_tongue.equals("")) {
				emailBody=	emailBody+"                            <tr >\r\n" + 
						"                              <th  scope=\"col\"> Mother Tongue </th>\r\n" + 
						"                              <th  scope=\"col\">: "+mother_tongue+"</th>\r\n" + 
						"                            </tr>\r\n" ;
			}
			if (!city.equals("")) {
				emailBody=	emailBody+"                            <tr >\r\n" + 
						"                              <th  scope=\"col\"> City </th>\r\n" + 
						"                              <th  scope=\"col\">: "+city+"</th>\r\n" + 
						"                            </tr>\r\n" ;
			}
			emailBody=	emailBody+"  </thead>\r\n" + 
					"                          \r\n" + 
					"                        </table>\r\n" + 
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  style=\"width: 100%;border-radius: 5px;background-color: #ffffff;margin-top: 10px;\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <td> <a href=\"http://103.174.102.195:8080/saathidaar/#/member-profile/" + member_id+"\" target=\"_blank\">View Full Profile</a> </td>\r\n" + 
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
					"            \r\n" + 
					"          <!-- END CENTERED WHITE CONTAINER -->\r\n" + 
					"          </div>\r\n" + 
					"        </td>\r\n" + 
					"        <td>&nbsp;</td>\r\n" + 
					"      </tr>\r\n" + 
					"    </table>\r\n" + 
					"  </body>\r\n" + 
					"</html>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailBody;
	}


	
	public String BuyPremiumPlanMail(List lst, String emailId_to, String fullName, String imageLink) {
		String emailBody="";
		try {
			String plan_name = constant.convertNullToBlank(lst.get(0).toString());
			String plan_amount = constant.convertNullToBlank(lst.get(1).toString());
			String plan_durations = constant.convertNullToBlank(lst.get(2).toString());
			
			emailBody="\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"<!doctype html>\r\n" + 
					"<html>\r\n" + 
					"  <head>\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width\" />\r\n" + 
					"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
					"    <title>Saathidaar</title>\r\n" + 
					"    <style>\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          GLOBAL RESETS\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      *\r\n" + 
					"      {\r\n" + 
					"            font-family: ABeeZee;font-size: 17px;\r\n" + 
					"      }\r\n" + 
					"      img {\r\n" + 
					"        border: none;\r\n" + 
					"        -ms-interpolation-mode: bicubic;\r\n" + 
					"        max-width: 100%; }\r\n" + 
					"      body {\r\n" + 
					"        background-color: rgba(128, 128, 128, 0.377);\r\n" + 
					"        font-family: sans-serif;\r\n" + 
					"        -webkit-font-smoothing: antialiased;\r\n" + 
					"        font-size: 17px;\r\n" + 
					"        line-height: 1.4;\r\n" + 
					"        margin: 0;\r\n" + 
					"        padding: 0; \r\n" + 
					"        -ms-text-size-adjust: 100%;\r\n" + 
					"        -webkit-text-size-adjust: 100%; }\r\n" + 
					"      table {\r\n" + 
					"        border-collapse: separate;\r\n" + 
					"        mso-table-lspace: 0pt;\r\n" + 
					"        mso-table-rspace: 0pt;\r\n" + 
					"        width: 100%;\r\n" + 
					"      }\r\n" + 
					"      .bodymain\r\n" + 
					"      {\r\n" + 
					"        -webkit-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);-moz-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);\r\n" + 
					"        box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);border-radius:10px;\r\n" + 
					"      }\r\n" + 
					"\r\n" + 
					"        table td {\r\n" + 
					"          font-family: sans-serif;\r\n" + 
					"          font-size: 17px;\r\n" + 
					"          vertical-align: top;  color: #ffffff; }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          BODY & CONTAINER\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      .body {\r\n" + 
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
					"        background: none;\r\n" + 
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
					"          font-size: 17px;\r\n" + 
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
					"        text-decoration: underline; }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          BUTTONS\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      .btn {\r\n" + 
					"        box-sizing: border-box;\r\n" + 
					"        width: 100%; }\r\n" + 
					"        .btn > tbody > tr > td {\r\n" + 
					"          padding-bottom: 15px;  color: #ffffff; }\r\n" + 
					"        .btn table {\r\n" + 
					"          width: auto; color: #ffffff;  }\r\n" + 
					"        .btn table td {\r\n" + 
					"          background-color: #742041;\r\n" + 
					"          border-radius: 5px;\r\n" + 
					"          text-align: center;  color: #ffffff; }\r\n" + 
					"        .btn a {\r\n" + 
					"          background-color: #de3277;\r\n" + 
					"          border-radius: 5px;\r\n" + 
					"          box-sizing: border-box;\r\n" + 
					"          cursor: pointer;\r\n" + 
					"          display: inline-block;\r\n" + 
					"          font-size: 17px;\r\n" + 
					"          font-weight: bold;\r\n" + 
					"          margin: 0;\r\n" + 
					"          padding: 12px 25px;\r\n" + 
					"          text-decoration: none;\r\n" + 
					"          text-transform: capitalize; }\r\n" + 
					"      .btn-primary table td {\r\n" + 
					"     }\r\n" + 
					"      .btn-primary a {\r\n" + 
					"     \r\n" + 
					"    \r\n" + 
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
					" font-size: 14px;\r\n" + 
					"  text-align: left;\r\n" + 
					"  padding: 8px;\r\n" + 
					"}\r\n" + 
					"       img\r\n" + 
					"       {\r\n" + 
					"        height: 150px;\r\n" + 
					"       }\r\n" + 
					"       .bg\r\n" + 
					"       {\r\n" + 
					"        background-color: #de3277;\r\n" + 
					"       }\r\n" + 
					"       button\r\n" + 
					"       {\r\n" + 
					"        background-color: #742041;color: #ffff;margin: 5px;\r\n" + 
					"       }\r\n" + 
					"       hr\r\n" + 
					"       {\r\n" + 
					"        color: #f6f6f6;\r\n" + 
					"       }\r\n" + 
					"       h2{\r\n" + 
					"        color: #ffffff;\r\n" + 
					"       }\r\n" + 
					"       h5{\r\n" + 
					"        color: #ffffff;\r\n" + 
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
					"        }\r\n" + 
					"        .btn-primary a:hover {\r\n" + 
					"        } }\r\n" + 
					"    </style>\r\n" + 
					"  </head>\r\n" + 
					"  <body  class=\"\">\r\n" + 
					"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" >\r\n" + 
					"      <tr>\r\n" + 
					"        <td>&nbsp;</td>\r\n" + 
					"        <td class=\"container\" >\r\n" + 
					"          <div class=\"content\">\r\n" + 
					"            <table class=\"main\" style=\"border-radius: 50px;\">\r\n" + 
					"\r\n" + 
					"              <!-- START MAIN CONTENT AREA -->\r\n" + 
					"              <tr>\r\n" + 
					"                <td class=\"wrapper\">\r\n" + 
					"                  <table  border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"bodymain\">\r\n" + 
					"                    <tr style=\"  background-image: linear-gradient(to right,#D11861,#680f33d1);\">\r\n" + 
					"                      <td>\r\n" + 
					"                        <h1><img  src=\""+imageLink+"\" alt=\"\"></h1>\r\n" + 
					"                        <hr>\r\n" + 
					"                        <h2> "+fullName+"</h2>\r\n" + 
					"                        <h5>Thanks you for purchasing "+plan_name+" from <a href=\"saathidaar.com\">Saathidaar.com</a></h5>\r\n" + 
					"                        <h5 > Here are your purchase details : </h5>\r\n" + 
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <td align=\"left\">\r\n" + 
					"                              </td>\r\n" + 
					"                            </tr>\r\n" + 
					"                          </tbody>\r\n" + 
					"                        </table>\r\n" + 
					"                        <table style=\"width: 100%; background-color: #ffffff;color: #000000;border-radius: 5px;\" class=\"table\">\r\n" + 
					"                          <thead>\r\n" + 
					"                            <tr >\r\n" + 
					"                              <th>Plan Name </th>\r\n" + 
					"                              <th  >: "+plan_name+"</th>\r\n" + 
					"                            </tr>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <th >Premium Membership</th>\r\n" + 
					"                              <th  >: "+plan_amount+"</th>\r\n" + 
					"                            </tr>\r\n" + 
					"                          </thead>\r\n" + 
					"                          \r\n" + 
					"                        </table>\r\n" + 
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"  style=\"width: 100%;border-radius: 5px;background-color: #ffffff;margin-top: 10px;\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <td> \r\n" + 
					"                                <p  style=\"color: #000000; border-bottom: none;\"> Your "+plan_name+" of Rs."+plan_amount+" is for the duration of "+plan_durations+" starting from today.\r\n" + 
					"                                  If you have any questions please visit our pages - <a href=\"http://103.174.102.195:8080/saathidaar/#/help\">HELP</a>, <a href=\"http://103.174.102.195:8080/saathidaar/#/privacy-policy\">Privacy Policy</a>, <a href=\"http://103.174.102.195:8080/saathidaar/#/term-condition\">T&C</a>. \r\n" + 
					"                                </p> \r\n" + 
					"                              </td>\r\n" + 
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
					"            \r\n" + 
					"          <!-- END CENTERED WHITE CONTAINER -->\r\n" + 
					"          </div>\r\n" + 
					"        </td>\r\n" + 
					"        <td>&nbsp;</td>\r\n" + 
					"      </tr>\r\n" + 
					"    </table>\r\n" + 
					"  </body>\r\n" + 
					"</html>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"";
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailBody;
	}


	
	public String RegistrationMail(String firstName, String lastName, String email, String phone,
			String confirmationToken, String imageLink) {
		String emailBody="";
		
		String mailMessage="<div style=\"margin-top: 15px;\"> you can change username and password when confirmation is done.  </div><br>\n <div style=\"margin-top: 15px;\">To confirm your e-mail address, please click the link below:\n"
		  		+ "http://103.174.102.195:8080/saathidaar_backend/api/users/confirm?token="+confirmationToken +"<div>";
		
		try {
			emailBody="<!doctype html>\r\n" + 
					"<html>\r\n" + 
					"  <head>\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width\" />\r\n" + 
					"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
					"    <title>Simple Transactional Email</title>\r\n" + 
					"    <style>\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          GLOBAL RESETS\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      *\r\n" + 
					"      {\r\n" + 
					"            font-family: ABeeZee;font-size: 17px;\r\n" + 
					"      }\r\n" + 
					"      img {\r\n" + 
					"        border: none;\r\n" + 
					"        -ms-interpolation-mode: bicubic;\r\n" + 
					"        max-width: 100%; }\r\n" + 
					"      body {\r\n" + 
					"        background-color: rgba(128, 128, 128, 0.377);\r\n" + 
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
					"        width: 100%;\r\n" + 
					"      }\r\n" + 
					"      .bodymain\r\n" + 
					"      {\r\n" + 
					"        -webkit-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);-moz-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);\r\n" + 
					"        box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);border-radius:10px;\r\n" + 
					"      }\r\n" + 
					"\r\n" + 
					"        table td {\r\n" + 
					"          font-family: sans-serif;\r\n" + 
					"          font-size: 17px;\r\n" + 
					"          vertical-align: top;  color: #ffffff; }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          BODY & CONTAINER\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      .body {\r\n" + 
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
					"        background: none;\r\n" + 
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
					"          padding-bottom: 15px;  color: #ffffff; }\r\n" + 
					"        .btn table {\r\n" + 
					"          width: auto; color: #ffffff;  }\r\n" + 
					"        .btn table td {\r\n" + 
					"          background-color: #742041;\r\n" + 
					"          border-radius: 5px;\r\n" + 
					"          text-align: center;  color: #ffffff; }\r\n" + 
					"        .btn a {\r\n" + 
					"          background-color: #de3277;\r\n" + 
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
					" font-size: 14px;\r\n" + 
					"  text-align: left;\r\n" + 
					"  padding: 8px;\r\n" + 
					"}\r\n" + 
					"       img\r\n" + 
					"       {\r\n" + 
					"        height: 150px;\r\n" + 
					"       }\r\n" + 
					"       .bg\r\n" + 
					"       {\r\n" + 
					"        background-color: #de3277;\r\n" + 
					"       }\r\n" + 
					"       button\r\n" + 
					"       {\r\n" + 
					"        background-color: #742041;color: #ffff;margin: 5px;\r\n" + 
					"       }\r\n" + 
					"       hr\r\n" + 
					"       {\r\n" + 
					"        color: #f6f6f6;\r\n" + 
					"       }\r\n" + 
					"       h2{\r\n" + 
					"        color: #ffffff;\r\n" + 
					"       }\r\n" + 
					"       h5{\r\n" + 
					"        color: #ffffff;\r\n" + 
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
					"  <body  class=\"\">\r\n" + 
					"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" >\r\n" + 
					"      <tr>\r\n" + 
					"        <td>&nbsp;</td>\r\n" + 
					"        <td class=\"container\" >\r\n" + 
					"          <div class=\"content\">\r\n" + 
					"            <table class=\"main\">\r\n" + 
					"\r\n" + 
					"              <!-- START MAIN CONTENT AREA -->\r\n" + 
					"              <tr>\r\n" + 
					"                <td class=\"wrapper\">\r\n" + 
					"                  <table style=\"background-image: linear-gradient(to right,#D11861,#680f33d1);\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"bodymain\">\r\n" + 
					"                    <tr>\r\n" + 
					"                      <td>\r\n" + 
					"                        <h1><img src=\""+imageLink+"\" alt=\"\"></h1>\r\n" + 
					"                        <hr>\r\n" + 
					"   					 <h4 style=\"text-align: center;\">You have successfully completed user registration on <strong>saathidaar.com</strong></h4>\r\n" + 
					"                        <h2>Hi "+firstName+"&nbsp;"+lastName+"</h2>\r\n" + 
					"                        <h5></h5>\r\n" + 
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <td align=\"left\">\r\n" + 
					"                              </td>\r\n" + 
					"                            </tr>\r\n" + 
					"                          </tbody>\r\n" + 
					"                        </table>\r\n" + 
					"                        <!-- <p>If you received this email by mistake, simply delete it. You won't be subscribed if you don't click the confirmation link above.</p> -->\r\n" + 
					"                        <table style=\"width: 100%; background-color: #ffffff;color: #000000;border-radius: 5px;\" class=\"table\">\r\n" + 
					"                          <thead>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <th  scope=\"col\">User Email</th>\r\n" + 
					"                              <th  scope=\"col\">:&nbsp; "+email+" </th>\r\n" + 
					"                            </tr>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <th  scope=\"col\"> User password </th>\r\n" + 
					"                              <th  scope=\"col\">:&nbsp; "+phone+"</th>\r\n" + 
					"                            </tr>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <th scope=\"col\">First Name </th>\r\n" + 
					"                              <th scope=\"col\">:&nbsp; "+firstName+"</th>\r\n" + 
					"                      \r\n" + 
					"                            </tr>\r\n" + 
					"                            <tr >\r\n" + 
					"                              <th  scope=\"col\">Last Name </th>\r\n" + 
					"                              <th  scope=\"col\">:&nbsp; "+lastName+"</th>\r\n" + 
					"                            </tr>\r\n" + 
					"                          </thead>\r\n" + 
					"                          \r\n" + 
					"                        </table>\r\n" + 
					" <div class=\"details\">"+
					mailMessage+ "</div>\r\n" +
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;border-radius: 5px;background-color: #ffffff;margin-top: 10px;\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
//					"                              <td> <a href=\"http://htmlemail.io\" target=\"_blank\">View Full Profile</a> </td>\r\n" + 
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
					"</html>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailBody;
	}


	public String ForgotPasswordMail(String firstName, String lastName, String username, String phone,
			String newPassword, String imageLink) {
	String emailBody="";
		try {
			emailBody="<!doctype html>\r\n" + 
					"<html>\r\n" + 
					"  <head>\r\n" + 
					"    <meta name=\"viewport\" content=\"width=device-width\" />\r\n" + 
					"    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\r\n" + 
					"    <title>Simple Transactional Email</title>\r\n" + 
					"    <style>\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          GLOBAL RESETS\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      *\r\n" + 
					"      {\r\n" + 
					"            font-family: ABeeZee;font-size: 17px;\r\n" + 
					"      }\r\n" + 
					"      img {\r\n" + 
					"        border: none;\r\n" + 
					"        -ms-interpolation-mode: bicubic;\r\n" + 
					"        max-width: 100%; }\r\n" + 
					"      body {\r\n" + 
					"        background-color: rgba(128, 128, 128, 0.377);\r\n" + 
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
					"        width: 100%;\r\n" + 
					"      }\r\n" + 
					"      .bodymain\r\n" + 
					"      {\r\n" + 
					"        -webkit-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);-moz-box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);\r\n" + 
					"        box-shadow: 0px 6px 15px 0px rgba(0,0,0,0.65);border-radius:10px;\r\n" + 
					"      }\r\n" + 
					"\r\n" + 
					"        table td {\r\n" + 
					"          font-family: sans-serif;\r\n" + 
					"          font-size: 17px;\r\n" + 
					"          vertical-align: top;  color: #ffffff; }\r\n" + 
					"      /* -------------------------------------\r\n" + 
					"          BODY & CONTAINER\r\n" + 
					"      ------------------------------------- */\r\n" + 
					"      .body {\r\n" + 
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
					"        background: none;\r\n" + 
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
					"          padding-bottom: 15px;  color: #ffffff; }\r\n" + 
					"        .btn table {\r\n" + 
					"          width: auto; color: #ffffff;  }\r\n" + 
					"        .btn table td {\r\n" + 
					"          background-color: #742041;\r\n" + 
					"          border-radius: 5px;\r\n" + 
					"          text-align: center;  color: #ffffff; }\r\n" + 
					"        .btn a {\r\n" + 
					"          background-color: #de3277;\r\n" + 
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
					" font-size: 14px;\r\n" + 
					"  text-align: left;\r\n" + 
					"  padding: 8px;\r\n" + 
					"}\r\n" + 
					"       img\r\n" + 
					"       {\r\n" + 
					"        height: 150px;\r\n" + 
					"       }\r\n" + 
					"       .bg\r\n" + 
					"       {\r\n" + 
					"        background-color: #de3277;\r\n" + 
					"       }\r\n" + 
					"       button\r\n" + 
					"       {\r\n" + 
					"        background-color: #742041;color: #ffff;margin: 5px;\r\n" + 
					"       }\r\n" + 
					"       hr\r\n" + 
					"       {\r\n" + 
					"        color: #f6f6f6;\r\n" + 
					"       }\r\n" + 
					"       h2{\r\n" + 
					"        color: #ffffff;\r\n" + 
					"       }\r\n" + 
					"       h5{\r\n" + 
					"        color: #ffffff;\r\n" + 
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
					"  <body  class=\"\">\r\n" + 
					"    <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"body\" >\r\n" + 
					"      <tr>\r\n" + 
					"        <td>&nbsp;</td>\r\n" + 
					"        <td class=\"container\" >\r\n" + 
					"          <div class=\"content\">\r\n" + 
					"            <table class=\"main\">\r\n" + 
					"\r\n" + 
					"              <!-- START MAIN CONTENT AREA -->\r\n" + 
					"              <tr>\r\n" + 
					"                <td class=\"wrapper\">\r\n" + 
					"                  <table style=\"background-image: linear-gradient(to right,#D11861,#680f33d1);\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"bodymain\">\r\n" + 
					"                    <tr>\r\n" + 
					"                      <td>\r\n" + 
					"                        <h1><img src=\""+imageLink+"\" alt=\"\"></h1>\r\n" + 
					"                        <hr>\r\n" + 				
					"                        <h2>Hi "+firstName+"&nbsp;"+lastName+"</h2>\r\n" + 
					"                        <h5>Your new password is "+newPassword+" on <a href=\"\">Saathidaar.com</a></h5>\r\n" + 
					"                        <h5></h5>\r\n" + 
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"btn btn-primary\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <td align=\"left\">\r\n" + 
					"                              </td>\r\n" + 
					"                            </tr>\r\n" + 
					"                          </tbody>\r\n" + 
					"                        </table>\r\n" + 
					"                        <!-- <p>If you received this email by mistake, simply delete it. You won't be subscribed if you don't click the confirmation link above.</p> -->\r\n" + 
					"                        <table style=\"width: 100%; background-color: #ffffff;color: #000000;border-radius: 5px;\" class=\"table\">\r\n" + 
					"                          <thead>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <th  scope=\"col\">User Email</th>\r\n" + 
					"                              <th  scope=\"col\">:&nbsp; "+username+" </th>\r\n" + 
					"                            </tr>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <th  scope=\"col\"> User password </th>\r\n" + 
					"                              <th  scope=\"col\">:&nbsp; "+newPassword+"</th>\r\n" + 
					"                            </tr>\r\n" + 
					"                            <tr>\r\n" + 
					"                              <th scope=\"col\">First Name </th>\r\n" + 
					"                              <th scope=\"col\">:&nbsp; "+firstName+"</th>\r\n" + 
					"                      \r\n" + 
					"                            </tr>\r\n" + 
					"                            <tr >\r\n" + 
					"                              <th  scope=\"col\">Last Name </th>\r\n" + 
					"                              <th  scope=\"col\">:&nbsp; "+lastName+"</th>\r\n" + 
					"                            </tr>\r\n" + 
					"                          </thead>\r\n" + 
					"                          \r\n" + 
					"                        </table>\r\n" + 
					"                        <table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"width: 100%;border-radius: 5px;background-color: #ffffff;margin-top: 10px;\">\r\n" + 
					"                          <tbody>\r\n" + 
					"                            <tr>\r\n" + 
//					"                              <td> <a href=\"http://htmlemail.io\" target=\"_blank\">View Full Profile</a> </td>\r\n" + 
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
					"</html>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return emailBody;
	}


	public String getForgotPasswordEmailBody(String firstName, String lastName, String email, String phone,
			String generatePassword, String imageLink) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}

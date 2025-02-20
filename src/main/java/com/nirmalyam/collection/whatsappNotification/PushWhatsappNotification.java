package com.nirmalyam.collection.whatsappNotification;

import org.springframework.stereotype.Component;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
@Component
public class PushWhatsappNotification {

	public String sendWhatsappNotification(String mobileNumber) {
		String status = "FAILURE";
		try {
			// Find your Account Sid and Token at twilio.com/console
			final String ACCOUNT_SID = "AC87b4831a013b824dd3fea46d4edfced5";
			final String AUTH_TOKEN = "3d7f5083ba04baf3403218eafdd0ed39";
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
			Message message = Message.creator(new com.twilio.type.PhoneNumber("whatsapp:+91" + mobileNumber),
					new com.twilio.type.PhoneNumber("whatsapp:+14155238886"),
					"Thanks for Choosing Nirmalaym.we will reachout your door step as expected timeline\n-Team Nirmalayam collection")
					.create();

			System.out.println(message.getBody());
			status = "SUCCESS";
		} catch (Exception e) {
			status = "FAILURE";
		}
		return status;
	}
}

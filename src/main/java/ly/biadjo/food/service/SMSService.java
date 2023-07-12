package ly.biadjo.food.service;

import ly.biadjo.food.web.rest.errors.BadRequestAlertException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

@Service
@Transactional
public class SMSService {

    public SMSService() {
    }

    public void sendSMS(String phone, String text) {
        try {
            System.out.println(phone + " " + text);

            String uri = "";
            if (phone.startsWith("+21891")) {
                uri = "http://172.50.5.1:8911/cgi-bin/sendsms?username=etmdr&password=etrdm&from=eTravel&to=" + phone + "&text=" + text;
            } else if (phone.startsWith("+21892") || phone.startsWith("+21894")) {
                uri = "http://172.50.5.1:8911/cgi-bin/sendsms?username=etlby&password=etybl&from=17878&to=" + phone + "&text=" + text;
            }
            //            if (phone.startsWith("+21891")) {
            //                uri = "http://156.38.58.240:8911/cgi-bin/sendsms?username=etmdr&password=etrdm&from=eTravel&to=" + phone + "&text=" + text;
            //            } else if (phone.startsWith("+21892") || phone.startsWith("+21894")) {
            //                uri = "http://156.38.58.240:8911/cgi-bin/sendsms?username=etlby&password=etybl&from=17878&to=" + phone + "&text=" + text;
            //            }
            else {
                throw new BadRequestAlertException("SMS not supported for this number!", "", "SMS_NOT_SUPPORTED");
            }
            System.out.println(uri);
            RestTemplate restTemplate = new RestTemplate();
            String response = restTemplate.getForObject(uri, String.class);
        } catch (Exception e) {
            throw new BadRequestAlertException("SMS Fail!", "", "SMS_FAIL");
        }
    }
}

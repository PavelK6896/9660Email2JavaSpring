package app.web.pavelk.email2.comtroller;


import app.web.pavelk.email2.entities.Message;
import app.web.pavelk.email2.service.MailSender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class MainController {

    private int count = 0;
    private MailSender mailSender;
    @Value("${mail.mename}")
    private String meName;

    @Autowired
    public MainController(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    @GetMapping
    public String greeting() {
        System.out.println("greeting");
        return "greeting.html";
    }

    @PostMapping
    @ResponseBody
    public String send(
            @RequestBody String body
    ) {

        System.out.println("post ");
        if (count > 200) {
            return "not count";
        }

        try {
            ObjectMapper mapper = new ObjectMapper();
            Message message = mapper.readValue(body, Message.class);
            System.out.println("message " + message);
            count++;
            mailSender.send1(meName, message.getSubject(), message.getMessage());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return "ok";
    }

}

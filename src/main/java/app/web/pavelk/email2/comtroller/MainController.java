package app.web.pavelk.email2.comtroller;


import app.web.pavelk.email2.service.MailSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

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
    public String  send(@RequestParam Map<String,String> map) {

        if (count > 500){
            return "redirect:/";
        }

        count++;
        mailSender.send1(meName, map.get("subject"), map.get("message") );
        System.out.println("send me yes = " + map + "\ncount = " + count);

        return "redirect:/";

    }

}

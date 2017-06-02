package kr.yerina.wmp.autonomousRegistration.controller;


import kr.yerina.wmp.autonomousRegistration.domain.Work;
import kr.yerina.wmp.autonomousRegistration.properties.MyConfiguration;
import kr.yerina.wmp.autonomousRegistration.repository.HelloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by philip on 2017-05-31.
 */
@Slf4j
@Controller
public class WmpHomeController {

    @Autowired
    private MyConfiguration myConfiguration;

    @Autowired
    private HelloRepository helloRepository;

    @GetMapping(value = "/")
    String home(Model model){
        model.addAttribute("greeting","인사말");
        log.debug("[config load test text ][{}]",myConfiguration);
        Work work = new Work();
        work.setCode("12345");
        work.setInputValue(8L);
        work.setWorkItem("내용");
        work.setSecondCode("12345");
        helloRepository.save(work);
        log.debug("[length][{}]",helloRepository.findAll().size());
        log.debug(String.valueOf(helloRepository.findAll()));
        return "home";
    }

    @GetMapping(value = "/hello")
    String hellow(Model model){
        model.addAttribute("greeting","hello world");
        log.debug("[config load test text ][{}]",myConfiguration);
        return "home";
    }

}

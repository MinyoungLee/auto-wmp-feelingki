package kr.yerina.wmp.autonomousRegistration.controller;


import kr.yerina.wmp.autonomousRegistration.domain.Work;
import kr.yerina.wmp.autonomousRegistration.properties.MyConfiguration;
import kr.yerina.wmp.autonomousRegistration.repository.WorksRepository;
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
    private WorksRepository worksRepository;

    @GetMapping(value = "/list")
    String home(Model model){

        model.addAttribute("workList",worksRepository.findAll());

        Work work = new Work();
        work.setCode("12345");
        work.setInputValue(8L);
        work.setWorkItem("내용");
        work.setSecondCode("12345");
        worksRepository.save(work);
        log.debug("[length][{}]", worksRepository.findAll().size());
        log.debug(String.valueOf(worksRepository.findAll()));
        return "home";
    }


}

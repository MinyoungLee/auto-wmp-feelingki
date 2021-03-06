package kr.yerina.wmp.autonomousRegistration.controller;


import kr.yerina.wmp.autonomousRegistration.domain.Work;
import kr.yerina.wmp.autonomousRegistration.properties.MyConfiguration;
import kr.yerina.wmp.autonomousRegistration.repository.WorksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;


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

    @GetMapping(value = "/")
    String home(Model model){
        model.addAttribute("workList",worksRepository.findAll());
        log.debug("[length][{}]", worksRepository.findAll().size());
        log.debug(String.valueOf(worksRepository.findAll()));
        return "home";
    }


    @PostMapping("/addWork")
    String addWork(@ModelAttribute Work work){
        log.debug("[add work requestBody][{}]",work);
        if(!StringUtils.isEmpty(work)){
            if(!StringUtils.isEmpty(work.getName()) && !StringUtils.isEmpty(work.getPassword())){
                worksRepository.save(work);
            }
        }
        return "redirect:/";
    }

    @GetMapping("/deleteWork")
    String removeWork(@RequestParam(value = "id") int delectId, HttpServletRequest request){
        log.info("Delete Remote Address IP : "+request.getRemoteAddr());
        log.debug("[delectId][{}]", delectId);
        worksRepository.delete(delectId);
        return "redirect:/";
    }

}

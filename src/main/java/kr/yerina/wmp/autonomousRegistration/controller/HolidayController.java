package kr.yerina.wmp.autonomousRegistration.controller;


import kr.yerina.wmp.autonomousRegistration.domain.Holiday;
import kr.yerina.wmp.autonomousRegistration.domain.Work;
import kr.yerina.wmp.autonomousRegistration.repository.HolidayRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
public class HolidayController {

    private HolidayRepository holidayRepository;

    @Autowired
    public HolidayController(HolidayRepository holidayRepository) {
        this.holidayRepository = holidayRepository;
    }

    @GetMapping(value = "/holiday")
    String holiday(Model model){
        model.addAttribute("holidayList",holidayRepository.findAll());
        log.debug("[holidayRepository length][{}]", holidayRepository.findAll().size());
        log.debug(String.valueOf(holidayRepository.findAll()));
        return "holiday";
    }

    @PostMapping("/addHoliday")
    String addWork(@ModelAttribute Holiday holiday){
        log.debug("[add holiday requestBody][{}]", holiday);
        if(!StringUtils.isEmpty(holiday)){
            holidayRepository.save(holiday);
        }
        return "redirect:/holiday";
    }

    @GetMapping("/deleteHoliday")
    String removeHoliday(@RequestParam(value = "id") int delectId){
        log.debug("[deleteId][{}]", delectId);
        holidayRepository.delete(delectId);
        return "redirect:/holiday";
    }

}

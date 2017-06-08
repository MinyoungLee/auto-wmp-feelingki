package kr.yerina.wmp.autonomousRegistration.scheduler;


import kr.yerina.wmp.autonomousRegistration.domain.Holiday;
import kr.yerina.wmp.autonomousRegistration.domain.Work;
import kr.yerina.wmp.autonomousRegistration.repository.HolidayRepository;
import kr.yerina.wmp.autonomousRegistration.repository.WorksRepository;
import kr.yerina.wmp.autonomousRegistration.utils.DateUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by philip on 2017-05-31.
 */
@Slf4j
@Component
public class WmpScheduler {

    @Autowired
    private WorksRepository worksRepository;

    @Autowired
    private HolidayRepository holidayRepository;

    //월~금 오후 5시 10분
    //초,분,시,일,월,요일, (년)
    @Scheduled(cron = "0 10 17 ? * MON-FRI")
    public void workProc(){
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);

        HttpHeaders requestHeaders = new HttpHeaders();

        String url = "http://wmp.feelingk.com/login";

        String targetDate = DateUtility.getToday(DateUtility.SDF_YYYYMMDD_DASH);
        String addWorkUrl = "http://wmp.feelingk.com/works/add/Love5757?targetDate="+targetDate;

        List<Holiday> holidayList = holidayRepository.findAll();
        log.info("[holidayList][{}]", holidayList);

        //오늘 날짜가 holiday에 없어야함.
        if(!holidayList.contains(targetDate)){
            if(!StringUtils.isEmpty(holidayList) && holidayList.size() > 0){
                Map<String, String> user = new HashMap<>();
                List<Work> workList = worksRepository.findAll();
                log.info("[scheduled][{}]",workList);
                if(!StringUtils.isEmpty(workList) && workList.size() > 0){
                for (Work work : workList) {
                    user.put("name", work.getName());
                    user.put("password", work.getPassword());
                    HttpEntity param = new HttpEntity(user, requestHeaders);
                    String response = restTemplate.postForObject(url, param, String.class);

                    HttpEntity addWorkParam = new HttpEntity(work, requestHeaders);
                    String addWorkResponse = restTemplate.postForObject(addWorkUrl, addWorkParam, String.class);
                    }
                }
            }
        }
    }

}

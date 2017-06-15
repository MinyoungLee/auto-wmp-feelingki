package kr.yerina.wmp.autonomousRegistration.scheduler;


import kr.yerina.wmp.autonomousRegistration.domain.Holiday;
import kr.yerina.wmp.autonomousRegistration.domain.Work;
import kr.yerina.wmp.autonomousRegistration.repository.HolidayRepository;
import kr.yerina.wmp.autonomousRegistration.repository.WorksRepository;
import kr.yerina.wmp.autonomousRegistration.utils.DateUtility;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.text.SimpleDateFormat;
import java.util.*;

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
    @Scheduled(cron = "0 30 17 ? * MON-FRI" ,zone = "Asia/Seoul")
    public void workProc(){

        String url = "http://wmp.feelingk.com/login";

        SimpleDateFormat sdf = DateUtility.SDF_YYYYMMDD_DASH;
        TimeZone zone = TimeZone.getTimeZone("Asia/Seoul");
        sdf.setTimeZone(zone);
        Date date = new Date();
        String targetDate = sdf.format(date);

        List<Holiday> holidayList = holidayRepository.findAll();
        log.info("[holidayList][{}]", holidayList);

        //오늘 날짜가 holiday에 없어야함.
        if(!holidayList.contains(targetDate)){
            Map<String, String> user = new HashMap<>();
            List<Work> workList = worksRepository.findAll();
            log.info("[scheduled][{}]",workList);
            if(!StringUtils.isEmpty(workList) && workList.size() > 0){
                for (Work work : workList) {
                    log.info("work " + work);
                    HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
                    RestTemplate restTemplate = new RestTemplate();
                    restTemplate.setRequestFactory(factory);
                    HttpHeaders requestHeaders = new HttpHeaders();

                    user.put("name", work.getName());
                    user.put("password", work.getPassword());
                    HttpEntity param = new HttpEntity(user, requestHeaders);
                    String response = restTemplate.postForObject(url, param, String.class);
                    log.info("response : "+response);

                    Map<String, String> workParam = new HashMap<>();
                    workParam.put("code", work.getCode());
                    workParam.put("secondCode",work.getSecondCode());
                    workParam.put("workItem", work.getWorkItem());
                    workParam.put("inputValue", String.valueOf(work.getInputValue()));
                    HttpEntity addWorkParam = new HttpEntity(workParam, requestHeaders);
                    log.info("addWorkParam "+addWorkParam);
                    String addWorkUrl = "http://wmp.feelingk.com/works/add/"+work.getName()+"?targetDate="+targetDate;
                    log.info("addWorkUrl "+ addWorkUrl);
                    String addWorkResponse = restTemplate.postForObject(addWorkUrl, addWorkParam, String.class);
                    log.info("response : "+addWorkResponse);
                }
            }
        }
    }
}

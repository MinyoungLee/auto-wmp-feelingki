package kr.yerina.wmp.autonomousRegistration.service;


import kr.yerina.wmp.autonomousRegistration.domain.Work;
import kr.yerina.wmp.autonomousRegistration.repository.WorksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by philip on 2017-05-31.
 */
@Service
@Slf4j
public class WmpService {

    @Autowired
    private WorksRepository worksRepository;

//    @Scheduled(cron = "30 5 * * 1-5")
    @Scheduled(fixedDelay = 1000)
    public void workProc(){
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);

        HttpHeaders requestHeaders = new HttpHeaders();

        String url = "http://wmp.feelingk.com/login";
        String addWorkUrl = "http://wmp.feelingk.com/works/add/Love5757?targetDate=2017-05-31";

        Map<String, String> user = new HashMap<>();
        List<Work> workList = worksRepository.findAll();
        log.debug("[{}]",workList);

        /*for (Work work : workList) {
            user.put("name", work.getName());
            user.put("password", work.getPassword());
            HttpEntity param = new HttpEntity(user, requestHeaders);
            String response = restTemplate.postForObject(url, param, String.class);
            log.debug("[로그인][{}]",response);

            HttpEntity addWorkParam = new HttpEntity(work, requestHeaders);
            String addWorkResponse = restTemplate.postForObject(addWorkUrl, addWorkParam, String.class);
            log.debug("[업무 등록 응답][{}]",addWorkResponse);
        }*/


    }

}

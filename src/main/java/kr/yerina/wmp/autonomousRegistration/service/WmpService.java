package kr.yerina.wmp.autonomousRegistration.service;


import kr.yerina.wmp.autonomousRegistration.domain.User;
import kr.yerina.wmp.autonomousRegistration.domain.Work;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * Created by philip on 2017-05-31.
 */
@Service
@Slf4j
public class WmpService {

    @Scheduled(cron = "30 5 * * 1-5")
    public void workProc(){
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setRequestFactory(factory);

        HttpHeaders requestHeaders = new HttpHeaders();

        String url = "http://wmp.feelingk.com/login";
        String addWorkUrl = "http://wmp.feelingk.com/works/add/Love5757?targetDate=2017-05-31";

        //db 조회 해서 가져 오기

        //로그인
        User user = new User();
        user.setName("love5757");
        user.setPassword("wnddud00");
        HttpEntity param = new HttpEntity(user, requestHeaders);
        String response = restTemplate.postForObject(url, param, String.class);
        log.debug("[로그인][{}]",response);

        //등록
        Work addWork = new Work();
        addWork.setCode("K17D046");
        addWork.setInputValue(8L);
        addWork.setWorkItem("API 테스트");
        addWork.setSecondCode("K17D046");
        HttpEntity addWorkParam = new HttpEntity(addWork, requestHeaders);
        String addWorkResponse = restTemplate.postForObject(addWorkUrl, addWorkParam, String.class);
        log.debug("[업무 등록 응답][{}]",addWorkResponse);

    }

}

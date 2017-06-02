package kr.yerina.wmp.autonomousRegistration.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by philip on 2017-05-31.
 */
@Entity
public class Work {
    @Id
    @GeneratedValue
    @Getter
    int id;
    @Getter @Setter
    String code;        //프로젝트 명
    @Getter @Setter
    Long inputValue;    //작업시간
    @Getter @Setter
    String workItem;    //작업내용
    @Getter @Setter
    String secondCode;  //수행프로젝트명
}

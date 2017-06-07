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
    private int id;
    @Getter @Setter
    private String code;        //프로젝트 명
    @Getter @Setter
    private Long inputValue;    //작업시간
    @Getter @Setter
    private String workItem;    //작업내용
    @Getter @Setter
    private String secondCode;  //수행프로젝트명

    @Getter @Setter
    private String name;        //wmp id
    @Getter @Setter
    private String password;    //wmp password
}

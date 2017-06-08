package kr.yerina.wmp.autonomousRegistration.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@ToString
public class Holiday {
    @Id
    @GeneratedValue
    @Getter @Setter
    Long id;
    @Getter @Setter
    String holidayDate;
}

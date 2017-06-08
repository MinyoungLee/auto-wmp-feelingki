package kr.yerina.wmp.autonomousRegistration.repository;

import kr.yerina.wmp.autonomousRegistration.domain.Holiday;
import kr.yerina.wmp.autonomousRegistration.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HolidayRepository extends JpaRepository<Holiday, Integer>{
}

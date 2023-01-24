package sg.nus.iss.leaveapp.leave.service;

import java.util.List;
import java.util.Optional;

import sg.nus.iss.leaveapp.leave.model.PublicHoliday;

public interface PublicHolidayService {
    PublicHoliday savePublicHoliday(PublicHoliday publicHoliday);
    List<PublicHoliday> getPublicHolList();
    Optional<PublicHoliday> getPublicHolById(long id);
    boolean updatePublicHoliday(PublicHoliday publicHoliday, PublicHoliday newPublicHoliday);
    boolean deletePublicHoliday(PublicHoliday publicHol);
    boolean checkIfOverlap(PublicHoliday publicHol);
    
}

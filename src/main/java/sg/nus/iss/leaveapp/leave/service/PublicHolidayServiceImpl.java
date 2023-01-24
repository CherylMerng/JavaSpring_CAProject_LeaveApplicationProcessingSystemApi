package sg.nus.iss.leaveapp.leave.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import sg.nus.iss.leaveapp.leave.model.PublicHoliday;
import sg.nus.iss.leaveapp.leave.repository.PublicHolidayRepository;

@Service
public class PublicHolidayServiceImpl implements PublicHolidayService {
    @Autowired
    private PublicHolidayRepository publicHolidayRepo;
    @Override
    public PublicHoliday savePublicHoliday(PublicHoliday publicHoliday){
        return publicHolidayRepo.save(publicHoliday);

    }
    @Override
    public boolean checkIfOverlap(PublicHoliday publicHol){
        List<PublicHoliday> existingPublicHolList = getPublicHolList();
        for (PublicHoliday hol: existingPublicHolList){
            if (publicHol.getDateOfPublicHol().isEqual(hol.getDateOfPublicHol())){
                return true;
            }
        }
        return false;
    }
    @Override
    public List<PublicHoliday> getPublicHolList(){
        return publicHolidayRepo.findAll();
    }
    @Override
    public Optional<PublicHoliday> getPublicHolById(long id){
        return publicHolidayRepo.findById(id);
    }
    @Override
    public boolean updatePublicHoliday(PublicHoliday publicHoliday, PublicHoliday newPublicHoliday){
        publicHoliday.setNameOfPublicHol(newPublicHoliday.getNameOfPublicHol());
        publicHolidayRepo.save(publicHoliday);
        return true;
    }
    @Override
    public boolean deletePublicHoliday(PublicHoliday publicHol){
        publicHolidayRepo.delete(publicHol);
        return true;
    }
    
}

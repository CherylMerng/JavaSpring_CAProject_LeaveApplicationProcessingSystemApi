package sg.nus.iss.leaveapp.leave.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sg.nus.iss.leaveapp.leave.model.PublicHoliday;

@Component
public class PublicHolidayValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz){
        return PublicHoliday.class.isAssignableFrom(clazz);
    }
    @Override
    public void validate(Object obj, Errors errors){
        PublicHoliday publicHol = (PublicHoliday) obj;
  
    }

}

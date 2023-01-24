package sg.nus.iss.leaveapp.leave;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import sg.nus.iss.leaveapp.leave.model.PublicHoliday;
import sg.nus.iss.leaveapp.leave.service.PublicHolidayService;

@SpringBootApplication
public class LeaveApplicationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(LeaveApplicationApiApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRun(PublicHolidayService publicHolService){
		return args -> {
			DateTimeFormatter df1 = DateTimeFormatter.ofPattern("dd/MM/yy");
			Map<String, String> dateofPublicHol = new HashMap<>(){{
				put("01/01/23", "New Year’s Day");
				put("02/01/23", "New Year’s Day In Lieu");
				put("22/01/23", "Chinese New Year");
				put("23/01/23", "Chinese New Year");
				put("24/01/23", "Chinese New Year In Lieu");
				put("07/04/23", "Good Friday");
				put("22/04/23", "Hari Raya Puasa");
				put("01/05/23", "Labour Day");
				put("03/06/23", "Vesak Day");
				put("29/06/23", "Hari Raya Haji");
				put("09/08/23", "National Day");
				put("12/11/23", "Deepavali");
				put("13/11/23", "Deepavali In Lieu");
				put("25/12/23", "Christmas");}};
				List<PublicHoliday> listOfPublicHol = new ArrayList<PublicHoliday>();
				for (String date: dateofPublicHol.keySet()){
					LocalDate localDate = LocalDate.parse(date,df1);
					String description = dateofPublicHol.get(date);
					listOfPublicHol.add(new PublicHoliday(localDate, description));
				}
				for (PublicHoliday publichol: listOfPublicHol ){
					publicHolService.savePublicHoliday(publichol);

				}};

}}

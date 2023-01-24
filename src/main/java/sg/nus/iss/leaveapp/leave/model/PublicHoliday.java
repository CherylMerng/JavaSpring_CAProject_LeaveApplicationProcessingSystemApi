package sg.nus.iss.leaveapp.leave.model;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Data
@NoArgsConstructor
@Entity
public class PublicHoliday {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateOfPublicHol;
    @NotNull
    private String nameOfPublicHol;

    public PublicHoliday(LocalDate dateOfPublicHol, String nameOfPublicHol){
        this.dateOfPublicHol = dateOfPublicHol;
        this.nameOfPublicHol = nameOfPublicHol;
    }

    @Override
    public String toString(){
        String description = "Date of Public Hol:" + dateOfPublicHol + "Name of Public Hol:" + nameOfPublicHol;
        return description;
    }

}

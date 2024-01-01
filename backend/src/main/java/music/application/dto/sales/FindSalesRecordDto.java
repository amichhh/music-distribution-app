package music.application.dto.sales;

import java.time.LocalDate;

import lombok.Data;

@Data
public class FindSalesRecordDto {
    private LocalDate day;
}

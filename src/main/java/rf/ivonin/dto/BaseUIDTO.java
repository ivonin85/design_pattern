package rf.ivonin.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseUIDTO {
    private String route;
    private String pageTitle;
}

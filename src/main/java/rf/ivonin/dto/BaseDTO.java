package rf.ivonin.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseDTO {
    private String route;
    private String JSONSchema;
    private int statusCode;
}

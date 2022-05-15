package rf.ivonin.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class BaseAPIDTO {
    private String route;
    private String JSONSchema;
    private int statusCode;
}

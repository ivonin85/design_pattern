package rf.ivonin.dto.registerDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.experimental.Accessors;

@lombok.Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisterDTO {
    private String password;
    private String email;
}

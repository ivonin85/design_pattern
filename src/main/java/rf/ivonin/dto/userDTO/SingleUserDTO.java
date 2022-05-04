package rf.ivonin.dto.userDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.experimental.Accessors;
import rf.ivonin.dto.generalDTO.SupportDTO;
import rf.ivonin.dto.generalDTO.UserDataDTO;

@lombok.Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleUserDTO {
    private UserDataDTO data;
    private SupportDTO support;
}

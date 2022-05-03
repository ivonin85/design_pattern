package rf.ivonin.data.dto.userDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.experimental.Accessors;
import rf.ivonin.data.dto.generalDTO.SupportDTO;
import rf.ivonin.data.dto.generalDTO.UserDataDTO;

@lombok.Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleUserDTO {
    private UserDataDTO data;
    private SupportDTO support;
}

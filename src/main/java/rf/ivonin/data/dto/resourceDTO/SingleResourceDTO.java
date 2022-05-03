package rf.ivonin.data.dto.resourceDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.experimental.Accessors;
import rf.ivonin.data.dto.generalDTO.ResourceDataDTO;
import rf.ivonin.data.dto.generalDTO.SupportDTO;

@lombok.Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class SingleResourceDTO {
	private ResourceDataDTO data;
	private SupportDTO support;
}

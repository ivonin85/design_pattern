package rf.ivonin.data.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import rf.ivonin.data.dto.createDTO.CreateRequestDTO;
import rf.ivonin.data.dto.createDTO.CreateResponseDTO;
import rf.ivonin.data.dto.registerDTO.RegisterDTO;
import rf.ivonin.data.dto.registerDTO.RegisterResponseDTO;
import rf.ivonin.data.dto.resourceDTO.SingleResourceDTO;
import rf.ivonin.data.dto.userDTO.SingleUserDTO;
import rf.ivonin.data.dto.userListDTO.UserListDTO;

@Data
@Accessors(chain = true)
public class HubDTO {

    private BaseDTO baseDTO;

    private RegisterResponseDTO registerResponseDTO;
    private RegisterDTO registerDTO;
    private SingleUserDTO singleUserDTO;
    private UserListDTO userListDTO;
    private SingleResourceDTO singleResourceDTO;
    private CreateRequestDTO createRequestDTO;
    private CreateResponseDTO createResponseDTO;

}

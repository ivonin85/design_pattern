package rf.ivonin.dto;

import lombok.Data;
import lombok.experimental.Accessors;
import rf.ivonin.dto.createDTO.CreateRequestDTO;
import rf.ivonin.dto.createDTO.CreateResponseDTO;
import rf.ivonin.dto.registerDTO.RegisterDTO;
import rf.ivonin.dto.registerDTO.RegisterResponseDTO;
import rf.ivonin.dto.resourceDTO.SingleResourceDTO;
import rf.ivonin.dto.userDTO.SingleUserDTO;
import rf.ivonin.dto.userListDTO.UserListDTO;

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

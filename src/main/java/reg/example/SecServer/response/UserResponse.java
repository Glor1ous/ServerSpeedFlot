package reg.example.SecServer.response;

import lombok.*;
import reg.example.SecServer.entity.UserEntity;
@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {
    private boolean success;
    private String message;
    private UserEntity user;
}

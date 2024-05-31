package reg.example.SecServer.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import reg.example.SecServer.entity.FlightEntity;
import reg.example.SecServer.entity.UserEntity;

import java.util.List;
import java.util.Optional;

public interface UserRepo extends JpaRepository<UserEntity, Long> {

    List<UserEntity> findByLoginAndPasswordAndEmail(String login,String password,String email);
  //  Optional<UserEntity> findByLogin(String login);


}

package reg.example.SecServer.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reg.example.SecServer.entity.FlightEntity;
import reg.example.SecServer.entity.UserEntity;
import reg.example.SecServer.repo.UserRepo;
import reg.example.SecServer.response.DataResponse;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Validated
public class UserService {
    private final UserRepo repo;

    public List<UserEntity> getAll() {
        return repo.findAll();
    }

    public Optional<UserEntity> findById(Long id) {
        return repo.findById(id);
    }

    public UserEntity save(UserEntity data) {
        return repo.save(data);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<UserEntity> findByParameters(String login, String password, String email) {
        return repo.findByLoginAndPasswordAndEmail(login, password, email);
    }

    public DataResponse<UserEntity> loginUser(String login, String password, String email) {
        List<UserEntity> users = repo.findByLoginAndPasswordAndEmail(login, password, email);
        if (!users.isEmpty()) {
            return new DataResponse<>(true, "Вход выполнен успешно", users.get(0));
        } else {
            return new DataResponse<>(false, "Неправильные данные, попробуйте снова", null);
        }
    }


}

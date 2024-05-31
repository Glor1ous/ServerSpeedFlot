package reg.example.SecServer.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reg.example.SecServer.entity.FlightEntity;
import reg.example.SecServer.entity.UserEntity;
import reg.example.SecServer.response.BaseResponse;
import reg.example.SecServer.response.DataResponse;
import reg.example.SecServer.response.ListResponse;
import reg.example.SecServer.service.UserService;

import java.util.List;

@Tag(name = "Пользователи", description = "Контроллер для работы с пользователями")
@RequestMapping("api/v1/user")
@RestController
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Регистрация пользователя")
    @PostMapping("/add")
    public ResponseEntity<DataResponse<UserEntity>> createFlight(@RequestBody UserEntity user){
        try {
            return ResponseEntity.ok(new DataResponse<UserEntity>(true, "Пользователь зарегистрирован", userService.save(user)));
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<UserEntity>(false, e.getMessage(), null));
        }


    }


    /*@PostMapping("/login")
    public ResponseEntity<DataResponse<UserEntity>> loginUser(@RequestBody UserEntity user) {
        DataResponse<UserEntity> response = userService.loginUser(user);
        return ResponseEntity.status(response.isStatus() ? 200 : 400).body(response);
    }*/


    @Operation(summary = "Обновить пользователя ")
    @PutMapping("/update")
    private ResponseEntity<BaseResponse> updateBy_user(@RequestBody UserEntity user) {
        try {
            if (userService.findById(user.getId()).isPresent()) {
                userService.save(user);
                return ResponseEntity.ok(new BaseResponse(true, "Пользователь обновлён"));
            } else {
                return ResponseEntity.badRequest().body(new BaseResponse(false, "Пользователь не был обновлен"));
            }
        } catch (Exception e ) {
            return ResponseEntity.badRequest().body(new BaseResponse(false, "Пользователь не был обновлен"));
        }
    }



    @Operation(summary = "Поиск всех пользователей")
    @GetMapping("/all")
    public ResponseEntity<ListResponse<UserEntity>> getAllUsers() {
        return ResponseEntity.ok(new ListResponse<>(true, "Пользователи", userService.getAll()));
    }




    /*
    @Operation(summary = "Поиск пользователя по ID")
    @GetMapping("/find{id}")
    public ResponseEntity<DataResponse<UserEntity>> findById(@RequestParam Long id) {
        try {
            UserEntity user = userService.findById(id).orElse(null);
            if (user != null) {
                return ResponseEntity.ok(new DataResponse<>(true, "Найден пользователь", user));
            } else {
                return ResponseEntity.ok(new DataResponse<>(false, "Пользователь не найден", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<>(false, e.getMessage(), null));
        }
    }
*/
//http://localhost:28245/api/v1/user/find?login=&password=&email=
    @Operation(summary = "Найти пользователя по всем данным")
    @GetMapping("/find")
    public ResponseEntity<DataResponse<List<UserEntity>>> findUser(
            @RequestParam String login,
            @RequestParam String password,
            @RequestParam String email
            ) {

        try {
            List<UserEntity> users = userService.findByParameters(login, password, email);
            if (!users.isEmpty()) {
                return ResponseEntity.ok(new DataResponse<>(true, "Пользователь найден", users));
            } else {
                return ResponseEntity.ok(new DataResponse<>(false, "Пользователя не было найдено"));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<>(false, "Ошибка при поиске пользователя: " + e.getMessage()));
        }
    }




    @Operation(summary = "Авторизация пользователя")
    @PostMapping("/login")
    public ResponseEntity<DataResponse<UserEntity>> loginUser(@RequestBody UserEntity user) {
        try {
            List<UserEntity> users = userService.findByParameters(user.getLogin(), user.getPassword(), user.getEmail());
            if (!users.isEmpty()) {
                return ResponseEntity.ok(new DataResponse<>(true, "Пользователь авторизован", users.get(0)));
            } else {
                return ResponseEntity.ok(new DataResponse<>(false, "Неправильные данные для авторизации", null));
            }
        } catch (RuntimeException e) {
            return ResponseEntity.ok(new DataResponse<>(false, "Ошибка при авторизации: " + e.getMessage(), null));
        }
    }






    @Operation(summary = "Удалить пользователя по ID")
    @DeleteMapping("/del{id}")
    public ResponseEntity<DataResponse <UserEntity>> deleteBy_id(@RequestParam Long id) {
        try {
            if (userService.findById(id).isPresent()) {
                userService.delete(id);
                return ResponseEntity.ok(new DataResponse<UserEntity>(true, "Пользователь был удален"));
            } else {
                return ResponseEntity.ok(new DataResponse<UserEntity>(false, "Пользователь не был найден и не был удален"));
            }
        } catch (RuntimeException  e) {
            return ResponseEntity.ok(new DataResponse<UserEntity>(false, "Пользователь не был удален: " + e.getMessage()));
        }
    }




}

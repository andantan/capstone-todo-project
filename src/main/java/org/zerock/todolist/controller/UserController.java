package org.zerock.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated; 
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.todolist.dto.LoginRequest;
import org.zerock.todolist.dto.PasswordChangeRequest;
import org.zerock.todolist.dto.SignUpRequest;
import org.zerock.todolist.service.UserService;

@RestController // REST 컨트롤러
@RequestMapping("/auth") // 기본 경로
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * 아이디 중복 확인 API 엔드포인트
     * GET /auth/check-username?username={username}
     * @param username 확인할 아이디 (쿼리 파라미터)
     * @return 아이디 사용 가능 시 200 OK ("success"), 중복 시 409 Conflict ("fault")
     */
    @GetMapping("/check-username")
    public ResponseEntity<String> checkUsername(@RequestParam String username) {
        if (username == null || username.trim().isEmpty()) {
             return new ResponseEntity<>("empty", HttpStatus.BAD_REQUEST);
        }

        if (userService.isUsernameAvailable(username)) {
            // 아이디 사용 가능
            return new ResponseEntity<>("success", HttpStatus.OK); // 200 OK
        } else {
            // 아이디 중복
            return new ResponseEntity<>("fault", HttpStatus.CONFLICT); // 409 Conflict
        }
    }

    /**
     * 회원가입 API 엔드포인트
     * POST /auth/register
     * 요청 본문 (JSON) : SignUpRequest DTO 형식
     * @param signUpRequest 회원가입 요청 DTO (@RequestBody로 받음)
     * @return 회원가입 성공 시 201 Created ("success"), 아이디 중복 시 409 Conflict ("fault"),
     * 유효성 검사 실패 시 400 Bad Request (Spring이 자동 처리)
     */
    @PostMapping("/register")
   
    public ResponseEntity<String> registerUser(@Validated @RequestBody SignUpRequest signUpRequest) {
       
        boolean isRegistered = userService.registerUser(signUpRequest);

        if (isRegistered) {
            // 회원가입 성공
            return new ResponseEntity<>("success", HttpStatus.CREATED); // 201 Created
        } else {
            // 아이디 중복으로 인한 회원가입 실패
            return new ResponseEntity<>("fault", HttpStatus.CONFLICT); // 409 Conflict
        }
    }

   /**
     * 비밀번호 변경 API 엔드포인트 (아이디, 이름, 전화번호 확인 후 변경)
     * POST /auth/change-password
     * 요청 본문 (JSON): PasswordChangeRequest DTO 형식
     * @param request 비밀번호 변경 요청 정보 (요청 본문에서 받음)
     * @return 비밀번호 변경 성공 시 "success" (200 OK), 사용자 정보 불일치 시 "user not found" (404 Not Found)
     */
    @PostMapping("/change-password")
    public ResponseEntity<String> changePassword(@RequestBody PasswordChangeRequest request) {
        boolean isChanged = userService.changePassword(request);

        if (isChanged) {
            // 비밀번호 변경 성공
            return new ResponseEntity<>("success", HttpStatus.OK); // 200 OK
        } else {
            // 사용자 정보 불일치
            return new ResponseEntity<>("user not found", HttpStatus.NOT_FOUND); // 404 Not Found 상태 코드 사용
        }
    }

    /**
     * 로그인 API 엔드포인트
     * POST /auth/login
     * 요청 본문 (JSON): LoginRequest DTO 형식
     * @param loginRequest 로그인 요청 DTO (@RequestBody로 받음)
     * @return 로그인 성공 시 토큰 등 정보 (200 OK), 실패 시 오류 메시지 (401 Unauthorized 등)
     */
    @PostMapping("/login")
    @Validated 
   
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

        
        boolean isAuthenticated = userService.verifyCredentials(loginRequest.getUsername(), loginRequest.getPassword());

        if (isAuthenticated) {
           //로그인 성공
             return ResponseEntity.ok().body("Login successful");
        } else {
            //로그인 실패
             return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials"); 
        }
    }
}
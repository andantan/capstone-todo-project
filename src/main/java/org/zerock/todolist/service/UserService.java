package org.zerock.todolist.service;

import org.zerock.todolist.domain.entity.User;
import org.zerock.todolist.dto.SignUpRequest;
import org.zerock.todolist.dto.PasswordChangeRequest; 
import org.zerock.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 

import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
@Transactional
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * 아이디(username)의 중복 여부를 확인합니다.
     * @param username 확인할 아이디
     * @return 아이디가 사용 가능하다면 true, 이미 존재한다면 false
     */
  
    @Transactional(readOnly = true)
    public boolean isUsernameAvailable(String username) {
        
        boolean exists = userRepository.existsByUsername(username);
        log.info("Checking availability for username: {}", username); // 기존 로깅 유지
        log.info("Username {} exists: {}", username, exists); // 기존 로깅 유지
        return !exists; // 존재하면 사용 불가 (!true = false), 존재 안 하면 사용 가능 (!false = true)
    }

    /**
     * 새로운 사용자를 등록합니다.
     * @param signUpRequest 회원가입 요청 DTO
     * @return 등록 성공 시 true, 아이디 중복 시 false
     */
     
    public boolean registerUser(SignUpRequest signUpRequest) {
        String username = signUpRequest.getUsername();

        // 아이디 중복 확인
        if (userRepository.existsByUsername(username)) {
             log.warn("Attempted to register with existing username: {}", username);
            return false; // 이미 존재하는 아이디
        }

        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(signUpRequest.getPassword());

        // User 엔티티 생성 (Builder 패턴 사용)
        User newUser = User.builder()
                .username(username)
                .password(encodedPassword)
                .name(signUpRequest.getName())
                .phoneNumber(signUpRequest.getPhoneNumber())
                .build();

        // 데이터베이스에 저장
        userRepository.save(newUser);
         log.info("User registered successfully: {}", username);
        return true; // 등록 성공
    }

     // 비밀번호 변경 로직 구현 시작
    /**
     * 아이디, 이름, 전화번호가 일치하는 경우 비밀번호를 변경합니다.
     * @param request 비밀번호 변경 요청 정보 (아이디, 이름, 전화번호, 새로운 비밀번호)
     * @return 비밀번호 변경 성공 시 true, 사용자 정보 불일치 시 false
     */
    
    public boolean changePassword(PasswordChangeRequest request) {
        log.info("Attempting to change password for user: {}", request.getUsername());

        // 아이디, 이름, 전화번호가 모두 일치하는 사용자를 찾습니다.
        Optional<User> userOptional = userRepository.findByUsernameAndNameAndPhoneNumber(
                request.getUsername(),
                request.getName(),
                request.getPhoneNumber()
        );

        // 일치하는 사용자가 없다면 비밀번호 변경 실패
        if (userOptional.isEmpty()) {
            log.warn("Password change failed: User not found or info mismatch for username {}", request.getUsername());
            return false;
        }

        // 일치하는 사용자가 있다면 비밀번호 변경 진행
        User user = userOptional.get();

        // 새로운 비밀번호 암호화
        String encodedNewPassword = passwordEncoder.encode(request.getNewPassword());

        // 사용자의 비밀번호 업데이트
        user.setPassword(encodedNewPassword);

        log.info("Password changed successfully for username {}", request.getUsername());
        return true; // 비밀번호 변경 성공
    } // 비밀번호 변경 로직 구현 끝


    /**
     * 사용자 자격 증명(아이디, 비밀번호)을 검증합니다. (로그인 로직의 일부)
     * @param username 아이디
     * @param password 원문 비밀번호
     * @return 자격 증명 일치 시 true, 불일치 시 false
     */
    @Transactional(readOnly = true) 
    public boolean verifyCredentials(String username, String password) {
        log.info("Verifying credentials for username: {}", username); // 로깅 추가
        // 아이디로 사용자 찾기
        Optional<User> userOptional = userRepository.findByUsername(username);

        if (userOptional.isEmpty()) {
            // 사용자가 존재하지 않음
             log.warn("Login failed: User not found for username {}", username);
            return false;
        }

        User user = userOptional.get();

        // 데이터베이스에 저장된 암호화된 비밀번호와 입력된 원문 비밀번호 비교
        // passwordEncoder.matches(원문 비밀번호, 암호화된 비밀번호)
        boolean passwordMatches = passwordEncoder.matches(password, user.getPassword());

        if (passwordMatches) {
             log.info("Login successful for username {}", username);
        } else {
             log.warn("Login failed: Incorrect password for username {}", username);
        }

        return passwordMatches;
    }


    
}
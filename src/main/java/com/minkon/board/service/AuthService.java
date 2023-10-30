package com.minkon.board.service;

import com.minkon.board.dto.ResponseDto;
import com.minkon.board.dto.SignInDto;
import com.minkon.board.dto.SignInResponseDto;
import com.minkon.board.dto.SignUpDto;
import com.minkon.board.entity.UserEntity;
import com.minkon.board.repository.UserRepository;
import com.minkon.board.security.TokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenProvider tokenProvider;


    private PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public ResponseDto<?> signUp(SignUpDto dto){
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userPasswordCheck = dto.getUserPasswordCheck();

        //email 중복 확인
        try{
            if(userRepository.existsById(userEmail)){
                return ResponseDto.setFailed("Existed Email!!");
            }
        }catch (Exception error){
            return ResponseDto.setFailed("Data Base Error!!");
        }

        //비밀번호 불일치시 fail 반환
        if(!userPassword.equals(userPasswordCheck))
            return ResponseDto.setFailed("password does not matched!");
        // UserEntity 생성
        UserEntity userEntity = new UserEntity(dto);

        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(userPassword);
        userEntity.setUserPassword(encodedPassword);

        // UserRepository를 이용해서 데이터베이스에 Entity 저장!!
        try {
            userRepository.save(userEntity);
        }catch (Exception error){
            return ResponseDto.setFailed("Data Base Error!!");
        }

        // 성공시 success response 반환
        return ResponseDto.setSuccess("SignUp Success",null);
    }

    // 로그인
    public ResponseDto<SignInResponseDto> signIn(SignInDto dto) {

        System.out.println("SignIn 함수 실행");
        System.out.println(dto);
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();

        UserEntity userEntity = null;

       try{
           userEntity = userRepository.findByUserEmail(userEmail);
           // 잘못된 이메일
           if(userEntity == null) return ResponseDto.setFailed("Sign in Failed");
           // 잘못된 패스워드
           if(!passwordEncoder.matches(userPassword,userEntity.getUserPassword())){
               return ResponseDto.setFailed("Sign in Failed");
           }
       }catch (Exception error){
           return ResponseDto.setFailed("Data Base Error!!");
       }

       userEntity.setUserPassword("");

       String token = tokenProvider.createRefreshToken(userEmail);


        System.out.println("토큰 생성 완료");
       int exprTime =3600000;

       SignInResponseDto signInResponseDto = new SignInResponseDto(token,exprTime,userEntity);
       return ResponseDto.setSuccess("Sign In Success",signInResponseDto);
    }
}

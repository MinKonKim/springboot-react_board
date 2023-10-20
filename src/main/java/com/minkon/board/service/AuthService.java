package com.minkon.board.service;

import com.minkon.board.dto.ResponseDto;
import com.minkon.board.dto.SignUpDto;
import com.minkon.board.entity.UserEntity;
import com.minkon.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;
    public ResponseDto<?> signUp(SignUpDto dto){
        String userEmail = dto.getUserEmail();
        String userPassword = dto.getUserPassword();
        String userPasswordCheck = dto.getUserPasswordCheck();

        //email 중복 확인
        try{
            if(userRepository.existsById(userEmail)){
                return ResponseDto.setFailed("Existed Email!!");
            }
        }catch (Exception e){
            return ResponseDto.setFailed("Data Base Error!!");
        }

        //비밀번호 불일치시 fail 반환
        if(!userPassword.equals(userPasswordCheck))
            return ResponseDto.setFailed("password does not matched!");
        // UserEntity 생성
        UserEntity userEntity = new UserEntity(dto);
        // UserRepository를 이용해서 데이터베이스에 Entity 저장!!
        try {
            userRepository.save(userEntity);
        }catch (Exception e){
            return ResponseDto.setFailed("Data Base Error!!");
        }

        // 성공시 success response 반환
        return ResponseDto.setSuccess("SignUp Success",null);
    }
}

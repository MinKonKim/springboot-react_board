package com.minkon.board.service;

import com.minkon.board.dto.PatchUserDto;
import com.minkon.board.dto.PatchUserResponseDto;
import com.minkon.board.dto.ResponseDto;
import com.minkon.board.entity.UserEntity;
import com.minkon.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseDto<PatchUserResponseDto> patchUser (PatchUserDto dto, String userEmail){

        UserEntity userEntity = null;
        String userNickname = dto.getUserNickname();
        String userProfile = dto.getUserProfile();

        try {
            userEntity = userRepository.findByUserEmail(userEmail);
            if(userEntity== null) return ResponseDto.setFailed("Does not exist user");

            userEntity.setUserNickname(userNickname);
            userEntity.setUserProfile(userProfile);

            userRepository.save(userEntity);
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.setFailed("db error");
        }

        userEntity.setUserPassword("");

        PatchUserResponseDto patchUserResponseDto = new PatchUserResponseDto(userEntity);

        return ResponseDto.setSuccess("Success",patchUserResponseDto);
    }
}

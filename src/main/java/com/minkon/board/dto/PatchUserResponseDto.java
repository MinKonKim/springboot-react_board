package com.minkon.board.dto;

import com.minkon.board.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PatchUserResponseDto {

    private UserEntity user;

}

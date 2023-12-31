package com.minkon.board.entity;

import com.minkon.board.dto.SignUpDto;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor // 아마 생성자 만들기용도 인듯
@NoArgsConstructor
@Entity(name="User") // - 해당 클래스를 Entity클래스로 사용하겠다.
@Table(name="User")  // - 데이터베이스에 있는 해당하는 테이블과 현재 클래스를 매핑 시키겠다.
public class UserEntity {
    @Id
    private String userEmail;
    private String userPassword;
    private String userNickname;
    private String userPhoneNumber;
    private String userAddress;
    private String userProfile;

    public UserEntity(SignUpDto dto){
        this.userEmail = dto.getUserEmail();
        this.userPassword = dto.getUserPassword();
        this.userNickname = dto.getUserNickname();
        this.userPhoneNumber = dto.getUserPhoneNumber();
        this.userAddress = dto.getUserAddress()+" "+dto.getUserAddressDetail();
    }
}

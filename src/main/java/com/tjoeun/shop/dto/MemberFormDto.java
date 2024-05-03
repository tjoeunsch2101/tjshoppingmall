package com.tjoeun.shop.dto;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberFormDto {

    @NotBlank(message="이름은 꼭 적어주세요")
    private String name;

    @NotEmpty(message="이메일은 꼭 적어주세요")
    @Email(message="이메일 형식으로 작성해 주세요")
    private String email;

    @NotEmpty(message="비밀번호는 꼭 적어주세요")
    @Length(min=4, max=20, message="비밀번호는 4 글자에서 20 글자 사이로 입력해 주세요")
    private String password;

    @NotEmpty(message="주소는 꼭 적어주세요")
    private String address;
}
package com.tjoeun.shop.entity;

import com.tjoeun.shop.constant.Role;
import com.tjoeun.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import jakarta.persistence.*;

@Entity
@Getter @Setter @ToString
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="member_id")
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING)
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, 
    		                              PasswordEncoder passwordEncoder){
    	
      // DTO --> Entity : ModelMapper 를 사용하지 않는 code
  		String password = passwordEncoder.encode(memberFormDto.getPassword());
  		
  		Member member = new Member();
  		member.setName(memberFormDto.getName());
  		member.setEmail(memberFormDto.getEmail());
  		member.setAddress(memberFormDto.getAddress());
  		member.setPassword(password);
  		member.setRole(Role.USER);
  		
  		return member;
    }

}

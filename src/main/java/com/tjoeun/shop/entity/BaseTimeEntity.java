package com.tjoeun.shop.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

//지정한 Entity(BasicTimeEntity) 는 Auditing 기능이 
//설정되었다는 것을 JPA에 알려줌
@EntityListeners(value= {AuditingEntityListener.class})
//자식 클래스에게 mapping 정보를 제공함
@MappedSuperclass
@Setter @Getter
public abstract class BaseTimeEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime regTime;

    @LastModifiedDate
    private LocalDateTime updateTime;

}
package com.tjoeun.shop.dto;

import com.tjoeun.shop.entity.ItemImg;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

@Getter @Setter
public class ItemImgDto {

    private Long id;
    private String imgName;
    private String oriImgName;
    private String imgUrl;
    private String repImgYn;

   // static 은 autowired 가 안 되서 객체를 직접 생성함
    private static ModelMapper modelMapper = new ModelMapper();

    // Entity 로 DTO 만들기
  	// parameter     return값
  	// Entity   -->  DTO        (DTO --> Entity)
    public static ItemImgDto of(ItemImg itemImg) {
        return modelMapper.map(itemImg,ItemImgDto.class);
    }

}
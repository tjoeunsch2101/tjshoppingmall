package com.tjoeun.shop.dto;

import com.tjoeun.shop.constant.ItemSellStatus;
import com.tjoeun.shop.entity.Item;
import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/*
id : 상품 코드
itemNm : 상품이름
price : 상품가격
stockNumber : 재고수량
itemDetail : 상품설명
itemSellStatus : 판매상태

@Null :   null 만 허용
@NotNull :   null 은 안 됨 / "", " " 은 허용
@NotEmpty :  null, "", [] 안 됨, " " 은 허용
@NotBlank :  null, "", " " 안 됨
*/

// 상품 등록 페이지에 입력하는 
// Item 상세정보와 ItemImage 정보를 저장하는 DTO
@Getter @Setter
public class ItemFormDto {

    private Long id;

    @NotBlank(message="상품 이름은 꼭 입력해 주세요")
    private String itemNm;

    @NotNull(message="상품 가격은 꼭 입력해 주세요")
    private Integer price;

    @NotNull(message="재고 수량은 꼭 입력해 주세요")
    private Integer stockNumber;
    
    @NotBlank(message="상품 상세 설명은 꼭 입력해 주세요")
    private String itemDetail;

    private ItemSellStatus itemSellStatus;

    private List<ItemImgDto> itemImgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();

    // DTO --> Entity : ModelMapper 를 사용한 code
    public Item createItem() {
    	Item item = modelMapper.map(this, Item.class);
    	return item;
    }
     
    // Entity --> Dto
    public static ItemFormDto entityToDto(Item item) {
    	ItemFormDto itemFormDto = modelMapper.map(item, ItemFormDto.class);
    	return itemFormDto;
    }
}
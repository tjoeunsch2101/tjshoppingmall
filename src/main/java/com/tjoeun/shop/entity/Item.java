package com.tjoeun.shop.entity;

import com.tjoeun.shop.constant.ItemSellStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import jakarta.persistence.*;
import com.tjoeun.shop.dto.ItemFormDto;
import com.tjoeun.shop.exception.OutOfStockException;

/*
id : 		상품코드
itemName : 상품이름
price : 상품가격
stockNumber : 상품재고
itemDetail : 상품설명 
itemSellStatus : 판매상태
regTime : 등록시간
updateTime : 수정시간
*/

@Entity
@Getter @Setter @ToString
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="item_id")
    private Long id;      

    @Column(nullable = false, length = 50)
    private String itemNm; 

    @Column(name="price", nullable = false)
    private int price; 

    @Column(nullable = false)
    private int stockNumber; 

    @Lob
    @Column(nullable = false)
    private String itemDetail;

    @Enumerated(EnumType.STRING)
    private ItemSellStatus itemSellStatus; 

  	// 상품 수정하기 : 상품 정보 수정시 호출됨
  	// Item Entity 의 상태가 변경되면
  	// ItemRepository 의 save() 를 호출하지않아도
  	// Dirty Checking(변경감지기능)에 의해서 DB 도 자동으로 갱신됨
  	// ItemAndImageDto : 화면에 입력한 수정 정보를 저장하고 있는 DTO
    public void updateItem(ItemFormDto itemFormDto){
        this.itemNm = itemFormDto.getItemNm();
        this.price = itemFormDto.getPrice();
        this.stockNumber = itemFormDto.getStockNumber();
        this.itemDetail = itemFormDto.getItemDetail();
        this.itemSellStatus = itemFormDto.getItemSellStatus();
    }

    public void removeStock(int orderedAmount){
        int restStock = this.stockNumber - orderedAmount;
        if(restStock<0){
        	throw new OutOfStockException("재고가 부족합니다 (현재 재고수량 :  " + this.stockNumber + ")");
        }
        // 재고가 부족하지 않아서 정상적으로 주문이 되는 경우
        this.stockNumber = restStock;
    }

  	//(주문이 취소되어) 현재 재고량 추가하기    
    public void addStock(int addStockNumber){
        this.stockNumber += addStockNumber;
    }

}
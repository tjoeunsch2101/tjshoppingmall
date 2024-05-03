package com.tjoeun.shop.entity;

import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter @Setter
public class OrderItem extends BaseEntity {
	
	/*
  	id 		    : 주문 상품 코드
  	order_id  : 주문 코드
  	item_id   : 상품 코드
  	orderPrice: 주문 가격
  	count 		: 주문 수량
  */

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    // 외래키(Many)가 있는 자식 table 이 연관관계에서 주인이 됨
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;
    
    // 외래키(Many)가 있는 자식 table 이 연관관계에서 주인이 됨
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice; 

    private int count; 

   // 주문할 상품과 수량(count)을 전달 받아서 OrderItem 객체를 생성해서 반환하기
    public static OrderItem createOrderItem(Item item, int count){
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setCount(count);
        orderItem.setOrderPrice(item.getPrice());
        item.removeStock(count);
        return orderItem;
    }

    public int getTotalPrice(){
        return orderPrice*count;
    }

    public void cancel() {
        this.getItem().addStock(count);
    }

}
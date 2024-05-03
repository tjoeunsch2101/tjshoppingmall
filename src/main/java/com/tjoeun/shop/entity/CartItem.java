package com.tjoeun.shop.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.*;

@Entity
@Getter @Setter @ToString
public class CartItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cart_id")
    private Cart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;
    
  	// 장바구니에 담는 상품 Entity 를 생성하는 메소드
    public static CartItem createCartItem(Cart cart, Item item, int count) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setItem(item);
        cartItem.setCount(count);
        return cartItem;
    }
    
  	// 장바구니에 담겨있는 상품을 더 추가로 구매하는 경우
  	// 구매 수량을 늘려주는 메소드
    public void addCount(int count){
        this.count += count;
    }

  	// CartItem Entity 클래스에 현재 장바구니에 있는 
  	// 수량을 변경하는 메소드 추가하기
    public void updateCount(int count){
        this.count = count;
    }

}
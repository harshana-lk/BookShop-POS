package com.bookiebook.pos.bo.custom;

import com.bookiebook.pos.bo.SuperBO;
import com.bookiebook.pos.to.ItemDetails;
import com.bookiebook.pos.to.Order;

import java.util.ArrayList;

public interface PlaceOrderBO extends SuperBO {
    boolean placeOrder(Order order, ArrayList<ItemDetails> details);
}

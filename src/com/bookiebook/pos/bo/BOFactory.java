package com.bookiebook.pos.bo;

import com.bookiebook.pos.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getInstance() {
        return boFactory == null ? boFactory = (new BOFactory()) : boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case CATEGORY:
                return new CategoryBOImpl();
            case CUSTOMER:
                return new CustomerBOImpl();
            case DELIVERY:
                return new DeliveryBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case ORDERDETAILS:
                return new OrderDetailsBOImpl();
            case SALARY:
                return new SalaryBOImpl();
            case USER:
                return new UserBOImpl();
            case VENDOR:
                return new VendorBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}

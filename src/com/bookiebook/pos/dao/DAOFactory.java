package com.bookiebook.pos.dao;

import com.bookiebook.pos.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = (new DAOFactory()) : daoFactory;
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CATEGORY:
                return new CategoryDAOImpl();
            case CUSTOMER:
                return new CustomerDAOImpl();
            case DELIVERY:
                return new DeliveryDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailsDAOImpl();
            case SALARY:
                return new SalaryDAOImpl();
            case USER:
                return new UserDAOImpl();
            case VENDOR:
                return new VendorDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            default:
                return null;
        }
    }
}

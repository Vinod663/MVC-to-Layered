package com.example.layeredarchitecture.DAO;

import com.example.layeredarchitecture.DAO.custom.CustomerDAO;
import com.example.layeredarchitecture.DAO.custom.impl.*;


public class DAOFactory {
    private static DAOFactory daoFactory;
    private DAOFactory() {

    }

    public static DAOFactory getInstance() {
        return daoFactory==null? daoFactory=new DAOFactory():daoFactory;
    }

    public enum DAOType{
        CUSTOMER,ITEM,ORDER,ORDER_DETAIL,QUERY
    }

    public CustomerDAO getDAO() {
        return new CustomerDAOImpl();

    }
    public SuperDAO getDAO(DAOType type) {
        switch (type){
            case CUSTOMER: return new CustomerDAOImpl();
                case ITEM: return new ItemDAOImpl();
                    case ORDER_DETAIL: return new OrderDetailDAOImpl();
                            case ORDER: return new OrderDAOImpl();
                                case QUERY: return new QueryDAOImpl();
                                    default: return null;

        }

    }

}

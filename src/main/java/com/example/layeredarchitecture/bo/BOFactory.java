package com.example.layeredarchitecture.bo;

import com.example.layeredarchitecture.bo.impl.CustomerBOImpl;
import com.example.layeredarchitecture.bo.impl.ItemBOImpl;
import com.example.layeredarchitecture.bo.impl.PlaceOrderBOImpl;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {}

    public static BOFactory getInstance() {
        return boFactory==null?boFactory=new BOFactory():boFactory;
    }


    public enum BOType {
        CUSTOMER,ITEM,PLACE_ORDER
    }
    public SuperBO getBO(BOType type) {
        switch (type) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            default:
                return null;
        }
    }
}

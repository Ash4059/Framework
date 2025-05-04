package com.MessageQueue.Framework.Utils;

public enum MAPPING_TYPE {

    GET_MAPPIG(0),
    POST_MAPPING(1),
    PUT_MAPPING(2),
    DELETE_MAPPING(3);

    private int value;
    MAPPING_TYPE(int value){
        this.value = value;
    }

}

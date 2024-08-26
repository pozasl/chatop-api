package com.chatop.api.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AbstractEntityToModelMapper {
    protected String convertDateForModel(Date date) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        return df.format(date);
    }
}

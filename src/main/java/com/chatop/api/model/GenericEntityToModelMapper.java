package com.chatop.api.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.BeanUtils;

import com.chatop.api.entity.GenericEntity;

public class GenericEntityToModelMapper<S extends GenericEntity<S>, T extends GenericModel<T>> {
    protected T entityToModel(S entity, T model) {
        BeanUtils.copyProperties(entity, model);
        // 2022/02/02
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        model.setCreated(df.format(entity.getCreationDate()));
        model.setUpdated(df.format(entity.getModificationDate()));
        return model;
    }

}

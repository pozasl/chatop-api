package com.chatop.api.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class GenericEntityToModelMapper<S extends GenericEntity<S>, T extends GenericModel<T>> {
    public T entityToModel(S entity, T model) {
        BeanUtils.copyProperties(entity, model);
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        model.setCreated(df.format(entity.getCreationDate()));
        model.setUpdated(df.format(entity.getModificationDate()));
        return model;
    }

}

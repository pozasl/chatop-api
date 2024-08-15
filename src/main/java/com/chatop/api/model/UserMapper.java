package com.chatop.api.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
  
  public User entityToModel(UserEntity entity) {
    User user = new User();
    if (Objects.isNull(entity)) {
      return user;
    }
    BeanUtils.copyProperties(entity, user);
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");

    user.setCreated(df.format(entity.getCreationDate()));
    user.setUpdated(df.format(entity.getModificationDate()));
    return user;
  }

}

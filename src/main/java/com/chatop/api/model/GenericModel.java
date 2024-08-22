package com.chatop.api.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

public abstract class GenericModel <T extends GenericModel<?>> {
    protected final T self;

    protected int id;

    @JsonProperty("owner_id")
    protected int ownerId;

    @JsonProperty("created_at")
    protected String created;

    @JsonProperty("updated_at")
    protected String updated;

    protected GenericModel(final Class<T> selfClass) {
        self= selfClass.cast(this);
    }

    public int getId() {
        return id;
    }

    public T setId(int id) {
        this.id = id;
        return self;
    }

    @JsonGetter("created_at")
    public String getCreated() {
        return this.created;
    }

    @JsonSetter("created_at")
    public T setCreated(String creationDateStr) {
        this.created = creationDateStr;
        return self;
    }

    @JsonGetter("updated_at")
    public String getUpdated() {
        return this.updated;
    }

    @JsonSetter("updated_at")
    public T setUpdated(String modificationDateStr) {
        this.updated = modificationDateStr;
        return self;
    }
}

package com.chatop.api.entity;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Transient;

@MappedSuperclass
public abstract class AbstractGenericEntity<T extends AbstractGenericEntity<?>> {

    @Transient
    protected final T self;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected int id;

    protected AbstractGenericEntity(final Class<T> selfClass) {
        // Casting this as Child Class for method chaining inheritance
        self = selfClass.cast(this);
        this.id = -1;
        this.creationDate = null;
        this.modificationDate = null;
    }

    @Column(name = "created_at")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date creationDate;

    @Column(name = "updated_at")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    protected Date modificationDate;

    public int getId() {
        return this.id;
    }

    public T setId(int id) {
        this.id = id;
        return self;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public T setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
        return self;
    }

    public Date getModificationDate() {
        return this.modificationDate;
    }

    public T setModificationDate(Date modificationDate) {
        this.modificationDate = modificationDate;
        return self;
    }

}

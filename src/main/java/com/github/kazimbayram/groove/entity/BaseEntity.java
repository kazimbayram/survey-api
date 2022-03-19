package com.github.kazimbayram.groove.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "sequences")
    @TableGenerator(name = "sequences", table = "sequence_table", pkColumnName = "table_name", valueColumnName = "next", allocationSize = 1)
    private Integer id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date insertedDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;

    @Version
    @Column(name = "version")
    private Integer version;

    @PrePersist
    protected void onCreation() {
        insertedDate = new Date();
        updatedDate = new Date();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = new Date();
    }

    @Override
    public String toString() {
        return String.format("%s(id = %d)", getClass().getSimpleName(), id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        BaseEntity that = (BaseEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}

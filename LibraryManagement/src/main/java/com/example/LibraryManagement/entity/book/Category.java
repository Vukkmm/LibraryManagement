package com.example.LibraryManagement.entity.book;

import com.example.LibraryManagement.entity.base.BaseEntityWithUpdater;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "categories")
public class Category extends BaseEntityWithUpdater {
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @Column(name = "is_deleted")
    private boolean isDeleted;

    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.isDeleted = false;
    }
}

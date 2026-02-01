package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pets")
@Data
@NoArgsConstructor
public class Pet {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String breed;
    
    @Column(nullable = false)
    private String age;
    
    @Column(nullable = false)
    private String color;
    
    @Column(length = 500)
    private String notes;
    
    @Column(nullable = false)
    private Boolean adopted;
    
    @Column(nullable = false)
    private Long createdAt;
    
    // Constructor with all fields
    public Pet(Long id, String name, String breed, String age, String color, String notes, Boolean adopted, Long createdAt) {
        this.id = id;
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
        this.notes = notes;
        this.adopted = adopted != null ? adopted : false;
        this.createdAt = createdAt != null ? createdAt : System.currentTimeMillis();
    }
    
    // Constructor without ID for creating new pets
    public Pet(String name, String breed, String age, String color, String notes) {
        this.name = name;
        this.breed = breed;
        this.age = age;
        this.color = color;
        this.notes = notes;
        this.adopted = false;
        this.createdAt = System.currentTimeMillis();
    }
    
    @PostLoad
    @PrePersist
    private void prePersist() {
        if (this.adopted == null) {
            this.adopted = false;
        }
        if (this.createdAt == null) {
            this.createdAt = System.currentTimeMillis();
        }
    }
}

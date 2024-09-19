package com.scaler.BookMyshow.models;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Theater extends BaseModel {
    private String name;
    @ManyToOne
    private Region region;
    @OneToMany
    private List<Screen> screens;
}

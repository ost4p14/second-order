package com.coursework.demo.entity;

import com.coursework.demo.entity.enums.LuggageType;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "luggage")
public class Luggage implements Serializable {
    @Id
    @GeneratedValue
    private long id;

    private String name;

    private int weight;

    @Enumerated(EnumType.STRING)
    private LuggageType luggageType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "passenger_id")
    private Passenger passenger;

}

package com.weddingplanner.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Client")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Client name is required.")
    private String name;

    @NotNull(message = "Wedding date is required.")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate weddingDate;

    @NotNull(message = "Wedding budget is required.")
    private BigDecimal budget;

    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private List<Event> events;

}
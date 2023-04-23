package com.mbo.model;

import com.mbo.enums.Profession;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Employee {

   @Id
   @GeneratedValue(generator = "UUID")
   @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
   private UUID id;

   private String firstName;
   private String lastName;
   private Integer daysOff;

   @CreationTimestamp
   private Timestamp createdDate;

   @UpdateTimestamp
   private Timestamp updatedDate;

   @Enumerated(EnumType.STRING)
   private Profession profession;

   @ManyToOne
   private Employee responsible;

}

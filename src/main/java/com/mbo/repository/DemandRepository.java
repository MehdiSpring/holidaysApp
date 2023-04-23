package com.mbo.repository;

import com.mbo.model.Demand;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface DemandRepository extends CrudRepository<Demand, UUID> {
}

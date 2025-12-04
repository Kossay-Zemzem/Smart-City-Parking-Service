package com.smartcity.parking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.smartcity.parking.models.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {
}


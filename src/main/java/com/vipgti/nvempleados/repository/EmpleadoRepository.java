package com.vipgti.nvempleados.repository;

import com.vipgti.nvempleados.domain.Empleado;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Empleado entity.
 */
@SuppressWarnings("unused")
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {

}

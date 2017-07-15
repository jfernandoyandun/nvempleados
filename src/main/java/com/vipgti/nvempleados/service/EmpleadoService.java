package com.vipgti.nvempleados.service;

import com.vipgti.nvempleados.domain.Empleado;
import com.vipgti.nvempleados.repository.EmpleadoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * Service Implementation for managing Empleado.
 */
@Service
@Transactional
public class EmpleadoService {

    private final Logger log = LoggerFactory.getLogger(EmpleadoService.class);
    
    @Inject
    private EmpleadoRepository empleadoRepository;

    /**
     * Save a empleado.
     *
     * @param empleado the entity to save
     * @return the persisted entity
     */
    public Empleado save(Empleado empleado) {
        log.debug("Request to save Empleado : {}", empleado);
        Empleado result = empleadoRepository.save(empleado);
        return result;
    }

    /**
     *  Get all the empleados.
     *  
     *  @param pageable the pagination information
     *  @return the list of entities
     */
    @Transactional(readOnly = true) 
    public Page<Empleado> findAll(Pageable pageable) {
        log.debug("Request to get all Empleados");
        Page<Empleado> result = empleadoRepository.findAll(pageable);
        return result;
    }

    /**
     *  Get one empleado by id.
     *
     *  @param id the id of the entity
     *  @return the entity
     */
    @Transactional(readOnly = true) 
    public Empleado findOne(Long id) {
        log.debug("Request to get Empleado : {}", id);
        Empleado empleado = empleadoRepository.findOne(id);
        return empleado;
    }

    /**
     *  Delete the  empleado by id.
     *
     *  @param id the id of the entity
     */
    public void delete(Long id) {
        log.debug("Request to delete Empleado : {}", id);
        empleadoRepository.delete(id);
    }
}

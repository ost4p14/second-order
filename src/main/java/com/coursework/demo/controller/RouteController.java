package com.coursework.demo.controller;

import com.coursework.demo.dto.RouteDTO;
import com.coursework.demo.entity.Route;
import com.coursework.demo.mapper.RouteMapper;
import com.coursework.demo.service.RouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;
import static org.springframework.http.HttpStatus.OK;

@RestController
@Api(tags = "Route API")
@RequestMapping("v1/routes")
public class RouteController {

    private final RouteService routeService;
    private final RouteMapper routeMapper;

    @Autowired
    public RouteController(RouteService routeService, RouteMapper routeMapper) {
        this.routeService = routeService;
        this.routeMapper = routeMapper;
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get route info by id")
    public ResponseEntity<RouteDTO> get(@PathVariable("id") long id) {
        Route route = routeService.getById(id);
        return ResponseEntity.status(OK).body(routeMapper.convertToDto(route));
    }


    @GetMapping
    @ApiOperation(value = "Get the list of all routes")
    public ResponseEntity<List<RouteDTO>> list(@PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok().body(routeMapper.convertToDtoList(routeService.getAll(pageable)));
    }


    @PostMapping
    @ApiOperation(value = "Create new route")
    public ResponseEntity<RouteDTO> save(@RequestBody RouteDTO routeDTO) {
        Route route = routeService.save(routeMapper.convertToEntity(routeDTO));
        return ResponseEntity.status(CREATED).body(routeMapper.convertToDto(route));
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update existing route by id")
    public ResponseEntity<RouteDTO> update(@PathVariable("id") long id, @RequestBody RouteDTO routeDTO) {
        if (id == routeDTO.getId()) {
            Route route = routeService.update(routeMapper.convertToEntity(routeDTO));
            return ResponseEntity.status(OK).body(routeMapper.convertToDto(route));
        } else {
            return ResponseEntity.status(BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete route by id")
    public ResponseEntity delete(@PathVariable("id") long id) {
        Route route = routeService.getById(id);
        routeService.delete(route);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}

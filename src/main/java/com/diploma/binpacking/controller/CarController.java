package com.diploma.binpacking.controller;

import com.diploma.binpacking.message.PackRequest;
import com.diploma.binpacking.model.Car;
import com.diploma.binpacking.service.CarService;
import com.diploma.binpacking.service.CargoService;
import com.diploma.binpacking.service.GraphExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;
    private CargoService cargoService;
    private GraphExampleService graphExampleService;

    @Autowired
    public CarController(CarService carService, CargoService cargoService, GraphExampleService graphExampleService) {
        this.carService = carService;
        this.cargoService = cargoService;
        this.graphExampleService = graphExampleService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        return ResponseEntity.ok(carService.getAll());
    }

    @PostMapping
    public ResponseEntity<String> packCargo(@RequestBody PackRequest packRequest) {
        return ResponseEntity.ok(cargoService.packCargo(packRequest));
    }

    @GetMapping(path = "/way/{start}-{end}")
    public ResponseEntity<String> getShortWay(@PathVariable String start,
                                              @PathVariable String end) {
        return ResponseEntity.ok(graphExampleService.getShortPath(start, end));
    }
}

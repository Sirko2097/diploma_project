package com.diploma.binpacking.controller;

import com.diploma.binpacking.message.PackRequest;
import com.diploma.binpacking.message.ResultRequest;
import com.diploma.binpacking.model.Car;
import com.diploma.binpacking.service.CalculationService;
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
    private CalculationService calculationService;

    @Autowired
    public CarController(CarService carService, CargoService cargoService,
                         GraphExampleService graphExampleService, CalculationService calculationService) {
        this.carService = carService;
        this.cargoService = cargoService;
        this.graphExampleService = graphExampleService;
        this.calculationService = calculationService;
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

    @PostMapping(path = "/maintask")
    public ResponseEntity<String> getResultCalculation(@RequestBody ResultRequest resultRequest) {
        return ResponseEntity.ok(calculationService.getResult(resultRequest));
    }
}

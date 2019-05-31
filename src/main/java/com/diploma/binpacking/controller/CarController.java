package com.diploma.binpacking.controller;

import com.diploma.binpacking.message.PackRequest;
import com.diploma.binpacking.model.Car;
import com.diploma.binpacking.service.CarService;
import com.diploma.binpacking.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {

    private CarService carService;
    private CargoService cargoService;

    @Autowired
    public CarController(CarService carService, CargoService cargoService) {
        this.carService = carService;
        this.cargoService = cargoService;
    }

    @GetMapping
    public ResponseEntity<List<Car>> getAll() {
        return ResponseEntity.ok(carService.getAll());
    }

    @PostMapping
    public ResponseEntity<String> packCargo(@RequestBody PackRequest packRequest) {
        return ResponseEntity.ok(cargoService.packCargo(packRequest));
    }
}

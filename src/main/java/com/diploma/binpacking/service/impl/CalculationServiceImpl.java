package com.diploma.binpacking.service.impl;

import com.diploma.binpacking.dao.CarDAO;
import com.diploma.binpacking.message.PackRequest;
import com.diploma.binpacking.message.ResultRequest;
import com.diploma.binpacking.service.CalculationService;
import com.diploma.binpacking.service.CarService;
import com.diploma.binpacking.service.CargoService;
import com.diploma.binpacking.service.GraphExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculationServiceImpl implements CalculationService {

    private CargoService cargoService;

    @Autowired
    public CalculationServiceImpl(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    /**
     * Change return types or use parsers.
     * @param resultRequest
     * @return
     */
    @Override
    public String getResult(ResultRequest resultRequest) {
        cargoService.packCargo(new PackRequest(resultRequest.getCargo()));
        GraphExampleService graphExampleService = new GraphExampleServiceImpl();
        graphExampleService.getShortPath(resultRequest.getStart(), resultRequest.getEnd());
        return null;
    }
}

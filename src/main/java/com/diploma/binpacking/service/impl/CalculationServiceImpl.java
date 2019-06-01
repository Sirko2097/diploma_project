package com.diploma.binpacking.service.impl;


import com.diploma.binpacking.message.PackRequest;
import com.diploma.binpacking.message.ResultRequest;
import com.diploma.binpacking.service.CalculationService;
import com.diploma.binpacking.service.CargoService;
import com.diploma.binpacking.service.GraphExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.NumberFormat;

@Service
public class CalculationServiceImpl implements CalculationService {

    private CargoService cargoService;

    @Autowired
    public CalculationServiceImpl(CargoService cargoService) {
        this.cargoService = cargoService;
    }

    @Override
    public String getResult(ResultRequest resultRequest) {
        NumberFormat format = new DecimalFormat("#0.00");
        double packingPrice = (Double.parseDouble(cargoService
                .packCargo(new PackRequest(resultRequest.getCargo()))));

        GraphExampleService graphExampleService = new GraphExampleServiceImpl();
        double shortPath = Double.valueOf(graphExampleService.getShortPath(resultRequest.getStart(),
                resultRequest.getEnd()));

        double result = packingPrice + shortPath * 28.5;
        System.out.println(format.format(packingPrice) + " + " + shortPath * 28.5 + " = " + result);

        return "Total price: " + format.format(result);
    }
}

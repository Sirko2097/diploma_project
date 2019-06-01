package com.diploma.binpacking.service.binpackingimpl;

import com.diploma.binpacking.exception.OverflowException;
import com.diploma.binpacking.model.Car;
import lombok.NoArgsConstructor;
import net.sourceforge.evoj.GenePool;
import net.sourceforge.evoj.Individual;
import net.sourceforge.evoj.core.DefaultPoolFactory;
import net.sourceforge.evoj.handlers.DefaultHandler;


import java.util.*;


public class LorryPacking {

    private String cargoOrder;

    private List<Car> cars;

    public LorryPacking(List<Car> cars) {
        this.cars = cars;
    }

    private StringBuilder answer = new StringBuilder();
    private ArrayList<Integer> carList;
    private ArrayList<Integer> priceList;

    private void init() {
        carList = new ArrayList<>();
        priceList = new ArrayList<>();




        cars.forEach(car -> {
            carList.add(car.getCapacity());
            priceList.add(car.getPrice());
        });
    }

    public void solve() {
        init();
        try {
            Cargo cargo = new Cargo();

            int[] cars = setArray(carList);

            int[] items = setArray(cargo.getCargoList(cargoOrder));


            Map<String, String> context = new HashMap<>();
            context.put("itemsCount", Integer.toString(items.length));
            context.put("lastBinIndex", Integer.toString(cars.length - 1));

            DefaultPoolFactory factory = new DefaultPoolFactory();
            GenePool<Solution> pool = factory.createPool(200, Solution.class, context);

            DefaultHandler handler = new DefaultHandler(new BinPackRatingCalculator(items, cars), null, null, null);

            handler.iterate(pool, 40);

            Solution bestSolution = pool.getBestSolution();


            showSolution(bestSolution, cars, items, priceList, carList);
        } catch (OverflowException e) {
            answer.append("We can't pack it");
            System.out.println("We can't pack it");
        } catch (Exception e) {
            answer.append("Error! Try input order again!");
        }
    }

    private int[] setArray(ArrayList<Integer> list) {

        int[] arr = new int[list.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    private void showSolution(Solution bestSolution, int[] cars, int[] items,
                              ArrayList<Integer> priceList, ArrayList<Integer> carList) throws OverflowException {
        double totalPrice = 0;
        System.out.print("\nCargo(kilograms): ");
        Arrays.stream(items).forEach((n) -> System.out.print(n + " "));
        System.out.println();
        System.out.print("Trucks(kilograms): ");
        Arrays.stream(cars).forEach((n) -> System.out.print(n + " "));
        System.out.println('\n');

        Individual individual = (Individual) bestSolution;
        PackRating rating = (PackRating) individual.getRating();
        System.out.println("Cars used:" + rating.getBinsUsed());

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i < cars.length; i++) {
            list.add(new ArrayList<>());
        }
        final List<Integer> tmpItems = bestSolution.getBinIndices();

        for (int i = 0; i < tmpItems.size(); i++) {
            Integer binIndex = tmpItems.get(i);
            List<Integer> bin = list.get(binIndex);
            bin.add(items[i]);
        }

        for (int i = 0; i < cars.length; i++) {
            double price;
            List<Integer> car = list.get(i);
            StringBuilder builder = new StringBuilder("\nCar #" + i + "(" + cars[i] + "): ");

            price = priceList.get(carList.indexOf(cars[i])) * 1.0 / cars[i];

            int sum = 0;
            for (Integer item : car) {
                builder.append(item).append(" ");
                sum += item;
            }
            builder.append("Total Weight: ").append(sum);
            if (sum > cars[i]) {
                throw new OverflowException();
            }
            if (sum > 0) {
                builder.append(" Price: ").append(sum * price);
                totalPrice += sum * price;
                System.out.println(builder);
//                answer.append(builder).append("<br>");
            }
        }
        answer.append(totalPrice);
        setAnswer(answer);
    }

    public String getAnswer() {
        solve();
        return answer.toString();
    }

    public void setAnswer(StringBuilder answer) {
        this.answer = answer;
    }

    public String getCargoOrder() {
        return cargoOrder;
    }

    public void setCargoOrder(String cargoOrder) {
        this.cargoOrder = cargoOrder;
    }
}

package org.oobootcamp.warmup.parkingLot;

import org.oobootcamp.warmup.parkingLot.Exceptions.CarNotFoundException;
import org.oobootcamp.warmup.parkingLot.Exceptions.DuplicateParkingException;
import org.oobootcamp.warmup.parkingLot.Exceptions.ParkingLotUnavailableException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ParkingLot {

    private final int totalCount;

    private List<Car> cars = new ArrayList<>();

    public ParkingLot(int count) {
        this.totalCount = count;
    }

    public Ticket park(Car car) {
        if (cars.size() == totalCount) {
            throw new ParkingLotUnavailableException();
        }
        checkDuplicateParking(car);
        cars.add(car);
        return new Ticket(car.getCarNo());
    }

    private void checkDuplicateParking(Car car) {
        Optional<Car> parkedCar = cars.stream().filter(car1 -> car1.getCarNo().equals(car.getCarNo())).findFirst();
        if (parkedCar.isPresent()) {
            throw new DuplicateParkingException();
        }

    }

    public Optional<Car> pickUpCar(Ticket ticket) {
        String carNo = ticket.getCarNo();

        Optional<Car> car = cars.stream().filter(c -> c.getCarNo().equals(carNo)).findAny();
        if (car.isEmpty()) {
            throw new CarNotFoundException();
        }
        cars.remove(car.get());
        return car;
    }
}

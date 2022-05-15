package org.oobootcamp.warmup.length;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.oobootcamp.warmup.parkingLot.Car;
import org.oobootcamp.warmup.parkingLot.Exceptions.CarNotFoundException;
import org.oobootcamp.warmup.parkingLot.Exceptions.DuplicateParkingException;
import org.oobootcamp.warmup.parkingLot.Exceptions.ParkingLotUnavailableException;
import org.oobootcamp.warmup.parkingLot.ParkingLot;
import org.oobootcamp.warmup.parkingLot.Ticket;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ParkingLotTest {
    @Test
    public void should_parking_success_and_get_ticket_when_parking_given_a_car_and_available_parking_lot() {
        //given
        int count = 20;
        ParkingLot parkingLot = new ParkingLot(count);
        String carNo = "陕A T123";
        Car car = new Car(carNo);
        //when
        Ticket ticket = parkingLot.park(car);
        //then
        assertThat(ticket.getCarNo()).isEqualTo(carNo);
    }

    @Test
    public void should_parking_failed_with_parking_lot_unavailable_exception_when_parking_given_a_car_and_unavailable_parking_lot() {
        //given
        int count = 20;
        ParkingLot parkingLot = new ParkingLot(count);
        for (int i = 0; i < 20; i++) {
            Car car = new Car(UUID.randomUUID().toString());
            parkingLot.park(car);
        }
        String carNo = "陕A T123";
        Car car = new Car(carNo);
        //when
        assertThrows(ParkingLotUnavailableException.class, () -> parkingLot.park(car));
    }

    @Test
    public void should_parking_failed_with_duplicate_parking_exception_when_parking_given_a_parked_car_and_available_parking_lot() {
        //given
        int count = 20;
        ParkingLot parkingLot = new ParkingLot(count);
        String carNo = "陕A T123";
        Car car = new Car(carNo);
        parkingLot.park(car);
        //when
        assertThrows(DuplicateParkingException.class, () -> parkingLot.park(car));
    }

    @Test
    public void should_given_a_car_when_pick_up_given_a_right_ticket(){
        //given
        ParkingLot parkingLot = new ParkingLot(20);
        Car myCar = new Car("陕A 12SF32");
        Ticket ticket = parkingLot.park(myCar);

        //when
        Optional<Car> car = parkingLot.pickUpCar(ticket);

        //then
        Assertions.assertTrue(car.isPresent());
        Assertions.assertEquals(myCar.getCarNo(), car.get().getCarNo());
    }

    @Test
    public void should_throw_car_not_found_exception_when_pick_up_given_fake_paring_ticket() {
        //given
        ParkingLot parkingLot = new ParkingLot(20);
       Ticket ticket = new Ticket("陕A 12SF32");

        //when
        assertThrows(CarNotFoundException.class, () -> parkingLot.pickUpCar(ticket));
    }
}

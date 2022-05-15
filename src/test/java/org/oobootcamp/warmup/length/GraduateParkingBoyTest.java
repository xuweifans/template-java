package org.oobootcamp.warmup.length;

import org.junit.jupiter.api.Test;
import org.oobootcamp.warmup.parkingLot.Car;
import org.oobootcamp.warmup.parkingLot.ParkingLot;
import org.oobootcamp.warmup.parkingLot.Ticket;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class GraduateParkingBoyTest {
    //给停车小弟三个停车场1（容量为1，空的）、停车场2（容量为1，空的）、停车场3（容量为1，空的），和一辆车1；让停车小弟停车时；停车成功，获得停车场1的停车票1
    @Test
    public void should_parking_success_and_get_a_ticket_when_parking_given_a_graduate_parking_boy_and_3_available_parking_lot() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(1);
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2, parkingLot3);
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLots);
        String carNo = "陕A T123";
        Car car = new Car(carNo);
        //when
        Ticket ticket = parkingBoy.park(car);
        //then
        assertThat(ticket.getCarNo()).isEqualTo(carNo);
    }
}

package org.oobootcamp.warmup.length;

import org.junit.jupiter.api.Test;
import org.oobootcamp.warmup.parkingLot.Car;
import org.oobootcamp.warmup.parkingLot.Exceptions.CarNotFoundException;
import org.oobootcamp.warmup.parkingLot.Exceptions.ParkingLotUnavailableException;
import org.oobootcamp.warmup.parkingLot.ParkingLot;
import org.oobootcamp.warmup.parkingLot.Ticket;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    //给停车小弟三个停车场1（容量为1，满的）、停车场2（容量为1，空的）、停车场3（容量为1，空的），和一辆车1；让停车小弟停车时；停车成功，获得停车场2的停车票2
    @Test
    public void should_parking_success_and_get_a_ticket_when_parking_given_a_graduate_parking_boy_and_1_unavailable_parking_lot_2_available_parking_lot() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car("陕A T123"));
        ParkingLot parkingLot2 = new ParkingLot(1);
        ParkingLot parkingLot3 = new ParkingLot(1);
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2, parkingLot3);
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLots);
        String carNo = "陕A T234";
        Car car = new Car(carNo);
        //when
        Ticket ticket = parkingBoy.park(car);
        //then
        assertThat(ticket.getCarNo()).isEqualTo(carNo);
        //没有验证是停车场2的停车票，是因为客户不关心票上的这个信息
    }

    //给停车小弟三个停车场1（容量为1，满的）、停车场2（容量为1，满的）、停车场3（容量为1，空的），和一辆车1；让停车小弟停车时；停车成功，获得停车场3的停车票3
    @Test
    public void should_parking_success_and_get_a_ticket_when_parking_given_a_graduate_parking_boy_and_2_unavailable_parking_lot_1_available_parking_lot() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car("陕A T123"));
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car("陕A T234"));
        ParkingLot parkingLot3 = new ParkingLot(1);
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2, parkingLot3);
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLots);
        String carNo = "陕A T345";
        Car car = new Car(carNo);
        //when
        Ticket ticket = parkingBoy.park(car);
        //then
        assertThat(ticket.getCarNo()).isEqualTo(carNo);
    }

    //给停车小弟两个停车场1（容量为1，空的）、停车场2（容量为1，满的），和一辆车1；让停车小弟停车时；停车成功，获得停车场1的停车票1
    @Test
    public void should_parking_success_and_get_a_ticket_when_parking_given_a_graduate_parking_boy_and_first_available_parking_lot_and_second_unavailable_parking_lot() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        ParkingLot parkingLot2 = new ParkingLot(1);
        parkingLot2.park(new Car("陕A T123"));
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2);
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLots);
        String carNo = "陕A T234";
        Car car = new Car(carNo);
        //when
        Ticket ticket = parkingBoy.park(car);
        //then
        assertThat(ticket.getCarNo()).isEqualTo(carNo);
    }

    //给停车小弟一个停车场1（容量为1，满的），和一辆车1；让停车小弟停车时；停车失败，返回“所有停车场已满”
    @Test
    public void should_parking_failed_with_parking_lot_unavailable_exception_when_parking_given_a_graduate_parking_boy_and_1_unavailable_parking_lot() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car("陕A T123"));
        List<ParkingLot> parkingLots = List.of(parkingLot1);
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLots);
        String carNo = "陕A T234";
        Car car = new Car(carNo);
        //when
        assertThrows(ParkingLotUnavailableException.class, () -> parkingBoy.park(car));
    }

    //不给小弟停车场，和一辆车1；让停车小弟停车时；停车失败，返回“无停车场可用”
    @Test
    public void should_parking_failed_with_no_parking_lot_exception_when_parking_given_a_graduate_parking_boy_and_null_parking_lot() {
        //given
        List<ParkingLot> parkingLots = new ArrayList<>();
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLots);
        String carNo = "陕A T234";
        Car car = new Car(carNo);
        //when
        assertThrows(NoParkingLotException.class, () -> parkingBoy.park(car));
    }

    //给停车小弟三个停车场1（容量为1，满的）、停车场2（容量为1，满的）、停车场3（容量为1，满的），和一张停车票2（车2停在停车场2）；让停车小弟取车时；取车成功，获得车2
    @Test
    public void should_return_a_car_when_pick_up_given_a_graduate_parking_boy_and_1_ticket() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car("陕A T123"));
        ParkingLot parkingLot2 = new ParkingLot(1);
        Car myCar = new Car("陕A T234");
        Ticket myTicket = parkingLot2.park(myCar);
        ParkingLot parkingLot3 = new ParkingLot(1);
        parkingLot3.park(new Car("陕A T345"));
        List<ParkingLot> parkingLots = List.of(parkingLot1, parkingLot2, parkingLot3);
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLots);
        //when
        Car returnCar = parkingBoy.pickUpCar(myTicket);
        //then
        assertThat(myCar.getCarNo()).isEqualTo(returnCar.getCarNo());
    }

    //给小弟一个停车场1（容量为1，满的），和一张停车票4（车4停在停车场4）；让停车小弟取车时；取车失败，返回“停车场不在管理范围内”
    @Test
    public void should_throw_car_not_found_exception_when_pick_up_given_a_graduate_parking_boy_and_1_fake_ticket() {
        //given
        ParkingLot parkingLot1 = new ParkingLot(1);
        parkingLot1.park(new Car("陕A T123"));
        List<ParkingLot> parkingLots = List.of(parkingLot1);
        GraduateParkingBoy parkingBoy = new GraduateParkingBoy(parkingLots);
        Ticket fakeTicket = new Ticket("AKLJSK");
        //when
        assertThrows(CarNotFoundException.class, () -> parkingBoy.pickUpCar(fakeTicket));
    }


}

package com.MakeMyTrip.springboot.mmt_project.service;

import com.MakeMyTrip.springboot.mmt_project.dao.BookingDetailsRepository;
import com.MakeMyTrip.springboot.mmt_project.dto.BookingDetailDTO;
import com.MakeMyTrip.springboot.mmt_project.dto.ShowBookingsDTO;
import com.MakeMyTrip.springboot.mmt_project.entity.BookingDetails;
import com.MakeMyTrip.springboot.mmt_project.entity.FareDetail;
import com.MakeMyTrip.springboot.mmt_project.entity.FlightDetail;
import com.MakeMyTrip.springboot.mmt_project.entity.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Service
public class BookingDetailServiceImpl implements BookingDetailService{

    private BookingDetailsRepository bookingDetailsRepository;
    private UserDetailService userDetailService;
    private FareDetailService fareDetailService;
    private FlightDetailService flightDetailService;
    private UserDetails userDetails;


    @Autowired public BookingDetailServiceImpl(BookingDetailsRepository bookingDetailsRepository, UserDetailService userDetailService, FareDetailService fareDetailService,FlightDetailService flightDetailService) {
        this.bookingDetailsRepository = bookingDetailsRepository;
        this.userDetailService = userDetailService;
        this.fareDetailService = fareDetailService;
        this.flightDetailService=flightDetailService;
    }

    //METHOD TO SAVE A BOOKING
    @Transactional
    @Override
    public void saveBooking(BookingDetailDTO bookingDetailsDTO){

        BookingDetails bookingDetails=new BookingDetails();
        UserDetails user=new UserDetails();
        user.setFirstName(bookingDetailsDTO.firstName());
        user.setLastName(bookingDetailsDTO.lastName());
        user.setPhoneNumber(bookingDetailsDTO.phoneNumber());
        user.setEmailId(bookingDetailsDTO.emailId());
        user.setGender(bookingDetailsDTO.gender());


        if(userDetailService.findByEmail(bookingDetailsDTO.emailId())!=null){
            UserDetails existingUser=userDetailService.findByEmail(bookingDetailsDTO.emailId());
            bookingDetails.setUser(existingUser);
        }
        else
        {
            UserDetails userDetails=userDetailService.saveUser(user);
            bookingDetails.setUser(userDetails);

        }

        FareDetail fare=fareDetailService.findFareById(bookingDetailsDTO.fare());
        if (!(fare==null)){
            bookingDetails.setFare(fare);
        }

        bookingDetailsRepository.save(bookingDetails);
    }

    //METHOD TO DELETE A BOOKING
    @Override
    public void deleteBookingById(int bookingId) {
        bookingDetailsRepository.deleteById(bookingId);
    }

    //METHOD TO FIND A BOOKING BY ID
    @Override
    public BookingDetails findBookingById(int bookingId) {
        return bookingDetailsRepository.findById(bookingId).orElse(null);
    }

    //METHOD TO FIND ALL BOOKINGS
    @Override
    public List<ShowBookingsDTO> findAllBookings(Integer pageNumber,Integer pageSize,String sortBy) {

        Pageable paging = null;
        if (sortBy.toLowerCase().equals("fare")){
             paging= PageRequest.of(pageNumber,pageSize, Sort.by("fare"));
        } else if (sortBy.toLowerCase().equals("bookingtime")) {
             paging= PageRequest.of(pageNumber,pageSize, Sort.by("bookingTime"));
        }else {
            paging= PageRequest.of(pageNumber,pageSize, Sort.by("fare"));
        }

        List<ShowBookingsDTO> bookingsDTOList=new ArrayList<>();
        List<BookingDetails> bookings= bookingDetailsRepository.findAll(paging).toList();
        List<FareDetail> fareDetailList=new ArrayList<>();
        List<FlightDetail> flightDetailList=new ArrayList<>();
        List<UserDetails> userDetailsList=new ArrayList<>();
        List<LocalDateTime> bookingTime=new ArrayList<>();

        //LIST FOR STORING FARES OBJECTS AND USER OBJECTS
        for (BookingDetails booking:bookings) {
            fareDetailList.add(booking.getFare());
            userDetailsList.add(booking.getUser());
            bookingTime.add(booking.getBookingTime());
        }
        //LIST FOR STORING FLIGHT OBJECTS
        for (FareDetail fare:fareDetailList) {
            FlightDetail flight=fare.getFlightNumber();
            flightDetailList.add(flight);
        }

        int sizeofbookingsList=bookings.size();

        for (int i=0;i<sizeofbookingsList;i++){
            ShowBookingsDTO showBookingsDTO=new ShowBookingsDTO();
            showBookingsDTO.setAirLine(flightDetailList.get(i).getAirline());
            showBookingsDTO.setDapartureTime(flightDetailList.get(i).getDepartTime());
            showBookingsDTO.setArrivalTime(flightDetailList.get(i).getArriveTime());
            showBookingsDTO.setDuration(flightDetailList.get(i).getDuration());
            showBookingsDTO.setSource(flightDetailList.get(i).getSource());
            showBookingsDTO.setDestination(flightDetailList.get(i).getDestination());
            showBookingsDTO.setFare(fareDetailList.get(i).getFare());
            showBookingsDTO.setClassType(fareDetailList.get(i).getClassType());
            showBookingsDTO.setBookingTime(bookings.get(i).getBookingTime());
            showBookingsDTO.setUserName((userDetailsList.get(i).getFirstName())+(userDetailsList.get(i).getLastName()));
            showBookingsDTO.setEmail(userDetailsList.get(i).getEmailId());
            showBookingsDTO.setPhoneNumber(userDetailsList.get(i).getPhoneNumber());
            showBookingsDTO.setGender(userDetailsList.get(i).getGender());
            bookingsDTOList.add(showBookingsDTO);
        }


        return bookingsDTOList;
    }

    //METHOD TO FIND BOOKINGS BY EMAIL
    @Override
    public List<ShowBookingsDTO> findBookingsByEmail(String emailId,Integer pageNumber,Integer pageSize,String sortBy) {

        Pageable paging = null;
        if (sortBy.toLowerCase().equals("fare")){
            paging= PageRequest.of(pageNumber,pageSize, Sort.by("fare"));
        } else if (sortBy.toLowerCase().equals("bookingtime")) {
            paging= PageRequest.of(pageNumber,pageSize, Sort.by("bookingTime"));
        }else {
            paging= PageRequest.of(pageNumber,pageSize, Sort.by("fare"));
        }

        List<ShowBookingsDTO> showBookingsDTOList=new ArrayList<>();
        List<FareDetail> fareDetailList=new ArrayList<>();
        List<FlightDetail> flightDetailList=new ArrayList<>();
        List<UserDetails> userDetailsList=new ArrayList<>();
        List<LocalDateTime> bookingTime=new ArrayList<>();
        List<BookingDetails> bookings=new ArrayList<>();

        userDetailsList.add(userDetailService.findByEmail(emailId));
        for (UserDetails u:userDetailsList) {
            bookings.addAll(bookingDetailsRepository.findByUser(u,paging));
        }
        for (BookingDetails b:bookings) {
            bookingTime.add(b.getBookingTime());
            fareDetailList.add(b.getFare());
        }
        for (FareDetail f:fareDetailList) {
            flightDetailList.add(f.getFlightNumber());
        }

        int sizeofbookingsList=bookings.size();
        int sizeofbookingtimelist=bookingTime.size();
        int sizeofuserlist=userDetailsList.size();
        int sizeofflightlist=flightDetailList.size();
        int sizeoffaredetaillist=fareDetailList.size();

        System.out.println("list size= "+ sizeofbookingtimelist+sizeofbookingsList+sizeofuserlist+sizeofflightlist+sizeoffaredetaillist);

        for (int i=0;i<sizeofbookingsList;i++){
            ShowBookingsDTO showBookingsDTO=new ShowBookingsDTO();
            showBookingsDTO.setAirLine(flightDetailList.get(i).getAirline());
            showBookingsDTO.setDapartureTime(flightDetailList.get(i).getDepartTime());
            showBookingsDTO.setArrivalTime(flightDetailList.get(i).getArriveTime());
            showBookingsDTO.setDuration(flightDetailList.get(i).getDuration());
            showBookingsDTO.setSource(flightDetailList.get(i).getSource());
            showBookingsDTO.setDestination(flightDetailList.get(i).getDestination());
            showBookingsDTO.setFare(fareDetailList.get(i).getFare());
            showBookingsDTO.setClassType(fareDetailList.get(i).getClassType());
            showBookingsDTO.setBookingTime(bookings.get(i).getBookingTime());
            if (i<sizeofuserlist){
                showBookingsDTO.setUserName((userDetailsList.get(i).getFirstName())+(userDetailsList.get(i).getLastName()));
                showBookingsDTO.setEmail(userDetailsList.get(i).getEmailId());
                showBookingsDTO.setPhoneNumber(userDetailsList.get(i).getPhoneNumber());
                showBookingsDTO.setGender(userDetailsList.get(i).getGender());
            }else {
                showBookingsDTO.setUserName((userDetailsList.get(0).getFirstName())+(userDetailsList.get(0).getLastName()));
                showBookingsDTO.setEmail(userDetailsList.get(0).getEmailId());
                showBookingsDTO.setPhoneNumber(userDetailsList.get(0).getPhoneNumber());
                showBookingsDTO.setGender(userDetailsList.get(0).getGender());
            }
            showBookingsDTOList.add(showBookingsDTO);
        }

        return showBookingsDTOList;
    }

    //METHODS TO FIND BOOKINGS BY FLIGHT NUMBER
    @Override
    public List<ShowBookingsDTO> findBookingsByFlightNumber(Integer flightNumber,Integer pageNumber,Integer pageSize,String sortBy) {
        Pageable paging = null;
        if (sortBy.toLowerCase().equals("fare")){
            paging= PageRequest.of(pageNumber,pageSize, Sort.by("fare"));
        } else if (sortBy.toLowerCase().equals("bookingtime")) {
            paging= PageRequest.of(pageNumber,pageSize, Sort.by("bookingTime"));
        }else {
            paging= PageRequest.of(pageNumber,pageSize, Sort.by("fare"));
        }
        List<ShowBookingsDTO> showBookingsDTOList=new ArrayList<>();
        List<FareDetail> fareDetailList=new ArrayList<>();
        List<FlightDetail> flightDetailList=new ArrayList<>();
        List<UserDetails> userDetailsList=new ArrayList<>();
        List<BookingDetails> bookings=new ArrayList<>();


        System.out.println("after list declaration");
        flightDetailList.add(flightDetailService.findById(flightNumber));
        for (FlightDetail fl:flightDetailList) {
            fareDetailList.addAll(fareDetailService.findByFlightNumber(fl));
        }
        System.out.println("after 1st for loop");
        System.out.println("farelist= "+fareDetailList);


        System.out.println("after 2nd for loop");
        for (FareDetail f:fareDetailList) {
            System.out.println("inside 2nd for loop");
            bookings.addAll(bookingDetailsRepository.findByFare(f,paging));
        }

        System.out.println("after 3rd for loop");
        for (BookingDetails b:bookings) {
            userDetailsList.add(b.getUser());
        }


        int sizeofbookingsList=bookings.size();

        int sizeofuserlist=userDetailsList.size();
        int sizeofflightlist=flightDetailList.size();
        int sizeoffaredetaillist=fareDetailList.size();

        System.out.println("list size= "+sizeofbookingsList+sizeofuserlist+sizeofflightlist+sizeoffaredetaillist);

        for (int i=0;i<sizeofbookingsList;i++){
            ShowBookingsDTO showBookingsDTO=new ShowBookingsDTO();
            showBookingsDTO.setAirLine(flightDetailList.get(0).getAirline());
            showBookingsDTO.setDapartureTime(flightDetailList.get(0).getDepartTime());
            showBookingsDTO.setArrivalTime(flightDetailList.get(0).getArriveTime());
            showBookingsDTO.setDuration(flightDetailList.get(0).getDuration());
            showBookingsDTO.setSource(flightDetailList.get(0).getSource());
            showBookingsDTO.setDestination(flightDetailList.get(0).getDestination());
            //FARE DETAILS LIST
            showBookingsDTO.setFare(bookings.get(i).getFare().getFare());
            showBookingsDTO.setClassType(bookings.get(i).getFare().getClassType());
            showBookingsDTO.setBookingTime(bookings.get(i).getBookingTime());
            if (i<sizeofuserlist){
                showBookingsDTO.setUserName((userDetailsList.get(i).getFirstName())+(userDetailsList.get(i).getLastName()));
                showBookingsDTO.setEmail(userDetailsList.get(i).getEmailId());
                showBookingsDTO.setPhoneNumber(userDetailsList.get(i).getPhoneNumber());
                showBookingsDTO.setGender(userDetailsList.get(i).getGender());
            }else {
                showBookingsDTO.setUserName((userDetailsList.get(0).getFirstName())+(userDetailsList.get(0).getLastName()));
                showBookingsDTO.setEmail(userDetailsList.get(0).getEmailId());
                showBookingsDTO.setPhoneNumber(userDetailsList.get(0).getPhoneNumber());
                showBookingsDTO.setGender(userDetailsList.get(0).getGender());
            }
            showBookingsDTOList.add(showBookingsDTO);
        }

        return showBookingsDTOList;
    }


}

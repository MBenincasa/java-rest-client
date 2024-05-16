package io.github.mbenincasa.javarestclient.client.response.booking;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "created-booking")
public class BookingPostResponse {

    @JacksonXmlProperty(localName = "bookingid")
    private int bookingId;

    @JacksonXmlProperty(localName = "booking")
    private BookingPostResponseBooking booking;

    public BookingPostResponse() {
    }

    public BookingPostResponse(int bookingId, BookingPostResponseBooking booking) {
        this.bookingId = bookingId;
        this.booking = booking;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public BookingPostResponseBooking getBooking() {
        return booking;
    }

    public void setBooking(BookingPostResponseBooking booking) {
        this.booking = booking;
    }

    @Override
    public String toString() {
        return "BookingPostResponse{" +
                "bookingId=" + bookingId +
                ", booking=" + booking +
                '}';
    }

    @JacksonXmlRootElement(localName = "booking")
    public static class BookingPostResponseBooking {

        @JacksonXmlProperty(localName = "firstname")
        private String firstName;

        @JacksonXmlProperty(localName = "lastname")
        private String lastName;

        @JacksonXmlProperty(localName = "totalprice")
        private int totalPrice;

        @JacksonXmlProperty(localName = "depositpaid")
        private boolean depositPaid;

        @JacksonXmlProperty(localName = "bookingdates")
        private BookingPostResponseBookingDates bookingDates;

        @JacksonXmlProperty(localName = "additionalneeds")
        private String additionalNeeds;

        public BookingPostResponseBooking() {
        }

        public BookingPostResponseBooking(String firstName, String lastName, int totalPrice, boolean depositPaid, BookingPostResponseBookingDates bookingDates, String additionalNeeds) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.totalPrice = totalPrice;
            this.depositPaid = depositPaid;
            this.bookingDates = bookingDates;
            this.additionalNeeds = additionalNeeds;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public boolean isDepositPaid() {
            return depositPaid;
        }

        public void setDepositPaid(boolean depositPaid) {
            this.depositPaid = depositPaid;
        }

        public BookingPostResponseBookingDates getBookingDates() {
            return bookingDates;
        }

        public void setBookingDates(BookingPostResponseBookingDates bookingDates) {
            this.bookingDates = bookingDates;
        }

        public String getAdditionalNeeds() {
            return additionalNeeds;
        }

        public void setAdditionalNeeds(String additionalNeeds) {
            this.additionalNeeds = additionalNeeds;
        }

        @Override
        public String toString() {
            return "BookingPostResponseBooking{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    ", totalPrice=" + totalPrice +
                    ", depositPaid=" + depositPaid +
                    ", bookingDates=" + bookingDates +
                    ", additionalNeeds='" + additionalNeeds + '\'' +
                    '}';
        }

        @JacksonXmlRootElement(localName = "bookingdates")
        public static class BookingPostResponseBookingDates {

            @JacksonXmlProperty(localName = "checkin")
            private String checkIn;

            @JacksonXmlProperty(localName = "checkout")
            private String checkOut;

            public BookingPostResponseBookingDates() {
            }

            public BookingPostResponseBookingDates(String checkIn, String checkOut) {
                this.checkIn = checkIn;
                this.checkOut = checkOut;
            }

            public String getCheckIn() {
                return checkIn;
            }

            public void setCheckIn(String checkIn) {
                this.checkIn = checkIn;
            }

            public String getCheckOut() {
                return checkOut;
            }

            public void setCheckOut(String checkOut) {
                this.checkOut = checkOut;
            }

            @Override
            public String toString() {
                return "BookingPostResponseBookingDates{" +
                        "checkIn='" + checkIn + '\'' +
                        ", checkOut='" + checkOut + '\'' +
                        '}';
            }
        }
    }
}

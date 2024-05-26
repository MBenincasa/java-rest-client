package io.github.mbenincasa.javarestclient.client.response.booking;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "booking")
public class BookingGetResponse {

    @JacksonXmlProperty(localName = "firstname")
    private String firstname;

    @JacksonXmlProperty(localName = "lastname")
    private String lastname;

    @JacksonXmlProperty(localName = "totalprice")
    private int totalprice;

    @JacksonXmlProperty(localName = "depositpaid")
    private boolean depositpaid;

    @JacksonXmlProperty(localName = "bookingdates")
    private BookingGetResponseDates bookingdates;

    @JacksonXmlProperty(localName = "additionalneeds")
    private String additionalneeds;

    public BookingGetResponse() {
    }

    public BookingGetResponse(String firstname, String lastname, int totalprice, boolean depositpaid, BookingGetResponseDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(int totalprice) {
        this.totalprice = totalprice;
    }

    public boolean isDepositpaid() {
        return depositpaid;
    }

    public void setDepositpaid(boolean depositpaid) {
        this.depositpaid = depositpaid;
    }

    public BookingGetResponseDates getBookingdates() {
        return bookingdates;
    }

    public void setBookingdates(BookingGetResponseDates bookingdates) {
        this.bookingdates = bookingdates;
    }

    public String getAdditionalneeds() {
        return additionalneeds;
    }

    public void setAdditionalneeds(String additionalneeds) {
        this.additionalneeds = additionalneeds;
    }

    @Override
    public String toString() {
        return "BookingGetResponse{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", totalprice=" + totalprice +
                ", depositpaid=" + depositpaid +
                ", bookingdates=" + bookingdates +
                ", additionalneeds='" + additionalneeds + '\'' +
                '}';
    }

    @JacksonXmlRootElement(localName = "bookingdates")
    public static class BookingGetResponseDates {

        @JacksonXmlProperty(localName = "checkin")
        private String checkin;

        @JacksonXmlProperty(localName = "checkout")
        private String checkout;

        public BookingGetResponseDates() {
        }

        public BookingGetResponseDates(String checkin, String checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }

        public String getCheckin() {
            return checkin;
        }

        public void setCheckin(String checkin) {
            this.checkin = checkin;
        }

        public String getCheckout() {
            return checkout;
        }

        public void setCheckout(String checkout) {
            this.checkout = checkout;
        }

        @Override
        public String toString() {
            return "BookingGetResponseDates{" +
                    "checkin='" + checkin + '\'' +
                    ", checkout='" + checkout + '\'' +
                    '}';
        }
    }
}

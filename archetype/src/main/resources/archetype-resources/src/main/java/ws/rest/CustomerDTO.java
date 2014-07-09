#set( $symbol_pound = '#' )
#set( $symbol_dollar = '$' )
#set( $symbol_escape = '\' )
package ${package}.ws.rest;

/**
 * Customer data object. Used to return customer information through REST
 * request. Spring handles data conversion from DTO to JSON.
 * 
 * @author Ignas
 * 
 */
public class CustomerDTO {
    
    /** Customer name. */
    private String name;

    /** Customer's city name. */
    private String cityName;

    /** Customer address. */
    private String address;

    /** Customer email. */
    private String email;

    /** Customer phone number. */
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}

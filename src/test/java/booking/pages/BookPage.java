package booking.pages;

import booking.utility.BrowserUtil;
import booking.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class BookPage {
    public BookPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "//*[contains(@class, 'coi-banner__accept')]")
    public WebElement acceptCookies;

    @FindBy(id = "cb_js_geo_location_dropdown")
    public  List<WebElement> allLocationsDropdown;

    @FindBy(id = "cb_accommodationtype")
    public List <WebElement> accomodationTypeDropdown;

    @FindBy(id = "Citybreak_trigger_from")
    public WebElement checkInDateBox;

    @FindBy(id = "Citybreak_trigger_to")
    public WebElement checkOutDateBox;

    @FindBy(id = "cb-ui-datepicker-div")
    public  WebElement datePickerTable;

    @FindBy(id = "cb_nodates")
    public WebElement noSpecificDatesCheckbox;

    @FindBy(id = "cb_numrooms")
    public List<WebElement> numberOfRoomsDropdown;

    @FindBy(id = "cb_numadults1")
    public List<WebElement> adultGuestsDropdown;

    @FindBy(id = "CB_SearchButton")
    public WebElement searchButton;

    @FindBy(id = "Citybreak_bookingdetails")
    public WebElement bookingDetails;

    @FindBy(xpath = "//div[@class='cb-pt']//tbody")
    public WebElement chartDetails;

    @FindBy ( xpath = "//span[.='Remove']")
    public WebElement removeButton;

    @FindBy(xpath = "//*[contains(@class, 'Citybreak_AccInfoBasic hproduct')]")
    public List<WebElement> hotelsList;

    @FindBy(xpath = "//div[contains(@class, 'Citybreak_BookAlt')]")
    public List<WebElement> hotelsSubList;

    public void selectDatePicker(LocalDate date)
    {
        int getDate = date.getDayOfMonth();
        String dateFormat = String.format("%02d", getDate); // Format to always have two digits


        List<WebElement> rows = datePickerTable.findElements(By.tagName("tr"));

        boolean dateFound = false;
        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                String cellText = cell.getText();
                if (cellText.equals(dateFormat)) {
                    cell.click();
                    dateFound = true;
                    break;
                }
            }
            if (dateFound) break;
        }
    }

    public void bookingSearch(LocalDate checkInDate, LocalDate checkOutDate)
    {
        checkInDateBox.click();
        selectDatePicker(checkInDate);
        checkOutDateBox.click();
        selectDatePicker(checkOutDate);
        searchButton.click();
    }


    public List<String> getBookingDetails()
    {
        List<WebElement> bookingDetailsItems = bookingDetails.findElements(By.tagName("li"));

        return bookingDetailsItems.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public void selectHotel(int hotelItem, int hotelSubitem)
    {
        BrowserUtil.waitFor(5);
        hotelsList.get(hotelItem).findElement(By.partialLinkText("Book now")).click();

        BrowserUtil.waitFor(5);
        hotelsSubList.get(hotelSubitem).findElement(By.xpath("//tbody//td[contains(@class, 'cb_choose')]//*[contains(@value, 'Book')]")).click();}


}
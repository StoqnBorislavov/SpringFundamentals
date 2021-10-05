package softuni.mobile.model.view;

import softuni.mobile.model.enums.EngineEnum;
import softuni.mobile.model.enums.TransmissionEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class OfferSummaryView {


    private String description;

    private EngineEnum engine;

    private String imageUrl;

    private int mileage;

    private int price;

    private TransmissionEnum transmission;

    private int year;

    private String model;

    private Long id;

    public OfferSummaryView() {
    }

    public Long getId() {
        return id;
    }

    public OfferSummaryView setId(Long id) {
        this.id = id;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public OfferSummaryView setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferSummaryView setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferSummaryView setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public int getMileage() {
        return mileage;
    }

    public OfferSummaryView setMileage(int mileage) {
        this.mileage = mileage;
        return this;
    }

    public int getPrice() {
        return price;
    }

    public OfferSummaryView setPrice(int price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferSummaryView setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public int getYear() {
        return year;
    }

    public OfferSummaryView setYear(int year) {
        this.year = year;
        return this;
    }

    public String getModel() {
        return model;
    }

    public OfferSummaryView setModel(String model) {
        this.model = model;
        return this;
    }
}
package softuni.mobile.model.service;


import softuni.mobile.model.enums.EngineEnum;
import softuni.mobile.model.enums.TransmissionEnum;

public class OfferUpdateServiceModel {
    private String description;

    private EngineEnum engine;

    private Integer mileage;

    private Integer price;

    private TransmissionEnum transmission;

    private Integer year;

    private Long id;

    private String imageUrl;

    public OfferUpdateServiceModel() {
    }

    public String getDescription() {
        return description;
    }

    public OfferUpdateServiceModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferUpdateServiceModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferUpdateServiceModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OfferUpdateServiceModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferUpdateServiceModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferUpdateServiceModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferUpdateServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferUpdateServiceModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}


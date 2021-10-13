package softuni.mobile.model.binding;

import softuni.mobile.model.enums.EngineEnum;
import softuni.mobile.model.enums.TransmissionEnum;

import javax.validation.constraints.*;

public class OfferUpdateBindingModel {


    @NotBlank
    private String description;

    @NotNull
    private EngineEnum engine;

    @NotNull
    @PositiveOrZero
    private Integer mileage;

    @NotNull
    @Min(100)
    private Integer price;

    @NotNull
    private TransmissionEnum transmission;

    @NotNull
    @Min(1930)
    private Integer year;

    private Long id;

    @NotBlank
    private String imageUrl;

    public OfferUpdateBindingModel() {
    }

    public String getDescription() {
        return description;
    }

    public OfferUpdateBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferUpdateBindingModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferUpdateBindingModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public Integer getPrice() {
        return price;
    }

    public OfferUpdateBindingModel setPrice(Integer price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferUpdateBindingModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferUpdateBindingModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OfferUpdateBindingModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferUpdateBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}

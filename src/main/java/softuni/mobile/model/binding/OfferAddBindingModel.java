package softuni.mobile.model.binding;

import softuni.mobile.model.enums.EngineEnum;
import softuni.mobile.model.enums.TransmissionEnum;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class OfferAddBindingModel {

    @NotNull
    private Long modelId;

    @NotEmpty
    private String description;

    @NotNull
    private EngineEnum engine;

    @NotNull
    @PositiveOrZero
    private Integer mileage;

    @NotNull
    @DecimalMin("100")
    private BigDecimal price;

    @NotNull
    private TransmissionEnum transmission;

    @NotNull
    @Min(1930)
    private Integer year;

    @NotEmpty
    private String imageUrl;

    public OfferAddBindingModel() {
    }

    public Long getModelId() {
        return modelId;
    }

    public OfferAddBindingModel setModelId(Long modelId) {
        this.modelId = modelId;
        return this;
    }


    public String getDescription() {
        return description;
    }

    public OfferAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public EngineEnum getEngine() {
        return engine;
    }

    public OfferAddBindingModel setEngine(EngineEnum engine) {
        this.engine = engine;
        return this;
    }

    public Integer getMileage() {
        return mileage;
    }

    public OfferAddBindingModel setMileage(Integer mileage) {
        this.mileage = mileage;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public OfferAddBindingModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public TransmissionEnum getTransmission() {
        return transmission;
    }

    public OfferAddBindingModel setTransmission(TransmissionEnum transmission) {
        this.transmission = transmission;
        return this;
    }

    public Integer getYear() {
        return year;
    }

    public OfferAddBindingModel setYear(Integer year) {
        this.year = year;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public OfferAddBindingModel setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }
}

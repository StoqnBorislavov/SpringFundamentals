package softuni.mobile.model.view;

import java.util.ArrayList;
import java.util.List;

public class BrandViewModel {

    private String name;
    private List<ModelViewModel> models = new ArrayList<>();

    public BrandViewModel() {
    }

    public String getName() {
        return name;
    }

    public BrandViewModel setName(String name) {
        this.name = name;
        return this;
    }

    public List<ModelViewModel> getModels() {
        return models;
    }

    public BrandViewModel setModels(List<ModelViewModel> models) {
        this.models = models;
        return this;
    }
}

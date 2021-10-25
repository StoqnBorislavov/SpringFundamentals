package softuni.mobile.service;

import softuni.mobile.model.entity.Model;

import java.util.List;

public interface ModelService {
    void initializeModels();
    Model findById(Long id);
    List<Model> findAllModels();


}

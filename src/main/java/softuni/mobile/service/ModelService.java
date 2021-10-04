package softuni.mobile.service;

import softuni.mobile.model.entity.Model;

public interface ModelService {
    void initializeModels();
    Model findById(Long id);
}

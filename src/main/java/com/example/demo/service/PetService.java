package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Pet;
import com.example.demo.repository.PetRepository;

@Service
public class PetService {
    
    @Autowired
    private PetRepository petRepository;
    
    public Pet addPet(Pet pet) {
        return petRepository.save(pet);
    }
    
    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }
    
    public List<Pet> getAvailablePets() {
        return petRepository.findByAdoptedFalse();
    }
    
    public List<Pet> getAdoptedPets() {
        return petRepository.findByAdoptedTrue();
    }
    
    public Optional<Pet> getPetById(Long id) {
        return petRepository.findById(id);
    }
    
    public Pet markAsAdopted(Long id) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            Pet adoptedPet = pet.get();
            adoptedPet.setAdopted(true);
            return petRepository.save(adoptedPet);
        }
        return null;
    }
    
    public void deletePet(Long id) {
        petRepository.deleteById(id);
    }
    
    public Pet updatePet(Long id, Pet petDetails) {
        Optional<Pet> pet = petRepository.findById(id);
        if (pet.isPresent()) {
            Pet existingPet = pet.get();
            existingPet.setName(petDetails.getName());
            existingPet.setBreed(petDetails.getBreed());
            existingPet.setAge(petDetails.getAge());
            existingPet.setColor(petDetails.getColor());
            existingPet.setNotes(petDetails.getNotes());
            return petRepository.save(existingPet);
        }
        return null;
    }
}

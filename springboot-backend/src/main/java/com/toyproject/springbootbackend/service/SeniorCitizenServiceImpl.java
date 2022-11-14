package com.toyproject.springbootbackend.service;

import com.toyproject.springbootbackend.model.SeniorCitizen;
import com.toyproject.springbootbackend.repository.SeniorCitizenRepository;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.util.List;

@Service
public class SeniorCitizenServiceImpl implements SeniorCitizenService{

    private SeniorCitizenRepository seniorCitizenRepository;

    public SeniorCitizenServiceImpl(SeniorCitizenRepository seniorCitizenRepository) {
        super();
        this.seniorCitizenRepository = seniorCitizenRepository;
    }

    @Override
    public List<SeniorCitizen> getAllSeniorCitizens() {
        return seniorCitizenRepository.findAll(Sort.by(Sort.Direction.ASC, "safetyLevel"));
    }


    @Override
    public SeniorCitizen saveSeniorCitizen(SeniorCitizen seniorCitizen) {
        return seniorCitizenRepository.save(seniorCitizen);
    }

    @Override
    public SeniorCitizen getSeniorCitizenById(Integer id) {
        return seniorCitizenRepository.findById(id).get();
    }

    @Override
    public SeniorCitizen findSeniorByEmail(String email) {
        return seniorCitizenRepository.findByEmail(email);
    }

    @Override
    public SeniorCitizen findSeniorByAge(Integer age) {
        return seniorCitizenRepository.findByAge(age);
    }

    @Override
    public SeniorCitizen updateSeniorCitizen(SeniorCitizen student) {
        return seniorCitizenRepository.save(student);
    }

    @Override
    public void deleteSeniorCitizenById(Integer id) {
        seniorCitizenRepository.deleteById(id);
    }
}
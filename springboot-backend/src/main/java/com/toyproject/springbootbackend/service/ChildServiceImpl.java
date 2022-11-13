package com.toyproject.springbootbackend.service;

import com.toyproject.springbootbackend.model.Child;
import com.toyproject.springbootbackend.repository.ChildRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildServiceImpl implements ChildService{
    @Autowired
    private ChildRepository childRepository;

    public ChildServiceImpl(ChildRepository childRepository) {
        super();
        this.childRepository = childRepository;
    }

    @Override
    public List<Child> getAllChildren() {
        return childRepository.findAll();
    }

    @Override
    public Child saveChild(Child child) {
        return childRepository.save(child);
    }

    @Override
    public Child getChildById(Integer id) {
        return childRepository.findById(id).get();
    }

    @Override
    public Child findChildByEmail(String email) { return childRepository.findByEmail(email);}

    @Override
    public Child findChildByAge(Integer age) {
        return childRepository.findByAge(age);
    }

    @Override
    public Child updateChild(Child child) {
        return childRepository.save(child);
    }

    @Override
    public void deleteChildById(Integer id) {
        childRepository.deleteById(id);
    }
}

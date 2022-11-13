package com.toyproject.springbootbackend.service;
import com.toyproject.springbootbackend.model.Child;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ChildService {

    List<Child> getAllChildren();

    Child saveChild(Child Child);

    Child getChildById(Integer id);

    Child  findChildByEmail(String email);

    Child findChildByAge(Integer age);

    Child updateChild(Child Child);

    void deleteChildById(Integer id);
}
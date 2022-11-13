package com.toyproject.springbootbackend;

import com.toyproject.springbootbackend.controller.ChildController;
import com.toyproject.springbootbackend.model.Child;
import com.toyproject.springbootbackend.service.ChildService;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class ChildUtilTests {

    private ChildService childService;
    @Test
    public void testAge () {
//        Child child = new Child("test","child12","2727272727","testchild12@gmail.com","testchild12",15);
//        System.out.print(child.toString());

    }

    @Test
    public void testFetchChildNames () {
        ChildController controller1 = new ChildController();
        ChildService child1;

    }
}

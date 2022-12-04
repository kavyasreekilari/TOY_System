package com.toyproject.springbootbackend.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "activityschedule")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ActivitySchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="activityschedule_id")
    private Integer activityschedule_id;

    @Column(name = "name")
    private String Name = "Book Reading";

    @Column(name = "description")
    private String Description = "None";

    @Column(name="childName")
    private String childName;

    @Column(name="seniorName")
    private String seniorName;

    @Column(name="schedule_date")
    private String date;

    @Column(name="confirmed")
    private String confirmed;

    @Column(name="starttime")
    private String startTime;

    @Column(name="endtime")
    private String endTime;

}

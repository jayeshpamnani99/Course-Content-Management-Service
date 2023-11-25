package com.example.coursecontentmanagementservice.Model;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="course_module")
public class CourseModule
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer id;
    private Integer courseId;
    private Integer serialNumber;
    private String title;


    private String description;
    private String contentUrl;

    private Integer moduleTypeId;

    @Transient
    private ModuleType moduleType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(Integer serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public Integer getModuleTypeId() {
        return moduleTypeId;
    }

    public void setModuleTypeId(Integer moduleTypeId) {
        this.moduleTypeId = moduleTypeId;
    }

    public ModuleType getModuleType() {
        return moduleType;
    }

    public void setModuleType(ModuleType moduleType) {
        this.moduleType = moduleType;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }


    @Override
    public String toString() {
        return "CourseModule{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", serialNumber=" + serialNumber +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", contentUrl='" + contentUrl + '\'' +
                ", moduleTypeId=" + moduleTypeId +
                ", moduleType=" + moduleType +
                '}';
    }
}

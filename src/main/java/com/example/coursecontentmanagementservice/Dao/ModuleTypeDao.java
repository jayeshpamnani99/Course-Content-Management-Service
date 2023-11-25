package com.example.coursecontentmanagementservice.Dao;

import com.example.coursecontentmanagementservice.Model.ModuleType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleTypeDao extends CrudRepository<ModuleType, Integer>
{

}

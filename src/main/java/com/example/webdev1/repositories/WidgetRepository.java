package com.example.webdev1.repositories;

import org.springframework.data.repository.CrudRepository;

import com.example.webdev1.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {

}

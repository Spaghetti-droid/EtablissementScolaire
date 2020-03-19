package com.fr.adaming.dao;

import org.springframework.data.jpa.repository.JpaRepository;

public interface IDao<E> extends JpaRepository<E,Integer> {

}

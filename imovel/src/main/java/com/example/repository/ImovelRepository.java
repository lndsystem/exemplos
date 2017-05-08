package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.model.Imovel;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {

}

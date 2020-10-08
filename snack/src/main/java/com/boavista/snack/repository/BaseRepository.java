package com.boavista.snack.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<E> extends CrudRepository<E, Long> {

	public Optional<E> findById(Long id);

	public E getById(Long id);

	public <S extends E> S save(S entity);

	public List<E> findAll();

}

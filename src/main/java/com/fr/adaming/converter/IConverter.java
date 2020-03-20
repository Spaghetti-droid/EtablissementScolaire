package com.fr.adaming.converter;

import java.util.List;

public interface IConverter<C, U, E> {
	
	public E convertCreateDtoToEntity(C createDto);
	
	public C convertEntityToCreateDto(E entity);
	
	public E convertUpdateDtoToEntity(U updateDto);
	
	public U convertEntityToUpdateDto(E entity);
	
	public List<E> convertListCreateDtoToEntity(List<C> listeCreateDto);
	
	public List<C> convertListEntityToCreateDto(List<E> listeEntity);
	
	public List<E> convertListUpdateDtoToEntity(List<U> listeUpdateDto);
	
	public List<U> convertListEntityToUpdateDto(List<E> listeEntity);
	
}

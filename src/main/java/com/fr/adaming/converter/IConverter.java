package com.fr.adaming.converter;

import java.util.List;

/**
 * Interface IConverter implementant les classes Converter de chaque entite
 * @author Isaline
 *
 * @param <C> objet CreateDto de l'entite consideree
 * @param <U> objet UpdateDto de l'entite consideree
 * @param <E> entite consideree
 */
public interface IConverter<C, U, E> {
	
	/**
	 * Methode permettant de convertir un objet CreateDto en l'objet Entite considere
	 * @param createDto objet CreateDto a convertir
	 * @return resultat de la conversion (objet Entite ou objet null)
	 */
	public E convertCreateDtoToEntity(C createDto);
	
	/**
	 * Methode permettant de convertir un objet Entite en l'objet CreateDto considere
	 * @param entity objet Entite a convertir
	 * @return resultat de la conversion (objet CreateDto ou objet null)
	 */
	public C convertEntityToCreateDto(E entity);
	
	/**
	 * Methode permettant de convertir un objet UpdateDto en l'objet Entite considere
	 * @param updateDto objet UpdateDto a convertir
	 * @return resultat de la conversion (objet Entite ou objet null)
	 */
	public E convertUpdateDtoToEntity(U updateDto);
	
	/**
	 * Methode permettant de convertir un objet Entite en l'objet UpdateDto considere
	 * @param entity objet Entite a convertir
	 * @return resultat de la conversion (objet UpdateDto ou objet null)
	 */
	public U convertEntityToUpdateDto(E entity);
	
	/**
	 * Methode permettant de convertir une liste d'objets CreateDto en une liste d'objets Entite consideres
	 * @param listeCreateDto objet liste d'objets CreateDto a convertir
	 * @return resultat de la conversion (liste d'objets Entite, pouvant etre vide)
	 */
	public List<E> convertListCreateDtoToEntity(List<C> listeCreateDto);
	
	/**
	 * Methode permettant de convertir une liste d'objets Entite en une liste d'objets CreateDto consideres
	 * @param listeEntity objet liste d'objets Entite a convertir
	 * @return resultat de la conversion (liste d'objets CreateDto, pouvant etre vide)
	 */
	public List<C> convertListEntityToCreateDto(List<E> listeEntity);
	
	/**
	 * Methode permettant de convetir une liste d'objets UpdateDto en une liste d'objets Entite consideres
	 * @param listeUpdateDto objet liste d'objets UpdateDto a convertir
	 * @return resultat de la conversion (liste d'objets Entite, pouvant etre vide)
	 */
	public List<E> convertListUpdateDtoToEntity(List<U> listeUpdateDto);
	
	/**
	 * Methode permettant de convertir une liste d'objets Entite en une liste d'objets UpdateDto consideres
	 * @param listeEntity objet liste d'objets Entite a convertir
	 * @return resultat de la conversion (liste d'objets UpdateDto, pouvant etre vide)
	 */
	public List<U> convertListEntityToUpdateDto(List<E> listeEntity);
	
}

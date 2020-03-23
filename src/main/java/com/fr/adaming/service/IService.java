package com.fr.adaming.service;

import java.util.List;

/**
 * <p>IService représente les méthodes des classes Services</p>
 * 
 * @author Grégoire Brebner
 *
 * @param E l'entité
 */
public interface IService<E> {

	/**
	 * <p>Cette methode create permet la création d'une entite</p>
	 * 
	 * @param entity L'entité à créer
	 * @return Une entite
	 */
	public E create(E entity);

	/**
	 * <p>Methode permettant l'affichage de la liste de toute les entites</p>
	 * 
	 * @return Une liste d'entité
	 */
	public List<E> readAll();

	/**
	 * <p>Methode permettant l'affichage d'une entite par son ID</p>
	 * 
	 * @param id Id de l'entite
	 * @return Une entite
	 */
	public E readById(Integer id);

	/**
	 * <p>Methode permettant la connaissance de l'existence d'une entite par son
	 * ID</p>
	 * 
	 * @param id Id de l'entite
	 * @return VRAI(entite existente) ou FAUX(entite non-existente)
	 */
	public boolean existsById(Integer id);

	/**
	 * <p>Methode permettant la suppression d'une entite par son ID</p>
	 * 
	 * @param id Id de l'entite à supprimer
	 * @return VRAI (supression effectue) ou FAUX (supression non-effectue)
	 */
	public boolean deleteById(Integer id);

	/**
	 * <p>Methode permettant la modification d'une entite </p>
	 * 
	 * @param entite Entite à modifier
	 * @return VRAI(modification effectue) ou FAUX (modification non efffectue)
	 */
	public boolean update(E entite);

}

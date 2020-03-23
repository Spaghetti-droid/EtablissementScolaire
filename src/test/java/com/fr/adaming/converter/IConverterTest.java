package com.fr.adaming.converter;

/**
 * @author Lea
 * 
 * <b>Description : </b>
 * <p>Interface pour les tests sur les Converter, implemente par toutes les classes converter test</p>
 *
 */
public interface IConverterTest {

	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une CreateDto en Entite</p>
	 */
	public void testConvertingCreateDtoToEntity();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une CreateDto Null en Entite, doit retourner null</p>
	 */
	public void testConvertingNullCreateDto_shouldReturnNullEntity();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une UpdateDto en Entite</p>
	 */
	public void testConvertingUpdateDtoToEntity();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une UpdateDto Null en Entite, doit retourner null</p>
	 */
	public void testConvertingNullUpdateDto_shouldReturnNullEntity();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Entite en CreateDto</p>
	 */
	public void testConvertingEntityToCreateDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Entite Null en CreateDto, doit retourner null</p>
	 */
	public void testConvertingNullEntity_shouldReturnNullCreateDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Entite en UpdateDto</p>
	 */
	public void testConvertingEntityToUpdateDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Entite Null en UpdateDto, doit retourner null</p>
	 */
	public void testConvertingNullEntity_shouldReturnNullUpdateDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste d'Entite en Liste de CreateDto</p>
	 */
	public void testConvertingListEntityToCreateDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste d'Entite Null en Liste de CreateDto, doit retourner une liste vide</p>
	 */
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste de CreateDto en Liste d'Entite</p>
	 */
	public void testConvertingListCreateDtoToEntity ();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste de CreateDto Null en Liste d'Entite, doit retourner une liste vide</p>
	 */
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() ;
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste d'Entite en Liste de CreateDto</p>
	 */
	public void testConvertingListEntityToUpdateDto();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste d'Entite Null en Liste de UpdateDto, doit retourner une liste vide</p>
	 */
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste de UpdateDto en Liste d'Entite, doit retourner une liste vide</p>
	 */
	public void testConvertingListUpdateDtoToEntity ();
	
	/**
	 * <b>Description : </b>
	 * <p>Methode pour convertir une Liste de UpdateDto Null en Liste d'Entite, doit retourner une liste vide</p>
	 */
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() ;
	
}

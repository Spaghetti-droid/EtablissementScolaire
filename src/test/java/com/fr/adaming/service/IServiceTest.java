package com.fr.adaming.service;

/**
 * Interface IServiceTest definissant les méthodes obligatoires dans les tests de la couche service
 * @author Lea
 *
 */

public interface IServiceTest {
	
	/**
	 * Methode permettant de tester la suppression d'un objet avec un Id existant dans la bd
	 *
	 * @return le resultat du test devrait etre positif
	 */
	public void testDeletingValidId_shouldReturnTrue();
	
	/**
	 * Methode permettant de tester la suppression d'un objet avec un Id invalide
	 *
	 * @return le resultat du test devrait etre negatif
	 */
	public void testDeletingInvalidId_shouldReturnFalse();
	
	/**
	 * Methode permettant de tester l'impression de tous les objets d'une table contenant des lignes
	 *
	 * @return le resultat du test devrait etre une liste d'objet
	 */
	public void testReadAllWithContent_shouldReturnList();
	
	/**
	 * Methode permettant de tester l'impression de tous les objets d'une table vide
	 *
	 * @return le resultat du test devrait etre une liste vide
	 */
	public void testReadAllNoContent_shouldReturnEmptyList();
	
	/**
	 * Methode permettant de tester la recherche par Id avec un parametre valide
	 *
	 * @return le resultat du test devrait etre un objet
	 */
	public void testReadByIdValidId_shouldReturnEntity();
	
	/**
	 * Methode permettant de tester la recherche par Id avec un parametre invalide
	 *
	 * @return le resultat du test devrait etre null
	 */
	public void testReadByIdInvalidId_shouldReturnNull();
	
	/**
	 * Methode permettant de tester l'existance ud'une ligne dans une table par id, avec un id valide
	 *
	 * @return le resultat du test devrait etre positif
	 */
	public void testExistsByIdValidId_ShouldReturnTrue();
	
	/**
	 * Methode permettant de tester l'existance ud'une ligne dans une table par id, avec un id non valide
	 *
	 * @return le resultat du test devrait etre negatif
	 */
	public void testExistsByIdInValidId_ShouldReturnFalse();
	
	

}

package com.fr.adaming.constant;

/**
 * <b>Classe permettant l'injection de constante pour la réponseDto</b>
 * 
 * @author Thierry-Meng Cloarec
 *
 */
public class WebMappingConstant {

	private WebMappingConstant webMapping = new WebMappingConstant();

	// CONSTANTES SUCCESS

	/**
	 * <b>Constante succes de la création de l'entite</b>
	 */
	public static final String SUCCESS_CREATE = "!succes de creation";

	/**
	 * <b>Constante succes de la modification de l'entite</b>
	 */
	public static final String SUCCESS_UPDATE = "!succes de modification";

	/**
	 * <b>Constante succes de la suppression de l'entite</b>
	 */
	public static final String SUCCESS_DELEDETE_BY_ID = "!succes de supression";

	/**
	 * <b>Constante succes de l'affichage par l'id de l'entite</b>
	 */
	public static final String SUCCESS_READ_BY_ID = "!succes de lecture";

	/**
	 * <b>Constante succes de l'affichage de la liste des entites</b>
	 */
	public static final String SUCCESS_READ_ALL = "!succes de la liste ";

	// CONSTANTES FAIL

	/**
	 * <b>Constante échec de la création de l'entite</b>
	 */
	public static final String FAIL_CREATE = "!erreur de creation";

	/**
	 * <b>Constante échec de la modification de l'entite</b>
	 */
	public static final String FAIL_UPDATE = "!erreur de modification";

	/**
	 * <b>Constante échec de la supression de l'entite</b>
	 */
	public static final String FAIL_DELEDETE = "!erreur de supression";

	/**
	 * <b>Constante échec de l'affichage par l'id de l'entite</b>
	 */
	public static final String FAIL_READ_BY_ID = "!erreur de lecture";

	/**
	 * <b>Constante échec de l'affichage de la liste des entites</b>
	 */
	public static final String FAIL_READ_ALL = "!erreur de liste";

	// CONSTANTE ETUDIANT

	/**
	 * <b>Constante succes de l'affichage des notes de l'etudiant</b>
	 */
	public static final String SUCCESS_NOTE_ETUDIANT = "succes note etudiant";

	/**
	 * <b>Constante succes de l'affichage des absences de l'etudiant</b>
	 */
	public static final String SUCCESS_ABSENCE_ETUDIANT = "succes absence etudiant";

	/**
	 * <b>Constante échec de l'affichage des notes de l'etudiant</b>
	 */
	public static final String FAIL_NOTE_ETUDIANT = "Fail note etudiant";

	/**
	 * <b>Constante échec de l'affichage des absences de l'etudiant</b>
	 */
	public static final String FAIL_ABSENCE_ETUDIANT = "Fail absence etudiant";

	// CONSTANTE MATIERE

	/**
	 * <b>Constante succes de l'affichage des examens par matiere </b>
	 */
	public static final String SUCCESS_EXAM_MATIERE = "Success exam matiere";

	/**
	 * <b>Constante échec de l'affichage des examens par matier</b>
	 */
	public static final String FAIL_EXAM_MATIERE = "Fail exam matiere";

}

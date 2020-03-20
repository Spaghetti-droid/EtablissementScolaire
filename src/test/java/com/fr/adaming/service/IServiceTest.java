package com.fr.adaming.service;

public interface IServiceTest {
	
	public void testDeletingValidId_shouldReturnTrue();
	
	public void testDeletingInvalidId_shouldReturnFalse();
	
	public void testReadAllWithContent_shouldReturnList();
	
	public void testReadAllNoContent_shouldReturnEmptyList();
	
	public void testReadByIdValidId_shouldReturnEntity();
	
	public void testReadByIdInvalidId_shouldReturnNull();
	
	public void testExistsByIdValidId_ShouldReturnTrue();
	
	public void testExistsByIdInValidId_ShouldReturnFalse();
	
	

}

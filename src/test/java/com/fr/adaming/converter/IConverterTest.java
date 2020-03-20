package com.fr.adaming.converter;

public interface IConverterTest {

	public void testConvertingCreateDtoToEntity();
	
	public void testConvertingNullCreateDto_shouldReturnNullEntity();
	
	public void testConvertingUpdateDtoToEntity();
	
	public void testConvertingNullUpdateDto_shouldReturnNullEntity();
	
	public void testConvertingEntityToCreateDto();
	
	public void testConvertingNullEntity_shouldReturnNullCreateDto();
	
	public void testConvertingEntityToUpdateDto();
	
	public void testConvertingNullEntity_shouldReturnNullUpdateDto();
	
	public void testConvertingListEntityToCreateDto();
	
	public void testConvertingNullListEntityToCreateDto_shouldReturnEmptyList();
	
	public void testConvertingListCreateDtoToEntity ();
	
	public void testConvertingNullListCreateDtoToEntity_shouldReturnEmptyList() ;
	
	public void testConvertingListEntityToUpdateDto();
	
	public void testConvertingNullListEntityToUpdateDto_shouldReturnEmptyList();
	
	public void testConvertingListUpdateDtoToEntity ();
	
	public void testConvertingNullListUpdateDtoToEntity_shouldReturnEmptyList() ;
	
}

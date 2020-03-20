package com.fr.adaming.controller;

public interface IControllerTest {

		
		public void testCreatingEntityWithValidBody_shouldReturnStatusOk();
		
		public void testCreatingEntityWithInvalidBody_shouldReturnBadStatus();
		
		public void testDeletingEntityWithValidId_shouldReturnStatusOk();
		
		public void testDeletingEntityWithInvalidId_shouldReturnBadStatus();
		
		public void testDeletingEntityWithNegativeId_shouldReturnBadStatus();
		
		public void testUpdatingEntityWithValidId_shouldReturnStatusOk();
		
		public void testUpdatingEntityWithInvalidId_shouldReturnBadStatus();
		
		public void testReadingEntityWithValidId_shouldReturnStatusOk();
		
		public void testReadingEntityWithInvalidId_shouldReturnBadStatus();
		
		public void testReadingEntityWithNegativeId_shouldReturnBadStatus();
		
		public void testReadingAllEntity_shouldReturnStatusOk();
		
		
}

package com.fr.adaming.converter;

import java.util.List;

public interface IConverter<CreateDto, UpdateDto, Entity> {
	
	public Entity convertCreateDtoToEntity(CreateDto createDto);
	
	public CreateDto convertEntityToCreateDto(Entity entity);
	
	public Entity convertUpdateDtoToEntity(UpdateDto updateDto);
	
	public UpdateDto convertEntityToUpdateDto(Entity entity);
	
	public List<Entity> convertListCreateDtoToEntity(List<CreateDto> listeCreateDto);
	
	public List<CreateDto> convertListEntityToCreateDto(List<Entity> listeEntity);
	
	public List<Entity> convertListUpdateDtoToEntity(List<UpdateDto> listeUpdateDto);
	
	public List<UpdateDto> convertListEntityToUpdateDto(List<Entity> listeEntity);
	
}

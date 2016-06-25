package it.polimi.awt.waterconsumer.repository;

import it.polimi.awt.waterconsumer.domain.Building;


public interface BuildingRepository{
	public Building findBuildingById(Integer id);
} 
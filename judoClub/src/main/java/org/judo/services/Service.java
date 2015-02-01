package org.judo.services;

import java.util.Date;
import java.util.LinkedList;

import org.judo.bean.Competition;
import org.judo.bean.Grade;

public class Service {

	public static LinkedList<Grade> getGrades()
	{
		LinkedList<Grade> grades = new LinkedList<Grade>();
		grades.add(new Grade("b","Blanche" ));
		grades.add(new Grade("J","Jaune" ));
		grades.add(new Grade("O","Orange" ));
		grades.add(new Grade("V","Verte" ));
		grades.add(new Grade("B","Bleue" ));
		grades.add(new Grade("M","Marron" ));
		
		grades.add(new Grade("1","Noire 1er Dan" ));
		grades.add(new Grade("2","Noire 2eme Dan" ));
		grades.add(new Grade("3","Noire 3eme Dan" ));
		grades.add(new Grade("4","Noire 4eme Dan" ));
		grades.add(new Grade("5","Noire 5eme Dan" ));
		grades.add(new Grade("6","Noire 6eme Dan" ));
		
		return grades ;
	}
	
	public static LinkedList<Competition> getCompetitions()
	{
		int id = 1 ;
		LinkedList<Competition> list = new LinkedList<Competition>();
		list.add(new Competition(id++, new Date(), "Open ", "Nantes", "Palais des sports", "44" ));
		list.add(new Competition(id++, new Date(), "Shiai", "Rennes", "Dojo régional", "35" ));
		list.add(new Competition(id++, new Date(), "Shiai", "Nantes", "Dojo du croissant", "44" ));
		list.add(new Competition(id++, new Date(), "Tournoi", "Lyon", "Halle des sports", "69" ));
		return list ;
	}
}

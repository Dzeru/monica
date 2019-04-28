package com.dzeru.monica.gymschedule.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity
public class GymLesson
{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private int day;
	private int lesson;
	private String note;
}

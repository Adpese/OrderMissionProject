package com.sopra.entity;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Sebastian on 09/03/2017.
 */
 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="division")
public class Division {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_division")
	private int Id;
	@Column(name="division")
	private String division;

}

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
@Table(name="agency")
public class Agency {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_agency")
	private int Id;
	@Column(name="agency")
	private String agency;

}

package com.nttdata.model;


import javax.persistence.*;

import lombok.Data;

import java.io.Serializable;

@Entity
@Data
public class User implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @SequenceGenerator(name = "SEQ_GEN", sequenceName = "SEQ_USER", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GEN")
    private Long id;
    private String userName;
    private String lastName;

    public User() {
    }

    public User(String userName, String lastName) {
        this.userName = userName;
        this.lastName = lastName;
    }



}

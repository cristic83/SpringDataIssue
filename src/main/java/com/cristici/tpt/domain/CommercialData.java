package com.cristici.tpt.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A CommercialData.
 */
@Entity
@Table(name = "commercial_data")
public class CommercialData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}

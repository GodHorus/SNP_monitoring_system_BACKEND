package com.example.master.model;

import jakarta.persistence.*;

@Entity
@Table(name = "blocks")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="block_name", nullable = false)
    private String blockName;

    public Block() {}
    public Block(String blockName) { this.blockName = blockName; }

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBlockName() { return blockName; }
    public void setBlockName(String blockName) { this.blockName = blockName; }
}

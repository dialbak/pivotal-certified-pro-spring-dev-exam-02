/*
Freeware License, some rights reserved

Copyright (c) 2019 Iuliana Cosmina

Permission is hereby granted, free of charge, to anyone obtaining a copy
of this software and associated documentation files (the "Software"),
to work with the Software within the limits of freeware distribution and fair use.
This includes the rights to use, copy, and modify the Software for personal use.
Users are also allowed and encouraged to submit corrections and modifications
to the Software for the benefit of other users.

It is not allowed to reuse,  modify, or redistribute the Software for
commercial use in any way, or for a user's educational materials such as books
or blog articles without prior permission from the copyright holder.

The above copyright notice and this permission notice need to be included
in all copies or substantial portions of the software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS OR APRESS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/
package com.apress.cems.dao;

import com.apress.cems.util.DateProcessor;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@MappedSuperclass
public abstract class AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(updatable = false)
    protected Long id;

    @Version
    protected int version;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateProcessor.DATE_FORMAT)
    @Column(name = "created_at", nullable = false)
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    protected LocalDateTime createdAt;


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateProcessor.DATE_FORMAT)
    @Column(name = "modified_at", nullable = false)
    @DateTimeFormat(pattern = DateProcessor.DATE_FORMAT)
    protected LocalDateTime modifiedAt;

    /**
     * Ce constructeur est requis par JPA. Toutes les sous-classes de cette classe hériteront de ce constructeur.
     */
    protected AbstractEntity() {
        createdAt = LocalDateTime.now();
        modifiedAt = LocalDateTime.now();
    }

    /**
     * Renvoie l'identifiant de l'entité. Cet identifiant est unique par entité. Il est utilisé par les frameworks de
     * persistance utilisés dans un projet,
     * et bien qu'il soit public, il ne doit pas être utilisé par le code d'application.
     * Cet identifiant est mappé par ORM (Object Relational Mapper) à la clé primaire de la base de données de
     * l'enregistrement Person auquel
     * l'instance d'entité est mappée.
     *
     * @return the unique entity identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Définit l'identifiant de l'entité. Cet identifiant est unique par entité. Est utilisé par les frameworks de persistance
     * et bien qu'il soit public, il ne doit jamais être défini par le code d'application.
     *
     * @param id the unique entity identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(LocalDateTime modifiedAt) {
        this.modifiedAt = modifiedAt;
    }

    // IDE generated methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        var that = (AbstractEntity) o;
        if (!Objects.equals(id, that.id)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @Override
    public String toString() {
        return String.format("AbstractEntity[id='%d%n', createdAt='%s', modifiedAt='%s', version='%d%n']",
                id, DateProcessor.toString(createdAt), DateProcessor.toString(modifiedAt), version);
    }
}

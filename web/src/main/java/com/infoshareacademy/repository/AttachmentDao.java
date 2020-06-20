package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Attachment;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class AttachmentDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Attachment save(String fileName) {
        Attachment attachment = new Attachment();
        attachment.setFileName(fileName);
        entityManager.persist(attachment);
        return attachment;
    }
}

package com.infoshareacademy.repository;

import com.infoshareacademy.domain.entity.Attachment;
import com.infoshareacademy.domain.entity.Event;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class AttachmentDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Inject
    EventDao eventDao;

    public Attachment save(String fileName) {
        Attachment attachment = new Attachment();
        attachment.setFileName(fileName);
        entityManager.persist(attachment);
        return attachment;
    }

    public void delete(Long eventId) {
        List<Attachment> attachments =  eventDao.findById(eventId).get().getAttachments();
        for(Attachment a : attachments) {
            a.setEvent(null);
            entityManager.remove(a);
        }
    }
}

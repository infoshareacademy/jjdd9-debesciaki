package com.infoshareacademy.mapper;

import com.infoshareacademy.domain.api.AttachmentJSON;
import com.infoshareacademy.domain.entity.Attachment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;

@Stateless
public class AttachmentMapper {
    private static final Logger STDLOG = LoggerFactory.getLogger(AttachmentMapper.class.getName());

    public AttachmentJSON daoToJson(Attachment attachment) {
        AttachmentJSON jsonAttachment = new AttachmentJSON();
        jsonAttachment.setFileName(attachment.getFileName());
        STDLOG.info("Success in mapping dao to json");
        return jsonAttachment;
    }

    public Attachment jsonToDao(AttachmentJSON attachment) {
        Attachment daoAttachment = new Attachment();
        daoAttachment.setFileName(attachment.getFileName());
        STDLOG.info("Success in mapping json to dao");
        return daoAttachment;
    }

}

package com.infoshareacademy.mapper;

import com.infoshareacademy.classJSONs.AttachmentJSON;
import com.infoshareacademy.entityDAO.Attachment;

import javax.ejb.Stateless;

@Stateless
public class AttachmentMapper {

    public AttachmentJSON daoToJson(Attachment attachment){
        AttachmentJSON jsonAttachment = new AttachmentJSON();
        jsonAttachment.setFileName(attachment.getFileName());
        return jsonAttachment;
    }

    public Attachment jsonToDao(Attachment attachment) {
        Attachment daoAttachment = new Attachment();
        daoAttachment.setFileName(attachment.getFileName());
        return daoAttachment;
    }

}

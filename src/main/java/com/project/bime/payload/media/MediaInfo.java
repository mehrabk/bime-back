package com.project.bime.payload.media;

import com.project.bime.model.extra.Attachment;
import lombok.Data;

@Data
public class MediaInfo {

    private long id;
    private String src;

    public MediaInfo(Attachment attachmentt) {
        setId(attachmentt.getId());
        setSrc(attachmentt.getSource());
    }
}

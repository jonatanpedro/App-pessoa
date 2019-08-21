package br.com.zeroquo.appPessoa.domain;

import java.time.ZonedDateTime;

public interface Auditable {

    ZonedDateTime getModificationTime();

    void setModificationTime(ZonedDateTime modificationTime);

    ZonedDateTime getCreationTime();

    void setCreationTime(ZonedDateTime creationTime);

    static void updateAuditable(Auditable source, Auditable target){
        target.setModificationTime(source.getModificationTime());
        target.setCreationTime(source.getCreationTime());
    }
}

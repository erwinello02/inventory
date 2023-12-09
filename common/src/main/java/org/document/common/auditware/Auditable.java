package org.document.common.auditware;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.Temporal;
import java.util.Date;
import static javax.persistence.TemporalType.TIMESTAMP;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public abstract class Auditable<U> {
    @CreatedBy
    private U createdBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date creationDate;

    @LastModifiedBy
    private U lastModifiedBy;

    @LastModifiedDate
    @Temporal(TIMESTAMP)
    private Date lastModifiedDate;
}

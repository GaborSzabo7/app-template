package hu.gaszabo.template.infrastructure.jpa.persistententity;

import static lombok.AccessLevel.PROTECTED;

import java.time.Instant;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters.InstantConverter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor(access = PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class AuditingPersistentEntity implements PersistentEntity {

	private static final long serialVersionUID = -3192817220578581819L;

	private static final int MAX_LENGTH = 240;

	@Column(name = "CREATED_BY", nullable = false, length = MAX_LENGTH)
	@CreatedBy
	private String createdBy;

	@Column(name = "CREATION_DATE", nullable = false)
	@Convert(converter = InstantConverter.class)
	@CreatedDate
	private Instant creationDate;

	@Column(name = "LAST_UPDATED_BY", nullable = false, length = MAX_LENGTH)
	@LastModifiedBy
	private String lastUpdatedBy;

	@Column(name = "LAST_UPDATE_DATE", nullable = false)
	@Convert(converter = InstantConverter.class)
	@LastModifiedDate
	private Instant lastUpdateDate;

	@Column(name = "VERSION", nullable = false)
	@Version
	private Long version;

}

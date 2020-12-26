package hu.gaszabo.template.infrastructure.jpa.persistententity;

import static javax.persistence.GenerationType.AUTO;
import static lombok.AccessLevel.PROTECTED;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.eclipse.persistence.annotations.UuidGenerator;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor(access = PROTECTED)
@MappedSuperclass
@UuidGenerator(name = "time-ordered-uuid")
public class TimeOrderedIdentityGeneratedPersistentEntity implements PersistentEntity {

	private static final long serialVersionUID = 4629290592133691984L;

	private static final int MAX_LENGTH = 128;

	@Id
	@GeneratedValue(generator = "time-ordered-uuid", strategy = AUTO)
	@Column(name = "ID", nullable = false, updatable = false, length = MAX_LENGTH)
	private String id;

}
